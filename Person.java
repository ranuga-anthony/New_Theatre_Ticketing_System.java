/**
 * COPYRIGHT (C) 2023 Ranuga Anthony. All Rights Reserved.
 * Class to implement a ticket booking service for New Theatre, and to create objects to be used by the main method.
 * Solves 4COSC010C Software Development II Coursework 22/23.
 * @author Ranuga Anthony
 * @version 1.0 2023-03-22
 */
public class Person { // Class file consisting of a person constructor and its attributes to be used by the main method and class
    private String name; // to save the user's name that is entered in the main program.
    private String surname; // to save the user's surname that is entered in the main program.
    private String email; // to save the user's email address that is entered in the main program.

    /**
     *
     * @param FName saving the name entered by the user in name attribute
     * @param Surname saving the surname entered by the user in surname attribute
     * @param Email saving the email address entered by the user in email attribute
     */
    public Person(String FName,String Surname,String Email){ // to create an object called Person from the class to be used in the main method and class
        name = FName;
        surname = Surname;
        email = Email;
    }

    /**
     *
     * @return the name of the person
     */
    public String getname(){
        return name;
 } // method to get the name of the user or person

    /**
     *
     * @return the surname of the person
     */
    public String getsurname(){
        return surname;} // method to get the surname of the user or person

    /**
     *
     * @return the email of the person
     */
    public String getemail(){
        return email;
 } // method to get the email address of the user or person
}
