package com.pedron.tradeflow.tradeflow.activities;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.pedron.tradeflow.tradeflow.R;
import com.pedron.tradeflow.tradeflow.entity.User;
import com.pedron.tradeflow.tradeflow.entity.Visit;
import com.pedron.tradeflow.tradeflow.util.Constant;
import com.pedron.tradeflow.tradeflow.util.DatabaseHandler;

import java.util.ArrayList;
import java.util.List;

import static android.Manifest.permission.READ_CONTACTS;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity implements LoaderCallbacks<Cursor> {

    /**
     * Id to identity READ_CONTACTS permission request.
     */
    private static final int REQUEST_READ_CONTACTS = 0;

    /**
     * A dummy authentication store containing known user names and passwords.
     */
    private static final String[] DUMMY_CREDENTIALS = new String[]{
            "00000:leobas", "01234:password", "123456:123456"
    };
    /**
     * Keep track of the login task to ensure we can cancel it if requested.
     */
    private UserLoginTask mAuthTask = null;

    // UI references.
    private AutoCompleteTextView mUserView;
    private EditText mPasswordView;
    private View mProgressView;
    private View mLoginFormView;

    private DatabaseHandler db;

    private List<User> uList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // Set up the login form.
        mUserView = (AutoCompleteTextView) findViewById(R.id.user);
        mUserView.setText("");
        populateAutoComplete();
        db = new DatabaseHandler(this);
        // add usr to database
        populateDB();

        mPasswordView = (EditText) findViewById(R.id.password);
        mPasswordView.setText("");
        mUserView.setNextFocusDownId(mPasswordView.getId());
        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.id.login || id == EditorInfo.IME_NULL) {
                    attemptLogin();
                    return true;
                }
                return false;
            }
        });

        Button mUserSignInButton = (Button) findViewById(R.id.user_sign_in_button);
        mUserSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });

        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);

        uList = db.getUsers();
    }

    private void populateAutoComplete() {
        if (!mayRequestContacts()) {
            return;
        }

        getLoaderManager().initLoader(0, null, this);
    }

    private boolean mayRequestContacts() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true;
        }
        if (checkSelfPermission(READ_CONTACTS) == PackageManager.PERMISSION_GRANTED) {
            return true;
        }
        if (shouldShowRequestPermissionRationale(READ_CONTACTS)) {
            Snackbar.make(mUserView, R.string.permission_rationale, Snackbar.LENGTH_INDEFINITE)
                    .setAction(android.R.string.ok, new View.OnClickListener() {
                        @Override
                        @TargetApi(Build.VERSION_CODES.M)
                        public void onClick(View v) {
                            requestPermissions(new String[]{READ_CONTACTS}, REQUEST_READ_CONTACTS);
                        }
                    });
        } else {
            requestPermissions(new String[]{READ_CONTACTS}, REQUEST_READ_CONTACTS);
        }
        return false;
    }

    /**
     * Callback received when a permissions request has been completed.
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (requestCode == REQUEST_READ_CONTACTS) {
            if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                populateAutoComplete();
            }
        }
    }


    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    private void attemptLogin() {
        if (mAuthTask != null) {
            return;
        }

        // Reset errors.
        mUserView.setError(null);
        mPasswordView.setError(null);

        // Store values at the time of the login attempt.
        String user = mUserView.getText().toString();
        String password = mPasswordView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid password, if the user entered one.
        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            mPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordView;
            cancel = true;
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(user)) {
            mUserView.setError(getString(R.string.error_field_required));
            focusView = mUserView;
            cancel = true;
        } else if (!isUserValid(user)) {
            mUserView.setError(getString(R.string.error_invalid_user));
            focusView = mUserView;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            showProgress(true);
            mAuthTask = new UserLoginTask(user, password);
            mAuthTask.execute((Void) null);
        }
    }


    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() > 6;
    }

    private boolean isUserValid(String user) {
        return user.length() > 1 && user.length() < 6;
    }

    /**
     * Shows the progress UI and hides the login form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            mLoginFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
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
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        return new CursorLoader(this,
                // Retrieve data rows for the device user's 'profile' contact.
                Uri.withAppendedPath(ContactsContract.Profile.CONTENT_URI,
                        ContactsContract.Contacts.Data.CONTENT_DIRECTORY), ProfileQuery.PROJECTION,

                // Select only email addresses.
                ContactsContract.Contacts.Data.MIMETYPE +
                        " = ?", new String[]{ContactsContract.CommonDataKinds.Email
                .CONTENT_ITEM_TYPE},

                // Show primary email addresses first. Note that there won't be
                // a primary email address if the user hasn't specified one.
                ContactsContract.Contacts.Data.IS_PRIMARY + " DESC");
    }

    @Override
    public void onLoadFinished(Loader<Cursor> cursorLoader, Cursor cursor) {
        List<String> users = new ArrayList<>();
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            users.add(cursor.getString(ProfileQuery.ADDRESS));
            cursor.moveToNext();
        }

        addUsersToAutoComplete(users);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> cursorLoader) {

    }

    private void addUsersToAutoComplete(List<String> usersCollection) {
        //Create adapter to tell the AutoCompleteTextView what to show in its dropdown list.
        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(LoginActivity.this,
                        android.R.layout.simple_dropdown_item_1line, usersCollection);

        mUserView.setAdapter(adapter);
    }


    private interface ProfileQuery {
        String[] PROJECTION = {
                ContactsContract.CommonDataKinds.Email.ADDRESS,
                ContactsContract.CommonDataKinds.Email.IS_PRIMARY,
        };

        int ADDRESS = 0;
        int IS_PRIMARY = 1;
    }

    /**
     * Represents an asynchronous login/registration task used to authenticate
     * the user.
     */
    public class UserLoginTask extends AsyncTask<Void, Void, Boolean> {

        private final String mUser;
        private final String mPassword;

        UserLoginTask(String email, String password) {
            mUser = email;
            mPassword = password;
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            // TODO: attempt authentication against a network service.

            try {
                // Simulate network access.
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                return false;
            }

            Boolean okLogin = false;

            for (int i = 0 ; i < uList.size() ; i++){
                User u = uList.get(i);
                if(mUser.equals(u.getUsuario())){
                    if(mPassword.equals(u.getContrasena())){
                        okLogin = true;
                    }
                }
            }


            // TODO: register the new account here.
            return okLogin;
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            mAuthTask = null;
            showProgress(false);

            if (success) {
               List<Visit> l = db.getVisits(mUser);
                if(l != null && l.size() > 0){
                    db.deleteRegistryFromVisits();
                    db.addVisitTradeflow("12345", "true", "ubicacion", "idTienda", "0", "0");
                }

                //finish();
                Intent intent = new Intent(LoginActivity.this, StoresActivity.class);
                Bundle extras = new Bundle();
                extras.putString("usuario", mUser);
                intent.putExtras(extras);

                //start the new activity
                startActivity(intent);
            } else {
                mPasswordView.setError(getString(R.string.error_incorrect_password));
                mPasswordView.requestFocus();
            }
        }

        @Override
        protected void onCancelled() {
            mAuthTask = null;
            showProgress(false);
        }
    }

    public void populateDB(){
        db = new DatabaseHandler(this);

        int x = db.checkTables("usuarios_tradeflow");

        if(x != Constant.DBVERSION) {
            db.addUserTradeflow("12345", "admin123", "admin", "0");

            db.addStoreTradeflow("441", "Ruiz Cortinez (3512)", "Walmart", "Supermercado", "1225", "Rodesia del Nte #402, col. San Roque, cp. 65008, San Pedro, Nuevo Leon", "12345", "n");
            db.addStoreTradeflow("610", "Centro (3022)", "Soriana", "Almacen", "855", "Jacarandas #233, col. Linda Vista, cp. 63005, Guadalupe, Nuevo Leon", "12345", "n");
            db.addStoreTradeflow("9405", "Los Angeles (5114)", "HEB", "Supermercado", "322", "Romulo Garza #2988, col. Los Morales, cp. 24452, San Nicolas de los Garza, Nuevo Leon", "12345", "n");
            db.addStoreTradeflow("102", "Escobedo (9663)", "OXXO", "CEDIS", "650", "Lazaro Cardenas #2011, col. Centrito Valle, cp. 66305, San Pedro, Nuevo Leon", "12345", "n");
            db.addStoreTradeflow("3365", "Universidad (1025)", "Famosa", "Almacen", "100", "Cuahutemoc #522, col. Centro, cp.68000, Monterrey, Nuevo Leon", "12345", "n");
            db.addStoreTradeflow("6631", "Juarez(9085)", "Comercial Treviño", "Almacen", "600", "Mariano Escobedo #2223, col. Santa Fe, cp. 69908, Santa Catarina, Nuevo Leon", "23456", "n");
            db.addStoreTradeflow("4461", "Guadalajara(2235)", "Mi Tiendita", "Supermercado", "325", "San Roque #390, col. Metropolitana, cp. 69830, El Carmen, Nuevo Leon", "23456", "n");


            db.addAlertTradeflow("100", "SQL completo, donde indicamos los campos", "0");
            db.addAlertTradeflow("100", "La polémica por la manera en la que son tratados los refugiados ", "0");
            db.addAlertTradeflow("100", "Developing a custom adapter", "0");
            db.addAlertTradeflow("322", "Como en el caso de los métodos de modificación de datos", "0");
            db.addAlertTradeflow("650", "Tutorial describes how to use the ListView view together with activities and fragments in A", "0");
            db.addAlertTradeflow("650", "Above syntax is calling startActivity method", "0");
            db.addAlertTradeflow("1225", "Intent is an abstract description of an operation", "0");
            db.addAlertTradeflow("855", "Existen 5 continentes, no 2. solo por que seas un continente con gran cultura", "0");
            db.addAlertTradeflow("855", "Durante un breve periodo de tiempo la solidaridad con el pueblo", "0");
            db.addAlertTradeflow("600", "Todo comenzó mal con el nuevo plan de Hoy No Circula, pues en el primer día de operación se registraron nuevamente niveles altos de partículas", "0");
            db.addAlertTradeflow("600", "Se registró un índice de contaminación superior a los 150 puntos IMECA", "0");
            db.addAlertTradeflow("600", "Entre las medidas que se podrían aplicar en la Fase 1 de Contingencia, se contempla el Doble No Circula", "0");

            db.addNewsTradeflow("1", "MES DEL BEBE = Capent Participará en las promociones que implemente la campaña de Comercial Mexicana");
            db.addNewsTradeflow("1", "Asegurar precio de venta de HINDS nutritiva (Edicion especial)");
            db.addNewsTradeflow("2", "AsyncTask allows you to perform asynchronous work on your user interface");
            db.addNewsTradeflow("3", "Las espantosas condiciones en las que vivían los niños conmocionaron al mundo y las organizaciones humanitarias");
            db.addNewsTradeflow("22", "Cientos de niños fueron adoptados por familias en Occidente");
            db.addNewsTradeflow("14", "Tras poco más de cuatro meses de haber llegado a Colombia, el corresponsal de BBC");
            db.addNewsTradeflow("14", "Así lo cuenta en este texto de corte personal y algo lúdico.");
            db.addNewsTradeflow("11", "Él intentará evitarlo en su cicla. Y por la noche se juntarán a hacer parche.");
            db.addNewsTradeflow("11", "Estas son algunas de ellas, una pequeña muestra, con la aclaración de que, como vivo en Bogotá");
            db.addNewsTradeflow("10", "El Breve Diccionario de Colombianismos de la Academia Colombiana de la Lengua la define como encogerse para dormir, hacerse un ovillo");
            db.addNewsTradeflow("19", "Tras poco más de cuatro meses de haber llegado a Colombia, el corresponsal de BBC");
            db.addNewsTradeflow("8", "Así lo cuenta en este texto de corte personal y algo lúdico.");
            db.addNewsTradeflow("9", "Él intentará evitarlo en su cicla. Y por la noche se juntarán a hacer parche.");
            db.addNewsTradeflow("25", "Estas son algunas de ellas, una pequeña muestra, con la aclaración de que, como vivo en Bogotá");
            db.addNewsTradeflow("22", "El Breve Diccionario de Colombianismos de la Academia Colombiana de la Lengua la define como encogerse para dormir, hacerse un ovillo");

            db.addClientTradeflow("1", "1");
            db.addClientTradeflow("1", "2");
            db.addClientTradeflow("1", "3");
            db.addClientTradeflow("1", "22");
            db.addClientTradeflow("1", "14");
            db.addClientTradeflow("2", "11");
            db.addClientTradeflow("2", "10");
            db.addClientTradeflow("2", "1");
            db.addClientTradeflow("3", "19");
            db.addClientTradeflow("4", "11");
            db.addClientTradeflow("4", "10");
            db.addClientTradeflow("4", "8");
            db.addClientTradeflow("4", "22");
            db.addClientTradeflow("5", "25");
            db.addClientTradeflow("5", "18");
            db.addClientTradeflow("6", "2");
            db.addClientTradeflow("6", "1");
            db.addClientTradeflow("6", "15");
            db.addClientTradeflow("7", "21");

            db.addProductTradeflow("1", "1", "Candimon");
            db.addProductTradeflow("2", "1", "Pasta de Lassar");
            db.addProductTradeflow("3", "1", "Hipoglos");
            db.addProductTradeflow("4", "1", "Chipotle Adobado");
            db.addProductTradeflow("5", "1", "Jalapeños");
            db.addProductTradeflow("6", "1", "Jalapeños Rellenos");
            db.addProductTradeflow("7", "2", "Rajas Rojas");
            db.addProductTradeflow("8", "2", "Rajas Verdes");
            db.addProductTradeflow("9", "2", "Rodajas Nachos");
            db.addProductTradeflow("10", "3", "Frijoles Bayos");
            db.addProductTradeflow("11", "3", "Frijoles Negros");
            db.addProductTradeflow("12", "3", "Piña");
            db.addProductTradeflow("13", "3", "Mango");
            db.addProductTradeflow("14", "3", "Durazno");
            db.addProductTradeflow("15", "3", "Salsa Roja");
            db.addProductTradeflow("16", "3", "Salsa Picante");
            db.addProductTradeflow("17", "4", "Salsa Verde");
            db.addProductTradeflow("18", "5", "Zanahoria");
            db.addProductTradeflow("19", "5", "Pure de Tomate");
            db.addProductTradeflow("20", "5", "Brochas");
            db.addProductTradeflow("21", "5", "Rodillos");
            db.addProductTradeflow("22", "5", "Solvente");
            db.addProductTradeflow("23", "5", "Impermeabilizantes");
            db.addProductTradeflow("24", "6", "Aerosoles");
            db.addProductTradeflow("25", "6", "Premier 10 años");
            db.addProductTradeflow("26", "6", "Plus 5 años");
            db.addProductTradeflow("27", "6", "Excedrin");
            db.addProductTradeflow("28", "6", "Capent");
            db.addProductTradeflow("29", "6", "Lamisil");
            db.addProductTradeflow("30", "7", "Voltaren");
            db.addProductTradeflow("31", "7", "Tesalon");
            db.addProductTradeflow("32", "7", "Teraflu");
            db.addProductTradeflow("33", "7", "Senokot");
            db.addProductTradeflow("34", "7", "Corega");
            db.addProductTradeflow("35", "8", "Eclipsol");

            db.addVisitTradeflow("12345", "true", "ubicacion", "idTienda", "0", "0");
        }
//        db.addActivityTradeflow("", "", "");
        db.close();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPasswordView.setText("");
        mUserView.setText("");
    }
}

