import java.util.Scanner;

public class Bane {
    public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
      
    System.out.println("___________________________________________________\n");
    System.out.println("Hello, it is me, Bane.");
    System.out.println("What do you want from me?\n");
    System.out.println("___________________________________________________\n");
    
    String input = sc.nextLine();
    while (!input.equals("bye")) {
      System.out.println("___________________________________________________\n");
      System.out.println("\t" + input);
      System.out.println("___________________________________________________\n");
      
      input = sc.nextLine();
    }
       
    System.out.println("___________________________________________________\n");
    System.out.println("Bye, hope to not see you again.\n");
    System.out.println("***************************************************\n");
    sc.close();
    }
}
