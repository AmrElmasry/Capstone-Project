package app.amrelmasry.capstone_project.common;

import android.content.Context;
import android.support.annotation.NonNull;

import com.google.firebase.analytics.FirebaseAnalytics;

/**
 * Created by Amr on 14/05/17.
 */

public class FirbaseAnalyticsProvider {
    private static FirebaseAnalytics mFirebaseAnalytics;


    public static FirebaseAnalytics getInstance(@NonNull Context context) {
        if (mFirebaseAnalytics == null) {
            mFirebaseAnalytics = FirebaseAnalytics.getInstance(context);
        }
        return mFirebaseAnalytics;
    }
}
