package Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
 
public class MainForm extends JFrame {
  public MainForm()
  {
    setTitle("Крестики нолики");
    setBounds(600, 600, 755, 825);
    setResizable(false);
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    MainGameField gameField = MainGameField.getInstance();
    JPanel buttonPanel = new JPanel(new GridLayout());
    add(gameField, BorderLayout.CENTER);
    add(buttonPanel, BorderLayout.SOUTH);
    JButton btnStart = new JButton("Новая игра");
    buttonPanel.add(btnStart);
              btnStart.addActionListener(new ActionListener()
  {
    @Override
    public void actionPerformed(ActionEvent e)
  {
    gameField.startNewGame();
  }
              });
        setVisible(true);
      }
}