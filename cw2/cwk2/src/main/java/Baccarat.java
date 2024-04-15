public class Baccarat {
  public static void main(String[] args) {
      Shoe shoe = new Shoe(6);
      shoe.shuffle();

      BaccaratHand pHand = new BaccaratHand();
      BaccaratHand bHand = new BaccaratHand();

      if (args.length > 2 && (args[2].equals("-i") || args[2].equals("--interactive"))){

        int roundc = 1;
        
        while (shoe.size()>6 && (ans == "y" || ans == "Y")){
            
            printf("Round %d\n", roundc);
 
            for (int i = 0; i < 2; i++) {
                BaccaratCard p = new BaccaratCard(shoe.deal().getRank(), shoe.deal().getSuit());
                pHand.add(p);
                BaccaratCard b = new BaccaratCard(shoe.deal().getRank(), shoe.deal().getSuit());
                bHand.add(b);
            }
            
            System.out.printf("Player: %s = %d\n", pHand.toString(), pHand.value());
            System.out.printf("Banker: %s = %d\n", bHand.toString(), bHand.value());
            
            int result = cardProcessing(pHand, bHand);

            switch(result){
                case 0:
                    System.out.println("Player win!");
                case 1:
                    System.out.println("Banker win!");
                case 2:
                    System.out.println("Tie");
            }

            Scanner scanner = new Scanner(System.in);
            System.out.println("Another round? (y/n)");
            String ans = scanner.nextLine();

            roundc++;
        }
      }
      else{

        for (int i = 0; i < 2; i++) {
            BaccaratCard p = new BaccaratCard(shoe.deal().getRank(), shoe.deal().getSuit());
            pHand.add(p);
            BaccaratCard b = new BaccaratCard(shoe.deal().getRank(), shoe.deal().getSuit());
            bHand.add(b);
        }

        System.out.printf("Player: %s = %d\n", pHand.toString(), pHand.value());
        System.out.printf("Banker: %s = %d\n", bHand.toString(), bHand.value());


        if (pHand.isNatural()) {
            System.out.println("Player has a Natural");
        }
        if (bHand.isNatural()) {
            System.out.println("Banker has a Natural");
        }
      }
  }

  public static int cardProcessing(BaccaratHand pHand, BaccaratHand bHand, Shoe shoe) {
    if (pHand.isNatural()) {
        System.out.println("Player has a Natural");
        return 0;
    }
    if (bHand.isNatural()) {
        System.out.println("Banker has a Natural");
        return 1;
    }

    if (pHand.value() <= 5) {
        System.out.println("Dealing third card to player ...");
        BaccaratCard p = new BaccaratCard(shoe.deal().getRank(), shoe.deal().getSuit());
        pHand.add(p);

        System.out.printf("Player: %s = %d\n", pHand.toString(), pHand.value());
        System.out.printf("Banker: %s = %d\n", bHand.toString(), bHand.value());

        switch (bHand.value()) {
            case 0:
            case 1:
            case 2:
                System.out.println("Dealing third card to banker ...");
                BaccaratCard b = new BaccaratCard(shoe.deal().getRank(), shoe.deal().getSuit());
                bHand.add(b);
                System.out.printf("Player: %s = %d\n", pHand.toString(), pHand.value());
                System.out.printf("Banker: %s = %d\n", bHand.toString(), bHand.value());
                break;
            case 3:
                if (p.getRank() != 8) {
                    System.out.println("Dealing third card to banker ...");
                    BaccaratCard q = new BaccaratCard(shoe.deal().getRank(), shoe.deal().getSuit());
                    bHand.add(q);
                    System.out.printf("Player: %s = %d\n", pHand.toString(), pHand.value());
                    System.out.printf("Banker: %s = %d\n", bHand.toString(), bHand.value());
                }
                break;
            case 4:
                if ((int)p.getRank() >= 2 && (int)p.getRank() <= 7) {
                    System.out.println("Dealing third card to banker ...");
                    BaccaratCard r = new BaccaratCard(shoe.deal().getRank(), shoe.deal().getSuit());
                    bHand.add(r);
                    System.out.printf("Player: %s = %d\n", pHand.toString(), pHand.value());
                    System.out.printf("Banker: %s = %d\n", bHand.toString(), bHand.value());
                }
                break;
            case 5:
                if ((int)p.getRank() >= 4 && (int)p.getRank() <= 7) {
                    System.out.println("Dealing third card to banker ...");
                    BaccaratCard s = new BaccaratCard(shoe.deal().getRank(), shoe.deal().getSuit());
                    bHand.add(s);
                    System.out.printf("Player: %s = %d\n", pHand.toString(), pHand.value());
                    System.out.printf("Banker: %s = %d\n", bHand.toString(), bHand.value());
                }
                break;
            case 6:
            case 7:
                // Banker stands
                break;
            default:
                break;
        }
    }

    return 2;
}


  
}
