package com.example.noteme;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteViewHolder> {

    private List<Note> noteList;
    private List<Note> filteredNoteList;

    public NoteAdapter(List<Note> noteList) {
        this.noteList = noteList;
        this.filteredNoteList = new ArrayList<>(noteList);
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.note_item, parent, false);
        return new NoteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        Note note = filteredNoteList.get(position);
        holder.title.setText(note.getTitle());
        holder.content.setText(note.getContent());
    }

    public void updateNotes(List<Note> newNotes) {
        this.noteList.clear();        // Clear old notes
        this.noteList.addAll(newNotes); // Add new notes
        notifyDataSetChanged();       // Notify the adapter to refresh the RecyclerView
    }

    @Override
    public int getItemCount() {
        return filteredNoteList.size();
    }

    public void filter(String text) {
        filteredNoteList.clear();
        if (text.isEmpty()) {
            filteredNoteList.addAll(noteList);
        } else {
            for (Note note : noteList) {
                if (note.getTitle().toLowerCase().contains(text.toLowerCase()) ||
                        note.getContent().toLowerCase().contains(text.toLowerCase())) {
                    filteredNoteList.add(note);
                }
            }
        }
        notifyDataSetChanged();
    }

    static class NoteViewHolder extends RecyclerView.ViewHolder {
        TextView title, content;

        NoteViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.noteTitle);
            content = itemView.findViewById(R.id.noteContent);
        }
    }
}
