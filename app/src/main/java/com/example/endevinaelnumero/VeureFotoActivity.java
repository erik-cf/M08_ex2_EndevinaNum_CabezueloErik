package com.example.endevinaelnumero;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;

public class VeureFotoActivity extends AppCompatActivity {

    ImageView image;
    Button tornarRanking;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_veure_foto);
        this.getSupportActionBar().hide();

        image = findViewById(R.id.bigfoto);
        tornarRanking = findViewById(R.id.backToRanking);

        tornarRanking.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                VeureFotoActivity.this.finish();
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        Uri photoUri = Uri.parse(getIntent().getStringExtra("photoUri"));
        image.setImageURI(photoUri);
    }
}
