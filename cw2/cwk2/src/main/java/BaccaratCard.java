public class BaccaratCard {
    private Card.Rank r;
    private Card.Suit s;
    
    public BaccaratCard(Card.Rank r, Card.Suit s) {
        this.r = r;
        this.s = s;
    }

    public Card.Rank getRank() {
        return r;
    }

    public Card.Suit getSuit() {
        return s;
    }

    @Override
    public String toString() {
        return String.format("%c%c", r.getSymbol(), s.getSymbol());
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
        return r == otherCard.getRank() && s == otherCard.getSuit();
    }

    @Override
    public int compareTo(BaccaratCard other) {
        int suitComparison = s.compareTo(other.getSuit());
        if (suitComparison != 0) {
            return suitComparison;
        }
        return r.compareTo(other.getRank());
    }   

    public int value() {
        int value = r.ordinal() + 1;
        if (value > 10) {
            return 0; // Baccarat rules: only consider units digit
        }
        return value;
    }
}
