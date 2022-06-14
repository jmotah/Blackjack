import java.util.*;

public class SetupCards {
 
    //Populates ArrayList suits and num with specific suitst and numbers
    public void setupCards(ArrayList<String> suits, ArrayList<Integer> num) {
        suits.add("SPADES");
        suits.add("HEARTS");
        suits.add("DIAMONDS");
        suits.add("CLUBS");

        num.add(1); //Ace
        num.add(2);
        num.add(3);
        num.add(4);
        num.add(5);
        num.add(6);
        num.add(7);
        num.add(8);
        num.add(9);
        num.add(10);
        num.add(11); //Jack
        num.add(12); //Queen
        num.add(13); //King
    }
}
