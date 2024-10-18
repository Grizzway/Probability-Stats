package PokemonAssignment;

import java.util.function.Function;

public class Pokemon extends Card {
    private  String name;
    private int hp;
    private Function<Integer, Boolean> action;

    public Pokemon(String name, int hp, Function<Integer, Boolean> action){
        this.name = name;
        this.hp = hp;
        this.action = action;
    }

    public Pokemon(String name, int hp){
        this.name = name;
        this.hp = hp;
    }
    public String getName(){
        return name;
    }

    public int getHp(){
        return hp;
    }

    public void setHp(int hp){
        this.hp = hp;
    }
}
