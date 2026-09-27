/*
===============================
File: HotelGUI.java

What is used:
- Java Swing GUI Toolkit (JFrame, JPanel, JButton, JLabel, JTextArea, JScrollPane, JOptionPane, Layout Managers)
- Inheritance (HotelGUI extends JFrame)
- Event Handling with Lambda Expressions (button.addActionListener(e -> ...))
- Exception Handling (try-catch blocks for NumberFormatException and DateTimeParseException)
- Java Date & Time API (LocalDate, DateTimeFormatter, ChronoUnit.DAYS)
- Regex Validation (phone number \\d{10} and email regex pattern matching)

Purpose:
- Ye desktop Graphical User Interface (GUI) main window hai jahan user buttons click karke system interact karta hai.

Main responsibility:
- Interactive Swing components render karna, user inputs capture & validate karna, aur results/alerts display karna.
===============================
*/

import javax.swing.*;
import java.awt.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;

// HotelGUI class jo JFrame ko inherit (extends) karke desktop window container banati hai.
public class HotelGUI extends JFrame {

    // Main business logic engine reference.
    private Hotel hotel;


    // =========================
    // COLORS & THEME CONSTANTS
    // =========================

    // App ke aesthetic theme ke liye Color constants.
    private final Color BACKGROUND =
            new Color(245, 247, 250);

    private final Color PRIMARY =
            new Color(37, 99, 235);

    private final Color DARK =
            new Color(31, 41, 55);

    private final Color SUCCESS =
            new Color(22, 163, 74);

    private final Color DANGER =
            new Color(220, 38, 38);

    private final Color WHITE =
            Color.WHITE;


    // =========================
    // CONSTRUCTOR
    // =========================

    // Constructor: GUI Window setup karta hai, Layout & Components add karta hai.
    public HotelGUI(Hotel hotel) {

        this.hotel = hotel;

        // Window Title Set karna
        setTitle(
                "Hotel Room Booking System"
        );

        // Frame Size set karna (width=850px, height=600px)
        setSize(850, 600);

        setMinimumSize(
                new Dimension(750, 550)
        );

        // Window close button (X) click karne par app exit ho jaye
        setDefaultCloseOperation(
                JFrame.EXIT_ON_CLOSE
        );

        // Window ko screen ke center mein position karna
        setLocationRelativeTo(null);


        // =========================
        // MAIN PANEL
        // =========================

        // Top level container JPanel with BorderLayout manager
        JPanel mainPanel =
                new JPanel(
                        new BorderLayout()
                );

        mainPanel.setBackground(
                BACKGROUND
        );

        mainPanel.setOpaque(true);


        // =========================
        // HEADER PANEL
        // =========================

        JPanel headerPanel =
                new JPanel();

        headerPanel.setLayout(
                new BoxLayout(
                        headerPanel,
                        BoxLayout.Y_AXIS
                )
        );

        headerPanel.setBackground(DARK);

        headerPanel.setOpaque(true);

        headerPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        25,
                        20,
                        25,
                        20
                )
        );


        // Title Label
        JLabel titleLabel =
                new JLabel(
                        "HOTEL ROOM BOOKING SYSTEM"
                );

        titleLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        24
                )
        );

        titleLabel.setForeground(WHITE);

        titleLabel.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );


        // Subtitle Label
        JLabel subtitleLabel =
                new JLabel(
                        "Hotel Room & Booking Management"
                );

        subtitleLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        14
                )
        );

        subtitleLabel.setForeground(
                new Color(
                        156,
                        163,
                        175
                )
        );

        subtitleLabel.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );


        headerPanel.add(titleLabel);

        headerPanel.add(
                Box.createRigidArea(
                        new Dimension(0, 8)
                )
        );

        headerPanel.add(subtitleLabel);


        // =========================
        // BUTTONS GRID PANEL
        // =========================

        // 6 buttons ko 3 rows x 2 columns grid mein arrange karne ke liye GridLayout
        JPanel gridPanel =
                new JPanel(
                        new GridLayout(
                                3,
                                2,
                                20,
                                20
                        )
                );

        gridPanel.setBackground(
                BACKGROUND
        );

        gridPanel.setOpaque(true);

        gridPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        40,
                        40,
                        40,
                        40
                )
        );


        // Helper method call karke action buttons create karte hain
        JButton btnViewRooms =
                createButton(
                        "View Rooms",
                        PRIMARY
                );

        JButton btnSearchRoom =
                createButton(
                        "Search Room",
                        PRIMARY
                );

        JButton btnBookRoom =
                createButton(
                        "Book Room",
                        SUCCESS
                );

        JButton btnCancelBooking =
                createButton(
                        "Cancel Booking",
                        DANGER
                );

        JButton btnViewBookings =
                createButton(
                        "View Bookings",
                        PRIMARY
                );

        JButton btnExit =
                createButton(
                        "Exit",
                        DARK
                );


        // Grid panel mein buttons append karna
        gridPanel.add(btnViewRooms);

        gridPanel.add(btnSearchRoom);

        gridPanel.add(btnBookRoom);

        gridPanel.add(btnCancelBooking);

        gridPanel.add(btnViewBookings);

        gridPanel.add(btnExit);


        // =========================
        // BUTTON EVENT LISTENERS (Lambda Syntax)
        // =========================

        // Lambda expression e -> {...} se button click events bind karte hain
        btnViewRooms.addActionListener(
                e -> handleViewRooms()
        );

        btnSearchRoom.addActionListener(
                e -> handleSearchRoom()
        );

        btnBookRoom.addActionListener(
                e -> handleBookRoom()
        );

        btnCancelBooking.addActionListener(
                e -> handleCancelBooking()
        );

        btnViewBookings.addActionListener(
                e -> handleViewBookings()
        );

        btnExit.addActionListener(
                e -> System.exit(0)
        );


        // Header aur Grid panels ko Main Panel mein attach karna
        mainPanel.add(
                headerPanel,
                BorderLayout.NORTH
        );

        mainPanel.add(
                gridPanel,
                BorderLayout.CENTER
        );


        // Main panel ko JFrame mein add karke window visible karna
        add(mainPanel);

        setVisible(true);
    }


    // =========================
    // BUTTON CREATION HELPER
    // =========================

    // Styled JButton instantiating helper method.
    private JButton createButton(
            String text,
            Color bg) {

        JButton button =
                new JButton(text);

        button.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        15
                )
        );

        button.setBackground(bg);

        button.setForeground(WHITE);

        button.setFocusPainted(false);

        button.setOpaque(true);

        button.setBorderPainted(false);

        button.setCursor(
                new Cursor(
                        Cursor.HAND_CURSOR
                )
        );

        return button;
    }


    // =========================
    // VIEW ROOMS ACTION
    // =========================

    // View Rooms button click handler: JTextArea dialog popup mein rooms display karta hai.
    private void handleViewRooms() {

        JTextArea textArea =
                new JTextArea(
                        hotel.getAllRooms()
                );

        textArea.setEditable(false);

        textArea.setFont(
                new Font(
                        "Monospaced",
                        Font.PLAIN,
                        13
                )
        );


        JScrollPane scrollPane =
                new JScrollPane(textArea);

        scrollPane.setPreferredSize(
                new Dimension(500, 300)
        );


        JOptionPane.showMessageDialog(
                this,
                scrollPane,
                "All Hotel Rooms",
                JOptionPane.INFORMATION_MESSAGE
        );
    }


    // =========================
    // SEARCH ROOM ACTION
    // =========================

    // Search Room button click handler: Room number prompt, try-catch parsing aur HashMap lookup result dialog.
    private void handleSearchRoom() {

        String input =
                JOptionPane.showInputDialog(
                        this,
                        "Enter Room Number:",
                        "Search Room",
                        JOptionPane.QUESTION_MESSAGE
                );


        if (input == null || input.trim().isEmpty()) {

            return;
        }


        // try-catch block to handle invalid non-integer inputs (NumberFormatException)
        try {

            int roomNumber =
                    Integer.parseInt(
                            input.trim()
                    );

            // HashMap lookup call
            Room room =
                    hotel.searchRoom(
                            roomNumber
                    );


            if (room != null) {

                JOptionPane.showMessageDialog(
                        this,
                        room.toString(),
                        "Room Details",
                        JOptionPane.INFORMATION_MESSAGE
                );

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Room " +
                        roomNumber +
                        " not found.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }

        } catch (NumberFormatException e) {

            // User ne numeric value ki jagah text enter kiya to exception catch ho jayegi
            JOptionPane.showMessageDialog(
                    this,
                    "Invalid Room Number!",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }


    // =========================
    // BOOK ROOM ACTION
    // =========================

    // Complete Booking Form dialog flow with regex & date validation.
    private void handleBookRoom() {

        JTextField txtName =
                new JTextField();

        JTextField txtPhone =
                new JTextField();

        JTextField txtEmail =
                new JTextField();

        JTextField txtRoom =
                new JTextField();

        JTextField txtCheckIn =
                new JTextField();

        JTextField txtCheckOut =
                new JTextField();


        Object[] fields = {

                "Customer Name:",
                txtName,

                "Phone (10 digits):",
                txtPhone,

                "Email Address:",
                txtEmail,

                "Room Number:",
                txtRoom,

                "Check-in Date (DD-MM-YYYY):",
                txtCheckIn,

                "Check-out Date (DD-MM-YYYY):",
                txtCheckOut
        };


        int result =
                JOptionPane.showConfirmDialog(
                        this,
                        fields,
                        "Book a Room",
                        JOptionPane.OK_CANCEL_OPTION
                );


        if (result != JOptionPane.OK_OPTION) {

            return;
        }


        String name =
                txtName.getText().trim();

        String phone =
                txtPhone.getText().trim();

        String email =
                txtEmail.getText().trim();

        String roomStr =
                txtRoom.getText().trim();

        String checkInStr =
                txtCheckIn.getText().trim();

        String checkOutStr =
                txtCheckOut.getText().trim();


        // Empty fields validation
        if (name.isEmpty() ||
            phone.isEmpty() ||
            email.isEmpty() ||
            roomStr.isEmpty() ||
            checkInStr.isEmpty() ||
            checkOutStr.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "All fields are required!",
                    "Validation Error",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }


        // Regex validation for 10-digit phone
        if (!isValidPhone(phone)) {

            JOptionPane.showMessageDialog(
                    this,
                    "Phone number must be exactly 10 digits!",
                    "Validation Error",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }


        // Regex validation for email format
        if (!isValidEmail(email)) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter a valid email address!",
                    "Validation Error",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }


        // Java Date & Time API parsing with try-catch
        try {

            int roomNumber =
                    Integer.parseInt(
                            roomStr
                    );


            DateTimeFormatter formatter =
                    DateTimeFormatter.ofPattern(
                            "dd-MM-yyyy"
                    );


            LocalDate checkInDate =
                    LocalDate.parse(
                            checkInStr,
                            formatter
                    );

            LocalDate checkOutDate =
                    LocalDate.parse(
                            checkOutStr,
                            formatter
                    );


            // Logical Date validation: Check-out must be after check-in
            if (!checkOutDate.isAfter(checkInDate)) {

                JOptionPane.showMessageDialog(
                        this,
                        "Check-out date must be after check-in date!",
                        "Validation Error",
                        JOptionPane.WARNING_MESSAGE
                );

                return;
            }


            // ChronoUnit.DAYS se night duration compute karna
            long numberOfNights =
                    ChronoUnit.DAYS.between(
                            checkInDate,
                            checkOutDate
                    );


            // Customer instantiation
            int customerId =
                    hotel.getNextCustomerId();

            Customer customer =
                    new Customer(
                            customerId,
                            name,
                            phone,
                            email
                    );


            int bookingId =
                    hotel.getNextBookingId();


            // Execute booking
            boolean success =
                    hotel.bookRoom(
                            bookingId,
                            customer,
                            roomNumber,
                            checkInStr,
                            checkOutStr,
                            numberOfNights
                    );


            if (success) {

                JOptionPane.showMessageDialog(
                        this,
                        "Room booked successfully!\n" +
                        "Booking ID: " + bookingId + "\n" +
                        "Total Nights: " + numberOfNights,
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE
                );

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Room " + roomNumber + " is unavailable or does not exist!",
                        "Booking Failed",
                        JOptionPane.ERROR_MESSAGE
                );
            }

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Invalid Room Number format!",
                    "Input Error",
                    JOptionPane.ERROR_MESSAGE
            );

        } catch (DateTimeParseException e) {

            // Catch invalid date inputs (e.g. 2026/10/01 instead of 01-10-2026)
            JOptionPane.showMessageDialog(
                    this,
                    "Invalid Date format! Please use DD-MM-YYYY.",
                    "Input Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }


    // =========================
    // CANCEL BOOKING ACTION
    // =========================

    // Cancel Booking button click handler: Booking ID prompt with validation and result alert.
    private void handleCancelBooking() {

        String input =
                JOptionPane.showInputDialog(
                        this,
                        "Enter Booking ID to Cancel:",
                        "Cancel Booking",
                        JOptionPane.QUESTION_MESSAGE
                );


        if (input == null || input.trim().isEmpty()) {

            return;
        }


        try {

            int bookingId =
                    Integer.parseInt(
                            input.trim()
                    );


            boolean success =
                    hotel.cancelBooking(
                            bookingId
                    );


            if (success) {

                JOptionPane.showMessageDialog(
                        this,
                        "Booking " + bookingId + " cancelled successfully!",
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE
                );

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Booking ID " + bookingId + " not found!",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Invalid Booking ID format!",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }


    // =========================
    // VIEW BOOKINGS ACTION
    // =========================

    // View Bookings click handler: displays all active reservations in scrollable text dialog.
    private void handleViewBookings() {

        JTextArea textArea =
                new JTextArea(
                        hotel.getAllBookings()
                );

        textArea.setEditable(false);

        textArea.setFont(
                new Font(
                        "Monospaced",
                        Font.PLAIN,
                        13
                )
        );


        JScrollPane scrollPane =
                new JScrollPane(textArea);

        scrollPane.setPreferredSize(
                new Dimension(550, 350)
        );


        JOptionPane.showMessageDialog(
                this,
                scrollPane,
                "All Hotel Bookings",
                JOptionPane.INFORMATION_MESSAGE
        );
    }


    // =========================
    // REGEX VALIDATION HELPERS
    // =========================

    // Regex helper method: verifies string is exactly 10 numeric digits.
    private boolean isValidPhone(
            String phone) {

        return phone != null &&
               phone.matches("\\d{10}");
    }


    // Regex helper method: verifies string matches standard email pattern.
    private boolean isValidEmail(
            String email) {

        String emailRegex =
                "^[A-Za-z0-9+_.-]+@(.+)$";

        return email != null &&
               email.matches(emailRegex);
    }
}