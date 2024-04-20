import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    static ArrayList<Transaction> transactions = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println("""
                      ----------------------------------------
                        Welcome to Personal Finance Tracker
                      ----------------------------------------
                """);
        displayMenu();
    }
    public static void displayMenu(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("""
                ************************************
                *               Menu               *
                ************************************
                 01. Add Transaction
                 02. View Transaction
                 03. Update Transaction
                 04. Delete Transaction
                 05. Exit
                """);

        int menuOption = 0;
        boolean isValidMenuOption = false;

        while (!isValidMenuOption){
            try {
                System.out.print("Enter option from menu : ");
                menuOption = scanner.nextInt();

                if(menuOption > 0 && menuOption <= 5){
                    isValidMenuOption = true;
                }

            }catch (InputMismatchException e){
                System.out.println("Invalid input");
                scanner.nextLine();
            }
        }

        switch (menuOption){
            case 1 -> addTransaction();
            case 2 -> viewTransaction();
            case 3 -> updateTransaction();
            case 4 -> deleteTransaction();
            case 5 -> { return;}
        }

        displayMenu();
    }
    public static void addTransaction(){
        Transaction transaction = new Transaction();
        transaction.setTransactionId(transactions.size() + 1);
        transaction.displayTransaction();
        transactions.add(transaction);
    }
    public static void viewTransaction(){
        for (Transaction i : transactions){
            i.displayTransaction();
        }
    }
    public static void updateTransaction(){
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the ID of the transaction you want to update: ");
        int idToUpdate = scanner.nextInt();
        scanner.nextLine();

        for (Transaction transaction : transactions) {
            if (transaction.getTransactionId() == idToUpdate) {
                transaction.displayTransaction();

                transaction = new Transaction();
                transaction.setTransactionId(idToUpdate);

                System.out.println("Updated transaction ");

                transaction.displayTransaction();

                return;
            }
        }
        System.out.println("Transaction with ID " + idToUpdate + " not found.");
    }
    public static void deleteTransaction(){
        Scanner scanner = new Scanner(System.in);
        int idToDelete = 0;

        try {
            System.out.print("Enter the ID of the transaction you want to delete: ");
            idToDelete = scanner.nextInt();
            scanner.nextLine();

        } catch (InputMismatchException e) {
            System.out.println("Integer required");
        }

        boolean found = false;
        for (int i = 0; i < transactions.size(); i++) {
            if (transactions.get(i).getTransactionId() == idToDelete) {
                transactions.remove(i);
                updateIds(i);
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Transaction with ID " + idToDelete + " not found.");
        }
    }
    public static void updateIds(int startIndex){
        for (int i = startIndex; i < transactions.size(); i++) {
            transactions.get(i).setTransactionId(i + 1);
        }
    }
}