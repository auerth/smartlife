package de.ghse.smarthome.objects;

import android.os.AsyncTask;

/**
 * Created by thorbi on 6/20/17.
 */

public class Backgroundworker extends AsyncTask<String, String, String> {

    @Override
    protected String doInBackground(String... urls) {
        asyncTask();

        return "";
    }


    public void asyncTask() {

    }

    @Override
    protected void onPreExecute() {

        //BEFOR AUSFÜHREN

    }

    @Override
    protected void onPostExecute(String result) {

        //NACH AUSFÜHREN

    }

    @Override
    protected void onProgressUpdate(String... values) {
        super.onProgressUpdate(values);
    }
}
