package com.example.triageapplication;


import java.io.File;
import java.io.IOException;
import triageApp.Nurse;
import triageApp.Patient;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;


/**
 * PatientSelect Screen
 * @author group_0304
 */

public class PatientSelect extends Activity {

	private Nurse nurse;
	public static String HealthCardNum;
	public static File folder = new File("/data/data/com.example.triageapplication/files/");
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_patient_select);
		TextView display = (TextView)findViewById(R.id.patientInfo);
		display.setMovementMethod(new ScrollingMovementMethod());
		System.out.print("10000000000000000000000000");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu;s this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_patient_select, menu);
		return true;
	}
	
	/**
	 * Finds the patient file in the directory and saves it
	 * @param view
	 * @throws IOException
	 */
	public void findPatient(View view) throws IOException{
		EditText HCNInput = (EditText)findViewById(R.id.HCNentry);
		String HCNCheck = HCNInput.getText().toString();
		//Make sure there is some kind of input
		if (!(HCNCheck.equals(""))){
			HealthCardNum = HCNCheck;
			nurse = new Nurse(this.getApplicationContext().getFilesDir(), HCNCheck + ".txt", true);
			/*FileOutputStream outputStream;
			try {
			outputStream = openFileOutput(HCNCheck + ".txt", Context.MODE_PRIVATE);
			//nurse.saveToFile(outputStream);
			outputStream.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} */
			TextView userInput = (TextView)findViewById(R.id.patientInfo);
			userInput.setText(nurse.toString());
			//Receive the Patient data from wherever using the HCN
			//userInput.setText(PatientList.get(HCNCheck));
			HCNInput.setText("");
		}
	}
	
	
	
	/**
	 * Displays a list patients (who have not seen by a doctor) by urgency
	 * @param view
	 */
	public void listPatients(View view) throws IOException {
		String r = "";
		String line;
		List<String> v1 = new ArrayList<String>();
		List<String> v2 = new ArrayList<String>();
		List<String> v3 = new ArrayList<String>();
		TextView display = (TextView)findViewById(R.id.patientInfo);
    	File[] listOfFiles = folder.listFiles();
    	System.out.print("1");
    	for (File file : listOfFiles) {
    	    if (file.isFile()){
    			String results = "";
    	    	BufferedReader br = new BufferedReader(new FileReader(file));
    	        while ((line = br.readLine()) != null){
    	        	results += line + "\n";
    	        }
    	        String[] v = results.split("\n");
    	        if (v[4].length() == 0){
    	        	nurse = new Nurse(this.getApplicationContext().getFilesDir(), v[2] + ".txt");
    	            Patient p = nurse.getPatient(v[2]);
    	            if (p.categorize() == "Urgent"){
    	            	v1.add(v[2]);
    	            }
    	            if (p.categorize() == "Less"){
    	            	v2.add(v[2]);
    	            }
    	            if (p.categorize() == "Non"){
    	            	v3.add(v[2]);
    	            } 
    	        }
    	        br.close();
    	    }
    	}
		
    	//DISPLAY THE LIST SOMEWHERE HERE
		for (String hcn:v1){
			nurse = new Nurse(this.getApplicationContext().getFilesDir(), hcn + ".txt");
			r += "========= Most Urgent ========= \n";
			r += nurse.toString();
		}
		for (String hcn:v2){
			nurse = new Nurse(this.getApplicationContext().getFilesDir(), hcn + ".txt");
			r += "========= Less Urgent ========= \n";
			r += nurse.toString();
		}
		for (String hcn:v3){
			nurse = new Nurse(this.getApplicationContext().getFilesDir(), hcn + ".txt");
			r += "========= Not Urgent ========= \n";
			r += nurse.toString();
		}
		display.setText(r);
	}
	
	
	
	/**
	 * Moves to  new record activity -> NewRecord.Only moves if the Healthcard
	 * number is not an empty string.
	 * @param view
	 */
	public void toNewRecord(View view) {
		EditText HCNInput = (EditText)findViewById(R.id.HCNentry);
		String HCNCheck = HCNInput.getText().toString();
		if (!(HCNCheck.equals(""))){
			Intent intent = new Intent(this, NewRecord.class);
			startActivity(intent);
		}
		
	}
	
	public void toLoginMenu (MenuItem item){
		Intent intent = new Intent(this, LoginMenu.class);
		startActivity(intent);
	}

}
