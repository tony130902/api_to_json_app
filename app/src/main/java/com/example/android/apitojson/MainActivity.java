package com.example.android.apitojson;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Start the AsyncTask to fetch the earthquake data


        Button convert_button = (Button) findViewById(R.id.convert_button);
        convert_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                EditText api = (EditText) findViewById(R.id.api_text);
                String url_text = api.getText().toString();
                EarthquakeAsyncTask task = new EarthquakeAsyncTask();
                task.execute(url_text);
            }
        });
    }


    public class EarthquakeAsyncTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... urls) {
            // Don't perform the request if there are no URLs, or the first URL is null.
            if (urls.length < 1 || urls[0] == null) {
                return null;
            }

            String result = QueryUtils.fetchEarthquakeData(urls[0]);
            return result;
        }

        @Override
        protected void onPostExecute(String data) {
            TextView json_text = findViewById(R.id.json_textview);
            json_text.setText(data);
        }
    }


}