package com.pedron.tradeflow.tradeflow.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import com.pedron.tradeflow.tradeflow.R;
import com.pedron.tradeflow.tradeflow.adapters.AdapterTrademark;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by leocg on 12/03/2016.
 */
public class TrademarkActivity extends AppCompatActivity implements AdapterTrademark.OnItemClickListener{

    private RecyclerView recycler;
    private List<String> trademarkList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.trademark_list);

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setDisplayShowTitleEnabled(false);
            actionBar.setDisplayShowCustomEnabled(true);

            LayoutInflater inflator = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View v = inflator.inflate(R.layout.custom_imageview_marcas, null);

            actionBar.setCustomView(v);
        }

        recycler = (RecyclerView) findViewById(R.id.recycler);
        recycler.hasFixedSize();
        recycler.setItemAnimator(new DefaultItemAnimator());
        recycler.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        trademarkList = getImagesUrls();
        AdapterTrademark adapt = new AdapterTrademark(getApplicationContext(), trademarkList);
        adapt.setOnItemClickListener(this);
        recycler.setAdapter(adapt);
        adapt.notifyDataSetChanged();
    }

    public List<String> getImagesUrls(){
        List<String> tmList = new ArrayList<>();
        String directory = getDirectory();

        //File root = Environment.getExternalStorageDirectory();
        File dir = new File(directory);
        for (File f : dir.listFiles()) {

            if (f.isAbsolute())
                tmList.add(f.getAbsolutePath());
        }

        return tmList;
    }

    public String getDirectory(){
        String directory = "";

        File folder = new File(Environment.getExternalStorageDirectory() +
                File.separator + "TRADEFLOW");
        boolean success = true;
        if (!folder.exists()) {
            success = folder.mkdir();
        }
        if (success) {
            File subfolder = new File(Environment.getExternalStorageDirectory() +
                    File.separator + "TRADEFLOW" + File.separator + "TRADEMARK");


            if (!subfolder.exists()) {
                subfolder.mkdir();
                directory = subfolder.getAbsolutePath();
            }else{
                directory = subfolder.getAbsolutePath();
                Log.i("Leo","Directorio ya creado: " + directory);
            }
        } else {
            new Throwable("No se puede crear el directorio");
        }

        return directory;
    }

    @Override
    public void onItemClick(AdapterTrademark.ViewHolder item, int position) {
        Intent intent = new Intent(TrademarkActivity.this, ProductsActivity.class);

        startActivity(intent);
    }
}