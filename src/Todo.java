import java.time.LocalDateTime;

public class Todo
{
    private String text;
    private LocalDateTime due;
    private Category cat;
    private Importance importance;
    private Status completion;

    Todo()
    {

    }

    Todo(String text,LocalDateTime due, Category cat,Importance importance, Status completion)
    {
        this.text = text;
        this.due = due;
        this.cat = cat;
        this.importance = importance;
        this.completion = completion;
    }

    public void setText(String t)
    {
        text = t;
    }
    public String getText()
    {
        return text;
    }
    public void setDue(LocalDateTime d)
    {
        due = d;
    }
    public LocalDateTime getDue()
    {
        return due;
    }
    public void setCat(Category c)
    {
        cat = c;
    }
    public Category getCat()
    {
        return cat;
    }
    public void setImportance(Importance t)
    {
        importance = t;
    }
    public Importance getImportance()
    {
        return importance;
    }
    public void setCompletion(Status t)
    {
        completion = t;
    }
    public Status getCompletion()
    {
        return completion;
    }

    public String toString()
    {
        return cat.getColour() + "Todo{text= "+text+" ,due= " +due+" ,importance= " +importance+
                " ,completion= " +completion+
                "}\033[0m";
    }

}
