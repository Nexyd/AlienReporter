package com.mobileinformationsystems.alienreporter.adapters;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.mobileinformationsystems.alienreporter.R;
import com.mobileinformationsystems.alienreporter.beans.AlienReportForm;
import com.mobileinformationsystems.alienreporter.callbacks.ItemTouchHelperAdapter;
import com.mobileinformationsystems.alienreporter.dialog.FormDialog;
import com.mobileinformationsystems.alienreporter.fragments.AlienReporterFragment;

import java.io.Serializable;
import java.util.List;

public class ReportFormDataAdapter
    extends RecyclerView.Adapter<ReportFormDataAdapter.ViewHolder>
    implements ItemTouchHelperAdapter
{
    private List<AlienReportForm> reportFormList;
    private Fragment fragment;

    public ReportFormDataAdapter(Fragment fragment, List<AlienReportForm> dataset) {
        this.fragment = fragment;
        this.reportFormList = dataset;
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
        holder.view.setText(reportFormList.get(position).getId());
    }

    @Override
    public void onItemMove(int fromPosition, int toPosition) {
        AlienReportForm prev = reportFormList.remove(fromPosition);

        try {
            reportFormList.add(toPosition, prev);
        } catch (IndexOutOfBoundsException ex) {
            reportFormList.add(0, prev);
        }

        notifyItemMoved(fromPosition, toPosition);
    }

    @Override
    public int getItemCount() {
        return reportFormList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder
    {
        Button view;
        ViewHolder(View view) {
            super(view);

            this.view = view.findViewById(R.id.subFormId);
            this.view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    FormDialog dialog = new FormDialog();
                    Bundle args = new Bundle();
                    args.putSerializable("reports", (Serializable) reportFormList);
                    args.putCharSequence("searchId", ((TextView)view).getText());

                    dialog.setArguments(args);
                    dialog.setTargetFragment(fragment, 1);
                    dialog.show(fragment.getFragmentManager(),
                        "report_form_dialog");
                }
            });
        }
    }
}