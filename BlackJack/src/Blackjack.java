//This is code I directly used for my work last semester.

import java.util.Scanner;

/*
* Number of player wins:
* Number of dealer wins:
* Number of tie games:
* Total # of games played:
* Percentage of player wins:
*
* BlackJack Algorithm:
* START GAME
* DRAW DEALER CARD
* TELL PLAYER CARD / HAND
* GET USER CHOICE (1-4)
* IF 1:
*   DRAW ANOTHER CARD
*   ADD CARD TO HAND
*   TELL PLAYER CARD / HAND
*   IF LOST GAME:
*       UPDATE SCOREBOARD
*       START NEW GAME
* IF 2:
*   TELL PLAYER DEALER / OWN HAND
*   TELL PLAYER WHO WON
*   UPDATE SCOREBOARD
*   START NEW GAME
* IF 3:
*   DISPLAY STATS
*   GET USER CHOICE (1-4)
* IF 4:
*   QUIT GAME
*/

public class Blackjack {
    private int playerWins = 0;
    private int dealerWins = 0;
    private int tieGames = 0;
    private int totalGames = 0;
    private int playerCard = 0;
    private int dealerCard = 0;
    private int playerHand = 0;
    Scanner scan = new Scanner(System.in);
    P1Random rng = new P1Random();

    private void playerDrawCard() {
        int drawnCard = rng.nextInt(13) + 1;
        int addToHand = 0;
        if (drawnCard >= 11) {
            addToHand = 10;
        } else {
            addToHand = drawnCard;
        }
        playerHand += addToHand;
        tell(drawnCard);
    }

    private void resetGame() {
        playerCard = 0;
        dealerCard = 0;
        playerHand = 0;
    }

    private int getPlayerInput() {
        System.out.println("1. Get another card");
        System.out.println("2. Hold hand");
        System.out.println("3. Print statistics");
        System.out.println("4. Exit\n");

        System.out.print("Choose an option:");

        int Choice = scan.nextInt();
        System.out.println(" ");
        return Choice;
    }

    private void cycle() {
        switch (getPlayerInput()) {
            case 1:
                playerDrawCard();
                if (playerHand > 21) {
                    System.out.println("You exceeded 21! You lose.\n");
                    dealerWins++;
                    resetGame();
                    return;
                } else if (playerHand == 21) {
                    System.out.println("BLACKJACK! You win!\n");
                    playerWins++;
                    resetGame();
                    return;
                }
                cycle();
                break;
            case 2:
                dealerDrawCard();
                System.out.println("Dealer's hand: " + dealerCard);
                System.out.println("Your hand is: " + playerHand);
                // CHECK WINS
                if (dealerCard > 21) { // Dealer Bust
                    System.out.println("\nYou win!\n");
                    this.playerWins++;
                } else if (playerHand > dealerCard) { // Player High hand
                    System.out.println("\nYou win!\n");
                    this.playerWins++;
                } else if (playerHand < dealerCard) { // Dealer High hand
                    System.out.println("\nDealer wins!\n");
                    this.dealerWins++;
                } else if (playerHand == dealerCard) {
                    System.out.println("\nIt's a tie! No one wins!\n");
                    this.tieGames++;
                }
                resetGame();
                return;
            case 3:
                printStatistics();
                cycle();
                break;
            case 4:
                System.exit(0);
                break;
            default:
                System.out.println("Invalid input!" +
                        "\nPlease enter an integer value between 1 and 4.\n");
                cycle();
                break;
        }
    }

    private void printStatistics() {
        System.out.println("Number of Player wins: " + playerWins);
        System.out.println("Number of Dealer wins: " + dealerWins);
        System.out.println("Number of tie games: " + tieGames);
        System.out.println("Total # of games played is: " + (totalGames - 1));
        System.out.println("Percentage of Player wins: " + (((double)playerWins * 100) / ((double)totalGames - 1)) + ("%"));
        System.out.println("  ");
    }

    public void play() {
        this.totalGames++;
        System.out.println("START GAME #" + totalGames + "\n");
        playerDrawCard();
        cycle();
    }

    private void dealerDrawCard() {
        dealerCard = rng.nextInt(11) + 16;
    }

    public void tell(int playerCard) {
        if (playerCard == 1){
            System.out.println("Your card is a ACE!");
            System.out.println("Your hand is: " + playerHand);
        } else if (playerCard == 11){
            System.out.println("Your card is a JACK!");
            System.out.println("Your hand is: " + (playerHand));
        } else if (playerCard == 12){
            System.out.println("Your card is a QUEEN!");
            System.out.println("Your hand is: " + (playerHand ));
        } else if (playerCard == 13){
            System.out.println("Your card is a KING!");
            System.out.println("Your hand is: " + (playerHand));
        } else {
            System.out.println("Your card is a " + playerCard + "!");
            System.out.println("Your hand is: " + playerHand);
        }
        System.out.println("");
    }

    public static void main(String args[]) {
        Blackjack blackjack = new Blackjack();
        while (true) {
            blackjack.play();
        }
    }
}