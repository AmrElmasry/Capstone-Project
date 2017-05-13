package app.amrelmasry.capstone_project.features.create;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.Toast;

import app.amrelmasry.capstone_project.R;
import app.amrelmasry.capstone_project.common.db.NotesDbUtils;
import app.amrelmasry.capstone_project.common.model.Note;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CreateNoteActivity extends AppCompatActivity {


    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.note_title)
    EditText mNoteTitle;

    @BindView(R.id.note_body)
    EditText mNoteBody;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_note);
        ButterKnife.bind(this);
        initToolbar();
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
        NotesDbUtils.saveNote(this, new Note(title, body));
        setResult(RESULT_OK);
        finish();
    }

    private boolean requireFieldAreEmpty() {
        return isEmpty(mNoteBody) || isEmpty(mNoteTitle);
    }

    private boolean isEmpty(EditText editText) {
        return TextUtils.isEmpty(editText.getText());
    }
}
