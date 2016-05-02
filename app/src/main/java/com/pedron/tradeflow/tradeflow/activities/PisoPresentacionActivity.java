package com.pedron.tradeflow.tradeflow.activities;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.pedron.tradeflow.tradeflow.R;
import com.pedron.tradeflow.tradeflow.util.DatabaseHandler;

import java.util.List;

public class PisoPresentacionActivity extends AppCompatActivity {
    TextView textView, subtitle;
    ImageView btnTackPic;
    Bitmap bitMap;
    ImageView ivThumbnailPhoto;
    static int TAKE_PICTURE = 1;
    boolean pict = false;

    String prod = "";
    String idProducto="";
    List presentacionList;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //SQLiteDatabase db = this.getReadableDatabase();
        //db=getWritableDatabase();
       /* Cursor c = db.rawQuery("SELECT column1,column2,column3 FROM table ", null);
        if(c.moveToFirst()){
            do{
                //assing values
                String column1 = c.getString(0);
                String column2 = c.getString(1);
                String column3 = c.getString(2);
                //Do something Here with values

            }while(c.moveToNext());
        }
        c.close();
        db.close();
        */

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_piso_presentacion);
        Intent i =getIntent();
        prod = i.getStringExtra("producto");
        idProducto=i.getStringExtra("idProducto");
        Bundle bun=i.getExtras();
       // DatabaseHandler db = new DatabaseHandler(this);
        //presentacionList = db.getPresentation(idProducto);

        if(bun.get("data")==null)
        {
            //Toast.makeText(getApplicationContext(),"Data null", Toast.LENGTH_LONG).show();
        }
        else
        {
            //Toast.makeText(getApplicationContext(),"Data bitmap", Toast.LENGTH_LONG).show();
        }
        btnTackPic = (ImageView) findViewById(R.id.camara);

        if(!pict){
            ivThumbnailPhoto = (ImageView) findViewById(R.id.imagen1);
            pict=true;
           // Toast.makeText(getApplicationContext(),"First pic=false", Toast.LENGTH_LONG).show();
        }else{
            ivThumbnailPhoto = (ImageView) findViewById(R.id.imagen2);
        }
        /*for (int ite = 0; ite < presentacionList.size(); ite++) {
            presentacionList.get(ite).toString();
            if(ite==0)
            {
                textView = (TextView) findViewById(R.id.producto0);
                textView.setText(presentacionList.get(ite).toString());
            }
            else
            {
                textView = (TextView) findViewById(R.id.producto2);
                textView.setText(presentacionList.get(ite).toString());
            }
            // 1 - can call methods of element
            // 2 - can use i to make index-based calls to methods of list

            // ...
        }
        */
        textView = (TextView) findViewById(R.id.producto0);
        textView.setText(prod+" 50ml");
        textView = (TextView) findViewById(R.id.producto2);
        textView.setText(prod+" 100ml");

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayShowCustomEnabled(true);
        LayoutInflater inflator = (LayoutInflater) this .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflator.inflate(R.layout.custom_imageview_gray, null);
        actionBar.setCustomView(v);

        textView = (TextView)findViewById(R.id.screen_title);
        textView.setText(R.string.piso_header);
        subtitle = (TextView)findViewById(R.id.screen_subtitle);
        subtitle.setText(prod);

        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#555D52")));

    }
    public void onClickPicture(View view) {

        // create intent with ACTION_IMAGE_CAPTURE action
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        // start camera activity
        startActivityForResult(intent, TAKE_PICTURE);

    }
    public void save(View v) {
        boolean check=false;
        RadioButton rb;
        rb = (RadioButton) findViewById(R.id.radioButton);


        if(!rb.isChecked())
        {
            rb = (RadioButton) findViewById(R.id.radioButton2);
            if(!rb.isChecked())
            {
                Toast.makeText(getApplicationContext(),"Favor de llenar todos los campos", Toast.LENGTH_LONG).show();
            }
            else
            {
                rb = (RadioButton) findViewById(R.id.radioButton3);
                if(!rb.isChecked())
                {
                    rb = (RadioButton) findViewById(R.id.radioButton4);
                    if(!rb.isChecked())
                    {
                 Toast.makeText(getApplicationContext(),"Favor de llenar todos los campos", Toast.LENGTH_LONG).show();
                    }
                    else
                    {
                        check=true;
                    }
                }
                else
                {
                check=true;
                }
            }



        }
        else
        {
            rb = (RadioButton) findViewById(R.id.radioButton3);
            if(!rb.isChecked()) {
                rb = (RadioButton) findViewById(R.id.radioButton4);
                if (!rb.isChecked()) {
                    Toast.makeText(getApplicationContext(),"Favor de llenar todos los campos", Toast.LENGTH_LONG).show();
                } else {
                    check = true;
                }
            }
            else
            {
                check = true;
            }
        }
        if(check) {
            Intent intent = new Intent(PisoPresentacionActivity.this, MenuActivity.class);

            intent.putExtra("producto", prod);
            startActivity(intent);
        }
    }

    public void picRetake1(View v) {
        ivThumbnailPhoto = (ImageView) findViewById(R.id.imagen1);
        boolean hasDraw=(ivThumbnailPhoto.getDrawable() != null);
        if(hasDraw)
        {
           // Toast.makeText(getApplicationContext(),"retake", Toast.LENGTH_LONG).show();
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("¿Desea volver a tomar la fotografia?")
                    .setCancelable(false)
                    .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            //TODO
                            ivThumbnailPhoto.setImageDrawable(null);
                            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                            // start camera activity
                            startActivityForResult(intent, TAKE_PICTURE);
                        }
                    })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });
            AlertDialog alert = builder.create();
            alert.show();

        }
        else
        {
            //Toast.makeText(getApplicationContext(),"draw false", Toast.LENGTH_LONG).show();

        }

    }

    public void picRetake2(View v) {
        ivThumbnailPhoto = (ImageView) findViewById(R.id.imagen2);
        boolean hasDraw=(ivThumbnailPhoto.getDrawable() != null);
        if(hasDraw)
        {
           // Toast.makeText(getApplicationContext(),"retake", Toast.LENGTH_LONG).show();
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("¿Desea volver a tomar la fotografia?")
                    .setCancelable(false)
                    .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            //TODO
                            ivThumbnailPhoto.setImageDrawable(null);
                            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                            // start camera activity
                            startActivityForResult(intent, TAKE_PICTURE);
                        }
                    })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });
            AlertDialog alert = builder.create();
            alert.show();

        }
        else
        {
           // Toast.makeText(getApplicationContext(),"draw false", Toast.LENGTH_LONG).show();

        }

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {

        if (requestCode == TAKE_PICTURE && resultCode== RESULT_OK && intent != null){
            boolean hasDraw=(ivThumbnailPhoto.getDrawable() != null);
                if(hasDraw)
                {
                   // Toast.makeText(getApplicationContext(),"TAKE_PICTURE="+TAKE_PICTURE, Toast.LENGTH_LONG).show();
                   // Toast.makeText(getApplicationContext(),"draw true", Toast.LENGTH_LONG).show();
                    ivThumbnailPhoto = (ImageView) findViewById(R.id.imagen2);
                }
            else
                {
                  //  Toast.makeText(getApplicationContext(),"TAKE_PICTURE="+TAKE_PICTURE, Toast.LENGTH_LONG).show();
                  //  Toast.makeText(getApplicationContext(),"draw false", Toast.LENGTH_LONG).show();

                }
            // get bundle
            Bundle extras = intent.getExtras();

            // get bitmap
            bitMap = (Bitmap) extras.get("data");

            ivThumbnailPhoto.setImageBitmap(bitMap);

            pict=true;



        }
    }
}
