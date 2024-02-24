import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * COPYRIGHT (C) 2023 Ranuga Anthony. All Rights Reserved.
 * Class to implement a ticket booking service for New Theatre.
 * Solves 4COSC010C Software Development II Coursework 22/23.
 * @author Ranuga Anthony
 * @version 1.0 2023-03-22
 */

public class Theatre {
    private static int[] row1 = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}; // row 1
    private static int[] row2 = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}; // row 2
    private static int[] row3 = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}; // row 3

    private static int row_no;  //  variable to save user's selected row number
    private static int seat_no; //  variable to save user's selected seat number

    private static int cancelrow_num; //  variable to save user's selected cancelling row number
    private static int cancelseat_num; //  variable to save user's selected cancelling seat number

    private static int[][] stagerows = {row1, row2, row3}; // creating a 2-dimensional array to store all 3 arrays row1, row2 and row3

    private static ArrayList<String> rowdetails = new ArrayList<String>(); // an arraylist to store all the available seat numbers
    private static String string_row_details;
    private static ArrayList<String> filelist = new ArrayList<String>(); // an arraylist to store the information within the stagerows 2 dimensional array to be used to write data to a text file
    static final String ERRORMSG1 = "Invalid seat number. Please enter a valid seat number again.";


    private static ArrayList<Ticket> tickets_info = new ArrayList<Ticket>(); // an arraylist to store the tickets

    private static char [] linearray; // an array

    public static void main(String[] args) {


        System.out.println("Welcome to the New Theatre");
        while (true) {

            System.out.print("\n");
            System.out.println("-------------------------------------");
            System.out.println("Please select an option:");
            System.out.println("1) Buy a ticket");
            System.out.println("2) Print seating area");
            System.out.println("3) Cancel ticket");
            System.out.println("4) List available seats");
            System.out.println("5) Save to file");
            System.out.println("6) Load to file");
            System.out.println("7) Print ticket information and total price");
            System.out.println("8) Sort tickets by price");
            System.out.println("0) Quit");
            System.out.println("--------------------------------------");
            Scanner input = new Scanner(System.in);
            System.out.print("Enter option: ");
            if (input.hasNextInt()) // Validating the option process to take in only integer inputs.
            {
                int option = input.nextInt();
                if (option == 0) {
                    System.out.println("Exiting program...");  //https://www.sololearn.com/Discuss/844740/how-do-you-exit-from-a-while-loop-from-a-switch-case-answered
                    break;
                }
                switch (option) // providing menu options using switch control structure
                {
                    case 1:
                        buy_ticket();
                        break;

                    case 2:
                        print_seating_area();
                        break;

                    case 3:
                        cancel_ticket();
                        break;

                    case 4:
                        show_available();
                        break;

                    case 5:
                        save();
                        break;

                    case 6:
                        load();
                        break;

                    case 7:
                        show_tickets_info();
                        break;

                    case 8:
                        sort_tickets();
                        break;


                    default:
                        System.out.println("Invalid Input as option. Please enter a value from the range 0-8.");
                        break;
                }
            } else {
                System.out.println("Invalid Input. Please enter an integer.");
                continue;
            }
        }
    }


    /**
     * allows the user to buy a ticket
     */
    public static void buy_ticket() {

        Scanner getInput = new Scanner(System.in);

        System.out.print("Enter your first name: ");
        String firstname = getInput.next();
        System.out.print("Enter your last name: ");
        String lastname = getInput.next();
        String emailaddress;
        while (true) { // Validating the email address entered by the user
            System.out.print("Enter your email address: ");
            emailaddress = getInput.next();

            if(emailaddress.contains("@") && (emailaddress.contains(".com"))) //https://www.w3schools.com/java/ref_string_contains.asp
                // using String contains() method to check whether the specific characters are present in the input email address
                {
                System.out.println("Valid email address recorded successfully.");
                break;
            }else{
                System.out.println("Invalid email address. Please enter a valid email address.");
            }
        }

        Person person = new Person(firstname,lastname,emailaddress); // creating a person object from Person class

        while (true) {
            System.out.print("Enter the row number between 1-3: ");

            try { // validation of row number as an integer

                row_no = getInput.nextInt();

                if (row_no >= 1 && row_no <= 3) { // validating the row number: whether the row number is in range
                    while (true) {
                        System.out.print("Enter the seat number: ");

                        try {
                            seat_no = getInput.nextInt(); // validating the seat number as an integer

                            if (seat_no >= 1 && seat_no <= stagerows[row_no - 1].length) {  // validating the seat number: whether the seat number is in range.
                                if (stagerows[row_no - 1][seat_no - 1] == 1) {
                                    System.out.println("Seat is already booked. Please enter a different seat number");
                                    continue;
                                } else {
                                    stagerows[row_no - 1][seat_no - 1] = 1;
                                    while(true){
                                        System.out.print("Please enter the price (£10, £20 and £30 ) : ");
                                        if (getInput.hasNextInt()) {
                                            int ticketprice = getInput.nextInt();
                                            if (ticketprice > 0 && ticketprice <= 30 && ticketprice % 10 == 0) { // validating the price entered by the user
                                                Ticket ticket = new Ticket(person, row_no, seat_no, ticketprice); // making a new ticket from Ticket class
                                                tickets_info.add(ticket); // adding the ticket to the arraylist of tickets
                                                System.out.print("\n");
                                                ticket.print();
                                                System.out.println("Seat Booking successful.");
                                                getInput.nextLine(); // https://www.freecodecamp.org/news/java-scanner-nextline-call-gets-skipped-solved/

                                            } else {
                                            System.out.println("Invalid price. Please enter a price out of £10, £20 and £30.");
                                            continue;
                                            }
                                        } else {
                                            System.out.println("Invalid Input. Please enter an integer for price.");
                                            getInput.next();
                                            continue;
                                        }
                                        break;
                                    }
                                }
                            } else {
                                System.out.println(ERRORMSG1);
                                continue;
                            }

                        break;
                        } catch (Exception e) {
                            System.out.println("Invalid Input. Please enter an integer for seat number.");
                            getInput.nextLine();// https://www.freecodecamp.org/news/java-scanner-nextline-call-gets-skipped-solved/
                        }
                    }

                } else {
                    System.out.println("Invalid Input. Please enter a row number between 1-3");
                    continue;
                }
                break;
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter an integer for row number.");
                getInput.nextLine(); // https://www.freecodecamp.org/news/java-scanner-nextline-call-gets-skipped-solved/
            }
        }
    }

    /**
     * print the seating arrangement/area
     */
    public static void print_seating_area() {
        System.out.println("\t" + "***********");
        System.out.println("\t" + "*  STAGE  *");
        System.out.println("\t" + "***********");


        for (int i = 0; i < stagerows.length; i++) { // printing the spaces infront of the respective rows
            if (i == 0) {
                System.out.print("\t");
            }
            if(i == 1){
                System.out.print("  ");
            }

            for (int j = 0; j < stagerows[i].length; j++) { // printing the arrangement of seats : 0 - available, X - non-available

                if (stagerows[i][j] == 0) {
                    System.out.print("O");

                } else {
                    System.out.print("X");
                }

                if (j == (stagerows[i].length - 1) / 2) { // seperating each row into 2 equal sections
                    System.out.print(" ");
                }
            }

            System.out.print("\n");
        }
    }

    /**
     * to cancel a ticket
     */
    public static void cancel_ticket() {
        Scanner Getinput = new Scanner(System.in);
        while (true) {
            try { // validating the cancel row number as an integer
                System.out.print("Enter the row number: ");

                cancelrow_num = Getinput.nextInt();
                if (cancelrow_num >= 1 && cancelrow_num <= 3) { // validating whether cancel row number is in range
                    while (true) {
                        System.out.print("Enter the seat number: ");

                        try { // validating the cancel seat number as an integer
                            cancelseat_num = Getinput.nextInt();
                            if (cancelseat_num > 0 && cancelseat_num <= stagerows[cancelrow_num - 1].length) { // validating whether cancel seat number is in range
                                if (stagerows[cancelrow_num - 1][cancelseat_num - 1] == 1) {
                                    stagerows[cancelrow_num - 1][cancelseat_num - 1] = 0;
                                    for(int i = 0; i< tickets_info.size();i++){ // iterating through tickets_info arraylist to find the corresponding ticket to remove it from arraylist
                                        if(tickets_info.get(i).getrow() == cancelrow_num && tickets_info.get(i).getseat() == cancelseat_num){
                                            tickets_info.remove(tickets_info.get(i));
                                        }
                                    }
                                    System.out.println("Seat Cancellation successful.");
                                } else {
                                    System.out.println("The seat number that you have entered is already available. Cancellation Unsuccessful. Please try again.");
                                }
                                break;
                            } else {
                                System.out.println(ERRORMSG1);
                                //continue;
                            }
                        } catch (Exception e) {
                            System.out.println("Invalid Input. Please enter an integer for seat number.");
                            Getinput.nextLine(); // https://www.freecodecamp.org/news/java-scanner-nextline-call-gets-skipped-solved/
                        }

                    }
                    break;
                } else {
                    System.out.println("Invalid Input. Please enter a row number between 1-3");
                    continue;
                }
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter an integer.");
                Getinput.nextLine(); // https://www.freecodecamp.org/news/java-scanner-nextline-call-gets-skipped-solved/
            }

        }


    }

    /**
     * print the list of available seats
     */
    public static void show_available() {

        for (int m = 0; m < stagerows.length; m++) {
            System.out.print("Seats available in row " + (m + 1) + " : ");
            for (int n = 0; n < stagerows[m].length; n++) {
                if (stagerows[m][n] == 0) { // to print the available seats in each row
                    rowdetails.add(Integer.toString(n + 1)); // https://www.freecodecamp.org/news/how-to-convert-integer-to-string-in-java/

                }
            }
            arraylist_to_string(rowdetails); // converting rowdetails arraylist to string
            rowdetails.clear(); // clearing the arraylist
            System.out.print("\n");

        }
    }

    /**
     * save the information in the row1,row2 and row3 arrays to a text file
     */
    public static void save() {
        try { // validating the creation of file
            File file = new File("list_of_seats.txt");
            boolean file_created = file.createNewFile();
            if (file_created) {
                System.out.println("The file " + file + " was successfully created.");
            } else if (file.exists()) {
                System.out.println("The file " + file + " already exists.");
            } else {
                System.out.println("Error while creating the file.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            FileWriter writer = new FileWriter("list_of_seats.txt");
            for (int i = 0; i < stagerows.length; i++) {
                int count = i + 1;
                writer.write("Row ");
                writer.write(Integer.toString(count)); // https://www.freecodecamp.org/news/how-to-convert-integer-to-string-in-java/
                writer.write(" : ");
                filelist.add(Arrays.toString(stagerows[i])); // https://www.geeksforgeeks.org/how-to-convert-an-array-to-string-in-java/
                String flist = filelist.toString(); // https://www.programiz.com/java-programming/examples/arraylist-string-conversion
                flist = flist.substring(2, flist.length() - 2) + ".";
                writer.write(flist);
                filelist.clear();
                writer.write("\n");

            }
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * retrieve the information from the text file and load it to the respective arrays
     */
    public static void load() {
        try {
            File inputFile = new File("list_of_seats.txt");
            Scanner readfile = new Scanner(inputFile);
            String fileline;
            int k = 1;
            while (readfile.hasNextLine()) {

                    fileline = readfile.nextLine();
                    String filestringline;
                    filestringline = fileline.substring(8, fileline.length() - 1);
                    filestringline = filestringline.replaceAll(",", ""); //https://programming.guide/java/remove-trailing-comma-from-comma-separated-string.html
                    filestringline = filestringline.replaceAll(" ", ""); // https://programming.guide/java/remove-trailing-comma-from-comma-separated-string.html
                    linearray = new char[filestringline.length()];
                    for(int i = 0; i<filestringline.length();i++){
                        linearray[i] = filestringline.charAt(i);

                    }
                    if(k == 1) {
                        filelineload(linearray,row1); // using filelineload method to include the values in each array
                        System.out.println("Row 1 details loaded successfully...");

                    }
                    if(k == 2) {
                        filelineload(linearray,row2); // using filelineload method to include the values in each array
                        System.out.println("Row 2 details loaded successfully...");

                    }
                    if(k == 3) {
                        filelineload(linearray,row3); // using filelineload method to include the values in each array
                        System.out.println("Row 3 details loaded successfully...");
                    }
                    k++;

            }
            readfile.close();
        } catch (IOException e) {
            System.out.println("Error while reading the file");
            e.printStackTrace();
        }

    }

    /**
     * print the ticket information
     */
    public static void show_tickets_info() {
        int total_price = 0;
        if (tickets_info.size() != 0) {  // iterating through the tickets_info arraylist to print each ticket
            System.out.println("\t" + "Ticket Information:");
            for (int i = 0; i < tickets_info.size(); i++) {

                tickets_info.get(i).print();
                System.out.print("\n");
                total_price += tickets_info.get(i).getprice();
            }
            System.out.print("Total price of all tickets : " + "£" + total_price); // print total price of tickets
        }
        else{
            System.out.println("No Tickets available.");
        }
    }

    /**
     * sort the tickets in ascending order of price
     */
    private static void sort_tickets() {
        int min_price; // creating a variable to store the minimum price
        Ticket temp;
        if (tickets_info.size() != 0) {
            for (int i = 0; i < tickets_info.size() - 1; i++) { // sorting the tickets by iterating through arraylist

                min_price = i;
                for (int j = i + 1; j <= tickets_info.size() - 1; j++) {
                    if (tickets_info.get(j).getprice() < tickets_info.get(min_price).getprice()) {
                        min_price = j;
                    }

                }
                temp = tickets_info.get(i);
                tickets_info.set(i, tickets_info.get(min_price));
                tickets_info.set(min_price, temp);
            }
            System.out.println("Tickets are sorted according to price in ascending order.");
            show_tickets_info();
        }
        else{
            System.out.println("No Tickets available.");
        }
    }

    /**
     * save the values in char array into the integer arrays by iterating through them
     * @param array array of chars to be converted to array of integers
     * @param row_array  array of integers that saves the information from the array of chars
     */
    public static void filelineload(char [] array, int [] row_array) {
        for (int l = 0; l < array.length; l++) {
            row_array[l] = Character.getNumericValue((array[l])); // converting the character in char array to integer and storing it in integer array.
        }
    }

    /**
     * convert arraylist to a string and slicing the string
     * @param string_list arraylist to be converted to string
     */
    public static void arraylist_to_string(ArrayList string_list) {
        string_row_details = string_list.toString(); // https://www.programiz.com/java-programming/examples/arraylist-string-conversion
        string_row_details = string_row_details.substring(1, string_row_details.length() - 1) + "."; // slicing the string and storing it in str variable
        System.out.print(string_row_details);
    }


}
