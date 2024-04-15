public class Baccarat {
  public static void main(String[] args) {
      Shoe shoe = new Shoe(6);
      shoe.shuffle();

      BaccaratHand pHand = new BaccaratHand();
      BaccaratHand bHand = new BaccaratHand();

      for (int i = 0; i < 2; i++) {
          BaccaratCard p = new BaccaratCard(shoe.deal().getRank(), shoe.deal().getSuit());
          pHand.add(p);
          BaccaratCard b = new BaccaratCard(shoe.deal().getRank(), shoe.deal().getSuit());
          bHand.add(b);
      }

      // Display the contents and value of each hand
      System.out.printf("Player: %s = %d\n", pHand.toString(), pHand.value());
      System.out.printf("Banker: %s = %d\n", bHand.toString(), bHand.value());

      // Inform the user if the player or banker has a Natural
      if (pHand.isNatural()) {
          System.out.println("Player has a Natural");
      }
      if (bHand.isNatural()) {
          System.out.println("Banker has a Natural");
      }
  }
}
