package triageApp;

import java.io.File;
import java.io.IOException;

/**
 * Physician class which prescribes prescription to the patients.
 * @author group_0304
 */
public class Physician {
	
	public Nurse nurse; //Nurse objected needed to work the physician class
	
	/**
	 * Constructor for Physician Class
	 */
	public Physician(File dir, String fileName) throws IOException {
		this.nurse = new Nurse(dir, fileName);
    }
	
	/**
	 * Sets the prescription of the the patient with the medication and instruction
	 * in the patient record also saving the time when the patient has been seen
	 * by a doctor
	 * @param HCN
	 * @param medic
	 * @param instru
	 */
	public void recordPrescription(String HCN, String medic, String instru) {
		nurse.getPatient(HCN).setMedic(medic);
		nurse.getPatient(HCN).setInstruction(instru);
		nurse.getPatient(HCN).setSeenByDoctor();
	}

}
