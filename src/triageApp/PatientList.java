package triageApp;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.LinkedHashMap;

/**
 * Manages the list of patients that are saved and currently being used
 * @author group_0304
 */

public class PatientList{

    protected Map<String, Patient> record;
    
    /**
     * Constructor for the PatientList class.
     */
	public PatientList(){
            this.record = new HashMap<String, Patient>();
	}
	/**
	 * Adds patient to the record map health card being the key and patient as the value
	 * @param patient
	 */
    public void addPatient(Patient patient){
        this.record.put(patient.getHealthCardNum(), patient);
    }

    /**
     * Gets the patient form the Map
     * @param healthCard
     * @return Patient
     */
    public Patient getPatient(String healthCard){
        return this.record.get(healthCard);
    }
    
    /**
     * Gets the list of patients from the Map
     * @return List
     */
    public List<Patient> getPatientList(){
        List<Patient> PatientList = new ArrayList<Patient>();
        for (Patient p:this.record.values()){
             PatientList.add(p);
        }
        return PatientList;
    }
    
    /**
     * Sorts the Map by key values in ascending order
     * @param map
     * @return Map
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
	public static <K extends Comparable,V> Map<K,V> sortByKeys(Map<K,V> map){
        List<K> keys = new LinkedList<K>(map.keySet());
        Collections.sort(keys);
      
        //LinkedHashMap will keep the keys in the order they are inserted
        //which is currently sorted on natural ordering
        Map<K,V> sortedMap = new LinkedHashMap<K,V>();
        for(K key: keys){
            sortedMap.put(key, map.get(key));
        }
      
        return sortedMap;
    }

    /**
     * Sorts the patient by arrival time 
     * @return Map
     */
	public Map<String, Patient> sortPatientsByArrival(){
            List<Patient> PatientList = new ArrayList<Patient>();
            for (Patient p:this.record.values()){
                PatientList.add(p);
            }
            Map<String,Patient> datevalues = new HashMap<String,Patient>();
            for (Patient pat:PatientList){
                datevalues.put(pat.getArrivalTime().toString(),pat);
            }
            Map<String,Patient> sortbytime = sortByKeys(datevalues);
            return sortbytime;
            
	}

	/**
	 * Default print method of the PatientList Class
	 */
    @Override
    public String toString(){
    	String result = "";
        for (Patient p:this.record.values()){
        	result = p.displayPatient();
        }
        return result;
    }
}