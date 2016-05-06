package com.pedron.tradeflow.tradeflow.activities;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.pedron.tradeflow.tradeflow.R;

public class BodegaActivity extends AppCompatActivity {
    TextView textView, subtitle;
    ImageView btnTackPic;
    Bitmap bitMap;
    ImageView ivThumbnailPhoto;
    static int TAKE_PICTURE = 1;
    final Context context = this;
    String prod = "";
    String idMarca = "";
    String idProducto="";
    String opMenu="";
    boolean pict = false;

    public void onClickPicture(View view) {

        // create intent with ACTION_IMAGE_CAPTURE action
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        // start camera activity
        startActivityForResult(intent, TAKE_PICTURE);

    }
    public void save(View v) {
        boolean check=false;
        EditText et,et2;
        et = (EditText) findViewById(R.id.editText);
        et2 = (EditText) findViewById(R.id.editText2);

        if((et.getText()+"1").equals("1")||(et2.getText()+"1").equals(""))
        {
            Toast.makeText(getApplicationContext(), "Favor de llenar todos los campos", Toast.LENGTH_LONG).show();
        }
        else
        {
            //Toast.makeText(getApplicationContext(), "et,et2"+et.getText()+","+et2.getText(), Toast.LENGTH_LONG).show();
            check=true;
        }

        if(check) {
            Intent intent = new Intent(BodegaActivity.this, MenuActivity.class);
            final Dialog dialog = new Dialog(context);
            dialog.setContentView(R.layout.custom_dialog);
            dialog.setTitle("\t\t\t\t\t\t\tBodega");


            // set the custom dialog components - text, image and button
            TextView text = (TextView) dialog.findViewById(R.id.text);
            //text.setText("Android custom dialog example!");
            text.setText("¿Desea continuar hacia resurtido o regresar a bodega?");
            ImageView image = (ImageView) dialog.findViewById(R.id.image);
            ImageView image2 = (ImageView) dialog.findViewById(R.id.image2);
            image2.setImageResource(R.drawable.resurtido);
            image2.getLayoutParams().width=100;
            // Button dialogButton = (Button) dialog.findViewById(R.id.dialogButtonOK);
            // if button is clicked, close the custom dialog
            image2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(BodegaActivity.this, ResurtidoActivity.class);
                    dialog.dismiss();
                    intent.putExtra("producto", prod);
                    intent.putExtra("idMarca", idMarca);
                    intent.putExtra("opMenu", "2");
                    startActivity(intent);
                }
            });
            image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(BodegaActivity.this, ProductsActivity.class);
                    dialog.dismiss();
                    intent.putExtra("producto", prod);
                    intent.putExtra("idMarca", idMarca);
                    intent.putExtra("opMenu", "3");
                    startActivity(intent);
                }
            });
            dialog.show();
            intent.putExtra("producto", prod);
            //startActivity(intent);
        }
    }

    public void picRetake1(View v) {
        ivThumbnailPhoto = (ImageView) findViewById(R.id.imagen1);
        boolean hasDraw=(ivThumbnailPhoto.getDrawable() != null);
        if(hasDraw)
        {
          //  Toast.makeText(getApplicationContext(),"retake", Toast.LENGTH_LONG).show();
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
          //  Toast.makeText(getApplicationContext(),"draw false", Toast.LENGTH_LONG).show();

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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bodega);
        Intent i =getIntent();
        prod = i.getStringExtra("producto");
        idMarca = i.getStringExtra("idMarca");
        idProducto=i.getStringExtra("idProducto");
        opMenu=i.getStringExtra("opMenu");

        btnTackPic = (ImageView) findViewById(R.id.camara);
        prod = i.getStringExtra("producto");

        if(!pict){
            ivThumbnailPhoto = (ImageView) findViewById(R.id.imagen1);
            pict=true;
        }else{
            ivThumbnailPhoto = (ImageView) findViewById(R.id.imagen2);
        }

        textView = (TextView) findViewById(R.id.producto0);
        textView.setText(prod+ " pza");
        textView = (TextView) findViewById(R.id.producto2);
        textView.setText(prod+ "pza");
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayShowCustomEnabled(true);
        LayoutInflater inflator = (LayoutInflater) this .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflator.inflate(R.layout.custom_imageview_gray, null);
        actionBar.setCustomView(v);

        textView = (TextView)findViewById(R.id.screen_title);
        textView.setText(R.string.bodega_header);
        subtitle = (TextView)findViewById(R.id.screen_subtitle);
        subtitle.setText(prod);

        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#555D52")));

    }

}
