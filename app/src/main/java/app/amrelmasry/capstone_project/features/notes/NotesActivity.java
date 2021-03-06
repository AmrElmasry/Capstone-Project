package app.amrelmasry.capstone_project.features.notes;

import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import app.amrelmasry.capstone_project.R;
import app.amrelmasry.capstone_project.common.FirbaseAnalyticsProvider;
import app.amrelmasry.capstone_project.common.Navigator;
import app.amrelmasry.capstone_project.common.UserEvents;
import app.amrelmasry.capstone_project.common.db.NotesContract;
import app.amrelmasry.capstone_project.common.model.Note;
import butterknife.BindInt;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NotesActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor>, NotesAdapter.OnNoteClickListener {

    private static final int CREATE_NEW_NOTE_REQUEST_CODE = 1;
    private static final int SHOW_NOTE_DETAILS_REQUEST_CODE = 2;
    private static final int NOTES_LOADER_ID = 100;
    @BindView(R.id.note_recycler_view)
    RecyclerView mNotesRecyclerView;
    @BindView(R.id.toolbar_image)
    ImageView mToolbarImageView;
    @BindView(R.id.empty_view)
    View mEmptyView;
    @BindInt(R.integer.span_count)
    int mSpanCount;
    private NotesAdapter mNotesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);
        ButterKnife.bind(this);
        mNotesAdapter = new NotesAdapter(null, this);
        mNotesRecyclerView.setAdapter(mNotesAdapter);
        mNotesRecyclerView.setLayoutManager(new GridLayoutManager(this, mSpanCount));
        getLoaderManager().initLoader(NOTES_LOADER_ID, null, this);
        initToolbar();
    }

    private void initToolbar() {
        ToolbarPhotoAsyncTask asyncTask = new ToolbarPhotoAsyncTask(mToolbarImageView);
        asyncTask.execute();
    }

    @OnClick(R.id.add_note_fab)
    void onAddNoteClicked() {
        FirbaseAnalyticsProvider.getInstance(this).logEvent(UserEvents.CREATE_NOTE, null);
        Navigator.openCreateNoteForResult(this, CREATE_NEW_NOTE_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CREATE_NEW_NOTE_REQUEST_CODE || requestCode == SHOW_NOTE_DETAILS_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                // reload data
                getLoaderManager().restartLoader(NOTES_LOADER_ID, null, this);
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(this, NotesContract.Notes.CONTET_URI, null,
                null, null, null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        if (cursor.getCount() == 0) {
            showEmptyView(true);
        } else {
            showEmptyView(false);
        }
        mNotesAdapter.swapCursor(cursor);
    }

    private void showEmptyView(boolean show) {
        mEmptyView.setVisibility(show ? View.VISIBLE : View.GONE);
        mNotesRecyclerView.setVisibility(show ? View.INVISIBLE : View.VISIBLE);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mNotesAdapter.swapCursor(null);
    }

    @Override
    public void onNoteClick(Note note) {
        Navigator.openNoteDetailsForResult(this, note, SHOW_NOTE_DETAILS_REQUEST_CODE);
    }
}
