package com.example.notesactivity;

import android.os.Bundle;

import com.example.notesactivity.database.NoteEntity;
import com.example.notesactivity.viewmodel.EditorViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.notesactivity.utilities.Constants.NOTE_ID_KEY;

public class EditorActivity extends AppCompatActivity {

    @BindView(R.id.noteText)
    TextView mTextView;

    private EditorViewModel mViewModel;
    private boolean mNewNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_check_w);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ButterKnife.bind(this);

        initViewModel();
    }

    private void initViewModel(){
        mViewModel = ViewModelProviders.of(this).get(EditorViewModel.class);

        mViewModel.mLiveNote.observe(this, new Observer<NoteEntity>() {
            @Override
            public void onChanged(NoteEntity noteEntity) {
                mTextView.setText(noteEntity.getText());

            }
        });

        Bundle extras = getIntent().getExtras();
        if (extras == null){
            setTitle(R.string.newNote);
            mNewNote = true;
        } else{
            setTitle(R.string.editNote);
            int noteID = extras.getInt(NOTE_ID_KEY);
            mViewModel.loadData(noteID);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNewNote){
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.menu_editor, menu);

        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            saveAndReturn();
            return true;
        }else if (item.getItemId() == R.id.action_delete){
            mViewModel.deleteNote();
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        saveAndReturn();
    }

    private void saveAndReturn() {
        mViewModel.saveNote(mTextView.getText().toString());
        finish();
    }
}
