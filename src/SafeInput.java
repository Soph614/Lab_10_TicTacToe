import java.util.Scanner;

public class SafeInput {
    public static void main(String[] args) {
        Scanner pipe = new Scanner(System.in);
        /* boolean yesNo;
        yesNo = getYNConfirm(pipe, "Enter 'Y' or 'N'");
        System.out.println("Y = True, N = False, You entered " + yesNo + "\n");
         */
        String giveYourMNumber = getRegExString(pipe, "Give your UC Student M Number", "^(M|m)\\d{5}$");
    }
    public static String getNonZeroLenString(Scanner pipe, String prompt) {
        String retString = "";
        do {
            System.out.print("\n" + prompt + ": ");
            retString = pipe.nextLine();
        }while(retString.length() == 0);
        return retString;
    }
    public static int getInt(Scanner pipe, String prompt) {
        boolean done = false;
        do {
            prompt = "Please give an integer";
            System.out.print("\n" + prompt + ": ");
            if (pipe.hasNextInt()) {
                int userInt = pipe.nextInt();
                System.out.println("Your integer is " + userInt + ".");
                done = true;
            } else if (pipe.hasNextDouble()) {
                double doubleTrash = pipe.nextDouble();
                System.out.println(doubleTrash + " is not an integer.");
            } else {
                String trash = pipe.nextLine();
                System.out.println(trash + " is not an integer.");
            }
        } while (!done);
        return 0;
    }
    public static double getDouble(Scanner pipe, String prompt) {
        boolean done = false;
        prompt = "Please give a double value";
        do {
            System.out.print("\n" + prompt + ": ");
            if (pipe.hasNextInt()) {
                int intTrash = pipe.nextInt();
                System.out.println("[" + intTrash + "] is not a double value.");
                pipe.nextLine();
            }
            else if (pipe.hasNextDouble()) {
                double userDouble = pipe.nextDouble();
                System.out.println("Your double value is " + userDouble + ".");
                done = true;
            }
            else {
                String trash = pipe.nextLine();
                System.out.println("[" + trash + "] is not a double value.");
                // pipe.nextLine();
            }
        } while (!done);
        return 0;
    }
    public static int getRangedInt(Scanner pipe, String prompt, int low, int high) {
        boolean done = false;
        do {
            System.out.print("\n" + prompt + ": ");
            if (pipe.hasNextInt()) {
                int userInt = pipe.nextInt();
                if(userInt >= low && userInt <= high) {
                    done = true;
                }
                else {
                    System.out.println("That integer is not between " + low + " and " + high + " inclusive.");
                }

            }
            else if (pipe.hasNextDouble()) {
                double doubleTrash = pipe.nextDouble();
                System.out.println("'" + doubleTrash + "' is not an integer at all.");
                pipe.nextLine(); // clears the buffer
            }
            else {
                String trash = pipe.nextLine();
                System.out.println("'" + trash + "' is not an integer at all.");
                // pipe.nextLine();
            }

        } while(!done);

        return low;
    }
    public static double getRangedDouble(Scanner pipe, String prompt, double low, double high) {
        boolean done = false;
        do {
            System.out.print("\n" + prompt + ": ");
            if (pipe.hasNextInt()) {
                int trash = pipe.nextInt();
                System.out.println("[" + trash + "] is not a double value at all.");
                pipe.nextLine();

            }
            else if (pipe.hasNextDouble()) {
                double userDouble = pipe.nextDouble();
                if(userDouble >= 1 && userDouble <= 100) {
                    System.out.println("You chose " + userDouble + ".");
                    done = true;
                }
                else {
                    System.out.println("That double value is not between " + low + " and " + high + " inclusive.");
                }
            }
            else {
                String trash = pipe.nextLine();
                System.out.println("[" + trash + "] is not a double value at all.");
                // pipe.nextLine();
            }

        } while(!done);
        return low;
    }
    /* public static boolean getYNConfirm(Scanner pipe, String prompt) {
        boolean returnValue = true;
        boolean gotAValue = false;
        String response = "";
        do {
            System.out.print("\n" + prompt + ": ");
            response = pipe.nextLine();
            \\ if (!yesOrNo.equalsIgnoreCase("Y") && !yesOrNo.equalsIgnoreCase("N")) {
                System.out.println(yesOrNo = " is not Y or N.");
            } else if (yesOrNo.equalsIgnoreCase("N")) {
                done = false;
                System.out.println("Y = True, N = False, you entered " + done + ".");
            } else { // if user input is 'Y'
                done = true;
                System.out.println("Y = True, N = False, you entered " + done + ".");
            }
            //
            if(response.equalsIgnoreCase("Y")) {
                returnValue = true;
                System.out.println("Y = True, N = False, you entered " + returnValue + ".");
            }
            else if(response.equalsIgnoreCase("N")) {
                returnValue = false;
                System.out.println("Y = True, N = False, you entered " + returnValue + ".");
            }
            else if(response.length() == 0) {
            }
            else {
                System.out.println(response + " is not Y or N.");
            }
        } while (!gotAValue);
        return false;
    }
     */
    public static boolean getYNConfirm(Scanner pipe, String prompt) {
        boolean done = true;
        String response = "";
        boolean gotAVal = false;

        do {
            System.out.print("\n" + prompt + " [Y/N]: ");
            response = pipe.nextLine();
            if(response.equalsIgnoreCase("Y")) {
                gotAVal = true;
                done = true;
            }
            else if(response.equalsIgnoreCase("N")) {
                gotAVal = true;
                done = false;
            }
            else {
                System.out.println("You must answer [Y/N]! " + response);
            }
        } while(!gotAVal);

        return done;
    }
    public static String getRegExString(Scanner pipe, String prompt, String regEx) {
        String userNumber = "";
        boolean gotCorrectValue = false;
        // pipe.nextLine();
        do {
            System.out.println("\n" + prompt + ": ");
            userNumber = pipe.nextLine();
            if(userNumber.matches(regEx)) {
                gotCorrectValue = true;
            }
            else {
                System.out.println("[" + userNumber + "] is incorrect. Please match the pattern " + regEx);
                // pipe.nextLine();
            }
        } while(!gotCorrectValue);
        return userNumber;
    }
}

