import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class Sudoku {
    public static int[][] plain() {
        boolean didWin = false;
        //int[][] board = new int[9][9];
        Generator g = new Generator();
        int[][] board = g.generate(2);
        //int[][] board = loadGame("C:\\Users\\Mateusz\\Documents\\My Games\\1.txt");
        //rzędy, baza reszty
/*/
        //test funkcjonalności
        board[0] = new int[] {1, 0, 0, 0, 0, 0, 0, 1, 0};
        board[1] = new int[] {0, 0, 0, 0, 0, 0, 0, 2, 2};
        board[2] = new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0};
        board[3] = new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0};
        board[4] = new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0};
        board[5] = new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0};
        board[6] = new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0};
        board[7] = new int[] {0, 0, 0, 0, 0, 0, 0, 0, 2};
        board[8] = new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0};


        //test wygranej

        board[0] = new int[] {3 ,1, 6, 5, 7, 8, 4, 9, 2};
        board[1] = new int[] {5, 2, 9, 1, 3, 4, 7, 6, 8};
        board[2] = new int[] {4, 8, 7, 6, 2, 9, 5, 3, 1};
        board[3] = new int[] {2, 6, 3, 4, 1, 5, 9, 8, 7};
        board[4] = new int[] {9, 7, 4, 8, 6, 3, 1, 2, 5};
        board[5] = new int[] {8, 5, 1, 7, 9, 2, 6, 4, 3};
        board[6] = new int[] {1, 3, 8, 9, 4, 7, 2, 5, 6};
        board[7] = new int[] {6, 9, 2, 3, 5, 1, 8, 7, 4};
        board[8] = new int[] {7, 4, 5, 2, 8, 6, 3, 1, 0}; //wstawić 9 by wygrać
/*/
        printBoard(board);
        VolumeControl.soundtrack();
        new Panel(board);
        //Menu.GUIgenerator(board);

        while (!didWin) {
            saveGame("C:\\Users\\Mateusz\\Documents\\My Games\\1.txt", board);
            //kwadraty
            int[] square1 = merge(Arrays.copyOfRange(board[0], 0, 3), Arrays.copyOfRange(board[1], 0, 3), Arrays.copyOfRange(board[2], 0, 3));
            int[] square2 = merge(Arrays.copyOfRange(board[0], 3, 6), Arrays.copyOfRange(board[1], 3, 6), Arrays.copyOfRange(board[2], 3, 6));
            int[] square3 = merge(Arrays.copyOfRange(board[0], 6, 9), Arrays.copyOfRange(board[1], 6, 9), Arrays.copyOfRange(board[2], 6, 9));
            int[] square4 = merge(Arrays.copyOfRange(board[3], 0, 3), Arrays.copyOfRange(board[4], 0, 3), Arrays.copyOfRange(board[5], 0, 3));
            int[] square5 = merge(Arrays.copyOfRange(board[3], 3, 6), Arrays.copyOfRange(board[4], 3, 6), Arrays.copyOfRange(board[5], 3, 6));
            int[] square6 = merge(Arrays.copyOfRange(board[3], 6, 9), Arrays.copyOfRange(board[4], 6, 9), Arrays.copyOfRange(board[5], 6, 9));
            int[] square7 = merge(Arrays.copyOfRange(board[6], 0, 3), Arrays.copyOfRange(board[7], 0, 3), Arrays.copyOfRange(board[8], 0, 3));
            int[] square8 = merge(Arrays.copyOfRange(board[6], 3, 6), Arrays.copyOfRange(board[7], 3, 6), Arrays.copyOfRange(board[8], 3, 6));
            int[] square9 = merge(Arrays.copyOfRange(board[6], 6, 9), Arrays.copyOfRange(board[7], 6, 9), Arrays.copyOfRange(board[8], 6, 9));

            //kolumny
            int[] column1 = merge(Arrays.copyOfRange(board[0], 0, 1), Arrays.copyOfRange(board[1], 0, 1), Arrays.copyOfRange(board[2], 0, 1), Arrays.copyOfRange(board[3], 0, 1), Arrays.copyOfRange(board[4], 0, 1), Arrays.copyOfRange(board[5], 0, 1), Arrays.copyOfRange(board[6], 0, 1), Arrays.copyOfRange(board[7], 0, 1), Arrays.copyOfRange(board[8], 0, 1));
            int[] column2 = merge(Arrays.copyOfRange(board[0], 1, 2), Arrays.copyOfRange(board[1], 1, 2), Arrays.copyOfRange(board[2], 1, 2), Arrays.copyOfRange(board[3], 1, 2), Arrays.copyOfRange(board[4], 1, 2), Arrays.copyOfRange(board[5], 1, 2), Arrays.copyOfRange(board[6], 1, 2), Arrays.copyOfRange(board[7], 1, 2), Arrays.copyOfRange(board[8], 1, 2));
            int[] column3 = merge(Arrays.copyOfRange(board[0], 2, 3), Arrays.copyOfRange(board[1], 2, 3), Arrays.copyOfRange(board[2], 2, 3), Arrays.copyOfRange(board[3], 2, 3), Arrays.copyOfRange(board[4], 2, 3), Arrays.copyOfRange(board[5], 2, 3), Arrays.copyOfRange(board[6], 2, 3), Arrays.copyOfRange(board[7], 2, 3), Arrays.copyOfRange(board[8], 2, 3));
            int[] column4 = merge(Arrays.copyOfRange(board[0], 3, 4), Arrays.copyOfRange(board[1], 3, 4), Arrays.copyOfRange(board[2], 3, 4), Arrays.copyOfRange(board[3], 3, 4), Arrays.copyOfRange(board[4], 3, 4), Arrays.copyOfRange(board[5], 3, 4), Arrays.copyOfRange(board[6], 3, 4), Arrays.copyOfRange(board[7], 3, 4), Arrays.copyOfRange(board[8], 3, 4));
            int[] column5 = merge(Arrays.copyOfRange(board[0], 4, 5), Arrays.copyOfRange(board[1], 4, 5), Arrays.copyOfRange(board[2], 4, 5), Arrays.copyOfRange(board[3], 4, 5), Arrays.copyOfRange(board[4], 4, 5), Arrays.copyOfRange(board[5], 4, 5), Arrays.copyOfRange(board[6], 4, 5), Arrays.copyOfRange(board[7], 4, 5), Arrays.copyOfRange(board[8], 4, 5));
            int[] column6 = merge(Arrays.copyOfRange(board[0], 5, 6), Arrays.copyOfRange(board[1], 5, 6), Arrays.copyOfRange(board[2], 5, 6), Arrays.copyOfRange(board[3], 5, 6), Arrays.copyOfRange(board[4], 5, 6), Arrays.copyOfRange(board[5], 5, 6), Arrays.copyOfRange(board[6], 5, 6), Arrays.copyOfRange(board[7], 5, 6), Arrays.copyOfRange(board[8], 5, 6));
            int[] column7 = merge(Arrays.copyOfRange(board[0], 6, 7), Arrays.copyOfRange(board[1], 6, 7), Arrays.copyOfRange(board[2], 6, 7), Arrays.copyOfRange(board[3], 6, 7), Arrays.copyOfRange(board[4], 6, 7), Arrays.copyOfRange(board[5], 6, 7), Arrays.copyOfRange(board[6], 6, 7), Arrays.copyOfRange(board[7], 6, 7), Arrays.copyOfRange(board[8], 6, 7));
            int[] column8 = merge(Arrays.copyOfRange(board[0], 7, 8), Arrays.copyOfRange(board[1], 7, 8), Arrays.copyOfRange(board[2], 7, 8), Arrays.copyOfRange(board[3], 7, 8), Arrays.copyOfRange(board[4], 7, 8), Arrays.copyOfRange(board[5], 7, 8), Arrays.copyOfRange(board[6], 7, 8), Arrays.copyOfRange(board[7], 7, 8), Arrays.copyOfRange(board[8], 7, 8));
            int[] column9 = merge(Arrays.copyOfRange(board[0], 8, 9), Arrays.copyOfRange(board[1], 8, 9), Arrays.copyOfRange(board[2], 8, 9), Arrays.copyOfRange(board[3], 8, 9), Arrays.copyOfRange(board[4], 8, 9), Arrays.copyOfRange(board[5], 8, 9), Arrays.copyOfRange(board[6], 8, 9), Arrays.copyOfRange(board[7], 8, 9), Arrays.copyOfRange(board[8], 8, 9));
            if (noZero(board[0]) && noZero(board[1]) && noZero(board[2]) && noZero(board[3]) && noZero(board[4])
                    && noZero(board[5]) && noZero(board[6]) && noZero(board[7]) && noZero(board[8])
                    && !dup(square1) && !dup(square2) && !dup(square3) && !dup(square4) && !dup(square5) && !dup(square6)
                    && !dup(square7) && !dup(square8) && !dup(square9) && !dup(column1) && !dup(column2) && !dup(column3) && !dup(column4)
                    && !dup(column5) && !dup(column6) && !dup(column7) && !dup(column8) && !dup(column9) && !dup(board[0]) && !dup(board[1])
                    && !dup(board[2]) && !dup(board[3]) && !dup(board[4]) && !dup(board[5]) && !dup(board[6]) && !dup(board[7]) && !dup(board[8])) {
                System.out.println("\n\n wygrałeś");
                didWin = true;
            } else {
                //te elementy są używane narazie tylko poza gui
                System.out.println("\n\n");
                //rzędy
                if (dup(board[0])) {
                    System.out.println("tragedia zaszła w pierwszym rzędzie");
                }
                if (dup(board[1])) {
                    System.out.println("tragedia zaszła w drugim rzędzie");
                }
                if (dup(board[2])) {
                    System.out.println("tragedia zaszła w trzecim rzędzie");
                }
                if (dup(board[3])) {
                    System.out.println("tragedia zaszła w czwartym rzędzie");
                }
                if (dup(board[4])) {
                    System.out.println("tragedia zaszła w piątym rzędzie");
                }
                if (dup(board[5])) {
                    System.out.println("tragedia zaszła w szóstym rzędzie");
                }
                if (dup(board[6])) {
                    System.out.println("tragedia zaszła w siódmym rzędzie");
                }
                if (dup(board[7])) {
                    System.out.println("tragedia zaszła w ósmym rzędzie");
                }
                if (dup(board[8])) {
                    System.out.println("tragedia zaszła w dziewiątym rzędzie");
                }
                //kolumny
                if (dup(column1)) {
                    System.out.println("tragedia zaszła w pierwszej kolumnie");
                }
                if (dup(column2)) {
                    System.out.println("tragedia zaszła w drugiej kolumnie");
                }
                if (dup(column3)) {
                    System.out.println("tragedia zaszła w trzeciej kolumnie");
                }
                if (dup(column4)) {
                    System.out.println("tragedia zaszła w czwartej kolumnie");
                }
                if (dup(column5)) {
                    System.out.println("tragedia zaszła w piątej kolumnie");
                }
                if (dup(column6)) {
                    System.out.println("tragedia zaszła w szóstej kolumnie");
                }
                if (dup(column7)) {
                    System.out.println("tragedia zaszła w siódmej kolumnie");
                }
                if (dup(column8)) {
                    System.out.println("tragedia zaszła w ósmej kolumnie");
                }
                if (dup(column9)) {
                    System.out.println("tragedia zaszła w dziewiątej kolumnie");
                }
                //kwadraty
                if (dup(square1)) {
                    System.out.println("tragedia zaszła w pierwszym kwadracie");
                }
                if (dup(square2)) {
                    System.out.println("tragedia zaszła w drugim kwadracie");
                }
                if (dup(square3)) {
                    System.out.println("tragedia zaszła w trzecim kwadracie");
                }
                if (dup(square4)) {
                    System.out.println("tragedia zaszła w czwartym kwadracie");
                }
                if (dup(square5)) {
                    System.out.println("tragedia zaszła w piątym kwadracie");
                }
                if (dup(square6)) {
                    System.out.println("tragedia zaszła w szóstym kwadracie");
                }
                if (dup(square7)) {
                    System.out.println("tragedia zaszła w siódmym kwadracie");
                }
                if (dup(square8)) {
                    System.out.println("tragedia zaszła w ósmym kwadracie");
                }
                if (dup(square9)) {
                    System.out.println("tragedia zaszła w dziewiątym kwadracie");
                }


                state(board);

            }
        }
        return board;//póki co nieużywana, może być w przyszłości
    }


    public static int validScanner(Scanner scan) {
        while (!scan.hasNextInt()) {
            System.out.println("Dane wejściowe nie są liczbą");
            scan.nextLine();
        }
        return scan.nextInt();
    }

    public static void state(int[][] board) {
        int x = -1;
        int y = -1;
        int newone = -1;
        Scanner sc = new Scanner(System.in);
        System.out.println("\n\n");
        while (!(x <= 9 && x > 0)) {
            System.out.println("Enter x coordinate");

            x = validScanner(sc);
            if (!(x <= 9 && x > 0)) {
                System.out.println("Nieprawidłowe dane");
                System.out.println("Wpisz jeszcze raz");
            }
        }
        while (!(y <= 9 && y > 0)) {
            System.out.println("Enter y coordinate");
            y = validScanner(sc);
            if (!(y <= 9 && y > 0)) {
                System.out.println("Nieprawidłowe dane");
                System.out.println("Wpisz jeszcze raz");
            }
        }
        while (!(newone <= 9 && newone >= 0)) {
            System.out.println("Enter new number");
            newone = validScanner(sc);
            if (!(newone <= 9 && newone > 0)) {
                System.out.println("Nieprawidłowe dane");
                System.out.println("Wpisz jeszcze raz");
            }
        }

        board[y - 1][x - 1] = newone;
        printBoard(board);
        saveGame("D:\\savegame.txt", board);
    }

    public static void printBoard(int[][] sudoku) {
        for (int i = 0; i < 9; i++) {
            System.out.print("\n");
            if (i % 3 == 0)
                System.out.print("\n");
            for (int j = 0; j < 9; j++) {
                if (j % 3 == 0)
                    System.out.print(" ");
                if (sudoku[i][j] == 0)
                    System.out.print(". ");
                if (sudoku[i][j] == 1)
                    System.out.print("1 ");
                if (sudoku[i][j] == 2)
                    System.out.print("2 ");
                if (sudoku[i][j] == 3)
                    System.out.print("3 ");
                if (sudoku[i][j] == 4)
                    System.out.print("4 ");
                if (sudoku[i][j] == 5)
                    System.out.print("5 ");
                if (sudoku[i][j] == 6)
                    System.out.print("6 ");
                if (sudoku[i][j] == 7)
                    System.out.print("7 ");
                if (sudoku[i][j] == 8)
                    System.out.print("8 ");
                if (sudoku[i][j] == 9)
                    System.out.print("9 ");
            }
        }
    }

    public static int[] merge(int[]... intArrays) {
        return Arrays.stream(intArrays)
                .flatMapToInt(Arrays::stream)
                .toArray();
    }

    public static boolean noZero(int[] number) {
        boolean tru = true;
        for (int i = 0; tru && i < number.length; ++i) {
            if (number[i] == 0) {
                tru = false;
            }
        }
        return tru;
    }


    public static boolean dup(int[] number) {
        int i;
        boolean a = false;
        for (i = 0; i < number.length; i++) {
            int j;
            for (j = i + 1; j < number.length; j++) {
                if (number[j] != 0 && number[i] != 0) {
                    if (j != i && number[j] == number[i]) {
                        a = true;
                    }
                }
            }
        }
        return a;
    }

    public static void saveGame(String pathOfFile, int[][] board) {
        try {

            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board.length; j++) {
                    builder.append(board[i][j] + "");
                    if (j < board.length - 1)
                        builder.append(",");
                }
                builder.append("\n");
            }
            BufferedWriter writer = new BufferedWriter(new FileWriter(pathOfFile));
            writer.write(builder.toString());
            writer.close();
            System.out.println("\nStan gry został zapisany w:  " + pathOfFile);
        } catch (Exception ex) {
            System.out.println("\nNie ma takiej ścieżki:  " + pathOfFile);
            System.out.println(ex.getMessage());

        }
    }

    public static int[][] loadGame(String pathOfFile){
        String savedGameFile = pathOfFile;
        int[][] board = new int[9][9];
        try{
            BufferedReader br = new BufferedReader(new FileReader(pathOfFile));
            String lines = "";
            int row = 0;
            while ((lines = br.readLine()) != null){
                String[] columns = lines.split(",");
                int column = 0;
                for (String cos:columns
                ) {
                    board[row][column] = Integer.parseInt(cos);
                    column++;
                }
                row++;}
            br.close();
        }catch (Exception err){
            System.out.println("\nNie ma takiej ścieżki:  " + pathOfFile);
            System.out.println(err.getMessage());
        }
        return board;
    }


    public static void main(String[] args) {
        plain();

    }

}