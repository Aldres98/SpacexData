package com.example.aldres.spacexdata;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aldres on 19.01.2018.
 */

public class JSONhelper {

    private HttpURLConnection connection;
    private List<DataModel> data;

    public List<DataModel> getDataFromUrl(String urlString) {

        try {
            URL url = new URL(urlString);

            connection = (HttpURLConnection) url.openConnection();
            connection.connect();
            InputStream inputStream = connection.getInputStream();

            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line = "";
            StringBuffer buffer = new StringBuffer();
            while ((line = reader.readLine()) != null) {

                buffer.append(line);
                String result = buffer.toString();
                JSONArray jsonArray = new JSONArray(result);
                data = new ArrayList<>();

                for (int i = 0; i < buffer.length(); i++) {

                    DataModel dataModel = new DataModel();
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    JSONObject rocketInfo = jsonObject.getJSONObject("rocket");
                    JSONObject linksInfo = jsonObject.getJSONObject("links");

                    String articleLink = linksInfo.getString("article_link");
                    String videoLink = linksInfo.getString("video_link");
                    String rocketName = rocketInfo.getString("rocket_name");
                    long launchDateUnix = jsonObject.getLong("launch_date_unix");
                    String missionPatch = linksInfo.getString("mission_patch");
                    String details = jsonObject.getString("details");
                    int flightNumber = jsonObject.getInt("flight_number");

                    dataModel.setRocketName(rocketName);
                    dataModel.setLaunchDateUnix(launchDateUnix);
                    dataModel.setMissionPatch(missionPatch);
                    dataModel.setArticleLink(articleLink);
                    dataModel.setVideoLink(videoLink);
                    dataModel.setDetails(details);
                    dataModel.setFlightNumber(flightNumber);

                    data.add(dataModel);
                }
            }

        } catch (JSONException | IOException e) {
            e.printStackTrace();
        }

        return data;
    }
}
