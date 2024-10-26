package PokemonAssignment;

import java.util.function.BiFunction;

public class Trainer extends Card{
    public String cardName;
    public BiFunction<Player, Player, Void> ability;


    public Trainer(){

    }

    public Trainer(String cardName, BiFunction<Player, Player, Void> ability){
        this.cardName = cardName;
        this.ability = ability;
    }

    public String getCardName() {
        return cardName;
    }
}
