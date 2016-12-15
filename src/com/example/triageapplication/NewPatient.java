package com.example.triageapplication;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import triageApp.Nurse;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

/**
 * New Patient Screen
 * @author group_0304
 */


public class NewPatient extends Activity { // implements OnClickListener {

	private EditText PatientnameInput;
	private EditText PatientbirthInput;
	private EditText PatientidInput;
	private Button saveButton;
	private Nurse nurse;

	//private ArrayList> PatientArrayList;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_patient);

		PatientnameInput = (EditText) findViewById(R.id.nameInput);
		PatientbirthInput = (EditText) findViewById(R.id.birthInput);
		PatientidInput = (EditText) findViewById(R.id.idInput);
		
		//saveButton = (Button) findViewById(R.id.submitPatientButton);
		//saveButton.setOnClickListener(this);

		//PatientArrayList = new ArrayList<Patient>();
	}
	
	/**
	 * Adds the patient into patient list and saves the info into their file which is located in the 
	 * app directory
	 */
	public void submitPatient (View v){
		
	//@Override
	//public void onClick(View v) {
		//if(v.getId() == R.id.submitPatientButton){
			String PatientName = PatientnameInput.getText().toString();
			String PatientDOB = PatientbirthInput.getText().toString();
			String PatientHealthCardNum = PatientidInput.getText().toString();

			try {
				nurse = new Nurse(this.getApplicationContext().getFilesDir(), PatientHealthCardNum + ".txt");
				nurse.addPatient(PatientName, PatientDOB, PatientHealthCardNum);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			FileOutputStream outputStream;
			try {
				outputStream = openFileOutput(PatientHealthCardNum + ".txt", Context.MODE_PRIVATE);
				try {
					nurse.saveToFile(outputStream);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//
			PatientSelect.HealthCardNum = PatientHealthCardNum;
			Intent intent = new Intent(this, NewRecord.class);
			startActivity(intent);
			
			finish();
		//}
	}
	
}
