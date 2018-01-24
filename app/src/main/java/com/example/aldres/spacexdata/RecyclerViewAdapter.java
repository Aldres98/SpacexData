package com.example.aldres.spacexdata;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import static com.example.aldres.spacexdata.UnixDateConverter.convertDate;

/**
 * Created by Aldres on 19.01.2018.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.DataViewHolder> {

    List<DataModel> recyclerData;


    @Override
    public RecyclerViewAdapter.DataViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        DataViewHolder holder = new DataViewHolder(view);
        return holder;
    }

    public RecyclerViewAdapter(List<DataModel> recyclerData) {
        this.recyclerData = recyclerData;
    }


    @Override
    public void onBindViewHolder(RecyclerViewAdapter.DataViewHolder holder, int position) {
        DataModel dataModel = recyclerData.get(position);
        DownloadImageTask iconDownloader = new DownloadImageTask(holder.missionPatch);
        iconDownloader.execute(dataModel.getMissionPatch());
        holder.rocketName.setText(dataModel.getRocketName());
        holder.launchDate.setText(convertDate(dataModel.getLaunchDateUnix()));
        if (!dataModel.getDetails().equals("null"))
            holder.additionalInfo.setText(dataModel.getDetails());
        else holder.additionalInfo.setText("No additional information provided");
    }

    @Override
    public int getItemCount() {
        return recyclerData.size();
    }

    class DataViewHolder extends RecyclerView.ViewHolder {
        TextView rocketName, launchDate, additionalInfo;
        ImageView missionPatch;

        public DataViewHolder(View dataView) {
            super(dataView);
            rocketName = dataView.findViewById(R.id.rocket_name);
            missionPatch = dataView.findViewById(R.id.mission_patch);
            launchDate = dataView.findViewById(R.id.launch_date);
            additionalInfo = dataView.findViewById(R.id.additional_info);

            dataView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();

                    if (pos != RecyclerView.NO_POSITION) {
                        DataModel clickedDataItem = recyclerData.get(pos);
                        String videoLink = clickedDataItem.getVideoLink();
                        String articleLink = clickedDataItem.getArticleLink();
                        AlertDialog linksDialog = createLinksDialog(v, articleLink, videoLink);
                        linksDialog.show();


                    }
                }
            });
        }
    }

    private AlertDialog createLinksDialog(final View v, final String articleLink, final String videoLink) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(v.getContext());
        dialogBuilder.setTitle("Choose action");
        dialogBuilder
                .setMessage("Do you want to read an article or to watch a video?")
                .setPositiveButton("Watch video", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(videoLink));
                        v.getContext().startActivity(browserIntent);
                    }
                });
        dialogBuilder.setNegativeButton("Read article", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(articleLink));
                v.getContext().startActivity(browserIntent);
            }
        });
        AlertDialog alertDialog = dialogBuilder.create();
        return alertDialog;


    }
}