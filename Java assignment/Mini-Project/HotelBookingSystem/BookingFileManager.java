/*
===============================
File: BookingFileManager.java

What is used:
- Java File I/O (File, FileWriter, FileReader, Scanner)
- Exception Handling (try-catch, try-with-resources)
- Permanent File Persistence for bookings.txt

Purpose:
- Ye file booking ki details permanently store karti hai.
- Java File I/O ka use karke file handling implement ki gayi hai (without BufferedReader/BufferedWriter).

Hinglish Explanation:
- FileWriter: File me text write karne ke liye use hota hai.
- append mode: FileWriter(FILE_NAME, true) - isse purani bookings delete nahi hoti, naya record file ke end me append hota hai.
- Scanner / FileReader: File se text line-by-line read karne ke liye utility.
- file existence: File exist karti hai ya nahi check karke, agar na ho toh createNewFile() se automatic create ki jati hai.
- writing booking details: Nayi booking record ko formatted text format me write karna.
- reading booking details: bookings.txt se saare records read karke GUI me return karna.
- removing a cancelled booking: Cancelled booking hatane ke baad active bookings ke saath file ko rewrite karna.
===============================
*/

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

// Helper class for handling all persistent file operations on bookings.txt
public class BookingFileManager {

    // Relative filename so bookings.txt is created in the project's working directory.
    // Ye file booking ki details permanently store karti hai.
    private static final String FILE_NAME = "bookings.txt";

    // File existence check aur automatic creation method
    // file existence check: File exist karti hai ya nahi, check karke automatic create ki jati hai.
    public static void ensureFileExists() throws IOException {
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            file.createNewFile(); // File exist nahi karti toh automatic create ho jayegi
        }
    }

    // Save/Append a single booking details to bookings.txt
    // writing booking details: Nayi booking ko file me append mode se save karna.
    // append mode ka use karke purani bookings ko preserve kiya jata hai.
    public static void appendBooking(Booking booking) throws IOException {
        ensureFileExists(); // file existence ensure kar rahe hain

        // FileWriter ke constructor me second argument 'true' pas karke append mode enable kiya jata hai.
        try (FileWriter writer = new FileWriter(FILE_NAME, true)) {
            // Each booking is stored clearly and separately in the required standard format
            writer.write("Booking ID: " + booking.getBookingId() + "\n");
            writer.write("Customer Name: " + booking.getCustomer().getName() + "\n");
            writer.write("Room Number: " + booking.getRoom().getRoomNumber() + "\n");
            writer.write("Check In: " + booking.getCheckIn() + "\n");
            writer.write("Check Out: " + booking.getCheckOut() + "\n");
            writer.write("----------------------------------------\n\n");
        }
    }

    // Read all raw content from bookings.txt for "View Bookings"
    // reading booking details: bookings.txt file se saare saved booking records ko read karna.
    public static String readAllBookings() throws IOException {
        File file = new File(FILE_NAME);

        // Agar file exist nahi karti ya file empty hai toh "No bookings found." show karenge
        if (!file.exists() || file.length() == 0) {
            return "No bookings found.";
        }

        StringBuilder content = new StringBuilder();
        // Scanner aur FileReader ka use karke file se text line-by-line read karte hain
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                content.append(scanner.nextLine()).append("\n");
            }
        }

        String result = content.toString().trim();
        if (result.isEmpty()) {
            return "No bookings found.";
        }

        return result;
    }

    // Rewrite the bookings.txt file with current list of active bookings (used during cancellation)
    // removing a cancelled booking: Cancelled booking ko list se hatakar updated records bookings.txt me write karna.
    public static void saveAllBookings(List<Booking> bookings) throws IOException {
        // FileWriter me second argument false rakha hai taaki file overwrite ho aur cancelled booking remove ho jaye.
        try (FileWriter writer = new FileWriter(FILE_NAME, false)) {
            for (Booking booking : bookings) {
                writer.write("Booking ID: " + booking.getBookingId() + "\n");
                writer.write("Customer Name: " + booking.getCustomer().getName() + "\n");
                writer.write("Room Number: " + booking.getRoom().getRoomNumber() + "\n");
                writer.write("Check In: " + booking.getCheckIn() + "\n");
                writer.write("Check Out: " + booking.getCheckOut() + "\n");
                writer.write("----------------------------------------\n\n");
            }
        }
    }

    // Application startup par bookings.txt se existing bookings load aur sync karne ke liye method
    // Ye method app start hote hi bookings.txt ko scan karke system state restore karta hai.
    public static void loadBookingsFromFile(Hotel hotel) {
        File file = new File(FILE_NAME);
        if (!file.exists() || file.length() == 0) {
            return; // Pehle se koi booking file nahi hai ya empty hai
        }

        try (Scanner scanner = new Scanner(file)) {
            int bookingId = 0;
            String customerName = null;
            int roomNumber = 0;
            String checkIn = null;
            String checkOut = null;

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (line.startsWith("Booking ID:")) {
                    bookingId = Integer.parseInt(line.substring(line.indexOf(":") + 1).trim());
                } else if (line.startsWith("Customer Name:")) {
                    customerName = line.substring(line.indexOf(":") + 1).trim();
                } else if (line.startsWith("Room Number:")) {
                    roomNumber = Integer.parseInt(line.substring(line.indexOf(":") + 1).trim());
                } else if (line.startsWith("Check In:")) {
                    checkIn = line.substring(line.indexOf(":") + 1).trim();
                } else if (line.startsWith("Check Out:")) {
                    checkOut = line.substring(line.indexOf(":") + 1).trim();
                } else if (line.startsWith("----------------------------------------")) {
                    if (bookingId > 0 && customerName != null && roomNumber > 0 && checkIn != null && checkOut != null) {
                        hotel.loadExistingBooking(bookingId, customerName, roomNumber, checkIn, checkOut);
                    }
                    // Reset variables for next block
                    bookingId = 0;
                    customerName = null;
                    roomNumber = 0;
                    checkIn = null;
                    checkOut = null;
                }
            }
        } catch (Exception e) {
            System.err.println("Error loading bookings from file: " + e.getMessage());
        }
    }
}
