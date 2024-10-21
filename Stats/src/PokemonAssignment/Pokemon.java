package PokemonAssignment;

import java.util.function.BiFunction;

public class Pokemon extends Card {
    private String name;
    private int hp;
    private int attachedEnergy = 0;
    private Type pokemonType;
    private BiFunction<Player, Player, Void> action1;
    private String actionName1;
    private BiFunction<Player, Player, Void> action2;
    private String actionName2;

    private enum Type{
        Fire,
        Water,
        Colorless,
        Lightning,
        Fighting
    }
    public Pokemon(){

    }

    public Pokemon(String name, int hp, BiFunction<Player, Player, Void> action){
        this.name = name;
        this.hp = hp;
        this.action1 = action;
    }
    public Pokemon(String name, int hp, BiFunction<Player, Player, Void> action1, BiFunction<Player, Player, Void> action2){
        this.name = name;
        this.hp = hp;
        this.action1 = action1;
        this.action2 = action2;
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

    public int getEnergy(){
        return attachedEnergy;
    }

    public void setEnergy(int energy){
        this.attachedEnergy = energy;
    }

    public void DisplayAbilities(){
        System.out.println(name + "'s abilities:");
        System.out.println("1. " + action1);
    }
}
