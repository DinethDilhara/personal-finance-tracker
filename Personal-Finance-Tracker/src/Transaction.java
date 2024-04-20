import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.time.LocalDate;

public class Transaction {
    private int transactionId;
    private LocalDate date;
    private float amount;
    private String isIncomeOrExpenses ;
    private String category;
    public Transaction(){
        setAmount();
        setIsIncomeOrExpenses();
        setCategory();
        setDate();

    }
    public void setTransactionId(int Id){
        this.transactionId = Id;
    }
    public void setAmount(){
        Scanner input = new Scanner(System.in);
        boolean isValid = false;

        while (!isValid){
            try{
                System.out.print("Enter amount of money : ");
                float price = input.nextInt();

                if(price>0){
                    this.amount = price;
                    isValid=true;
                }else {
                    System.out.println("invalid input ");
                    input.nextLine();
                }
            }catch (InputMismatchException e){
                System.out.println("invalid dat type Integer required ");
                input.nextLine();
            }
        }

        input.close();
    }
    public void setIsIncomeOrExpenses() {
        Scanner input = new Scanner(System.in);
        String typeOfExpense = null;
        boolean isValid = false;
        
        while (!isValid){
            System.out.print("Is this transaction  Income or Expenses ? : ");
            typeOfExpense = input.nextLine().trim();
            
            if(typeOfExpense.equalsIgnoreCase("income") || typeOfExpense.equalsIgnoreCase("Expense")){
                isValid = true ;
            }else {
                System.out.println("invalid input");
            }
            
        }
        input.close();
        this.isIncomeOrExpenses = typeOfExpense;
    }
    public void setCategory(){
        Scanner input = new Scanner(System.in);

        System.out.print("What is the category of this transaction ? : ");
        this.category = input.nextLine();
        input.close();
    }
    public void setDate(){
        Scanner input = new Scanner(System.in);
        String option ;

        do{
            System.out.print("Do you want to add today as day \nEnter yes or no : ");
            option = input.nextLine().trim();
        }while (!option.equalsIgnoreCase("yes") && !option.equalsIgnoreCase("no"));

        switch (option){
            case "yes" -> this.date = LocalDate.now();

            case "no" -> {
                boolean validInput = false;

                while (!validInput) {
                    System.out.println("Please enter a date (YYYY-MM-DD): ");
                    String userInput = input.nextLine();

                    try {
                        this.date = LocalDate.parse(userInput);
                        validInput = true;
                    } catch (DateTimeParseException e) {
                        System.out.println("Invalid date format. Please enter date in YYYY-MM-DD format.");
                    }
                }
            }
        }
        input.close();
    }
    public int getTransactionId(){
        return this.transactionId;
    }
    public LocalDate getDate() {
        return date;
    }
    public float getAmount() {
        return amount;
    }
    public String getIsIncomeOrExpenses() {
        return isIncomeOrExpenses;
    }
    public String getCategory() {
        return category;
    }
    public void displayTransaction(){
        String amountWithTwoDecimals = String.format("%.2f", getAmount());

        System.out.printf("""
                          -----------------------
                          Transaction ID : %d
                          Amount : %s
                          Income or Expenses : %s
                          Category : %s
                          Date : %s
                          -----------------------
                          """,getTransactionId(),amountWithTwoDecimals,getIsIncomeOrExpenses(),getCategory(),getDate());
    }
}
