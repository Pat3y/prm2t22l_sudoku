import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Klasa do tworzenie generatrów sudoku
 */
public class Generator {
    /**
     * Plansza która będzie modyfikowana w trakcie pracy generatora
     */
    private int[][] board = new int[9][];
    /**
     * Solver użwywany do wypełnia pustej planszy oraz sprawdznia czy istnieje tylko jedno rozwiązanie.
     */
    private final BacktrackingSolver solver = new BacktrackingSolver();

    /**
     * Metoda do wypełnia pustej planszy.
     */
    private void fillIn() {
        int[][] board = new int[9][];
        board[0] = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
        board[1] = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
        board[2] = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
        board[3] = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
        board[4] = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
        board[5] = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
        board[6] = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
        board[7] = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
        board[8] = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
        solver.solve(1, board);
        this.board = board;
    }

    /**
     * Metoda do usuwania określonej liczby pozycji wybieranych losowo
     * @param ile liczba pozycji do usunięcia
     */
    private void remove(int ile) {
        int actuallyRemoved = 0;
        while (actuallyRemoved != ile){
            actuallyRemoved = 0;
            fillIn();
            Random rand = new Random();
            List<Integer> positions = new ArrayList<>();
            for (int i = 0; i < 81; i++) {
                positions.add(i);
            }
            for (int i = 0; i < ile; i++) {
                int position = rand.nextInt(positions.size());
                int random = positions.get(position);
                int row = (random - random % 9) / 9;
                int col = random % 9;
                int valueToBeDeleted = board[row][col];
                board[row][col] = 0;
                actuallyRemoved++;
                if (!solver.solve(0, board)) {
                    board[row][col] = valueToBeDeleted;
                    i--;
                    actuallyRemoved--;
                }
                positions.remove(position);
                if (positions.size() == 0) {
                    break;
                }
            }
        }

    }

    /**
     * Metodo generująca planszę.
     * @param difficultyLevel określenie poziomu trudności
     * @return wygenerowana plansza
     */
    public int[][] generate(int difficultyLevel) {
        /*
        poziom trudności:
        1 - łatwy
        2 - średni
        3 - trudny
         */
        int toRemove;
        if (difficultyLevel == 1){
            toRemove = 35;
        }
        else if (difficultyLevel == 2){
            toRemove = 45;
        }
        else {
            toRemove = 58;
        }
        remove(toRemove);
        return board;
    }
    // metoda używana tylko dla testów
    public void printBoard() {
        for (int i = 0; i < 9; i++) {
            System.out.print("\n");
            if (i % 3 == 0)
                System.out.print("\n");
            for (int j = 0; j < 9; j++) {
                if (j % 3 == 0)
                    System.out.print(" ");
                if (board[i][j] == 0)
                    System.out.print(". ");
                if (board[i][j] == 1)
                    System.out.print("1 ");
                if (board[i][j] == 2)
                    System.out.print("2 ");
                if (board[i][j] == 3)
                    System.out.print("3 ");
                if (board[i][j] == 4)
                    System.out.print("4 ");
                if (board[i][j] == 5)
                    System.out.print("5 ");
                if (board[i][j] == 6)
                    System.out.print("6 ");
                if (board[i][j] == 7)
                    System.out.print("7 ");
                if (board[i][j] == 8)
                    System.out.print("8 ");
                if (board[i][j] == 9)
                    System.out.print("9 ");
            }
        }
    }


    public static void main(String[] args) {
        Generator generator = new Generator();
        generator.generate(3);
        generator.printBoard();
    }
}
