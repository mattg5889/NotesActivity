 package com.example.notesactivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.notesactivity.UI.NotesAdapter;
import com.example.notesactivity.database.NoteEntity;
import com.example.notesactivity.viewmodel.MainViewModel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

 public class MainActivity extends AppCompatActivity {

    @BindView(R.id.recycler_view)
     RecyclerView mRecyclerView;

    @OnClick(R.id.fab)
    void fabClickHandler(){
        Intent intent = new Intent( this,EditorActivity.class);
        startActivity(intent);
    }

    private List<NoteEntity> notesData = new ArrayList<>();
    private NotesAdapter mAdapter;
    private MainViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ButterKnife.bind(this);
        initViewModel();
        initRecyclerView();

//        FloatingActionButton fab = findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        notesData.addAll(mViewModel.mNotes);
        for(NoteEntity note :
                notesData){
            Log.i("PlainNotes",note.toString());
        }
    }

     private void initViewModel() {
         mViewModel = ViewModelProviders.of(this)
                 .get(MainViewModel.class);
     }

     private void initRecyclerView() {
        mRecyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);

        mAdapter = new NotesAdapter(notesData, this);
        mRecyclerView.setAdapter(mAdapter);
     }

     @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_addSampleData) {
            Log.i("onOptionsItemsSelected", "onOptionsItemSelected - add sample data");
            addSampleData();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

     private void addSampleData() {
         Log.i("onOptionsItemsSelected", "onOptionsItemSelected: MainActivity - add sample data");
        mViewModel.addSampleData();
     }
 }
