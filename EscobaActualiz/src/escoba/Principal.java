package escoba;
import java.util.Scanner;
import java.util.ArrayList;

public class Principal {

    public static void main(String[] args) {
      
        do{
            Scanner entrada = new Scanner(System.in);
        
            System.out.println("Introduce el nombre del jugador: ");
            String name = entrada.nextLine();
        
            HumanPlayer player1 = new HumanPlayer(name, 0, 0, hand, capturedCards);
            System.out.println("Jugador creado");
            
            ComputerPlayer player2 = new ComputerPlayer('player2', 0, 0, hand, capturedCards);

        }while(points <= 21);
    } 
}
