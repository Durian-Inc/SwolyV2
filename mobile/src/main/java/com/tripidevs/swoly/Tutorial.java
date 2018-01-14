package com.tripidevs.swoly;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;

import com.hololo.tutorial.library.Step;
import com.hololo.tutorial.library.TutorialActivity;


public class Tutorial extends TutorialActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Start of maxes
        addFragment(new Step.Builder().setTitle("Creating A New Max")
                .setBackgroundColor(Color.parseColor("#FF4081")) // int background color
                .setDrawable(R.drawable.new_entry) // int top drawable
                .setSummary("To create a new entry in your maxes, you will want to click the add button that the bottom of the page. " +
                        "From there, a dialog will appear to enter a new max. ")
                .setSummary("Continue to learn more...")
                .build());

        //Entry
        addFragment(new Step.Builder().setTitle("A New Max Entry")
                .setContent("Enter the name of the exercise and the weight to add it to your lists.")
                .setBackgroundColor(Color.parseColor("#E53935")) // int background color
                .setDrawable(R.drawable.entry) // int top drawable
                .setSummary("Continue to learn more...")
                .build());

        //Full entry
        addFragment(new Step.Builder().setTitle("Congrats On Your New Max")
                .setContent("When a new max is created. A new card is added to your home screen. This card includes: The title of the lift," +
                        " the max weight, and the percentages that can be calculated.")
                .setBackgroundColor(Color.parseColor("#E53935")) // int background color
                .setDrawable(R.drawable.squat) // int top drawable
                .setSummary("You''re now ready to use Swoly!")
                .build());
    }

    @Override
    public void finishTutorial() {
        SharedPreferences.Editor sharedPreferencesEditor =
                PreferenceManager.getDefaultSharedPreferences(this.getBaseContext()).edit();
        sharedPreferencesEditor.putBoolean("finishedTutorial", true);
        sharedPreferencesEditor.apply();
        finish();
    }

}
