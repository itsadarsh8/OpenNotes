package com.example.myapplication;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class NotesViewModel extends AndroidViewModel {
    private NotesRepository mNotesRepository;
    private LiveData<List<Notes>> mAllNotes;

    public NotesViewModel(Application application){
        super(application);
        mNotesRepository=new NotesRepository(application);
        mAllNotes=mNotesRepository.getAllNotes();
    }

    public void insert(Notes note){
        mNotesRepository.insert(note);
    }

    public void update(Notes note){
        mNotesRepository.update(note);
    }

    public void delete(Notes note){
        mNotesRepository.delete(note);
    }

    public void deleteAll(){
        mNotesRepository.deleteAll();
    }

    public LiveData<List<Notes>> getAllNotes(){
        return mAllNotes;
    }


}
