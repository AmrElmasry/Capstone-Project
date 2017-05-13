package app.amrelmasry.capstone_project.features.notes;

import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import app.amrelmasry.capstone_project.R;
import app.amrelmasry.capstone_project.common.db.NotesDbUtils;
import app.amrelmasry.capstone_project.common.model.Note;

/**
 * Created by Amr on 13/05/17.
 */

class NotesAdapter extends RecyclerView.Adapter<NotesViewHolder> {


    private Cursor mCursor;

    NotesAdapter(Cursor mCursor) {
        this.mCursor = mCursor;
    }

    @Override
    public NotesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_note, parent, false);
        return new NotesViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(NotesViewHolder holder, int position) {
        Note note = NotesDbUtils.getNoteFromCursorAtPosition(mCursor, position);
        holder.bind(note);
    }

    public void swapCursor(Cursor newCursor) {
        if (mCursor == newCursor)
            return;
        mCursor = newCursor;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mCursor != null ? mCursor.getCount() : 0;
    }
}
