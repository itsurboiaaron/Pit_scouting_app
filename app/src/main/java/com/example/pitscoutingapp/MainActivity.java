package com.example.pitscoutingapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import static java.sql.DriverManager.println;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.io.File;
import java.io.FileWriter;

public class MainActivity extends AppCompatActivity {

    int time = 1;
    Button save;
    Button delete;
    Button cameraBeta;
    public String constants;

    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final String dir2 = MainActivity.this.getFilesDir() + "/Pit App Files/" + count + ".jpg";
        File newdir = new File(dir2);
        newdir.mkdirs();

        final EditText enterText = findViewById(R.id.editText);
        final EditText enterText2 = findViewById(R.id.editText1);
        final EditText enterText3 = findViewById(R.id.editText2);
        final EditText enterText4 = findViewById(R.id.editText3);
        final EditText enterText5 = findViewById(R.id.editText4);
        final EditText enterText6 = findViewById(R.id.editText5);
        final EditText enterText7 = findViewById(R.id.editText6);
        final EditText enterText8 = findViewById(R.id.editText7);
        final EditText enterText9 = findViewById(R.id.editText8);
        final EditText enterText10 = findViewById(R.id.editText9);
        final EditText enterText11 = findViewById(R.id.editText10);
        final EditText enterText12 = findViewById(R.id.editText11);
        final EditText enterText13 = findViewById(R.id.editText12);
        final EditText enterText14 = findViewById(R.id.editText13);
        final EditText enterText15 = findViewById(R.id.editText14);
        final EditText enterText16 = findViewById(R.id.editText15);
        save = findViewById(R.id.button3);
        delete = findViewById(R.id.delete);
        cameraBeta = findViewById(R.id.camera1);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saving(enterText, enterText2, enterText3, enterText4, enterText5, enterText6, enterText7, enterText8, enterText9, enterText10, enterText11, enterText12, enterText13, enterText14, enterText15, enterText16);
            }
        });
        cameraBeta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saving(enterText, enterText2, enterText3, enterText4, enterText5, enterText6, enterText7, enterText8, enterText9, enterText10, enterText11, enterText12, enterText13, enterText14, enterText15, enterText16);
                openNewActivity();
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

    public void openNewActivity(){
        Intent intent = new Intent(this, camera.class);
        startActivity(intent);

    }

    private void saving(EditText enterText, EditText enterText2, EditText enterText3, EditText enterText4, EditText enterText5, EditText enterText6, EditText enterText7, EditText enterText8, EditText enterText9, EditText enterText10, EditText enterText11, EditText enterText12, EditText enterText13, EditText enterText14, EditText enterText15, EditText enterText16){
        if (!enterText.getText().toString().isEmpty()) {
            File file = new File(MainActivity.this.getFilesDir(), "Pit App Files");
            if (!file.exists()) {
                file.mkdir();
            }
            try {
                File gpxfile = new File(file, "FRC Pit Scouting App" + enterText.getText().toString());
                FileWriter writer = new FileWriter(gpxfile);
                writer.append("Team number: " + enterText.getText().toString() + "    ");
                constants = enterText.getText().toString();
                writer.append("Team name: " + enterText2.getText().toString() + "    ");
                writer.append("drive base: " + enterText3.getText().toString() + "    ");
                writer.append("type of wheels: " + enterText4.getText().toString() + "    ");
                writer.append("size of wheels: " + enterText5.getText().toString() + "    ");
                writer.append("places on the field that they can access: " + enterText6.getText().toString() + "    ");
                writer.append("can climb: " + enterText7.getText().toString() + "    ");
                writer.append("can they climb reliably: " + enterText8.getText().toString() + "    ");
                writer.append("can buddy climb: " + enterText9.getText().toString() + "    ");
                writer.append("where they can climb: " + enterText10.getText().toString() + "    ");
                writer.append("reliably of shooter: " + enterText11.getText().toString() + "    ");
                writer.append("where they can shot from: " + enterText12.getText().toString() + "    ");
                writer.append("where they can climb: " + enterText13.getText().toString() + "    ");
                writer.append("how many balls they can fit: " + enterText14.getText().toString() + "    ");
                writer.append("cycles they can go through in a match: " + enterText15.getText().toString() + "    ");
                writer.append("motors: " + enterText16.getText().toString() + "    ");
                writer.flush();
                writer.close();
                Toast.makeText(MainActivity.this, "Saved your text", Toast.LENGTH_LONG).show();
            } catch (Exception e) {
            }
        }
    }

    }
/*
    <string name="plainView_sisxteen">motors</string>
 */

