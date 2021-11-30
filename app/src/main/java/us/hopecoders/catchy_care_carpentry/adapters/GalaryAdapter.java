package us.hopecoders.catchy_care_carpentry.adapters;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.Dashbord;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import us.hopecoders.catchy_care_carpentry.R;
import us.hopecoders.catchy_care_carpentry.ui.Galary;


public class GalaryAdapter extends RecyclerView.Adapter<GalaryAdapter.GalaryViewHolder> {
    List<Dashbord> dashbordList = new ArrayList<Dashbord>();

    Context context;


    public GalaryAdapter(List<Dashbord> dashbordList , Context context) {
        this.dashbordList = dashbordList;
        this.context = context;
    }
    public static class GalaryViewHolder extends RecyclerView.ViewHolder {
        public Dashbord dashbord;
        View itemView;

        public GalaryViewHolder(@NonNull View itemView) {
            super(itemView);
            this.itemView=itemView;
            System.out.println("dassshhhhhhhhhhhhhhhhhhhhhhhhhh"+dashbord);
            List<Dashbord> listOfTasks = new ArrayList<>();

            Amplify.API.query(
                    ModelQuery.list(Dashbord.class),
                    response -> {
                        for (Dashbord task : response.getData()) {
                           listOfTasks.add(task);
                            Log.v("MyAmplifyApp", task.getImageUrl());
                        }


                    },
                    error -> Log.e("MyAmplifyApp", "Query failure", error)
            );
        }
        public void handler() {
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                @Override
                public void run() {
                    itemView.getContext().startActivity(new Intent(itemView.getContext(), Galary.class));
                }
            });
        }
    }

    @NonNull
    @Override
    public GalaryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_galary, parent, false);
        GalaryViewHolder galaryViewHolder = new GalaryViewHolder(view);
        return galaryViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull GalaryViewHolder holder, int position) {
        holder.dashbord = dashbordList.get(position);
        ImageView imageView = holder.itemView.findViewById(R.id.imageView7);
        Picasso.get().load(holder.dashbord.getImageUrl()).into(imageView);

    }

    @Override
    public int getItemCount() {
        return dashbordList.size();
    }


}
