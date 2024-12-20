# Probability & Stats

This repo is for my CSCI-3327 class. It is meant to show off various implementations of stats functions as well as different case scenarios to use them. Alongside this, is a (mostly) functional Pokemon Trading Card game. 

Below you can find various samples of the code functioning. Click one of the dropdowns to see the two halves of the project.

<details>
  <summary>First Half of Project</summary>

## Assignment 1 - Stats Library & Set Operations
### Stats Library

The statistics library computes various statistical measures and distributions based on provided data sets and parameters. Below are the results obtained from several calculations:

1. **Descriptive Statistics for the Data Set**:  
   Given the list of numbers `[3, 3, 4, 4, 6, 6, 6, 6, 7, 7, 7, 8, 9, 9, 9]`:
   - **Mean**: 6.27 (approximately) - the average of the data set.
   - **Median**: 6.0 - the middle value when the numbers are sorted.
   - **Mode**: 6 - the most frequently occurring number in the data set.
   - **Variance**: 3.93 (approximately) - a measure of the data set's spread.
   - **Standard Deviation**: 1.98 (approximately) - a measure of the average distance of each data point from the mean.
   
   ![](https://i.imgur.com/YoFtSKG.png)

2. **Combinatorial Calculations**:
   - **Factorial of 50**: `30414093201713378043612608166064768844377641568960512000000000000` - the product of all positive integers up to 50.
   - **Permutations of 6 picking 4**: 360 - the number of ways to arrange 4 items from a set of 6.
   - **Combinations of 5 picking 2**: 10 - the number of ways to choose 2 items from a set of 5.

   ![](https://i.imgur.com/NXk14iq.png)

3. **Binomial Distribution**:
   - For a probability \( p = 0.8 \) and total trials = 10, the probability on the 7th trial is **0.20** (approximately).
   - **Expected value** for 5 trials with a probability of 0.5: 2.5.
   - **Variance** for the same distribution: 1.25.
   - **Standard Deviation**: 1.12 (approximately).
  
   ![](https://i.imgur.com/9OQuHcZ.png)

4. **Geometric Distribution**:
   - For 1 trial with a probability of 0.5:
     - **Expected value**: 2.0.
     - **Variance**: 2.0.
     - **Standard Deviation**: 1.41 (approximately).

     ![](https://i.imgur.com/tC8RjnL.png)
### Set Operations

The set operations module provides functions to perform basic set operations on lists. Here are the results from the operations conducted on two given lists:

- **Given List A**:  
  `[1, 2, 3, 7, 8, 9, 10]`

- **Given List B**:  
  `[2, 4, 6, 8, 10]`

  ![](https://i.imgur.com/sHGOoGm.png)
  
1. **Union of List A and List B**:  
   The union combines all unique elements from both lists:  
   **Result**: `[1, 2, 3, 4, 6, 7, 8, 9, 10]`

2. **Intersection of List A and List B**:  
   The intersection finds the common elements in both lists:  
   **Result**: `[2, 8, 10]`

3. **Complement of Subset B with respect to Superset A**:  
   The complement includes all elements in List A that are not in List B:  
   **Result**: `[1, 3, 7, 9]`

  ![](https://i.imgur.com/R9HEH3x.png)

## Assignment 2 - Birthday Problems

The BirthdayCalc program estimates the probability that at least two people in a group share the same birthday. This probability was calculated over multiple runs for different group sizes and trial counts. Below are the results from the simulations:

1. **Probability of 2 people out of 30 having the same birthday**:
   - Average of **10 runs**: **0.6** (60%)
   - Average of **1,000 runs**: **0.709** (70.9%)
   - Average of **10,000 runs**: **0.7024** (70.24%)
   - Average of **100,000 runs**: **0.70787** (70.787%)

2. **Probability of 2 people out of 15 having the same birthday**:
   - Average of **1,000 runs**: **0.259** (25.9%)

![](https://i.imgur.com/F1uDBU9.png)


---

## Assignment 3 - Monty Hall Simulator

The Monty Hall Simulator explores the probability of winning a prize in the classic Monty Hall problem, based on whether the player decides to change their initial choice of door. Below are the results from running the simulation for **10,000 trials**:

1. **Probability of picking the correct door without changing**:
   - Average of **10,000 runs**: **0.326** (32.6%)

2. **Probability of picking the correct door by changing**:
   - Average of **10,000 runs**: **0.6626** (66.26%)

### Analysis
- **Question A**: The sample space can be represented as \( S = \{G, D1, D2\} \), where \( G \) is the prize door and \( D1 \) and \( D2 \) are the non-prize doors. Each outcome initially has a probability of \( \frac{1}{3} \).

- **Question B**: After the contestant has chosen a door, the host reveals a non-prize door, leading to two scenarios:
  - If the player stays with their original choice, the probability remains at \( \frac{1}{3} \).
  - If the player switches to the other door, their chances of winning increase to \( \frac{2}{3} \), as the host's action of revealing a dud door provides additional information. 

![](https://i.imgur.com/uQK52rk.png)

---

## Assignment 4 - Pokemon Game & Monte Carlo 

### Section 1: Monte Carlo Simulation for Drawing Pokémon Cards
This simulation determines the probability of drawing at least one Pokémon card in a hand of 7 cards. The user can specify the number of runs for averaging results. For example, the simulation result for 1,000 runs is approximately 0.132, indicating a 13.2% chance of drawing at least one Pokémon card.

![](https://i.imgur.com/KUzvATs.png)

### Section 2: Probability with Varying Charmander Cards
This section runs a Monte Carlo simulation across varying numbers of Charmander cards in the deck, assessing the probability of drawing at least one Pokémon in a hand of 7 cards. The simulation runs from 1 to 60 Charmander cards and outputs the average probabilities for each amount of runs. The results are as follows:

Here is the code for this:
![](https://i.imgur.com/ZKI5hy6.png)

Followed by a screenshot of the results pictured in Excel:
![](https://i.imgur.com/5XUmJNz.png)

### Section 3: Probability of Rare Candy Cards
This simulation assesses the probability of having all specified Rare Candy cards in the player's prize cards. It runs from 1 to 4 Rare Candy cards, yielding the following results:
- 1 Rare Candy: 0.10350
- 2 Rare Candies: 0.00850
- 3 Rare Candies: 0.00040
- 4 Rare Candies: 0.00000

![](https://i.imgur.com/QbpSvCt.png)

### Section 4 - Pokémon Trading Card Game (If you find any bugs, please send me an email or make an issue in the issues tab)
This section features a full-fledged Pokémon trading card game (not implemented in the best way, I'll admit), which involves two players competing against each other. The game proceeds through the following steps:

1. **Player Names**: The game begins by asking both players to enter their names.

    ![](https://i.imgur.com/cg2mJEC.png)

2. **Choosing Active Pokémon**: Players select their active Pokémon.

    ![](https://i.imgur.com/0UE09OR.png)

3. **Game Mechanics**: Players take turns performing actions such as:
 - Attaching energy to their Pokémon.
 - Playing Trainer cards.
 - Attacking their opponent's Pokémon.

    ![](https://i.imgur.com/zLFQbjT.png)

    ![](https://i.imgur.com/uo9YDZ8.png)
   
The game concludes when one player runs out of prize cards or their opponent has no Pokémon left to play.

![](https://i.imgur.com/g9dlFaq.png)

</details>

<details>
  <summary>Second Half of Project</summary>

## Stats Library (Continued)

This is a continued version of my previous stats library now with the new functions from the [Function Sheet 2](https://github.com/Grizzway/Probability-Stats/blob/master/Function%20Sheet%202.pdf)

1. **Negative Binomial Distribution**:
   - **Negative Binomial Probability (5 successes, 3 trials, p = 0.4)**: 0.0774 (approximately).
   - **Negative Binomial Probability (5 successes, 2 trials, p = 0.3)**: 0.0179 (approximately).
   - **Negative Binomial Mean (r = 3, p = 0.5)**: 6.0.
   - **Negative Binomial Standard Deviation (r = 3, p = 0.5)**: 2.449 (approximately).
   
   ![](https://i.imgur.com/fHYCNge.png)

2. **Hypergeometric Distribution**:
   - **Hypergeometric Probability (2 successes, 5 successes in population, 3 draws, population size = 10)**: 0.0.
   - **Hypergeometric Probability (50 successes, 10 successes in population, 5 draws, population size = 2)**: 0.2098 (approximately).
   - **Hypergeometric Mean (successes = 20, draws = 10, population size = 50)**: 4.0.
   - **Hypergeometric Standard Deviation (successes = 20, draws = 10, population size = 50)**: 1.400 (approximately).
   
   ![](https://i.imgur.com/5bigX72.png)

3. **Poisson Distribution**:
   - **Poisson Probability (λ = 5.0, k = 10)**: 0.0181 (approximately).
   
   ![](https://i.imgur.com/KMHdPhZ.png)

4. **Uniform Distribution**:
   - **Uniform Probability (a = 1.0, b = 5.0, x = 3.0)**: 0.25.
   
   ![](https://i.imgur.com/KgXUW5X.png)

5. **Exponential Distribution**:
   - **Exponential Probability (λ = 2.0, x = 3.0)**: 0.1116 (approximately).
   
   ![](https://i.imgur.com/sZ6Scaa.png)

## Salting/Smoothing Program & Learning MatLab
Check the folder labeled ["PlotterDatas"](https://github.com/Grizzway/Probability-Stats/tree/master/PlotterDatas) to see the full list of generated data. In this folder there are two folders labeled ["GeneratedData"](https://github.com/Grizzway/Probability-Stats/tree/master/PlotterDatas/GeneratedData) and ["CompiledData](https://github.com/Grizzway/Probability-Stats/tree/master/PlotterDatas/CompiledData). Generated Data is what was made by the program, and CompiledData is all of the steps (Generated,Salting,Smoothing) for each trial. 

I also used JFreeChart to chart the data without the need of excel. The screenshot of the output is shown below:
![](https://i.imgur.com/I0er92a.png)

I also learned Matlab. I took the Official MatLab Onboarding course and the [Certificate](https://github.com/Grizzway/Probability-Stats/blob/master/MatLab/MatLabcertificate.pdf) for that is in the project files along with everything else.
![](https://i.imgur.com/3XjfSoc.png)

With MatLab, I learned how the syntax worked and how to plot graphs. I wrote [some code](https://github.com/Grizzway/Probability-Stats/tree/master/MatLab) to plot the graphs for the first generated Trial data.
![](https://i.imgur.com/g2TtJ5S.png)

## Dataset Project

The dataset I used for this project was a collection of the average CO2 PPM in the atmosphere for each month from January 1979 until August 2024. 
Here is an example of the dataset:

![](https://i.imgur.com/rp5ubGl.png)

More of this can be found in the ["CO2 PPM Data.csv"](https://github.com/Grizzway/Probability-Stats/blob/master/CO2%20PPM%20Data.csv) and ["SectionQuestions.pdf"](https://github.com/Grizzway/Probability-Stats/blob/master/SectionQuestions.pdf) where I went and made a bunch of questions regarding this data.

## Android Pokemon Game App

See contents of ["Journey With Android Studio.pdf"](https://github.com/Grizzway/Probability-Stats/blob/master/Journey%20With%20Android%20Studio.pdf)

</details>

