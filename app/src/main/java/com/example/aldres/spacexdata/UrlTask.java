package com.example.aldres.spacexdata;

import android.os.AsyncTask;

import java.util.List;

/**
 * Created by Aldres on 19.01.2018.
 */

public abstract class UrlTask extends AsyncTask<String, String, List<DataModel>> {

    @Override
    protected List<DataModel> doInBackground(String... params) {

        JSONhelper jsoNhelper = new JSONhelper();
        List<DataModel> data = jsoNhelper.getDataFromUrl(params[0]);

        return data;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }
}
