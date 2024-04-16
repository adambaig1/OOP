public class BaccaratCard {
    private Card.Rank rank;
    private Card.Suit suit;
    
    public BaccaratCard(Card.Rank r, Card.Suit s) {
        this.rank = r;
        this.suit = s;
    }

    public Card.Rank getRank() {
        return this.rank;
    }

    public Card.Suit getSuit() {
        return this.suit;
    }

    @Override
    public String toString() {
        return String.format("%c%c", this.rank.getSymbol(), this.suit.getSymbol());
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof BaccaratCard)) {
            return false;
        }
        BaccaratCard otherCard = (BaccaratCard) other;
        return rank == otherCard.getRank() && suit == otherCard.getSuit();
    }


    public int compareTo(BaccaratCard other) {
        int suitComparison = suit.compareTo(other.getSuit());
        if (suitComparison != 0) {
            return suitComparison;
        }
        return rank.compareTo(other.getRank());
    }   

    public int value() {
        int value = this.rank.ordinal() + 1;
        if (value > 9) {
            return 0;
        }
        return value;
    }
}
