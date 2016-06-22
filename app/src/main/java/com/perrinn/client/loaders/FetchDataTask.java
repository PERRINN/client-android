package com.perrinn.client.loaders;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class FetchDataTask extends AsyncTask {

    private final String LOG_TAG = FetchDataTask.class.getSimpleName();
    private final String API_URL = "http://10.0.2.2:8080/greeting"; // this might be changed to a parameter
    public FetchDataTask() {

    }


    @Override
    protected Object doInBackground(Object[] objects) {

        // These two need to be declared outside the try/catch
        // so that they can be closed in the finally block.
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String responseStr;

        try {
            URL url = new URL(API_URL);

            // Create the request to OpenWeatherMap, and open the connection
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            // Read the input stream into a String
            InputStream inputStream = urlConnection.getInputStream();
            StringBuffer buffer = new StringBuffer();
            if (inputStream == null) {
                // Nothing to do.
                responseStr = null;
            }
            reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null) {
                // Since it's JSON, adding a newline isn't necessary (it won't affect parsing)
                // But it does make debugging a *lot* easier if you print out the completed
                // buffer for debugging.
                buffer.append(line + "\n");
            }

            if (buffer.length() == 0) {
                // Stream was empty.  No point in parsing.
                return null;
            }
            responseStr = buffer.toString();
            Log.v(LOG_TAG, "API Greeting response : " + responseStr);

        } catch (IOException e) {
            Log.v(LOG_TAG,"Impossible to open nor use the stream.");
            responseStr = null;
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (final IOException e) {
                    Log.v(LOG_TAG,"Impossible to close the stream.");
                }
            }
        }

        return null;
    }

}
