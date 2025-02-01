package core;

import task.Task;

public class Ui {

    public static void separatorLine() {
	    System.out.println("________________________________________________________________\n");
    }

	public static void greeting() {
  
		String logo ="\n\t▄▄▄▄    ▄▄▄       ███▄    █ ▓█████  \n"
					+  "\t▓█████▄ ▒████▄     ██ ▀█   █ ▓█   ▀ \n"
					+  "\t▒██▒ ▄██▒██  ▀█▄  ▓██  ▀█ ██▒▒███   \n"
					+  "\t▒██░█▀  ░██▄▄▄▄██ ▓██▒  ▐▌██▒▒▓█  ▄ \n"
					+  "\t░▓█  ▀█▓ ▓█   ▓██▒▒██░   ▓██░░▒████▒\n"
					+  "\t░▒▓███▀▒ ▒▒   ▓▒█░░ ▒░   ▒ ▒ ░░ ▒░ ░\n"
					+  "\t▒░▒   ░   ▒   ▒▒ ░░ ░░   ░ ▒░ ░ ░  ░\n"
					+  "\t░    ░   ░   ▒      ░   ░ ░    ░    \n"
					+  "\t░            ░  ░         ░    ░  ░ \n"
					+  "\t      ░                             \n";

		System.out.println(logo); 
        separatorLine();
		System.out.println("""
				Hello, it is me, Bane.
				Why have you called upon me?\n""");
        separatorLine();
	}

    public static void farewell() {
        separatorLine();
		System.out.println("Bye, hope to not see you again.");
        separatorLine();
    }

    public static void listReply(String type) {
        switch (type) {
        case "empty":
            System.out.println("What were you expecting? A present? It's empty!");
            break;

        case "success":
            System.out.println("Reminding you of things you have already forgetten:\n");
            break;

        }
    }

    public static void markReply(String type) {

        switch (type) {
        case "marked":
            System.out.println("Finally getting work done eh?\n");
            break;

        case "unmarked":
            System.out.println("As expected, you didn't do it and tried to cheat.\n");
            break;

        case "index_out_of_bounds":
            System.out.println("Mark Non-Existent Entry.\n");
            System.out.println("You're trying to unmark/mark something that doesn't exist!");
            break;

        case "empty_command":
            System.out.println("Empty Command.\n");
            System.out.println("""
                    Nobody understood that instruction.
                    Format: mark/unmark [task index]""");
            break;
        }
    }

    public static void taskReply(String type) {
        switch (type) {
        case "fail":
            System.out.println("Wow, you're bad at this. Try again.");
            break;

        case "empty command":
            System.out.println("Empty Command.\n");
            System.out.println("""
					You have to input something else other than the command itself,
					just in case you have forgotten. 
					Format: [command] [task] <duration if applicable> """);
            break;
        }
    }

    public static void taskReply(String type, Task task, int alSize) {
        switch (type) {
        case "success":
            StringBuilder sb = new StringBuilder("Added to list of things to \"forget\",\n\n");
            sb.append("  " + task);
            sb.append(String.format("\n\nwhich makes the total: %d.", alSize));
            System.out.println(sb.toString());
            break;

        }
    }

    public static void deleteReply(String type) {
        switch (type) {
        case "success":
			System.out.println("Giving up are we? You disappoint me.");
            break;

        case "empty_command":
            System.out.println("""
						I do not understand how it is so hard to be correct.
						Format: delete [integer]""");
            break;

        case "delete_out_of_bounds":
            System.out.println("You are trying to delete something that isn't even there.");
            break;
        }
    }

    public static void unknownInputReply() {
        System.out.println("Unknown Input.\n");
		System.out.println("I fail to comprehend the inner machinations of the \nthing you call a brain. Try again");

    }

    public static void loadReply(String type) {
        switch (type) {
        case "success":
            System.out.println("""
                    Added new file "./data/Bane.txt" because I 
                    clearly have to do everything for you.""");
            break;

        case "file_creation_fail":
            System.out.println("""
                    It seems that you're on your own.
                    Create "./data/Bane.txt" and wake me up when done.""");
            break;

        case "read_file_error":
            System.out.println("""
					Gahhhh! Something went wrong with the file!
					Fix it and get back to me dirtbag!""");
            break;
        }
    }

    public static void saveReply(String type) {
        switch (type) {
        case "write_error":
            System.out.println("""
                    Looks like there was an error whilst saving
                    Heh! I'll leave you to handle it :P"""); 
                break;

        case "file_open_error":
            System.out.println("""
					Looks like your files aren't working like they used to.""");
            break;
            
        }
    }
    
}
