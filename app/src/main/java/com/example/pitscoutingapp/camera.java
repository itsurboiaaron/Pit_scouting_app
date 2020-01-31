package com.example.pitscoutingapp;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class camera extends Activity {
    private static final int CAMERA_REQUEST = 1888;
    private ImageView imageView;
    private static final int MY_CAMERA_PERMISSION_CODE = 100;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.camera);

        final OutputStream[] outputStream = new OutputStream[1];

        this.imageView = (ImageView) this.findViewById(R.id.imageView);
        final EditText enterText = findViewById(R.id.editText);
        Button photoButton = (Button) this.findViewById(R.id.button);
        Button save_photo = (Button) this.findViewById(R.id.button5);

        //ImagePath = enterText.getText().toString();

        save_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                BitmapDrawable bitmapDrawable = (BitmapDrawable) imageView.getDrawable();
                Bitmap bitmap = bitmapDrawable.getBitmap();

                //File filepath = Environment.getExternalStorageDirectory();
                File dir = new File(camera.this.getFilesDir() + "/Pit App Files");
                dir.mkdir();
                File file = new File(dir, System.currentTimeMillis() + ".jpg");
                try{
                    outputStream[0] = new FileOutputStream(file);
                } catch(FileNotFoundException e){
                    e.printStackTrace();
                }
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream[0]);
                Toast.makeText(camera.this, "Image Saved Successfully", Toast.LENGTH_LONG).show();
                try {
                    outputStream[0].flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    outputStream[0].close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });
        photoButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    requestPermissions(new String[]{Manifest.permission.CAMERA}, MY_CAMERA_PERMISSION_CODE);
                } else {
                    Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(cameraIntent, CAMERA_REQUEST);
                }

            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == MY_CAMERA_PERMISSION_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "camera permission granted", Toast.LENGTH_LONG).show();
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);
            } else {
                Toast.makeText(this, "camera permission denied", Toast.LENGTH_LONG).show();
            }
        }
    }



        @Override
        protected void onActivityResult ( int requestCode, int resultCode, Intent data)
        {
            if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK) {
                Bitmap photo = (Bitmap) data.getExtras().get("data");
                imageView.setImageBitmap(photo);
                addToGallery();
            }

        }
        private void addToGallery() {
            Intent galleryIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
            File f = new File(camera.this.getFilesDir() + "/Pit App Files");
            Uri picUri = Uri.fromFile(f);
            galleryIntent.setData(picUri);
            this.sendBroadcast(galleryIntent);
    }
    public void openNewActivity1(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    }