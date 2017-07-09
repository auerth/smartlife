package de.ghse.smartlife.objects;

import android.os.AsyncTask;


public class Backgroundworker extends AsyncTask<String, String, String> {
    /*
    *Call on AsyncTask start
     */
    @Override
    protected String doInBackground(String... urls) {
        asyncTask();
        return "";
    }

    /*
    *Method to override main task
     */
    public void asyncTask() {

    }

    /*
    *Called before doInBackground method
     */
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    /*
    *Called after doInBackground method
    */
    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
    }

    /*
    *Called when progress changed
    */
    @Override
    protected void onProgressUpdate(String... values) {
        super.onProgressUpdate(values);
    }
}
