package com.example.triageapplication;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

/**
 * Main menu screen (Patient Select Screen for the Nurses)
 * @author group_0304
 */
public class MainMenu extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_menu);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main_menu, menu);
		return true;
	}
	/**
	 * Moves to the PatientSelect activity.Where you can select patients by their healthcard number
	 * @param view
	 */
	public void toPatientSelect(View view) {
	    Intent intent = new Intent(this, PatientSelect.class);
	    startActivity(intent);
	}
	
	/**
	 * Moves to NewPatient activity, if the user wants to add a new patient.
	 * @param view
	 */
	public void toNewPatient(View view) {
	    Intent intent = new Intent(this, NewPatient.class);
	    startActivity(intent);
	}
	

}
