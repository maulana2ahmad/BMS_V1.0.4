package com.example.bms;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.bms.fragment.OccupancyByTVFragment;
import com.example.bms.fragment.OccupancyDetileFragment;
import com.example.bms.fragment.OccupancyIndustryFragment;
import com.example.bms.fragment.SummaryFragment;
import com.example.bms.services.ApiRetrofit;
import com.google.android.material.navigation.NavigationView;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    private ActionBarDrawerToggle toggle;
    private NavigationView mNavigation;
    private DrawerLayout mdrawerLayout;
    private Toolbar toolbar;
    private String token;
    private TextView tvUsername;

    private String title = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //token = getIntent().getStringExtra("token");
        token = getSharedPreferences("TOKEN", 0)
                .getString("x", "");

        mdrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        mNavigation = (NavigationView) findViewById(R.id.navigation);

        mNavigation.setNavigationItemSelectedListener(this);

        setSupportActionBar(toolbar);

        toolbar.setLogo(R.drawable.logomnc); //use logo no click
        //toolbar.setNavigationIcon(R.drawable.logomnc);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true); //arrow back in toolbal

        mdrawerLayout.addDrawerListener(toggle);
        toggle = new ActionBarDrawerToggle(HomeActivity.this, mdrawerLayout, toolbar,
                R.string.open, R.string.close);
        toggle.syncState();

        //main layout
        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, new SummaryFragment(token))
                    .commit();
            title = ("Summary 4 TV");
            mNavigation.setCheckedItem(R.id.menu_summary);
            //end main layout
        }

        setActionBarTitle(title);

        retrotittoketn();

    }

    private void retrotittoketn() {

        ApiRetrofit retrofittoken = new ApiRetrofit();
        retrofittoken.ApiRetrofit2(token);
    }


    //implement method
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId())
        {
            case R.id.menu_summary :
                title = ("Summary 4 TV");
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, new SummaryFragment(token))
                        .commit();
                break;

            case R.id.menu_occupancyByTv :
                title = ("Occupancy By TV");
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, new OccupancyByTVFragment())
                        .commit();
                break;

            case R.id.menu_occupancyDetail :
                title = ("Occupancy Detail");
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, new OccupancyDetileFragment())
                        .commit();
                break;

            case R.id.menu_occupancy_industry :
                title = ("Occupancy Industry");
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, new OccupancyIndustryFragment())
                        .commit();
                break;

            case R.id.menu_logout:
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                finish();
        }

        mdrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {

        if (mdrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mdrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    //method title
    private void setActionBarTitle(String title) {

        if (getSupportActionBar() != null)
        {
            getSupportActionBar().setTitle(title);
        }
    }

}
