package com.mobileinformationsystems.alienreporter.adapters;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.mobileinformationsystems.alienreporter.R;
import com.mobileinformationsystems.alienreporter.beans.AlienReportForm;
import com.mobileinformationsystems.alienreporter.callbacks.ItemTouchHelperAdapter;
import com.mobileinformationsystems.alienreporter.dialog.FormDialog;

import java.util.List;

public class ReportFormDataAdapter
    extends RecyclerView.Adapter<ReportFormDataAdapter.ViewHolder>
    implements ItemTouchHelperAdapter
{
    private List<AlienReportForm> reportFormList;

    public ReportFormDataAdapter(List<AlienReportForm> dataset) {
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

    static class ViewHolder
        extends RecyclerView.ViewHolder
    {
        Button view;
        ViewHolder(View view) {
            super(view);
            this.view = view.findViewById(R.id.subFormId);

            this.view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FormDialog dialog = new FormDialog();
                    Bundle bundle = new Bundle();
//                    bundle.putParcelable(StaticValues.USER, loggedUser);
//
//                    dialog.setArguments(bundle);
//                    dialog.setTargetFragment(UrgentRequestFragment.this,
//                        StaticValues.REQUEST_URGENT_ADD_OTHER_USERS);
//
//                    dialog.show(getChildFragmentManager(),
//                        StaticValues.DIALOG_OTHER_USERS);
                }
            });
        }
    }
}