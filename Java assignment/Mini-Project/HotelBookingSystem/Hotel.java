/*
===============================
File: Hotel.java

What is used:
- Class and Object
- Arrays (Room[])
- Collections Framework:
  * LinkedList<Booking> for dynamic reservation lists
  * HashMap<Integer, Room> for O(1) average-time room lookups
  * TreeMap<Integer, Room> for sorted room displays
- StringBuilder for efficient String concatenation
- Loops (Enhanced for-loop & standard index-based loop)
- Conditionals (if-else validation)

Purpose:
- Ye hotel data engine hai jo rooms array, bookings collections aur business logic control karta hai.

Main responsibility:
- Room searching, room booking, booking cancellation, aur formatted room/booking records retrieve karna.
===============================
*/

import java.util.LinkedList;
import java.util.HashMap;
import java.util.TreeMap;

// Hotel class jo saare rooms, bookings aur searching operations ko manage karti hai.
public class Hotel {

    // Room-related information store karne ke liye base Array.
    private Room[] rooms;

    // Dynamic booking transactions store karne ke liye LinkedList collection.
    private LinkedList<Booking> bookings;

    // Room number se instant O(1) average-time search ke liye HashMap collection (Key: Room Number, Value: Room object).
    private HashMap<Integer, Room> roomMap;

    // Room numbers ke natural sorted order mein rooms maintain karne ke liye TreeMap collection.
    private TreeMap<Integer, Room> sortedRooms;

    // Auto-incrementing IDs for new customers and bookings.
    private int nextCustomerId = 1;
    private int nextBookingId = 1001;


    // =========================
    // CONSTRUCTOR
    // =========================

    // Constructor: Room array receive karta hai aur HashMap, TreeMap aur LinkedList initialize karta hai.
    public Hotel(Room[] rooms) {

        this.rooms = rooms;

        // Booking records ke liye empty LinkedList instantiate karte hain.
        bookings = new LinkedList<>();

        // Fast O(1) average-time lookup ke liye empty HashMap instantiate karte hain.
        roomMap = new HashMap<>();

        // Sorted display ke liye empty TreeMap instantiate karte hain.
        sortedRooms = new TreeMap<>();

        // Enhanced for-loop se saare rooms ko HashMap aur TreeMap mein put karte hain.
        for (Room room : rooms) {

            // HashMap mein Room number ko Key aur Room object ko Value set karte hain.
            roomMap.put(
                    room.getRoomNumber(),
                    room
            );

            // TreeMap mein room insert hote hi automatically sorted order mein arrange ho jata hai.
            sortedRooms.put(
                    room.getRoomNumber(),
                    room
            );
        }

        // Application start hote hi bookings.txt file se previously saved bookings load karte hain.
        BookingFileManager.loadBookingsFromFile(this);
    }


    // =========================
    // DISPLAY ROOMS
    // =========================

    // Console par saare rooms print karne ke liye method.
    public void displayRooms() {

        System.out.println(
                "\n--- All Hotel Rooms ---"
        );

        // Enhanced for-loop se array ke har Room ko console par output karte hain.
        for (Room room : rooms) {

            System.out.println(room);
        }
    }


    // =========================
    // GET ALL ROOMS FOR GUI
    // =========================

    // GUI text area ke liye saare rooms ko ek single formatted string mein accumulate karke return karta hai.
    public String getAllRooms() {

        // StringBuilder ka use high-performance string concatenation ke liye kiya gaya hai.
        StringBuilder result =
                new StringBuilder();

        for (Room room : rooms) {

            result.append(room)
                    .append("\n");
        }

        return result.toString();
    }


    // =========================
    // SEARCH ROOM
    // =========================

    // HashMap se room number dwara O(1) average-time lookup karke Room object return karta hai.
    public Room searchRoom(int roomNumber) {

        return roomMap.get(roomNumber);
    }


    // =========================
    // DISPLAY SORTED ROOMS
    // =========================

    // TreeMap ke sorted values ko console par print karne ke liye method.
    public void displaySortedRooms() {

        System.out.println(
                "\n--- Rooms in Sorted Order ---"
        );

        for (Room room : sortedRooms.values()) {

            System.out.println(room);
        }
    }


    // =========================
    // GET CUSTOMER ID
    // =========================

    // Agla Customer ID return karke counter ko auto-increment (++ operator) karta hai.
    public int getNextCustomerId() {

        return nextCustomerId++;
    }


    // =========================
    // GET BOOKING ID
    // =========================

    // Agla Booking ID return karke counter ko auto-increment (++ operator) karta hai.
    public int getNextBookingId() {

        return nextBookingId++;
    }


    // =========================
    // LOAD EXISTING BOOKING FROM FILE
    // =========================

    // File se load ki gayi existing booking ko system state mein sync karne ke liye helper method.
    public void loadExistingBooking(
            int bookingId,
            String customerName,
            int roomNumber,
            String checkIn,
            String checkOut) {

        Room room = roomMap.get(roomNumber);
        if (room == null) {
            return;
        }

        long numberOfNights = 1;
        try {
            java.time.format.DateTimeFormatter formatter =
                    java.time.format.DateTimeFormatter.ofPattern("dd-MM-yyyy");
            java.time.LocalDate checkInDate =
                    java.time.LocalDate.parse(checkIn, formatter);
            java.time.LocalDate checkOutDate =
                    java.time.LocalDate.parse(checkOut, formatter);
            numberOfNights =
                    java.time.temporal.ChronoUnit.DAYS.between(checkInDate, checkOutDate);
            if (numberOfNights <= 0) numberOfNights = 1;
        } catch (Exception e) {
            numberOfNights = 1;
        }

        int customerId = getNextCustomerId();
        Customer customer = new Customer(customerId, customerName, "N/A", "N/A");

        Booking booking = new Booking(bookingId, customer, room, checkIn, checkOut, numberOfNights);
        bookings.add(booking);

        // Room ki availability state ko false set karte hain
        room.setAvailable(false);

        // Auto-increment ID counter ko update karte hain taaki fresh bookings par ID collision na ho
        if (bookingId >= nextBookingId) {
            nextBookingId = bookingId + 1;
        }
    }


    // =========================
    // BOOK ROOM
    // =========================

    // Core business method: Room check karta hai, Booking object banata hai, LinkedList mein add karta hai aur room ko unavailable mark karta hai.
    public boolean bookRoom(
            int bookingId,
            Customer customer,
            int roomNumber,
            String checkIn,
            String checkOut,
            long numberOfNights) {

        // HashMap ka lookup method call karke O(1) average-time mein Room dhoondhte hain.
        Room room =
                roomMap.get(roomNumber);


        // Check 1: Agar HashMap ne null return kiya matlab room exist nahi karta.
        if (room == null) {

            System.out.println(
                    "Room " +
                    roomNumber +
                    " does not exist."
            );

            return false;
        }


        // Check 2: Agar room mil gaya par pehle se booked hai (available == false).
        if (!room.isAvailable()) {

            System.out.println(
                    "Room " +
                    roomNumber +
                    " is already booked."
            );

            return false;
        }


        // Naya Booking object create karte hain. Constructor ke andar bill auto-calculate hota hai.
        Booking booking =
                new Booking(
                        bookingId,
                        customer,
                        room,
                        checkIn,
                        checkOut,
                        numberOfNights
                );


        // Booking record ko LinkedList collection mein add karte hain.
        bookings.add(booking);


        // Room ki availability flag ko false set karte hain taaki dobara book na ho sake.
        room.setAvailable(false);


        // Booking details ko bookings.txt file me append mode se store karte hain (writing booking details).
        try {
            BookingFileManager.appendBooking(booking);
        } catch (Exception e) {
            System.err.println("Error saving booking to file: " + e.getMessage());
            e.printStackTrace();
        }


        System.out.println(
                "Room " +
                roomNumber +
                " booked successfully!"
        );

        System.out.println(booking);

        return true;
    }


    // =========================
    // DISPLAY BOOKINGS
    // =========================

    // Console par saari active bookings print karta hai.
    public void displayBookings() {

        System.out.println(
                "\n--- All Bookings ---"
        );

        for (Booking booking : bookings) {

            System.out.println(booking);
        }
    }


    // =========================
    // GET BOOKINGS FOR GUI
    // =========================

    // GUI JTextArea ke liye active bookings ko bookings.txt file se read karke return karta hai (reading booking details).
    public String getAllBookings() {

        try {
            return BookingFileManager.readAllBookings();
        } catch (Exception e) {
            System.err.println("Error reading bookings file: " + e.getMessage());
            return "No bookings found.";
        }
    }


    // =========================
    // CANCEL BOOKING
    // =========================

    // Booking ID ke dwara reservation cancel karke room ko firse available karta hai.
    public boolean cancelBooking(
            int bookingId) {

        // Index-based for loop LinkedList traversal ke liye.
        for (int i = 0;
             i < bookings.size();
             i++) {

            Booking booking =
                    bookings.get(i);


            // Target booking ID match karne par logic execute karte hain.
            if (booking.getBookingId()
                    == bookingId) {

                // Step 1: Room object fetch karke setAvailable(true) call karte hain taaki room free ho jaye.
                booking.getRoom()
                        .setAvailable(true);


                // Step 2: LinkedList se booking transaction remove kar dete hain.
                bookings.remove(i);


                // Step 3: Updated bookings list ko bookings.txt file me rewrite karte hain (removing a cancelled booking).
                try {
                    BookingFileManager.saveAllBookings(bookings);
                } catch (Exception e) {
                    System.err.println("Error updating bookings file after cancellation: " + e.getMessage());
                    e.printStackTrace();
                }


                System.out.println(
                        "Booking " +
                        bookingId +
                        " cancelled successfully."
                );

                return true;
            }
        }


        // Loop khatam ho gaya aur booking ID nahi mila.
        System.out.println(
                "Booking " +
                bookingId +
                " not found."
        );

        return false;
    }
}