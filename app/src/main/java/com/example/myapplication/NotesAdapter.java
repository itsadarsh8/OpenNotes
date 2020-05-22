package com.example.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NotesViewHolder> {

    private List<Notes> notes=new ArrayList<>();


    @NonNull
    @Override
    public NotesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.note_item,parent,false);
        return new NotesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotesViewHolder holder, int position) {
    Notes currentNote=notes.get(position);
    holder.mtitle.setText(currentNote.getTitle());
    holder.mdescription.setText(currentNote.getDescription());
    holder.mpriority.setText(String.valueOf(currentNote.getPriority()));
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public void setNotes(List<Notes> notes){
        this.notes=notes;
        notifyDataSetChanged();
    }

    public class NotesViewHolder extends RecyclerView.ViewHolder{
        private TextView mtitle;
        private TextView mdescription;
        private TextView mpriority;


        public NotesViewHolder(@NonNull View itemView) {
            super(itemView);

            mtitle=itemView.findViewById(R.id.text_view_title);
            mdescription=itemView.findViewById(R.id.text_view_description);
            mpriority=itemView.findViewById(R.id.text_view_priority);
        }
    }
}
