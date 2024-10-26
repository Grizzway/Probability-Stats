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

        if(self.hand.size() < 2){
            return null;
        }

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
        for(int i = 0; !self.hand.isEmpty(); i++){
            self.discardPile.add(self.hand.get(0));
            self.hand.remove(0);
        }

        int initDeckSize = self.deck.size();
        //adds 7 cards to the player's hand
        for(int i = 0; i < 7 && i < initDeckSize; i++){
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
        for(int i = 0; i < self.deck.size(); i++){
            if(self.deck.get(i) instanceof Energy card){
                self.hand.add(card);
                self.deck.remove(card);
                break;
            }
        }



        Collections.shuffle(self.deck);
        return null;
    });


    /**
     * This card allows the user to draw 3 cards.
     */
    private final static Trainer Nemona = new Trainer("Nemona", (Player opponent, Player self) -> {

        for(int i = 0; i < 3 && !self.deck.isEmpty(); i++){
            self.hand.add(self.deck.get(0));
            self.deck.remove(0);
        }
        return null;
    });



    static {
        trainerCards = new ArrayList<>() {{
            add(RareCandy);
            add(UltraBall);
            add(ProfessorsResearch);
            add(BossOrders);
            add(EnergySearch);
            add(Nemona);
        }};
    }

}
