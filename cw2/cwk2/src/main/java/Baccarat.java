import java.util.Scanner;

public class Baccarat {
    public static void main(String[] args) {
        Shoe shoe = new Shoe(6);
        shoe.shuffle();

        if (args.length > 0 && (args[0].equals("-i") || args[0].equals("--interactive"))) {

            int roundc = 1;
            int pwin = 0;
            int bwin = 0;
            int tie = 0;
            Boolean finish = true;

            Scanner scanner = new Scanner(System.in);

            while (finish) {

                if (shoe.size() < 6){
                    System.out.println("shoe has below 6 cards in it. game over");
                    finish = false;
                    break;
                }

                BaccaratHand pHand = new BaccaratHand();
                BaccaratHand bHand = new BaccaratHand();

                System.out.println("");
                System.out.printf("Round %d\n", roundc);

                for (int i = 0; i < 2; i++) {
                    if (shoe.size() == 0){
                        finish = false;
                        break;
                    }
                    else{
                        BaccaratCard p = new BaccaratCard(shoe.deal().getRank(), shoe.deal().getSuit());
                        pHand.add(p);
                    }

                    if (shoe.size() == 0){
                        finish = false;
                        break;
                    }
                    else{
                        BaccaratCard b = new BaccaratCard(shoe.deal().getRank(), shoe.deal().getSuit());
                        bHand.add(b);
                    }
                }

                System.out.printf("Player: %s = %d\n", pHand.toString(), pHand.value());
                System.out.printf("Banker: %s = %d\n", bHand.toString(), bHand.value());

                int result = cardProcessing(pHand, bHand, shoe);

                switch (result) {
                    case 0:
                        System.out.println("Player win!");
                        pwin++;
                        break;
                    case 1:
                        System.out.println("Banker win!");
                        bwin++;
                        break;
                    case 2:
                        System.out.println("Tie");
                        tie++;
                        break;
                    case 100:
                        finish = false;
                        break;
                }

                System.out.printf("Another round? (y/n) ");
                String ans = scanner.nextLine();

                if (ans.equals("y") || ans.equals("Y")){
                    finish = true;
                }
                else if (ans.equals("n") || ans.equals("N")){
                    finish = false;
                }
                else{
                    System.out.println("Invalid input. Either y or n only");
                }

                roundc++;
            }

            scanner.close();

            System.out.println("");
            System.out.printf("%d rounds played\n", roundc-1);
            System.out.printf("%d player wins\n", pwin);
            System.out.printf("%d banker wins\n", bwin);
            System.out.printf("%d ties\n", tie);

        } else {

            BaccaratHand pHand = new BaccaratHand();
            BaccaratHand bHand = new BaccaratHand();

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

        Boolean dealt = false;
        if (shoe.size()<3){
            return 100;
        }
        
        if (pHand.isNatural() && bHand.isNatural()){
            System.out.println("Both are natural");
            return 2;
        } else if (pHand.isNatural()) {
            System.out.println("Player has a Natural");
            return 0;
        } else if (bHand.isNatural()) {
            System.out.println("Banker has a Natural");
            return 1;
        }

        if (pHand.value() < 6){
            System.out.println("Dealing third card to player ...");
            BaccaratCard p = new BaccaratCard(shoe.deal().getRank(), shoe.deal().getSuit());
            pHand.add(p);
            System.out.printf("Player: %s = %d\n", pHand.toString(), pHand.value());
            System.out.printf("Banker: %s = %d\n", bHand.toString(), bHand.value());

            if (bHand.value()<3){
                System.out.println("Dealing third card to banker ...");
                BaccaratCard b = new BaccaratCard(shoe.deal().getRank(), shoe.deal().getSuit());
                bHand.add(b);
                System.out.printf("Player: %s = %d\n", pHand.toString(), pHand.value());
                System.out.printf("Banker: %s = %d\n", bHand.toString(), bHand.value()); 
                dealt = true;
            }
            else {
                switch (bHand.value()) {
                    case 3:
                        if(p.value() != 8){
                            System.out.println("Dealing third card to banker ...");
                            BaccaratCard q = new BaccaratCard(shoe.deal().getRank(), shoe.deal().getSuit());
                            bHand.add(q);
                            System.out.printf("Player: %s = %d\n", pHand.toString(), pHand.value());
                            System.out.printf("Banker: %s = %d\n", bHand.toString(), bHand.value());  
                            dealt = true;                       
                        }
                        break;
                    case 4:
                        if (1<p.value() && p.value()<8){
                            System.out.println("Dealing third card to banker ...");
                            BaccaratCard q = new BaccaratCard(shoe.deal().getRank(), shoe.deal().getSuit());
                            bHand.add(q);
                            System.out.printf("Player: %s = %d\n", pHand.toString(), pHand.value());
                            System.out.printf("Banker: %s = %d\n", bHand.toString(), bHand.value());
                            dealt = true;                         
                        }
                        break;
                    case 5:
                        if (3<p.value() && p.value()<8){
                            System.out.println("Dealing third card to banker ...");
                            BaccaratCard q = new BaccaratCard(shoe.deal().getRank(), shoe.deal().getSuit());
                            bHand.add(q);
                            System.out.printf("Player: %s = %d\n", pHand.toString(), pHand.value());
                            System.out.printf("Banker: %s = %d\n", bHand.toString(), bHand.value());
                            dealt = true;                          
                        }
                        break;
                    case 6:
                        if (p.value() == 6 || p.value() == 7){
                            System.out.println("Dealing third card to banker ...");
                            BaccaratCard q = new BaccaratCard(shoe.deal().getRank(), shoe.deal().getSuit());
                            bHand.add(q);
                            System.out.printf("Player: %s = %d\n", pHand.toString(), pHand.value());
                            System.out.printf("Banker: %s = %d\n", bHand.toString(), bHand.value());
                            dealt = true;                            
                        }
                        break;
                    case 7:
                        break;          
                    default:
                        break;
                    }    
                }   
            

        } else if (bHand.value() < 6){
            if (dealt == false){
                System.out.println("Dealing third card to banker ...");
                BaccaratCard b = new BaccaratCard(shoe.deal().getRank(), shoe.deal().getSuit());
                bHand.add(b);
                System.out.printf("Player: %s = %d\n", pHand.toString(), pHand.value());
                System.out.printf("Banker: %s = %d\n", bHand.toString(), bHand.value());
            }
        }
        
        int pcomp = pHand.value() - 9;
        int bcomp = bHand.value() - 9;

        if(pcomp > bcomp){
            return 0;
        } else if(pcomp == bcomp){
            return 2;
        }
        else{
            return 1;
        }


    }

}
