import java.util.ArrayList;

public class BaccaratHand {
    private ArrayList<BaccaratCard> hand;

    public BaccaratHand() {
        hand = new ArrayList<BaccaratCard>();
    }

    public int size() {
        return hand.size();
    }

    public void add(BaccaratCard card) {
        hand.add(card);
    }

    public int value() {
        int totalValue = 0;
        for (BaccaratCard card : hand) {
            totalValue += card.value();
        }
        return totalValue % 10;
    }
    

    public boolean isNatural() {
        if (this.size() == 2) {
            return this.value() == 8 || this.value() == 9;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (BaccaratCard card : hand) {
            s.append(" ").append(card.toString()); 
        }
        return s.toString();
    }

}
