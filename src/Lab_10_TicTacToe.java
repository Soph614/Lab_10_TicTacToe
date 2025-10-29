import java.util.Scanner;

public class Lab_10_TicTacToe {
    private static final int ROWS = 3;
    private static final int COLS = 3;
    private static String board [][] = new String[ROWS][COLS];
    public static void main(String[] args) {
        /*
        Clear the board and set the player to X (since X always moves first)
        clearBoard();

        get the coordinates for the move which should be 1 – 3 for the row and col

        convert the player move coordinates to the array indices which are 0 – 2 by subtracting 1

        loop until the converted player coordinates are a valid move which is an empty space on the board

        record the valid move on the board
        display();
        increment the move counter
        move = move++

         */

        // INITIATING VARIABLES
        int row;
        int col;
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
                    SafeInput.getRangedInt(pipe, "Enter what row you want your move in [1-3]",1, 3);
                    row = pipe.nextInt();
                    row = row - 1;
                    SafeInput.getRangedInt(pipe, "Enter what column you want your move in [1-3]", 1, 3);
                    col = pipe.nextInt();
                    col = col - 1;
                    valid = isValidMove(row, col);
                    if (!valid) {
                        System.out.println("That space has already been taken.");
                        System.out.println(); // making the spacing nice
                    }
                }while (!isValidMove(row, col));
                move = move + 1;
                if(move == 1 | move == 3 | move == 5 | move == 7 | move == 9)
                {
                    board[row][col] = "X";
                    String player = "X";
                }
                if(move == 2 | move == 4 | move == 6 | move == 8) {
                    board[row][col] = "O";
                    String player = "O";
                }
                if(move >= 5) {
                    xWon = isWin("X");
                    if(xWon) {
                        System.out.println("X wins!");
                        display();
                        gameFinished = true;
                    }
                    oWon = isWin("O");
                    if(oWon) {
                        System.out.println("O wins!");
                        display();
                        gameFinished = true;
                    }
                }
                if(move == 9) {
                    display();
                    System.out.println("No more room on the board!");
                    gameFinished = true;
                }
            }while(!gameFinished);
            done = SafeInput.getYNConfirm(pipe, "Press Y if you're done, N if not (you want to keep playing)");
        }while(!done);
    }

    private static void clearBoard() {

        for(int row = 0; row < ROWS; row++) {
            for(int col = 0; col < COLS; col++) {
                board[row][col] = " ";
            }
        }
    }

    private static void display() {
        System.out.println(board[0][0] + "|" + board[0][1] + "|" + board[0][2]);
        System.out.println(board[1][0] + "|" + board[1][1] + "|" + board[1][2]);
        System.out.println(board[2][0] + "|" + board[2][1] + "|" + board[2][2]);

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



}