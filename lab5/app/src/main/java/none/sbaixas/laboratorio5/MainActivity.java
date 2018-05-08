package none.sbaixas.laboratorio5;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    FormFragment fragment;
    FormViewFragment viewFragment;
    private SharedPreferences settings;
    private TextView emailTextView;
    public static final String LOGIN_PREFERENCES = "LoginPrefs";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_menu);
        settings = getSharedPreferences(LOGIN_PREFERENCES, MODE_PRIVATE);
        setContentView(R.layout.activity_main);
        emailTextView = (TextView) findViewById(R.id.username);
        String email = settings.getString("userEmail", null);
        String password = settings.getString("userPwd", null);
        if(email == null && password == null) {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivityForResult(intent, 48);
        }
        else{
            emailTextView.setText(email);
        }
        drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navView = findViewById(R.id.nav_view);
        NavigationView.OnNavigationItemSelectedListener listener = new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId()==R.id.nav_new_form){
                    fragment = new FormFragment();
                    android.support.v4.app.FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.main_frame, fragment).commit();
                    transaction.addToBackStack(null);
                }
                if(item.getItemId()==R.id.nav_view_forms){
                    viewFragment = new FormViewFragment();
                    android.support.v4.app.FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.main_frame, viewFragment).commit();
                    transaction.addToBackStack(null);
                }
                drawerLayout.closeDrawers();
                return true;
            }
        };



        navView.setNavigationItemSelectedListener(listener);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(resultCode == RESULT_OK){
            SharedPreferences.Editor prefEditor = settings.edit();
            prefEditor.putString("userEmail", data.getExtras().getString("email"));
            prefEditor.putString("userPwd", data.getExtras().getString("password"));
            prefEditor.putString("userTkn", data.getExtras().getString("token"));
            prefEditor.commit();
            emailTextView.setText(data.getExtras().getString("email"));
            Context context = getApplicationContext();
            CharSequence text = "Login Successful";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
    }

    public void onLogoutClick(View view){
        SharedPreferences.Editor prefEditor = settings.edit();
        prefEditor.remove("userEmail");
        prefEditor.remove("userPwd");
        prefEditor.remove("userTkn");
        prefEditor.commit();
        emailTextView.setText("");
        Intent intent = new Intent(this, LoginActivity.class);
        startActivityForResult(intent, 48);
        new Thread(new Runnable() {
            @Override
            public void run() {
                MainApplication.formDatabase.daoAccess().nukeTable();
            }
        }).start();
    }

    public void onDoneClick(View view){
        fragment.onDoneClick(view);
    }

    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() > 0) {
            getFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
    }
}
