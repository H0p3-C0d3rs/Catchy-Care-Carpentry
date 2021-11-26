package us.hopecoders.catchy_care_carpentry.adapters;

        import android.content.Intent;
        import android.content.SharedPreferences;
        import android.graphics.Color;
        import android.preference.PreferenceManager;
        import android.util.Log;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.CheckBox;
        import android.widget.CheckedTextView;
        import android.widget.RadioButton;
        import android.widget.TextView;
        import android.widget.Toast;

        import androidx.annotation.NonNull;
        import androidx.recyclerview.widget.RecyclerView;

        import com.amplifyframework.api.graphql.model.ModelMutation;
        import com.amplifyframework.core.Amplify;
        import com.amplifyframework.datastore.generated.model.Furnuture;

        import org.jetbrains.annotations.NotNull;

        import java.util.ArrayList;
        import java.util.List;

        import us.hopecoders.catchy_care_carpentry.R;


public class FurnitureAdapter extends RecyclerView.Adapter<FurnitureAdapter.FurViewHolder> {

    List<Furnuture> furList = new ArrayList<Furnuture>();

    public FurnitureAdapter(List<Furnuture> furList) {
        this.furList = furList;
    }

    public static class FurViewHolder extends RecyclerView.ViewHolder {
        public Furnuture furnuture;
        View itemView;

        public FurViewHolder(@NonNull View itemView) {
            super(itemView);
            this.itemView = itemView;

            TextView carDeleteButton =itemView.findViewById(R.id.DeleteButton);

            carDeleteButton.setOnClickListener((v)->{
                Amplify.API.mutate(ModelMutation.delete(furnuture),
                        result -> {
                            Log.i("MyAmplifyApp", "Todo with id: " + result.getData().getId());
                        },
                        error -> {
                            Log.e("MyAmplifyApp", "Create failed", error);
                        }
                );

            });

            itemView.setOnClickListener(view -> {
                itemView.setBackgroundColor(Color.parseColor("#FFFFFF"));
                SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(view.getContext());
                SharedPreferences.Editor sharedPreferencesEditor = sharedPreferences.edit();
                sharedPreferencesEditor.putString("carId", furnuture.getId());
                Toast.makeText(itemView.getContext(),  furnuture.getType()+ "-" +furnuture.getModel()+" Selected Successfully!", Toast.LENGTH_LONG).show();
                sharedPreferencesEditor.apply();
                itemView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        view.setBackgroundColor(Color.parseColor("#042C5A"));
                    }
                }, 150);
            });
        }
    }

    @NonNull
    @Override
    public FurViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_furniture, parent, false);
        FurViewHolder furViewHolder = new FurViewHolder(view);
        return furViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull FurViewHolder holder, int position) {
        holder.furnuture = furList.get(position);
        TextView type = holder.itemView.findViewById(R.id.typeInFrag);
        TextView model = holder.itemView.findViewById(R.id.modelInFrag);
        type.setText(holder.furnuture.getType());
        model.setText(holder.furnuture.getModel());
    }

    @Override
    public int getItemCount() {
        return furList.size();
    }

}
