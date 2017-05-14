package app.amrelmasry.capstone_project.features.create;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import app.amrelmasry.capstone_project.R;
import app.amrelmasry.capstone_project.common.db.NotesDbUtils;
import app.amrelmasry.capstone_project.common.model.Note;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CreateNoteActivity extends AppCompatActivity {


    public static final String EXTRA_NOTE = "app.amrelmasry.capstone_project.extra_note";

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.note_title)
    EditText mNoteTitle;

    @BindView(R.id.delete_note)
    ImageView mDeleteNote;

    @BindView(R.id.note_body)
    EditText mNoteBody;

    @BindView(R.id.adview)
    AdView mAdView;

    private boolean mEditNoteMode = false;

    private Note mNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_note);
        ButterKnife.bind(this);
        initToolbar();
        if (getIntent().hasExtra(EXTRA_NOTE)) {
            mEditNoteMode = true;
            mNote = getIntent().getParcelableExtra(EXTRA_NOTE);
            populateNoteData();
        }
        initAdMob();
    }

    private void initAdMob() {
        MobileAds.initialize(this, getString(R.string.admob_test_unit_id));
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
    }

    private void populateNoteData() {
        mNoteTitle.setText(mNote.getTitle());
        mNoteBody.setText(mNote.getBody());
        mDeleteNote.setVisibility(View.VISIBLE);
    }

    private void initToolbar() {
        setSupportActionBar(mToolbar);
    }

    @OnClick(R.id.save_note)
    void onSaveNoteClicked() {
        if (requireFieldAreEmpty()) {
            Toast.makeText(this, "You have to enter both the note title and body first", Toast.LENGTH_SHORT).show();
            return;
        }
        String title = mNoteTitle.getText().toString().trim();
        String body = mNoteBody.getText().toString().trim();
        if (mEditNoteMode) {
            if (noteHasBeenUpdated()) {
                // update note
                NotesDbUtils.updateNote(this, mNote.getId(), title, body);
                setResult(RESULT_OK);
            }
        } else {
            // create new note
            NotesDbUtils.saveNote(this, title, body);
            setResult(RESULT_OK);
        }
        finish();
    }

    private boolean noteHasBeenUpdated() {
        return !mNote.getBody().equals(mNoteBody.getText().toString().trim()) ||
                !mNote.getTitle().equals(mNoteTitle.getText().toString().trim());
    }

    private boolean requireFieldAreEmpty() {
        return isEmpty(mNoteBody) || isEmpty(mNoteTitle);
    }

    private boolean isEmpty(EditText editText) {
        return TextUtils.isEmpty(editText.getText().toString().trim());
    }

    @OnClick(R.id.delete_note)
    void onDeleteClicked() {
        NotesDbUtils.deleteNote(this, mNote.getId());
        setResult(RESULT_OK);
        finish();
    }

    @Override
    public void onPause() {
        if (mAdView != null) {
            mAdView.pause();
        }
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mAdView != null) {
            mAdView.resume();
        }
    }

    @Override
    public void onDestroy() {
        if (mAdView != null) {
            mAdView.destroy();
        }
        super.onDestroy();
    }
}
