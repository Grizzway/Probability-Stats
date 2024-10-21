package PokemonAssignment;

public class Energy extends Card{
    private Type energyType;

    public enum Type{
        Fire,
        Water,
        Colorless,
        Lightning,
        Fighting
    }

    public Energy(){

    }

    public Energy(Type energyType){
        this.energyType = energyType;
    }

}
