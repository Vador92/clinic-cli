/**
 * @author Varun
 * @author Yuet
 */
public class MedicalRecord {
    private Patient[] patients;
    private int size; // number of patient objects in the array

    final static int EMPTY = 0;
    final static int SOURCE = 0;
    final static int INCREMENT = 1;
    // medical record is only used when the PS command is called, "completes" or clears out the appt list
    // used for adding up all the charges

    // constructor
    public MedicalRecord() {
        this.patients = new Patient[1];
        this.size = EMPTY;
    }

    public MedicalRecord add(List clinic){
        // create new medical record
        MedicalRecord medicalRecord = new MedicalRecord();
        // check if clinic is empty
        if (clinic.getSize() == EMPTY){
            return medicalRecord;
        }

        // if not empty then add the first patient
        Patient firstPatient = new Patient(clinic.get(SOURCE).getPatientProfile(), null);
        // increment size
        medicalRecord.size+=INCREMENT;
        clinic.remove(clinic.get(SOURCE));
        medicalRecord.patients[SOURCE] = firstPatient;

        // then check the rest
        for (int i = SOURCE; i < clinic.getSize(); i++) {
            Patient newPatient = new Patient(clinic.get(i).getPatientProfile(), null);

        }
        while (clinic.getSize() != EMPTY){
            clinic.remove(clinic.get(SOURCE));
        }
        return medicalRecord;
    }

    // add to visits
    private void nextAppointment(MedicalRecord medicalRecord, int index){

    }

    private void grow(){
        Patient[] expandedPatient = new Patient[patients.length + INCREMENT];
        // copy contents from original array to new array
        for (int i = SOURCE; i < patients.length; i++) {
            expandedPatient[i] = patients[i];
        }
        // set appointments to extended array
        patients = expandedPatient;
    }

    public int getSize(){
        return size;
    }

    public Patient getRecord(int index){
        return patients[index];
    }

    @Override
    public String toString() {
        int size = this.getSize();
        String billingRecords= "";
        for (int i = 0; i < size; i++) {
            billingRecords +=
                    this.getRecord(i).toString() + "\n";
        }
        return billingRecords;
    }
}

/*
this is for when we print the medical records, we move the visits to this
 This is an array-based implementation of a linear data structure, “Bag,” to hold a list of patient objects.
The bag allows adding a patient object but does not allow removal.
 You must implement an add() method, or -2 points.
 You can add constructors and methods, but you CAN NOT add/change the instance variables, or -2 points
for each violation
 */