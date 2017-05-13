package app.amrelmasry.capstone_project.common.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Amr on 13/05/17.
 */

public class NotesSqlOpenHelper extends SQLiteOpenHelper {


    private static final String DB_NAME = "notes.db";
    private static final int DB_VERSION = 1;


    public NotesSqlOpenHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String createTable = "CREATE TABLE " +
                NotesContract.Notes.TABLE_NAME +
                " (" +
                NotesContract.Notes._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                NotesContract.Notes.COLUMN_TITLE + " TEXT NOT NULL, " +
                NotesContract.Notes.COLUMN_BODY + " TEXT NOT NULL, " +
                NotesContract.Notes.COLUMN_TIMESTAMP + " TIMESTAMP DEFAULT CURRENT_TIMESTAMP " +
                ");";

        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + NotesContract.Notes.TABLE_NAME);
        onCreate(db);
    }
}
