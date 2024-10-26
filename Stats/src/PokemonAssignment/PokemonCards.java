package PokemonAssignment;

import java.util.*;


public class PokemonCards {
    public static Random random = new Random();
    public static ArrayList<Pokemon> pokemonCards;

    private static final Pokemon Charmander = new Pokemon("Charmander", 50,
            new ArrayList<>(Arrays.asList(
                    new Ability("Scratch", 1, PokemonCards::Scratch),
                    new Ability("Ember", 2, PokemonCards::Ember))));

    private final static Pokemon Pikachu = new Pokemon("Pikachu", 60,
            new ArrayList<>(List.of(
                    new Ability("Iron Tail", 2, PokemonCards::IronTail))));

    private final static Pokemon Arceus = new Pokemon("Arceus", 80,
            new ArrayList<>(List.of(
                    new Ability("Break Ground", 3, PokemonCards::BreakGround))));

    private final static Pokemon Chatot = new Pokemon("Chatot", 70,
            new ArrayList<>(Arrays.asList(
                    new Ability("Tackle", 1, PokemonCards::Tackle),
                    new Ability("Wave Splash", 2, PokemonCards::WaveSplash))));

    private final static Pokemon Buizel = new Pokemon("Buizel", 70,
            new ArrayList<>(Arrays.asList(
                    new Ability("A Capella", 1, PokemonCards::ACapella),
                    new Ability("Gust", 1, PokemonCards::Gust))));

    private final static Pokemon Mudkip = new Pokemon("Mudkip", 70,
            new ArrayList<>(List.of(
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

    /**
     * Executes the Scratch move for the active Pokémon. The move requires at least 1 energy.
     * If the attacker has sufficient energy, it deals 10 damage to the opponent's active Pokémon.
     *
     * @param opponent The opposing player.
     * @param self The player performing the move.
     * @return Always returns null.
     */
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

    /**
     * Executes the Ember move for the active Pokémon. The move requires at least 2 energy.
     * If the attacker has sufficient energy, it deals 30 damage to the opponent's active Pokémon.
     *
     * @param opponent The opposing player.
     * @param self The player performing the move.
     * @return Always returns null.
     */
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
     * Executes the Iron Tail move for the active Pokémon. The move requires at least 2 energy.
     * The player flips a coin until they get tails, dealing 20 damage for each heads flipped.
     *
     * @param opponent The opposing player.
     * @param self The player performing the move.
     * @return Always returns null.
     */
    private static Void IronTail(Player opponent, Player self){
        Pokemon attacker = (Pokemon) self.active;
        Pokemon attacked = (Pokemon) opponent.active;
        if(attacker.getEnergy() < 2 ){
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

    /**
     * Executes the Break Ground move for the active Pokémon. The move requires at least 3 energy.
     * If the attacker has sufficient energy, it deals 30 damage to the opponent's active Pokémon.
     *
     * @param opponent The opposing player.
     * @param self The player performing the move.
     * @return Always returns null.
     */
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

    /**
     * Executes the Tackle move for the active Pokémon. The move requires at least 1 energy.
     * If the attacker has sufficient energy, it deals 10 damage to the opponent's active Pokémon.
     *
     * @param opponent The opposing player.
     * @param self The player performing the move.
     * @return Always returns null.
     */
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

    /**
     * Executes the Wave Splash move for the active Pokémon. The move requires at least 2 energy.
     * If the attacker has sufficient energy, it deals 20 damage to the opponent's active Pokémon.
     *
     * @param opponent The opposing player.
     * @param self The player performing the move.
     * @return Always returns null.
     */
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
     * Executes the A Capella ability for the active Pokémon. This ability requires at least 1 energy.
     * The player can pull up to 3 Pokémon cards from their deck to place on their bench, and the deck is shuffled afterward.
     *
     * @param opponent The opposing player.
     * @param self The player performing the ability.
     * @return Always returns null.
     */
    private static Void ACapella(Player opponent, Player self){
        Pokemon attacker = (Pokemon) self.active;
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

    /**
     * Executes the Gust move for the active Pokémon. The move requires at least 1 energy.
     * If the attacker has sufficient energy, it deals 20 damage to the opponent's active Pokémon.
     *
     * @param opponent The opposing player.
     * @param self The player performing the move.
     * @return Always returns null.
     */
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

    /**
     * Executes the Quick Attack move for the active Pokémon. The move requires at least 1 energy.
     * If the attacker has sufficient energy, it deals 10 damage plus an additional 10 damage if a coin flip results in heads.
     *
     * @param opponent The opposing player.
     * @param self The player performing the move.
     * @return Always returns null.
     */
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
