import java.util.*;

public class Dealer {
    
    Card card;
    boolean drawIsPossible = true;
    boolean ifDealerDrewAfterPlayerStood = false;
    
    //Returns the boolean value
    public boolean getDrawIsPossible() {
        return drawIsPossible;
    }
    
    //Sets the boolean value
    public boolean getIfDealerDrewAfterPlayerStood() {
        return ifDealerDrewAfterPlayerStood;
    }
    
    public void setIfDealerDrewAfterPlayerStood (boolean newValue) {
        ifDealerDrewAfterPlayerStood = true;
    }
    
    //Deals a card, specifically done for the player's hand
    public Card dealCardForPlayer(ArrayList<String> suits, ArrayList<Integer> num, ArrayList<Card> playerHand) {
        
        //Initially chooses random card
        int suitIndex = (int) (Math.random() * suits.size());
        int numIndex = (int) (Math.random() * num.size());
        int value = num.get(numIndex);
        
        //Checks if that specific card was already dealt. If it was, chooses another random card continues through that loop until it chooses a card that wasn't used
        while(cardAlreadyDealt(suits, num, playerHand, suitIndex, numIndex)) {
            suitIndex = (int) (Math.random() * suits.size());
            numIndex = (int) (Math.random() * num.size());
            value = num.get(numIndex);
        }
    
        if((num.get(numIndex) == 11) || (num.get(numIndex) == 12) || (num.get(numIndex) == 13)) {
            value = 10;
        }
        return new Card(suits.get(suitIndex), num.get(numIndex), value);
    }
    
    //Checks to see if a card was already dealt or not
    public boolean cardAlreadyDealt(ArrayList<String> suits, ArrayList<Integer> num, ArrayList<Card> hand, int suitIndex, int numIndex) {
        
        for(int i = 0; i < hand.size(); i++) {
            if(hand.get(i).getSuit().equals(suits.get(suitIndex))) {
                if(hand.get(i).getNum() == num.get(numIndex)) {
                    return true;
                }
            }
        }
        return false;
    }
    
    //Checks if a card pulled was an ace. If yes, gives the option to alter the value between 1 and 11
    public void checkForAce(ArrayList<Card> playerHand) {
        
        String userInput = "";
        
        if(playerHand.size() == 2) {
            if(playerHand.get(0).getNum() == 1) {
                while((userInput != "11") || (userInput != "1")) {
                    Scanner input = new Scanner(System.in);
                    
                    System.out.println("Your first card delt was an ACE! Type '11' for it's value to be 11, or type '1' for it's value to be 1.");
                    userInput = "";
                    userInput = input.nextLine();
                    
                    if(userInput.equals("1")) {
                        playerHand.get(0).setValue(1);
                        break;
                    }else if(userInput.equals("11")) {
                        playerHand.get(0).setValue(11);
                        break;
                    }
                }
            }else if(playerHand.get(1).getNum() == 1){
                while((userInput != "11") || (userInput != "1")) {
                    Scanner input = new Scanner(System.in);
                    
                    System.out.println("Your second card delt was an ACE! Type '11' for it's value to be 11, or type '1' for it's value to be 1.");
                    userInput = "";
                    userInput = input.nextLine();
                    
                    if(userInput.equals("1")) {
                        playerHand.get(1).setValue(1);
                        break;
                    }else if(userInput.equals("11")) {
                        playerHand.get(1).setValue(11);
                        break;
                    }
                }
            }   
        }else{
            if(playerHand.get(playerHand.size()-1).getNum() == 1) {
                while((userInput != "11") || (userInput != "1")) {
                    Scanner input = new Scanner(System.in);
                    
                    System.out.println("The delt card was an ACE! Type '11' for it's value to be 11, or type '1' for it's value to be 1!");
                    userInput = "";
                    userInput = input.nextLine();
                    
                    if(userInput.equals("1")) {
                        playerHand.get(playerHand.size()-1).setValue(1);
                        System.out.println("------------");
                        break;
                    }else if(userInput.equals("11")) {
                        playerHand.get(playerHand.size()-1).setValue(11);
                        System.out.println("------------");
                        break;
                    }
                }
            }
        }
    }
    
    //Deals a card, specifically done for the computer's hand
    public Card dealCardForComputer(ArrayList<String> suits, ArrayList<Integer> num, ArrayList<Card> computerHand) {
        
        //Initially chooses random card
        int suitIndex = (int) (Math.random() * suits.size());
        int numIndex = (int) (Math.random() * num.size());
        int value = num.get(numIndex);
        
        //Checks if that specific card was already dealt. If it was, chooses another random card continues through that loop until it chooses a card that wasn't used
        while(cardAlreadyDealt(suits, num, computerHand, suitIndex, numIndex)) {
            suitIndex = (int) (Math.random() * suits.size());
            numIndex = (int) (Math.random() * num.size());
            value = num.get(numIndex);
        }
        if((num.get(numIndex) == 11) || (num.get(numIndex) == 12) || (num.get(numIndex) == 13)) {
            value = 10;
        }
        return new Card(suits.get(suitIndex), num.get(numIndex), value);
    }
    
    //Deals the cards and applies the logic to if a computer draws a card for not
    public void dealCardAndLogicForComputer(ArrayList<String> suits, ArrayList<Integer> num, ArrayList<Card> computerHand) {
       
       Card card;
       int valueTotal = 0;
        
        for(int i = 0; i < computerHand.size(); i++) {
            valueTotal += computerHand.get(i).getValue();
        }
        
        if(drawIsPossible && valueTotal < 21) {
            if((valueTotal-21) < -4) {
                card = dealCardForComputer(suits, num, computerHand);
                computerHand.add(card);
                valueTotal += computerHand.get(computerHand.size()-1).getValue();
            }else if(((valueTotal - 21) >= -4) && ((valueTotal-21) < -2) && (Math.random() >= 0.5)) {
                card = dealCardForComputer(suits, num, computerHand);
                computerHand.add(card);
                valueTotal += computerHand.get(computerHand.size()-1).getValue();
            }else if((valueTotal - 21) >= -2) {
                drawIsPossible = false;
            }else{
                drawIsPossible = false;
            }
        }else{
            drawIsPossible = false;
        }
    }
    
    //Continuously deals cards as needed to dealer, after the players stands
    public boolean dealCardAndLogicForComputerAfterStand(ArrayList<String> suits, ArrayList<Integer> num, ArrayList<Card> computerHand) {
        Card card;
        int valueTotal = 0;
        boolean returnValue = false;
        
        for(int i = 0; i < computerHand.size(); i++) {
            valueTotal += computerHand.get(i).getValue();
        }
        
        while(drawIsPossible && valueTotal < 21) {
            if((valueTotal-21) < -4) {
                card = dealCardForComputer(suits, num, computerHand);
                computerHand.add(card);
                valueTotal += computerHand.get(computerHand.size()-1).getValue();
                returnValue = true;
            }else if(((valueTotal - 21) >= -4) && ((valueTotal-21) < -2) && (Math.random() >= 0.33)) {
                card = dealCardForComputer(suits, num, computerHand);
                computerHand.add(card);
                valueTotal += computerHand.get(computerHand.size()-1).getValue();
                returnValue = true;
            }else if((valueTotal - 21) >= -2) {
                drawIsPossible = false;
            }else{
                drawIsPossible = false;
            }
        }
        return returnValue;
    }
    
    //Prints the player hand
    public void printHandPlayer(ArrayList<Card> hand) {
        
        System.out.println("------------");
        System.out.println("PLAYER'S HAND: ");
        
        int totalCardValue = 0;
        
        for(int i = 0; i < hand.size(); i++) {
            System.out.println(hand.get(i).calculateIfFaceCard() + " of " + hand.get(i).getSuit());
            totalCardValue += hand.get(i).getValue();
        }
        System.out.println("TOTAL VALUE - " + totalCardValue);
    }
    
    //Prints the computer hand, not showing their second card
    public void printHandComputer(ArrayList<Card> hand) {
        System.out.println("------------");
        System.out.println("DEALER'S HAND: ");
        
        System.out.println(hand.get(0).calculateIfFaceCard() + " of " + hand.get(0).getSuit());
        System.out.println("TURNED OVER!");
        
        for(int i = 2; i < hand.size(); i++) {
            System.out.println(hand.get(i).calculateIfFaceCard() + " of " + hand.get(i).getSuit());
        }
    }
    
    //Prints the computer's hand, showing their second card
    public void printHandComputerFinal(ArrayList<Card> hand) {
        System.out.println("------------");
        System.out.println("DEALER'S HAND: ");
        
        int totalHandValue = 0;
        
        for(int i = 0; i < hand.size(); i++) {
            System.out.println(hand.get(i).calculateIfFaceCard() + " of " + hand.get(i).getSuit());
            totalHandValue += hand.get(i).getValue();
        }
        System.out.println("TOTAL VALUE - " + totalHandValue);
    }
    
    //Checks for a win
    public String checkForWin(ArrayList<Card> playerHand, ArrayList<Card> computerHand) {

        int totalPlayerHandNum = 0;
        int totalComputerHandNum = 0;

        for(int i = 0; i < playerHand.size(); i++) {
            totalPlayerHandNum += playerHand.get(i).getValue();
        }

        for(int i = 0; i < computerHand.size(); i++) {
            totalComputerHandNum += computerHand.get(i).getValue();
        }
        
        System.out.println("PLAYER - " + totalPlayerHandNum + " card value!");
        System.out.println("DEALER - " + totalComputerHandNum + " card value!");
        
        if((totalPlayerHandNum == 21) && playerHand.size() == 2) {
            System.out.println("------------");
            return "BLACKJACK! PLAYER WINS!";
        }else if((totalPlayerHandNum > 21) && (totalComputerHandNum > 21)) {
            System.out.println("------------");
            return "BUST! DEALER WINS!";
        }else if((totalPlayerHandNum > 21) && (totalComputerHandNum < 21)) {
            System.out.println("------------");
            return "BUST! DEALER WINS!";
        }else if((totalPlayerHandNum < 21) && (totalComputerHandNum > 21)) {
            System.out.println("------------");
            return "PLAYER WINS!";
        }else if(Math.abs(totalPlayerHandNum-21) == Math.abs(totalComputerHandNum-21)) {
            System.out.println("------------");
            return "DEALER WINS!";
        }else if(Math.abs(totalPlayerHandNum-21) < Math.abs(totalComputerHandNum-21)) {
            System.out.println("------------");
            return "PLAYER WINS!";
        }else if(Math.abs(totalPlayerHandNum-21) > Math.abs(totalComputerHandNum-21)) {
            System.out.println("------------");
            return "DEALER WINS!";
        }
        return "COMPUTER WINS!";
    }
    
    //Automatically checks if there is a bust by the player before he stands
    public boolean checkForWinBeforeStand(ArrayList<Card> playerHand, ArrayList<Card> computerHand) {
        
        int totalPlayerHandNum = 0;
        int totalComputerHandNum = 0;

        for(int i = 0; i < playerHand.size(); i++) {
            totalPlayerHandNum += playerHand.get(i).getValue();
        }

        for(int i = 0; i < computerHand.size(); i++) {
            totalComputerHandNum += computerHand.get(i).getValue();
        }
        
        if(totalPlayerHandNum > 21) {
            return true;
        }else if((playerHand.size() == 2) && totalPlayerHandNum == 21) {
            return true;
        }
        return false;
    }
}
