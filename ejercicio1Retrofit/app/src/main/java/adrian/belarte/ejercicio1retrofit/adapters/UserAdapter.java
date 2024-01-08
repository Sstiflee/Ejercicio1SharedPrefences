package adrian.belarte.ejercicio1retrofit.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import adrian.belarte.ejercicio1retrofit.R;
import adrian.belarte.ejercicio1retrofit.modelos.DataItem;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserVH> {
    private List<DataItem> objects;
    private int resource;
    private Context context;

    public UserAdapter(List<DataItem> objects, int resource, Context context) {
        this.objects = objects;
        this.resource = resource;
        this.context = context;
    }


    @NonNull
    @Override
    public UserVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View userView = LayoutInflater.from(context).inflate(resource,null);
        userView.setLayoutParams(new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        return new UserVH(userView);
    }

    @Override
    public void onBindViewHolder(@NonNull UserVH holder, int position) {
        DataItem item = objects.get(position);

        holder.lbNombre.setText(item.getFirstName());
        holder.lbApellidos.setText(item.getLastName());
        holder.lbEmail.setText(item.getEmail());

        Picasso.get()
                .load(item.getAvatar())
                .error(R.drawable.ic_launcher_background)
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(holder.imgUser);
    }

    @Override
    public int getItemCount() {
        return objects.size();
    }

    public class UserVH extends RecyclerView.ViewHolder{
        ImageView imgUser;
        TextView lbNombre;
        TextView lbApellidos;
        TextView lbEmail;
        public UserVH(@NonNull View itemView) {
            super(itemView);
            imgUser = itemView.findViewById(R.id.imgImagenUserCard);
            lbNombre = itemView.findViewById(R.id.lbNombreUserCard);
            lbApellidos = itemView.findViewById(R.id.lbApellidosUserCard);
            lbEmail = itemView.findViewById(R.id.lbEmailUserCard);
        }
    }
}
