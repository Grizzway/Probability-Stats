package PokemonAssignment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;
import java.util.Random;


public class TrainerCards {
    public static ArrayList<Trainer> trainerCards;
    public static final Random random = new Random();
    /**
     * Did not include implementation for the Rare Candy since none of the Pokemon I have are evolutions.
     * I also did not want to implement evolutions in order to keep it simple.
     *
     * This card is only meant to be used in the monte carlo simulation and not meant to be played in the game.
     */
    private final static Trainer RareCandy = new Trainer("Rare Candy", (Player opponent, Player self) -> {
        //Oooooo it does nothing!
        return null;
    });

    /**
     * The Ultraball card has the player discard two cards, then search the deck for a Pokemon card to place in their hand.
     * If the player does not have another Pokemon card in their deck, the card is placed in the Discard Pile regardless
     */
    private final static Trainer UltraBall = new Trainer("Ultraball", (Player opponent, Player self) -> {
        //Check to see if deck has any Pokemon cards, return null it doesn't.
        if(self.deck.stream().noneMatch(card -> card instanceof Pokemon))
            return null;

        //Discard the first two cards
        for(int i = 0; i < 2; i++){
            self.discardPile.add(self.hand.get(0));
            self.hand.remove(0);
        }
        //Get a Pokemon card from the deck to reveal
        Optional<Card> reveal = self.deck.stream().filter(card -> card instanceof Pokemon).findAny();

        //If there is a card, place it in the hand and remove it from the deck
        reveal.ifPresent(card -> {
            self.hand.add(card);
            self.deck.remove(card);
                });

        Collections.shuffle(self.deck);
        return null;
    });

    /**
     * The purpose of Professor's Research is for the player to discard their whole hand, and then draw 7 new cards.
     * The discarded cards are placed in the player's discard pile.
     */
    private final static Trainer ProfessorsResearch = new Trainer("Professor's Research", (Player opponent, Player self) -> {

        //remove all the cards from the player's hand
        for(Card card : self.hand){
            self.hand.remove(card);
            self.discardPile.add(card);
        }

        //adds 7 cards to the player's hand
        for(int i = 0; i < 7; i++){
            self.hand.add(self.deck.get(0));
            self.deck.remove(0);
        }
        return null;
    });

    /**
     * The Boss's Order card replaces the opponents active card with one of their benched card.
     * If the opponent has no benched cards, nothing happens.
     */
    private final static Trainer BossOrders = new Trainer("Boss's Orders", (Player opponent, Player self) -> {
        //return if they have an empty bench
        if(opponent.bench.isEmpty())
            return null;

        //get a random benched card, remove it from the bench, replace it with the current active card
        Card newActive = opponent.bench.get(random.nextInt(opponent.bench.size()));
        opponent.bench.remove(newActive);
        opponent.bench.add(opponent.active);
        opponent.active = newActive;
        return null;
    });

    /**
     * Energy Search allows the user to search their deck for an Energy Card.
     * When one is chosen, it is put into the hand and the deck is shuffled.
     */
    private final static Trainer EnergySearch = new Trainer("Energy Search", (Player opponent, Player self) -> {

        return null;
    });

    /**
     * This card allows the user to draw a card for every trainer in the opponent's deck.
     */
    private final static Trainer AcerolasPremonition = new Trainer("Acerola's Premonition", (Player opponent, Player self) -> {

        return null;
    });

    /**
     * This card allows the user to draw 3 cards.
     */
    private final static Trainer Nemona = new Trainer("Nemona", (Player opponent, Player self) -> {

        return null;
    });

    /**
     * This makes the opponent discard two energy cards from their hand.
     */
    private final static Trainer Serena = new Trainer("Serena", (Player opponent, Player self) -> {

        return null;
    });

    /**
     * This allows the user to draw 6 cards.
     */
    private final static Trainer AshKetchum = new Trainer("Ash Ketchum", (Player opponent, Player self) -> {

        return null;
    });

    /**
     * This allows the user to heal one of the Pokemon in their bench or active for 60 HP.
     */
    private final static Trainer PokemonCenterLady = new Trainer("Pokemon Center Lady", (Player opponent, Player self) -> {

        return null;
    });

    /**
     * This allows the user to search their deck for 2 Pokemon and put them in their hand.
     * After this is done, the player's deck is shuffled.
     */
    private final static Trainer PokemonFanClub = new Trainer("Pokemon Fan Club", (Player opponent, Player self) -> {

        return null;
    });

    static {
        trainerCards = new ArrayList<>() {{
            add(RareCandy);
            add(UltraBall);
            add(ProfessorsResearch);
            add(BossOrders);
            add(EnergySearch);
            add(AcerolasPremonition);
            add(Nemona);
            add(Serena);
            add(AshKetchum);
            add(PokemonCenterLady);
            add(PokemonFanClub);
        }};
    }

}
