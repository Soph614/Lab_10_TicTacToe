import java.util.Scanner;

public class Lab_10_TicTacToe {
    private static final int ROWS = 3;
    private static final int COLS = 3;
    private static String board [][] = new String[ROWS][COLS];
    public static void main(String[] args) {

        // INITIATING VARIABLES
        int row = 0;
        int col = 0;
        boolean tie = false;
        boolean done = false;
        boolean xWon = false;
        boolean oWon = false;
        Scanner pipe = new Scanner(System.in);

        // LOOP THAT CONTROLS PLAYING AGAIN
        do {
            int move = 0;
            clearBoard();
            boolean gameFinished = false;
            // LOOP THAT LOOKS FOR WINS AND TIES AND ENDS GAME ACCORDINGLY
            do {
                boolean valid = true;
                // LOOP THAT TESTS FOR A VALID MOVE
                do {
                    display();
                    // COLLECTING COORDINATES
                    // row
                    row = SafeInput.getRangedInt(pipe, "Enter what row you want your move in [1-3]",1, 3);;
                    row = row - 1;
                    // column
                    col = SafeInput.getRangedInt(pipe, "Enter what column you want your move in [1-3]", 1, 3);
                    col = col - 1;
                    pipe.nextLine();
                    // CHECK FOR VALIDITY:
                    valid = isValidMove(row, col);
                    if (!valid) {
                        System.out.println("That space has already been taken."); // letting the user know their error
                    }
                }while (!isValidMove(row, col));
                // INCREMENT MOVES
                move = move + 1;
                // ASSIGN X OR O ACCORDING TO MOVE NUMBER
                if(move == 1 | move == 3 | move == 5 | move == 7 | move == 9)
                {
                    board[row][col] = "X";
                    String player = "X";
                }
                if(move == 2 | move == 4 | move == 6 | move == 8) {
                    board[row][col] = "O";
                    String player = "O";
                }
                // TEST FOR WINS
                if(move >= 5) {
                    // (testing individually so that the computer can announce the winner)
                    xWon = isWin("X");
                    if(xWon) {
                        System.out.println("-------");
                        System.out.println("X wins!");
                        System.out.println("-------");
                        display();
                        gameFinished = true; // game loop ends
                    }
                    oWon = isWin("O");
                    if(oWon) {
                        System.out.println("-------");
                        System.out.println("O wins!");
                        System.out.println("-------");
                        display();
                        gameFinished = true; // game loop ends
                    }
                }
                // TEST FOR TIE
                if(move >= 7) {
                    tie = isTie();
                    if(tie) {
                        System.out.println("-----------");
                        System.out.println("It's a tie!"); // announcing the reason why the game is no longer prompting for input
                        System.out.println("-----------");
                        display(); // showing the board
                        gameFinished = true; // game loop ends
                    }
                }
                // TEST FOR 9 MOVES (FULL BOARD)
                // Unnecessary because all full boards will be ties.
                /* if(move == 9) {
                    if(tie) {
                        gameFinished = true;
                    }
                    else {
                        display();
                        System.out.println("--------------------------");
                        System.out.println("No more room on the board!");
                        System.out.println("--------------------------");
                        gameFinished = true; // game loop ends
                    }
                }
                 */
            }while(!gameFinished);
            done = SafeInput.getYNConfirm(pipe, "Press Y if you're done, N to keep playing");
        }while(!done);
    }

    // METHODS...
    private static void clearBoard() {

        for(int row = 0; row < ROWS; row++) {
            for(int col = 0; col < COLS; col++) {
                board[row][col] = " ";
            }
        }
    }

    private static void display() {
        System.out.println("# # # # #");
        System.out.println("# " + board[0][0] + "|" + board[0][1] + "|" + board[0][2] + " #");
        System.out.println("# " + board[1][0] + "|" + board[1][1] + "|" + board[1][2] + " #");
        System.out.println("# " + board[2][0] + "|" + board[2][1] + "|" + board[2][2] + " #");
        System.out.println("# # # # #");

    }
    private static boolean isValidMove(int row, int col) {
        boolean retVal = false;
        if(board[row][col].equals(" ")) // is it a space?
            retVal = true;
        return retVal;
    }

    private static boolean isWin(String player) {
        if(isColWin(player) || isRowWin(player) || isDiagonalWin(player)) {
            return true;
        }
        return false;
    }
    private static boolean isRowWin(String player) {
        for(int row = 0; row < ROWS; row++) {
            if(board[row][0].equals(player) && board[row][1].equals(player) && board[row][2].equals(player)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isColWin(String player) {
        for(int col = 0; col < ROWS; col++) {
            if(board[0][col].equals(player) && board[1][col].equals(player) && board[2][col].equals(player)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isDiagonalWin(String player) {
        for(int row = 0; row < ROWS; row++) {
            if(board[0][0].equals(player) && board[1][1].equals(player) && board[2][2].equals(player)) {
                return true;
            }
            if(board[2][0].equals(player) && board[1][1].equals(player) && board[0][2].equals(player)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isTie() {
        if(isColTie() && isRowTie() && isDiagonalTie()) {
            return true;
        }
        return false;
    }

    private static boolean isRowTie() {
        for(int row = 0; row < ROWS; row++) {
            if(board[row][0].equals("X") | board[row][1].equals("X") | board[row][2].equals("X") && board[row][0].equals("O") | board[row][1].equals("O") | board[row][2].equals("O")) {
                return true;
            }
        }
        return false;
    }

    private static boolean isColTie() {
        for(int col = 0; col < ROWS; col++) {
            if(board[0][col].equals("X") | board[1][col].equals("X") && board[2][col].equals("X") && board[0][col].equals("O") | board[1][col].equals("O") | board[2][col].equals("O")) {
                return true;
            }
        }
        return false;
    }

    private static boolean isDiagonalTie() {
        for(int row = 0; row < ROWS; row++) {
         // IF one diagonal vector eliminated.............................................................................................................................and the other eliminated as well
            if(board[0][0].equals("X") | board[1][1].equals("X") | board[2][2].equals("X") && board[0][0].equals("O") | board[1][1].equals("O") | board[2][2].equals("O") && board[2][0].equals("X") | board[1][1].equals("X") | board[0][2].equals("X") && board[2][0].equals("O") | board[1][1].equals("O") | board[0][2].equals("O"))
                         return true;
            }
        return false;
    }
}