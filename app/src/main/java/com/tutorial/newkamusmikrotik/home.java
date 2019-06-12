package com.tutorial.newkamusmikrotik;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import android.view.View;
import android.widget.GridLayout;
import android.widget.Toast;
import android.graphics.Color;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import android.net.Uri;

public class home extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    GridLayout mainGrid;
    AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        mainGrid = (GridLayout) findViewById(R.id.mainGrid);

        // set Event
        // setToggleEvent(mainGrid);
        setSingleEvent(mainGrid);

        // Sample AdMob app ID: ca-app-pub-3940256099942544~3347511713
        MobileAds.initialize(this, "ca-app-pub-2684730854768563~1230187668");

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

    }

    /** Called when leaving the activity */
    @Override
    public void onPause() {
        if (mAdView != null) {
            mAdView.pause();
        }
        super.onPause();
    }

    /** Called when returning to the activity */
    @Override
    public void onResume() {
        super.onResume();
        if (mAdView != null) {
            mAdView.resume();
        }
    }

    /** Called before the activity is destroyed */
    @Override
    public void onDestroy() {
        if (mAdView != null) {
            mAdView.destroy();
        }
        super.onDestroy();
    }




    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setMessage("Apakah anda ingin keluar?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        home.this.finish();
                    }
                })
                .setNegativeButton("No", null)
                .show();
    }

    private void setToggleEvent(GridLayout mainGrid) {
        //Loop all child item of Main Grid
        for (int i = 0; i < mainGrid.getChildCount(); i++) {
            //You can see , all child item is CardView , so we just cast object to CardView
            final CardView cardView = (CardView) mainGrid.getChildAt(i);
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (cardView.getCardBackgroundColor().getDefaultColor() == -1) {
                        //Change background color
                        cardView.setCardBackgroundColor(Color.parseColor("#FF6F00"));
                        Toast.makeText(home.this, "State : True", Toast.LENGTH_SHORT).show();

                    } else {
                        //Change background color
                        cardView.setCardBackgroundColor(Color.parseColor("#FFFFFF"));
                        Toast.makeText(home.this, "State : False", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void setSingleEvent(GridLayout mainGrid) {
        for (int i = 0; i < mainGrid.getChildCount(); i++) {
            CardView cardView = (CardView) mainGrid.getChildAt(i);
            final int finalI = i;
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (finalI == 0) // Untuk open Activity
                    {
                        Intent intent = new Intent(home.this, belajarmikrotik.class);
                        startActivity(intent);
                    } else if (finalI == 1) // Untuk open Activity
                    {
                        Intent intent = new Intent(home.this, istilahmikrotik.class);
                        startActivity(intent);
                    } else if (finalI == 2) // Untuk open Activity
                    {
                        Intent intent = new Intent(home.this, videomikrotik.class);
                        startActivity(intent);

                    } else if (finalI == 3) // Untuk About Button pada CardView
                    {

                    } else if (finalI == 4) // Untuk share button pada CardView
                    {
                        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                        sharingIntent.setType("text/plain");
                        String shareBodyText = "Check it out. Your message goes here";
                        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT,"Subject here");
                        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBodyText);
                        startActivity(Intent.createChooser(sharingIntent, "Sharing Option"));

                    } else if (finalI == 5)
                    {
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=com.tutorial.kamusmikrotik"));
                        startActivity(intent);
                    }

                }
            });
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the BelajarMikrotik/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        int id = item.getItemId();
        // switch (item.getItemId()) ;

        //noinspection SimplifiableIfStatement

        // Kodingan untuk Menu Item
        if (id == R.id.action_exit) {
            new AlertDialog.Builder(this)
                    .setMessage("Apakah anda ingin keluar?")
                    .setCancelable(false)
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            home.this.finish();
                        }
                    })
                    .setNegativeButton("No", null)
                    .show();
        }

        // Tombol About
        else if (id == R.id.action_about) {
            new AlertDialog.Builder(this)
                    .setMessage("PANDUAN MIKROTIK")
                    .setCancelable(false)
                    .setPositiveButton("OK", null)
                    .show();
        }
        return true;
    }

    public void shareText(View view) {
        Intent intent = new Intent(android.content.Intent.ACTION_SEND);
        intent.setType("text/plain");
        String shareBodyText = "Your shearing message goes here";
        intent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject/Title");
        intent.putExtra(android.content.Intent.EXTRA_TEXT, shareBodyText);
        startActivity(Intent.createChooser(intent, "Choose sharing method"));
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.activity_belajar_mikrotik) {
            Intent intent = new Intent(home.this, belajarmikrotik.class);
            startActivity(intent);


        } else if (id == R.id.activity_istilah_mikrotik) {
            Intent intent = new Intent(home.this, istilahmikrotik.class);
            startActivity(intent);

        } else if (id == R.id.activity_video_mikrotik) {
            Intent intent = new Intent(home.this, videomikrotik.class);
            startActivity(intent);

        } else if (id == R.id.action_about) {
            new AlertDialog.Builder(this)
                    .setMessage("PANDUAN MIKROTIK")
                    .setCancelable(false)
                    .setPositiveButton("OK", null)
                    .show();
            return true;

            // Button Share
        } else if (id == R.id.btn_share) {
            Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
            sharingIntent.setType("text/plain");
            String shareBodyText = "Check it out. Your message goes here";
            sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT,"Subject here");
            sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBodyText);
            startActivity(Intent.createChooser(sharingIntent, "Sharing Option"));
            return true;

        } else if (id == R.id.btn_rate) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
