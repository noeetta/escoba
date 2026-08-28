package escoba;
import java.util.ArrayList;

public abstract class Player {
    protected String name;
    protected int points;
    protected int escobas;
    protected ArrayList<Card> hand; 
    protected ArrayList<Card> capturedCards;
    
    
    //métodos
    public Player(String name, int points, int escobas, ArrayList<Card> hand, ArrayList<Card> capturedCards){
        this.name = name;
        this.points = points;
        this.escobas = escobas;
        this.hand = new ArrayList<>();
        this.capturedCards = new ArrayList<>();
    }

    public String getName() { return name; }
    public ArrayList<Card> getHand(){ return hand;}
    public void receiveCard(Card cardr){ hand.add(cardr); } // Añadimos cartas a nuestra mano

    
    public int getEscobas(){
        return escobas;
    }
    
    public int getPoints(){
        return points;
    }
    
    public void addPoints(){
        points += points;
    }
    
    public String toString(){
        String namePlayer = String.valueOf(name);
        return namePlayer + " has " + points + " points";
    }

    
}
