package PokemonAssignment;

import java.util.ArrayList;
import java.util.function.BiFunction;

public class Pokemon extends Card {
    private String name;
    private int hp;
    private int attachedEnergy = 0;
    private Type pokemonType;
    private ArrayList<Ability> abilities = new ArrayList<>();

    public enum Type{
        Fire,
        Water,
        Colorless,
        Lightning,
        Fighting
    }
    public Pokemon(){

    }

    public Pokemon(String name, int hp, Type pokemonType, ArrayList<Ability> abilities){
        this.name = name;
        this.hp = hp;
        this.abilities = abilities;
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

    }
}
