package com.t.instagramstory;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<StoryModel> storyModelList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=findViewById(R.id.storyRec);
        storyModelList = new ArrayList<>();
        StoryAdapter adapter = new StoryAdapter(storyModelList);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);


        storyModelList.add(new StoryModel("aziz"));
        storyModelList.add(new StoryModel("aziz"));
        storyModelList.add(new StoryModel("aziz"));
        storyModelList.add(new StoryModel("aziz"));
        storyModelList.add(new StoryModel("aziz"));
        storyModelList.add(new StoryModel("aziz"));



    }
}