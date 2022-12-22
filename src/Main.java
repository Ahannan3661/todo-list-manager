import java.time.LocalDateTime;
import java.util.ArrayList;

public class Main
{

    public static void main(String[] args)
    {
        //{text='Take out the rubbish', due=2021-03-09T16:30, importance=NORMAL,
        //	completion=completed}
        LocalDateTime n  = LocalDateTime.of(2017,11,6,6,30,40);
        LocalDateTime n1  = LocalDateTime.of(2017,11,6,6,30,40);
        Todo t1 = new Todo("Take out the rubbish",n,Category.BLUE,Importance.NORMAL,Status.COMPLETED);
        Todo t2 = new Todo("sad life",n1,Category.RED,Importance.HIGH,Status.PENDING);
        ArrayList<Todo> todos = new ArrayList();
        todos.add(t1);
        todos.add(t2);

        CLIMenu menu = new CLIMenu(todos);

    }

}
