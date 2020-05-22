package com.example.myapplication;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;


import java.util.List;

public class NotesRepository {

    private  NotesDao mNotesDao;
    private LiveData<List<Notes>> mAllNotes;

    public NotesRepository(Application application){
        NotesDatabase notesDatabase=NotesDatabase.getInstance(application);
        mNotesDao=notesDatabase.notesDao();
        mAllNotes=mNotesDao.getAllNotes();
    }

    public  void insert(Notes notes){
        new InsertNotesAsyncTask(mNotesDao).execute(notes);
    }
    public  void update(Notes notes){
        new UpdateNotesAsyncTask(mNotesDao).execute(notes);
    }
    public  void delete(Notes notes){
        new DeleteNotesAsyncTask(mNotesDao).execute(notes);
    }
    public  void deleteAll(){
        new DeleteAllNotesAsyncTask(mNotesDao);
    }
    public  LiveData<List<Notes>> getAllNotes(){

        return mAllNotes;

    }

    private static class InsertNotesAsyncTask extends AsyncTask<Notes,Void,Void>{
        private NotesDao noteDao;

        public InsertNotesAsyncTask(NotesDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Notes... notes) {
            noteDao.insert(notes[0]);

            return null;

        }
    }

    private static class UpdateNotesAsyncTask extends AsyncTask<Notes,Void,Void>{
        private NotesDao noteDao;

        public UpdateNotesAsyncTask(NotesDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Notes... notes) {
            noteDao.update(notes[0]);

            return null;
        }
    }

    private static class DeleteNotesAsyncTask extends AsyncTask<Notes,Void,Void>{
        private NotesDao noteDao;

        public DeleteNotesAsyncTask(NotesDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Notes... notes) {
            noteDao.delete(notes[0]);

            return null;
        }
    }

    private static class DeleteAllNotesAsyncTask extends AsyncTask<Void,Void,Void>{
        private NotesDao noteDao;

        public DeleteAllNotesAsyncTask(NotesDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {

            noteDao.deleteAllNotes();
            return null;
        }
    }


}
