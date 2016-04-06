package com.pedron.tradeflow.tradeflow.activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.pedron.tradeflow.tradeflow.R;

public class PisoPresentacionActivity extends AppCompatActivity {
    TextView textTitle, textSub;
    ImageView btnTackPic;
    Bitmap bitMap;
    ImageView ivThumbnailPhoto;
    static int TAKE_PICTURE = 1;
    int pict=1;
    String prod="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resurtido);
        Intent i = getIntent();
        prod = i.getStringExtra("producto");
        btnTackPic = (ImageView) findViewById(R.id.camara);

        if(pict == 1){
            ivThumbnailPhoto = (ImageView) findViewById(R.id.imagen1);
            pict++;
        }
        else{
            ivThumbnailPhoto = (ImageView) findViewById(R.id.imagen2);
        }

//        textTitle = (TextView) findViewById(R.id.producto0);
//        textTitle.setText(prod);
//        textView = (TextView) findViewById(R.id.producto2);
//        textView.setText(prod);

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayShowCustomEnabled(true);
        LayoutInflater inflator = (LayoutInflater) this .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflator.inflate(R.layout.custom_imageview_gray, null);
        actionBar.setCustomView(v);

        textTitle = (TextView) findViewById(R.id.screen_title);
        textTitle.setText(R.string.resurtido_header);

        textSub = (TextView) findViewById(R.id.screen_subtitle);
        textSub.setText(prod);

//        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#555D52")));

    }
    public void onClickPicture(View view) {

        // create intent with ACTION_IMAGE_CAPTURE action
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        // start camera activity
        startActivityForResult(intent, TAKE_PICTURE);

    }
    public void save(View v) {
        Intent intent = new Intent(PisoPresentacionActivity.this, BodegaActivity.class);

        intent.putExtra("producto", prod);
        startActivity(intent);

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {

        if (requestCode == TAKE_PICTURE && resultCode== RESULT_OK && intent != null){
            // get bundle
            Bundle extras = intent.getExtras();

            // get bitmap
            bitMap = (Bitmap) extras.get("data");
                ivThumbnailPhoto.setImageBitmap(bitMap);

        }
    }
}
