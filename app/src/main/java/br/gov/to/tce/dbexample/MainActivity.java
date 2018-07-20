package br.gov.to.tce.dbexample;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recycle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recycle = findViewById(R.id.recycle);
        recycle.setLayoutManager(new LinearLayoutManager(this));
        recycle.setItemAnimator(new DefaultItemAnimator());

        updateRecycleView();
    }

    public void handleFloatButton(View button) {
        Log.i("INFO", "handleFloatButton");
        Intent intent = new Intent(this, FormActivity.class);
        startActivityForResult(intent, 1020);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        //Atualizar lista se operacao foi bem sucedida
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == 1020) {
                //setar adpter da recycleview
                updateRecycleView();
            }
        }

        super.onActivityResult(requestCode, resultCode, data);

    }

    private void updateRecycleView() {
        //Atualizar lista
        DBHandler db = new DBHandler(this, "db", 1);
        RegAdapter adapter = new RegAdapter(this, db.getRegs());

        recycle.setAdapter(adapter);
    }
}
