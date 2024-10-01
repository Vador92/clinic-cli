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
     * This method adds unique clients, with charges, to the medical records.
     * @param clinic The list of all the appointments
     * @return MedicalRecord object with all unique clients
     */
    public void add(List clinic){

        if (clinic.getSize() == EMPTY){
            return;
        }

        clinic.bubbleSortPatient(clinic.getAppointments());

        for (int i = SOURCE; i < clinic.getSize(); i++) {
            Patient newPatient = new Patient(clinic.get(i).getPatientProfile(), null);
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
                this.nextAppointment(this, patientIndex, clinic.get(i));
            }
        }
        while (clinic.getSize() != EMPTY){
            clinic.remove(clinic.get(SOURCE));
        }
    }


    private void nextAppointment(MedicalRecord medicalRecord,
                                 int index,
                                 Appointment newAppointment){
        Patient exisingPatient = medicalRecord.getRecord(index);
        exisingPatient.addAppointment(newAppointment);
        exisingPatient.getCharge();
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

    private int findPatient(Patient newPatient){
        for(int i = 0; i < size; i++){
            if(patients[i].equals(newPatient)){
                return i;
            }
        }
        return NOT_FOUND;
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
        for (int i = SOURCE; i < size; i++) {
            billingRecords += String.format("(%d) ", i+PRINT_SHIFT) +
                    this.getRecord(i).toString() + "\n";
        }
        return billingRecords;
    }
}