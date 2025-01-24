import java.util.Scanner;
import java.util.ArrayList;

public class Bane {
    
  public static ArrayList<Task> al = new ArrayList<>(); 
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
      
    System.out.println("___________________________________________________\n");
    System.out.println("Hello, it is me, Bane.");
    System.out.println("What do you want from me?\n");
    System.out.println("___________________________________________________\n");
    
    String input = sc.nextLine();
    do {  
      response(input);
      input = sc.nextLine();

    } while(!input.equals("bye"));
      
    response(input);
       
    sc.close();
  }
    
    
  public static void response(String dialogue) {

    System.out.println("___________________________________________________\n");

    if (dialogue.equals("bye")) {
      System.out.println("Bye, hope to not see you again.\n");
      
    } else if (dialogue.equals("list")) {

      if (al.isEmpty()) {
        System.out.println("what were you expecting? A present?");
      } else {
        for (int i = 1; i <= al.size(); i++) {
          System.out.println("    " + i + "." + al.get(i - 1));
        }
        
      }
    } else if (dialogue.matches("mark \\d")) {
        String[] arr = dialogue.split(" ");
        int num = Integer.valueOf(arr[1]);
        al.get(num - 1).statusToggle();
        System.out.println("Finally getting work done eh?"); 
        System.out.println("    " + num + "." + al.get(num - 1));

    } else if (dialogue.matches("unmark \\d")) {
        String[] arr = dialogue.split(" ");
        int num = Integer.valueOf(arr[1]);
        al.get(num - 1).statusToggle();
        System.out.println("As expected, didn't do your work and tried to cheat");
        System.out.println("    " + num + "." + al.get(num - 1));

    } else {
      Task t = new Task(dialogue); 
      al.add(t);
      System.out.println("added to the trashpile: " + dialogue);
       
    }
    System.out.println("___________________________________________________\n");
    
  }
}
