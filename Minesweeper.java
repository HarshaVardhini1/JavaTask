import java.util.Random;
import java.util.Scanner;

public class Minesweeper {
    int[][] field;
    char[][] displayField;
    static boolean gameover=false;

    public Minesweeper(){
        Scanner input=new Scanner(System.in);
        System.out.print("Enter the total number of rows : ");
        int rows=input.nextInt();
        System.out.print("Enter the total number of columns : ");
        int columns=input.nextInt();
        System.out.print("Enter the total number of mines : ");
        int mines=input.nextInt();

        field=new int[rows][columns];
        displayField=new char[rows][columns];
        setMines(mines);
        calculateNeighborsValue();
        showDisplayField();
    }

    public void startGame(){
        while (!gameover) {
            Scanner input=new Scanner(System.in);
            System.out.print("Enter the row : ");
            int row = input.nextInt()-1;
            System.out.print("Enter the column : ");
            int column = input.nextInt()-1;
            System.out.print("Do you want to flag or open ? (f/o) ");
            char action = input.next().charAt(0);
            if(row>=0 && row<field.length && column>=0 && column<field[row].length) {
                checkUserMove(row, column, action);
            }
            else{
                System.out.println("Please enter a value between 1 to "+field.length+" for rows \n"+
                        "Please enter a value between 1 to "+field[row].length+" for columns \n"+
                        "Please enter 'f' to flag or remove flag and 'o' to open!!");
            }
        }
    }

    public void setMines(int mineCount){
        Random number = new Random();
        for(int i=0;i<field.length;i++){
            for(int j=0;j<field[i].length;j++){
                field[i][j]=0;
            }
        }
        for(int i=0;i<mineCount;i++) {
            int row = number.nextInt(field.length);
            int col = number.nextInt(field[row].length);
            if (field[row][col] != -1) {
                field[row][col] = -1;
            }
            else {
                if (row + 1 < field.length && field[row + 1][col] != -1) {
                    field[row + 1][col] = -1;
                }
                else {
                    if (col + 1 < field[row].length && field[row][col + 1] != -1) {
                        field[row][col + 1] = -1;
                    }
                    else {
                        if (row - 1 > 0 && field[row - 1][col] != -1) {
                            field[row - 1][col] = -1;
                        }
                        else {
                            if (col - 1 > 0 && field[row][col] != -1) {
                                field[row][col - 1] = -1;
                            }
                            else {
                                i--;
                            }
                        }
                    }
                }
            }
        }
    }

    public void calculateNeighborsValue(){
        for(int i=0;i<field.length;i++){
            for(int j=0;j<field[i].length;j++){
                displayField[i][j]='-';
                if(field[i][j]==-1){
                    if (i < field.length && j < field[i].length && i >= 1 && j >= 1 && field[i-1][j-1]!=-1) {
                        field[i-1][j-1]++;
                    }
                    if (i < field.length && j < field[i].length-1 && i >= 1 && j >= 0 && field[i-1][j+1]!=-1) {
                        field[i-1][j+1]++;
                    }
                    if (i < field.length-1 && j < field[i].length && i >= 0 && j >= 1 && field[i+1][j-1]!=-1 ) {
                        field[i+1][j-1]++;
                    }
                    if (i < field.length-1 && j < field[i].length-1 && i >= 0 && j >= 0 && field[i+1][j+1]!=-1 ) {
                        field[i+1][j+1]++;
                    }
                    if (i < field.length && j < field[i].length && i >= 1 && j >= 0 &&  field[i-1][j]!=-1) {
                        field[i-1][j]++;
                    }
                    if(i < field.length && j < field[i].length && i >= 0 && j >= 1 && field[i][j-1]!=-1) {
                        field[i][j-1]++;
                    }
                    if (i < field.length && j < field[i].length-1 && i >= 0 && j >= 0 && field[i][j+1]!=-1) {
                        field[i][j+1]++;
                    }
                    if (i < field.length-1 && j < field[i].length && i >= 0 && j >= 0 &&  field[i+1][j]!=-1) {
                        field[i+1][j]++;
                    }
                }
            }
        }
    }

    public void showDisplayField(){
        System.out.println("");
        for(int i=0;i<displayField.length;i++){
            for(int j=0;j<displayField[i].length;j++){
                System.out.print(displayField[i][j]+" \t");
            }
            System.out.println("\n");
        }
    }

    public void showEmptyNeighbor(int row,int column){
        for (int di = -1; di <= 1; di++) {
            for (int dj = -1; dj <= 1; dj++) {
                if (row + di >= 0 && row + di < field.length && column + dj >= 0 && column + dj < field[row].length) {
                    if ((displayField[row + di][column + dj] == '-' || displayField[row + di][column + dj] == 'F' )) {
                        displayField[row + di][column + dj] = Character.forDigit(field[row + di][column + dj], 10);
                        if (displayField[row + di][column + dj] == '0') {
                            showEmptyNeighbor(row + di, column + dj);
                        }
                    }
                }
            }
        }
    }

    public void checkUserMove(int row,int column,char action){
        if(action != 'f') {
            if (displayField[row][column] == '-') {
                if (field[row][column] == -1) {
                    gameover = true;
                    for (int i = 0; i < displayField.length; i++) {
                        for (int j = 0; j < displayField[i].length; j++) {
                            if (field[i][j] == -1) {
                                displayField[i][j] = '*';
                            }
                        }
                    }
                    System.out.println("GAME OVER!! YOU OPENED A MINE!");
                }
                else if (field[row][column] == 0) {
                    displayField[row][column] = Character.forDigit(field[row][column], 10);
                    showEmptyNeighbor(row, column);
                }
                else {
                    displayField[row][column] = Character.forDigit(field[row][column], 10);
                }
                checkIfWon();
            }
            else if (displayField[row][column] == 'F') {
                System.out.println("This square has already been flagged!");
            }
            else {
                System.out.println("This square has already been opened!");
            }
        }
        else {
            if(displayField[row][column]=='F'){
                displayField[row][column]='-';
            }
            else if(displayField[row][column]=='-'){
                displayField[row][column] = 'F';
            }
            else {
                System.out.println("This square has already been opened !");
            }
        }
        showDisplayField();
    }

    public void checkIfWon(){
        int count=0;
        for(int i=0;i<field.length;i++){
            for(int j=0;j<field[i].length;j++){
                if((displayField[i][j]=='-' || displayField[i][j]=='F') && field[i][j]!=-1){
                    count++;
                }
            }
        }
        if(count==0){
            gameover=true;
            System.out.println("You won the game!!");
        }
    }

    public static void main(String[] ar){
         Minesweeper game=new Minesweeper();
         game.startGame();
    }
}
