import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToeGame extends JFrame {
    private JButton[][] buttons;
    private boolean xTurn = true;

    public TicTacToeGame() {
        setTitle("Tic-Tac-Toe");
        setSize(300, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        buttons = new JButton[3][3];
        JPanel panel = new JPanel(new GridLayout(3, 3));
        initializeButtons(panel);
        getContentPane().add(panel);

        setVisible(true);
    }

    private void initializeButtons(JPanel panel) {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                JButton button = new JButton();
                button.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 40));
                buttons[row][col] = button;
                panel.add(button);

                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (button.getText().isEmpty()) {
                            if (xTurn) {
                                button.setText("X");
                            } else {
                                button.setText("O");
                            }
                            xTurn = !xTurn;
                            checkWinCondition();
                        }
                    }
                });
            }
        }
    }

    private void checkWinCondition() {
        String winner = "";
        // Check rows
        for (int row = 0; row < 3; row++) {
            if (!buttons[row][0].getText().isEmpty() &&
                    buttons[row][0].getText().equals(buttons[row][1].getText()) &&
                    buttons[row][0].getText().equals(buttons[row][2].getText())) {
                winner = buttons[row][0].getText();
                break;
            }
        }

        // Check columns
        for (int col = 0; col < 3; col++) {
            if (!buttons[0][col].getText().isEmpty() &&
                    buttons[0][col].getText().equals(buttons[1][col].getText()) &&
                    buttons[0][col].getText().equals(buttons[2][col].getText())) {
                winner = buttons[0][col].getText();
                break;
            }
        }

        // Check diagonals
        if (!buttons[0][0].getText().isEmpty() &&
                buttons[0][0].getText().equals(buttons[1][1].getText()) &&
                buttons[0][0].getText().equals(buttons[2][2].getText())) {
            winner = buttons[0][0].getText();
        } else if (!buttons[0][2].getText().isEmpty() &&
                buttons[0][2].getText().equals(buttons[1][1].getText()) &&
                buttons[0][2].getText().equals(buttons[2][0].getText())) {
            winner = buttons[0][2].getText();
        }

        if (!winner.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Player " + winner + " wins!");
            resetGame();
        } else if (isBoardFull()) {
            JOptionPane.showMessageDialog(this, "It's a draw!");
            resetGame();
        }
    }

    private boolean isBoardFull() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                    if (buttons[row][col].getText().isEmpty()) {
                        return false;
                    }
            }
        }
        return true;
    }

    private void resetGame() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                buttons[row][col].setText("");
            }
        }
        xTurn = true;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new TicTacToeGame();
        });
    }
}
