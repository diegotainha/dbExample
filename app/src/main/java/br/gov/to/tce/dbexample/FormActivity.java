package br.gov.to.tce.dbexample;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.media.Rating;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class FormActivity extends AppCompatActivity {

    File file;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
    }

    public void handleSalvar(View button) {
        DBHandler db = new DBHandler(this, "db", 1);

        EditText editName = findViewById(R.id.form_edit_name);
        RatingBar rating = findViewById(R.id.form_rating);

        Reg reg = new Reg(editName.getText().toString(), file.getAbsolutePath(), rating.getRating());

        this.setResult(Activity.RESULT_OK);

        db.insert(reg);

        finish();
    }

    public void handleCameraButton(View button) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE_SECURE);

        String name = "IMG_" + new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());

        File path = getExternalFilesDir(Environment.DIRECTORY_PICTURES);

        try {
            file = File.createTempFile(name, ".jpg", path);

            Uri imageUri = FileProvider.getUriForFile(this, "br.gov.to.tce.dbexample.fileprovider", file);

            intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);

            startActivityForResult(intent, 1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //Atualizar lista se operacao foi bem sucedida
        if (resultCode == Activity.RESULT_OK) {
            ImageView image = findViewById(R.id.form_image);

//            Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());

            Bundle extras = data.getExtras();
            Bitmap bitmap = (Bitmap) extras.get("data");

            image.setImageBitmap(bitmap);
        }

        super.onActivityResult(requestCode, resultCode, data);
    }
}
