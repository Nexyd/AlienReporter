package com.mobileinformationsystems.alienreporter.connector;

import android.os.AsyncTask;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class HTTPConnector extends AsyncTask<String, Void, String> {
    private HTTPConnectorResponse delegate;

    public HTTPConnector(HTTPConnectorResponse delegate) {
        this.delegate = delegate;
    }

    @Override
    protected String doInBackground(String... params) {
        try {
            URL url = new URL(params[0]);
            HttpURLConnection connection = connect(url);
            BufferedInputStream stream = new BufferedInputStream(connection.getInputStream());
            Scanner scanner = new Scanner(stream).useDelimiter("\\A");

            String result = "";
            while (scanner.hasNext()) {
                result = scanner.next() + "\n";
            }

            return result;

        } catch (IOException exception) {
            return exception.getMessage();
        } catch (Exception exception) {
            return exception.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String json) {
        delegate.onProcessFinished(json);
    }

    private HttpURLConnection connect(URL url) throws IOException {
        HttpURLConnection connection =
            (HttpURLConnection) url.openConnection();

        connection.setRequestMethod("GET");
        connection.setConnectTimeout(5000);
        connection.setRequestProperty(
            "Content-Type", "application/json");

        return connection;
    }
}