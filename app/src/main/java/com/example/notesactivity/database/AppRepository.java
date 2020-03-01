package com.example.notesactivity.database;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.example.notesactivity.utilities.SampleData;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class AppRepository {
    private static AppRepository ourInstance;

    public LiveData<List<NoteEntity>> mNotes;
    private AppDatabase mDB;
    private Executor executor = Executors.newSingleThreadExecutor();

    public static AppRepository getInstance(Context context) {
        if(ourInstance == null){
            ourInstance = new AppRepository(context);
        }
        return ourInstance;
    }

    private AppRepository(Context context) {
        mDB = AppDatabase.getInstance(context);
        mNotes = getAllNotes();
    }


    public void addSampleData() {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                mDB.noteDAO().insertAll(SampleData.getNotes());
                Log.i("onOptionsItemsSelected", "onOptionsItemSelected: AppRepository - mDB_noteDAO_insertAll(mNotes)");
            }
        });
    }

    private LiveData<List<NoteEntity>> getAllNotes(){
        return mDB.noteDAO().getAll();
    }

    public void deleteAllNotes() {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                mDB.noteDAO().deleteAll();
            }
        });
    }

    public NoteEntity getNoteByID(int noteID) {
        return mDB.noteDAO().getNoteById(noteID);
    }

    public void insertNote(NoteEntity note) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                mDB.noteDAO().insertNote(note);
            }
        });
    }

    public void deleteNote(NoteEntity note) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                mDB.noteDAO().deleteNote(note);
            }
        });
    }
}
