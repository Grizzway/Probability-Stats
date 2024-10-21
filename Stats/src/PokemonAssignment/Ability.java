package PokemonAssignment;

import java.util.function.BiFunction;

public class Ability {
    private String name;
    private int requiredEnergy = 0;
    private BiFunction<Player, Player, Void> action;

    public Ability(String name, int requiredEnergy, BiFunction<Player, Player, Void> action) {
        this.name = name;
        this.requiredEnergy = requiredEnergy;
        this.action = action;
    }

    public String getName() {
        return name;
    }

    public BiFunction<Player, Player, Void> getAction() {
        return action;
    }

    // Execute the ability
    public void use(Player user, Player target) {
        action.apply(user, target);
    }
}