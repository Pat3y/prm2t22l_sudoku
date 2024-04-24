import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Klasa do tworzenia solverów do sudoku bazujących na algorytmie z nawrotami (backtracking).
 */
public class BacktrackingSolver {
    /**
     * Informacja o znalezioniu rozwiązania.
     */
    private boolean solutionFound = false;
    /**
     * Licznik znalezionych rozwiązań.
     */
    private int numberOfSolutions = 0;
    /**
     * Licznik używany do nadawania warstwom rekurencji identifikatorów.
     */
    private int counter = 0;

    /**
     * Metoda sprawdza czy podana wartość występuje już w danmy rzędie.
     * @param value wartość którą sprawdzamy
     * @param row indeks rzędu który sprawdzamy
     * @param board plansza nad którą działamy
     * @return true jeśli wartość już występuje, false w przeciwnym wypadku.
     */
    private boolean valueInRow(int value, int row, int[][] board) {
        for (int i = 0; i < 9; i++) {
            if (board[row][i] == value) {
                return true;
            }
        }
        return false;
    }

    /**
     * Metoda sprawdza czy podana wartość występuje już w danej kolumnie.
     * @param value wartość którą sprawdzamy
     * @param col indeks kolumny którą sprawdzamy
     * @param board plansza nad którą działamy
     * @return true jeśli wartość już występuje, false w przeciwnym wypadku.
     */
    private boolean valueInColumn(int value, int col, int[][] board) {
        for (int i = 0; i < 9; i++) {
            if (board[i][col] == value) {
                return true;
            }
        }
        return false;
    }

    /**
     * Metoda sprawdza czy podana wartość występuje już w danym kwadracie.
     * Kwadrat jest określany na podsatwie podanych indeksów rzędu i kolumny
     * @param value wartość którą sprawdzamy
     * @param row indeks rzędu który sprawdzamy
     * @param col indeks kolumny którą sprawdzmy
     * @param board plansza na której działamy
     * @return true jeśli wartość już występuje, false w przeciwnym wypadku.
     */
    private boolean valueInSquare(int value, int row, int col, int[][] board) {
        for (int i = row - row % 3; i < row - row % 3 + 3; i++) {
            for (int j = col - col % 3; j < col - col % 3 + 3; j++) {
                if (value == board[i][j]) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Metoda sprawdza czy w podane miejsce palnszy można wpisać podaną wartośc
     * @param val wartość którą chcemy wpisać
     * @param row numer rzędu do którego chcemy wpisać wartość
     * @param col numer kolumny do której chcemy wpisać wartość
     * @param board plansza na której działamy
     * @return true jeśli wartość może zostać wpisan, false w przeciwnym wypadku
     */
    private boolean canBeInserted(int val, int row, int col, int[][] board) {
        return !valueInSquare(val, row, col, board) && !valueInRow(val, row, board) && !valueInColumn(val, col, board);
    }

    /**
     * Metoda sprawdza czy plansz została już ukończona
     * @param board palnasza którą spawdzamy
     * @return true jeśli zotała ukończona, false w przeciwnym wypadku
     */
    private boolean isFinished(int[][] board) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == 0) {
                    return false;
                }
            }
        }
        numberOfSolutions++;
        solutionFound = true;
        return true;
    }

    /**
     * Metoda służąca do rozwiązywania podanej planszy licząc jej rozwiązaniae oraz do wypełnia losowo pustej planszy.
     * W trybie liczenia rozwiązań metoda zakońcy działnia przy znalezieniu drugiego rozwiązania
     * @param mode wybór trybu pracy
     * @param board plansza której liczbę rozwiazań checmy sprawdzić lub pusta plansza dla trybu drugiego
     * @return w przypadku pierwszego trybu true jeśli plansza ma jedno rozwiąznie, false jeśli rozwiązań nie ma lub jest więcej niż jedno. W trybie drugim zawsze true.
     */
    public boolean solve(int mode, int[][] board) {
        //mode 0 - rozwiązywanie, sprawdzanie czy rozwiąznie jest jednoznaczne
        //mode 1 - losowe wypełnianie
        int row = -1;
        int col = -1;
        int layerID = counter;
        boolean emptyCell = false;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == 0) {
                    row = i;
                    col = j;
                    emptyCell = true;
                    break;
                }

            }
            if (emptyCell) {
                break;
            }
        }
        if (isFinished(board)) {
            return true;
        }
        if (numberOfSolutions > 1) {
            return false;
        }
        if (mode == 0) {
            for (int val = 1; val < 10; val++) {
                if (canBeInserted(val, row, col, board)) {
                    board[row][col] = val;
                    counter++;
                    if (solve(0, board)) {
                        if (solutionFound) {
                            board[row][col] = 0;
                            solutionFound = false;
                            continue;
                        }
                        return true;
                    } else {
                        board[row][col] = 0;
                    }
                }
            }
        }
        if (mode == 1) {
            List<Integer> values = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));
            for (int i = 0; i < 9; i++) {
                Random rand = new Random();
                int random = rand.nextInt(values.size());
                int val = values.get(random);
                if (canBeInserted(val, row, col, board)) {
                    board[row][col] = val;
                    if (solve(1, board)) {
                        numberOfSolutions = 0;
                        counter = 0;
                        solutionFound = false;
                        return true;
                    } else {
                        board[row][col] = 0;
                    }
                }
                values.remove(random);
            }
        }
        if (layerID == 0 && mode == 0) {
            counter = 0;
            boolean result = numberOfSolutions == 1;
            numberOfSolutions = 0;
            return result;
        }
        return false;
    }


    public static void main(String[] args) {
        int[][] board = new int[9][];
        //dokładnie jedno rozwiąz
//        board[0] = new int[]{0, 0, 0, 7, 0, 0, 0, 0, 0};
//        board[1] = new int[]{1, 0, 0, 0, 0, 0, 0, 0, 0};
//        board[2] = new int[]{0, 0, 0, 4, 3, 0, 2, 0, 0};
//        board[3] = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 6};
//        board[4] = new int[]{0, 0, 0, 5, 0, 9, 0, 0, 0};
//        board[5] = new int[]{0, 0, 0, 0, 0, 0, 4, 1, 8};
//        board[6] = new int[]{0, 0, 0, 0, 8, 1, 0, 0, 0};
//        board[7] = new int[]{0, 0, 2, 0, 0, 0, 0, 5, 0};
//        board[8] = new int[]{0, 4, 0, 0, 0, 0, 3, 0, 0};
        board[0] = new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0};
        board[1] = new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0};
        board[2] = new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0};
        board[3] = new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0};
        board[4] = new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0};
        board[5] = new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0};
        board[6] = new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0};
        board[7] = new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0};
        board[8] = new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0};
        BacktrackingSolver x = new BacktrackingSolver();
        System.out.println(x.solve(0, board));
    }
}

