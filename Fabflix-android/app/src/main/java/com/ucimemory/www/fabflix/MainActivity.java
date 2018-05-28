package com.ucimemory.www.fabflix;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;

import static com.ucimemory.www.fabflix.R.id.mainLayout;


public class MainActivity extends AppCompatActivity {

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
//                    showFragment(new homeFragment());
                    return true;
                case R.id.navigation_movies:
                    showFragment(new movieFragment());
                    return true;
                case R.id.navigation_me:
//                    showFragment(new meFragment());
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Bundle bundle = getIntent().getExtras();
        try{
            Toast toast = Toast.makeText(this, bundle.get("message") + "!",Toast.LENGTH_LONG);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
        }
        catch (Exception e) {
        }
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    protected void showFragment(Fragment fragment) {
        if (fragment == null)
            return;
        FragmentManager fM = getFragmentManager();
        if (fM != null) {
            FragmentTransaction ft = fM.beginTransaction();
            if (ft != null) {
                ft.replace(R.id.mainLayout, fragment);
                ft.commit();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.search_m:
                super.onSearchRequested(); //start search dialog
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
