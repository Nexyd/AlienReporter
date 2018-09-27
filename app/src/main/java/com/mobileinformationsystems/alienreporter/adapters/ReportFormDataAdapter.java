package com.mobileinformationsystems.alienreporter.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mobileinformationsystems.alienreporter.R;

public class ReportFormDataAdapter
    extends RecyclerView.Adapter<ReportFormDataAdapter.ViewHolder>
{
    private String[] dataset;

    static class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView view;
        ViewHolder(View view) {
            super(view);
            this.view = view.findViewById(R.id.text);
        }
    }

    public ReportFormDataAdapter(String[] dataset) {
        this.dataset = dataset;
    }

    @NonNull @Override
    public ReportFormDataAdapter.ViewHolder onCreateViewHolder(
        @NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(
            R.layout.report_form_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.view.setText(dataset[position]);
    }

    @Override
    public int getItemCount() {
        return dataset.length;
    }
}