# Hotel Room Booking System

A desktop GUI-based Hotel Management Application built with Java Swing, demonstrating core Object-Oriented Programming (OOP) principles, Java Collections Framework, Exception Handling, and the Java Date/Time API.

---

## 🏛️ Academic Details

- **University:** ITM SKILL UNIVERSITY
- **Program:** B.Tech CSE
- **Batch:** 2025–2029
- **Cohort:** Jeff Bezos
- **Project Title:** Hotel Room Booking System

---

## 👥 Team Members

| Sr. No. | Name | Roll No. | Batch | Cohort |
| :---: | :--- | :---: | :---: | :---: |
| 1 | **Om Maurya** | 150096725208 | B.Tech CSE 2025–2029 | Jeff Bezos |
| 2 | **Om Sawant** | 150096725194 | B.Tech CSE 2025–2029 | Jeff Bezos |
| 3 | **Prince Singh** | 150096725168 | B.Tech CSE 2025–2029 | Jeff Bezos |
| 4 | **Kushal Nakrani** | 150096725176 | B.Tech CSE 2025–2029 | Jeff Bezos |
| 5 | **Vyom Shah** | 150096725053 | B.Tech CSE 2025–2029 | Jeff Bezos |

---

## 🚀 Features & Functionalities

1. **View All Rooms:** Displays available and occupied rooms with room type (Single, Double, Deluxe), price per night, and current booking status.
2. **Search Room:** Perform instant $O(1)$ search by room number using `HashMap`.
3. **Book a Room:** Interactive booking flow with customer details (ID, Name, Phone, Email) and check-in / check-out dates (`DD-MM-YYYY`). Automatically calculates total nights and total bill amount, and appends the booking details persistently to `bookings.txt`.
4. **Cancel Booking:** Instant cancellation using booking ID. Automatically restores room availability status and updates `bookings.txt`.
5. **View All Bookings:** Displays comprehensive booking records saved persistently in `bookings.txt`.
6. **Persistent File Storage:** Automatic creation and management of `bookings.txt` using Java File I/O (`FileWriter` in append mode, `BufferedReader`/`FileReader`). Restores booking state and room availability across application restarts.
7. **Input Validation & Exception Handling:** Robust validation for 10-digit phone numbers, email regex, date formatting, logical date checks, and proper File I/O exception handling.

---

## 💻 Tech Stack & Concepts Used

- **Language:** Java (JDK 8+)
- **GUI Framework:** Java Swing (`JFrame`, `JPanel`, `JButton`, `JOptionPane`, `JTextArea`)
- **File I/O & Persistence:** Java File I/O (`File`, `FileWriter`, `FileReader`, `BufferedWriter`, `BufferedReader`, `PrintWriter`) storing data persistently in `bookings.txt`
- **Data Structures / Collections:** `LinkedList<Booking>`, `HashMap<Integer, Room>`, `TreeMap<Integer, Room>`
- **Date & Time API:** `LocalDate`, `DateTimeFormatter`, `ChronoUnit.DAYS`
- **OOP Concepts:** Encapsulation, Abstraction, Composition, Method Overriding (`toString()`)

---

## 📁 Submission File Structure

```
HotelBookingSystem/
├── Booking.java              # Java model for booking transactions
├── BookingFileManager.java   # File I/O helper class for persistent storage in bookings.txt
├── Customer.java             # Java model for customer profiles
├── Hotel.java                # Core hotel data management & business logic
├── HotelGUI.java             # Swing graphical user interface
├── Main.java                 # Main application entry point
├── Room.java                 # Java model for hotel rooms
├── bookings.txt              # Persistent file storage for all active bookings
├── Project_Documentation.docx # Complete editable Word documentation report (python-docx)
├── Project_Presentation.pptx # 7-slide editable PowerPoint presentation (python-pptx)
└── README.md                 # Project documentation and guide
```

---

## 🛠️ How to Compile and Run

### Prerequisites
- JDK 8 or higher installed on your system.

### Compilation
Open terminal or command prompt in the `HotelBookingSystem` folder and run:
```bash
javac *.java
```

### Execution
Run the application via `Main`:
```bash
java Main
```

---

## 📄 Submission Materials

- **`Project_Documentation.docx`**: Full college project report in Microsoft Word format (.docx) including cover page, team table, feature responsibilities, abstract, concepts, implementation, workflow, outputs, test cases, and references.
- **`Project_Presentation.pptx`**: 7-slide editable PowerPoint presentation formatted according to academic guidelines.
