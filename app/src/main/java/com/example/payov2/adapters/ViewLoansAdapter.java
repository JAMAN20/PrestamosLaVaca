package com.example.payov2.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.payov2.R;
import com.example.payov2.activities.UpdateActivity;
import com.example.payov2.activities.UpdateLoanActivity;

import java.util.ArrayList;

public class ViewLoansAdapter extends RecyclerView.Adapter<ViewLoansAdapter.MyViewHolder> {

    private static Context context;
    static Activity activity;
    static ArrayList loan_Id, clientFullName, clientDirection, paymentFrecuency, startDate, dueDate, capital, applyInterestTo, interest, numberOfPayments,
                    applyInterestForDelay, interestForDelay, graceDays, total, quotaValue;
    public ViewLoansAdapter(Activity activity, Context context, ArrayList loan_Id, ArrayList clientFullName, ArrayList clientDirection, ArrayList paymentFrecuency, ArrayList startDate, ArrayList dueDate,
                            ArrayList capital, ArrayList applyInterestTo, ArrayList interest, ArrayList numberOfPayments, ArrayList applyInterestForDelay,
                            ArrayList interestForDelay, ArrayList graceDays, ArrayList total, ArrayList quotaValue)
    {
        this.context = context;
        this.activity = activity;
        this.loan_Id = loan_Id;
        this.clientFullName = clientFullName;
        this.clientDirection = clientDirection;
        this.paymentFrecuency = paymentFrecuency;
        this.startDate = startDate;
        this.dueDate = dueDate;
        this.capital = capital;
        this.applyInterestTo = applyInterestTo;
        this.interest = interest;
        this.numberOfPayments = numberOfPayments;
        this.applyInterestForDelay = applyInterestForDelay;
        this.interestForDelay = interestForDelay;
        this.graceDays = graceDays;
        this.total = total;
        this.quotaValue = quotaValue;
    }

    @NonNull
    @Override
    public ViewLoansAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.list_item_view_loans, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewLoansAdapter.MyViewHolder holder, int position) {
        holder.txtLoan_Id.setText(String.valueOf(loan_Id.get(position)));
        holder.txtClientName.setText(String.valueOf(clientFullName.get(position)));
        holder.txtClientDirection.setText(String.valueOf(clientDirection.get(position)));
    }

    @Override
    public int getItemCount() {
        return loan_Id.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView txtLoan_Id, txtClientName, txtClientDirection;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            txtLoan_Id = itemView.findViewById(R.id.viewLoanId_viewLoans);
            txtClientName = itemView.findViewById(R.id.viewNombreCliente_viewLoans);
            txtClientDirection = itemView.findViewById(R.id.viewDirectionCliente_viewLoans);

            itemView.setOnClickListener(v -> {
                Intent intent = new Intent(context, UpdateLoanActivity.class);
                intent.putExtra("Loan_Id", txtLoan_Id.getText());
                intent.putExtra("ClientName", clientFullName.get(getAdapterPosition()).toString());
                intent.putExtra("ClientDirection", clientDirection.get(getAdapterPosition()).toString());
                intent.putExtra("PaymentFrecuency", paymentFrecuency.get(getAdapterPosition()).toString());
                intent.putExtra("StartDate", startDate.get(getAdapterPosition()).toString());
                intent.putExtra("DueDate", dueDate.get(getAdapterPosition()).toString());
                intent.putExtra("Capital", capital.get(getAdapterPosition()).toString());
                intent.putExtra("ApplyInterestTo", applyInterestTo.get(getAdapterPosition()).toString());
                intent.putExtra("Interest", interest.get(getAdapterPosition()).toString());
                intent.putExtra("NumberOfPayments", numberOfPayments.get(getAdapterPosition()).toString());
                intent.putExtra("ApplyInterestForDelay", applyInterestForDelay.get(getAdapterPosition()).toString());
                intent.putExtra("InterestForDelay", interestForDelay.get(getAdapterPosition()).toString());
                intent.putExtra("GraceDays", graceDays.get(getAdapterPosition()).toString());
                intent.putExtra("Total", total.get(getAdapterPosition()).toString());
                intent.putExtra("QuotaValues", quotaValue.get(getAdapterPosition()).toString());
                activity.startActivityForResult(intent, 1);
            });
        }
    }
}
