/*
===============================
File: Booking.java

What is used:
- Class and Object
- Encapsulation (private fields, public getters)
- Composition (Customer and Room objects as properties)
- Constructor with Automated Bill Calculation Logic
- Method Overriding (@Override toString)

Purpose:
- Ye class a-to-z booking transaction ko represent karti hai.

Main responsibility:
- Customer, Room, Check-in/Check-out dates, total stay nights aur automated total bill store aur compute karna.
===============================
*/

// Booking class jo Customer aur Room ke beech ki reservation transaction ko represent karti hai.
public class Booking {

    // Unique Booking ID store karne ke liye variable.
    private int bookingId;

    // Customer object jo room book kar raha hai (Composition concept).
    private Customer customer;

    // Room object jo book kiya gaya hai (Composition concept).
    private Room room;

    // Check-in date string (format: DD-MM-YYYY).
    private String checkIn;

    // Check-out date string (format: DD-MM-YYYY).
    private String checkOut;

    // Total kitne raat (nights) ka stay hai.
    private long numberOfNights;

    // Total billing amount (Room Price/Night * numberOfNights).
    private double totalAmount;

    // Constructor: Booking transaction details initialize karta hai aur bill auto-calculate karta hai.
    public Booking(
            int bookingId,
            Customer customer,
            Room room,
            String checkIn,
            String checkOut,
            long numberOfNights) {

        this.bookingId = bookingId;
        this.customer = customer;
        this.room = room;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.numberOfNights = numberOfNights;

        // Automatically calculate total bill amount (Per night price * number of nights)
        this.totalAmount = room.getPricePerNight() * numberOfNights;
    }

    // Getter method: Booking ID return karta hai.
    public int getBookingId() {
        return bookingId;
    }

    // Getter method: Associated Customer object return karta hai.
    public Customer getCustomer() {
        return customer;
    }

    // Getter method: Reserved Room object return karta hai.
    public Room getRoom() {
        return room;
    }

    // Getter method: Check-in date string return karta hai.
    public String getCheckIn() {
        return checkIn;
    }

    // Getter method: Check-out date string return karta hai.
    public String getCheckOut() {
        return checkOut;
    }

    // Getter method: Number of stay nights return karta hai.
    public long getNumberOfNights() {
        return numberOfNights;
    }

    // Getter method: Calculated total bill amount return karta hai.
    public double getTotalAmount() {
        return totalAmount;
    }

    // Method Overriding: Booking transaction details ko full summary string ke roop mein return karta hai.
    @Override
    public String toString() {
        return "Booking ID: " + bookingId +
                " | Customer: " + customer.getName() +
                " | Room: " + room.getRoomNumber() +
                " | Check-in: " + checkIn +
                " | Check-out: " + checkOut +
                " | Nights: " + numberOfNights +
                " | Total: ₹" + totalAmount;
    }
}