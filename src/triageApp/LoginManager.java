package triageApp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Scanner;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.HashMap;

/**
 * Manages the Username and passwords for the login screen
 * @author group_0304
 */

public class LoginManager {
	
	private Map<String, String> nurse;
	
	/**
	 * Constructor for the LoginManager class and creates the new file
	 * passwords.txt
	 * 
	 */
	public LoginManager(File dir) throws IOException {
		this.nurse = new HashMap<String, String>();
		File file = new File(dir, "passwords.txt");
		file.createNewFile();
	}
	
	/**
	 * Saves the username and password for the nurses and the physicians in the password.txt file
	 * @param outputStream
	 * @throws IOException
	 */
	public void saveLogIn(FileOutputStream outputStream) throws IOException {
		
        try {
        	outputStream.write("nurse1,123,NUR\n".getBytes());
        	outputStream.write("nurse2,456,NUR\n".getBytes());
        	outputStream.write("nurse3,789,NUR\n".getBytes());
        	outputStream.write("phy1,123,PHY\n".getBytes());
        	outputStream.write("phy2,456,PHY\n".getBytes());
        	outputStream.write("phy3,789,PHY".getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }
	/**
	 * Checks the username and password in the passwords.txt in the app directory
	 * 
	 * @return a map of <String, String> which are nurses and physicians
	 * @throws FileNotFoundException
	 */
	public Map<String, String> getUser() throws FileNotFoundException{
		File file = new File("/data/data/com.example.triageapplication/files/passwords.txt");
		Scanner scanner = new Scanner(new FileInputStream(file));
		String [] record;
		while (scanner.hasNextLine()){
			record = scanner.nextLine().split(",");
			String username = record[0];
			String password = record[1];
			String usertype = record[2];
			nurse.put(username, password+","+usertype);
		}
		scanner.close();
		return nurse;
	}

}
