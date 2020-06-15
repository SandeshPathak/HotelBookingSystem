package hotelbookingsystem;

import java.text.ParseException;

public class HotelBookingSystem {

    public static void main(String[] args) throws ParseException {
        City city = new City();//Calling a new city hotel object
        city.getHotelName();//Get the hotel name
        city.getHotelLocation();//Get the hotel location
        city.guestFirstname();
        city.guestLastname();
        city.bookaDate();
        city.bookGuests();
        city.selectRoomIntro();

        city.BookingReference();//Write the Reference to the text file
        city.printText();//Read the text file and print out the data entered

    }

}