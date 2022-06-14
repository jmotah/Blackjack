public class Card {
    
    private String suit;
    private int num;
    private int value;
    
    //Class Constructor
    public Card(String suit_, int num_) {
        suit = suit_;
        num = num_;
        value = num_;
    }
    
    public Card (String suit_, int num_, int value_) {
        suit = suit_;
        num = num_;
        value = value_;
    }
    
    //Grabs the cards suit
    public String getSuit() {
        return suit.toUpperCase();
    }
    
    //Grabs the cards associated number
    public int getNum() {
        return num;
    }
    
    //Grabs the cards value
    public int getValue() {
        return value;
    }
    
    //Sets the value. For aces only, allowing value to be changed between 1 and 11
    public void setValue(int newValue) {
        value = newValue;
    }
    
    //If the number picked was a 1, 11, 12, or 13, then it displays the face rather than the associated number
    public String calculateIfFaceCard() {
        if(getNum() == 1) {
            return "ACE";
        }else if(getNum() == 11) {
            return "JACK";
        }else if(getNum() == 12) {
            return "QUEEN";
        }else if(getNum() == 13) {
            return "KING";
        }
        return getNum() + "";
    }
    
}
