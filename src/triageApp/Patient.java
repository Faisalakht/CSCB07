package triageApp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
//import java.util.si

/**
 * Is an entity that has  attributes associated with it 
 * @author group_0304
 */

public class Patient {
	
	private String name;
	private String DOB;
	private String healthCard;
	private String arrival;
	private String temp;
	private String heartRate;
	private String bloodPressure;
	private String bloodPressure2;
	private String medic;
	private String instruction;
	private Map<String, ArrayList<String>> report;
	private String seenbydoctortime = "";
	
	/**
	 * Constructor of the Patient class
	 * @param name
	 * @param DOB
	 * @param healthCard
	 */
	
	public Patient(String name, String DOB, String healthCard) {
		this.name = name;
		this.DOB = DOB;
		this.healthCard = healthCard;
		Date d = new Date();
		this.arrival = d.toString();
		this.report = new HashMap<String, ArrayList<String>>();
	}
	
	/**
	 * Constructor for the patient class includes time as a parameter
	 * @param name
	 * @param DOB
	 * @param healthCard
	 * @param time
	 */
	public Patient(String name, String DOB, String healthCard, String time) {
		this.name = name;
		this.DOB = DOB;
		this.healthCard = healthCard;
		this.arrival = time;
		this.report = new HashMap<String, ArrayList<String>>();
	}
	/**
	 * Override the constructor for the patient class.For patients who have a prescription
	 * associated with them.Has medic and instruction as an additional parameter.
	 * @param name
	 * @param DOB
	 * @param healthCard
	 * @param time
	 * @param medic
	 * @param instru
	 */
	public Patient(String name, String DOB, String healthCard, String time, String medic, String instru) {
		this.name = name;
		this.DOB = DOB;
		this.healthCard = healthCard;
		this.arrival = time;
		this.medic = medic;
		this.instruction = instru;
		this.report = new HashMap<String, ArrayList<String>>();
	}
	
	
	/**
	 * Gets name
	 * @return String
	 */
	public String getName() {
		return name;
	}
	/**get Date of Birth
	 * 
	 * @return String
	 */
	public String getDOB() {
		return DOB;
	}
	
	/**
	 * Gets the health card number
	 * @return String
	 */
	public String getHealthCardNum() {
		return healthCard;
	}
	
	/**
	 * Gets Arrival time
	 * @return String
	 */
	public String getArrivalTime() {
		return arrival;
	}
	
	/**
	 * Sets the temperature 
	 * @param temp
	 */
	public void setTemp(String temp) {
		this.temp = temp;
	}
	
	/**
	 * Gets the temperature
	 * @return String
	 */
	public String getTemp() {
		return temp;
	}
	
	/**
	 * Sets the heart rate
	 * @param heartRate
	 */
	public void setHeartRate(String heartRate) {
		this.heartRate = heartRate;
	}
	
	/**
	 * Gets the heart rate
	 * @return String
	 */
	public String getHeartRate() {
		return heartRate;
	}
	
	/**
	 * Sets the blood Pressure
	 * @param bloodPressure (systolic)
	 */
	public void setBloodPressure(String bloodPressure) {
		this.bloodPressure = bloodPressure;
	}
	
	/**
	 * Sets the blood Pressure
	 * @param bloodPressure (dystolic)
	 */
	public void setBloodPressure2(String bloodPressure2) {
		this.bloodPressure2 = bloodPressure2;
	}
	
	/**
	 * Gets the blood pressure systolic
	 * @return String
	 */
	public String getBloodPressure() {
		return bloodPressure;
	}
	
	/**
	 * Gets the blood pressure dystolic
	 * @return String
	 */
	public String getBloodPressure2() {
		return bloodPressure2;
	}
	
	/**
	 * Sets the medication for the patient
	 * @param medic
	 */
	public void setMedic(String medic) {
		this.medic = medic;
	}
	/**
	 * Gets the medication for the patient
	 * @return String
	 */
	public String getMedic() {
		return medic;
	}
	/**
	 * Sets the instruction for the medication
	 * @param instru
	 */
	public void setInstruction(String instru) {
		this.instruction = instru;		
	}
	/**
	 * Gets the instruction for the medication.
	 * @return String
	 */
	public String getInstruction() {
		return instruction;
	}
	/**
	 * Gets the age for the patient
	 * @return Int
	 */
	public int getAge() {
		int DOB = Integer.parseInt(getDOB().substring(0, 4));
		final Calendar c = Calendar.getInstance();
		int year = c.get(Calendar.YEAR);
		int age = year - DOB;
		return age;
	}
	/**
	 * Looks are the vitals and returns a number which then will be used to asses
	 * the urgency level for the patient.
	 * @return Int
	 */
	public int policy() {
		int result = 0;
		if (getAge() < 2) {
			result += 1;
		}
		//String priceFormattednew = String.format("%.2f", price);
		if (Float.parseFloat(getTemp()) >= 39.0) {
			result += 1;
		}
		if (Integer.parseInt(getBloodPressure()) >= 140) {
			result += 1;
		}
		if ((Integer.parseInt(getHeartRate()) >= 100) || (Integer.parseInt(getHeartRate()) <= 50)) {
			result += 1;
		}
		return result;
	}
	/**
	 * Sets the patient into category of Urgent,Less and Non depending on the
	 * policy number.
	 * @return String
	 */
	public String categorize() {
		String category = null;
		if (policy() == 3 || policy() == 4) {
			category = "Urgent";
		}
		if (policy() == 2) {
			category = "Less";
		}
		if (policy() == 0 || policy() == 1) {
			category = "Non";
		}
		return category;
	}
	
	/**
	 * Makes a new report for the patient and adds it to the Map 
	 * @param temp
	 * @param bloodPressure
	 * @param heartRate
	 */
	public void newReport(String temp, String bloodPressure, String heartRate) {
		ArrayList<String> vital = new ArrayList<String>();
		setTemp(temp);
		setBloodPressure(bloodPressure);
		setHeartRate(heartRate);
		vital.add(getTemp());
		vital.add(getBloodPressure());
		vital.add(getHeartRate());
		Date date = new Date();
		report.put(date.toString(), vital);
	}
	
	/**
	 * Makes a new report for the patient and adds it to the Map which includes time
	 * @param temp
	 * @param bloodPressure
	 * @param heartRate
	 * @param time
	 */
	public void newReport(String temp, String bloodPressure, String heartRate, String time) {
		ArrayList<String> vital = new ArrayList<String>();
		setTemp(temp);
		setBloodPressure(bloodPressure);
		setHeartRate(heartRate);
		vital.add(getTemp());
		vital.add(getBloodPressure());
		vital.add(getHeartRate());
		report.put(time, vital);
	}
	
	/**
	 * Gets the list of vitals
	 * @param date
	 * @return List
	 */
	public List<String> getReport(String date) {
		return report.get(date);
	}
	
	/**
	 * prints all the attributes of the patient class including the Map which containts
	 * all the reports.This is used for displaying.
	 * @return String
	 */
	public String displayPatient() {
		String result = "";
		result += "Name: " + getName() + "\n";
		result += "Date of Birth: " + getDOB() + "\n";
		result += "Health Card Number: " + getHealthCardNum() + "\n";
		result += "Arrival Time: " + getArrivalTime() + "\n";
		result += "Seen By Doctor at: " + getSeenByDoctorTime() + "\n";
		result += "Medication: " + getMedic() + "\n";
		result += "Instruction: " + getInstruction() + "\n";
		result += "\n";
		Object[] key = report.keySet().toArray();
		Collection<ArrayList<String>> value = report.values();
		Iterator<ArrayList<String>> iterator = value.iterator();
		for (int x=0; x<report.size(); x++) {
			result += "Report" + (x+1) + ": \n";
			result += Arrays.asList(key).get(x);
			result += "\n";
			if (iterator.hasNext()) {
				ArrayList<String> temp = iterator.next();
				result += "Temperature: " + temp.get(0) + "\n";
				result += "Blood Pressure: " + temp.get(1) + "\n";
				result += "Heart Rate: " + temp.get(2) + "\n";
				result += "\n";
			}
		}
		return result;
	}
	
	/**
	 * Default print method for Patient.Used for storing information in the file for
	 * each patient.
	 * @return String
	 */
	public String toString() {
		String result = "";
		result += getName() + "\n";
		result += getDOB() + "\n";
		result += getHealthCardNum() + "\n";
		result += getArrivalTime() + "\n";
		result += getSeenByDoctorTime() + "\n";
		result += getMedic() + "\n";
		result += getInstruction() + "\n";
		Object[] key = report.keySet().toArray();
		Collection<ArrayList<String>> value = report.values();
		Iterator<ArrayList<String>> iterator = value.iterator();
		for (int x=0; x<report.size(); x++) {
			//result += "Report" + (x+1) + ": \n";
			result += Arrays.asList(key).get(x) + "\n";
			if (iterator.hasNext()) {
				ArrayList<String> temp = iterator.next();
				result += temp.get(0) + "\n";
				result += temp.get(1) + "\n";
				result += temp.get(2) + "\n";
				//result += "\n";
			}
		}
		return result;
	}
	
	/**
	 * Sets the time when the patient is seen by the physician.
	 */
	public void setSeenByDoctor(){
		Date date = new Date();
		seenbydoctortime = date.toString();
	}
	/**
	 * For the givien doctor it sets the time when the patient is seen by them.
	 * @param doctor
	 */
	public void setSeenByDoctorTime(String doctor) {
		seenbydoctortime = doctor;
	}
	
	/**
	 * When was the patient last visited by a doctor. 
	 * @return String
	 */
	public String getSeenByDoctorTime() {
		return seenbydoctortime;
	}
	
	/**
	 * To see if the patient has been visited by a physician.
	 * @return boolean
	 */
	public boolean getSeenByDoctor(){
		
		if (seenbydoctortime.equals("")){
			return false;
		}
		else{
			return true;
		}
	}
	
		
	
}

