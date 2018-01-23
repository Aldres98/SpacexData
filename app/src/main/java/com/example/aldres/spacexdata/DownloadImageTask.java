package com.example.aldres.spacexdata;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.URL;

/**
 * Created by Aldres on 20.01.2018.
 */

public class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {

    private ImageView missionPatch;
    private Bitmap mPatch = null;
    private final int REQUIRED_THUMB_WIDTH = 128;
    private final int REQUIRED_THUMB_HEIGHT = 128;


    public DownloadImageTask(ImageView missionPatch) {
        this.missionPatch = missionPatch;
    }



    @Override
    protected Bitmap doInBackground(String... url) {
        String urlDisplay = url[0];
        try {
            // On this stream we're going to receive image params, to calculate, how much should we scale it.
            // We have to scale bitmap, because otherwise it'll consume lots of memory, so it'll possibly lead to app crash.
            InputStream in = new URL(urlDisplay).openStream();
            BitmapFactory.Options downloadOptions = new BitmapFactory.Options();
            downloadOptions.inJustDecodeBounds = true;
            mPatch = BitmapFactory.decodeStream(in, null, downloadOptions);
            downloadOptions.inJustDecodeBounds = false;
            downloadOptions.inSampleSize = calculateInSampleSize(downloadOptions, REQUIRED_THUMB_WIDTH, REQUIRED_THUMB_HEIGHT );
            in.close();

            // On this stream we're downloading image, matching our options
            in = new URL(urlDisplay).openStream();
            mPatch = BitmapFactory.decodeStream(in, null, downloadOptions);

        } catch (Exception e) {
            Log.e("Icon downaload error: ", e.getMessage());
        }
        return mPatch;
    }

    @Override
    protected void onPostExecute(Bitmap result) {
        missionPatch.setImageBitmap(result);
    }

    private static int calculateInSampleSize(
            BitmapFactory.Options options, int reqWidth, int reqHeight) {

        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            while ((halfHeight / inSampleSize) >= reqHeight
                    && (halfWidth / inSampleSize) >= reqWidth) {
                inSampleSize *= 2;
            }
        }
        return inSampleSize;
    }

}
