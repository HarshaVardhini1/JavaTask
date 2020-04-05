import java.util.Random;
import java.util.Scanner;

public class Minesweeper {
    int[][] field;
    char[][] displayField;
    static boolean gameover=false;

    public Minesweeper(int rows,int columns,int mineCount){
        field=new int[rows][columns];
        displayField=new char[rows][columns];
        setMines(mineCount);
        calculateNeighborsValue();
        showDisplayField();
    }

    public void setMines(int mineCount){
        Random number = new Random();
        while(mineCount>0) {
            int row = number.nextInt(field.length);
            int col = number.nextInt(field[row].length);
            if (field[row][col] != -1) {
                field[row][col] = -1;
                mineCount--;
            }
        }

    }

    public void calculateNeighborsValue(){
        for(int i=0;i<field.length;i++){
            for(int j=0;j<field[i].length;j++){
                displayField[i][j]='-';
                if(field[i][j]==-1){
                    for (int di = -1; di <= 1; di++) {
                        for (int dj = -1; dj <= 1; dj++) {
                            if(i+di>=0 && i+di<field.length && j+dj>=0 && j+dj<field[i].length && field[i+di][j+dj]!=-1) {
                                field[i + di][j + dj]++;
                            }
                        }
                    }
                }
            }
        }
    }

    public void showDisplayField(){
        for(int i=0;i<displayField.length;i++){
            for(int j=0;j<displayField[i].length;j++){
                System.out.print(displayField[i][j]+" ");
            }
            System.out.println("");
        }
    }

    public void showEmptyNeighbor(int row,int column){
        for (int di = -1; di <= 1; di++) {
            for (int dj = -1; dj <= 1; dj++) {
                if (row + di >= 0 && row + di < field.length && column + dj >= 0 && column + dj < field[row].length) {
                    if ((displayField[row + di][column + dj] == '-' || displayField[row + di][column + dj] == 'F' )&& field[row + di][column + dj] != -1) {
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
        Scanner input=new Scanner(System.in);
        System.out.print("Enter the total number of rows : ");
        int rows=input.nextInt();
        System.out.print("Enter the total number of columns : ");
        int columns=input.nextInt();
        System.out.print("Enter the total number of mines : ");
        int mines=input.nextInt();
        Minesweeper game=new Minesweeper(rows,columns,mines);
        while (!gameover) {
            System.out.print("Enter the row : ");
            int row = input.nextInt()-1;
            System.out.print("Enter the column : ");
            int column = input.nextInt()-1;
            System.out.print("Do you want to flag or open ? (f/o) ");
            char action = input.next().charAt(0);
            game.checkUserMove(row, column, action);
        }
    }
}
