package app.amrelmasry.capstone_project.features.notes;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import app.amrelmasry.capstone_project.R;
import app.amrelmasry.capstone_project.common.model.Note;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Amr on 13/05/17.
 */

class NotesViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.note_title)
    TextView mNoteTitle;

    @BindView(R.id.note_body)
    TextView mNoteBody;

    private NotesAdapter.OnNoteClickListener listener;
    private Note mNote;


    public NotesViewHolder(View itemView, NotesAdapter.OnNoteClickListener listener) {
        super(itemView);
        this.listener = listener;
        ButterKnife.bind(this, itemView);
    }

    public void bind(Note note) {
        this.mNote = note;
        mNoteTitle.setText(note.getTitle());
        mNoteBody.setText(note.getBody());
    }

    @OnClick(R.id.note_container_view)
    void onNoteClicked() {
        listener.onNoteClick(mNote);
    }
}
