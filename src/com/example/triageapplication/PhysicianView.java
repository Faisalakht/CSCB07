package com.example.triageapplication;

import java.io.IOException;
import triageApp.Nurse;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


/**
 * New Physician Screen
 * @author group_0304
 */

public class PhysicianView extends Activity {
	
	private Nurse nurse;
	public static String HealthCardNum;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_physician_view);
		TextView display = (TextView)findViewById(R.id.patientInfo);
		display.setMovementMethod(new ScrollingMovementMethod());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_physician_view, menu);
		return true;
	}
	
	/**
	 * Finds the patient in the app directory by their healthcard number
	 * @param view
	 * @throws IOException
	 */
	public void findPatient(View view) throws IOException{
		EditText HCNInput = (EditText)findViewById(R.id.HCNentry);
		String HCNCheck = HCNInput.getText().toString();
		HealthCardNum = HCNCheck;
		nurse = new Nurse(this.getApplicationContext().getFilesDir(), HCNCheck + ".txt", true);
			
		TextView userInput = (TextView)findViewById(R.id.patientInfo);
		//Use this line if you want to see the cool scrollbar!
		userInput.setText(nurse.toString());
		//Receive the Patient data from wherever using the HCN
		//userInput.setText(PatientList.get(HCNCheck));
		HCNInput.setText("");
	}
	
	/**
	 * Moves to  new prescription activity -> NewPres
	 * @param view
	 */
	public void toNewPres(View view) {		
		EditText HCNInput = (EditText)findViewById(R.id.HCNentry);
		String HCNCheck = HCNInput.getText().toString();
		if (!(HCNCheck.equals(""))){
			Intent intent = new Intent(this, NewPres.class);
			startActivity(intent);
		}
	}


}
