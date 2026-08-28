package escoba;
import java.util.ArrayList;
import java.util.Scanner;

public class GameController implements Jugable{
    private Table table = new Table();
    private GameView view = new GameView();
    private ArrayList<Player> players = new ArrayList<>();
    private int currentPlayerIndex;
    private boolean gameOver;
    
    
    Scanner scan = new Scanner (System.in);
    
    //métodos
    public void play(){}
    
    public void setupGame(){}
    
    public int countPoints(){
        int points = 0;
        points =+ Player.getEscobas();
        return points;
    }
}
