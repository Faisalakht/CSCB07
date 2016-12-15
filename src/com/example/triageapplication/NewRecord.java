package com.example.triageapplication;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import triageApp.Nurse;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

/**
 * New record screen
 * @author group_0304
 */


public class NewRecord extends Activity {

	private EditText TempInput;
	private EditText HeartRateInput;
	private EditText BloodPressureInput;

	private Nurse nurse;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_record);

		TempInput = (EditText) findViewById(R.id.tempInput);
		HeartRateInput = (EditText) findViewById(R.id.heartInput);
		BloodPressureInput = (EditText) findViewById(R.id.bloodInput);

		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_new_record, menu);
		return true;
	}
	
	/**
	 * Adds vitals to an existing patient's record and updates their file and moves
	 * main menu activity
	 * @param view
	 * @throws IOException
	 */
	public void submitRecord(View view) throws IOException {
			String Temp = TempInput.getText().toString();
			String Heart = HeartRateInput.getText().toString();
			String Blood = BloodPressureInput.getText().toString();

			nurse = new Nurse(this.getApplicationContext().getFilesDir(), PatientSelect.HealthCardNum + ".txt");
			nurse.newReport(PatientSelect.HealthCardNum, Temp, Blood, Heart);
			
			FileOutputStream outputStream;
			try {
				outputStream = openFileOutput(PatientSelect.HealthCardNum + ".txt", Context.MODE_PRIVATE); //Adds the patient information to the patients file
				nurse.saveToFile(outputStream);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			Intent intent = new Intent(this, MainMenu.class);
		    startActivity(intent);
		    this.finish();
		}
	

	
}
