import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu extends Sudoku {
    public static void GUIgenerator(int[][] board) {
        JFrame frame = new JFrame("MENU GLOWNE");
        JPanel panel = new JPanel();
        JButton button = new JButton("START");
        JButton load = new JButton("LOAD GAME");
        JButton exit = new JButton("EXIT");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setVisible(true);
        frame.add(panel);
        panel.add(exit);
        panel.add(button);
        panel.add(load);
        panel.setVisible(true);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent err) {
                new Panel(board);
            }

        });
        load.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                load.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        //int[][] board = new int[9][9];
                        int [][] data = Sudoku.loadGame("C:\\Users\\Mateusz\\Documents\\My Games\\1.txt");
                        new Panel(data);
                        frame.dispose();

                    }
                });
            }
        });
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent er) {
                frame.dispose();
            }
        });
    }



    public static void main(String[] args) {
/*/
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    GUIgenerator();
                }
                catch (Exception err){
                    System.out.println("Wystąpił błąd");
                    err.printStackTrace();
                }
            }
        });/*/
    }

}
