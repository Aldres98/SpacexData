package com.example.aldres.spacexdata;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
    }

    @Override
    public int getItemCount() {
            return recyclerData.size();
    }

    class DataViewHolder extends RecyclerView.ViewHolder{
        TextView rocketName, launchDate;
        ImageView missionPatch;

        public DataViewHolder(View dataView) {
            super(dataView);
            rocketName = dataView.findViewById(R.id.rocket_name);
            missionPatch = dataView.findViewById(R.id.mission_patch);
            launchDate = dataView.findViewById(R.id.launch_date);

            dataView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();

                    if (pos !=RecyclerView.NO_POSITION){
                        DataModel clickedDataItem = recyclerData.get(pos);
                        Intent browserIntent = new Intent(Intent.ACTION_VIEW,  Uri.parse(clickedDataItem.getArticleLink()));
                        v.getContext().startActivity(browserIntent);

                    }
                }
            });
        }
    }

}
