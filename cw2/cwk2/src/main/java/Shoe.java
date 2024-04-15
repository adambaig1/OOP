import java.util.ArrayList;
import java.util.Collections;

public class Shoe{
    private ArrayList<BaccaratCard> cards;

    public Shoe(int decks){
        if (decks != 6 && decks != 8){
            throw new CardException("error: only 6 or 8 decks are allowed");
        }

        cards = new ArrayList<BaccaratCard>(decks*52);

        for (int i = 0; i < decks; i++) {
            for (Card.Suit suit : Card.Suit.values()) {
                for (Card.Rank rank : Card.Rank.values()) {
                    cards.add(new BaccaratCard(rank, suit));
                }
            }
        }

    }

    public int size(){
        return cards.size();
    }

    public void shuffle(){
        Collections.shuffle(cards);
    }

    public Card deal(){
        if (this.size() == 0){
            throw new CardException("error: shoe is empty");
        }

        Card c = new Card(cards.remove(0).getRank(), cards.remove(0).getSuit());
        return c;
    }

    // public BaccaratCard deal(){
    //     if (this.size() == 0){
    //         throw new CardException("error: shoe is empty");
    //     }
    //     return cards.remove(0);
    // }
}