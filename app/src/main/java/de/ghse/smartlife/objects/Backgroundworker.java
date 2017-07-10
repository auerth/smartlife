package de.ghse.smartlife.objects;

import android.os.AsyncTask;


public class Backgroundworker extends AsyncTask<Void, Void, String> {


    /**
     * Call on AsyncTask start
     *
     * @param v
     */
    @Override
    protected String doInBackground(Void... v) {
        asyncTask();
        return "";
    }

    /**
     * Method to override main task
     */
    public void asyncTask() {

    }

    /**
     * Called before doInBackground method
     */
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    /**
     * Called after doInBackground method
     *
     * @param v string of doInBackGround
     */
    @Override
    protected void onPostExecute(String v) {
        super.onPostExecute(v);
    }

    /**
     * Called when progress changed
     *
     * @param values
     */
    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}
