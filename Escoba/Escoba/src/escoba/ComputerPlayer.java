package escoba;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase que representa al jugador controlado por la CPU.
 * Extiende de Player e implementa la toma de decisiones para la Escoba.
 */
public class ComputerPlayer extends Player {

    /**
     * Constructor del jugador IA.
     * @param name Nombre de la CPU.
     * @param points Puntos acumulados en la partida.
     * @param escobas Número de escobas conseguidas.
     */
    public ComputerPlayer(String name, int points, int escobas) {
        super(name, points, escobas); // Llamada al constructor de la clase base Player
    }

    /**
     * "Mente de la IA": Evalúa todas las cartas en mano y en mesa para realizar el mejor movimiento.
     * 
     * @param tableCards Lista de cartas que están actualmente sobre la mesa.
     * @return Objeto Move con la carta que la IA decide jugar y las cartas que captura (si las hay).
     */
    public Move makeMove(ArrayList<Card> tableCards) {
        Move bestMove = null;
        int bestScore = -1; // Puntuación de la mejor jugada encontrada hasta el momento

        // ----------------------------------------------------------------------------------
        // Evaluamos todas las posibles capturas de 15 con cada carta de la mano
        // ----------------------------------------------------------------------------------
        for (Card handCard : new ArrayList<>(hand)) {
            // Calculamos cuánto valor nos falta en la mesa para llegar a 15
            int target = 15 - handCard.getValue();
            
            // Buscamos todas las combinaciones en la mesa cuya suma sea igual a 'target'
            List<ArrayList<Card>> combinations = findCombinations(tableCards, target);

            // Analizamos cada combinación válida para asignarle una puntuación estratégica
            for (ArrayList<Card> combo : combinations) {
                int score = evaluateMove(handCard, combo, tableCards.size());
                
                // Si la jugada actual da más puntos estratégicos que la anterior, la guardamos
                if (score > bestScore) {
                    bestScore = score;
                    bestMove = new Move(handCard, combo);
                }
            }
        }

        // ----------------------------------------------------------------------------------
        // Si no hay ninguna captura posible, seleccionamos una carta para descartar
        // ----------------------------------------------------------------------------------
        if (bestMove == null) {
            Card cardToDiscard = chooseCardToDiscard();
            // Creamos un movimiento sin captura (lista vacía)
            bestMove = new Move(cardToDiscard, new ArrayList<>());
        }

        // ----------------------------------------------------------------------------------
        // Aplicamos el movimiento en la mano de la CPU
        // ----------------------------------------------------------------------------------
        hand.remove(bestMove.getPlayedCard()); // Quitamos la carta jugada de la mano

        return bestMove;
    }

    /**
     * Cuantos más puntos devuelve la función, más probabilidad de usar el movimiento.
     */
    private int evaluateMove(Card playedCard, ArrayList<Card> capturedCombo, int totalTableCards) {
        int score = 0;

        // PRIORIDAD 1: Hacer Escoba (capturar todas las cartas que hay en la mesa)
        if (capturedCombo.size() == totalTableCards) {
            score += 100;
        }

        // PRIORIDAD 2: Capturar el 7 de Oros
        if (playedCard.getNum() == 7 && playedCard.getStick().equals("coins")) {
            score += 50;
        }
        for (Card c : capturedCombo) {
            if (c.getNum() == 7 && c.getStick().equals("coins")) {
                score += 50;
            }
        }

        // PRIORIDAD 3: Capturar otros Sietes
        if (playedCard.getNum() == 7) score += 15;
        for (Card c : capturedCombo) {
            if (c.getNum() == 7) score += 15;
        }

        // PRIORIDAD 4: Capturar cartas del palo de Oros
        if (playedCard.getStick().equals("coins")) score += 10;
        for (Card c : capturedCombo) {
            if (c.getStick().equals("coins")) score += 10;
        }

        // PRIORIDAD 5: Capturar la mayor cantidad de cartas posibles
        score += (capturedCombo.size() + 1) * 2;

        return score;
    }

    /**
     * Estrategia defensiva: Elige la carta menos valiosa cuando la IA está obligada a descartar.
     */
    private Card chooseCardToDiscard() {
        Card worstCard = hand.get(0);
        int lowestValue = 999;

        for (Card c : hand) {
            int val = c.getValue();
            
            // Penalizamos soltar Oros o Sietes para evitar regalarlos
            if (c.getStick().equals("coins")) val += 20;
            if (c.getNum() == 7) val += 30;

            // Elegimos la carta con el menor valor
            if (val < lowestValue) {
                lowestValue = val;
                worstCard = c;
            }
        }
        return worstCard;
    }

    /**
     * Algoritmo para encontrar combinaciones de cartas sobre la mesa que sumen
     * exactamente el valor objetivo ('target').
     */
    private List<ArrayList<Card>> findCombinations(ArrayList<Card> cards, int target) {
        List<ArrayList<Card>> result = new ArrayList<>();
        backtrack(cards, target, 0, new ArrayList<>(), result);
        return result;
    }

    /**
     * Función que genera los subconjuntos de cartas que alcanzan la suma deseada.
     */
    private void backtrack(ArrayList<Card> cards, int target, int start, ArrayList<Card> current, List<ArrayList<Card>> result) {
        int currentSum = 0;
        for (Card c : current) {
            currentSum += c.getValue(); // Sumamos el valor de la combinación actual
        }

        // CASO 1: Si la suma coincide exactamente con el objetivo, guardamos la combinación
        if (currentSum == target) {
            result.add(new ArrayList<>(current));
            return;
        }

        // CASO 2: Si la suma supera el objetivo, no seguimos buscando
        if (currentSum > target) {
            return;
        }

        // Exploración  pasando por los elementos restantes
        for (int i = start; i < cards.size(); i++) {
            current.add(cards.get(i)); // Añadimos la carta a la combinación temporal
            backtrack(cards, target, i + 1, current, result); // Llamada recursiva
            current.remove(current.size() - 1); // Deshacemos el cambio (backtrack)
        }
    }
}