package escoba;
import java.util.ArrayList;
import java.util.Collections;

public class Table {
    private ArrayList<Card> deck;
    private ArrayList<Card> well;
    private ArrayList<Card> onTableCards;

    public Table(){
        deck = new ArrayList<>(); // Establecemos el ArrayList de la baraja
        well = new ArrayList<>(); // Establecemos el ArrayList del pozo
        String[] sticks = {"coins", "cups", "swords", "sticks"}; // Establecemos los nombres de los palos
        for (String s : sticks) { // Ejecutamos el bucle para los cuatro palos
            for (int i = 1; i <= 12; i++){ // Ejecutamos un segundo bucle para las diez cartas de cada palo, teniendo así 40
                if (i == 8 || i == 9) {continue;} // Saltamos el 8 y el 9
                deck.add(new Card(i, s)); // Añadimos a cada número un palo y lo metemos en el ArrayList de la baraja
            }
        }
    }
    
    public void shuffleDeck() {
        Collections.shuffle(deck);
    } // Barajamos las cartas
    
    public void dealInitial(){
       for(int i = 0; i < 2; i++){
       Card card = deck.remove(0);
       //hand.add(card); de human player, p.ej
         for(int j = 0; j < 2; j++){
         card = deck.remove(0);
         //hand.add(card); de computer player    
         }
       } 
        
       for(int i = 0; i < 3; i++){
           Card card = deck.remove(0);
           onTableCards.add(card);
       }
    }
    
    public void drawFromDeck(){ //entiendo que lo pones como ir repartiendo ya pasada la primera mano(¿?)
       for(int i = 0; i < 2; i++){
       Card card = deck.remove(0);
       //hand.add(card); de human player, p.ej
         for(int j = 0; j < 2; j++){
         card = deck.remove(0);
         //hand.add(card); de computer player    
         }
       }
    }
    
    public ArrayList<Card> getOnTableCards(){
        return onTableCards;
    }
    
    public void addCardsToTable(){
        Card card = deck.remove(0);
        onTableCards.add(card);  
    }
    
    public void removeCardsToTable(){ 
        /* Todas las cartas, como si fuese terminar todas las manos? 
        * Si es ir cogiendo cartas específicas, solo se me ocurre utilizar un actionListener o algo similar, 
        * no sé si se puede hacer eso con bucles.
        */
        
    }
    
}
