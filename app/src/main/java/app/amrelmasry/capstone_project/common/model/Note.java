package app.amrelmasry.capstone_project.common.model;

import android.support.annotation.NonNull;

/**
 * Created by Amr on 13/05/17.
 */

public class Note {

    @NonNull
    private String title;
    @NonNull
    private String body;

    public Note(@NonNull String title, @NonNull String body) {
        this.title = title;
        this.body = body;
    }

    @NonNull
    public String getTitle() {
        return title;
    }

    public void setTitle(@NonNull String title) {
        this.title = title;
    }

    @NonNull
    public String getBody() {
        return body;
    }

    public void setBody(@NonNull String body) {
        this.body = body;
    }
}
