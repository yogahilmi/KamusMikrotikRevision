package com.tutorial.kamusmikrotik;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.content.Intent;
import android.view.View;
import android.support.v7.widget.GridLayout;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import android.net.Uri;

public class Home extends AppCompatActivity {

    GridLayout mainGrid;
    AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        mainGrid = (GridLayout) findViewById(R.id.mainGrid);

        // set Event
        // setToggleEvent(mainGrid);
        setSingleEvent(mainGrid);

        // Sample AdMob app ID: ca-app-pub-3940256099942544~3347511713
        MobileAds.initialize(this, "ca-app-pub-2684730854768563~1230187668");
        // MobileAds.initialize(this, "ca-app-pub-3940256099942544~3347511713");

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

    }

//    /** Called when leaving the activity */
//    @Override
//    public void onPause() {
//        if (mAdView != null) {
//            mAdView.pause();
//        }
//        super.onPause();
//    }
//
//    /** Called when returning to the activity */
//    @Override
//    public void onResume() {
//        super.onResume();
//        if (mAdView != null) {
//            mAdView.resume();
//        }
//    }
//
//    /** Called before the activity is destroyed */
//    @Override
//    public void onDestroy() {
//        if (mAdView != null) {
//            mAdView.destroy();
//        }
//        super.onDestroy();
//    }


    private void setSingleEvent(GridLayout mainGrid) {
        for (int i = 0; i < mainGrid.getChildCount(); i++) {
            CardView cardView = (CardView) mainGrid.getChildAt(i);
            final int finalI = i;
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (finalI == 0) // Untuk open Activity
                    {
                        Intent intent = new Intent(Home.this, BelajarMikrotik.class);
                        startActivity(intent);
                    } else if (finalI == 1) // Untuk open Activity
                    {
                        Intent intent = new Intent(Home.this, IstilahMikrotik.class);
                        startActivity(intent);
                    } else if (finalI == 2) // Untuk open Activity
                    {
                        Intent intent = new Intent(Home.this, VideoMikrotik.class);
                        startActivity(intent);

                    } else if (finalI == 3) // Untuk About Button pada CardView
                    {
                        Intent intent= new Intent(Home.this, About.class);
                        startActivity(intent);

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
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setMessage("Apakah anda ingin keluar?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Home.this.finish();
                    }
                })
                .setNegativeButton("No", null)
                .show();
    }
}
