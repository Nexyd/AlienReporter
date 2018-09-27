package com.mobileinformationsystems.alienreporter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.mobileinformationsystems.alienreporter.adapters.ReportFormDataAdapter;
import com.mobileinformationsystems.alienreporter.beans.AlienReport;
import com.mobileinformationsystems.alienreporter.beans.AlienReportForm;
import com.mobileinformationsystems.alienreporter.connector.HTTPConnector;
import com.mobileinformationsystems.alienreporter.connector.HTTPConnectorResponse;

public class MainActivity
    extends AppCompatActivity
    implements HTTPConnectorResponse
{
    private TextView formId;
    private TextView lastChangedDate;
    private TextView lastChangedBy;
    private RecyclerView forms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        formId = findViewById(R.id.formIdText);
        lastChangedDate = findViewById(R.id.lastChangedDateText);
        lastChangedBy = findViewById(R.id.lastChangedByText);
        forms = findViewById(R.id.forms);

        new HTTPConnector(this).execute(
            getString(R.string.url));
    }

    public void onProcessFinished(String json) {
        Gson gson = new Gson();
        AlienReport report = gson.fromJson(
            json, AlienReport.class);

        String[] dataset = { "1", "2", "3" };
        formId.setText(report.getFormId());
        lastChangedDate.setText(report.getLastChangedDate());
        lastChangedBy.setText(report.getLastChangedBy());

        forms.setLayoutManager(new LinearLayoutManager(this));
        forms.setAdapter(new ReportFormDataAdapter(dataset));
    }
}
