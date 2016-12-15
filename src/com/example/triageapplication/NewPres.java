package com.example.triageapplication;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import triageApp.Physician;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class NewPres extends Activity {

	private EditText medInput;
	private EditText instructionsInput;
	public static String HealthCardNum;
	private Physician physician;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_pres);

		medInput = (EditText) findViewById(R.id.medInput);
		instructionsInput = (EditText) findViewById(R.id.instructionsInput);

		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_new_pres, menu);
		return true;
	}
	
	/**
	 * Adds the medication and the instruction for the medication to the patients
	 * main menu activity
	 * @param view
	 * @throws IOException
	 */
	public void submitPrescription(View view) throws IOException {
	
			String Meds = medInput.getText().toString();
			String Inst = instructionsInput.getText().toString();

			
			physician = new Physician(this.getApplicationContext().getFilesDir(), PhysicianView.HealthCardNum + ".txt");
			physician.recordPrescription(PhysicianView.HealthCardNum, Meds, Inst);
			
			FileOutputStream outputStream;
			try {
				outputStream = openFileOutput(PhysicianView.HealthCardNum + ".txt", Context.MODE_PRIVATE);
				physician.nurse.saveToFile(outputStream);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// Release from the existing UI and go back to the previous UI
			
			Intent intent = new Intent(this, PhysicianView.class);
		    startActivity(intent);
		    
		}

	
}
