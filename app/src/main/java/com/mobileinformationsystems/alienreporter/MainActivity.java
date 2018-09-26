package com.mobileinformationsystems.alienreporter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.gson.Gson;
import com.mobileinformationsystems.alienreporter.beans.AlienReport;
import com.mobileinformationsystems.alienreporter.connector.HTTPConnector;
import com.mobileinformationsystems.alienreporter.connector.HTTPConnectorResponse;

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

        formId = findViewById(R.id.formId);
        lastChangedDate = findViewById(R.id.lastChangedDate);
        lastChangedBy = findViewById(R.id.lastChangedBy);

        new HTTPConnector(this);
    }

    public void onProcessFinished(String json) {
        Gson gson = new Gson();
        AlienReport report = gson.fromJson(
            json, AlienReport.class);

        formId.setText(report.getFormId());
        lastChangedDate.setText(report.getLastChangedDate());
        lastChangedBy.setText(report.getLastChangedBy());
    }
}
