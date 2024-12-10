package PokemonAssignment;

public class PokemonTester {

    public static void main(String[] args){
        CardGame game = new CardGame();

        /*
         * Uncomment the one you wish to see in action and hit play.
         */

        //System.out.println("The average probability of picking one pokemon in your hand over 1000 runs is: " + game.MonteCarloOnePokemon(1000));
        //game.MonteCarloEngine(1000);
        //game.MonteCarloPrizes(10000);
        game.PlayGame();
    }
}
