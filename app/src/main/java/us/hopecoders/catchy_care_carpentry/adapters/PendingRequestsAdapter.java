package us.hopecoders.catchy_care_carpentry.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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

import cn.pedant.SweetAlert.SweetAlertDialog;
import us.hopecoders.catchy_care_carpentry.R;
import us.hopecoders.catchy_care_carpentry.ui.Dashboard;
import us.hopecoders.catchy_care_carpentry.ui.Details;

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




            itemView.setOnClickListener(view -> {
                Log.v("tesssssssssssssssssst",request.toString());
                Intent goToDetails = new Intent(itemView.getContext(), Details.class);
                try{
                    goToDetails.putExtra("requestName", request.getName());
                    goToDetails.putExtra("taken", request.getIsTaken());
                    goToDetails.putExtra("requestDescription", request.getDescription());
                    goToDetails.putExtra("phone", request.getPhone());
                    goToDetails.putExtra("username", request.getUser().getUsername());
                    goToDetails.putExtra("cityName", request.getOurLocation().getCityName());
                    goToDetails.putExtra("countryName", request.getOurLocation().getCountryName());
                    goToDetails.putExtra("lat", request.getOurLocation().getLatitude());
                    goToDetails.putExtra("lng", request.getOurLocation().getLongitude());
                    goToDetails.putExtra("woodType", request.getFurnuture().getType());
                    goToDetails.putExtra("woodModel", request.getFurnuture().getModel());
                    goToDetails.putExtra("gasoline", request.getFurnuture().getWoodType());
                    Button btn= itemView.findViewById(R.id.UpdateButton);
                    btn.setEnabled(true);
                    itemView.getContext().startActivity(goToDetails);

                }
                catch(Exception e){
                    new SweetAlertDialog(itemView.getContext(), SweetAlertDialog.WARNING_TYPE)
                            .setTitleText("Oh .. No")
                            .setContentText("there is missing data ...")
                            .setConfirmText("OK, Never Mind")
                            .show();
                    Button btn= itemView.findViewById(R.id.UpdateButton);
                    btn.setEnabled(false);



                }
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
