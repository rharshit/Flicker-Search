package com.rharshit.flickrsearch;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;

public class MainActivity extends AppCompatActivity {

    EditText etSeatch;
    Button bSearch;
    GridView gvImages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etSeatch = findViewById(R.id.et_search);
        bSearch = findViewById(R.id.b_search);
        gvImages = findViewById(R.id.gv_images);
    }
}
