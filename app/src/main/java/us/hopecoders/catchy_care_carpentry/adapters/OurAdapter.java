package us.hopecoders.catchy_care_carpentry.adapters;

import android.content.Context;
import android.content.Intent;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.amplifyframework.api.graphql.model.ModelMutation;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.Request;

import java.util.ArrayList;
import java.util.List;

import us.hopecoders.catchy_care_carpentry.R;
import us.hopecoders.catchy_care_carpentry.auth.Profile;
import us.hopecoders.catchy_care_carpentry.ui.Details;
import us.hopecoders.catchy_care_carpentry.ui.MainScreen;

public class OurAdapter extends RecyclerView.Adapter<OurAdapter.RequestViewHolder> {

    public final static String TAG = MainScreen.class.getSimpleName();
    List<Request> allRequests = new ArrayList<Request>();

    public OurAdapter(List<Request> list) {
        this.allRequests = list;
    }

    public static class RequestViewHolder extends RecyclerView.ViewHolder {
        public Request request;
        View itemView;

        public RequestViewHolder(@NonNull View itemView) {
            super(itemView);
            this.itemView = itemView;

            TextView textView =itemView.findViewById(R.id.deleteButton);

            textView.setOnClickListener((v)->{
                Amplify.API.mutate(ModelMutation.delete(request),
                        result -> {
                            Log.i("MyAmplifyApp", "Todo with id: " + result.getData().getId());
                            Intent goToProfile = new Intent(itemView.getContext(), Profile.class);
                            itemView.getContext().startActivity(goToProfile);
                        },
                        error -> {
                            Log.e("MyAmplifyApp", "Create failed", error);
                        }
                );

            });

            itemView.setOnClickListener(view -> {
                Intent goToDetails = new Intent(itemView.getContext(), Details.class);
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
                itemView.getContext().startActivity(goToDetails);
            });

        }
    }

    @NonNull
    @Override
    public RequestViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_requests_in_profile, parent, false);
        return new RequestViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RequestViewHolder holder, int position) {
        holder.request = allRequests.get(position);
        TextView requestName=holder.itemView.findViewById(R.id.requestNameInFragment);
        TextView description=holder.itemView.findViewById(R.id.descriptionInFragment);

        requestName.setText(holder.request.getName());
        description.setText(holder.request.getDescription());
    }

    @Override
    public int getItemCount() {
        return allRequests.size();
    }

}

