package Main;

public class Player {
	  MainGameField gameField;
	  protected int sign;
	  public Player(int sign)
	  {
	    this.sign = sign;
	  }
	 
	  boolean shot(int x, int y)
	  {
	    gameField = MainGameField.getInstance();
	    if (!gameField.isCellBusy(x, y))
	    {
	      gameField.cell[x][y] = sign;
	      return true;
	    }
	    return false;
	  }
	  boolean win()
	  {
	    return gameField.checkWin(this.sign);
	  }
	}
