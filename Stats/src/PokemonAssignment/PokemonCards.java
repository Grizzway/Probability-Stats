package PokemonAssignment;

import java.util.ArrayList;
import java.util.Random;


public class PokemonCards {
    public static Random random = new Random();
    public static ArrayList<Pokemon> pokemonCards;
    private static final Pokemon Charmander = new Pokemon("Charmander", 50, PokemonCards::Scratch, PokemonCards::Ember);
    private final static Pokemon Pikachu = new Pokemon("Pikachu", 60, PokemonCards::IronTail);
    private final static Pokemon Arceus = new Pokemon("Arceus", 80, PokemonCards::BreakGround);
    private final static Pokemon Chatot = new Pokemon("Chatot", 70, PokemonCards::Tackle, PokemonCards::WaveSplash );
    private final static Pokemon Buizel = new Pokemon("Buizel", 70, PokemonCards::ACapella, PokemonCards::Gust );
    private final static Pokemon Mudkip = new Pokemon("Mudkip", 70, PokemonCards::QuickAttack);
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
        System.out.println(self.getName() + "'s " + attacker.getName() + " has hit " + attacked.getName() + " with " + 10 + " damage!");
        return null;
    }

    private static Void ACapella(Player opponent, Player self){
        return null;
    }

    private static Void Gust(Player opponent, Player self){
        return null;
    }

    private static Void QuickAttack(Player opponent, Player self){
        return null;
    }
}
