package PokemonAssignment;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class CardGame {
    private ArrayList<Card> player1;
    private ArrayList<Card> player2;
    private ArrayList<Card> hand;

    public CardGame(){
        player1 = new ArrayList<>();
        player2 = new ArrayList<>();
        hand = new ArrayList<>();
    }

    public void FillDeck(int charmanderCount){
        for(int i = 0; i < 60 - charmanderCount; i++){
            player1.add(new Energy());
        }
        for(int i = 0; i < charmanderCount; i++){
            player1.add(new Pokemon("Charmander", 60));
        }
        Collections.shuffle(player1);
    }

    public void DrawHand(){
        Random rng = new Random();
        for(int i = 0; i < 7; i++){
            int cardSeedIndex = rng.nextInt(player1.size());
            hand.add(player1.get(cardSeedIndex));
            player1.remove(cardSeedIndex);
        }
    }


    //Returns true if there is a pokemon
    //Returns false if no pokemon
    public boolean CheckForRedraw() {
        for(Card card : hand) {
            if (card instanceof Pokemon)
                return true;
        }
        return false;
    }

//    public int RedrawUntilCorrect(){
//        int timesRedraw = 0;
//        while(!CheckForRedraw()){
//            ResetDeckAndHand();
//            DrawHand();
//            timesRedraw++;
//        }
//        return timesRedraw;
//    }

    public void ResetDeckAndHand(){
        player1.clear();
        hand.clear();
    }

//    public void DisplayHand(){
//        for(Card card : hand){
//            System.out.println(card.getClass().toString());
//        }
//    }

    public double MonteCarlo(int timesToRun){
        int totalSuccess = 0;

        for(int i = 0; i < timesToRun; i++){
            FillDeck(1);
            DrawHand();
            if(CheckForRedraw())
                totalSuccess++;

            ResetDeckAndHand();
        }
        return (double)totalSuccess/timesToRun;
    }

    public void MonteCarloEngine(int timesToRun){
        for(int i = 0; i < 60; i++){
            int totalSuccess = 0;
            for(int j = 0; j < timesToRun; j++){
                FillDeck(i);
                DrawHand();
                if(CheckForRedraw()) {
                    totalSuccess++;
                }

                ResetDeckAndHand();
            }
            System.out.println((double)totalSuccess/timesToRun);
        }
    }
}
