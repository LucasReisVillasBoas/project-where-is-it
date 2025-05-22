package com.example.project_where_is_it.ui.activity;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import com.example.project_where_is_it.R;
import com.example.project_where_is_it.ui.navigation.NavigationDrawerHelper;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_view);

        NavigationDrawerHelper.setupDrawer(this, drawerLayout, navigationView, getSupportFragmentManager());

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new com.example.project_where_is_it.ui.fragment.SearchFragment())
                    .commit();
        }
    }
}
