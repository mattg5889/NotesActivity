package com.example.notesactivity.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.notesactivity.database.AppRepository;
import com.example.notesactivity.database.NoteEntity;
import com.example.notesactivity.utilities.SampleData;

import java.util.List;

public class MainViewModel extends AndroidViewModel {

    public List<NoteEntity> mNotes;
    private AppRepository mRepository;

    public MainViewModel(@NonNull Application application) {
        super(application);

        mRepository = AppRepository.getInstance(application.getApplicationContext());
        mNotes = mRepository.mNotes;
    }


    public void addSampleData() {
        Log.i("onOptionsItemsSelected", "onOptionsItemSelected: MainViewModel - add sample data");
        mRepository.addSampleData();
    }
}
