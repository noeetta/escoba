package escoba;
import java.util.Scanner;

public class GameView {
    
    Scanner scan = new Scanner (System.in);
    
    
    //métodos
    public GameView(){}
    
    public void showWelcome(){
        System.out.println("-------------------Bienvenido al juego de la escoba-------------------");
    }
    
    //public void showGreeting(){}¿?
    
    public void askPlayerSetUp(){}
    
    public void showTableCards(){
        System.out.println("Cartas sobre la mesa: ");
        for(Card nameCard : onTableCards){
            System.out.println(nameCard);
        }
    }
    
    public void showPlayerHand(){
        System.out.println("Las cartas en la mano son: ");
        for(Card card : hand){
            System.out.println(hand);
        }
    
    }
    
    public void TurnInfo(){}
    
}
