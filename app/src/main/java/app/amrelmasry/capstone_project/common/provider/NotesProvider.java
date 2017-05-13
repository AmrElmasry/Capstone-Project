package app.amrelmasry.capstone_project.common.provider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import app.amrelmasry.capstone_project.common.db.NotesContract;
import app.amrelmasry.capstone_project.common.db.NotesSqlOpenHelper;

/**
 * Created by Amr on 13/05/17.
 */

public class NotesProvider extends ContentProvider {
    private static final int MULTI_NOTES_MODE = 100;
    private static final int SINGLE_NOTE_WITH_ID = 101;
    private NotesSqlOpenHelper mDpOpenHelper;

    private UriMatcher mUriMatcher = createUriMatcher();

    private UriMatcher createUriMatcher() {
        UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

        uriMatcher.addURI(NotesContract.AUTHORITY, NotesContract.PATH_NOTES, MULTI_NOTES_MODE);
        uriMatcher.addURI(NotesContract.AUTHORITY, NotesContract.PATH_NOTES + "/#", SINGLE_NOTE_WITH_ID);

        return uriMatcher;
    }

    @Override
    public boolean onCreate() {
        mDpOpenHelper = new NotesSqlOpenHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection,
                        @Nullable String selection,
                        @Nullable String[] selectionArgs,
                        @Nullable String sortOrder) {

        int match = mUriMatcher.match(uri);

        Cursor cursor;
        switch (match) {
            case MULTI_NOTES_MODE:
                cursor = mDpOpenHelper.getReadableDatabase().query(NotesContract.Notes.TABLE_NAME,
                        projection, selection, selectionArgs, null, null, sortOrder);
                break;
            case SINGLE_NOTE_WITH_ID:
                String id = uri.getPathSegments().get(1);
                cursor = mDpOpenHelper.getReadableDatabase().query(NotesContract.Notes.TABLE_NAME,
                        projection, NotesContract.Notes._ID + " = " + id, null, null, null, sortOrder);
                break;
            default:
                throw new UnsupportedOperationException();
        }
        cursor.setNotificationUri(getContext().getContentResolver(), uri);
        return cursor;
    }


    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {

        int match = mUriMatcher.match(uri);

        Uri returnUri;

        switch (match) {
            case MULTI_NOTES_MODE:
                long id = mDpOpenHelper.getWritableDatabase()
                        .insert(NotesContract.Notes.TABLE_NAME, null, values);
                if (id > 0) {
                    returnUri = ContentUris.withAppendedId(NotesContract.Notes.CONTET_URI, id);
                } else {
                    throw new SQLException("Failed to insert row into " + uri);
                }
                break;
            default:
                throw new UnsupportedOperationException();
        }
        getContext().getContentResolver().notifyChange(uri, null);

        return returnUri;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        int match = mUriMatcher.match(uri);
        int countUpdated;
        switch (match) {
            case SINGLE_NOTE_WITH_ID:
                String id = uri.getPathSegments().get(1);
                countUpdated = mDpOpenHelper.getWritableDatabase().update(NotesContract.Notes.TABLE_NAME,
                        values,
                        NotesContract.Notes._ID + "=" + id, null);
                break;
            default:
                throw new UnsupportedOperationException();
        }

        if (countUpdated > 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return countUpdated;

    }


}
