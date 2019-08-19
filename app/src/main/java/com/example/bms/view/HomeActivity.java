package com.example.bms.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.bms.R;
import com.example.bms.view.fragment.OccupancyByTVFragment;
import com.example.bms.view.fragment.OccupancyDetileFragment;
import com.example.bms.view.fragment.OccupancyIndustryFragment;
import com.example.bms.view.fragment.SummaryFragment;
import com.google.android.material.navigation.NavigationView;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    private ActionBarDrawerToggle toggle;
    private NavigationView mNavigation;
    private DrawerLayout mdrawerLayout;
    private Toolbar toolbar;

    private String TITLE = "Summary";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mdrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        mNavigation = (NavigationView) findViewById(R.id.navigation);

        mNavigation.setNavigationItemSelectedListener(this);


        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.logomnc);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        mdrawerLayout.addDrawerListener(toggle);
        toggle = new ActionBarDrawerToggle(HomeActivity.this, mdrawerLayout, toolbar,
                R.string.open, R.string.close);

        //main layout
        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, new SummaryFragment())
                    .commit();
            mNavigation.setCheckedItem(R.id.menu_summary);
            //end main layout
        }
    }

    //implement method
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId())
        {
            case R.id.menu_summary :
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, new SummaryFragment())
                        .commit();
                break;
            case R.id.menu_occupancyByTv :
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, new OccupancyByTVFragment())
                        .commit();
                break;
            case R.id.menu_occupancyDetail :
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, new OccupancyDetileFragment())
                        .commit();
                break;
            case R.id.menu_occupancy_industry :
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, new OccupancyIndustryFragment())
                        .commit();
                break;
        }

        mdrawerLayout.closeDrawer(GravityCompat.START);
        return false;
    }

    @Override
    public void onBackPressed() {

        if (mdrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mdrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

}
