package us.hopecoders.catchy_care_carpentry.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.amplifyframework.api.graphql.model.ModelMutation;
import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.Request;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import us.hopecoders.catchy_care_carpentry.R;
import us.hopecoders.catchy_care_carpentry.ui.Dashboard;

public class PendingRequestsAdapter extends RecyclerView.Adapter<PendingRequestsAdapter.PendingRequestsHolder> {

    List<Request> requestList = new ArrayList<Request>();
    Context context;

    public PendingRequestsAdapter(List<Request> requestList , Context context) {
        this.requestList = requestList;
        this.context = context;
    }

    public static class PendingRequestsHolder extends RecyclerView.ViewHolder {
        public Request request;
        View itemView;

        public PendingRequestsHolder(@NonNull View itemView) {
            super(itemView);
            this.itemView = itemView;

            TextView UpdateButton = itemView.findViewById(R.id.UpdateButton);
            UpdateButton.setOnClickListener((v) -> {
                Amplify.API.query(
                        ModelQuery.get(Request.class, request.getId()),
                        response -> {
                            Log.i("MyAmplifyApp", "UpdateQuery");
                            Request requestUpdate = response.getData().copyOfBuilder()
                                    .isTaken(true)
                                    .build();
                            Amplify.API.mutate(ModelMutation.update(requestUpdate),
                                    response1 -> {
                                        Log.i("MyAmplifyApp", "Updated Todo with id: " + response1.getData().getId());
                                        handler();
                                    },
                                    error -> Log.e("MyAmplifyApp", "Update failed", error)
                            );
                        },
                        error -> Log.e("MyAmplifyApp", "Query failure", error)
                );
            });
        }

        public void handler() {
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                @Override
                public void run() {
                    itemView.getContext().startActivity(new Intent(itemView.getContext(), Dashboard.class));
                }
            });
        }
    }

    @NonNull
    @NotNull
    @Override
    public PendingRequestsHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_pending_request, parent, false);
        PendingRequestsAdapter.PendingRequestsHolder pendingRequestsHolder = new PendingRequestsAdapter.PendingRequestsHolder(view);
        return pendingRequestsHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull PendingRequestsHolder holder, int position) {
        holder.request = requestList.get(position);
        TextView name = holder.itemView.findViewById(R.id.pendingName);
        TextView description = holder.itemView.findViewById(R.id.pendingDescription);
        TextView phone = holder.itemView.findViewById(R.id.pendingPhone);
        TextView location = holder.itemView.findViewById(R.id.pendingLocation);
        name.setText(holder.request.getName());
        description.setText(holder.request.getDescription());
        phone.setText(holder.request.getPhone());
        location.setText(holder.request.getOurLocation().getCityName());
    }

    @Override
    public int getItemCount() {
        return requestList.size();
    }
}
