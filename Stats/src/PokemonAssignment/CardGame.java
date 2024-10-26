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
     * Populates player1's deck with a specified number of Charmander cards and fills the
     * remaining deck slots with Energy cards for a total of 60 cards. The deck is then shuffled.
     *
     * @param charmanderCount The number of Charmander cards to add to the deck.
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
     * Populates player1's deck with a specified number of Charmander and Rare Candy cards,
     * filling the remaining slots with Energy cards for a total of 60 cards. The deck is then shuffled.
     *
     * @param rareCandyCount  The number of Rare Candy cards to add to the deck.
     * @param charmanderCount The number of Charmander cards to add to the deck.
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


    /**
     * Checks if the player's hand contains at least one Pokemon card.
     *
     * @param player The player whose hand is being checked.
     * @return true if there is a Pokemon card in the hand; false otherwise.
     */
    private boolean CheckForRedraw(Player player) {
        for(Card card : player.hand) {
            if (card instanceof Pokemon)
                return true;
        }
        return false;
    }

    /**
     * Verifies if the player's prize cards contain the specified number of Trainer (Rare Candy) cards.
     *
     * @param expectedAmount The expected number of Trainer cards in the prizes.
     * @return true if the actual amount of Trainer cards in prizes matches the expected amount; false otherwise.
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
     * Clears the player's deck, hand, and prize cards to reset for a new simulation run.
     */
    private void ResetDeckAndHand(){
        player1.deck.clear();
        player1.hand.clear();
        player1.prizes.clear();
    }

    /**
     * Draws a hand of 7 random cards for the specified player from the deck.
     *
     * @param player The player who will receive a new hand of 7 cards.
     */
    private void DrawHand(Player player){
        Random rng = new Random();
        for(int i = 0; i < 7; i++){
            int cardSeedIndex = rng.nextInt(player1.deck.size());
            player.hand.add(player.deck.get(cardSeedIndex));
            player.deck.remove(cardSeedIndex);
        }
    }

    /**
     * Runs a Monte Carlo simulation to determine the probability of drawing at least one Pokemon card in a hand of 7 cards.
     *
     * @param timesToRun The number of simulation runs to perform.
     * @return The probability of drawing at least one Pokemon card in a hand, as a double.
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
     * Runs a Monte Carlo simulation across varying numbers of Charmander cards in the deck to determine the probability
     * of drawing at least one Pokemon in a hand of 7 cards.
     *
     * @param timesToRun The number of simulation runs for each Charmander count.
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
     * Runs a Monte Carlo simulation across varying numbers of Rare Candy cards in the deck to determine the probability
     * of having all specified candies in the player's prize cards.
     *
     * @param timesToRun The number of simulation runs for each Rare Candy count.
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
            System.out.printf("Average Probability for %d Candy(s):  %.5f%n", candies ,(double)totalSuccess/timesToRun);
        }
    }

    /*
    * Game logic
    * */

    /**
     * Initializes two players for the game with the specified names, creates their decks,
     * draws initial hands for both players, and allows each to choose an active Pokemon.
     *
     * @param player1Name The name of the first player.
     * @param player2Name The name of the second player.
     */
    public void CreatePlayers(String player1Name, String player2Name){
        player1 = new Player(player1Name);
        player2 = new Player(player2Name);

        CreateDeck(player1);
        CreateDeck(player2);

        DrawHand(player1, player2);
        DrawHand(player2, player1);

        System.out.println(player1Name + ", who will you play as your active?");
        Optional<Card> chosen1Card = DisplayPokemonInHand(player1, false);
        chosen1Card.ifPresent(card -> {
            addPokemonToActiveFromHand(player1, card);
        });


        System.out.println(player2Name + ", who will you play as your active?");
        Optional<Card> chosen2Card = DisplayPokemonInHand(player2, false);
        chosen2Card.ifPresent(card -> {
            addPokemonToActiveFromHand(player2, card);
        });

        DrawPrizes(player1);
        DrawPrizes(player2);

        currentPlayer = player1;
        currentOpponent = player2;
    }

    /**
     * Creates a deck for the specified player, consisting of a combination of Pokemon, Trainer, and Energy cards,
     * and then shuffles the deck.
     *
     * @param player The player whose deck will be created.
     */
    public void CreateDeck(Player player){
        //Add 5 random Pokemon cards
        for(int i = 0; i < 5; i++){
            Pokemon pokemon = PokemonCards.pokemonCards.get(random.nextInt(PokemonCards.pokemonCards.size()));
            player.deck.add(new Pokemon(pokemon));
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
     * Draws a hand of 7 cards for the specified player, ensuring that the hand contains at least one Pokemon card.
     * If no Pokemon is drawn initially, the hand is redrawn, with the opponent drawing a card each time.
     *
     * @param self The player drawing their hand.
     * @param opponent The opposing player, who draws a card each time a hand is redrawn.
     */
    private void DrawHand(Player self, Player opponent){
        Random rng = new Random();
        int timesRedrawn = 0;
        do {
            for (int i = 0; i < 7; i++) {
                int cardSeedIndex = rng.nextInt(self.deck.size());
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
     * Draws 6 prize cards from the player's deck and adds them to their prize pile.
     * The cards are removed from the deck in the process.
     *
     * @param player The player from whom the prizes are drawn.
     */
    private void DrawPrizes(Player player){
        for(int i = 0; i < 6; i++){
            player.prizes.add(player.deck.get(0));
            player.deck.remove(0);
        }
    }

    /**
     * Adds a specified Pokemon card to the player's bench if there is space available.
     * If the bench is full (5 Pokemon), a message is displayed indicating that no more Pokemon can be added.
     * The Pokemon card is removed from the player's hand.
     *
     * @param player The player whose bench is being added to.
     * @param pokemon The Pokemon card to be added to the bench.
     */
    public void addToBench(Player player, Card pokemon){
        if(player.bench.size() == 5){
            System.out.println("Bench is full! Only 5 Pokemon may be on the bench at a time!");
        }
        else{
            player.bench.add(pokemon);
            player.hand.remove(pokemon);
        }
    }

    /**
     * Attaches an Energy card from the player's hand to a specified Pokemon.
     * If an Energy card is available, it is removed from the hand and added to the Pokemon's energy count.
     * A message is printed to confirm the attachment. If no Energy cards are available, a message is displayed.
     *
     * @param player The player who is attaching the energy.
     * @param pokemon The Pokemon to which the Energy is being attached.
     * @return True if the energy was successfully attached; false otherwise.
     */
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

    /**
     * Switches the active Pokemon for another specified Pokemon card.
     * The switch can only occur if the player has at least one Energy card in their hand.
     * If successful, the current active Pokemon is either put on the bench or replaced,
     * and the new Pokemon is set as active. Messages are printed to indicate the action.
     *
     * @param player The player who is switching Pokemon.
     * @param switchingIn The Pokemon card that is being switched in as active.
     */
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

    /**
     * Plays a Trainer card from the player's hand, applying its ability to the opponent.
     * The Trainer card is moved to the player's discard pile after being played.
     *
     * @param self The player who is playing the Trainer card.
     * @param opponent The opponent who is affected by the Trainer card's ability.
     * @param trainerCard The Trainer card being played.
     */
    public void playTrainerCard(Player self, Player opponent, Card trainerCard){
        Trainer trainer = (Trainer) trainerCard;
        self.discardPile.add(trainerCard);
        self.hand.remove(trainerCard);
        trainer.ability.apply(opponent, self);
        System.out.println(self.getName() + " has played " + trainer.getCardName() + "!");
    }

    /**
     * Sets a specified Pokemon card from the player's bench as the active Pokemon.
     * The Pokemon is removed from the bench after being set as active.
     *
     * @param player The player whose active Pokemon is being changed.
     * @param pokemon The Pokemon card being added to the active position.
     */
    public void addPokemonToActiveFromBench(Player player, Card pokemon){
        player.active = pokemon;
        player.bench.remove(pokemon);
    }

    /**
     * Sets a specified Pokemon card from the player's hand as the active Pokemon.
     * The Pokemon is removed from the hand after being set as active.
     *
     * @param player The player whose active Pokemon is being changed.
     * @param pokemon The Pokemon card being added to the active position.
     */
    public void addPokemonToActiveFromHand(Player player, Card pokemon){
        player.active = pokemon;
        player.hand.remove(pokemon);
    }

    /**
     * Uses a specified ability on the opponent, applying its effects.
     * If the opponent's active Pokemon is knocked out, a prize is drawn,
     * and the opponent is prompted to switch to a new active Pokemon.
     *
     * @param self The player using the Pokemon ability.
     * @param opponent The opponent who is affected by the ability.
     * @param ability The ability being used.
     */
    public void usePokemonAbility(Player self, Player opponent, Ability ability){
        var action = ability.getAction();
        action.apply(opponent, self);
        Pokemon oppActive = (Pokemon) opponent.active;
        if(oppActive.getHp() <= 0){
            self.hand.add(self.prizes.get(0));
            self.prizes.remove(0);
            System.out.println(self.getName() + " has knocked out " + oppActive.getName() + "!");
            opponent.discardPile.add(oppActive);

            System.out.println(opponent.getName() + "! Pick a Pokemon from your bench to be your new active!");
            Optional<Card> chosenPokemonCard = DisplayPokemonInBench(opponent);
            if(chosenPokemonCard.isEmpty()){
                System.out.println("No Pokemon in Bench! Pick from your hand!");
                chosenPokemonCard = DisplayPokemonInHand(opponent, false);
                if(chosenPokemonCard.isPresent()){
                    addPokemonToActiveFromHand(opponent, chosenPokemonCard.get());
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

    /**
     * Checks if the player currently has an active Pokemon.
     * A Pokemon is considered active if its name is not "No Active!".
     *
     * @param player The player whose active status is being checked.
     * @return True if the player has an active Pokemon; false otherwise.
     */
    public boolean hasActive(Player player){
        Pokemon currentActive = (Pokemon) player.active;
        return !currentActive.getName().equals("No Active!");
    }

    /**
     * Determines if the specified player is the winner of the game.
     * A player is considered a winner if their prize pile is empty or
     * if the opponent has no active Pokemon and no Pokemon in hand or bench.
     *
     * @param self The player being checked for victory.
     * @param opponent The opposing player.
     * @return True if the specified player is a winner; false otherwise.
     */
    public boolean isWinner(Player self, Player opponent){
        return self.prizes.isEmpty() || (opponent.hand.stream().noneMatch(card -> card instanceof Pokemon)
                && opponent.bench.stream().noneMatch(card -> card instanceof Pokemon) && !hasActive(opponent));
    }

    /**
     * Displays the available Pokemon cards in the player's hand.
     * The player can select a Pokemon card by entering its corresponding number.
     * If the player chooses to go back, an empty Optional is returned.
     *
     * @param player The player whose hand is being displayed.
     * @return An Optional containing the selected Pokemon card, or an empty Optional if the player chooses to go back.
     */
    public Optional<Card> DisplayPokemonInHand(Player player){{
        return DisplayPokemonInHand(player, true);
    }}

    /**
     * Displays the available Pokemon cards in the player's hand.
     * The player can select a Pokemon card by entering its corresponding number.
     * If the player chooses to go back, an empty Optional is returned.
     *
     * @param player The player whose hand is being displayed.
     * @param allowGoBack Allow the player to go back in the menu.
     * @return An Optional containing the selected Pokemon card, or an empty Optional if the player chooses to go back.
     */
    public Optional<Card> DisplayPokemonInHand(Player player, boolean allowGoBack){
        System.out.println("Available Pokemon in Hand:\n");
        ArrayList<Card> pokemonCards = new ArrayList<>();
        for (int i = 0; i < player.hand.size(); i++){
            if(player.hand.get(i) instanceof Pokemon pokemonCard){
                pokemonCards.add(pokemonCard);
                System.out.println(pokemonCards.size() + ". Name: " + pokemonCard.getName() + " || HP: " + pokemonCard.getHp());
            }
        }

        if (!pokemonCards.isEmpty()) {
            if(allowGoBack)
                System.out.println("\n0. Go Back.");
            while (true) {
                if (gameInput.hasNextInt()) {
                    int selection = gameInput.nextInt();

                    if (selection == 0 && allowGoBack) {
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

    /**
     * Displays the available Pokemon cards in the player's bench.
     * The player can select a Pokemon card by entering its corresponding number.
     * If the player chooses to go back, an empty Optional is returned.
     *
     * @param player The player whose bench is being displayed.
     * @return An Optional containing the selected Pokemon card, or an empty Optional if the player chooses to go back.
     */
    public Optional<Card> DisplayPokemonInBench(Player player){
        return DisplayPokemonInBench(player, true);
    }

    /**
     * Displays the available Pokemon cards in the player's bench.
     * The player can select a Pokemon card by entering its corresponding number.
     * If the player chooses to go back, an empty Optional is returned.
     *
     * @param player The player whose bench is being displayed.
     * @param allowGoBack Whether you want the use to go back in the menu or not.
     * @return An Optional containing the selected Pokemon card, or an empty Optional if the player chooses to go back.
     */
    public Optional<Card> DisplayPokemonInBench(Player player, boolean allowGoBack){
        System.out.println("Available Pokemon in Bench:\n");
        ArrayList<Card> pokemonCards = new ArrayList<>();
        for (int i = 0; i < player.bench.size(); i++){
            if(player.bench.get(i) instanceof Pokemon pokemonCard){
                pokemonCards.add(pokemonCard);
                System.out.println((pokemonCards.size()) + ". Name: " + pokemonCard.getName() + " || HP: " + pokemonCard.getHp());
            }
        }

        if (!pokemonCards.isEmpty()) {
            if(allowGoBack)
                System.out.println("\n0. Go Back.");
            while (true) {
                if (gameInput.hasNextInt()) {
                    int selection = gameInput.nextInt();

                    if (selection == 0 && allowGoBack) {
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

    /**
     * Displays the abilities of a specified Pokemon.
     * The player can select an ability by entering its corresponding number.
     * If the player chooses to go back, an empty Optional is returned.
     *
     * @param pokemon The Pokemon whose abilities are being displayed.
     * @return An Optional containing the selected ability, or an empty Optional if the player chooses to go back.
     */
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

    /**
     * Displays the available Trainer cards in the player's hand.
     * The player can select a Trainer card by entering its corresponding number.
     * If the player chooses to go back, an empty Optional is returned.
     *
     * @param player The player whose Trainer cards are being displayed.
     * @return An Optional containing the selected Trainer card, or an empty Optional if the player chooses to go back.
     */
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

    /**
     * Switches the current player to the opponent and resets action flags.
     * If the current player is player1, it changes the current player to player2
     * and sets player1 as the opponent. It also resets the flags for attack, energy attachment,
     * and trainer card play to false.
     */
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

    /**
     * Displays the available options for the current player and processes their selection.
     * The player can choose to attack, attach energy, play a trainer card, bench a Pokémon from hand,
     * switch active Pokémon with one from the bench, display game info, or end their turn.
     *
     * @return True if the current player wins the game after their turn, false otherwise.
     */
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

    /**
     * Sets up the game by prompting players to enter their names.
     */
    public void SetupGame(){
        System.out.println("Enter a name for Player 1.");
        String player1Name = gameInput.nextLine();
        System.out.println("Enter a name for Player 2.");
        String player2Name = gameInput.nextLine();
        CreatePlayers(player1Name, player2Name);
    }

    /**
     * Displays the current game information, including the active Pokémon, bench Pokémon,
     * and the number of cards in each player's deck and hand.
     */
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
     * Starts the game loop, alternating turns between players until there is a winner.
     */
    public void PlayGame(){
        SetupGame();
        boolean isWinner = false;
        while(!isWinner){
            isWinner = DisplayOptions();
        }

        System.out.println("The winner is " + winner.getName() + "!");
    }

}
