package com.mobileinformationsystems.alienreporter;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.mobileinformationsystems.alienreporter.adapters.ReportFormDataAdapter;
import com.mobileinformationsystems.alienreporter.beans.AlienReport;
import com.mobileinformationsystems.alienreporter.connector.HTTPConnector;
import com.mobileinformationsystems.alienreporter.connector.HTTPConnectorResponse;
import com.mobileinformationsystems.alienreporter.fragments.AlienReporterFragment;
import com.mobileinformationsystems.alienreporter.helper.SimpleItemTouchHelperCallback;

import java.text.DateFormat;

public class MainActivity
    extends AppCompatActivity
    implements HTTPConnectorResponse
{
    private TextView formId;
    private TextView lastChangedDate;
    private TextView lastChangedBy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        formId = findViewById(R.id.formIdText);
        lastChangedDate = findViewById(R.id.lastChangedDateText);
        lastChangedBy = findViewById(R.id.lastChangedByText);

        new HTTPConnector(this).execute(
            getString(R.string.url));
    }

    public void onProcessFinished(String json) {
        Gson gson = new Gson();
        AlienReport report = gson.fromJson(
            json, AlienReport.class);

//        String date = DateFormat.getDateTimeInstance(
//            DateFormat.SHORT, DateFormat.MEDIUM)
//            .format(report.getLastChangedDate());

        formId.setText(report.getFormId());
        lastChangedDate.setText(report.getLastChangedDate());
        lastChangedBy.setText(report.getLastChangedBy());

        AlienReporterFragment fragment =
            AlienReporterFragment.newInstance(report.getForm());

        FragmentTransaction fragmentTransaction =
            getSupportFragmentManager().beginTransaction();

        fragmentTransaction.replace(R.id.workarea_placeholder, fragment);
        fragmentTransaction.commitAllowingStateLoss();
    }
}