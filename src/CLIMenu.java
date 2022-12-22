import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class CLIMenu
{
    ArrayList<Todo> todos;

    CLIMenu()
    {

    }

    CLIMenu(ArrayList<Todo> todos)
    {
        this.todos = todos;
        Scanner in = new Scanner(System.in);
        System.out.println("1. List Todos\n2. Add Todo\n3. Update Todos\n4. Delete Todo\n5. Quit");
        int type=in.nextInt();
        while(type<=5 && type>0)
        {
            while(type==1)
            {
                for(int i=1 ; i<=todos.size() ; i++)
                {
                    System.out.println(i+". "+todos.get(i-1).toString());
                }

                System.out.println("\n1. List Todos\n2. Add Todo\n3. Update Todos\n4. Delete Todo\n5. Quit");
                type=in.nextInt();
            }
            while(type==2)
            {
                System.out.println("Enter a title for the todo");
                in.skip("[\r\n]+");
                String text =in.nextLine();
                System.out.println("\nEnter a due date for the todo in the format YYYY-MM-DDTHH:MM");
                String date=in.next();
                LocalDateTime due = LocalDateTime.parse(date);
                System.out.println("\nSelect a category for the todo");
                System.out.println("Select an item between 1 and 6 and press enter");
                System.out.println("1. Red\n2. White\n3. Blue\n4. Purple\n5. Yellow\n6. Green");
                int pick = in.nextInt();
                Category cat=null;
                switch(pick)
                {
                    case 1:
                        cat= Category.RED;
                        break;
                    case 2:
                        cat= Category.WHITE;
                        break;
                    case 3:
                        cat= Category.BLUE;
                        break;
                    case 4:
                        cat= Category.PURPLE;
                        break;
                    case 5:
                        cat= Category.YELLOW;
                        break;
                    case 6:
                        cat= Category.GREEN;
                        break;
                    default:
                        System.out.println("Invalid input");
                        System.exit(1);

                }
                System.out.println("Select an importance for the todo");
                System.out.println("Select an item between 1 and 3 and press enter");
                System.out.println("1. Low\n2. Normal\n3. High\n");
                int pick1 = in.nextInt();
                Importance imp = null;
                switch(pick1)
                {
                    case 1:
                        imp= Importance.LOW;
                        break;
                    case 2:
                        imp= Importance.NORMAL;
                        break;
                    case 3:
                        imp= Importance.HIGH;
                        break;
                    default:
                        System.out.println("Invalid input");
                        System.exit(1);

                }
                System.out.println("Todo List Updated!");
                todos.add(new Todo(text,due,cat,imp,Status.PENDING));

                System.out.println("1. List Todos\n2. Add Todo\n3. Update Todos\n4. Delete Todo\n5. Quit\n");
                type=in.nextInt();
            }
            while(type==3)
            {
                System.out.println("Which todo do you want to update?");
                int choice  = in.nextInt();
                System.out.println("1. Title\n2. Due Date\n3. Category\n4. Importance\n5. Completion");
                int pick = in.nextInt();
                switch(pick)
                {
                    case 1:
                        System.out.println("Enter a title for the todo");
                        String text=in.next();
                        todos.get(choice-1).setText(text);
                        break;
                    case 2:
                        System.out.println("Enter a due date for the todo in the format YYYY-MM-DDTHH:MM");
                        String date=in.next();
                        todos.get(choice-1).setDue(LocalDateTime.parse(date));
                        break;
                    case 3:
                        System.out.println("Select a category for the todo");
                        System.out.println("Select an item between 1 and 6 and press enter");
                        System.out.println("1. Red\n2. White\n3. Blue\n4. Purple\n5. Yellow\n6. Green");
                        int pick1 = in.nextInt();
                        Category cat = null;
                        switch(pick1)
                        {
                            case 1:
                                cat= Category.RED;
                                break;
                            case 2:
                                cat= Category.WHITE;
                                break;
                            case 3:
                                cat= Category.BLUE;
                                break;
                            case 4:
                                cat= Category.PURPLE;
                                break;
                            case 5:
                                cat= Category.YELLOW;
                                break;
                            case 6:
                                cat= Category.GREEN;
                                break;
                            default:
                                System.out.println("Invalid input");
                                System.exit(1);

                        } todos.get(choice-1).setCat(cat);
                        break;
                    case 4:
                        System.out.println("Select an importance for the todo");
                        System.out.println("Select an item between 1 and 3 and press enter");
                        System.out.println("1. Low\n2. Normal\n3. High");
                        int pick2 = in.nextInt();
                        Importance imp = null;
                        switch(pick2)
                        {
                            case 1:
                                imp= Importance.LOW;
                                break;
                            case 2:
                                imp= Importance.NORMAL;
                                break;
                            case 3:
                                imp= Importance.HIGH;
                                break;
                            default:
                                System.out.println("Invalid input");
                                System.exit(1);

                        }todos.get(choice-1).setImportance(imp);
                        break;
                    case 5:
                        System.out.println("Select a status for the todo");
                        System.out.println("Select an item between 1 and 4 and press enter");
                        System.out.println("1. Pending\n2. Started\n3. Partial\n4. Completed");
                        int pick3 = in.nextInt();
                        Status s = null;
                        switch(pick3)
                        {
                            case 1:
                                s= Status.PENDING;
                                break;
                            case 2:
                                s= Status.STARTED;
                                break;
                            case 3:
                                s= Status.PARTIAL;
                                break;
                            case 4:
                                s= Status.COMPLETED;
                                break;
                            default:
                                System.out.println("Invalid input");
                                System.exit(1);

                        }todos.get(choice-1).setCompletion(s);
                        break;
                    default:
                        System.out.println("Invalid input");
                        System.exit(1);

                }
                System.out.println("Todo List was Updated!");

                System.out.println("1. List Todos\n2. Add Todo\n3. Update Todos\n4. Delete Todo\n5. Quit");
                type=in.nextInt();

            }

            while(type==4)
            {
                System.out.println("Pick the number of the entry you want to delete and press enter");
                for(int i=1 ; i<=todos.size() ; i++)
                {
                    System.out.println(i+". "+todos.get(i-1).toString());
                } int pick = in.nextInt();
                try
                {
                    todos.remove(pick-14);
                    System.out.println("Entry#"+pick+" was Delted!");
                }catch(IndexOutOfBoundsException e)
                {
                    System.out.println("Invalid input");
                    System.exit(1);
                }


                System.out.println("1. List Todos\n2. Add Todo\n3. Update Todos\n4. Delete Todo\n5. Quit");
                type=in.nextInt();
            }
            while(type==5)
            {
                System.out.println("x-----Program Ended-----x");
                System.exit(1);
            }
        }
        System.out.println("Invalid input");

    }

}
