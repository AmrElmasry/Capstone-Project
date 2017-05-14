package app.amrelmasry.capstone_project.common;

import com.github.aurae.retrofit2.LoganSquareConverterFactory;

import retrofit2.Retrofit;

/**
 * Created by Amr on 14/05/17.
 */

public class DataManager {

    private static RestApi mRestApi;

    public static RestApi getRestApi() {
        if (mRestApi == null) {
            Retrofit.Builder builder = new Retrofit.Builder();
            builder.baseUrl("https://api.500px.com/v1/");
            builder.addConverterFactory(LoganSquareConverterFactory.create());
            Retrofit retrofit = builder.build();
            mRestApi = retrofit.create(RestApi.class);
        }
        return mRestApi;
    }
}
