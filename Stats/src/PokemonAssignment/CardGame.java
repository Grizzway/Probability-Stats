package PokemonAssignment;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class CardGame {
    private Player player1 = new Player();
    private Player player2 = new Player();

    /**
     * <p></p>
     *
     */
    private void FillDeckMonteCarlo(int charmanderCount){
        for(int i = 0; i < 60 - charmanderCount; i++){
            player1.deck.add(new Energy(Energy.Type.Colorless));
        }
        for(int i = 0; i < charmanderCount; i++){
            player1.deck.add(PokemonCards.pokemonCards.get(0));
        }
        Collections.shuffle(player1.deck);
    }

    private void FillDeckMonteCarloPrizes(int rareCandyCount, int charmanderCount){
        for(int i = 0; i < 60 - rareCandyCount - charmanderCount; i++){
            player1.deck.add(new Energy(Energy.Type.Colorless));
        }
        for(int i = 0; i < charmanderCount; i++){
            player1.deck.add(PokemonCards.pokemonCards.get(0));
        }
        for(int i = 0; i < rareCandyCount; i++){
            player1.deck.add(TrainerCards.trainerCards.get(0));
        }
        Collections.shuffle(player1.deck);
    }

    private void DrawHand(Player player){
        Random rng = new Random();
        for(int i = 0; i < 7; i++){
            int cardSeedIndex = rng.nextInt(player1.deck.size());
            player.hand.add(player.deck.get(cardSeedIndex));
            player.deck.remove(cardSeedIndex);
        }
    }

    private void DrawPrizes(Player player){
        for(int i = 0; i < 6; i++){
            player.prizes.add(player.deck.get(0));
            player.deck.remove(0);
        }
    }

    //Returns true if there is a pokemon
    //Returns false if no pokemon
    private boolean CheckForRedraw() {
        for(Card card : player1.hand) {
            if (card instanceof Pokemon)
                return true;
        }
        return false;
    }

    private boolean CheckPrizesForAllCandies(int expectedAmount){
        int actualAmount = 0;
        for(Card card : player1.prizes){
            if(card instanceof Trainer){
                actualAmount++;
            }
        }
        return actualAmount == expectedAmount;
    }

//    public int RedrawUntilCorrect(Player player){
//        int timesRedraw = 0;
//        while(!CheckForRedraw()){
//            ResetDeckAndHand();
//            DrawHand(player);
//            timesRedraw++;
//        }
//        return timesRedraw;
//    }


    private void ResetDeckAndHand(){
        player1.deck.clear();
        player1.hand.clear();
        player1.prizes.clear();
    }


//    public void DisplayHand(){
//        for(Card card : hand){
//            System.out.println(card.getClass().toString());
//        }
//    }


    public double MonteCarloOnePokemon(int timesToRun){
        int totalSuccess = 0;

        for(int run = 0; run < timesToRun; run++){
            FillDeckMonteCarlo(1);
            DrawHand(player1);
            if(CheckForRedraw())
                totalSuccess++;

            ResetDeckAndHand();
        }
        return (double)totalSuccess/timesToRun;
    }

    public void MonteCarloEngine(int timesToRun){
        for(int charmanders = 0; charmanders < 60; charmanders++){
            int totalSuccess = 0;
            for(int run = 0; run < timesToRun; run++){
                FillDeckMonteCarlo(charmanders);
                DrawHand(player1);
                if(CheckForRedraw())
                    totalSuccess++;


                ResetDeckAndHand();
            }
            System.out.println((double)totalSuccess/timesToRun);
        }
    }

    public void MonteCarloPrizes(int timesToRun){
        for(int candies = 1; candies <= 4; candies++){
            int totalSuccess = 0;
            for(int runs = 0; runs < timesToRun; runs++){
                FillDeckMonteCarloPrizes(candies, 8);
                DrawHand(player1);
                DrawPrizes(player1);
                if(CheckPrizesForAllCandies(candies))
                    totalSuccess++;

                ResetDeckAndHand();
            }
            System.out.printf("%.5f%n", (double)totalSuccess/timesToRun);
        }
    }

}
