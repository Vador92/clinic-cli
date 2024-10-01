/**
 * This class is to hold the unique patients with total charges.
 * Deletes user appointments from clinic once transferred to medical record.
 * @author Varun Doreswamy
 */
public class MedicalRecord {
    final static int EMPTY = 0;
    final static int SOURCE = 0;
    final static int NOT_FOUND = -1;
    final static int INCREMENT = 1;
    final static int PRINT_SHIFT = 1;
    final static int START_VALUE = 1;

    // instance variables
    private Patient[] patients;
    private int size;

    /**
     * This is the constructor class for creating a new medical record.
     */
    public MedicalRecord() {
        this.patients = new Patient[START_VALUE];
        this.size = EMPTY;
    }

    /**
     * Sets the next appointment for a Patient, if the patient exists.
     * @param medicalRecord Uses the medical record to set next appointment.
     * @param index Is the index used for adding to that patient.
     * @param newAppointment Is the next appointment for that patient.
     */
    private void nextAppointment(MedicalRecord medicalRecord,
                                 int index,
                                 Appointment newAppointment){
        Patient exisingPatient = medicalRecord.getRecord(index);
        exisingPatient.addAppointment(newAppointment);
        exisingPatient.getCharge();
    }

    /**
     * This method grows the appointment list, saving space by only adding 1.
     */
    private void grow(){
        Patient[] expandedPatient = new Patient[patients.length + INCREMENT];
        // copy contents from original array to new array
        for (int i = SOURCE; i < patients.length; i++) {
            expandedPatient[i] = patients[i];
        }
        // set appointments to extended array
        patients = expandedPatient;
    }

    /**
     * This method finds an existing patient and returns index, if found.
     * @param newPatient Is the patient we are trying to find.
     * @return int of the index of the patient array.
     */
    private int findPatient(Patient newPatient){
        for(int i = 0; i < size; i++){
            if(patients[i].equals(newPatient)){
                return i;
            }
        }
        return NOT_FOUND;
    }

    /**
     * This method adds unique clients, with charges, to the medical records.
     * @param clinic The list of all the appointments
     */
    public void add(List clinic){
        if (clinic.getSize() == EMPTY){
            return;
        }

        clinic.bubbleSortPatient(clinic.getAppointments());

        for (int i = SOURCE; i < clinic.getSize(); i++) {
            Patient newPatient = new Patient(clinic.get(i)
                    .getPatientProfile(), null);
            int patientIndex = findPatient(newPatient);
            if (patientIndex == NOT_FOUND){
                if(this.getSize() == patients.length){
                    grow();
                }
                patients[this.size] = newPatient;
                patients[this.size].addAppointment(clinic.get(i));
                patients[this.size].getCharge();
                this.size++;
            }
            else{
                this.nextAppointment(this, patientIndex,
                        clinic.get(i));
            }
        }
        while (clinic.getSize() != EMPTY){
            clinic.remove(clinic.get(SOURCE));
        }
    }

    /**
     * Getter method for getting the size of the medicalrecord.
     * @return int for the number of elements in the patients array.
     */
    public int getSize(){
        return size;
    }

    /**
     * This gets the patient at a certain index.
     * @param index is the index used for finding the patient.
     * @return Patient object at that index.
     */
    public Patient getRecord(int index){
        return patients[index];
    }

    /**
     * Method to return a string of the all the patients and total charges.
     * @return String of formatted billing record.
     */
    @Override
    public String toString() {
        int size = this.getSize();
        String billingRecords= "";
        for (int i = SOURCE; i < size; i++) {
            billingRecords += String.format("(%d) ", i+PRINT_SHIFT) +
                    this.getRecord(i).toString() + "\n";
        }
        return billingRecords;
    }
}