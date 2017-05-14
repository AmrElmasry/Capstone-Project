package app.amrelmasry.capstone_project.common;

import app.amrelmasry.capstone_project.BuildConfig;
import app.amrelmasry.capstone_project.common.model.PhotosResponse;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Amr on 14/05/17.
 */

public interface RestApi {

    String CONSUMER_KEY = BuildConfig.API_CONSUMER_KEY;

    @GET("photos?feature=popular&consumer_key=" + CONSUMER_KEY)
    Call<PhotosResponse> getInspirationalPhotos();
}
