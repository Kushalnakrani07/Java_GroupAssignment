/*
===============================
File: Room.java

What is used:
- Class and Object
- Encapsulation (private variables, public getters/setters)
- Constructor
- Methods
- Method Overriding (@Override toString)

Purpose:
- Ye class hotel ke ek single room ki details aur availability status model karti hai.

Main responsibility:
- Room number, room type, price per night aur availability state ko store aur manage karna.
===============================
*/

// Room class ek blueprint hai jo single hotel room ki properties ko define karti hai.
public class Room {

    // Room ka unique number store karne ke liye (private taaki bahar se directly modify na ho).
    private int roomNumber;

    // Room ka type store karne ke liye (e.g., Single, Double, Deluxe).
    private String roomType;

    // Room ka ek raat ka charge (price per night) store karne ke liye.
    private double pricePerNight;

    // Room filhaal available hai (true) ya booked hai (false), ye track karne ke liye flag.
    private boolean available;

    // Constructor: Jab naya Room object banta hai tab room parameters ko initialize karta hai.
    // Default par har naya room available = true set hota hai.
    public Room(int roomNumber, String roomType, double pricePerNight) {
        // 'this' keyword current object ke instance variables ko assign karne ke liye use hota hai.
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.pricePerNight = pricePerNight;
        this.available = true; // By default room available hota hai.
    }

    // Getter method: Room number ki value read karne ke liye return karta hai.
    public int getRoomNumber() {
        return roomNumber;
    }

    // Getter method: Room type ki String value return karta hai.
    public String getRoomType() {
        return roomType;
    }

    // Getter method: Price per night ki value return karta hai.
    public double getPricePerNight() {
        return pricePerNight;
    }

    // Getter method: Room ki availability state (true/false) check karne ke liye.
    public boolean isAvailable() {
        return available;
    }

    // Setter method: Booking hone par ya cancel hone par availability state ko update karne ke liye.
    public void setAvailable(boolean available) {
        this.available = available;
    }

    // Method Overriding: Object class ke toString() method ko override karke custom formatted string return karta hai.
    @Override
    public String toString() {
        return "Room " + roomNumber +
                " | Type: " + roomType +
                " | Price: ₹" + pricePerNight +
                " | Available: " + available;
    }
}