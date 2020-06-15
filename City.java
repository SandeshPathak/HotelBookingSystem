package hotelbookingsystem;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class City {

    private String hotelName = "Ramada City Hotel";
    private String hotelLocation = "28 Queen Street";
    //Userinput Information 
    public int numberofRooms;
    public int numberofGuests;
    public int remainingGuests;
    public String guestFirstname;
    public String guestLastname;
    public LocalDate formatted_checkin_date;
    public LocalDate formatted_checkout_date;

    Scanner scanner = new Scanner(System.in);
    SimpleDateFormat myFormat = new SimpleDateFormat("dd MM yyyy");

    //Getters to call the hotelName and hotel Location
    public String getHotelName() {
        System.out.println(hotelName);
        return hotelName;
    }

    public String getHotelLocation() {
        System.out.println(hotelLocation);
        return hotelLocation;
    }

    //Default Constructor
    public City() {
        System.out.println("Welcome to Ramada City hotel");

    }
    //Boolean for the valid or not statement

    public void guestFirstname() {//Method to get the first name
        System.out.println("Please enter your first name");
        this.guestFirstname = scanner.nextLine();
        while (!guestFirstname.matches("[a-zA-Z]+")) {//Checks wether the entered data is a Character
            System.out.println("Please enter a valid First Name");
            this.guestFirstname = scanner.nextLine();
        }

    }

    public void guestLastname() {//Method to get the last name

        System.out.println("Please enter your last name");
        this.guestLastname = scanner.nextLine();

        while (!guestLastname.matches("[a-zA-Z]+")) {//Checks wether the entered data is a Character
            System.out.println("Please enter a valid Last name");
            this.guestLastname = scanner.nextLine();

        }

    }

    public void bookaDate() {//Method to Book a checkin and checkout date
        int totalYearsBooked, totalMonthsBooked, totalDaysBooked;
        System.out.println("Please Enter A Check In Date in a dd-MM-yyyy format");

        LocalDate current_date = java.time.LocalDate.now();//Current date 
        try {

            String checkin_date = scanner.nextLine();

            DateTimeFormatter checkin_date_formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");//Check in date format
            this.formatted_checkin_date = LocalDate.parse(checkin_date, checkin_date_formatter);

            boolean compare = formatted_checkin_date.isBefore(current_date);//Check to compare the checkin date is before the current date

            if (compare) {
                System.out.println("The date you have entered is in the past. Please enter the future date");
                bookaDate();
            } else {

                System.out.println("Please Enter A Check Out Date in a dd-MM-yyyy format");
                String checkout_date = scanner.nextLine();
                DateTimeFormatter checkout_date_formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                this.formatted_checkout_date = LocalDate.parse(checkout_date, checkout_date_formatter);
                //This is to make sure the checkout date is after the checkin and current date
                boolean compare_checkout_date = formatted_checkout_date.isAfter(this.formatted_checkin_date) && this.formatted_checkout_date.isAfter(current_date);

                Period intervalPeriod = Period.between(formatted_checkin_date, formatted_checkout_date);
                totalYearsBooked = intervalPeriod.getYears();
                totalMonthsBooked = intervalPeriod.getMonths();
                totalDaysBooked = intervalPeriod.getDays();

                //Customer can stay for maximum 7 days only
                if (compare_checkout_date && totalDaysBooked < 7 && totalMonthsBooked < 1 && totalYearsBooked < 1) {
                    System.out.println("Thank you.");
                } else {
                    System.out.println("The date entered is not valid. Please enter a valid range");
                    bookaDate();
                }
            }

        } catch (Exception ex) {
            System.out.println("Please enter a valid date, Let's try again");
            bookaDate();
        }
    }

    public void bookGuests() {//Method to get the Guest number
        boolean isNumber;

        do {
            System.out.println("Please enter the number of Guests");

            if (scanner.hasNextInt()) {

                this.numberofGuests = scanner.nextInt();
                isNumber = false;
                if (numberofGuests > 10) {
                    System.out.println("Cannot book more than 10 guests, Please try again");
                    numberofGuests = 0;

                } else if (numberofGuests == 0) {
                    System.out.println("The number cannot be 0");
                    numberofGuests = 0;

                } else {
                    break;

                }

            } else {
                System.out.println("Not a valid input");
                isNumber = false;
                scanner.next(); // To flush the scanner value
            }

        } while (!(isNumber));
        this.remainingGuests = this.numberofGuests;

    }

    public void selectRoomIntro() {//Method to Select the Room
        System.out.println("Please choose which room you would like to book ");
        System.out.println("Enter 1. For Single Room");
        System.out.println("Enter 2. For Double Room");
        System.out.println("Enter 3. For Deluxe Room");

        try {
            String total_numberofRooms = scanner.next();
            this.numberofRooms = Integer.parseInt(total_numberofRooms);
            selectRoom(this.numberofRooms);
        } catch (Exception ex) {
            System.out.println("Please Enter a Valid input exiting the system");
            System.exit(0);
        }

    }

    private void selectRoom(int numberOfRooms) {//Method to select the room for the guests 
        //Single Room can only have 1 guest, Double Room can have upto 2 guests and Deluxe Room can fit in 3 guests

        while (this.remainingGuests > 0) {
            switch (numberofRooms) {
                case 1:

                    if (this.remainingGuests <= 1) {
                        System.out.println("Thank you for choosing the Rooms");
                        numberofRooms = 1;
                    }
                    this.remainingGuests--;
                    if (this.remainingGuests > 0) {
                        System.out.println("One single room is selected. Please choose other rooms for " + this.remainingGuests + " guests ");
                        selectRoomIntro();
                    }
                    break;
                case 2:
                    if (this.remainingGuests <= 2) {
                        System.out.println("Thank you for choosing the Rooms");
                        numberofRooms = 2;
                    }
                    this.remainingGuests = this.remainingGuests - 2;
                    if (this.remainingGuests > 0) {
                        System.out.println("One Double room is selected. Please choose other rooms for " + this.remainingGuests + " guests");
                        selectRoomIntro();
                    }
                    break;

                case 3:

                    if (this.remainingGuests <= 3) {

                        System.out.println("Thank you for choosing the Rooms, The rooms are all booked");
                        System.out.println("Your details are below, Please see the receptionist at the counter");
                    }
                    this.remainingGuests = this.remainingGuests - 3;
                    if (this.remainingGuests > 0) {
                        System.out.println("One Deluxe room is selected. Please choose other rooms for " + this.remainingGuests + " guests");
                        selectRoomIntro();
                    }
                    break;
                default: {
                    System.out.println("Not a valid input, Please enter the room again");
                    this.remainingGuests = -1;
                    selectRoomIntro();
                    break;
                }

            }
        }

    }

    public void BookingReference() {//Method to write the details to an output.txt file
        PrintWriter pw;
        try {
            pw = new PrintWriter("../output.txt");
            pw.println("First Name:" + this.guestFirstname);
            pw.println("Last Name:" + this.guestLastname);
            pw.println("Checkin Date: " + this.formatted_checkin_date);
            pw.println("Checkout Date: " + this.formatted_checkout_date);
            pw.println("Number of Guests:" + this.numberofGuests);
            pw.flush();
            pw.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public void printText() {//Method to read the details from the output.txt file
        File file = new File("../output.txt");//Output file is created outside of the Project File

        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader(file));
            String st;
            while ((st = br.readLine()) != null) {
                System.out.println(st);
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(City.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(City.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
