package com.example.payov2.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.payov2.R;
import com.example.payov2.activities.UpdateActivity;

import java.util.ArrayList;

public class ClientsAdapater extends RecyclerView.Adapter<ClientsAdapater.MyViewHolder> {

    private static Context context;
    static Activity activity;
    static ArrayList id, fullName, identification, phone, homePhone, mail, direction, refFullName, refPhone, refDirection;
    public ClientsAdapater(Activity activity, Context context, ArrayList id, ArrayList fullName, ArrayList identification, ArrayList phone, ArrayList homePhone, ArrayList mail, ArrayList direction, ArrayList refFullName, ArrayList refPhone,
                           ArrayList refDirection)
    {
        this.context = context;
        this.activity = activity;
        this.id = id;
        this.fullName = fullName;
        this.identification = identification;
        this.phone = phone;
        this.homePhone = homePhone;
        this.mail = mail;
        this.direction = direction;
        this.refFullName = refFullName;
        this.refPhone = refPhone;
        this.refDirection = refDirection;
    }

    @NonNull
    @Override
    public ClientsAdapater.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.list_item_clients, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ClientsAdapater.MyViewHolder holder, int position) {
        holder.txtId.setText(String.valueOf(id.get(position)));
        holder.txtFullName.setText(String.valueOf(fullName.get(position)));
        holder.txtDirection.setText(String.valueOf(direction.get(position)));

    }

    @Override
    public int getItemCount() {
        return id.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txtId, txtFullName, txtDirection;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            txtId = itemView.findViewById(R.id.viewId);
            txtFullName = itemView.findViewById(R.id.viewNombre);
            txtDirection = itemView.findViewById(R.id.viewDireccion);

            itemView.setOnClickListener(v -> {
                Intent intent = new Intent(context, UpdateActivity.class);
                intent.putExtra("Id", txtId.getText());
                intent.putExtra("FullName", fullName.get(getAdapterPosition()).toString());
                intent.putExtra("Identification", identification.get(getAdapterPosition()).toString());
                intent.putExtra("Phone", phone.get(getAdapterPosition()).toString());
                intent.putExtra("HomePhone", homePhone.get(getAdapterPosition()).toString());
                intent.putExtra("Mail", mail.get(getAdapterPosition()).toString());
                intent.putExtra("Direction", direction.get(getAdapterPosition()).toString());
                intent.putExtra("RefFullName", refFullName.get(getAdapterPosition()).toString());
                intent.putExtra("RefPhone", refPhone.get(getAdapterPosition()).toString());
                intent.putExtra("RefDirection", refDirection.get(getAdapterPosition()).toString());
                activity.startActivityForResult(intent, 1);
            });

        }
    }
}
