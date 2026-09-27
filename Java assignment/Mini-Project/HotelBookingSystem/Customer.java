/*
===============================
File: Customer.java

What is used:
- Class and Object
- Encapsulation (private fields, public getters)
- Constructor
- Method Overriding (@Override toString)

Purpose:
- Ye class customer (guest) ki identity aur contact details ko store aur represent karti hai.

Main responsibility:
- Customer ID, Name, Phone number aur Email address ko safely encapsulate aur store karna.
===============================
*/

// Customer class jo hotel mein room book karne wale guest ka record represent karti hai.
public class Customer {

    // Customer ka unique ID number store karne ke liye variable.
    private int customerId;

    // Customer ka full name store karne ke liye variable.
    private String name;

    // Customer ka 10-digit mobile number store karne ke liye variable.
    private String phone;

    // Customer ka valid email address store karne ke liye variable.
    private String email;

    // Constructor: Naya Customer object banate waqt saari customer information initialize karta hai.
    public Customer(int customerId, String name, String phone, String email) {
        this.customerId = customerId;
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

    // Getter method: Customer ID return karta hai.
    public int getCustomerId() {
        return customerId;
    }

    // Getter method: Customer name return karta hai.
    public String getName() {
        return name;
    }

    // Getter method: Customer phone number return karta hai.
    public String getPhone() {
        return phone;
    }

    // Getter method: Customer email return karta hai.
    public String getEmail() {
        return email;
    }

    // Method Overriding: Customer details ko single line formatted string ke roop mein return karta hai.
    @Override
    public String toString() {
        return "Customer ID: " + customerId +
                " | Name: " + name +
                " | Phone: " + phone +
                " | Email: " + email;
    }
}