package com.mobileinformationsystems.alienreporter.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;
import com.mobileinformationsystems.alienreporter.R;
import com.mobileinformationsystems.alienreporter.adapters.ReportFormDataAdapter;
import com.mobileinformationsystems.alienreporter.beans.AlienReport;
import com.mobileinformationsystems.alienreporter.connector.HTTPConnector;
import com.mobileinformationsystems.alienreporter.helper.SimpleItemTouchHelperCallback;

public class AlienReporterFragment
    extends Fragment
{
    private TextView formId;
    private TextView lastChangedDate;
    private TextView lastChangedBy;
    private RecyclerView forms;
    private ConstraintLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
        @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        layout = (ConstraintLayout) inflater.inflate((
            R.layout.activity_main), container, false);

        formId = layout.findViewById(R.id.formIdText);
        lastChangedDate = layout.findViewById(R.id.lastChangedDateText);
        lastChangedBy = layout.findViewById(R.id.lastChangedByText);
        forms = layout.findViewById(R.id.forms);

        new HTTPConnector(this).execute(
                getString(R.string.url));
    }

    public void onProcessFinished(String json) {
        Gson gson = new Gson();
        AlienReport report = gson.fromJson(
                json, AlienReport.class);

        formId.setText(report.getFormId());
        lastChangedDate.setText(report.getLastChangedDate());
        lastChangedBy.setText(report.getLastChangedBy());
        ReportFormDataAdapter adapter = new ReportFormDataAdapter(report.getForm());
        forms.setLayoutManager(new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false));

        forms.setAdapter(adapter);
        ItemTouchHelper.Callback callback =
                new SimpleItemTouchHelperCallback(getBaseContext(), adapter);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(callback);
        itemTouchHelper.attachToRecyclerView(forms);
    }
}
