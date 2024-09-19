# softwaremethproject1
scheduler software to help the clinic manage the appointments.

## Requirements:

    1. The system shall maintain an appointment calendar for all upcoming appointments.

    2. The system shall keep track of the date, time, patient, and provider for each appointment.

    3. The system shall uniquely identify each patient with a profile containing the patient's first name, last name, and  
    date of birth.

    4. The system shall be able to schedule a new appointment for a patient with the requested date, time, and provider.

    5. The system shall not schedule an appointment if a given patient has an existing appointment with the same date
    and time.

    6. The system shall be able to cancel an existing appointment given the patient and the date and time of the 
    appointment.

    7. The system shall reschedule an existing appointment given a different timeslot on the same day.

    8. The system shall be able to display the list of appointments ordered by the patient profile and appointment date 
    and time, or by the appointment date and time and provider, or by location.

    9. The system shall maintain a list of visits for each patient when the patient's appointments have been completed.

    10. The system shall be able to print the billing statements for all patients when the appointments are completed.

#### Package Limitations
- Scanner
- StringTokenizer
- Calendar
- DecimalFormat class

### Recitation Notes
- Make sure appointment is comparable in order to compare different appoints using a searching algorithm
- Medical records class
- Clear appointments once charges are completed
- Main function is a while
- it keeps getting commands from user
- use a switch statement to check the different cases
- no array list, only scanner

#### Appointment class
- comparable basically makes the class comparable to other objects of the class
- 4 mandatory fields all private, basically 4 classes
- Need a constructor
- must make equals, toString, compareTo with override tags (MUST DO THIS)
- for toString there is a specific implement
- toString must be in all classes
- recursively call charge on appoint until next appointment is null
- need a schedular class
- in main
    - only call the schedular class
- for the list class, when removing move from the end of the list to the first non-used index
#### Date Class
- year, month, day, isValid() function
- must debug for every class in order to avoid bugs

#### enum classes
- need classes for each enum


#### Test cases
- use the test case tools to check the accuracy of the program with the output