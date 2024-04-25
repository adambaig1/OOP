public class BaccaratCard extends Card {

    public BaccaratCard(Rank r, Suit s) {
        super(r, s);
    }

    @Override
    public int value() {
        int value = getRank().ordinal() + 1;
        if (value > 9) {
            return 0;
        }
        return value;
    }

    @Override
    public String toString() {
        return String.format("%c%c", getRank().getSymbol(), getSuit().getSymbol());
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
        return getRank() == otherCard.getRank() && getSuit() == otherCard.getSuit();
    }

    @Override
    public int compareTo(Card other) {
        int suitComparison = getSuit().compareTo(other.getSuit());
        if (suitComparison != 0) {
            return suitComparison;
        }
        return getRank().compareTo(other.getRank());
    }
}
