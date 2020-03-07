import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.Random;

public class ColumnsGUI {
    private static Timer timer;
    private static JFrame frame;
    private static JPanel gameScreen;
    private static JPanel buttonPanel;
    private static JPanel boardPanel;
    private static JButton startButton;
    private static Columns game = new Columns();

    public static void main(String[] args)
    {
        frame = new JFrame("Team 5 - Columns");
        gameScreen = new JPanel();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(gameScreen);
        setGameScreen();

        frame.setSize(400, 700);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static void setGameScreen()
    {
        JPanel contentPane = (JPanel) frame.getContentPane();
        int condition = JComponent.WHEN_IN_FOCUSED_WINDOW;
        InputMap inputMap = contentPane.getInputMap(condition);
        ActionMap actionMap = contentPane.getActionMap();

        String up = "up";
        String down = "down";
        String left = "left";
        String right = "right";

        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0), up);
        actionMap.put(up, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.rotateFaller();
                boardPanel.updateUI();
                boardPanel.removeAll();
                setBoardPanel();
            }
        });

        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0), down);
        actionMap.put(down, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (game.getNumMatched() > 0)
                {
                    game.deleteMatched();
                }
                else
                {
                    game.dropFaller();
                    game.markMatched();
                }
                boardPanel.updateUI();
                boardPanel.removeAll();
                setBoardPanel();
            }
        });

        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0), left);
        actionMap.put(left, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.moveFallerLeft();
                boardPanel.updateUI();
                boardPanel.removeAll();
                setBoardPanel();
            }
        });

        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0), right);
        actionMap.put(right, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.moveFallerRight();
                boardPanel.updateUI();
                boardPanel.removeAll();
                setBoardPanel();
            }
        });

        frame.getContentPane().setLayout(new BorderLayout());
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        startButton = new JButton("START");
        buttonPanel.add(startButton);

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                {
                    if (timer != null)
                    {
                        timer.stop();
                    }
                    boardPanel.removeAll();
                    game = new Columns();
                    setBoardPanel();

                    Random rand = new Random();
                    int randCol = rand.nextInt(game.getCols()) + 1;
                    game.initializeFaller(randCol);
                    boardPanel.updateUI();
                    boardPanel.removeAll();
                    setBoardPanel();
                    timer = new Timer(500, new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            if (game.isFrozen())
                            {
                                Random rand = new Random();
                                int randCol = rand.nextInt(game.getCols()) + 1;
                                game.initializeFaller(randCol);
                            }
                            else
                            {
                                if (game.getNumMatched() > 0)
                                {
                                    game.deleteMatched();
                                }
                                else
                                {
                                    game.dropFaller();
                                    game.markMatched();
                                }
                            }

                            if (game.isGameOver())
                            {
                                game.handleGameOver();
                                //ADD GAME OVER LABEL HERE
                                timer.stop();
                            }

                            boardPanel.updateUI();
                            boardPanel.removeAll();
                            setBoardPanel();
                        }
                    });
                    timer.start();
                }
            }
        });
        frame.getContentPane().add(buttonPanel, BorderLayout.NORTH);

        boardPanel = new JPanel();
        boardPanel.setLayout(new GridLayout(game.getRows(), game.getCols()));
        setBoardPanel();
        frame.getContentPane().add(boardPanel, BorderLayout.CENTER);
    }

    public static void setBoardPanel()
    {
        for (int i = 0 ; i < game.getRows(); i++)
        {
            for (int j = 0; j < game.getCols(); j++)
            {
                JPanel piecePanel = new JPanel();
                piecePanel.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 1));
                if (game.getCell(i, j) ==  0)
                {
                    piecePanel.setBackground(Color.BLACK);
                }
                else if (game.getCell(i, j) == 1)
                {
                    piecePanel.setBackground(Color.RED);
                }
                else if (game.getCell(i, j) == 2)
                {
                    piecePanel.setBackground(Color.YELLOW);
                }
                else if (game.getCell(i, j) == 3)
                {
                    piecePanel.setBackground(Color.GREEN);
                }
                else if (game.getCell(i, j) == 4)
                {
                    piecePanel.setBackground(Color.BLUE);
                }
                else if (game.getCell(i, j) == 5)
                {
                    piecePanel.setBackground(Color.MAGENTA);
                }
                boardPanel.add(piecePanel);
            }
        }
    }
}