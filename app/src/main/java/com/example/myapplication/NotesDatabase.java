package com.example.myapplication;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

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
                    .fallbackToDestructiveMigration()  //To prevent crash while incrementing version.
                    .build();

        }

        return instance;
    }


}
