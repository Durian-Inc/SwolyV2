package com.tripidevs.swoly;

import android.app.Activity;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;

/**
 * Created by griffin melson on 1/10/2017.
 */

public class SettingsActivity extends PreferenceActivity{
  @Override
  protected void onCreate(Bundle savedInstanceState){
    super.onCreate(savedInstanceState);
    getFragmentManager().beginTransaction().replace(android.R.id.content, new MyPreferenceFragment()).commit();

  }

  public static class MyPreferenceFragment extends PreferenceFragment{
    @Override
    public void onCreate(final Bundle savedInstanceState){
      super.onCreate(savedInstanceState);
      addPreferencesFromResource(R.xml.settings);
    }
  }
}
