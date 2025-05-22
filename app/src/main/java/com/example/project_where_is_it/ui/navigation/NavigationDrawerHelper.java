package com.example.project_where_is_it.ui.navigation;


import android.app.Activity;
import android.view.MenuItem;
import androidx.annotation.NonNull;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import com.example.project_where_is_it.R;
import com.example.project_where_is_it.ui.fragment.SearchFragment;
import com.example.project_where_is_it.ui.fragment.ResultsFragment;
import com.example.project_where_is_it.ui.fragment.MapFragment;
import com.google.android.material.navigation.NavigationView;

public class NavigationDrawerHelper {

    public static void setupDrawer(Activity activity, DrawerLayout drawerLayout, NavigationView navigationView, FragmentManager fragmentManager) {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFragment = null;
                int itemId = item.getItemId();

                if (itemId == R.id.nav_search) {
                    selectedFragment = new SearchFragment();
                } else if (itemId == R.id.nav_results) {
                    selectedFragment = new ResultsFragment();
                } else if (itemId == R.id.nav_map) {
                    selectedFragment = new MapFragment();
                } else if (itemId == R.id.nav_exit) {
                    activity.finish(); // Exit the app
                    return true;
                }

                if (selectedFragment != null) {
                    fragmentManager.beginTransaction()
                            .replace(R.id.fragment_container, selectedFragment)
                            .commit();
                }

                drawerLayout.closeDrawers();
                return true;
            }
        });
    }
}
