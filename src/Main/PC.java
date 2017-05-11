package Main;

import java.util.Random;

public class PC {
  MainGameField gameField;
  int playerSign = 0;
  protected int sign;
  public PC(int sign, int playerSign)
  {
    this.sign = sign;
    this.playerSign = playerSign;
  }
 
  boolean steap(int x, int y)
  {
    gameField = MainGameField.getInstance();
    x = -1;
    y = -1;
    boolean pc_win = false;
      for (int i = 0; i < 3; i++)
        for (int j = 0; j < 3; j++)
          if (!gameField.isCellBusy(i, j))
          {
            gameField.cell[i][j] = sign;
            if (gameField.checkWin(sign))
            {
              x = i;
              y = j;
              pc_win = true;
            }
            gameField.cell[i][j] = gameField.NOT_SIGN;
          }
      do
      {
        Random rnd = new Random();
        x = rnd.nextInt(3);
        y = rnd.nextInt(3);
      }
      while (gameField.isCellBusy(x, y));
    gameField.cell[x][y] = sign;
    return true;
  }
 
  boolean win()
  {
    return gameField.checkWin(this.sign);
  }
}