package PokemonAssignment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;


public class PokemonCards {
    public static Random random = new Random();
    public static ArrayList<Pokemon> pokemonCards;

    private static final Pokemon Charmander = new Pokemon("Charmander", 50,
            new ArrayList<Ability>(Arrays.asList(
                    new Ability("Scratch", 1, PokemonCards::Scratch),
                    new Ability("Ember", 2, PokemonCards::Ember))));

    private final static Pokemon Pikachu = new Pokemon("Pikachu", 60,
            new ArrayList<Ability>(Arrays.asList(
                    new Ability("Iron Tail", 2, PokemonCards::IronTail))));

    private final static Pokemon Arceus = new Pokemon("Arceus", 80,
            new ArrayList<Ability>(Arrays.asList(
                    new Ability("Break Ground",3, PokemonCards::BreakGround))));

    private final static Pokemon Chatot = new Pokemon("Chatot", 70,
            new ArrayList<Ability>(Arrays.asList(
                    new Ability("Tackle", 1, PokemonCards::Tackle),
                    new Ability("Wave Splash", 2, PokemonCards::WaveSplash))));

    private final static Pokemon Buizel = new Pokemon("Buizel", 70,
            new ArrayList<Ability>(Arrays.asList(
                    new Ability("A Capella", 1, PokemonCards::ACapella),
                    new Ability("Gust", 1, PokemonCards::Gust))));

    private final static Pokemon Mudkip = new Pokemon("Mudkip", 70,
            new ArrayList<Ability>(Arrays.asList(
                    new Ability("Quick Attack", 1, PokemonCards::QuickAttack))));

    static {
        pokemonCards = new ArrayList<>() {{
            add(Charmander);
            add(Pikachu);
            add(Arceus);
            add(Chatot);
            add(Buizel);
            add(Mudkip);
        }};
    }

    private static Void Scratch(Player opponent, Player self){
        Pokemon attacker = (Pokemon) self.active;
        Pokemon attacked = (Pokemon) opponent.active;
        if(attacker.getEnergy() < 1 ){
            System.out.println("You do not have enough attached energy to use this move!");
            return null;
        }

        System.out.println(attacker.getName() + " uses Scratch!");
        attacked.setHp(attacked.getHp()-10);
        System.out.println(self.getName() + "'s " + attacker.getName() + " has hit " + attacked.getName() + " with " + 10 + " damage!");
        return null;
    }

    private static Void Ember(Player opponent, Player self){
        Pokemon attacker = (Pokemon) self.active;
        Pokemon attacked = (Pokemon) opponent.active;
        if(attacker.getEnergy() < 2 ){
            System.out.println("You do not have enough attached energy to use this move!");
            return null;
        }

        System.out.println(attacker.getName() + " uses Ember!");
        attacked.setHp(attacked.getHp()-30);
        System.out.println(self.getName() + "'s " + attacker.getName() + " has hit " + attacked.getName() + " with " + 30 + " damage!");
        return null;
    }

    /**
     * A move for Pikachu. When this attack is triggered, the player flips a coin until they get tails
     * This attack does 20 damage for every instance of heads.
     * This attack requires 1 energy.
     */
    private static Void IronTail(Player opponent, Player self){
        Pokemon attacker = (Pokemon) self.active;
        Pokemon attacked = (Pokemon) opponent.active;
        if(attacker.getEnergy() < 1 ){
            System.out.println("You do not have enough attached energy to use this move!");
            return null;
        }

        int coin;
        int coinsFlipped = 0;

        //Flip coin until tails. Heads is 0 and Tails is 1.
        do{
            coin = random.nextInt(2);
            coinsFlipped++;
        } while(coin != 1);

        System.out.println(attacker.getName() + " uses Iron Tail!");
        attacked.setHp(attacked.getHp()-(20*coinsFlipped));
        System.out.println(self.getName() + "'s " + attacker.getName() + " has hit " + attacked.getName() + " with " + 20*coinsFlipped + " damage!");
        return null;
    }

    private static Void BreakGround(Player opponent, Player self){
        Pokemon attacker = (Pokemon) self.active;
        Pokemon attacked = (Pokemon) opponent.active;
        if(attacker.getEnergy() < 3 ){
            System.out.println("You do not have enough attached energy to use this move!");
            return null;
        }

        System.out.println(attacker.getName() + " uses Break Ground!");
        attacked.setHp(attacked.getHp()-30);
        System.out.println(self.getName() + "'s " + attacker.getName() + " has hit " + attacked.getName() + " with " + 30 + " damage!");
        return null;
    }

    private static Void Tackle(Player opponent, Player self){
        Pokemon attacker = (Pokemon) self.active;
        Pokemon attacked = (Pokemon) opponent.active;
        if(attacker.getEnergy() < 1 ){
            System.out.println("You do not have enough attached energy to use this move!");
            return null;
        }
        System.out.println(attacker.getName() + " uses Tackle!");
        attacked.setHp(attacked.getHp()-10);
        System.out.println(self.getName() + "'s " + attacker.getName() + " has hit " + attacked.getName() + " with " + 10 + " damage!");
        return null;
    }

    private static Void WaveSplash(Player opponent, Player self){
        Pokemon attacker = (Pokemon) self.active;
        Pokemon attacked = (Pokemon) opponent.active;
        if(attacker.getEnergy() < 2 ){
            System.out.println("You do not have enough attached energy to use this move!");
            return null;
        }
        System.out.println(attacker.getName() + " uses Wave Splash!");
        attacked.setHp(attacked.getHp()-20);
        System.out.println(self.getName() + "'s " + attacker.getName() + " has hit " + attacked.getName() + " with " + 20 + " damage!");
        return null;
    }

    /**
     * This ability allows the user to pull up to 3 Pokemon cards from their Deck and place them into their bench.
     * The deck is then shuffled after this ability.
     */
    private static Void ACapella(Player opponent, Player self){
        Pokemon attacker = (Pokemon) self.active;
        Pokemon attacked = (Pokemon) opponent.active;
        if(attacker.getEnergy() < 1 ){
            System.out.println("You do not have enough attached energy to use this move!");
            return null;
        }

        System.out.println(attacker.getName() + " uses Wave A Capella!");
        ArrayList<Card> foundCards = new ArrayList<>();
        int cardsFound = 0;
        for(int i = 0; !self.deck.isEmpty(); i++){
            if(self.deck.get(i) instanceof Pokemon card){
                cardsFound++;
                foundCards.add(card);
                self.deck.remove(card);
            }
        }

        if(cardsFound == 0){
            System.out.println("No Pokemon cards were found in your deck!");
            return null;
        }

        int currentBenchSize = self.bench.size();
        if(currentBenchSize == 5){
            self.hand.addAll(foundCards);
            System.out.println("Your bench is full! Placing " + cardsFound + " cards into your hand.%n");
        }
        else if(currentBenchSize < 5){
            int cardsToAdd = 5-currentBenchSize;
            for(int i = 0; i < cardsToAdd; i++){
                self.bench.add(foundCards.get(0));
                foundCards.remove(0);
            }

            if(!foundCards.isEmpty()){
                self.hand.addAll(foundCards);
            }

            System.out.println("Found " + cardsFound + " cards and Added " + (cardsToAdd) + " cards to your bench. "
                                + (cardsFound-cardsToAdd) + "cards were placed into your hand.");
        }
        Collections.shuffle(self.deck);
        return null;
    }

    private static Void Gust(Player opponent, Player self){
        Pokemon attacker = (Pokemon) self.active;
        Pokemon attacked = (Pokemon) opponent.active;
        if(attacker.getEnergy() < 1 ){
            System.out.println("You do not have enough attached energy to use this move!");
            return null;
        }
        System.out.println(attacker.getName() + " uses Gust!");
        attacked.setHp(attacked.getHp()-20);
        System.out.println(self.getName() + "'s " + attacker.getName() + " has hit " + attacked.getName() + " with " + 20 + " damage!");
        return null;
    }

    private static Void QuickAttack(Player opponent, Player self){
        Pokemon attacker = (Pokemon) self.active;
        Pokemon attacked = (Pokemon) opponent.active;
        if(attacker.getEnergy() < 1 ){
            System.out.println("You do not have enough attached energy to use this move!");
            return null;
        }

        int bonusDmg = 0;
        int coin = random.nextInt(2);
        //Heads is 0
        if(coin == 0)
            bonusDmg = 10;

        System.out.println(attacker.getName() + " uses Quick Attack!");
        attacked.setHp(attacked.getHp()-(10+bonusDmg));
        System.out.println(self.getName() + "'s " + attacker.getName() + " has hit " + attacked.getName() + " with " + (10+bonusDmg) + " damage!");
        return null;
    }
}
