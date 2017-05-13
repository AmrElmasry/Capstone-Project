package app.amrelmasry.capstone_project.common.db;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by Amr on 13/05/17.
 */

@SuppressWarnings("WeakerAccess")
public final class NotesContract {


    public static String AUTHORITY = "com.amrelmasry.notes";
    public static Uri BASE_CONTENT_URI = Uri.parse("content://" + AUTHORITY);

    public static String PATH_NOTES = "notes";

    private NotesContract() {
        throw new RuntimeException("No Intances.");
    }

    public static class Notes implements BaseColumns {

        public static final Uri CONTET_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_NOTES).build();

        public static final String TABLE_NAME = "notes";
        public static final String COLUMN_TITLE = "title";
        public static final String COLUMN_BODY = "body";
        public static final String COLUMN_TIMESTAMP = "timestamp";
    }
}
