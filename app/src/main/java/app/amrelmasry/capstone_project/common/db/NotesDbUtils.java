package app.amrelmasry.capstone_project.common.db;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import app.amrelmasry.capstone_project.common.model.Note;

/**
 * Created by Amr on 13/05/17.
 */

public class NotesDbUtils {

    public static void saveNote(Context context, Note note) {
        final ContentResolver contentResolver = context.getContentResolver();
        ContentValues values = new ContentValues();
        values.put(NotesContract.Notes.COLUMN_TITLE, note.getTitle());
        values.put(NotesContract.Notes.COLUMN_BODY, note.getBody());
        contentResolver.insert(NotesContract.Notes.CONTET_URI, values);
    }

    public static Note getNoteFromCursorAtPosition(Cursor cursor, int position) {
        if (!cursor.moveToPosition(position)) {
            throw new IllegalStateException("Item Can't be found in Cursor");
        }
        int titleIndex = cursor.getColumnIndex(NotesContract.Notes.COLUMN_TITLE);
        int bodyIndex = cursor.getColumnIndex(NotesContract.Notes.COLUMN_BODY);
        String title = cursor.getString(titleIndex);
        String body = cursor.getString(bodyIndex);
        return new Note(title, body);
    }
}
