/**
 * @author Varun
 * @author Yuet
 */
public class MedicalRecord {
    private Patient[] patients;
    private int size; // number of patient objects in the array


    // Here we should create a method to get the billing statements
    // from the user.

    // medical record is only used when the PS command is called, "completes" or clears out the appt list
    // used for adding up all the charges

    // constructor
    public MedicalRecord(int size) {
        this.patients = new Patient[size];
        this.size = 0;
    }

    public void add(Patient newPatient){

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