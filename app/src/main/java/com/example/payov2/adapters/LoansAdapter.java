package com.example.payov2.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.payov2.R;
import com.example.payov2.activities.ConfiguracionActivity;
import com.example.payov2.activities.CreateLoansActivity;

import java.util.ArrayList;

public class LoansAdapter extends RecyclerView.Adapter<LoansAdapter.MyViewHolder> {

    private static Context context;
    static Activity activity;
    static ArrayList client_Id, client_FullName, clientDirection_client;

    public LoansAdapter(Activity activity, Context context, ArrayList client_Id, ArrayList client_FullName, ArrayList clientDirection_client)
    {
        this.context = context;
        this.activity = activity;
        this.client_Id = client_Id;
        this.client_FullName = client_FullName;
        this.clientDirection_client = clientDirection_client;
    }

    @NonNull
    @Override
    public LoansAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.list_item_clients, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LoansAdapter.MyViewHolder holder, int position) {
        holder.clientId.setText(String.valueOf(client_Id.get(position)));
        holder.clientFullName.setText(String.valueOf(client_FullName.get(position)));
        holder.clientDirection.setText(String.valueOf(clientDirection_client.get(position)));
    }

    @Override
    public int getItemCount() {
        return client_Id.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView clientId, clientFullName, clientDirection;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            clientId = itemView.findViewById(R.id.viewId);
            clientFullName = itemView.findViewById(R.id.viewNombre);
            clientDirection = itemView.findViewById(R.id.viewDireccion);

            itemView.setOnClickListener(v -> {
                Intent intent = new Intent(context, CreateLoansActivity.class);
                intent.putExtra("ClientFullName", client_FullName.get(getAdapterPosition()).toString());
                intent.putExtra("ClientDirection", clientDirection_client.get(getAdapterPosition()).toString());
                activity.startActivityForResult(intent, 1);
            });
        }
    }
}
