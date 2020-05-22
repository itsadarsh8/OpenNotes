package com.example.myapplication;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Notes.class}, version =1)
public abstract class NotesDatabase extends RoomDatabase {

    private static NotesDatabase instance;

    public abstract NotesDao notesDao();

    // synchronized- Only one thread at a time can access it.
    public static synchronized NotesDatabase getInstance(Context context){
        //It can only be created once(for the first time)
        if(instance==null){
            instance= Room.databaseBuilder(context.getApplicationContext()
                    ,NotesDatabase.class
                    ,"note_database")
                    .addCallback(PopulateDb)
                    .fallbackToDestructiveMigration()  //To prevent crash while incrementing version.
                    .build();


        }

        return instance;
    }

    public static RoomDatabase.Callback PopulateDb= new RoomDatabase.Callback(){

        //Called when database is created for the first time
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            new PopulateDbAsyncTask(instance).execute();
            super.onCreate(db);
        }
    };

    public static class PopulateDbAsyncTask extends AsyncTask<Void,Void,Void>{
        private NotesDao noteDao;

        public PopulateDbAsyncTask(NotesDatabase db) {
            this.noteDao = db.notesDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            noteDao.insert(new Notes("Title 1", "Discription 1",2));
            noteDao.insert(new Notes("Title 2", "Discription 2",7));
            noteDao.insert(new Notes("Title 3", "Discription 3",1));

            return null;
        }
    }


}
