package app.amrelmasry.capstone_project.features.notes;

import android.os.AsyncTask;
import android.text.TextUtils;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.lang.ref.WeakReference;

import app.amrelmasry.capstone_project.common.DataManager;
import app.amrelmasry.capstone_project.common.model.PhotosResponse;
import retrofit2.Call;

/**
 * Created by Amr on 14/05/17.
 */

public class ToolbarPhotoAsyncTask extends AsyncTask<Void, Void, String> {

    private WeakReference<ImageView> imageView;

    public ToolbarPhotoAsyncTask(ImageView imageView) {
        this.imageView = new WeakReference<>(imageView);
    }

    @Override
    protected String doInBackground(Void... params) {
        Call<PhotosResponse> call = DataManager.getRestApi().getInspirationalPhotos();
        PhotosResponse photosResponse;
        try {
            photosResponse = call.execute().body();
            String image_url = photosResponse.getPhotos().get(0).getImage_url();
            if (TextUtils.isEmpty(image_url)) {
                return null;
            }
            return image_url;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(String url) {
        ImageView imageView = this.imageView.get();
        if (imageView != null) {
            Picasso.with(imageView.getContext()).load(url).into(imageView);
        }
    }
}
