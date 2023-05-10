import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner read = new Scanner(System.in);
        ArrayList<Part> database = new ArrayList<Part>();
        //the database/ArrayList imports data from the file if it exists
        database = FileIO.fileGet(database);
        int choice = 0;

        System.out.println("Welcome to the Car Part Database!");
        System.out.println("Please Note that the Database is Only Changed When the Application is Properly Exited");
        System.out.println("Using the Built in Choice in the Menu Provided\n");

        while (choice != 6) {

            System.out.println("Select an option:");
            System.out.println("1: Add a Car Part to the Database");
            System.out.println("2: Search for a Car Part");
            System.out.println("3: Display the Entire Database");
            System.out.println("4: Update a Part in the Database");
            System.out.println("5: Remove a Car Part from the Database");
            System.out.println("6: Save and Quit Application");
            System.out.print("Enter your choice (1-6): ");

            choice = read.nextInt();
            read.nextLine();

            switch (choice) {
                case 1:
                    database = addCarPart(database, read);
                    break;
                case 2:
                    searchForCarPart(database, read);
                    break;
                case 3:
                    displayDatabase(database);
                    break;
                case 4:
                    database = updateCarPart(database, read);
                    break;
                case 5:
                    database = removeCarPart(database, read);
                    break;
                case 6:
                    System.out.println("Exiting application...");
                    // database/arraylist data is overwritten to the file
                    // (file is created as well if not present)
                    FileIO.filePrint(database);
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 6.");
                    break;
            }
        }

        read.close();
    }

    private static int globalSearch(ArrayList<Part> database, String part){
        //the following loop searches the database for the part searched,
        // and returns the position of the containing Part Class
        for(int x = 0; x < database.size(); x++){
            if(database.get(x).getPart().compareTo(part) == 0){
                return x;
            }
        }
        //-10 is used as an identifier to indicate when the program can't find the object
        return -10;
    }

    private static ArrayList addCarPart(ArrayList<Part> database, Scanner read) {
        String part;
        int quantity;
        double price;

        System.out.println("Enter the car part name: ");
        part = read.nextLine();

        System.out.println("Enter the quantity of the part: ");
        quantity = read.nextInt();

        System.out.println("Enter the price: ");
        price = read.nextDouble();
        //creates a new Part class at the end of the arraylist/database with the data
        //added by the user
        database.add(new Part(part, quantity, price));

        return database;
    }

    private static void searchForCarPart(ArrayList<Part> database, Scanner read) {
        System.out.println("Please enter the name of the part: ");
        String part = read.nextLine();
        //gets the index of the object in the database/arraylist
        int x = globalSearch(database, part);
        //if(no object found)
        if(x == -10){
            System.out.println("Entered Part Could Not Be Found");
        } else{
            //prints object to user
            database.get(x).Print();
        }
    }

    private static void displayDatabase(ArrayList<Part> database) {
        //prints database/arraylist one object at a time to the user
        for(int x = 0; x < database.size(); x++){
            database.get(x).Print();
        }
    }

    private static ArrayList updateCarPart(ArrayList database, Scanner read) {
        System.out.println("Please enter the name of the part: ");
        String part = read.nextLine();
        //gets the index of the object in the database/arraylist
        int x = globalSearch(database, part);
        //if(no object found)
        if(x == -10){
            System.out.println("Entered Part Could Not Be Found");
        } else{
            System.out.println("Enter the quantity of the part: ");
            int quantity = read.nextInt();

            System.out.println("Enter the price: ");
            double price = read.nextDouble();

            database.set(x, new Part(part, quantity, price));
        }
        return database;
    }

    private static ArrayList removeCarPart(ArrayList<Part> database, Scanner read) {
        System.out.println("Please enter the name of the part: ");
        String part = read.nextLine();
        //gets the index of the object in the database/arraylist
        int x = globalSearch(database, part);
        //if(no object found)
        if(x == -10){
            System.out.println("Entered Part Could Not Be Found");
        } else{
            database.remove(x);
            System.out.println("Part Removed From Database");
        }
        return database;
    }
}