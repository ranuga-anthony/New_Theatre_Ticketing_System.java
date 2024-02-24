/**
 * COPYRIGHT (C) 2023 Ranuga Anthony. All Rights Reserved.
 * Class to implement a ticket booking service for New Theatre, and to create objects to be used by the main method.
 * Solves 4COSC010C Software Development II Coursework 22/23.
 * @author Ranuga Anthony
 * @version 1.0 2023-03-22
 */
public class Ticket {
    private int row; // to save the user's row number
    private int seat; // to save the user's seat number
    private int price; // to save the price of the seat selected by the user

    private Person person; // to create a ticket with the person attribute in it

    /**
     * creating a ticket constructor method
     * @param person saving the name entered by the user in name attribute
     * @param SRow saving the row entered by the user in row attribute
     * @param SSeat saving the seat entered by the user in seat attribute
     * @param Ticketprice saving the ticket price entered by the user in price attribute
     */
    public Ticket(Person person, int SRow, int SSeat, int Ticketprice){
        this.person = person;
        row = SRow;
        seat = SSeat;
        price = Ticketprice;
    }

    /**
     *
     * @return the row selected by the user
     */
    public int getrow(){
        return row;
    }

    /**
     *
     * @return the seat selected by the user
     */
    public int getseat(){
        return seat;
    }

    /**
     *
     * @return the price entered by the user
     */
    public int getprice(){
        return price;
    }

    /**
     * print the ticket details
     */
    public void print(){
        System.out.println("Person name: " + person.getname());
        System.out.println("Person surname: " + person.getsurname());
        System.out.println("Person email address: " + person.getemail());
        System.out.println("Row: " + row);
        System.out.println("Seat: " + seat);
        System.out.println("Price: " + "£" + price);

    }

}
