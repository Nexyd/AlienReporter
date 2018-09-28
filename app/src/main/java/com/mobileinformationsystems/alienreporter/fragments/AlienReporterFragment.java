package com.mobileinformationsystems.alienreporter.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
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
import com.mobileinformationsystems.alienreporter.beans.AlienReportForm;
import com.mobileinformationsystems.alienreporter.connector.HTTPConnector;
import com.mobileinformationsystems.alienreporter.connector.HTTPConnectorResponse;
import com.mobileinformationsystems.alienreporter.helper.SimpleItemTouchHelperCallback;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class AlienReporterFragment
    extends Fragment
{
    private List<AlienReportForm> reports;

    public static AlienReporterFragment newInstance(List<AlienReportForm> reports) {
        AlienReporterFragment fragment = new AlienReporterFragment();
        Bundle args = new Bundle();
        args.putSerializable("reports", (Serializable) reports);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable @Override
    public View onCreateView(
        @NonNull  LayoutInflater inflater,
        @Nullable ViewGroup container,
        @Nullable Bundle savedInstanceState)
    {
        Bundle args = getArguments();
        if ((args != null) && (args.getSerializable("reports") != null))
            reports = (List<AlienReportForm>) args.getSerializable("reports");
        else
            reports = new ArrayList<>();

        ConstraintLayout layout = (ConstraintLayout) inflater.inflate((
            R.layout.alien_report_fragment), container, false);

        RecyclerView forms = layout.findViewById(R.id.forms);
        ReportFormDataAdapter adapter = new ReportFormDataAdapter(this, reports);
        forms.setLayoutManager(new LinearLayoutManager(getContext(),
            LinearLayoutManager.VERTICAL, false));

        forms.setAdapter(adapter);
        ItemTouchHelper.Callback callback =
            new SimpleItemTouchHelperCallback(getContext(), adapter);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(callback);
        itemTouchHelper.attachToRecyclerView(forms);

        return layout;
    }
}
