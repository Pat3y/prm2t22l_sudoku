import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.undo.UndoManager;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Panel implements ActionListener {
    //int[][] board = new int[9][];//dla ręcznych testów
    String loc1= "resources\\1.txt";
    String locc1="resources\\1config.txt";
    String loc2= "resources\\2.txt";
    String locc2="resources\\2config.txt";
    String loc3= "resources\\3.txt";
    String locc3="resources\\3config.txt";
    JMenuBar menuBar = new JMenuBar();
    JButton saveGame1 = new JButton("Save Game 1");
    JButton saveGame2 = new JButton("Save Game 2");
    JButton saveGame3 = new JButton("Save Game 3");
    JButton submit = new JButton("Submit");
    JButton undo = new JButton("Undo");
    JButton sendVolume = new JButton("Set Soundtrack Volume");
    JTextField vol = new JTextField();
    JMenu file = new JMenu("Game");
    JMenu newGame = new JMenu("New Game");
    JMenu saveGame = new JMenu("Save Game");
    JMenu loadGame = new JMenu("Load Game");
    JButton loadGame1 = new JButton("Load Game 1");
    JButton loadGame2 = new JButton("Load Game 2");
    JButton loadGame3 = new JButton("Load Game 3");
    JButton easyLevel = new JButton("Easy      ");
    JButton mediumLevel = new JButton("Medium");
    JButton hardLevel = new JButton("Hard      ");
    Generator g = new Generator();
    final int[][] board = g.generate(2);
    UndoManager manager = new UndoManager();
    JFrame frame = new JFrame();
    JLabel winOrNot = new JLabel("");
    final JPanel GuiPanel = new JPanel(new GridLayout(9, 9));
    final JTextField[][] subPanels = new JTextField[9][9];
    final JPanel errorPanel1 = new JPanel(new GridLayout(9, 9));
    final JTextField[][] subPanels1 = new JTextField[9][9];
    final JPanel errorPanel2 = new JPanel(new GridLayout(9, 9));
    final JTextField[][] subPanels2 = new JTextField[9][9];
    final JPanel errorPanel3 = new JPanel(new GridLayout(9, 9));
    final JTextField[][] subPanels3 = new JTextField[9][9];
    final JPanel ButtonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    Border LineBorder = new LineBorder(Color.lightGray);
    Border outerBorder = BorderFactory.createLineBorder(Color.black, 2);
    Border innerBorder = BorderFactory.createLineBorder(Color.BLUE, 1);
    public Panel(int[][] board) {
        menuBar.add(file);
        file.add(saveGame);
        file.add(newGame);
        file.add(loadGame);
        saveGame.add(saveGame1);
        saveGame.add(saveGame2);
        saveGame.add(saveGame3);
        newGame.add(easyLevel);
        newGame.add(mediumLevel);
        newGame.add(hardLevel);
        loadGame.add(loadGame1);
        loadGame.add(loadGame2);
        loadGame.add(loadGame3);
        loadGame1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                final int[][] board = loadGame(loc1);
                //changePanels(board, frame,GuiPanel);

                frame.dispose();
                new PanelIncognito(board,loadGame(locc1));
                submit.doClick();

            }

        });
        loadGame2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                final int[][] board = loadGame(loc2);
                //changePanels(board, frame,GuiPanel);

                frame.dispose();
                new PanelIncognito(board,loadGame(locc2));
                submit.doClick();

            }

        });loadGame3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                final int[][] board = loadGame(loc3);
                //changePanels(board, frame,GuiPanel);

                frame.dispose();
                new PanelIncognito(board,loadGame(locc3));
                submit.doClick();

            }

        });
        saveGame2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveGame(loc2, subPanels);
                saveEdit(locc2, subPanels);
                winOrNot.setText("Stan gry został zapisany.");
            }
        });
        saveGame3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveGame(loc3, subPanels);
                saveEdit(locc3, subPanels);
                winOrNot.setText("Stan gry został zapisany.");
            }
        });
        easyLevel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                int[][] board1 = g.generate(1);
                new Panel(board1);
            }
        });
        mediumLevel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                int[][] board2 = g.generate(2);
                new Panel(board2);
            }
        });
        hardLevel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                int[][] board3 = g.generate(3);
                new Panel(board3);
            }
        });
        saveGame1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveGame(loc1, subPanels);
                saveEdit(locc1, subPanels);
                winOrNot.setText("Stan gry został zapisany.");
            }


        });

        frame.setJMenuBar(menuBar);
        /*/board[0] = new int[] {3 ,1, 6, 5, 7, 8, 4, 9, 2};//dla testu
        board[1] = new int[] {5, 2, 9,1, 3, 4, 7, 6, 8};
        board[2] = new int[] {4, 8, 7, 6, 2, 9, 5, 3, 1};
        board[3] = new int[] {2, 6, 3, 4, 1, 5, 9, 8, 7};
        board[4] = new int[] {9, 7, 4, 8, 6, 3, 1, 2, 5};
        board[5] = new int[] {8, 5, 1, 7, 9, 2, 6, 4, 3};
        board[6] = new int[] {1, 3, 8, 9, 4, 7, 2, 5, 6};
        board[7] = new int[] {6, 9, 2, 3, 5, 1, 8, 7, 4};
        board[8] = new int[] {7, 4, 5, 2, 8, 6, 3, 1, 0};/*/

        Border innerBorder2 = BorderFactory.createLineBorder(Color.BLUE, 2);
        GuiPanel.setBorder(outerBorder);
        errorPanel1.setBorder(outerBorder);
        errorPanel2.setBorder(outerBorder);
        errorPanel3.setBorder(outerBorder);


        for(int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                subPanels[r][c] = new JTextField(String.valueOf(board[r][c]));
                subPanels[r][c].setBorder(innerBorder);
                if(((c>2 && c<6) || (r>2 && r<6)) && !(((c>2 && c<6) && (r>2 && r<6)))){
                    subPanels[r][c].setBackground(Color.GRAY);
                }
                else {
                    subPanels[r][c].setBackground(Color.WHITE);
                }


                subPanels[r][c].getDocument().addUndoableEditListener(manager);
                GuiPanel.add(subPanels[r][c]);
                if (subPanels[r][c].getText().equals("0")) {
                    subPanels[r][c].setEditable(true);
                } else {
                    subPanels[r][c].setEditable(false);
                }
            }


        }
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                subPanels1[i][j] = new JTextField();
                subPanels1[i][j].setBorder(innerBorder);
                subPanels1[i][j].setBackground(Color.WHITE);
                errorPanel1.add(subPanels1[i][j]);
                subPanels2[i][j] = new JTextField();
                subPanels2[i][j].setBorder(innerBorder);
                subPanels2[i][j].setBackground(Color.WHITE);
                errorPanel2.add(subPanels2[i][j]);
                subPanels3[i][j] = new JTextField();
                subPanels3[i][j].setBorder(innerBorder);
                subPanels3[i][j].setBackground(Color.WHITE);
                errorPanel3.add(subPanels3[i][j]);
            }
        }
        ButtonPanel.add(submit);
        ButtonPanel.add(undo);
        ButtonPanel.add(winOrNot);
        ButtonPanel.add(sendVolume);
        vol.setEditable(true);
        vol.setSize(30,30);
        frame.setSize(520, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ButtonPanel.setBorder(LineBorder);


        frame.setLayout(null);

        GuiPanel.setBounds(0, 0, 400, 400);
        ButtonPanel.setBounds(0, 400, 500, 100);
        errorPanel1.setBounds(400, 0, 100, 100);
        errorPanel2.setBounds(400, 100, 100, 100);
        errorPanel3.setBounds(400, 200, 100, 100);
        vol.setBounds(400,300,50,50);

        frame.add(GuiPanel);
        frame.add(ButtonPanel);
        frame.add(errorPanel1);
        frame.add(errorPanel2);
        frame.add(errorPanel3);
        frame.add(vol);

        submit.addActionListener(this::actionPerformed);
        sendVolume.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int b = Integer.parseInt(vol.getText());
                int c= 100-b;
                VolumeControl.volume(c);
            }
        });
        undo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    manager.undo();
                } catch (Exception ex) {

                }
            }
        });

        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                String num = subPanels[r][c].getText();
                if (!num.contains("0") && !hasDuplicatesInRows(subPanels) && checkSquares(subPanels)) {
                    winOrNot.setText("Gratulacje, wygrałeś!");
                    ButtonPanel.add(winOrNot);
                } else {
                    int[][] board = new int[9][9];
                    for (int x = 0; x < 9; x++) {
                        for (int y = 0; y < 9; y++) {
                            board[x][y] = Integer.parseInt(subPanels[x][y].getText());

                        }
                    }
                    Sudoku.printBoard(board);

                    System.out.println(board);
                    int[] square1 = Sudoku.merge(Arrays.copyOfRange(board[0], 0, 3), Arrays.copyOfRange(board[1], 0, 3), Arrays.copyOfRange(board[2], 0, 3));
                    int[] square2 = Sudoku.merge(Arrays.copyOfRange(board[0], 3, 6), Arrays.copyOfRange(board[1], 3, 6), Arrays.copyOfRange(board[2], 3, 6));
                    int[] square3 = Sudoku.merge(Arrays.copyOfRange(board[0], 6, 9), Arrays.copyOfRange(board[1], 6, 9), Arrays.copyOfRange(board[2], 6, 9));
                    int[] square4 = Sudoku.merge(Arrays.copyOfRange(board[3], 0, 3), Arrays.copyOfRange(board[4], 0, 3), Arrays.copyOfRange(board[5], 0, 3));
                    int[] square5 = Sudoku.merge(Arrays.copyOfRange(board[3], 3, 6), Arrays.copyOfRange(board[4], 3, 6), Arrays.copyOfRange(board[5], 3, 6));
                    int[] square6 = Sudoku.merge(Arrays.copyOfRange(board[3], 6, 9), Arrays.copyOfRange(board[4], 6, 9), Arrays.copyOfRange(board[5], 6, 9));
                    int[] square7 = Sudoku.merge(Arrays.copyOfRange(board[6], 0, 3), Arrays.copyOfRange(board[7], 0, 3), Arrays.copyOfRange(board[8], 0, 3));
                    int[] square8 = Sudoku.merge(Arrays.copyOfRange(board[6], 3, 6), Arrays.copyOfRange(board[7], 3, 6), Arrays.copyOfRange(board[8], 3, 6));
                    int[] square9 = Sudoku.merge(Arrays.copyOfRange(board[6], 6, 9), Arrays.copyOfRange(board[7], 6, 9), Arrays.copyOfRange(board[8], 6, 9));

                    //kolumny
                    int[] column1 = Sudoku.merge(Arrays.copyOfRange(board[0], 0, 1), Arrays.copyOfRange(board[1], 0, 1), Arrays.copyOfRange(board[2], 0, 1), Arrays.copyOfRange(board[3], 0, 1), Arrays.copyOfRange(board[4], 0, 1), Arrays.copyOfRange(board[5], 0, 1), Arrays.copyOfRange(board[6], 0, 1), Arrays.copyOfRange(board[7], 0, 1), Arrays.copyOfRange(board[8], 0, 1));
                    int[] column2 = Sudoku.merge(Arrays.copyOfRange(board[0], 1, 2), Arrays.copyOfRange(board[1], 1, 2), Arrays.copyOfRange(board[2], 1, 2), Arrays.copyOfRange(board[3], 1, 2), Arrays.copyOfRange(board[4], 1, 2), Arrays.copyOfRange(board[5], 1, 2), Arrays.copyOfRange(board[6], 1, 2), Arrays.copyOfRange(board[7], 1, 2), Arrays.copyOfRange(board[8], 1, 2));
                    int[] column3 = Sudoku.merge(Arrays.copyOfRange(board[0], 2, 3), Arrays.copyOfRange(board[1], 2, 3), Arrays.copyOfRange(board[2], 2, 3), Arrays.copyOfRange(board[3], 2, 3), Arrays.copyOfRange(board[4], 2, 3), Arrays.copyOfRange(board[5], 2, 3), Arrays.copyOfRange(board[6], 2, 3), Arrays.copyOfRange(board[7], 2, 3), Arrays.copyOfRange(board[8], 2, 3));
                    int[] column4 = Sudoku.merge(Arrays.copyOfRange(board[0], 3, 4), Arrays.copyOfRange(board[1], 3, 4), Arrays.copyOfRange(board[2], 3, 4), Arrays.copyOfRange(board[3], 3, 4), Arrays.copyOfRange(board[4], 3, 4), Arrays.copyOfRange(board[5], 3, 4), Arrays.copyOfRange(board[6], 3, 4), Arrays.copyOfRange(board[7], 3, 4), Arrays.copyOfRange(board[8], 3, 4));
                    int[] column5 = Sudoku.merge(Arrays.copyOfRange(board[0], 4, 5), Arrays.copyOfRange(board[1], 4, 5), Arrays.copyOfRange(board[2], 4, 5), Arrays.copyOfRange(board[3], 4, 5), Arrays.copyOfRange(board[4], 4, 5), Arrays.copyOfRange(board[5], 4, 5), Arrays.copyOfRange(board[6], 4, 5), Arrays.copyOfRange(board[7], 4, 5), Arrays.copyOfRange(board[8], 4, 5));
                    int[] column6 = Sudoku.merge(Arrays.copyOfRange(board[0], 5, 6), Arrays.copyOfRange(board[1], 5, 6), Arrays.copyOfRange(board[2], 5, 6), Arrays.copyOfRange(board[3], 5, 6), Arrays.copyOfRange(board[4], 5, 6), Arrays.copyOfRange(board[5], 5, 6), Arrays.copyOfRange(board[6], 5, 6), Arrays.copyOfRange(board[7], 5, 6), Arrays.copyOfRange(board[8], 5, 6));
                    int[] column7 = Sudoku.merge(Arrays.copyOfRange(board[0], 6, 7), Arrays.copyOfRange(board[1], 6, 7), Arrays.copyOfRange(board[2], 6, 7), Arrays.copyOfRange(board[3], 6, 7), Arrays.copyOfRange(board[4], 6, 7), Arrays.copyOfRange(board[5], 6, 7), Arrays.copyOfRange(board[6], 6, 7), Arrays.copyOfRange(board[7], 6, 7), Arrays.copyOfRange(board[8], 6, 7));
                    int[] column8 = Sudoku.merge(Arrays.copyOfRange(board[0], 7, 8), Arrays.copyOfRange(board[1], 7, 8), Arrays.copyOfRange(board[2], 7, 8), Arrays.copyOfRange(board[3], 7, 8), Arrays.copyOfRange(board[4], 7, 8), Arrays.copyOfRange(board[5], 7, 8), Arrays.copyOfRange(board[6], 7, 8), Arrays.copyOfRange(board[7], 7, 8), Arrays.copyOfRange(board[8], 7, 8));
                    int[] column9 = Sudoku.merge(Arrays.copyOfRange(board[0], 8, 9), Arrays.copyOfRange(board[1], 8, 9), Arrays.copyOfRange(board[2], 8, 9), Arrays.copyOfRange(board[3], 8, 9), Arrays.copyOfRange(board[4], 8, 9), Arrays.copyOfRange(board[5], 8, 9), Arrays.copyOfRange(board[6], 8, 9), Arrays.copyOfRange(board[7], 8, 9), Arrays.copyOfRange(board[8], 8, 9));

                    if (Sudoku.dup(board[0])) {
                        subPanels1[0][0].setBackground(Color.red);
                        subPanels1[0][1].setBackground(Color.red);
                        subPanels1[0][2].setBackground(Color.red);
                        subPanels1[0][3].setBackground(Color.red);
                        subPanels1[0][4].setBackground(Color.red);
                        subPanels1[0][5].setBackground(Color.red);
                        subPanels1[0][6].setBackground(Color.red);
                        subPanels1[0][7].setBackground(Color.red);
                        subPanels1[0][8].setBackground(Color.red);
                    } else {
                        subPanels1[0][0].setBackground(Color.white);
                        subPanels1[0][1].setBackground(Color.white);
                        subPanels1[0][2].setBackground(Color.white);
                        subPanels1[0][3].setBackground(Color.white);
                        subPanels1[0][4].setBackground(Color.white);
                        subPanels1[0][5].setBackground(Color.white);
                        subPanels1[0][6].setBackground(Color.white);
                        subPanels1[0][7].setBackground(Color.white);
                        subPanels1[0][8].setBackground(Color.white);
                    }
                    if (Sudoku.dup(board[1])) {
                        subPanels1[1][0].setBackground(Color.red);
                        subPanels1[1][1].setBackground(Color.red);
                        subPanels1[1][2].setBackground(Color.red);
                        subPanels1[1][3].setBackground(Color.red);
                        subPanels1[1][4].setBackground(Color.red);
                        subPanels1[1][5].setBackground(Color.red);
                        subPanels1[1][6].setBackground(Color.red);
                        subPanels1[1][7].setBackground(Color.red);
                        subPanels1[1][8].setBackground(Color.red);
                    } else {
                        subPanels1[1][0].setBackground(Color.white);
                        subPanels1[1][1].setBackground(Color.white);
                        subPanels1[1][2].setBackground(Color.white);
                        subPanels1[1][3].setBackground(Color.white);
                        subPanels1[1][4].setBackground(Color.white);
                        subPanels1[1][5].setBackground(Color.white);
                        subPanels1[1][6].setBackground(Color.white);
                        subPanels1[1][7].setBackground(Color.white);
                        subPanels1[1][8].setBackground(Color.white);
                    }
                    if (Sudoku.dup(board[2])) {
                        subPanels1[2][0].setBackground(Color.red);
                        subPanels1[2][1].setBackground(Color.red);
                        subPanels1[2][2].setBackground(Color.red);
                        subPanels1[2][3].setBackground(Color.red);
                        subPanels1[2][4].setBackground(Color.red);
                        subPanels1[2][5].setBackground(Color.red);
                        subPanels1[2][6].setBackground(Color.red);
                        subPanels1[2][7].setBackground(Color.red);
                        subPanels1[2][8].setBackground(Color.red);
                    } else {
                        subPanels1[2][0].setBackground(Color.white);
                        subPanels1[2][1].setBackground(Color.white);
                        subPanels1[2][2].setBackground(Color.white);
                        subPanels1[2][3].setBackground(Color.white);
                        subPanels1[2][4].setBackground(Color.white);
                        subPanels1[2][5].setBackground(Color.white);
                        subPanels1[2][6].setBackground(Color.white);
                        subPanels1[2][7].setBackground(Color.white);
                        subPanels1[2][8].setBackground(Color.white);
                    }
                    if (Sudoku.dup(board[3])) {
                        subPanels1[3][0].setBackground(Color.red);
                        subPanels1[3][1].setBackground(Color.red);
                        subPanels1[3][2].setBackground(Color.red);
                        subPanels1[3][3].setBackground(Color.red);
                        subPanels1[3][4].setBackground(Color.red);
                        subPanels1[3][5].setBackground(Color.red);
                        subPanels1[3][6].setBackground(Color.red);
                        subPanels1[3][7].setBackground(Color.red);
                        subPanels1[3][8].setBackground(Color.red);
                    } else {
                        subPanels1[3][0].setBackground(Color.white);
                        subPanels1[3][1].setBackground(Color.white);
                        subPanels1[3][2].setBackground(Color.white);
                        subPanels1[3][3].setBackground(Color.white);
                        subPanels1[3][4].setBackground(Color.white);
                        subPanels1[3][5].setBackground(Color.white);
                        subPanels1[3][6].setBackground(Color.white);
                        subPanels1[3][7].setBackground(Color.white);
                        subPanels1[3][8].setBackground(Color.white);
                    }
                    if (Sudoku.dup(board[4])) {
                        subPanels1[4][0].setBackground(Color.red);
                        subPanels1[4][1].setBackground(Color.red);
                        subPanels1[4][2].setBackground(Color.red);
                        subPanels1[4][3].setBackground(Color.red);
                        subPanels1[4][4].setBackground(Color.red);
                        subPanels1[4][5].setBackground(Color.red);
                        subPanels1[4][6].setBackground(Color.red);
                        subPanels1[4][7].setBackground(Color.red);
                        subPanels1[4][8].setBackground(Color.red);

                    } else {
                        subPanels1[4][0].setBackground(Color.white);
                        subPanels1[4][1].setBackground(Color.white);
                        subPanels1[4][2].setBackground(Color.white);
                        subPanels1[4][3].setBackground(Color.white);
                        subPanels1[4][4].setBackground(Color.white);
                        subPanels1[4][5].setBackground(Color.white);
                        subPanels1[4][6].setBackground(Color.white);
                        subPanels1[4][7].setBackground(Color.white);
                        subPanels1[4][8].setBackground(Color.white);
                    }
                    if (Sudoku.dup(board[5])) {
                        subPanels1[5][0].setBackground(Color.red);
                        subPanels1[5][1].setBackground(Color.red);
                        subPanels1[5][2].setBackground(Color.red);
                        subPanels1[5][3].setBackground(Color.red);
                        subPanels1[5][4].setBackground(Color.red);
                        subPanels1[5][5].setBackground(Color.red);
                        subPanels1[5][6].setBackground(Color.red);
                        subPanels1[5][7].setBackground(Color.red);
                        subPanels1[5][8].setBackground(Color.red);
                    } else {
                        subPanels1[5][0].setBackground(Color.white);
                        subPanels1[5][1].setBackground(Color.white);
                        subPanels1[5][2].setBackground(Color.white);
                        subPanels1[5][3].setBackground(Color.white);
                        subPanels1[5][4].setBackground(Color.white);
                        subPanels1[5][5].setBackground(Color.white);
                        subPanels1[5][6].setBackground(Color.white);
                        subPanels1[5][7].setBackground(Color.white);
                        subPanels1[5][8].setBackground(Color.white);
                    }
                    if (Sudoku.dup(board[7])) {
                        subPanels1[7][0].setBackground(Color.red);
                        subPanels1[7][1].setBackground(Color.red);
                        subPanels1[7][2].setBackground(Color.red);
                        subPanels1[7][3].setBackground(Color.red);
                        subPanels1[7][4].setBackground(Color.red);
                        subPanels1[7][5].setBackground(Color.red);
                        subPanels1[7][6].setBackground(Color.red);
                        subPanels1[7][7].setBackground(Color.red);
                        subPanels1[7][8].setBackground(Color.red);
                    } else {
                        subPanels1[7][0].setBackground(Color.white);
                        subPanels1[7][1].setBackground(Color.white);
                        subPanels1[7][2].setBackground(Color.white);
                        subPanels1[7][3].setBackground(Color.white);
                        subPanels1[7][4].setBackground(Color.white);
                        subPanels1[7][5].setBackground(Color.white);
                        subPanels1[7][6].setBackground(Color.white);
                        subPanels1[7][7].setBackground(Color.white);
                        subPanels1[7][8].setBackground(Color.white);
                    }
                    if (Sudoku.dup(board[8])) {
                        subPanels1[8][0].setBackground(Color.red);
                        subPanels1[8][1].setBackground(Color.red);
                        subPanels1[8][2].setBackground(Color.red);
                        subPanels1[8][3].setBackground(Color.red);
                        subPanels1[8][4].setBackground(Color.red);
                        subPanels1[8][5].setBackground(Color.red);
                        subPanels1[8][6].setBackground(Color.red);
                        subPanels1[8][7].setBackground(Color.red);
                        subPanels1[8][8].setBackground(Color.red);
                    } else {
                        subPanels1[8][0].setBackground(Color.white);
                        subPanels1[8][1].setBackground(Color.white);
                        subPanels1[8][2].setBackground(Color.white);
                        subPanels1[8][3].setBackground(Color.white);
                        subPanels1[8][4].setBackground(Color.white);
                        subPanels1[8][5].setBackground(Color.white);
                        subPanels1[8][6].setBackground(Color.white);
                        subPanels1[8][7].setBackground(Color.white);
                        subPanels1[8][8].setBackground(Color.white);
                    }
                    if (Sudoku.dup(board[6])) {
                        subPanels1[6][0].setBackground(Color.red);
                        subPanels1[6][1].setBackground(Color.red);
                        subPanels1[6][2].setBackground(Color.red);
                        subPanels1[6][3].setBackground(Color.red);
                        subPanels1[6][4].setBackground(Color.red);
                        subPanels1[6][5].setBackground(Color.red);
                        subPanels1[6][6].setBackground(Color.red);
                        subPanels1[6][7].setBackground(Color.red);
                        subPanels1[6][8].setBackground(Color.red);
                    } else {
                        subPanels1[6][0].setBackground(Color.white);
                        subPanels1[6][1].setBackground(Color.white);
                        subPanels1[6][2].setBackground(Color.white);
                        subPanels1[6][3].setBackground(Color.white);
                        subPanels1[6][4].setBackground(Color.white);
                        subPanels1[6][5].setBackground(Color.white);
                        subPanels1[6][6].setBackground(Color.white);
                        subPanels1[6][7].setBackground(Color.white);
                        subPanels1[6][8].setBackground(Color.white);
                    }
                    if (Sudoku.dup(column1)) {
                        subPanels2[0][0].setBackground(Color.blue);
                        subPanels2[1][0].setBackground(Color.blue);
                        subPanels2[2][0].setBackground(Color.blue);
                        subPanels2[3][0].setBackground(Color.blue);
                        subPanels2[4][0].setBackground(Color.blue);
                        subPanels2[5][0].setBackground(Color.blue);
                        subPanels2[6][0].setBackground(Color.blue);
                        subPanels2[7][0].setBackground(Color.blue);
                        subPanels2[8][0].setBackground(Color.blue);
                    } else {
                        subPanels2[0][0].setBackground(Color.white);
                        subPanels2[1][0].setBackground(Color.white);
                        subPanels2[2][0].setBackground(Color.white);
                        subPanels2[3][0].setBackground(Color.white);
                        subPanels2[4][0].setBackground(Color.white);
                        subPanels2[5][0].setBackground(Color.white);
                        subPanels2[6][0].setBackground(Color.white);
                        subPanels2[7][0].setBackground(Color.white);
                        subPanels2[8][0].setBackground(Color.white);
                    }
                    if (Sudoku.dup(column2)) {
                        subPanels2[0][1].setBackground(Color.blue);
                        subPanels2[1][1].setBackground(Color.blue);
                        subPanels2[2][1].setBackground(Color.blue);
                        subPanels2[3][1].setBackground(Color.blue);
                        subPanels2[4][1].setBackground(Color.blue);
                        subPanels2[5][1].setBackground(Color.blue);
                        subPanels2[6][1].setBackground(Color.blue);
                        subPanels2[7][1].setBackground(Color.blue);
                        subPanels2[8][1].setBackground(Color.blue);
                    } else {
                        subPanels2[0][1].setBackground(Color.white);
                        subPanels2[1][1].setBackground(Color.white);
                        subPanels2[2][1].setBackground(Color.white);
                        subPanels2[3][1].setBackground(Color.white);
                        subPanels2[4][1].setBackground(Color.white);
                        subPanels2[5][1].setBackground(Color.white);
                        subPanels2[6][1].setBackground(Color.white);
                        subPanels2[7][1].setBackground(Color.white);
                        subPanels2[8][1].setBackground(Color.white);
                    }
                    if (Sudoku.dup(column3)) {
                        subPanels2[0][2].setBackground(Color.blue);
                        subPanels2[1][2].setBackground(Color.blue);
                        subPanels2[2][2].setBackground(Color.blue);
                        subPanels2[3][2].setBackground(Color.blue);
                        subPanels2[4][2].setBackground(Color.blue);
                        subPanels2[5][2].setBackground(Color.blue);
                        subPanels2[6][2].setBackground(Color.blue);
                        subPanels2[7][2].setBackground(Color.blue);
                        subPanels2[8][2].setBackground(Color.blue);
                    } else {
                        subPanels2[0][2].setBackground(Color.white);
                        subPanels2[1][2].setBackground(Color.white);
                        subPanels2[2][2].setBackground(Color.white);
                        subPanels2[3][2].setBackground(Color.white);
                        subPanels2[4][2].setBackground(Color.white);
                        subPanels2[5][2].setBackground(Color.white);
                        subPanels2[6][2].setBackground(Color.white);
                        subPanels2[7][2].setBackground(Color.white);
                        subPanels2[8][2].setBackground(Color.white);
                    }
                    if (Sudoku.dup(column4)) {
                        subPanels2[0][3].setBackground(Color.blue);
                        subPanels2[1][3].setBackground(Color.blue);
                        subPanels2[2][3].setBackground(Color.blue);
                        subPanels2[3][3].setBackground(Color.blue);
                        subPanels2[4][3].setBackground(Color.blue);
                        subPanels2[5][3].setBackground(Color.blue);
                        subPanels2[6][3].setBackground(Color.blue);
                        subPanels2[7][3].setBackground(Color.blue);
                        subPanels2[8][3].setBackground(Color.blue);
                    } else {
                        subPanels2[0][3].setBackground(Color.white);
                        subPanels2[1][3].setBackground(Color.white);
                        subPanels2[2][3].setBackground(Color.white);
                        subPanels2[3][3].setBackground(Color.white);
                        subPanels2[4][3].setBackground(Color.white);
                        subPanels2[5][3].setBackground(Color.white);
                        subPanels2[6][3].setBackground(Color.white);
                        subPanels2[7][3].setBackground(Color.white);
                        subPanels2[8][3].setBackground(Color.white);
                    }
                    if (Sudoku.dup(column5)) {
                        subPanels2[0][4].setBackground(Color.blue);
                        subPanels2[1][4].setBackground(Color.blue);
                        subPanels2[2][4].setBackground(Color.blue);
                        subPanels2[3][4].setBackground(Color.blue);
                        subPanels2[4][4].setBackground(Color.blue);
                        subPanels2[5][4].setBackground(Color.blue);
                        subPanels2[6][4].setBackground(Color.blue);
                        subPanels2[7][4].setBackground(Color.blue);
                        subPanels2[8][4].setBackground(Color.blue);
                    } else {
                        subPanels2[0][4].setBackground(Color.white);
                        subPanels2[1][4].setBackground(Color.white);
                        subPanels2[2][4].setBackground(Color.white);
                        subPanels2[3][4].setBackground(Color.white);
                        subPanels2[4][4].setBackground(Color.white);
                        subPanels2[5][4].setBackground(Color.white);
                        subPanels2[6][4].setBackground(Color.white);
                        subPanels2[7][4].setBackground(Color.white);
                        subPanels2[8][4].setBackground(Color.white);
                    }
                    if (Sudoku.dup(column6)) {
                        subPanels2[0][5].setBackground(Color.blue);
                        subPanels2[1][5].setBackground(Color.blue);
                        subPanels2[2][5].setBackground(Color.blue);
                        subPanels2[3][5].setBackground(Color.blue);
                        subPanels2[4][5].setBackground(Color.blue);
                        subPanels2[5][5].setBackground(Color.blue);
                        subPanels2[6][5].setBackground(Color.blue);
                        subPanels2[7][5].setBackground(Color.blue);
                        subPanels2[8][5].setBackground(Color.blue);
                    } else {
                        subPanels2[0][5].setBackground(Color.white);
                        subPanels2[1][5].setBackground(Color.white);
                        subPanels2[2][5].setBackground(Color.white);
                        subPanels2[3][5].setBackground(Color.white);
                        subPanels2[4][5].setBackground(Color.white);
                        subPanels2[5][5].setBackground(Color.white);
                        subPanels2[6][5].setBackground(Color.white);
                        subPanels2[7][5].setBackground(Color.white);
                        subPanels2[8][5].setBackground(Color.white);
                    }
                    if (Sudoku.dup(column7)) {
                        subPanels2[0][6].setBackground(Color.blue);
                        subPanels2[1][6].setBackground(Color.blue);
                        subPanels2[2][6].setBackground(Color.blue);
                        subPanels2[3][6].setBackground(Color.blue);
                        subPanels2[4][6].setBackground(Color.blue);
                        subPanels2[5][6].setBackground(Color.blue);
                        subPanels2[6][6].setBackground(Color.blue);
                        subPanels2[7][6].setBackground(Color.blue);
                        subPanels2[8][6].setBackground(Color.blue);
                    } else {
                        subPanels2[0][6].setBackground(Color.white);
                        subPanels2[1][6].setBackground(Color.white);
                        subPanels2[2][6].setBackground(Color.white);
                        subPanels2[3][6].setBackground(Color.white);
                        subPanels2[4][6].setBackground(Color.white);
                        subPanels2[5][6].setBackground(Color.white);
                        subPanels2[6][6].setBackground(Color.white);
                        subPanels2[7][6].setBackground(Color.white);
                        subPanels2[8][6].setBackground(Color.white);
                    }
                    if (Sudoku.dup(column8)) {
                        subPanels2[0][7].setBackground(Color.blue);
                        subPanels2[1][7].setBackground(Color.blue);
                        subPanels2[2][7].setBackground(Color.blue);
                        subPanels2[3][7].setBackground(Color.blue);
                        subPanels2[4][7].setBackground(Color.blue);
                        subPanels2[5][7].setBackground(Color.blue);
                        subPanels2[6][7].setBackground(Color.blue);
                        subPanels2[7][7].setBackground(Color.blue);
                        subPanels2[8][7].setBackground(Color.blue);
                    } else {
                        subPanels2[0][7].setBackground(Color.white);
                        subPanels2[1][7].setBackground(Color.white);
                        subPanels2[2][7].setBackground(Color.white);
                        subPanels2[3][7].setBackground(Color.white);
                        subPanels2[4][7].setBackground(Color.white);
                        subPanels2[5][7].setBackground(Color.white);
                        subPanels2[6][7].setBackground(Color.white);
                        subPanels2[7][7].setBackground(Color.white);
                        subPanels2[8][7].setBackground(Color.white);
                    }
                    if (Sudoku.dup(column9)) {
                        subPanels2[0][8].setBackground(Color.blue);
                        subPanels2[1][8].setBackground(Color.blue);
                        subPanels2[2][8].setBackground(Color.blue);
                        subPanels2[3][8].setBackground(Color.blue);
                        subPanels2[4][8].setBackground(Color.blue);
                        subPanels2[5][8].setBackground(Color.blue);
                        subPanels2[6][8].setBackground(Color.blue);
                        subPanels2[7][8].setBackground(Color.blue);
                        subPanels2[8][8].setBackground(Color.blue);
                    } else {
                        subPanels2[0][8].setBackground(Color.white);
                        subPanels2[1][8].setBackground(Color.white);
                        subPanels2[2][8].setBackground(Color.white);
                        subPanels2[3][8].setBackground(Color.white);
                        subPanels2[4][8].setBackground(Color.white);
                        subPanels2[5][8].setBackground(Color.white);
                        subPanels2[6][8].setBackground(Color.white);
                        subPanels2[7][8].setBackground(Color.white);
                        subPanels2[8][8].setBackground(Color.white);
                    }
                    if (Sudoku.dup(square1)) {
                        subPanels3[0][0].setBackground(Color.magenta);
                        subPanels3[0][1].setBackground(Color.magenta);
                        subPanels3[0][2].setBackground(Color.magenta);
                        subPanels3[1][0].setBackground(Color.magenta);
                        subPanels3[2][0].setBackground(Color.magenta);
                        subPanels3[1][2].setBackground(Color.magenta);
                        subPanels3[1][1].setBackground(Color.magenta);
                        subPanels3[2][1].setBackground(Color.magenta);
                        subPanels3[2][2].setBackground(Color.magenta);
                    } else {
                        subPanels3[0][0].setBackground(Color.white);
                        subPanels3[0][1].setBackground(Color.white);
                        subPanels3[0][2].setBackground(Color.white);
                        subPanels3[1][0].setBackground(Color.white);
                        subPanels3[2][0].setBackground(Color.white);
                        subPanels3[1][2].setBackground(Color.white);
                        subPanels3[1][1].setBackground(Color.white);
                        subPanels3[2][1].setBackground(Color.white);
                        subPanels3[2][2].setBackground(Color.white);
                    }
                    if (Sudoku.dup(square4)) {
                        subPanels3[3][0].setBackground(Color.magenta);
                        subPanels3[3][1].setBackground(Color.magenta);
                        subPanels3[3][2].setBackground(Color.magenta);
                        subPanels3[4][0].setBackground(Color.magenta);
                        subPanels3[4][1].setBackground(Color.magenta);
                        subPanels3[4][2].setBackground(Color.magenta);
                        subPanels3[5][0].setBackground(Color.magenta);
                        subPanels3[5][1].setBackground(Color.magenta);
                        subPanels3[5][2].setBackground(Color.magenta);
                    } else {
                        subPanels3[3][0].setBackground(Color.white);
                        subPanels3[3][1].setBackground(Color.white);
                        subPanels3[3][2].setBackground(Color.white);
                        subPanels3[4][0].setBackground(Color.white);
                        subPanels3[4][1].setBackground(Color.white);
                        subPanels3[4][2].setBackground(Color.white);
                        subPanels3[5][0].setBackground(Color.white);
                        subPanels3[5][1].setBackground(Color.white);
                        subPanels3[5][2].setBackground(Color.white);
                    }
                    if (Sudoku.dup(square7)) {
                        subPanels3[6][0].setBackground(Color.magenta);
                        subPanels3[6][1].setBackground(Color.magenta);
                        subPanels3[6][2].setBackground(Color.magenta);
                        subPanels3[7][0].setBackground(Color.magenta);
                        subPanels3[7][1].setBackground(Color.magenta);
                        subPanels3[7][2].setBackground(Color.magenta);
                        subPanels3[8][0].setBackground(Color.magenta);
                        subPanels3[8][1].setBackground(Color.magenta);
                        subPanels3[8][2].setBackground(Color.magenta);
                    } else {
                        subPanels3[6][0].setBackground(Color.white);
                        subPanels3[6][1].setBackground(Color.white);
                        subPanels3[6][2].setBackground(Color.white);
                        subPanels3[7][0].setBackground(Color.white);
                        subPanels3[7][1].setBackground(Color.white);
                        subPanels3[7][2].setBackground(Color.white);
                        subPanels3[8][0].setBackground(Color.white);
                        subPanels3[8][1].setBackground(Color.white);
                        subPanels3[8][2].setBackground(Color.white);
                    }
                    if (Sudoku.dup(square2)) {
                        subPanels3[0][3].setBackground(Color.magenta);
                        subPanels3[0][4].setBackground(Color.magenta);
                        subPanels3[0][5].setBackground(Color.magenta);
                        subPanels3[1][3].setBackground(Color.magenta);
                        subPanels3[1][4].setBackground(Color.magenta);
                        subPanels3[1][5].setBackground(Color.magenta);
                        subPanels3[2][3].setBackground(Color.magenta);
                        subPanels3[2][4].setBackground(Color.magenta);
                        subPanels3[2][5].setBackground(Color.magenta);
                    } else {
                        subPanels3[0][3].setBackground(Color.white);
                        subPanels3[0][4].setBackground(Color.white);
                        subPanels3[0][5].setBackground(Color.white);
                        subPanels3[1][3].setBackground(Color.white);
                        subPanels3[1][4].setBackground(Color.white);
                        subPanels3[1][5].setBackground(Color.white);
                        subPanels3[2][3].setBackground(Color.white);
                        subPanels3[2][4].setBackground(Color.white);
                        subPanels3[2][5].setBackground(Color.white);
                    }
                    if (Sudoku.dup(square3)) {
                        subPanels3[0][6].setBackground(Color.magenta);
                        subPanels3[0][7].setBackground(Color.magenta);
                        subPanels3[0][8].setBackground(Color.magenta);
                        subPanels3[1][6].setBackground(Color.magenta);
                        subPanels3[1][7].setBackground(Color.magenta);
                        subPanels3[1][8].setBackground(Color.magenta);
                        subPanels3[2][6].setBackground(Color.magenta);
                        subPanels3[2][7].setBackground(Color.magenta);
                        subPanels3[2][8].setBackground(Color.magenta);
                    } else {
                        subPanels3[0][6].setBackground(Color.white);
                        subPanels3[0][7].setBackground(Color.white);
                        subPanels3[0][8].setBackground(Color.white);
                        subPanels3[1][6].setBackground(Color.white);
                        subPanels3[1][7].setBackground(Color.white);
                        subPanels3[1][8].setBackground(Color.white);
                        subPanels3[2][6].setBackground(Color.white);
                        subPanels3[2][7].setBackground(Color.white);
                        subPanels3[2][8].setBackground(Color.white);
                    }
                    if (Sudoku.dup(square6)) {
                        subPanels3[3][6].setBackground(Color.magenta);
                        subPanels3[3][7].setBackground(Color.magenta);
                        subPanels3[3][8].setBackground(Color.magenta);
                        subPanels3[4][6].setBackground(Color.magenta);
                        subPanels3[4][7].setBackground(Color.magenta);
                        subPanels3[4][8].setBackground(Color.magenta);
                        subPanels3[5][6].setBackground(Color.magenta);
                        subPanels3[5][7].setBackground(Color.magenta);
                        subPanels3[5][8].setBackground(Color.magenta);
                    } else {
                        subPanels3[3][6].setBackground(Color.white);
                        subPanels3[3][7].setBackground(Color.white);
                        subPanels3[3][8].setBackground(Color.white);
                        subPanels3[4][6].setBackground(Color.white);
                        subPanels3[4][7].setBackground(Color.white);
                        subPanels3[4][8].setBackground(Color.white);
                        subPanels3[5][6].setBackground(Color.white);
                        subPanels3[5][7].setBackground(Color.white);
                        subPanels3[5][8].setBackground(Color.white);
                    }
                    if (Sudoku.dup(square8)) {
                        subPanels3[6][3].setBackground(Color.magenta);
                        subPanels3[6][4].setBackground(Color.magenta);
                        subPanels3[6][5].setBackground(Color.magenta);
                        subPanels3[7][3].setBackground(Color.magenta);
                        subPanels3[7][4].setBackground(Color.magenta);
                        subPanels3[7][5].setBackground(Color.magenta);
                        subPanels3[8][3].setBackground(Color.magenta);
                        subPanels3[8][4].setBackground(Color.magenta);
                        subPanels3[8][5].setBackground(Color.magenta);
                    } else {
                        subPanels3[6][3].setBackground(Color.white);
                        subPanels3[6][4].setBackground(Color.white);
                        subPanels3[6][5].setBackground(Color.white);
                        subPanels3[7][3].setBackground(Color.white);
                        subPanels3[7][4].setBackground(Color.white);
                        subPanels3[7][5].setBackground(Color.white);
                        subPanels3[8][3].setBackground(Color.white);
                        subPanels3[8][4].setBackground(Color.white);
                        subPanels3[8][5].setBackground(Color.white);
                    }
                    if (Sudoku.dup(square9)) {
                        subPanels3[6][6].setBackground(Color.magenta);
                        subPanels3[6][7].setBackground(Color.magenta);
                        subPanels3[6][8].setBackground(Color.magenta);
                        subPanels3[7][6].setBackground(Color.magenta);
                        subPanels3[7][7].setBackground(Color.magenta);
                        subPanels3[7][8].setBackground(Color.magenta);
                        subPanels3[8][6].setBackground(Color.magenta);
                        subPanels3[8][7].setBackground(Color.magenta);
                        subPanels3[8][8].setBackground(Color.magenta);
                    } else {
                        subPanels3[6][6].setBackground(Color.white);
                        subPanels3[6][7].setBackground(Color.white);
                        subPanels3[6][8].setBackground(Color.white);
                        subPanels3[7][6].setBackground(Color.white);
                        subPanels3[7][7].setBackground(Color.white);
                        subPanels3[7][8].setBackground(Color.white);
                        subPanels3[8][6].setBackground(Color.white);
                        subPanels3[8][7].setBackground(Color.white);
                        subPanels3[8][8].setBackground(Color.white);
                    }
                    if (Sudoku.dup(square5)) {
                        subPanels3[3][3].setBackground(Color.magenta);
                        subPanels3[3][4].setBackground(Color.magenta);
                        subPanels3[3][5].setBackground(Color.magenta);
                        subPanels3[4][3].setBackground(Color.magenta);
                        subPanels3[4][4].setBackground(Color.magenta);
                        subPanels3[4][5].setBackground(Color.magenta);
                        subPanels3[5][3].setBackground(Color.magenta);
                        subPanels3[5][4].setBackground(Color.magenta);
                        subPanels3[5][5].setBackground(Color.magenta);
                    } else {
                        subPanels3[3][3].setBackground(Color.white);
                        subPanels3[3][4].setBackground(Color.white);
                        subPanels3[3][5].setBackground(Color.white);
                        subPanels3[4][3].setBackground(Color.white);
                        subPanels3[4][4].setBackground(Color.white);
                        subPanels3[4][5].setBackground(Color.white);
                        subPanels3[5][3].setBackground(Color.white);
                        subPanels3[5][4].setBackground(Color.white);
                        subPanels3[5][5].setBackground(Color.white);
                    }

                    winOrNot.setText("Coś jest jeszcze nie tak");
                }
            }
        }

    }

    public boolean hasDuplicatesInRows(JTextField[][] text) {
        for (int row = 0; row < text.length; row++) {
            for (int col = 0; col < text[row].length; col++) {
                int num = Integer.parseInt(text[row][col].getText());
                for (int otherCol = col + 1; otherCol < text.length; otherCol++) {
                    if (num == Integer.parseInt(text[row][otherCol].getText())) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    boolean checkSquares(JTextField[][] grid) {
        List<Integer> values = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));
        List<Integer> values1 = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));
        List<Integer> x = new ArrayList<>(Arrays.asList(0, 3, 6));
        for (int row:x) {
            for (int col:x){
                for (int i = 0; i < 3; i++){
                    for (int j = 0; j < 3; j++){
                        values.remove((Integer) Integer.parseInt(grid[row+i][col+j].getText()));
                    }
                }
                if (values.isEmpty()){
                    values = values1;
                    continue;
                }
                else {
                    return false;
                }

            }

        }
        return true;
    }
    public static void saveEdit(String pathOfFile,  JTextField[][] text) {
        try {

            StringBuilder builder = new StringBuilder();
            for(int i = 0; i < 9; i++)
            {
                for(int j = 0; j < 9; j++)
                {if(text[i][j].isEditable()){
                    builder.append("1");
                }else{builder.append("0");}
                    //builder.append(text[i][j].getText()+"");
                    if(j < 8)
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
    public static void saveGame(String pathOfFile,  JTextField[][] text) {
        try {

            StringBuilder builder = new StringBuilder();
            for(int i = 0; i < 9; i++)
            {
                for(int j = 0; j < 9; j++)
                {
                    //builder.append(text[i][j].isEditable());
                    builder.append(text[i][j].getText()+"");
                    if(j < 8)
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
            BufferedReader reader = new BufferedReader(new FileReader(savedGameFile));
            String line = "";
            int row = 0;
            while((line = reader.readLine()) != null)
            {
                String[] cols = line.split(",");
                int col = 0;
                for(String  c : cols)
                {
                    board[row][col] = Integer.parseInt(c);
                    col++;
                }
                row++;
            }
            reader.close();
        }catch (Exception err){
            System.out.println("\nNie ma takiej ścieżki:  " + pathOfFile);
            System.out.println(err.getMessage());
        }
        return board;
    }

    public static void main(String... args) {
        //new Panel();
    }
}
class PanelIncognito implements ActionListener {
    //int[][] board = new int[9][];//dla ręcznych testów
    String loc1= "resources\\1.txt";
    String locc1="resources\\1config.txt";
    String loc2= "resources\\2.txt";
    String locc2="resources\\2config.txt";
    String loc3= "resources\\3.txt";
    String locc3="resources\\3config.txt";
    JMenuBar menuBar = new JMenuBar();
    JButton saveGame1 = new JButton("Save Game 1");
    JButton saveGame2 = new JButton("Save Game 2");
    JButton saveGame3 = new JButton("Save Game 3");
    JButton submit = new JButton("Submit");
    JButton undo = new JButton("Undo");
    JButton sendVolume = new JButton("Set Soundtrack Volume");
    JTextField vol = new JTextField();
    JMenu file = new JMenu("Game");
    JMenu newGame = new JMenu("New Game");
    JMenu saveGame = new JMenu("Save Game");
    JMenu loadGame = new JMenu("Load Game");
    JButton loadGame1 = new JButton("Load Game 1");
    JButton loadGame2 = new JButton("Load Game 2");
    JButton loadGame3 = new JButton("Load Game 3");
    JButton easyLevel = new JButton("Easy      ");
    JButton mediumLevel = new JButton("Medium");
    JButton hardLevel = new JButton("Hard      ");
    Generator g = new Generator();
    final int[][] board = g.generate(2);
    UndoManager manager = new UndoManager();
    JFrame frame = new JFrame();
    JLabel winOrNot = new JLabel("");
    final JPanel GuiPanel = new JPanel(new GridLayout(9, 9));
    final JTextField[][] subPanels = new JTextField[9][9];
    final JPanel errorPanel1 = new JPanel(new GridLayout(9, 9));
    final JTextField[][] subPanels1 = new JTextField[9][9];
    final JPanel errorPanel2 = new JPanel(new GridLayout(9, 9));
    final JTextField[][] subPanels2 = new JTextField[9][9];
    final JPanel errorPanel3 = new JPanel(new GridLayout(9, 9));
    final JTextField[][] subPanels3 = new JTextField[9][9];
    final JPanel ButtonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    Border LineBorder = new LineBorder(Color.lightGray);
    Border outerBorder = BorderFactory.createLineBorder(Color.black, 2);
    Border innerBorder = BorderFactory.createLineBorder(Color.BLUE, 1);
    public PanelIncognito(int[][] board,int[][] config) {
        menuBar.add(file);
        file.add(saveGame);
        file.add(newGame);
        file.add(loadGame);
        saveGame.add(saveGame1);
        saveGame.add(saveGame2);
        saveGame.add(saveGame3);
        newGame.add(easyLevel);
        newGame.add(mediumLevel);
        newGame.add(hardLevel);
        loadGame.add(loadGame1);
        loadGame.add(loadGame2);
        loadGame.add(loadGame3);
        loadGame1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                final int[][] board = loadGame(loc1);
                //changePanels(board, frame,GuiPanel);
                frame.dispose();
                new PanelIncognito(board,loadGame(locc1));
            }

        });
        loadGame2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                final int[][] board = loadGame(loc2);
                //changePanels(board, frame,GuiPanel);
                frame.dispose();
                new PanelIncognito(board,loadGame(locc2));
                submit.doClick();

            }

        });loadGame3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                final int[][] board = loadGame(loc3);
                //changePanels(board, frame,GuiPanel);

                frame.dispose();
                new PanelIncognito(board,loadGame(locc3));
                submit.doClick();

            }

        });
        saveGame2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveGame(loc2, subPanels);
                saveEdit(locc2, subPanels);
                winOrNot.setText("Stan gry został zapisany.");
            }
        });
        saveGame3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveGame(loc3, subPanels);
                saveEdit(locc3, subPanels);
                winOrNot.setText("Stan gry został zapisany.");
            }
        });
        easyLevel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                int[][] board1 = g.generate(1);
                new Panel(board1);
            }
        });
        mediumLevel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                int[][] board2 = g.generate(2);
                new Panel(board2);
            }
        });
        hardLevel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                int[][] board3 = g.generate(3);
                new Panel(board3);
            }
        });
        saveGame1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveGame(loc1, subPanels);
                saveEdit(locc1, subPanels);
                winOrNot.setText("Stan gry został zapisany.");
            }


        });

        frame.setJMenuBar(menuBar);
        /*/board[0] = new int[] {3 ,1, 6, 5, 7, 8, 4, 9, 2};//dla testu
        board[1] = new int[] {5, 2, 9,1, 3, 4, 7, 6, 8};
        board[2] = new int[] {4, 8, 7, 6, 2, 9, 5, 3, 1};
        board[3] = new int[] {2, 6, 3, 4, 1, 5, 9, 8, 7};
        board[4] = new int[] {9, 7, 4, 8, 6, 3, 1, 2, 5};
        board[5] = new int[] {8, 5, 1, 7, 9, 2, 6, 4, 3};
        board[6] = new int[] {1, 3, 8, 9, 4, 7, 2, 5, 6};
        board[7] = new int[] {6, 9, 2, 3, 5, 1, 8, 7, 4};
        board[8] = new int[] {7, 4, 5, 2, 8, 6, 3, 1, 0};/*/

        Border innerBorder2 = BorderFactory.createLineBorder(Color.BLUE, 2);
        GuiPanel.setBorder(outerBorder);
        errorPanel1.setBorder(outerBorder);
        errorPanel2.setBorder(outerBorder);
        errorPanel3.setBorder(outerBorder);


        for(int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                subPanels[r][c] = new JTextField(String.valueOf(board[r][c]));
                subPanels[r][c].setBorder(innerBorder);
                if(((c>2 && c<6) || (r>2 && r<6)) && !(((c>2 && c<6) && (r>2 && r<6)))){
                    subPanels[r][c].setBackground(Color.GRAY);
                }
                else {
                    subPanels[r][c].setBackground(Color.WHITE);
                }

                subPanels[r][c].getDocument().addUndoableEditListener(manager);
                GuiPanel.add(subPanels[r][c]);
                if (config[r][c] == 1) {
                    subPanels[r][c].setEditable(true);
                } else {
                    subPanels[r][c].setEditable(false);
                }
            }


        }
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                subPanels1[i][j] = new JTextField();
                subPanels1[i][j].setBorder(innerBorder);
                subPanels1[i][j].setBackground(Color.WHITE);
                errorPanel1.add(subPanels1[i][j]);
                subPanels2[i][j] = new JTextField();
                subPanels2[i][j].setBorder(innerBorder);
                subPanels2[i][j].setBackground(Color.WHITE);
                errorPanel2.add(subPanels2[i][j]);
                subPanels3[i][j] = new JTextField();
                subPanels3[i][j].setBorder(innerBorder);
                subPanels3[i][j].setBackground(Color.WHITE);
                errorPanel3.add(subPanels3[i][j]);
            }
        }
        ButtonPanel.add(submit);
        ButtonPanel.add(undo);
        ButtonPanel.add(winOrNot);
        ButtonPanel.add(sendVolume);
        vol.setEditable(true);
        vol.setSize(30,30);
        frame.setSize(520, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ButtonPanel.setBorder(LineBorder);


        frame.setLayout(null);

        GuiPanel.setBounds(0, 0, 400, 400);
        ButtonPanel.setBounds(0, 400, 500, 100);
        errorPanel1.setBounds(400, 0, 100, 100);
        errorPanel2.setBounds(400, 100, 100, 100);
        errorPanel3.setBounds(400, 200, 100, 100);
        vol.setBounds(400,300,50,50);

        frame.add(GuiPanel);
        frame.add(ButtonPanel);
        frame.add(errorPanel1);
        frame.add(errorPanel2);
        frame.add(errorPanel3);
        frame.add(vol);

        submit.addActionListener(this::actionPerformed);
        sendVolume.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int b = Integer.parseInt(vol.getText());
                int c= 100-b;
                VolumeControl.volume(c);
            }
        });
        undo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    manager.undo();
                } catch (Exception ex) {

                }
            }
        });

        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                String num = subPanels[r][c].getText();
                if (!num.contains("0") && !hasDuplicatesInRows(subPanels) && checkSquares(subPanels)) {
                    winOrNot.setText("Gratulacje, wygrałeś!");
                    ButtonPanel.add(winOrNot);
                } else {
                    int[][] board = new int[9][9];
                    for (int x = 0; x < 9; x++) {
                        for (int y = 0; y < 9; y++) {
                            board[x][y] = Integer.parseInt(subPanels[x][y].getText());

                        }
                    }
                    Sudoku.printBoard(board);

                    System.out.println(board);
                    int[] square1 = Sudoku.merge(Arrays.copyOfRange(board[0], 0, 3), Arrays.copyOfRange(board[1], 0, 3), Arrays.copyOfRange(board[2], 0, 3));
                    int[] square2 = Sudoku.merge(Arrays.copyOfRange(board[0], 3, 6), Arrays.copyOfRange(board[1], 3, 6), Arrays.copyOfRange(board[2], 3, 6));
                    int[] square3 = Sudoku.merge(Arrays.copyOfRange(board[0], 6, 9), Arrays.copyOfRange(board[1], 6, 9), Arrays.copyOfRange(board[2], 6, 9));
                    int[] square4 = Sudoku.merge(Arrays.copyOfRange(board[3], 0, 3), Arrays.copyOfRange(board[4], 0, 3), Arrays.copyOfRange(board[5], 0, 3));
                    int[] square5 = Sudoku.merge(Arrays.copyOfRange(board[3], 3, 6), Arrays.copyOfRange(board[4], 3, 6), Arrays.copyOfRange(board[5], 3, 6));
                    int[] square6 = Sudoku.merge(Arrays.copyOfRange(board[3], 6, 9), Arrays.copyOfRange(board[4], 6, 9), Arrays.copyOfRange(board[5], 6, 9));
                    int[] square7 = Sudoku.merge(Arrays.copyOfRange(board[6], 0, 3), Arrays.copyOfRange(board[7], 0, 3), Arrays.copyOfRange(board[8], 0, 3));
                    int[] square8 = Sudoku.merge(Arrays.copyOfRange(board[6], 3, 6), Arrays.copyOfRange(board[7], 3, 6), Arrays.copyOfRange(board[8], 3, 6));
                    int[] square9 = Sudoku.merge(Arrays.copyOfRange(board[6], 6, 9), Arrays.copyOfRange(board[7], 6, 9), Arrays.copyOfRange(board[8], 6, 9));

                    //kolumny
                    int[] column1 = Sudoku.merge(Arrays.copyOfRange(board[0], 0, 1), Arrays.copyOfRange(board[1], 0, 1), Arrays.copyOfRange(board[2], 0, 1), Arrays.copyOfRange(board[3], 0, 1), Arrays.copyOfRange(board[4], 0, 1), Arrays.copyOfRange(board[5], 0, 1), Arrays.copyOfRange(board[6], 0, 1), Arrays.copyOfRange(board[7], 0, 1), Arrays.copyOfRange(board[8], 0, 1));
                    int[] column2 = Sudoku.merge(Arrays.copyOfRange(board[0], 1, 2), Arrays.copyOfRange(board[1], 1, 2), Arrays.copyOfRange(board[2], 1, 2), Arrays.copyOfRange(board[3], 1, 2), Arrays.copyOfRange(board[4], 1, 2), Arrays.copyOfRange(board[5], 1, 2), Arrays.copyOfRange(board[6], 1, 2), Arrays.copyOfRange(board[7], 1, 2), Arrays.copyOfRange(board[8], 1, 2));
                    int[] column3 = Sudoku.merge(Arrays.copyOfRange(board[0], 2, 3), Arrays.copyOfRange(board[1], 2, 3), Arrays.copyOfRange(board[2], 2, 3), Arrays.copyOfRange(board[3], 2, 3), Arrays.copyOfRange(board[4], 2, 3), Arrays.copyOfRange(board[5], 2, 3), Arrays.copyOfRange(board[6], 2, 3), Arrays.copyOfRange(board[7], 2, 3), Arrays.copyOfRange(board[8], 2, 3));
                    int[] column4 = Sudoku.merge(Arrays.copyOfRange(board[0], 3, 4), Arrays.copyOfRange(board[1], 3, 4), Arrays.copyOfRange(board[2], 3, 4), Arrays.copyOfRange(board[3], 3, 4), Arrays.copyOfRange(board[4], 3, 4), Arrays.copyOfRange(board[5], 3, 4), Arrays.copyOfRange(board[6], 3, 4), Arrays.copyOfRange(board[7], 3, 4), Arrays.copyOfRange(board[8], 3, 4));
                    int[] column5 = Sudoku.merge(Arrays.copyOfRange(board[0], 4, 5), Arrays.copyOfRange(board[1], 4, 5), Arrays.copyOfRange(board[2], 4, 5), Arrays.copyOfRange(board[3], 4, 5), Arrays.copyOfRange(board[4], 4, 5), Arrays.copyOfRange(board[5], 4, 5), Arrays.copyOfRange(board[6], 4, 5), Arrays.copyOfRange(board[7], 4, 5), Arrays.copyOfRange(board[8], 4, 5));
                    int[] column6 = Sudoku.merge(Arrays.copyOfRange(board[0], 5, 6), Arrays.copyOfRange(board[1], 5, 6), Arrays.copyOfRange(board[2], 5, 6), Arrays.copyOfRange(board[3], 5, 6), Arrays.copyOfRange(board[4], 5, 6), Arrays.copyOfRange(board[5], 5, 6), Arrays.copyOfRange(board[6], 5, 6), Arrays.copyOfRange(board[7], 5, 6), Arrays.copyOfRange(board[8], 5, 6));
                    int[] column7 = Sudoku.merge(Arrays.copyOfRange(board[0], 6, 7), Arrays.copyOfRange(board[1], 6, 7), Arrays.copyOfRange(board[2], 6, 7), Arrays.copyOfRange(board[3], 6, 7), Arrays.copyOfRange(board[4], 6, 7), Arrays.copyOfRange(board[5], 6, 7), Arrays.copyOfRange(board[6], 6, 7), Arrays.copyOfRange(board[7], 6, 7), Arrays.copyOfRange(board[8], 6, 7));
                    int[] column8 = Sudoku.merge(Arrays.copyOfRange(board[0], 7, 8), Arrays.copyOfRange(board[1], 7, 8), Arrays.copyOfRange(board[2], 7, 8), Arrays.copyOfRange(board[3], 7, 8), Arrays.copyOfRange(board[4], 7, 8), Arrays.copyOfRange(board[5], 7, 8), Arrays.copyOfRange(board[6], 7, 8), Arrays.copyOfRange(board[7], 7, 8), Arrays.copyOfRange(board[8], 7, 8));
                    int[] column9 = Sudoku.merge(Arrays.copyOfRange(board[0], 8, 9), Arrays.copyOfRange(board[1], 8, 9), Arrays.copyOfRange(board[2], 8, 9), Arrays.copyOfRange(board[3], 8, 9), Arrays.copyOfRange(board[4], 8, 9), Arrays.copyOfRange(board[5], 8, 9), Arrays.copyOfRange(board[6], 8, 9), Arrays.copyOfRange(board[7], 8, 9), Arrays.copyOfRange(board[8], 8, 9));

                    if (Sudoku.dup(board[0])) {
                        subPanels1[0][0].setBackground(Color.red);
                        subPanels1[0][1].setBackground(Color.red);
                        subPanels1[0][2].setBackground(Color.red);
                        subPanels1[0][3].setBackground(Color.red);
                        subPanels1[0][4].setBackground(Color.red);
                        subPanels1[0][5].setBackground(Color.red);
                        subPanels1[0][6].setBackground(Color.red);
                        subPanels1[0][7].setBackground(Color.red);
                        subPanels1[0][8].setBackground(Color.red);
                    } else {
                        subPanels1[0][0].setBackground(Color.white);
                        subPanels1[0][1].setBackground(Color.white);
                        subPanels1[0][2].setBackground(Color.white);
                        subPanels1[0][3].setBackground(Color.white);
                        subPanels1[0][4].setBackground(Color.white);
                        subPanels1[0][5].setBackground(Color.white);
                        subPanels1[0][6].setBackground(Color.white);
                        subPanels1[0][7].setBackground(Color.white);
                        subPanels1[0][8].setBackground(Color.white);
                    }
                    if (Sudoku.dup(board[1])) {
                        subPanels1[1][0].setBackground(Color.red);
                        subPanels1[1][1].setBackground(Color.red);
                        subPanels1[1][2].setBackground(Color.red);
                        subPanels1[1][3].setBackground(Color.red);
                        subPanels1[1][4].setBackground(Color.red);
                        subPanels1[1][5].setBackground(Color.red);
                        subPanels1[1][6].setBackground(Color.red);
                        subPanels1[1][7].setBackground(Color.red);
                        subPanels1[1][8].setBackground(Color.red);
                    } else {
                        subPanels1[1][0].setBackground(Color.white);
                        subPanels1[1][1].setBackground(Color.white);
                        subPanels1[1][2].setBackground(Color.white);
                        subPanels1[1][3].setBackground(Color.white);
                        subPanels1[1][4].setBackground(Color.white);
                        subPanels1[1][5].setBackground(Color.white);
                        subPanels1[1][6].setBackground(Color.white);
                        subPanels1[1][7].setBackground(Color.white);
                        subPanels1[1][8].setBackground(Color.white);
                    }
                    if (Sudoku.dup(board[2])) {
                        subPanels1[2][0].setBackground(Color.red);
                        subPanels1[2][1].setBackground(Color.red);
                        subPanels1[2][2].setBackground(Color.red);
                        subPanels1[2][3].setBackground(Color.red);
                        subPanels1[2][4].setBackground(Color.red);
                        subPanels1[2][5].setBackground(Color.red);
                        subPanels1[2][6].setBackground(Color.red);
                        subPanels1[2][7].setBackground(Color.red);
                        subPanels1[2][8].setBackground(Color.red);
                    } else {
                        subPanels1[2][0].setBackground(Color.white);
                        subPanels1[2][1].setBackground(Color.white);
                        subPanels1[2][2].setBackground(Color.white);
                        subPanels1[2][3].setBackground(Color.white);
                        subPanels1[2][4].setBackground(Color.white);
                        subPanels1[2][5].setBackground(Color.white);
                        subPanels1[2][6].setBackground(Color.white);
                        subPanels1[2][7].setBackground(Color.white);
                        subPanels1[2][8].setBackground(Color.white);
                    }
                    if (Sudoku.dup(board[3])) {
                        subPanels1[3][0].setBackground(Color.red);
                        subPanels1[3][1].setBackground(Color.red);
                        subPanels1[3][2].setBackground(Color.red);
                        subPanels1[3][3].setBackground(Color.red);
                        subPanels1[3][4].setBackground(Color.red);
                        subPanels1[3][5].setBackground(Color.red);
                        subPanels1[3][6].setBackground(Color.red);
                        subPanels1[3][7].setBackground(Color.red);
                        subPanels1[3][8].setBackground(Color.red);
                    } else {
                        subPanels1[3][0].setBackground(Color.white);
                        subPanels1[3][1].setBackground(Color.white);
                        subPanels1[3][2].setBackground(Color.white);
                        subPanels1[3][3].setBackground(Color.white);
                        subPanels1[3][4].setBackground(Color.white);
                        subPanels1[3][5].setBackground(Color.white);
                        subPanels1[3][6].setBackground(Color.white);
                        subPanels1[3][7].setBackground(Color.white);
                        subPanels1[3][8].setBackground(Color.white);
                    }
                    if (Sudoku.dup(board[4])) {
                        subPanels1[4][0].setBackground(Color.red);
                        subPanels1[4][1].setBackground(Color.red);
                        subPanels1[4][2].setBackground(Color.red);
                        subPanels1[4][3].setBackground(Color.red);
                        subPanels1[4][4].setBackground(Color.red);
                        subPanels1[4][5].setBackground(Color.red);
                        subPanels1[4][6].setBackground(Color.red);
                        subPanels1[4][7].setBackground(Color.red);
                        subPanels1[4][8].setBackground(Color.red);

                    } else {
                        subPanels1[4][0].setBackground(Color.white);
                        subPanels1[4][1].setBackground(Color.white);
                        subPanels1[4][2].setBackground(Color.white);
                        subPanels1[4][3].setBackground(Color.white);
                        subPanels1[4][4].setBackground(Color.white);
                        subPanels1[4][5].setBackground(Color.white);
                        subPanels1[4][6].setBackground(Color.white);
                        subPanels1[4][7].setBackground(Color.white);
                        subPanels1[4][8].setBackground(Color.white);
                    }
                    if (Sudoku.dup(board[5])) {
                        subPanels1[5][0].setBackground(Color.red);
                        subPanels1[5][1].setBackground(Color.red);
                        subPanels1[5][2].setBackground(Color.red);
                        subPanels1[5][3].setBackground(Color.red);
                        subPanels1[5][4].setBackground(Color.red);
                        subPanels1[5][5].setBackground(Color.red);
                        subPanels1[5][6].setBackground(Color.red);
                        subPanels1[5][7].setBackground(Color.red);
                        subPanels1[5][8].setBackground(Color.red);
                    } else {
                        subPanels1[5][0].setBackground(Color.white);
                        subPanels1[5][1].setBackground(Color.white);
                        subPanels1[5][2].setBackground(Color.white);
                        subPanels1[5][3].setBackground(Color.white);
                        subPanels1[5][4].setBackground(Color.white);
                        subPanels1[5][5].setBackground(Color.white);
                        subPanels1[5][6].setBackground(Color.white);
                        subPanels1[5][7].setBackground(Color.white);
                        subPanels1[5][8].setBackground(Color.white);
                    }
                    if (Sudoku.dup(board[7])) {
                        subPanels1[7][0].setBackground(Color.red);
                        subPanels1[7][1].setBackground(Color.red);
                        subPanels1[7][2].setBackground(Color.red);
                        subPanels1[7][3].setBackground(Color.red);
                        subPanels1[7][4].setBackground(Color.red);
                        subPanels1[7][5].setBackground(Color.red);
                        subPanels1[7][6].setBackground(Color.red);
                        subPanels1[7][7].setBackground(Color.red);
                        subPanels1[7][8].setBackground(Color.red);
                    } else {
                        subPanels1[7][0].setBackground(Color.white);
                        subPanels1[7][1].setBackground(Color.white);
                        subPanels1[7][2].setBackground(Color.white);
                        subPanels1[7][3].setBackground(Color.white);
                        subPanels1[7][4].setBackground(Color.white);
                        subPanels1[7][5].setBackground(Color.white);
                        subPanels1[7][6].setBackground(Color.white);
                        subPanels1[7][7].setBackground(Color.white);
                        subPanels1[7][8].setBackground(Color.white);
                    }
                    if (Sudoku.dup(board[8])) {
                        subPanels1[8][0].setBackground(Color.red);
                        subPanels1[8][1].setBackground(Color.red);
                        subPanels1[8][2].setBackground(Color.red);
                        subPanels1[8][3].setBackground(Color.red);
                        subPanels1[8][4].setBackground(Color.red);
                        subPanels1[8][5].setBackground(Color.red);
                        subPanels1[8][6].setBackground(Color.red);
                        subPanels1[8][7].setBackground(Color.red);
                        subPanels1[8][8].setBackground(Color.red);
                    } else {
                        subPanels1[8][0].setBackground(Color.white);
                        subPanels1[8][1].setBackground(Color.white);
                        subPanels1[8][2].setBackground(Color.white);
                        subPanels1[8][3].setBackground(Color.white);
                        subPanels1[8][4].setBackground(Color.white);
                        subPanels1[8][5].setBackground(Color.white);
                        subPanels1[8][6].setBackground(Color.white);
                        subPanels1[8][7].setBackground(Color.white);
                        subPanels1[8][8].setBackground(Color.white);
                    }
                    if (Sudoku.dup(board[6])) {
                        subPanels1[6][0].setBackground(Color.red);
                        subPanels1[6][1].setBackground(Color.red);
                        subPanels1[6][2].setBackground(Color.red);
                        subPanels1[6][3].setBackground(Color.red);
                        subPanels1[6][4].setBackground(Color.red);
                        subPanels1[6][5].setBackground(Color.red);
                        subPanels1[6][6].setBackground(Color.red);
                        subPanels1[6][7].setBackground(Color.red);
                        subPanels1[6][8].setBackground(Color.red);
                    } else {
                        subPanels1[6][0].setBackground(Color.white);
                        subPanels1[6][1].setBackground(Color.white);
                        subPanels1[6][2].setBackground(Color.white);
                        subPanels1[6][3].setBackground(Color.white);
                        subPanels1[6][4].setBackground(Color.white);
                        subPanels1[6][5].setBackground(Color.white);
                        subPanels1[6][6].setBackground(Color.white);
                        subPanels1[6][7].setBackground(Color.white);
                        subPanels1[6][8].setBackground(Color.white);
                    }
                    if (Sudoku.dup(column1)) {
                        subPanels2[0][0].setBackground(Color.blue);
                        subPanels2[1][0].setBackground(Color.blue);
                        subPanels2[2][0].setBackground(Color.blue);
                        subPanels2[3][0].setBackground(Color.blue);
                        subPanels2[4][0].setBackground(Color.blue);
                        subPanels2[5][0].setBackground(Color.blue);
                        subPanels2[6][0].setBackground(Color.blue);
                        subPanels2[7][0].setBackground(Color.blue);
                        subPanels2[8][0].setBackground(Color.blue);
                    } else {
                        subPanels2[0][0].setBackground(Color.white);
                        subPanels2[1][0].setBackground(Color.white);
                        subPanels2[2][0].setBackground(Color.white);
                        subPanels2[3][0].setBackground(Color.white);
                        subPanels2[4][0].setBackground(Color.white);
                        subPanels2[5][0].setBackground(Color.white);
                        subPanels2[6][0].setBackground(Color.white);
                        subPanels2[7][0].setBackground(Color.white);
                        subPanels2[8][0].setBackground(Color.white);
                    }
                    if (Sudoku.dup(column2)) {
                        subPanels2[0][1].setBackground(Color.blue);
                        subPanels2[1][1].setBackground(Color.blue);
                        subPanels2[2][1].setBackground(Color.blue);
                        subPanels2[3][1].setBackground(Color.blue);
                        subPanels2[4][1].setBackground(Color.blue);
                        subPanels2[5][1].setBackground(Color.blue);
                        subPanels2[6][1].setBackground(Color.blue);
                        subPanels2[7][1].setBackground(Color.blue);
                        subPanels2[8][1].setBackground(Color.blue);
                    } else {
                        subPanels2[0][1].setBackground(Color.white);
                        subPanels2[1][1].setBackground(Color.white);
                        subPanels2[2][1].setBackground(Color.white);
                        subPanels2[3][1].setBackground(Color.white);
                        subPanels2[4][1].setBackground(Color.white);
                        subPanels2[5][1].setBackground(Color.white);
                        subPanels2[6][1].setBackground(Color.white);
                        subPanels2[7][1].setBackground(Color.white);
                        subPanels2[8][1].setBackground(Color.white);
                    }
                    if (Sudoku.dup(column3)) {
                        subPanels2[0][2].setBackground(Color.blue);
                        subPanels2[1][2].setBackground(Color.blue);
                        subPanels2[2][2].setBackground(Color.blue);
                        subPanels2[3][2].setBackground(Color.blue);
                        subPanels2[4][2].setBackground(Color.blue);
                        subPanels2[5][2].setBackground(Color.blue);
                        subPanels2[6][2].setBackground(Color.blue);
                        subPanels2[7][2].setBackground(Color.blue);
                        subPanels2[8][2].setBackground(Color.blue);
                    } else {
                        subPanels2[0][2].setBackground(Color.white);
                        subPanels2[1][2].setBackground(Color.white);
                        subPanels2[2][2].setBackground(Color.white);
                        subPanels2[3][2].setBackground(Color.white);
                        subPanels2[4][2].setBackground(Color.white);
                        subPanels2[5][2].setBackground(Color.white);
                        subPanels2[6][2].setBackground(Color.white);
                        subPanels2[7][2].setBackground(Color.white);
                        subPanels2[8][2].setBackground(Color.white);
                    }
                    if (Sudoku.dup(column4)) {
                        subPanels2[0][3].setBackground(Color.blue);
                        subPanels2[1][3].setBackground(Color.blue);
                        subPanels2[2][3].setBackground(Color.blue);
                        subPanels2[3][3].setBackground(Color.blue);
                        subPanels2[4][3].setBackground(Color.blue);
                        subPanels2[5][3].setBackground(Color.blue);
                        subPanels2[6][3].setBackground(Color.blue);
                        subPanels2[7][3].setBackground(Color.blue);
                        subPanels2[8][3].setBackground(Color.blue);
                    } else {
                        subPanels2[0][3].setBackground(Color.white);
                        subPanels2[1][3].setBackground(Color.white);
                        subPanels2[2][3].setBackground(Color.white);
                        subPanels2[3][3].setBackground(Color.white);
                        subPanels2[4][3].setBackground(Color.white);
                        subPanels2[5][3].setBackground(Color.white);
                        subPanels2[6][3].setBackground(Color.white);
                        subPanels2[7][3].setBackground(Color.white);
                        subPanels2[8][3].setBackground(Color.white);
                    }
                    if (Sudoku.dup(column5)) {
                        subPanels2[0][4].setBackground(Color.blue);
                        subPanels2[1][4].setBackground(Color.blue);
                        subPanels2[2][4].setBackground(Color.blue);
                        subPanels2[3][4].setBackground(Color.blue);
                        subPanels2[4][4].setBackground(Color.blue);
                        subPanels2[5][4].setBackground(Color.blue);
                        subPanels2[6][4].setBackground(Color.blue);
                        subPanels2[7][4].setBackground(Color.blue);
                        subPanels2[8][4].setBackground(Color.blue);
                    } else {
                        subPanels2[0][4].setBackground(Color.white);
                        subPanels2[1][4].setBackground(Color.white);
                        subPanels2[2][4].setBackground(Color.white);
                        subPanels2[3][4].setBackground(Color.white);
                        subPanels2[4][4].setBackground(Color.white);
                        subPanels2[5][4].setBackground(Color.white);
                        subPanels2[6][4].setBackground(Color.white);
                        subPanels2[7][4].setBackground(Color.white);
                        subPanels2[8][4].setBackground(Color.white);
                    }
                    if (Sudoku.dup(column6)) {
                        subPanels2[0][5].setBackground(Color.blue);
                        subPanels2[1][5].setBackground(Color.blue);
                        subPanels2[2][5].setBackground(Color.blue);
                        subPanels2[3][5].setBackground(Color.blue);
                        subPanels2[4][5].setBackground(Color.blue);
                        subPanels2[5][5].setBackground(Color.blue);
                        subPanels2[6][5].setBackground(Color.blue);
                        subPanels2[7][5].setBackground(Color.blue);
                        subPanels2[8][5].setBackground(Color.blue);
                    } else {
                        subPanels2[0][5].setBackground(Color.white);
                        subPanels2[1][5].setBackground(Color.white);
                        subPanels2[2][5].setBackground(Color.white);
                        subPanels2[3][5].setBackground(Color.white);
                        subPanels2[4][5].setBackground(Color.white);
                        subPanels2[5][5].setBackground(Color.white);
                        subPanels2[6][5].setBackground(Color.white);
                        subPanels2[7][5].setBackground(Color.white);
                        subPanels2[8][5].setBackground(Color.white);
                    }
                    if (Sudoku.dup(column7)) {
                        subPanels2[0][6].setBackground(Color.blue);
                        subPanels2[1][6].setBackground(Color.blue);
                        subPanels2[2][6].setBackground(Color.blue);
                        subPanels2[3][6].setBackground(Color.blue);
                        subPanels2[4][6].setBackground(Color.blue);
                        subPanels2[5][6].setBackground(Color.blue);
                        subPanels2[6][6].setBackground(Color.blue);
                        subPanels2[7][6].setBackground(Color.blue);
                        subPanels2[8][6].setBackground(Color.blue);
                    } else {
                        subPanels2[0][6].setBackground(Color.white);
                        subPanels2[1][6].setBackground(Color.white);
                        subPanels2[2][6].setBackground(Color.white);
                        subPanels2[3][6].setBackground(Color.white);
                        subPanels2[4][6].setBackground(Color.white);
                        subPanels2[5][6].setBackground(Color.white);
                        subPanels2[6][6].setBackground(Color.white);
                        subPanels2[7][6].setBackground(Color.white);
                        subPanels2[8][6].setBackground(Color.white);
                    }
                    if (Sudoku.dup(column8)) {
                        subPanels2[0][7].setBackground(Color.blue);
                        subPanels2[1][7].setBackground(Color.blue);
                        subPanels2[2][7].setBackground(Color.blue);
                        subPanels2[3][7].setBackground(Color.blue);
                        subPanels2[4][7].setBackground(Color.blue);
                        subPanels2[5][7].setBackground(Color.blue);
                        subPanels2[6][7].setBackground(Color.blue);
                        subPanels2[7][7].setBackground(Color.blue);
                        subPanels2[8][7].setBackground(Color.blue);
                    } else {
                        subPanels2[0][7].setBackground(Color.white);
                        subPanels2[1][7].setBackground(Color.white);
                        subPanels2[2][7].setBackground(Color.white);
                        subPanels2[3][7].setBackground(Color.white);
                        subPanels2[4][7].setBackground(Color.white);
                        subPanels2[5][7].setBackground(Color.white);
                        subPanels2[6][7].setBackground(Color.white);
                        subPanels2[7][7].setBackground(Color.white);
                        subPanels2[8][7].setBackground(Color.white);
                    }
                    if (Sudoku.dup(column9)) {
                        subPanels2[0][8].setBackground(Color.blue);
                        subPanels2[1][8].setBackground(Color.blue);
                        subPanels2[2][8].setBackground(Color.blue);
                        subPanels2[3][8].setBackground(Color.blue);
                        subPanels2[4][8].setBackground(Color.blue);
                        subPanels2[5][8].setBackground(Color.blue);
                        subPanels2[6][8].setBackground(Color.blue);
                        subPanels2[7][8].setBackground(Color.blue);
                        subPanels2[8][8].setBackground(Color.blue);
                    } else {
                        subPanels2[0][8].setBackground(Color.white);
                        subPanels2[1][8].setBackground(Color.white);
                        subPanels2[2][8].setBackground(Color.white);
                        subPanels2[3][8].setBackground(Color.white);
                        subPanels2[4][8].setBackground(Color.white);
                        subPanels2[5][8].setBackground(Color.white);
                        subPanels2[6][8].setBackground(Color.white);
                        subPanels2[7][8].setBackground(Color.white);
                        subPanels2[8][8].setBackground(Color.white);
                    }
                    if (Sudoku.dup(square1)) {
                        subPanels3[0][0].setBackground(Color.magenta);
                        subPanels3[0][1].setBackground(Color.magenta);
                        subPanels3[0][2].setBackground(Color.magenta);
                        subPanels3[1][0].setBackground(Color.magenta);
                        subPanels3[2][0].setBackground(Color.magenta);
                        subPanels3[1][2].setBackground(Color.magenta);
                        subPanels3[1][1].setBackground(Color.magenta);
                        subPanels3[2][1].setBackground(Color.magenta);
                        subPanels3[2][2].setBackground(Color.magenta);
                    } else {
                        subPanels3[0][0].setBackground(Color.white);
                        subPanels3[0][1].setBackground(Color.white);
                        subPanels3[0][2].setBackground(Color.white);
                        subPanels3[1][0].setBackground(Color.white);
                        subPanels3[2][0].setBackground(Color.white);
                        subPanels3[1][2].setBackground(Color.white);
                        subPanels3[1][1].setBackground(Color.white);
                        subPanels3[2][1].setBackground(Color.white);
                        subPanels3[2][2].setBackground(Color.white);
                    }
                    if (Sudoku.dup(square4)) {
                        subPanels3[3][0].setBackground(Color.magenta);
                        subPanels3[3][1].setBackground(Color.magenta);
                        subPanels3[3][2].setBackground(Color.magenta);
                        subPanels3[4][0].setBackground(Color.magenta);
                        subPanels3[4][1].setBackground(Color.magenta);
                        subPanels3[4][2].setBackground(Color.magenta);
                        subPanels3[5][0].setBackground(Color.magenta);
                        subPanels3[5][1].setBackground(Color.magenta);
                        subPanels3[5][2].setBackground(Color.magenta);
                    } else {
                        subPanels3[3][0].setBackground(Color.white);
                        subPanels3[3][1].setBackground(Color.white);
                        subPanels3[3][2].setBackground(Color.white);
                        subPanels3[4][0].setBackground(Color.white);
                        subPanels3[4][1].setBackground(Color.white);
                        subPanels3[4][2].setBackground(Color.white);
                        subPanels3[5][0].setBackground(Color.white);
                        subPanels3[5][1].setBackground(Color.white);
                        subPanels3[5][2].setBackground(Color.white);
                    }
                    if (Sudoku.dup(square7)) {
                        subPanels3[6][0].setBackground(Color.magenta);
                        subPanels3[6][1].setBackground(Color.magenta);
                        subPanels3[6][2].setBackground(Color.magenta);
                        subPanels3[7][0].setBackground(Color.magenta);
                        subPanels3[7][1].setBackground(Color.magenta);
                        subPanels3[7][2].setBackground(Color.magenta);
                        subPanels3[8][0].setBackground(Color.magenta);
                        subPanels3[8][1].setBackground(Color.magenta);
                        subPanels3[8][2].setBackground(Color.magenta);
                    } else {
                        subPanels3[6][0].setBackground(Color.white);
                        subPanels3[6][1].setBackground(Color.white);
                        subPanels3[6][2].setBackground(Color.white);
                        subPanels3[7][0].setBackground(Color.white);
                        subPanels3[7][1].setBackground(Color.white);
                        subPanels3[7][2].setBackground(Color.white);
                        subPanels3[8][0].setBackground(Color.white);
                        subPanels3[8][1].setBackground(Color.white);
                        subPanels3[8][2].setBackground(Color.white);
                    }
                    if (Sudoku.dup(square2)) {
                        subPanels3[0][3].setBackground(Color.magenta);
                        subPanels3[0][4].setBackground(Color.magenta);
                        subPanels3[0][5].setBackground(Color.magenta);
                        subPanels3[1][3].setBackground(Color.magenta);
                        subPanels3[1][4].setBackground(Color.magenta);
                        subPanels3[1][5].setBackground(Color.magenta);
                        subPanels3[2][3].setBackground(Color.magenta);
                        subPanels3[2][4].setBackground(Color.magenta);
                        subPanels3[2][5].setBackground(Color.magenta);
                    } else {
                        subPanels3[0][3].setBackground(Color.white);
                        subPanels3[0][4].setBackground(Color.white);
                        subPanels3[0][5].setBackground(Color.white);
                        subPanels3[1][3].setBackground(Color.white);
                        subPanels3[1][4].setBackground(Color.white);
                        subPanels3[1][5].setBackground(Color.white);
                        subPanels3[2][3].setBackground(Color.white);
                        subPanels3[2][4].setBackground(Color.white);
                        subPanels3[2][5].setBackground(Color.white);
                    }
                    if (Sudoku.dup(square3)) {
                        subPanels3[0][6].setBackground(Color.magenta);
                        subPanels3[0][7].setBackground(Color.magenta);
                        subPanels3[0][8].setBackground(Color.magenta);
                        subPanels3[1][6].setBackground(Color.magenta);
                        subPanels3[1][7].setBackground(Color.magenta);
                        subPanels3[1][8].setBackground(Color.magenta);
                        subPanels3[2][6].setBackground(Color.magenta);
                        subPanels3[2][7].setBackground(Color.magenta);
                        subPanels3[2][8].setBackground(Color.magenta);
                    } else {
                        subPanels3[0][6].setBackground(Color.white);
                        subPanels3[0][7].setBackground(Color.white);
                        subPanels3[0][8].setBackground(Color.white);
                        subPanels3[1][6].setBackground(Color.white);
                        subPanels3[1][7].setBackground(Color.white);
                        subPanels3[1][8].setBackground(Color.white);
                        subPanels3[2][6].setBackground(Color.white);
                        subPanels3[2][7].setBackground(Color.white);
                        subPanels3[2][8].setBackground(Color.white);
                    }
                    if (Sudoku.dup(square6)) {
                        subPanels3[3][6].setBackground(Color.magenta);
                        subPanels3[3][7].setBackground(Color.magenta);
                        subPanels3[3][8].setBackground(Color.magenta);
                        subPanels3[4][6].setBackground(Color.magenta);
                        subPanels3[4][7].setBackground(Color.magenta);
                        subPanels3[4][8].setBackground(Color.magenta);
                        subPanels3[5][6].setBackground(Color.magenta);
                        subPanels3[5][7].setBackground(Color.magenta);
                        subPanels3[5][8].setBackground(Color.magenta);
                    } else {
                        subPanels3[3][6].setBackground(Color.white);
                        subPanels3[3][7].setBackground(Color.white);
                        subPanels3[3][8].setBackground(Color.white);
                        subPanels3[4][6].setBackground(Color.white);
                        subPanels3[4][7].setBackground(Color.white);
                        subPanels3[4][8].setBackground(Color.white);
                        subPanels3[5][6].setBackground(Color.white);
                        subPanels3[5][7].setBackground(Color.white);
                        subPanels3[5][8].setBackground(Color.white);
                    }
                    if (Sudoku.dup(square8)) {
                        subPanels3[6][3].setBackground(Color.magenta);
                        subPanels3[6][4].setBackground(Color.magenta);
                        subPanels3[6][5].setBackground(Color.magenta);
                        subPanels3[7][3].setBackground(Color.magenta);
                        subPanels3[7][4].setBackground(Color.magenta);
                        subPanels3[7][5].setBackground(Color.magenta);
                        subPanels3[8][3].setBackground(Color.magenta);
                        subPanels3[8][4].setBackground(Color.magenta);
                        subPanels3[8][5].setBackground(Color.magenta);
                    } else {
                        subPanels3[6][3].setBackground(Color.white);
                        subPanels3[6][4].setBackground(Color.white);
                        subPanels3[6][5].setBackground(Color.white);
                        subPanels3[7][3].setBackground(Color.white);
                        subPanels3[7][4].setBackground(Color.white);
                        subPanels3[7][5].setBackground(Color.white);
                        subPanels3[8][3].setBackground(Color.white);
                        subPanels3[8][4].setBackground(Color.white);
                        subPanels3[8][5].setBackground(Color.white);
                    }
                    if (Sudoku.dup(square9)) {
                        subPanels3[6][6].setBackground(Color.magenta);
                        subPanels3[6][7].setBackground(Color.magenta);
                        subPanels3[6][8].setBackground(Color.magenta);
                        subPanels3[7][6].setBackground(Color.magenta);
                        subPanels3[7][7].setBackground(Color.magenta);
                        subPanels3[7][8].setBackground(Color.magenta);
                        subPanels3[8][6].setBackground(Color.magenta);
                        subPanels3[8][7].setBackground(Color.magenta);
                        subPanels3[8][8].setBackground(Color.magenta);
                    } else {
                        subPanels3[6][6].setBackground(Color.white);
                        subPanels3[6][7].setBackground(Color.white);
                        subPanels3[6][8].setBackground(Color.white);
                        subPanels3[7][6].setBackground(Color.white);
                        subPanels3[7][7].setBackground(Color.white);
                        subPanels3[7][8].setBackground(Color.white);
                        subPanels3[8][6].setBackground(Color.white);
                        subPanels3[8][7].setBackground(Color.white);
                        subPanels3[8][8].setBackground(Color.white);
                    }
                    if (Sudoku.dup(square5)) {
                        subPanels3[3][3].setBackground(Color.magenta);
                        subPanels3[3][4].setBackground(Color.magenta);
                        subPanels3[3][5].setBackground(Color.magenta);
                        subPanels3[4][3].setBackground(Color.magenta);
                        subPanels3[4][4].setBackground(Color.magenta);
                        subPanels3[4][5].setBackground(Color.magenta);
                        subPanels3[5][3].setBackground(Color.magenta);
                        subPanels3[5][4].setBackground(Color.magenta);
                        subPanels3[5][5].setBackground(Color.magenta);
                    } else {
                        subPanels3[3][3].setBackground(Color.white);
                        subPanels3[3][4].setBackground(Color.white);
                        subPanels3[3][5].setBackground(Color.white);
                        subPanels3[4][3].setBackground(Color.white);
                        subPanels3[4][4].setBackground(Color.white);
                        subPanels3[4][5].setBackground(Color.white);
                        subPanels3[5][3].setBackground(Color.white);
                        subPanels3[5][4].setBackground(Color.white);
                        subPanels3[5][5].setBackground(Color.white);
                    }

                    winOrNot.setText("Coś jest jeszcze nie tak");
                }
            }
        }

    }

    public boolean hasDuplicatesInRows(JTextField[][] text) {
        for (int row = 0; row < text.length; row++) {
            for (int col = 0; col < text[row].length; col++) {
                int num = Integer.parseInt(text[row][col].getText());
                for (int otherCol = col + 1; otherCol < text.length; otherCol++) {
                    if (num == Integer.parseInt(text[row][otherCol].getText())) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    boolean checkSquares(JTextField[][] grid) {
        List<Integer> values = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));
        List<Integer> values1 = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));
        List<Integer> x = new ArrayList<>(Arrays.asList(0, 3, 6));
        for (int row:x) {
            for (int col:x){
                for (int i = 0; i < 3; i++){
                    for (int j = 0; j < 3; j++){
                        values.remove((Integer) Integer.parseInt(grid[row+i][col+j].getText()));
                    }
                }
                if (values.isEmpty()){
                    values = values1;
                    continue;
                }
                else {
                    return false;
                }

            }

        }
        return true;
    }
    public static void saveEdit(String pathOfFile,  JTextField[][] text) {
        try {

            StringBuilder builder = new StringBuilder();
            for(int i = 0; i < 9; i++)
            {
                for(int j = 0; j < 9; j++)
                {if(text[i][j].isEditable()){
                    builder.append("1");
                }else{builder.append("0");}
                    //builder.append(text[i][j].getText()+"");
                    if(j < 8)
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
    public static void saveGame(String pathOfFile,  JTextField[][] text) {
        try {

            StringBuilder builder = new StringBuilder();
            for(int i = 0; i < 9; i++)
            {
                for(int j = 0; j < 9; j++)
                {
                    //builder.append(text[i][j].isEditable());
                    builder.append(text[i][j].getText()+"");
                    if(j < 8)
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
            BufferedReader reader = new BufferedReader(new FileReader(savedGameFile));
            String line = "";
            int row = 0;
            while((line = reader.readLine()) != null)
            {
                String[] cols = line.split(",");
                int col = 0;
                for(String  c : cols)
                {
                    board[row][col] = Integer.parseInt(c);
                    col++;
                }
                row++;
            }
            reader.close();
        }catch (Exception err){
            System.out.println("\nNie ma takiej ścieżki:  " + pathOfFile);
            System.out.println(err.getMessage());
        }
        return board;
    }

    public static void main(String... args) {
        //new Panel();
    }
}