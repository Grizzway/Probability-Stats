package PokemonAssignment;

import java.util.ArrayList;

public class Player {
    public String name;
    public ArrayList<Card> deck = new ArrayList<>();
    public ArrayList<Card> hand = new ArrayList<>();
    public ArrayList<Card> discardPile = new ArrayList<>();
    public ArrayList<Card> prizes = new ArrayList<>();
    public ArrayList<Card> bench = new ArrayList<>();
    public Card active = new Pokemon();

    public Player(){

    }

    public Player(String name, ArrayList<Card> deck, ArrayList<Card> hand, ArrayList<Card> discardPile){
        this.name = name;
        this.deck = deck;
        this.hand = hand;
        this.discardPile = discardPile;
    }


    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }
}
