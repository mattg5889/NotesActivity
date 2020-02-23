package com.example.notesactivity.database;

import android.content.Context;
import android.util.Log;

import com.example.notesactivity.utilities.SampleData;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class AppRepository {
    private static AppRepository ourInstance;

    public List<NoteEntity> mNotes;
    private AppDatabase mDB;
    private Executor executor = Executors.newSingleThreadExecutor();

    public static AppRepository getInstance(Context context) {
        if(ourInstance == null){
            ourInstance = new AppRepository(context);
        }
        return ourInstance;
    }

    private AppRepository(Context context) {
        mNotes = SampleData.getNotes();
        mDB = AppDatabase.getInstance(context);
    }


    public void addSampleData() {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                mDB.noteDAO().insertAll(mNotes);
                Log.i("onOptionsItemsSelected", "onOptionsItemSelected: AppRepository - mDB_noteDAO_insertAll(mNotes)");
            }
        });
    }
}
