package PokemonAssignment;
import javax.swing.text.html.Option;
import java.util.*;

public class CardGame {
    Player player1 = new Player();
    Player player2 = new Player();
    Player currentPlayer = player1;
    Player currentOpponent = player2;
    Player winner = new Player();
    boolean hasAttacked = false;
    boolean hasPlayedTrainer = false;
    boolean hasAttachedEnergy = false;
    private final Random random = new Random();
    private Scanner gameInput = new Scanner(System.in);

    /*
     * Monte Carlo Sim
     */

    /**
     *
     */
    private void FillDeckMonteCarlo(int charmanderCount){
        for(int i = 0; i < 60 - charmanderCount; i++){
            player1.deck.add(new Energy());
        }
        for(int i = 0; i < charmanderCount; i++){
            player1.deck.add(PokemonCards.pokemonCards.get(0));
        }
        Collections.shuffle(player1.deck);
    }

    /**
     *
     */
    private void FillDeckMonteCarloPrizes(int rareCandyCount, int charmanderCount){
        for(int i = 0; i < 60 - rareCandyCount - charmanderCount; i++){
            player1.deck.add(new Energy());
        }
        for(int i = 0; i < charmanderCount; i++){
            player1.deck.add(PokemonCards.pokemonCards.get(0));
        }
        for(int i = 0; i < rareCandyCount; i++){
            player1.deck.add(TrainerCards.trainerCards.get(0));
        }
        Collections.shuffle(player1.deck);
    }

    //Returns true if there is a pokemon
    //Returns false if no pokemon
    /**
     *
     */
    private boolean CheckForRedraw(Player player) {
        for(Card card : player.hand) {
            if (card instanceof Pokemon)
                return true;
        }
        return false;
    }

    /**
     *
     */
    private boolean CheckPrizesForAllCandies(int expectedAmount){
        int actualAmount = 0;
        for(Card card : player1.prizes){
            if(card instanceof Trainer){
                actualAmount++;
            }
        }
        return actualAmount == expectedAmount;
    }

    /**
     *
     */
    private void ResetDeckAndHand(){
        player1.deck.clear();
        player1.hand.clear();
        player1.prizes.clear();
    }

    private void DrawHand(Player player){
        Random rng = new Random();
        for(int i = 0; i < 7; i++){
            int cardSeedIndex = rng.nextInt(player1.deck.size());
            player.hand.add(player.deck.get(cardSeedIndex));
            player.deck.remove(cardSeedIndex);
        }
    }

    /**
     *
     */
    public double MonteCarloOnePokemon(int timesToRun){
        int totalSuccess = 0;

        for(int run = 0; run < timesToRun; run++){
            FillDeckMonteCarlo(1);
            DrawHand(player1);
            if(CheckForRedraw(player1))
                totalSuccess++;

            ResetDeckAndHand();
        }
        return (double)totalSuccess/timesToRun;
    }

    /**
     *
     */
    public void MonteCarloEngine(int timesToRun){
        for(int charmanders = 0; charmanders < 60; charmanders++){
            int totalSuccess = 0;
            for(int run = 0; run < timesToRun; run++){
                FillDeckMonteCarlo(charmanders);
                DrawHand(player1);
                if(CheckForRedraw(player1))
                    totalSuccess++;


                ResetDeckAndHand();
            }
            System.out.println((double)totalSuccess/timesToRun);
        }
    }

    /**
     *
     */
    public void MonteCarloPrizes(int timesToRun){
        for(int candies = 1; candies <= 4; candies++){
            int totalSuccess = 0;
            for(int runs = 0; runs < timesToRun; runs++){
                FillDeckMonteCarloPrizes(candies, 8);
                DrawHand(player1);
                DrawPrizes(player1);
                if(CheckPrizesForAllCandies(candies))
                    totalSuccess++;

                ResetDeckAndHand();
            }
            System.out.printf("%.5f%n", (double)totalSuccess/timesToRun);
        }
    }

    /*
    * Game logic
    * */

    public void CreatePlayers(String player1Name, String player2Name){
        player1 = new Player(player1Name);
        player2 = new Player(player2Name);

        CreateDeck(player1);
        CreateDeck(player2);

        DrawHand(player1, player2);
        DrawHand(player2, player1);

        System.out.println(player1Name + ", who will you play as your active?");
        Optional<Card> chosen1Card = DisplayPokemonInHand(player1);
        chosen1Card.ifPresent(card -> {
            addPokemonToActiveFromHand(player1, card);
        });


        System.out.println(player2Name + ", who will you play as your active?");
        Optional<Card> chosen2Card = DisplayPokemonInHand(player2);
        chosen2Card.ifPresent(card -> {
            addPokemonToActiveFromHand(player2, card);
        });

        DrawPrizes(player1);
        DrawPrizes(player2);

        currentPlayer = player1;
        currentOpponent = player2;
    }

    /**
    *
    */
    public void CreateDeck(Player player){
        //Add 5 random Pokemon cards
        for(int i = 0; i < 5; i++){
            player.deck.add(PokemonCards.pokemonCards.get(random.nextInt(PokemonCards.pokemonCards.size())));
        }
        //Add 40 random Trainer cards
        for(int i = 0; i < 40; i++){
            player.deck.add(TrainerCards.trainerCards.get(random.nextInt(1, TrainerCards.trainerCards.size())));
        }
        //Add 15 Energy cards
        for(int i = 0; i < 15; i++){
            player.deck.add(new Energy());
        }

        Collections.shuffle(player.deck);
    }

    /**
     *
     */
    private void DrawHand(Player self, Player opponent){
        Random rng = new Random();
        int timesRedrawn = 0;
        do {
            for (int i = 0; i < 7; i++) {
                int cardSeedIndex = rng.nextInt(player1.deck.size());
                self.hand.add(self.deck.get(cardSeedIndex));
                self.deck.remove(cardSeedIndex);
            }
            timesRedrawn++;
        } while (!CheckForRedraw(self));

        if(timesRedrawn > 0){
            for(int i = 0; i < timesRedrawn; i++) {
                opponent.hand.add(opponent.deck.get(0));
                opponent.deck.remove(0);
            }
        }
    }

    /**
     *
     */
    private void DrawPrizes(Player player){
        for(int i = 0; i < 6; i++){
            player.prizes.add(player.deck.get(0));
            player.deck.remove(0);
        }
    }


    public void addToBench(Player player, Card pokemon){
        if(player.bench.size() == 5){
            System.out.println("Bench is full! Only 5 Pokemon may be on the bench at a time!");
        }
        else{
            player.bench.add(pokemon);
            player.hand.remove(pokemon);
        }
    }

    public boolean attachEnergy(Player player, Pokemon pokemon){
        Optional<Card> energyCard = player.hand.stream().filter(card -> card instanceof Energy).findAny();
        if(energyCard.isPresent()){
            player.discardPile.add(energyCard.get());
            player.hand.remove(energyCard.get());
            pokemon.setEnergy(pokemon.getEnergy()+1);
            System.out.println("Added one Energy to " + pokemon.getName() + "!");
            return true;
        }
        System.out.println("You don't have any Energy in your hand!");
        return false;
    }

    public void switchPokemon(Player player, Card switchingIn){
        Optional<Card> energyCard = player.hand.stream().filter(card -> card instanceof Energy).findAny();
        Pokemon currentActive = (Pokemon) player.active;
        Pokemon switchedIn = (Pokemon)  switchingIn;
        if(energyCard.isPresent()){
            player.discardPile.add(energyCard.get());
            player.hand.remove(energyCard.get());
            if(currentActive.getName().equals("No Active!")){
                player.active = switchingIn;
                System.out.println(switchedIn.getName() + " has been put into play!");
            }
            else {
                player.bench.add(player.active);
                player.bench.remove(switchingIn);
                player.active = switchingIn;
                System.out.println(currentActive.getName() + " has been switched out for " + switchedIn.getName() + "!");
            }
        }
        System.out.println("You don't have any Energy in your hand! It cost one Energy to switch out your Pokemon!");
    }

    public void playTrainerCard(Player self, Player opponent, Card trainerCard){
        Trainer trainer = (Trainer) trainerCard;
        self.discardPile.add(trainerCard);
        self.hand.remove(trainerCard);
        trainer.ability.apply(opponent, self);
        System.out.println(self.getName() + " has played " + trainer.getCardName() + "!");
    }

    public void addPokemonToActiveFromBench(Player player, Card pokemon){
        player.active = pokemon;
        player.bench.remove(pokemon);
    }

    public void addPokemonToActiveFromHand(Player player, Card pokemon){
        player.active = pokemon;
        player.hand.remove(pokemon);
    }

    public void usePokemonAbility(Player self, Player opponent, Ability ability){
        var action = ability.getAction();
        action.apply(opponent, self);
        Pokemon oppActive = (Pokemon) opponent.active;
        if(oppActive.getHp() <= 0){
            self.hand.add(self.prizes.get(0));
            self.prizes.remove(0);
            System.out.println(self.getName() + " has knocked out " + oppActive.getName() + "!");
            opponent.discardPile.add(oppActive);

            if(!opponent.bench.isEmpty()){
                System.out.println(opponent.getName() + "! Pick a Pokemon from your bench to be your new active!");
                Optional<Card> chosenPokemonCard = DisplayPokemonInBench(opponent);
                if(chosenPokemonCard.isEmpty()){
                    System.out.println("No Pokemon in Bench! Pick from your hand!");
                    chosenPokemonCard = DisplayPokemonInHand(opponent);
                    if(chosenPokemonCard.isPresent()){
                        opponent.active = chosenPokemonCard.get();
                    }
                    else{
                        System.out.println("No Pokemon in Hand either!");
                        ((Pokemon) opponent.active).setName("No Active!");
                    }
                }
                else{
                    addPokemonToActiveFromBench(opponent, chosenPokemonCard.get());
                }
            }

        }
    }

    public boolean hasActive(Player player){
        Pokemon currentActive = (Pokemon) player.active;
        return !currentActive.getName().equals("No Active!");
    }

    public boolean isWinner(Player self, Player opponent){
        return self.prizes.isEmpty() || (opponent.hand.stream().noneMatch(card -> card instanceof Pokemon)
                && opponent.bench.stream().noneMatch(card -> card instanceof Pokemon) && !hasActive(opponent));
    }


    public Optional<Card> DisplayPokemonInHand(Player player){
        System.out.println("Available Pokemon in Hand:\n");
        ArrayList<Card> pokemonCards = new ArrayList<>();
        for (int i = 0; i < player.hand.size(); i++){
            if(player.hand.get(i) instanceof Pokemon pokemonCard){
                pokemonCards.add(pokemonCard);
                System.out.println(pokemonCards.size() + ". Name: " + pokemonCard.getName() + " || HP: " + pokemonCard.getHp());
            }
        }

        if (!pokemonCards.isEmpty()) {
            System.out.println("\n0. Go Back.");
            while (true) {
                if (gameInput.hasNextInt()) {
                    int selection = gameInput.nextInt();

                    if (selection == 0) {
                        return Optional.empty();
                    }
                    else if(selection > 0 && selection <= pokemonCards.size()){
                        return Optional.of(pokemonCards.get(selection-1));
                    }
                    else{
                        System.out.println("Invalid Selection.");
                    }
                }
                else {
                    System.out.println("Invalid Input.");
                    gameInput.next();
                }
            }
        }
        return Optional.empty();
    }

    public Optional<Card> DisplayPokemonInBench(Player player){
        System.out.println("Available Pokemon in Bench:\n");
        ArrayList<Card> pokemonCards = new ArrayList<>();
        for (int i = 0; i < player.bench.size(); i++){
            if(player.bench.get(i) instanceof Pokemon pokemonCard){
                pokemonCards.add(pokemonCard);
                System.out.println((pokemonCards.size()) + ". Name: " + pokemonCard.getName() + " || HP: " + pokemonCard.getHp());
            }
        }

        if (!pokemonCards.isEmpty()) {
            System.out.println("\n0. Go Back.");
            while (true) {
                if (gameInput.hasNextInt()) {
                    int selection = gameInput.nextInt();

                    if (selection == 0) {
                        return Optional.empty();
                    }
                    else if(selection > 0 && selection <= pokemonCards.size()){
                        return Optional.of(pokemonCards.get(selection-1));
                    }
                    else{
                        System.out.println("Invalid Selection.");
                    }
                }
                else {
                    System.out.println("Invalid Input.");
                    gameInput.next();
                }
            }
        }
        return Optional.empty();
    }

    public Optional<Ability> DisplayAbilities(Pokemon pokemon){
        ArrayList<Ability> abilities = pokemon.getAbilities();
        for(int i = 0; i < abilities.size(); i++){
            System.out.println((i+1) +". Ability Name: " + abilities.get(i).getName() + "\nRequired Energy: " + abilities.get(i).getRequiredEnergy());
        }

        System.out.println("\n0. Go Back.");
        while (true) {
            if (gameInput.hasNextInt()) {
                int selection = gameInput.nextInt();

                if (selection == 0) {
                    return Optional.empty();
                }
                else if(selection > 0 && selection <= abilities.size()){
                    return Optional.of(abilities.get(selection-1));
                }
                else{
                    System.out.println("Invalid Selection.");
                }
            }
            else {
                System.out.println("Invalid Input.");
                gameInput.next();
            }
        }
    }

    public Optional<Card> DisplayTrainerCards(Player player) {
        System.out.println("Available Trainer Cards in Hand:\n");
        ArrayList<Card> trainerCards = new ArrayList<>();
        for (int i = 0; i < player.hand.size(); i++) {
            if (player.hand.get(i) instanceof Trainer trainerCard) {
                trainerCards.add(trainerCard);
                System.out.println((trainerCards.size()) + ". Card Name: " + trainerCard.cardName);
            }
        }
        if (!trainerCards.isEmpty()) {
            System.out.println("\n0. Go Back.");
            while (true) {
                if (gameInput.hasNextInt()) {
                    int selection = gameInput.nextInt();

                    if (selection == 0) {
                        System.out.println("Going back!");
                        return Optional.empty();
                    }
                    else if(selection > 0 && selection <= trainerCards.size()){
                        return Optional.of(trainerCards.get(selection-1));
                    }
                    else{
                        System.out.println("Invalid Selection.");
                    }
                }
                else {
                    System.out.println("Invalid Input.");
                    gameInput.next();
                }
            }
        }
        System.out.println("No Trainer Cards in your hand!");
        return Optional.empty();
    }

    public void switchCurrentPlayer(){
        if(player1 == currentPlayer){
            currentPlayer = player2;
            currentOpponent = player1;
        }
        else{
            currentPlayer = player1;
            currentOpponent = player2;
        }
        hasAttacked = false;
        hasAttachedEnergy = false;
        hasPlayedTrainer = false;
    }

    public boolean DisplayOptions(){
        while(true) {
            System.out.println("What will you do, " + currentPlayer.getName() +"?\n");
            if (!hasAttacked)
                System.out.println("1. Attack");
            else
                System.out.println("X. ALREADY ATTACKED");
            if (!hasAttachedEnergy)
                System.out.println("2. Attach Energy");
            else
                System.out.println("X. ALREADY ATTACHED ENERGY");
            if (!hasPlayedTrainer)
                System.out.println("3. Play Trainer");
            else
                System.out.println("X. ALREADY PLAYED TRAINER CARD");

            System.out.println("4. Bench Pokemon from Hand");
            System.out.println("5. Switch Active with Benched");
            System.out.println("6. Display Game Info");
            System.out.println("\n0. End Turn");
            int action = gameInput.nextInt();

            switch (action) {
                case 1: { //Attack
                    if (!hasAttacked){
                        Optional<Ability> chosenAbility = DisplayAbilities((Pokemon) currentPlayer.active);
                        if(chosenAbility.isPresent()){
                            usePokemonAbility(currentPlayer, currentOpponent, chosenAbility.get());
                            hasAttacked = true;
                            if(isWinner(currentPlayer, currentOpponent)){
                                winner = currentPlayer;
                                return true;
                            }
                        }
                    }
                    else{
                        System.out.println("You have already attacked!");
                    }
                    break;
                }
                case 2:{ //Attach Energy
                    if (!hasAttachedEnergy){
                        hasAttachedEnergy = attachEnergy(currentPlayer, (Pokemon) currentPlayer.active);
                    }
                    else{
                        System.out.println("You have already attached energy!");
                    }
                    break;
                }
                case 3: { //Play Trainer Card
                    if (!hasPlayedTrainer){
                        Optional<Card> trainerCard = DisplayTrainerCards(currentPlayer);
                        if(trainerCard.isPresent()){
                            playTrainerCard(currentPlayer, currentOpponent, trainerCard.get());
                            hasPlayedTrainer = true;
                        }
                    }
                    else{
                        System.out.println("You have already played a trainer card!");
                    }
                    break;
                }
                case 4:{ //Add Pokemon to Bench from Hand
                    Optional<Card> chosenCard = DisplayPokemonInHand(currentPlayer);
                    chosenCard.ifPresent(card -> addToBench(currentPlayer, card));
                    break;
                }
                case 5:{ //Switch Active Pokemon with one from Bench
                    Optional<Card> chosenCard = DisplayPokemonInBench(currentPlayer);
                    chosenCard.ifPresent(card -> switchPokemon(currentPlayer, card));
                    break;
                }
                case 6: //Display Game info
                {
                    DisplayGameInfo();
                    break;
                }
                case 0:{ //End turn
                    System.out.println(currentPlayer.getName() + " has ended their turn! You're up, " + currentOpponent.getName());
                    if(isWinner(currentPlayer, currentOpponent)){
                        winner = currentPlayer;
                        return true;
                    }
                    else{
                        switchCurrentPlayer();
                        return false;
                    }

                }
                default:{ //Invalid Input
                    System.out.println("Not a valid option! Choose a number 1-5, assuming you haven't made a move yet.");
                }

            }
        }
    }

    public void SetupGame(){
        System.out.println("Enter a name for Player 1.");
        String player1Name = gameInput.nextLine();
        System.out.println("Enter a name for Player 2.");
        String player2Name = gameInput.nextLine();
        CreatePlayers(player1Name, player2Name);
    }

    public void DisplayGameInfo(){
        System.out.println("----------------------------------------------");
        System.out.println(player1.getName() + "'s Board:");
        Pokemon p1Active = (Pokemon) player1.active;
        System.out.println("ACTIVE: " + p1Active.getName() + " || HP: " + p1Active.getHp());
        System.out.print("BENCH: ");
        for(Card card : player1.bench){
            Pokemon pokemon = (Pokemon) card;
            System.out.print("(" + pokemon.getName() + "," + pokemon.getHp() + "HP) ");
        }
        System.out.println("\n" + player1.deck.size() + " cards in deck.");
        System.out.println(player1.hand.size() + " cards in hand.");
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println(player2.getName() + "'s Board:");
        Pokemon p2Active = (Pokemon) player2.active;
        System.out.println("ACTIVE: " + p2Active.getName() + " || HP: " + p2Active.getHp());
        System.out.print("BENCH: ");
        for(Card card : player2.bench){
            Pokemon pokemon = (Pokemon) card;
            System.out.print("(" + pokemon.getName() + "," + pokemon.getHp() + "HP) ");
        }
        System.out.println("\n" + player2.deck.size() + " cards in deck.");
        System.out.println(player2.hand.size() + " cards in hand.");
        System.out.println("----------------------------------------------");
    }

    /**
     *
     */
    public void PlayGame(){
        SetupGame();
        boolean isWinner = false;
        while(!isWinner){
            System.out.println(currentPlayer.getName());
            System.out.println(currentOpponent.getName());
            isWinner = DisplayOptions();
        }

        System.out.println("The winner is " + winner.getName() + "!");
    }

}
