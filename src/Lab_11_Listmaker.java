import java.util.ArrayList;
import java.util.Scanner;

public class Lab_11_Listmaker {
    // Class-level Declaration
    private static ArrayList<String> userList = new ArrayList<>();

    /**
     * Main method.
     * @param args command line arguments.
     */
    public static void main(String[] args) {
        // Declarations
        Scanner in = new Scanner(System.in);
        String ADD = "A";
        String DELETE = "D";
        String INSERT = "I";
        String PRINT = "P";
        String userOption = "";
        boolean runProgram = true;

        //IPO
        while(runProgram) {
            displayList();
            System.out.printf("A - Add an item to the list.%nD - Delete an item from the list.%nI - Insert an item into the list.%nP - Print the list.%nQ - Quit the program.%n");
            userOption = SafeInput.getRegExString(in, "Please select an option by entering the letter", "^[AaDdIiPpQq]$").trim();
            System.out.println();
            if(userOption.equalsIgnoreCase(ADD)) {
                addToList(in);
            } else if(userOption.equalsIgnoreCase(DELETE)) {
                deleteFromList(in);
            } else if(userOption.equalsIgnoreCase(INSERT)) {
                insertIntoList(in);
            } else if(userOption.equalsIgnoreCase(PRINT)) {
                System.out.println(userList);
                System.out.println();
            } else {
                runProgram = quitProgram(in);
            }
        }
        in.close();
    }

    /**
     * Function to print the list.
     */
    private static void displayList() {
        if(userList.isEmpty()) {
            System.out.println("The list is empty.");
        } else {
            for(int i = 0; i < userList.size(); i++) {
                System.out.printf("Item %d - %s%n", i + 1, userList.get(i));
            }
        }
        System.out.println();
    }

    /**
     * Function to add items to list.
     * @param pipe a Scanner opened to read from System.in.
     */
    private static void addToList(Scanner pipe) {
        displayList();
        String item = SafeInput.getNonZeroLenString(pipe, "Enter a string item you'd like to add to the list");
        userList.add(item);
        System.out.printf("The item %s has been added.%n", item);
        System.out.println();
    }

    /**
     * Function to delete items in list.
     * @param pipe a Scanner opened to read from System.in.
     */
    private static void deleteFromList(Scanner pipe) {
        displayList();
        int index = SafeInput.getRangedInt(pipe, "Please enter the number of the item you'd like to delete", 1, userList.size()) - 1;
        userList.remove(index);
        System.out.printf("Item %d has been deleted.%n", index + 1);
        System.out.println();
    }

    /**
     * Function to insert items into a list.
     * @param pipe a Scanner opened to read from System.in.
     */
    private static void insertIntoList(Scanner pipe) {
        displayList();
        int index = SafeInput.getRangedInt(pipe, "Please enter the index where you want to add an item", 0, userList.size());
        String item = SafeInput.getNonZeroLenString(pipe, "Enter the string item you'd like to add to the list");
        userList.add(index, item);
        System.out.printf("The item %s has been inserted into the list at index %d.%n", item, index);
        System.out.println();
    }

    /**
     * Function to quit the program
     * @param pipe a Scanner opened to read from System.in.
     */
    private static boolean quitProgram(Scanner pipe) {
        return !(SafeInput.getYNConfirm(pipe, "Are you sure you want to quit the program?"));
    }
}
