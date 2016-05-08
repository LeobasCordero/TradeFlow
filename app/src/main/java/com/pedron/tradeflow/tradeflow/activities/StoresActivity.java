package com.pedron.tradeflow.tradeflow.activities;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.pedron.tradeflow.tradeflow.R;
import com.pedron.tradeflow.tradeflow.adapters.AdapterStores;
import com.pedron.tradeflow.tradeflow.entity.Store;
import com.pedron.tradeflow.tradeflow.util.Constant;
import com.pedron.tradeflow.tradeflow.util.DatabaseHandler;
import com.pedron.tradeflow.tradeflow.util.DialogClass;
import com.pedron.tradeflow.tradeflow.util.GPSTracker;
import com.pedron.tradeflow.tradeflow.util.HelperUtilities;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Created by leocg on 11/03/2016.
 */
public class StoresActivity extends AppCompatActivity {

    ListView lv, lva;
    ArrayAdapter adapter, adapterAll;
    EditText inputSearch;
    List<Store> storeList, allStores;
    List<String> allStoreString;
    ImageView config;
    TextView textView, dateFooter;
    private View mProgressView, mStoresView, mSFooterView;
    public CheckinTask mCheckinTask = null;
    private Boolean exit = false;
    String idUser;
//    Dialog listDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stores);

        new AlertDialog.Builder(StoresActivity.this)
                .setTitle("Locacion")
                .setMessage("¿Desea habilitar el GPS?")
                .setPositiveButton("Habilitar GPS", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Intent callGPSSettingIntent = new Intent(
                                android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                        startActivity(callGPSSettingIntent);
                    }
                })
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();

        lv = (ListView) findViewById(R.id.list_view_stores);
        lva = (ListView) findViewById(R.id.list_view_all_stores);
        inputSearch = (EditText) findViewById(R.id.inputSearch);
        config = (ImageView) findViewById(R.id.config);
        dateFooter = (TextView)findViewById(R.id.date_footer);
        String todayDate = getFecha();
        dateFooter.setText(todayDate);

        mProgressView = findViewById(R.id.login_progress);
        mStoresView = findViewById(R.id.stores_form);
        mSFooterView = findViewById(R.id.stores_footer);

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayShowCustomEnabled(true);
        LayoutInflater inflator = (LayoutInflater) this .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflator.inflate(R.layout.custom_imageview, null);
        actionBar.setCustomView(v);
        textView = (TextView) findViewById(R.id.screen_title);
        textView.setText(R.string.stores_header);

        Intent intent = getIntent();
        Bundle b = intent.getExtras();
        idUser = b.getString("usuario");

//        turnGPSOn();

        config.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Intent act = new Intent(StoresActivity.this, AlertsActivity.class);
                Bundle extras = new Bundle();
                storeList.get(position);
                Log.i("Leobas", "idTienda: " + storeList.get(position).getIdTienda());
                extras.putString("idTienda", storeList.get(position).getIdTienda());
                act.putExtras(extras);

                startActivity(act);*/
            }
        });

        storeList = getStores();
        allStores = getAllStores();
        allStoreString = getStringFromAllStores(allStores);

        // Adding items to listview
        adapter = new AdapterStores(this, storeList);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               /* ImageView imR = (ImageView) view.findViewById(R.id.sem_rojo);
                ImageView imA = (ImageView) view.findViewById(R.id.sem_ama);
                ImageView imC = (ImageView) view.findViewById(R.id.img_cruz);
                imR.setVisibility(View.INVISIBLE);
                imA.setVisibility(View.VISIBLE);
                if(imC.getVisibility() == View.VISIBLE)
                    imC.setVisibility(View.INVISIBLE);

                showProgress(true);
                mCheckinTask = new CheckinTask(position);
                mCheckinTask.execute((Void) null);*/
                /*Log.i("Leobas", "view tag: " + view.getTag());
                switch(view.getId()){
                    case R.id.img_cruz:
                        Log.i("Leobas", "entro a la cruz");
                        break;
                    case R.id.element_list_store:
                        ImageView imR = (ImageView) view.findViewById(R.id.sem_rojo);
                        ImageView imA = (ImageView) view.findViewById(R.id.sem_ama);
                        ImageView imC = (ImageView) view.findViewById(R.id.img_cruz);
                        imR.setVisibility(View.INVISIBLE);
                        imA.setVisibility(View.VISIBLE);
                        if(imC.getVisibility() == View.VISIBLE)
                            imC.setVisibility(View.INVISIBLE);

                        showProgress(true);
                        mCheckinTask = new CheckinTask(position);
                        mCheckinTask.execute((Void) null);
                        break;
                }*/
                /*showProgress(true);
                mCheckinTask = new CheckinTask(position);
                mCheckinTask.execute((Void) null);*/
            }

        });


        inputSearch.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                if(inputSearch.getText().length() >= 3){
                    if(lv.getVisibility() == View.VISIBLE)
                        verifyVisibility(true);

                    adapterAll = new ArrayAdapter(getApplicationContext(), R.layout.list_item_all_stores, R.id.nombre_tienda_s, allStoreString);
                    lva.setAdapter(adapterAll);

//                    adapterAll.notifyDataSetChanged();

                    lva.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

//                            Log.i(TAG, "onListItemClick: " + position);
                            Store newStore;
                            int lastPos = storeList.size();
                            //enviar el nombre que voy a borrar
                            TextView v = (TextView)view.findViewById(R.id.nombre_tienda_s);
                            newStore = getStoreToAddRemove(v.getText().toString());
                            newStore.setCruz(Constant.CRUZ_TRUE);
                            storeList.add(lastPos, newStore);

                            if(lv.getVisibility() == View.INVISIBLE)
                                verifyVisibility(false);

                            inputSearch.setText("");
                            lv.setAdapter(adapter);
                        }
                    });
                    adapterAll.getFilter().filter(cs);
                }else if(inputSearch.getText().length() == 0){
                    if(lv.getVisibility() == View.INVISIBLE)
                        verifyVisibility(false);
                }

            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
                                          int arg3) {
                // TODO Auto-generated method stub

            }

            @Override
            public void afterTextChanged(Editable arg0) {
                // TODO Auto-generated method stub

                /*if(inputSearch.getText().length() == 0){
                    if(lva.getVisibility() == View.VISIBLE) {
                        Log.i("Leobas", "zero");
                        lva.setVisibility(View.INVISIBLE);
                        lv.setVisibility(View.VISIBLE);
                    }
                }*/
            }
        });
    }


    public void turnGPSOn(){
        Intent intent = new Intent("android.location.GPS_ENABLED_CHANGE");
        intent.putExtra("enabled", true);
        this.sendBroadcast(intent);

        String provider = Settings.Secure.getString(this.getContentResolver(), Settings.Secure.LOCATION_PROVIDERS_ALLOWED);
        if(!provider.contains("gps")){ //if gps is disabled
            final Intent poke = new Intent();
            poke.setClassName("com.android.settings", "com.android.settings.widget.SettingsAppWidgetProvider");
            poke.addCategory(Intent.CATEGORY_ALTERNATIVE);
            poke.setData(Uri.parse("3"));
            this.sendBroadcast(poke);
            Log.i("Leobas", "habilito el GPS");
        }
    }

    // automatic turn off the gps
    public void turnGPSOff(){
        String provider = Settings.Secure.getString(this.getContentResolver(), Settings.Secure.LOCATION_PROVIDERS_ALLOWED);
        if(provider.contains("gps")){ //if gps is enabled
            final Intent poke = new Intent();
            poke.setClassName("com.android.settings", "com.android.settings.widget.SettingsAppWidgetProvider");
            poke.addCategory(Intent.CATEGORY_ALTERNATIVE);
            poke.setData(Uri.parse("3"));
            this.sendBroadcast(poke);
        }
    }

    public List<Store> getStores(){
        DatabaseHandler db = new DatabaseHandler(this);

        return db.getStoresPerUser(idUser);
    }

    public List<Store> getAllStores(){
        DatabaseHandler db = new DatabaseHandler(this);

        return db.getStores(idUser);
    }

    public String getFecha(){
        String date;
        Calendar cal = GregorianCalendar.getInstance();
        HelperUtilities h = new HelperUtilities();
        date = h.getDia(cal.get(Calendar.DAY_OF_WEEK)) + ", " + cal.get(Calendar.DAY_OF_MONTH)
                + " " + h.getMes(cal.MONTH) + " " + cal.get(Calendar.YEAR);

        return date;
    }

    /**
     * Shows the progress UI and hides the login form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mStoresView.setVisibility(show ? View.GONE : View.VISIBLE);
            mSFooterView.setVisibility(show ? View.GONE : View.VISIBLE);

            mStoresView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mStoresView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mStoresView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }


    /**
     * Represents an asynchronous check in for the store
     */
    public class CheckinTask extends AsyncTask<Void, Void, Boolean> {

        private final int position;
        Boolean checkin = true;

        CheckinTask(int pos){
            position = pos;
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            // TODO: attempt authentication against a network service.

            try {
                // Simulate network access.
                LocationManager locManager = (LocationManager)getSystemService(LOCATION_SERVICE);

                LocationListener locListener = new LocationListener() {
                    public void onLocationChanged(Location location) {
                        //Hacer la insercion del registro en BDD
//                        Log.i("Leobas", "location " + location.getLongitude() + ", " + location.getAltitude());
                    }

                    public void onProviderDisabled(String provider){
                        Log.i("Leobas", "GPS apagado");
                    }

                    public void onProviderEnabled(String provider){
                        Log.i("Leobas", "GPS encendido");
                    }

                    public void onStatusChanged(String provider, int status, Bundle extras){
                        Log.i("Leobas", "Status Changed");
                    }
                };

                if ( Build.VERSION.SDK_INT >= 23 &&
                        ContextCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_FINE_LOCATION ) != PackageManager.PERMISSION_GRANTED &&
                        ContextCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//                    return true;
                }

                Thread.sleep(4000);
            } catch (Exception e) {
                Log.i("Leobas", e.getMessage());
                return false;
            }
            // TODO: register the new account here.
            return checkin;
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            mCheckinTask = null;
            showProgress(false);

            if (success) {
                GPSTracker gps = new GPSTracker(getApplicationContext());
                if(gps.canGetLocation())
                {
                    gps.getLatitude(); // returns latitude
                    gps.getLongitude(); // returns longitude
                    Toast.makeText(getApplicationContext(), "Locacion: "+gps.getLatitude()+", "+gps.getLongitude(), Toast.LENGTH_LONG).show();
                    Toast.makeText(getApplicationContext(), R.string.check_in_toast, Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(getApplicationContext(), "No se puede obtener la ubicacion", Toast.LENGTH_LONG).show();
                }
                //finish();
                Intent launchActivity = new Intent(StoresActivity.this, AlertsActivity.class);
                Bundle extras = new Bundle();
                storeList.get(position);
//                Log.i("Leobas", "idTienda: " + storeList.get(position).getIdTienda());
                extras.putString("idTienda", storeList.get(position).getIdTienda());
                launchActivity.putExtras(extras);
                turnGPSOff();
                startActivity(launchActivity);
//                Toast.makeText(getApplicationContext(), R.string.check_in_toast, Toast.LENGTH_LONG).show();
            } else {
                Log.i("Leobas", "10");
                //Seria regresar los foquitos como estaban
            }
            Log.i("Leobas", "11");
        }

        @Override
        protected void onCancelled() {
            mCheckinTask = null;
            showProgress(false);
        }
    }


    @Override
    public void onBackPressed() {
        DialogClass dialog = new DialogClass(StoresActivity.this);
        dialog.showCustomDialog();
    }

    public List<String> getStringFromAllStores(List<Store> allStoresList){
        List<String> allNameStores = new ArrayList<String>();
        for(Store s : allStoresList){
            allNameStores.add(s.getNombreTienda());
        }

        return allNameStores;
    }

    public void verifyVisibility(boolean visibility){
        if(visibility){
            lv.setVisibility(View.INVISIBLE);
            lva.setVisibility(View.VISIBLE);
        }else{
            lva.setVisibility(View.INVISIBLE);
            lv.setVisibility(View.VISIBLE);
        }
    }

    public Store getStoreToAddRemove(String nom){
        Store storeToRemove = new Store();

        for(int i = 0; i <= allStores.size(); i++){
            if(allStores.get(i).getNombreTienda().equals(nom)){
                storeToRemove = allStores.get(i);
                storeToRemove.setCruz(Constant.CRUZ_TRUE);
                allStores.remove(i);
                allStoreString.clear();
                allStoreString = getStringFromAllStores(allStores);
                break;
            }
        }

        return storeToRemove;
    }

    public void removeStoreFromList(int position){
        Store s = storeList.get(position);
        allStores.add(s);
        allStoreString = getStringFromAllStores(allStores);
        storeList.remove(position);
        adapter.notifyDataSetChanged();
    }

    public void nextTask(View parent, int position){
        ImageView imR = (ImageView) parent.findViewById(R.id.sem_rojo);
        ImageView imA = (ImageView) parent.findViewById(R.id.sem_ama);
        ImageView imC = (ImageView) parent.findViewById(R.id.img_cruz);

        if(imR.getVisibility() == View.VISIBLE) {
            imR.setVisibility(View.INVISIBLE);
            imA.setVisibility(View.VISIBLE);
        }
        if(imC.getVisibility() == View.VISIBLE)
            imC.setVisibility(View.INVISIBLE);

        showProgress(true);
        mCheckinTask = new CheckinTask(position);
        mCheckinTask.execute((Void) null);
    }

}
