package Views;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import Helper.CustomDialogResult;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;

public class TicTacToeForm extends JFrame implements ActionListener{
    private JLabel playerXScoreLabel, playerOScoreLabel,highestScoreLabel;
    private int playerXScore = 0, playerOScore = 0,highestScore = 0;
    private JButton[] jButton = new JButton[9];
    private int[] gameChances = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    private int activePlayer = 0;
    private int[][] wps = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};
    private boolean isGameOver = false;

    ImageIcon againIcon = new ImageIcon("src/images/refresh.png"); 
    ImageIcon gameOver = new ImageIcon("src/images/game-over.png");
    ImageIcon winIcon = new ImageIcon("src/images/win.png");
    ImageIcon drawIcon = new ImageIcon("src/images/equality.png");
    ImageIcon occupiedIcon = new ImageIcon("src/images/occupied.png");

    public TicTacToeForm() {
        super.setTitle("Tic Tac Toe");
        super.setSize(800, 600); // Increased width for score display
        centerFrame();
        ImageIcon imageIcon = new ImageIcon("src/images/icon_image.png");
        setIconImage(imageIcon.getImage());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        createGameLayout();
        setVisible(true);
    }

    private void centerFrame() {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();
        Dimension frameSize = getSize();
        int x = (screenSize.width - frameSize.width) / 2;
        int y = (screenSize.height - frameSize.height) / 2;
        setLocation(x, y);
    }

    private void createGameLayout() {
        this.getContentPane().setBackground(Color.decode("#B7ADFF"));
        this.setLayout(new BorderLayout());

        JLabel heading, clockLabel;
        Font font = new Font("", Font.BOLD, 40);

        heading = new JLabel("Tic Tac Toe");
        heading.setFont(font);
        heading.setHorizontalAlignment(SwingConstants.CENTER);
        heading.setForeground(Color.white);
        this.add(heading, BorderLayout.NORTH);

        Font f = new Font("", Font.PLAIN, 20);
        clockLabel = new JLabel("Clock");
        clockLabel.setFont(f);
        clockLabel.setHorizontalAlignment(SwingConstants.CENTER);
        clockLabel.setForeground(Color.white);
        this.add(clockLabel, BorderLayout.SOUTH);

        Thread t = new Thread() {
            public void run() {
                try {
                    while (true) {
                        String dateTime = new Date().toLocaleString();
                        clockLabel.setText(dateTime);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        t.start();

        // Create a panel for scores on the left side
        JPanel scorePanel = new JPanel();
        scorePanel.setLayout(new GridBagLayout());
        scorePanel.setBackground(Color.decode("#9b5de5"));

        // Apply rounded border
        int borderRadius = 20; // Set the desired border radius
        Color borderColor = Color.decode("#B7ADFF"); // Set the border color
        scorePanel.setBorder(new RoundedBorder(borderRadius, borderColor));


        
        // Set the preferred size to occupy 20% of the height
        int height = getHeight();
        scorePanel.setPreferredSize(new Dimension(200, (int) (height * 20)));
        // GridBagConstraints for managing layout
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(0, 10, 5, 10);

        JLabel playerXLabel = new JLabel("Player O Score: ");
        playerXLabel.setForeground(Color.white);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;
        scorePanel.add(playerXLabel,gbc);

        playerXScoreLabel = new JLabel("0");
        playerXScoreLabel.setFont(new Font("", Font.BOLD, 20));
        playerXScoreLabel.setForeground(Color.white);
        gbc.gridx = 1;
        scorePanel.add(playerXScoreLabel,gbc);

        JLabel playerOLabel = new JLabel("Player X Score: ");
        playerOLabel.setForeground(Color.white);
        gbc.gridx = 0;
        gbc.gridy = 1;
        scorePanel.add(playerOLabel,gbc);

        playerOScoreLabel = new JLabel("0");
        playerOScoreLabel.setFont(new Font("", Font.BOLD, 20));
        playerOScoreLabel.setForeground(Color.white);
        gbc.gridx = 1;
        scorePanel.add(playerOScoreLabel,gbc);

        // Highest Score
        JLabel highestScoreTitle = new JLabel("Highest  Score: ");
        highestScoreTitle.setForeground(Color.white);
        gbc.gridx = 0;
        gbc.gridy = 2;
        scorePanel.add(highestScoreTitle,gbc);

        highestScoreLabel = new JLabel("0");
        highestScoreLabel.setFont(new Font("", Font.BOLD, 20));
        highestScoreLabel.setForeground(Color.white);
        gbc.gridx = 1;
        scorePanel.add(highestScoreLabel,gbc);

        this.add(scorePanel, BorderLayout.WEST);

        // Layout grid and buttons
        JPanel maiJPanel = new JPanel();
       maiJPanel.setLayout(new GridBagLayout()); // Use GridBagLayout for better control
       maiJPanel.setBackground(Color.decode("#B7ADFF"));

       

    Font buttFont = new Font("", Font.PLAIN, 30);

    GridBagConstraints buttonGbc = new GridBagConstraints();
    buttonGbc.gridx = 0;
    buttonGbc.gridy = 0;
    buttonGbc.insets = new Insets(5, 5, 5, 5); // Spacing between buttons

    for (int i = 0; i < 9; i++) {
        JButton btn = new JButton();
        btn.setFont(buttFont);
        // btn.setBackground(Color.white);
        // btn.setForeground(Color.black);
        btn.setOpaque(true);
        btn.setBorder(new RoundedBorder(borderRadius, borderColor)); // Add border
        btn.setPreferredSize(new Dimension(130, 130));
        btn.addActionListener(this);
        btn.setName(String.valueOf(i));

        // Add button to the grid
        maiJPanel.add(btn, buttonGbc);

        // Update the grid position for next button
        buttonGbc.gridx++;
        if ((i + 1) % 3 == 0) {
            buttonGbc.gridx = 0;
            buttonGbc.gridy++;
        }

        jButton[i] = btn;
    }

        this.add(maiJPanel, BorderLayout.CENTER);
    }

    private int showCustomConfirmDialog(JFrame jFrame, String message, boolean isButtons, ImageIcon imageIcon) {
        JDialog dialog = new JDialog(jFrame, true);
        dialog.setLayout(new GridBagLayout());
        dialog.setSize(370, 170);
        dialog.setLocationRelativeTo(jFrame);
    
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 3, 0, 3);
    
        // Image label
        if (imageIcon != null) {
            JLabel imageLabel = new JLabel(imageIcon);
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.weightx = 0.1;
            gbc.weighty = 0.1;
            gbc.gridwidth = 1;
            dialog.add(imageLabel, gbc);
        }
    
        // Message label
        JLabel messageLabel = new JLabel(message, SwingConstants.CENTER);
        gbc.gridx = (imageIcon == null) ? 0 : 1; // Adjust gridx based on whether there's an image
        gbc.gridy = 0;
        gbc.weightx = 0.9;
        gbc.weighty = 0.1;
        gbc.gridwidth = (imageIcon == null) ? GridBagConstraints.REMAINDER : GridBagConstraints.REMAINDER;
        dialog.add(messageLabel, gbc);
    
        // Button panel
        JPanel buttonPanel = new JPanel();
        if (isButtons) {
            RoundedButton yesButton = new RoundedButton("Yes", Color.decode("#B7ADFF"), Color.decode("#c690ff"), 20);
            RoundedButton noButton = new RoundedButton("No", Color.decode("#B7ADFF"), Color.decode("#c690ff"), 20);
            RoundedButton cButton = new RoundedButton("Cancel", Color.decode("#B7ADFF"), Color.decode("#c690ff"), 20);
    
            yesButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            yesButton.addActionListener(e -> {
                dialog.dispose();
                CustomDialogResult.setResult(JOptionPane.YES_OPTION);
            });
    
            noButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            noButton.addActionListener(e -> {
                dialog.dispose();
                CustomDialogResult.setResult(JOptionPane.NO_OPTION);
            });
    
            cButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            cButton.addActionListener(e -> {
                dialog.dispose();
                CustomDialogResult.setResult(JOptionPane.CANCEL_OPTION);
            });
    
            buttonPanel.add(yesButton);
            buttonPanel.add(noButton);
            buttonPanel.add(cButton);
        }
        // Add button panel to the dialog
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 1.0; // Fill the horizontal space
        gbc.gridwidth = GridBagConstraints.REMAINDER; // Span all columns
        dialog.add(buttonPanel, gbc);
    
        dialog.setVisible(true);
        return CustomDialogResult.getResult();
    }

    private int showCustomfirmDialog(JFrame jFrame, String message, ImageIcon imageIcon) {
        JDialog dialog = new JDialog(jFrame, true);
        dialog.setLayout(new GridBagLayout());
        dialog.setSize(370, 170);
        dialog.setLocationRelativeTo(jFrame);
    
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 3, 0, 3);
        
        // Image label
        if (imageIcon != null) {
            JLabel imageLabel = new JLabel(imageIcon);
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.weightx = 0.2; // Adjust weightx for proper resizing
            gbc.weighty = 0.2;
            gbc.gridwidth = 1;
            dialog.add(imageLabel, gbc);
        }
    
        // Message label
        JLabel messageLabel = new JLabel(message, SwingConstants.CENTER);
        gbc.gridx = (imageIcon == null) ? 0 : 1; // Adjust gridx based on whether there's an image
        gbc.gridy = 0;
        gbc.weightx = 0.8; // Adjust weightx for proper resizing
        gbc.weighty = 0.2;
        gbc.gridwidth = GridBagConstraints.REMAINDER; // Span all columns if image is present
        dialog.add(messageLabel, gbc);
    
        // Button panel
        JPanel buttonPanel = new JPanel();
        RoundedButton okButton = new RoundedButton("Okay", Color.decode("#B7ADFF"), Color.decode("#c690ff"), 20);
        okButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        okButton.addActionListener(e -> {
            dialog.dispose();
            CustomDialogResult.setResult(JOptionPane.OK_OPTION);
        });
        buttonPanel.add(okButton);
    
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 1.0; // Fill the horizontal space
        gbc.weighty = 0.0; // No vertical resizing needed
        gbc.gridwidth = GridBagConstraints.REMAINDER; // Span all columns
        dialog.add(buttonPanel, gbc);
    
        dialog.setVisible(true);
        return CustomDialogResult.getResult();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton currentButton = (JButton) e.getSource();
        String namString = currentButton.getName();
        int name = Integer.parseInt(namString.trim());

    if (isGameOver) {
        showCustomfirmDialog(this, "....Game is Over....",gameOver);
        resetGame();
        return;
    }

    if (gameChances[name] == 2) {
        if (activePlayer == 1) {
            currentButton.setIcon(new ImageIcon("src/images/X.png"));
            gameChances[name] = activePlayer;
            activePlayer = 0;
        } else {
            currentButton.setIcon(new ImageIcon("src/images/O.png"));
            gameChances[name] = activePlayer;
            activePlayer = 1;
        }

        // Check for a winner
        for (int[] temp : wps) {
            if (gameChances[temp[0]] == gameChances[temp[1]] && gameChances[temp[1]] == gameChances[temp[2]] && gameChances[temp[2]] != 2) {
                int winner = gameChances[temp[0]]; // 0 for X, 1 for O
                isGameOver = true;

                // Display correct winner
                String winnerMessage = winner == 0 ? "Player O" : "Player X";
                showCustomfirmDialog(this, "Congratulations... " + winnerMessage + " has won the game....",winIcon);

                // Update scores
                if (winner == 0) {
                    playerXScore  += 100;
                    playerXScoreLabel.setText(String.valueOf(playerXScore));
                } else if (winner == 1) {
                    playerOScore  += 100;
                    playerOScoreLabel.setText(String.valueOf(playerOScore));
                }

                int result = showCustomConfirmDialog(this, "Do you want to play again", true,againIcon);
                if (result == JOptionPane.YES_OPTION) {
                    resetGame();
                } else {
                    resetScores();
                    System.exit(0);
                }
                // Update highest score
                int currentHighest = Math.max(playerXScore, playerOScore);
                if (currentHighest > highestScore) {
                    highestScore = currentHighest;
                    highestScoreLabel.setText(String.valueOf(highestScore));
                }
                break;
            }
        }

        // Check for a draw
        int c = 0;
        for (int j : gameChances) {
            if (j == 2) {
                c++;
                break;
            }
        }
        if (c == 0 && !isGameOver) {
            showCustomfirmDialog(this, "It's a draw....No Player won the Game",drawIcon);
            int result = showCustomConfirmDialog(this, "Do you want to play again", true,againIcon);

            if (result == JOptionPane.YES_OPTION) {
                resetGame();
            } else {
                resetScores();
                System.exit(0);
            }
            isGameOver = true;
        }
    } else {
        showCustomfirmDialog(this, "Position Already Occupied",occupiedIcon);
    }
    }

    private void resetGame() {
        // Reset the board
        for (JButton button : jButton) {
            button.setIcon(null);
        }
        gameChances = new int[]{2, 2, 2, 2, 2, 2, 2, 2, 2};
        isGameOver = false;
        activePlayer = 0;
    }

    private void resetScores() {
        playerXScore = 0;
        playerXScoreLabel.setText("0");
        playerOScore = 0;
        playerOScoreLabel.setText("0");
        // Reset highest score
        highestScore = 0;
        highestScoreLabel.setText(String.valueOf(highestScore));
    }
}