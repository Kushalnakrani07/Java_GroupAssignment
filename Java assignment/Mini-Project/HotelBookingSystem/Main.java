/*
===============================
File: Main.java

What is used:
- Class and Main Method (public static void main(String[] args))
- Object Instantiation (new Room, new Hotel, new HotelGUI)
- Array of Objects (Room[])
- Multithreading / Event Dispatch Thread (SwingUtilities.invokeLater)
- Lambda Expression (() -> new HotelGUI(hotel))

Purpose:
- Ye Java application ka main entry point hai.

Main responsibility:
- Hotel rooms array initialize karna, Hotel object instantiating karna aur Swing Event Dispatch Thread par GUI launch karna.
===============================
*/

// Main class jo application execution shuru karti hai.
public class Main {

    // Java Virtual Machine (JVM) dwara sabse pehle call hone wala main method.
    public static void main(String[] args) {

        // Room array banakar 6 pre-configured hotel rooms (Single, Double, Deluxe) initialize karte hain.
        Room[] rooms = {
            new Room(101, "Single", 1500.0),
            new Room(102, "Double", 2500.0),
            new Room(103, "Deluxe", 3500.0),
            new Room(104, "Single", 1500.0),
            new Room(105, "Double", 2500.0),
            new Room(106, "Deluxe", 3500.0)
        };

        // Hotel class ka object banate hain aur rooms array pass karte hain.
        Hotel hotel = new Hotel(rooms);

        // SwingUtilities.invokeLater lambda expression ke dwara GUI ko Swing Event Dispatch Thread (EDT) par safely launch karti hai.
        javax.swing.SwingUtilities.invokeLater(() -> new HotelGUI(hotel));
    }
}