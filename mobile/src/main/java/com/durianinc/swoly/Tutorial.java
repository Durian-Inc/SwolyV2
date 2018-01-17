package com.durianinc.swoly;

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
        addFragment(new Step.Builder().setTitle("Swoly")
                .setBackgroundColor(Color.parseColor("#d33682")) // int background color
                .setDrawable(R.drawable.swoly_icon) // int top drawable
                .setContent("Welcome to Swoly!\n" +
                        "A companion app to help you work out more simply!\n" +
                        "Swoly is a fitness app that stores maxes for the gym and lets you calculate workouts based on those maxes.")
                .setSummary("Continue...")
                .build());

        //Entry
        addFragment(new Step.Builder().setTitle("Maxes")
                .setBackgroundColor(Color.parseColor("#21222a")) // int background color
                .setDrawable(R.drawable.maxes_card) // int top drawable
                .setContent("In weight lifting, a max is the maximum amount of weight you can do " +
                        "for one repitition of an exercise. Swoly stores and tracks your maxes so" +
                        " you can change them as you improve!\nTo add a max, press the plus in" +
                        " the bottom right.")
                .setSummary("Continue...")
                .build());

        //Full entry
        addFragment(new Step.Builder().setTitle("Percentages")
                .setBackgroundColor(Color.parseColor("#21222a")) // int background color
                .setDrawable(R.drawable.maxes_buttons) // int top drawable
                .setContent("With your maxes in the system, you are able to quickly calculate the" +
                        " proper amount of weight to add onto each side of the bar based on a " +
                        "percentage.\nThe pencil icon will let you choose a custom percentage.")
                .setSummary("You're now ready to use Swoly!")
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
