import java.io.*;
import java.util.*;

class Contact {
    private String name;
    private String phoneNumber;
    private String email;

    public Contact(String name, String phoneNumber, String email) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Name: " + name + ", Phone: " + phoneNumber + ", Email: " + email;
    }
}

class ContactManager {
    private List<Contact> contacts;
    private static final String FILENAME = "contacts.txt";

    public ContactManager() {
        this.contacts = new ArrayList<>();
        loadContacts();
    }

    public void addContact(String name, String phoneNumber, String email) {
        Contact newContact = new Contact(name, phoneNumber, email);
        contacts.add(newContact);
        saveContacts();
    }

    public void viewContacts() {
        if (contacts.isEmpty()) {
            System.out.println("Contact list is empty.");
        } else {
            for (Contact contact : contacts) {
                System.out.println(contact);
            }
        }
    }

    public void editContact(int index, String name, String phoneNumber, String email) {
        if (index >= 0 && index < contacts.size()) {
            Contact editedContact = new Contact(name, phoneNumber, email);
            contacts.set(index, editedContact);
            saveContacts();
        } else {
            System.out.println("Invalid contact index.");
        }
    }

    public void deleteContact(int index) {
        if (index >= 0 && index < contacts.size()) {
            contacts.remove(index);
            saveContacts();
        } else {
            System.out.println("Invalid contact index.");
        }
    }

    private void saveContacts() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILENAME))) {
            oos.writeObject(contacts);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    private void loadContacts() {
        File file = new File(FILENAME);
        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILENAME))) {
                contacts = (List<Contact>) ois.readObject();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}

public class ContactManagerApp {
    private static final Scanner scanner = new Scanner(System.in);
    private static ContactManager contactManager = new ContactManager();

    public static void main(String[] args) {
        showMenu();
    }

    private static void showMenu() {
        int choice;
        do {
            System.out.println("\n===== Contact Manager Menu =====");
            System.out.println("1. Add New Contact");
            System.out.println("2. View All Contacts");
            System.out.println("3. Edit a Contact");
            System.out.println("4. Delete a Contact");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline after nextInt()

            switch (choice) {
                case 1:
                    addNewContact();
                    break;
                case 2:
                    viewAllContacts();
                    break;
                case 3:
                    editContact();
                    break;
                case 4:
                    deleteContact();
                    break;
                case 5:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 5.");
            }
        } while (choice != 5);

        scanner.close();
    }

    private static void addNewContact() {
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        System.out.print("Enter phone number: ");
        String phoneNumber = scanner.nextLine();
        System.out.print("Enter email address: ");
        String email = scanner.nextLine();

        contactManager.addContact(name, phoneNumber, email);
        System.out.println("Contact added successfully.");
    }

    private static void viewAllContacts() {
        System.out.println("\n===== All Contacts =====");
        contactManager.viewContacts();
    }

    private static void editContact() {
        System.out.print("Enter index of contact to edit (starting from 0): ");
        int index = scanner.nextInt();
        scanner.nextLine(); // Consume newline after nextInt()

        System.out.print("Enter new name: ");
        String name = scanner.nextLine();
        System.out.print("Enter new phone number: ");
        String phoneNumber = scanner.nextLine();
        System.out.print("Enter new email address: ");
        String email = scanner.nextLine();

        contactManager.editContact(index, name, phoneNumber, email);
        System.out.println("Contact edited successfully.");
    }

    private static void deleteContact() {
        System.out.print("Enter index of contact to delete (starting from 0): ");
        int index = scanner.nextInt();
        scanner.nextLine(); // Consume newline after nextInt()

        contactManager.deleteContact(index);
        System.out.println("Contact deleted successfully.");
    }
}
