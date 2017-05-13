package app.amrelmasry.capstone_project.common.db;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import app.amrelmasry.capstone_project.common.model.Note;

/**
 * Created by Amr on 13/05/17.
 */

public class NotesDbUtils {

    public static void saveNote(Context context, String title, String body) {
        final ContentResolver contentResolver = context.getContentResolver();
        ContentValues values = new ContentValues();
        values.put(NotesContract.Notes.COLUMN_TITLE, title);
        values.put(NotesContract.Notes.COLUMN_BODY, body);
        contentResolver.insert(NotesContract.Notes.CONTET_URI, values);
    }

    public static Note getNoteFromCursorAtPosition(Cursor cursor, int position) {
        if (!cursor.moveToPosition(position)) {
            throw new IllegalStateException("Item Can't be found in Cursor");
        }
        int idIndex = cursor.getColumnIndex(NotesContract.Notes._ID);
        int titleIndex = cursor.getColumnIndex(NotesContract.Notes.COLUMN_TITLE);
        int bodyIndex = cursor.getColumnIndex(NotesContract.Notes.COLUMN_BODY);
        String title = cursor.getString(titleIndex);
        String body = cursor.getString(bodyIndex);
        long id = cursor.getLong(idIndex);
        return new Note(id, title, body);
    }

    public static void updateNote(Context context, long updatedNoteId, String newTitle, String newBody) {
        final ContentResolver contentResolver = context.getContentResolver();
        ContentValues values = new ContentValues();
        values.put(NotesContract.Notes.COLUMN_TITLE, newTitle);
        values.put(NotesContract.Notes.COLUMN_BODY, newBody);
        Uri uri = NotesContract.Notes.CONTET_URI.buildUpon().appendPath(String.valueOf(updatedNoteId)).build();
        contentResolver.update(uri, values, null, null);
    }
}
