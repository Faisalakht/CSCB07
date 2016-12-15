package com.example.triageapplication;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import triageApp.LoginManager;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

/**
 * Login Screen in which the physician and nurses login.
 * @author group_0304
 */


public class LoginMenu extends Activity {
	
	private LoginManager logman;

	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login_menu);
		
		try {
			logman = new LoginManager(this.getApplicationContext().getFilesDir());
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		FileOutputStream outputStream;
		try {
			outputStream = openFileOutput("passwords.txt", Context.MODE_PRIVATE);
			try {
				logman.saveLogIn(outputStream);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_login_menu, menu);
		return true;
	}
	
	
	
	/**
	 * Logging  nto the app with a username and password.Checks if the user is a physican
	 * or a nurse and sends them to their proper activity associated with them.
	 * @param view
	 * @throws IOException
	 */
	public void loginCheck(View view) throws IOException {
		
		//Try and make a list of users/pass to test
		
		//Check if userInput matches a user/pass
		
		//Finds the EditText box
		EditText userInput = (EditText)findViewById(R.id.userLogin); 
		EditText passInput = (EditText)findViewById(R.id.passLogin);
		//Receives the input
		String userCheck = userInput.getText().toString();
		String passCheck = passInput.getText().toString();
		
		Map<String, String> users = new HashMap<String, String>();
		try{
			users = logman.getUser();
			if (users.containsKey(userCheck)){
				String [] check = users.get(userCheck).split(",");
				String password = check[0];
				String usertype = check[1];
				//check usertype
				if (password.equals(passCheck)){
					if (usertype.equals("NUR")){
						Intent intent = new Intent(this, MainMenu.class); //Sends the user to the Nurse view
						startActivity(intent);						
					}
					else if (usertype.equals("PHY")){
						Intent intent = new Intent(this, PhysicianView.class); //Sends the user to the physicain menu
						startActivity(intent);
					}
				}
				
			}
		} catch (FileNotFoundException e){
			e.printStackTrace();
		}
		
	}
	

}
