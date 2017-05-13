package app.amrelmasry.capstone_project.common;

import android.app.Activity;
import android.content.Intent;

import app.amrelmasry.capstone_project.features.create.CreateNoteActivity;

/**
 * Created by Amr on 12/05/17.
 */

public final class Navigator {

    private Navigator() {
        throw new IllegalStateException("No Instances.");
    }

    public static void openCreateNoteForResult(Activity activity, int requestCode) {
        Intent intent = new Intent(activity, CreateNoteActivity.class);
        activity.startActivityForResult(intent, requestCode);

    }
}
