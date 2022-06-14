import java.util.*;

public class Main
{
    public static void main(String[] args)
    {
        ArrayList<String> suits = new ArrayList<String>();
        ArrayList<Integer> num = new ArrayList<Integer>();
        
        ArrayList<Card> playerHand = new ArrayList<Card>();
        ArrayList<Card> computerHand = new ArrayList<Card>();
        
        Scanner input = new Scanner(System.in);
        String userInput = "";
        
        SetupCards sCards = new SetupCards();
        Dealer dealer = new Dealer();
        sCards.setupCards(suits, num);
        
        boolean stillPlayable = true;
        boolean checkWinBeforeStand = false;
    
        while(!userInput.toLowerCase().equals("play")) {
            System.out.println("Welcome to BLACKJACK!");
            System.out.println("Type 'PLAY' to play!");
        
            userInput = "";
            userInput = input.nextLine();
        
            if(!userInput.toLowerCase().equals("play")) {
                System.out.println("------------");
            }
        }
        System.out.println("------------");
        
        Card card;
        card = dealer.dealCardForPlayer(suits, num, playerHand);
        playerHand.add(card);
        card = dealer.dealCardForPlayer(suits, num, playerHand);
        playerHand.add(card);
            
        System.out.println("PLAYER'S HAND:");
        System.out.println("Your first card: " + playerHand.get(0).calculateIfFaceCard() + " of " + playerHand.get(0).getSuit());
        System.out.println("Your second card: " + playerHand.get(1).calculateIfFaceCard() + " of " + playerHand.get(1).getSuit());
        System.out.println("------------");
        dealer.checkForAce(playerHand);
            
        dealer.dealCardAndLogicForComputer(suits, num, computerHand);
        dealer.dealCardAndLogicForComputer(suits, num, computerHand);
            
        System.out.println("DEALER'S HAND:");
        System.out.println("Dealers first card: " + computerHand.get(0).calculateIfFaceCard() + " of " + computerHand.get(0).getSuit());
        System.out.println("Dealers second card: Turned Over");
        
        
        if(dealer.checkForWinBeforeStand(playerHand, computerHand)) {
            stillPlayable = false;
        }
        
        while(stillPlayable) {
                
            System.out.println("------------");
            System.out.println("To ask for another card, type 'HIT'");
            System.out.println("If you want to hold your total and end your turn, type 'STAND'");
                
            userInput = "";
            userInput = input.nextLine();
                
            if(userInput.toLowerCase().equals("hit")) {
                card = dealer.dealCardForPlayer(suits, num, playerHand);
                playerHand.add(card);
                System.out.println("------------");
                System.out.println("Player next added card: " + playerHand.get(playerHand.size()-1).calculateIfFaceCard() + " of " + playerHand.get(playerHand.size()-1).getSuit());
                dealer.checkForAce(playerHand);
                
                dealer.printHandPlayer(playerHand);
                
                if(dealer.checkForWinBeforeStand(playerHand, computerHand)) {
                    stillPlayable = false;
                    break;
                }
                
            }else if(userInput.toLowerCase().equals("stand")) {
                if(dealer.dealCardAndLogicForComputerAfterStand(suits, num, computerHand)) {
                    dealer.setIfDealerDrewAfterPlayerStood(true);
                }
                if(dealer.getIfDealerDrewAfterPlayerStood()) {
                    System.out.println("------------");
                    System.out.println("The dealer drew a few more times!");
                }
                stillPlayable = false;
            }
        }
        System.out.println("------------");
        System.out.println(dealer.checkForWin(playerHand, computerHand));
        
        System.out.println("------------");
        System.out.println("ALL PLAYERS FINAL CARDS:");
        dealer.printHandPlayer(playerHand);
        dealer.printHandComputerFinal(computerHand);
        System.out.println("------------");
        
    }
}
