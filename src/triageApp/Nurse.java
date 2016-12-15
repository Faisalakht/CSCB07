package triageApp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

/**
 * Adds and saves patients into and sets their vitals by creating new reports
 * @author group_0304
 */
 public class Nurse {
     
     private PatientList pl = new PatientList();
     
     
     /**
      * The constructor checks if the file is already created.If so its gets
      * the file path of the file
      * @param file directory
      * @param String filename
     * @throws IOException 
      */
     public Nurse(File dir, String fileName, Boolean dummy) throws IOException{
         //call logManager to check if input is valid
    	 File file = new File(dir, fileName);
         if (file.exists()) {
        	 readFile(file.getPath());
         }
     }
     
     /**
      * Override the original constructor when creating a nurse is creating a patient
      * file if it doesn't already exist
      * @param dir
      * @param fileName
      * @throws IOException
      */
     public Nurse(File dir, String fileName) throws IOException{
         //call logManager to check if input is valid
    	 File file = new File(dir, fileName);
         if (file.exists()) {
        	 readFile(file.getPath());
         } else {
             file.createNewFile();
         }
     }
     
     /**
      * Adding patient to the PatientList
      * @param name patient's name 
      * @param birthdate patient's birthdate 
      * @param HCN patient's Healthcard Number 
      */
     public void addPatient(String name, String dob, String HCN){
         Patient patient = new Patient(name, dob, HCN);
         pl.addPatient(patient);
     }
     
     /**
      * Adding patient to the PatientList with an additional parameter of theit
      * arrival time.
      * @param name patient's name 
      * @param birthdate patient's birthdate 
      * @param HCN patient's Healthcard Number 
      */
     public void addPatient(String name, String dob, String HCN, String time){
         Patient patient = new Patient(name, dob, HCN, time);
         pl.addPatient(patient);
     }
     /**
     /**
      * Override for adding patient to the list.When the patient visits the physician it will
      * automatically be seen by a doctor.
      * @param name patient's name 
      * @param birthdate patient's birthdate 
      * @param HCN patient's Healthcard Number 
      * @param time
      * @param medic
      * @param instru
      * @param doctor
      */
     public void addPatient(String name, String dob, String HCN, String time, String medic, String instru, String doctor){
         Patient patient = new Patient(name, dob, HCN, time, medic, instru);
         if (!patient.getSeenByDoctor()) {
         	patient.setSeenByDoctorTime(doctor);
         }
         pl.addPatient(patient);
     }
     
     
     /**
      * Gets the patient by their Health Card Number
      * @param HCN
      * @return Patient
      */
     public Patient getPatient(String HCN) {
    	 return pl.getPatient(HCN);
     }
     
     /**
      * Gets the patient object and prints it
      * load patient's information from PatientList
      * @param HCN patient's HealthCard Number
     * @throws IOException 
     * @return String
      */
     public String loadPatient(String getPath, String HCN) throws IOException{
         readFile(getPath);
         return pl.getPatient(HCN).toString();
     }
     /**
      * Prints the Patientlist HashMap
      * @return String
      */
     public String toString() {
    	 return pl.toString();
     }
     
     /**
      * Update Patient's vital signs
      * @param temp patient's temperature
      * @param bloodPressure patient's blood pressure
      * @param heartRate patient's hearth rate
      */
     public void newReport(String HCN, String temp, String bloodPressure, String heartRate) {
         pl.getPatient(HCN).newReport(temp, bloodPressure, heartRate);
     }
     
     /**
      * Updates the Patients's vital
      * @param HCN
      * @param temp
      * @param bloodPressure
      * @param heartRate
      * @param time
      */
     public void newReport(String HCN, String temp, String bloodPressure, String heartRate, String time) {
         pl.getPatient(HCN).newReport(temp, bloodPressure, heartRate, time);
     }
     
     /**
      * Saves the patient list into a text file by their healthcardnumber
      * @param outputStream
      * @throws IOException
      */
     public void saveToFile(FileOutputStream outputStream) throws IOException {
         try {
         	// writes each patient info one per line into outputStream
        	 for (Patient p : pl.getPatientList()) {
        		 outputStream.write(p.toString().getBytes());
        	 }
         } catch (IOException e) {
             e.printStackTrace();
         }
         
     }
     
     /**
      * Reads the file for the patient and creates the patient object and adds
      * the vitals back into the patient object
      * @param filePath
      * @throws FileNotFoundException
      */
     public void readFile (String filePath) throws FileNotFoundException {
    	 
    	Scanner scanner = new Scanner(new FileInputStream(filePath));
    	String line = "";
        String [] record;
         
        while(scanner.hasNextLine()) {
        	 line += scanner.nextLine() + "\n";
        }
        scanner.close();
        record = line.split("\n");
        String name = record[0];
        String dob = record[1];
        String HCN = record[2];
        String time = record[3];
        String doctor = record[4];
        String medic = record[5];
        String instr = record [6];
        addPatient(name, dob, HCN, time, medic, instr, doctor);
        for (int i=7; i<record.length; i=i+4) {
        	String reportTime = record[i];
  	      	String temp = record[1+i];
  	      	String pressure = record[2+i];
  	      	String rate = record[3+i];
  	      	newReport(HCN, temp, pressure, rate, reportTime);
        }
     }
     
     
 }
