package app.amrelmasry.capstone_project.features.notes;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import app.amrelmasry.capstone_project.R;
import app.amrelmasry.capstone_project.common.model.Note;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Amr on 13/05/17.
 */

class NotesViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.note_title)
    TextView mNoteTitle;

    @BindView(R.id.note_body)
    TextView mNoteBody;

    public NotesViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(Note note) {
        mNoteTitle.setText(note.getTitle());
        mNoteBody.setText(note.getBody());
    }
}
