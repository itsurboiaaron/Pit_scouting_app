package com.example.pitscoutingapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;

import static java.sql.DriverManager.println;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    int time = 1;
    Button save;
    Button delete;

    int TAKE_PHOTO_CODE = -1;
    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final String dir = MainActivity.this.getFilesDir() + "/Pit App Files/" + count + ".jpg";
        File newdir = new File(dir);
        newdir.mkdirs();

        final EditText enterText = findViewById(R.id.editText);
        final EditText enterText2 = findViewById(R.id.editText1);
        final EditText enterText3 = findViewById(R.id.editText2);
        save = findViewById(R.id.button3);
        delete = findViewById(R.id.delete);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!enterText.getText().toString().isEmpty()) {
                    File file = new File(MainActivity.this.getFilesDir(), "Pit App Files");
                    if (!file.exists()) {
                        file.mkdir();
                    }
                    try {
                        File gpxfile = new File(file, "FRC Pit Scouting App" + enterText.getText().toString());
                        FileWriter writer = new FileWriter(gpxfile);
                        writer.append("Team number: " + enterText.getText().toString() + "    " );
                        writer.append("Team name: " + enterText2.getText().toString() + "    " );
                        writer.append("drive base: " + enterText3.getText().toString() + "    " );
                        writer.flush();
                        writer.close();
                        Toast.makeText(MainActivity.this, "Saved your text", Toast.LENGTH_LONG).show();
                    } catch (Exception e) {
                    }
                }
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                File dir = new File(MainActivity.this.getFilesDir() + "/Pit App Files");
                if (dir.isDirectory()) {
                    String[] children = dir.list();
                    for (int i = 0; i < children.length; i++) {
                        new File(dir, children[i]).delete();
                    }
                }
                Log.d("delete update", "deleted " + dir);
                Toast.makeText(MainActivity.this, "deleted the files", Toast.LENGTH_LONG).show();
            }
        });

        Button capture = (Button) findViewById(R.id.btnCapture);
        capture.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                // Here, the counter will be incremented each time, and the
                // picture taken by camera will be stored as 1.jpg,2.jpg
                // and likewise.
                count++;
                String file = dir + count + ".jpg";
                File newfile = new File(file);
                try {
                    newfile.createNewFile();
                } catch (IOException e) {
                }

                Uri outputFileUri = Uri.fromFile(newfile);

                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);

                startActivityForResult(cameraIntent, TAKE_PHOTO_CODE);
                Log.d("Camera", "saving file in " + file);
            }
        });
    }
        @Override
        protected void onActivityResult(int requestCode, int resultCode, Intent data) {
            super.onActivityResult(requestCode, resultCode, data);

            if (requestCode == TAKE_PHOTO_CODE && resultCode == RESULT_OK) {
                Log.d("CameraDemo", "Pic saved");
            }
        }

    public void night_mode(View view) throws InterruptedException {

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
        finish();
        Log.d("Night mode update", "Night Mode is currently off");
        println("Night Mode is currently off");
        Thread.sleep(time);
    }

    public void Light_Mode(View view) throws InterruptedException {

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
        finish();
        Log.d("Night mode update", "Night Mode is currently on");
        println("Night Mode is currently on");
        Thread.sleep(time);
    }



}
