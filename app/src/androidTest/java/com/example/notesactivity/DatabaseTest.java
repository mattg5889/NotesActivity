package com.example.notesactivity;

import android.content.Context;
import android.util.Log;

import androidx.room.Room;
import androidx.test.espresso.internal.inject.InstrumentationContext;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import com.example.notesactivity.database.AppDatabase;
import com.example.notesactivity.database.NoteDAO;
import com.example.notesactivity.database.NoteEntity;
import com.example.notesactivity.utilities.SampleData;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class DatabaseTest {
    public static final String TAG = "Junit";
    private AppDatabase mDb;
    private NoteDAO mDAO;

    @Before
    public void createDB(){
        Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        //Line above pulled from https://stackoverflow.com/questions/52924431/androidx-test-instrumentationregistry-is-deprecated

        mDb = Room.inMemoryDatabaseBuilder(context,
                AppDatabase.class).build();

        mDAO = mDb.noteDAO();
        Log.i(TAG, "Create DB.");
    }

    @After
    public void closeDB(){
        mDb.close();
        Log.i(TAG, "Close DB.");
    }

    @Test
    public void createAndRetrieveNotes(){
        mDAO.insertAll(SampleData.getNotes());
        int count = mDAO.getCount();
        Log.i(TAG, "createAndRetrieveNotes: count = " + count);
        assertEquals(SampleData.getNotes().size(), count);
    }

    @Test
    public void compareStrings(){
        mDAO.insertAll(SampleData.getNotes());
        NoteEntity original = SampleData.getNotes().get(0);
        NoteEntity fromDb = mDAO.getNoteById(1);
        assertEquals(original.getText(), fromDb.getText());
        assertEquals(1, fromDb.getId());
    }
}
