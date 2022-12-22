import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;

import javax.swing.JTextArea;
import javax.swing.border.LineBorder;


import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.UIManager;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

public class GUI extends JFrame {

    private JPanel contentPane;
    private JList todolist;
    private JButton btnList;
    private JButton btnPick;
    private JTextField txtdue;
    private JComboBox cbcat;
    private JComboBox cbimp;
    private JComboBox cbstatus;
    private JTextArea txttitle;
    private JButton btnAdd;
    private JButton btnupdate;
    private ArrayList<Todo> mytodolist = new ArrayList<>();
    DefaultListModel todomodel = new DefaultListModel();
    Todo todo;
    Connection con;
    PreparedStatement pst;
    ResultSet rs;
    private JButton btnclear;
    private JButton btndel;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Throwable e) {
            e.printStackTrace();
        }
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    GUI frame = new GUI();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public GUI()
    {
        start();

        initcomponents();
        createevents();

    }


    private void initcomponents()
    {
        setTitle("My Todo List");
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 804, 481);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(414, 52, 364, 344);
        contentPane.add(scrollPane);

        todolist = new JList(todomodel);
        scrollPane.setViewportView(todolist);

        btnList = new JButton("List Elements");

        btnList.setFont(new Font("Times New Roman", Font.BOLD, 14));
        btnList.setBounds(414, 407, 127, 23);
        contentPane.add(btnList);

        btnPick = new JButton("Select");

        btnPick.setFont(new Font("Times New Roman", Font.BOLD, 14));
        btnPick.setBounds(689, 407, 89, 23);
        contentPane.add(btnPick);

        JLabel lblNewLabel = new JLabel("Todo List");
        lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 16));
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setBounds(414, 11, 364, 53);
        contentPane.add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("Title          :");
        lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 14));
        lblNewLabel_1.setBounds(10, 31, 77, 14);
        contentPane.add(lblNewLabel_1);

        txttitle = new JTextArea();
        txttitle.setBorder(new LineBorder(Color.LIGHT_GRAY));
        txttitle.setBounds(97, 26, 307, 93);
        contentPane.add(txttitle);

        JLabel lblDueDate = new JLabel("Due Date    :");
        lblDueDate.setFont(new Font("Times New Roman", Font.BOLD, 14));
        lblDueDate.setBounds(10, 146, 77, 14);
        contentPane.add(lblDueDate);

        txtdue = new JTextField();
        txtdue.setBounds(97, 144, 307, 20);
        contentPane.add(txtdue);
        txtdue.setColumns(10);

        JLabel lblCategory = new JLabel("Category    :");
        lblCategory.setFont(new Font("Times New Roman", Font.BOLD, 14));
        lblCategory.setBounds(10, 185, 76, 14);
        contentPane.add(lblCategory);

        cbcat = new JComboBox();
        cbcat.setModel(new DefaultComboBoxModel(new Object[] {"",Category.RED,Category.WHITE,Category.BLUE,Category.PURPLE,Category.YELLOW,Category.GREEN}));

        cbcat.setBounds(97, 182, 307, 22);
        contentPane.add(cbcat);

        cbimp = new JComboBox();
        cbimp.setModel(new DefaultComboBoxModel(new Object[] {"",Importance.LOW,Importance.NORMAL,Importance.HIGH}));
        cbimp.setBounds(97, 223, 307, 22);
        contentPane.add(cbimp);

        JLabel lblImportance = new JLabel("Importance:");
        lblImportance.setFont(new Font("Times New Roman", Font.BOLD, 14));
        lblImportance.setBounds(10, 226, 76, 14);
        contentPane.add(lblImportance);

        cbstatus = new JComboBox();
        cbstatus.setEnabled(false);
        cbstatus.setModel(new DefaultComboBoxModel(new Object[] {Status.PENDING,Status.STARTED,Status.PARTIAL,Status.COMPLETED}));

        cbstatus.setBounds(97, 263, 307, 22);
        contentPane.add(cbstatus);

        JLabel lblStatus = new JLabel("Status        :");
        lblStatus.setFont(new Font("Times New Roman", Font.BOLD, 14));
        lblStatus.setBounds(10, 266, 76, 14);
        contentPane.add(lblStatus);

        btnAdd = new JButton("Add");

        btnAdd.setFont(new Font("Times New Roman", Font.BOLD, 14));
        btnAdd.setBounds(315, 313, 89, 23);
        contentPane.add(btnAdd);

        btnupdate = new JButton("Update");

        btnupdate.setEnabled(false);
        btnupdate.setFont(new Font("Times New Roman", Font.BOLD, 14));
        btnupdate.setBounds(10, 313, 89, 23);
        contentPane.add(btnupdate);

        btnclear = new JButton("Clear");

        btnclear.setFont(new Font("Times New Roman", Font.BOLD, 14));
        btnclear.setBounds(315, 352, 89, 23);
        contentPane.add(btnclear);

        btndel = new JButton("Delete");

        btndel.setFont(new Font("Times New Roman", Font.BOLD, 14));
        btndel.setEnabled(false);
        btndel.setBounds(10, 353, 89, 23);
        contentPane.add(btndel);

    }

    private void createevents()
    {

        btndel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                try {
                    Todo todo = (Todo)todolist.getSelectedValue();
                    Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
                    con = DriverManager.getConnection("jdbc:ucanaccess://Todo.accdb");

                    pst = con.prepareStatement("delete from Content where title = ?");
                    pst.setString(1, todo.getText());
                    pst.executeUpdate();
                    mytodolist.remove(todo);
                    todomodel.clear();
                    for(int i=0 ; i<mytodolist.size() ; i++)
                    {
                        todomodel.addElement(mytodolist.get(i));
                    }
                    btnclear.doClick();
                    JOptionPane.showMessageDialog(null, "Removed From List");

                } catch (ClassNotFoundException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }

            }
        });


        btnupdate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                try
                {
                    Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
                    con = DriverManager.getConnection("jdbc:ucanaccess://Todo.accdb");
                    String title = txttitle.getText();
                    LocalDateTime due = LocalDateTime.parse(txtdue.getText());
                    pst = con.prepareStatement("update content SET title=?,due=?,category=?,importance=?,status=? WHERE title=?");
                    pst.setString(1, title);
                    pst.setString(2, due+"");
                    pst.setString(3, cbcat.getSelectedItem()+"");
                    pst.setString(4, cbimp.getSelectedItem()+"");
                    pst.setString(5, cbstatus.getSelectedItem()+"");
                    Todo todo = (Todo)todolist.getSelectedValue();
                    pst.setString(6, todo.getText());
                    pst.executeUpdate();
                    mytodolist.set(todolist.getSelectedIndex(), new Todo(txttitle.getText(),LocalDateTime.parse(txtdue.getText()),(Category)cbcat.getSelectedItem(),(Importance)cbimp.getSelectedItem(),(Status)cbstatus.getSelectedItem()));

                    todomodel.clear();
                    for(int i=0 ; i<mytodolist.size() ; i++)
                    {
                        todomodel.addElement(mytodolist.get(i));
                    }
                    btnclear.doClick();
                    JOptionPane.showMessageDialog(null, "List Updated");

                } catch (ClassNotFoundException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }

            }
        });


        btnAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                if(txttitle.getText().equals("") || txtdue.getText().equals("") || cbcat.getSelectedIndex()==0 || cbimp.getSelectedIndex()==0)
                {
                    JOptionPane.showMessageDialog(null, "Please provide all the information above");
                }
                else
                {
                    String title = txttitle.getText();
                    LocalDateTime due = LocalDateTime.parse(txtdue.getText());
                    Category cat = (Category)cbcat.getSelectedItem();
                    Importance imp = (Importance)cbimp.getSelectedItem();
                    Status s = (Status)cbstatus.getSelectedItem();
                    Todo todo = new Todo(title,due,cat,imp,s);
                    mytodolist.add(todo);

                    try
                    {
                        Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
                        con = DriverManager.getConnection("jdbc:ucanaccess://Todo.accdb");

                        pst = con.prepareStatement("insert into content(title,due,category,importance,status) values (?,?,?,?,?)");
                        pst.setString(1, title);
                        pst.setString(2, due+"");
                        pst.setString(3, cat+"");
                        pst.setString(4, imp+"");
                        pst.setString(5, s+"");
                        pst.executeUpdate();
                    } catch (ClassNotFoundException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    } catch (SQLException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                    btnclear.doClick();
                    JOptionPane.showMessageDialog(null, "Added to the List!");
                    todomodel.clear();
                    for(int i=0 ; i<mytodolist.size() ; i++)
                    {
                        todomodel.addElement(mytodolist.get(i));
                    }





                }
            }
        });


        btnPick.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                if(todolist.getSelectedIndex() == -1)
                {
                    JOptionPane.showMessageDialog(null, "Click on an Emelent from the list First!");
                }
                else
                {
                    Todo todo = (Todo) todolist.getSelectedValue();
                    txttitle.setText(todo.getText());
                    int index = 0;
                    switch(todo.getCat())
                    {
                        case RED:
                            index= 1 ;
                            break;
                        case WHITE:
                            index= 2 ;
                            break;
                        case BLUE:
                            index= 3 ;
                            break;
                        case PURPLE:
                            index= 4 ;
                            break;
                        case YELLOW:
                            index= 5 ;
                            break;
                        case GREEN:
                            index= 6 ;
                            break;
                    }
                    cbcat.setSelectedIndex(index);
                    switch(todo.getImportance())
                    {
                        case LOW:
                            index= 1 ;
                            break;
                        case NORMAL:
                            index= 2 ;
                            break;
                        case HIGH:
                            index= 3 ;
                            break;
                    }
                    cbimp.setSelectedIndex(index);
                    switch(todo.getCompletion())
                    {
                        case PENDING:
                            index= 0 ;
                            break;
                        case STARTED:
                            index= 1 ;
                            break;
                        case PARTIAL:
                            index= 2 ;
                            break;
                        case COMPLETED:
                            index= 3 ;
                            break;
                    }
                    cbstatus.setSelectedIndex(index);
                    txtdue.setText(todo.getDue()+"");

                    btnAdd.setEnabled(false);
                    btnupdate.setEnabled(true);
                    btndel.setEnabled(true);
                    cbstatus.setEnabled(true);
                    todolist.setEnabled(false);
                }
            }
        });



        btnclear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                cbimp.setSelectedIndex(0);
                cbcat.setSelectedIndex(0);
                cbstatus.setSelectedIndex(0);
                txttitle.setText("");
                txtdue.setText("");
                btnAdd.setEnabled(true);
                btnupdate.setEnabled(false);
                btndel.setEnabled(false);
                cbstatus.setEnabled(false);
                todolist.setEnabled(true);
            }
        });


        btnList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                todomodel.clear();


                if(mytodolist.size()>0)
                {
                    for(int i=0 ; i<mytodolist.size() ; i++)
                    {
                        todomodel.addElement(mytodolist.get(i));
                    }

                }else
                {
                    JOptionPane.showMessageDialog(null, "No enlisted elements yet!");
                }


            }
        });

    }

    private void start()
    {
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            con = DriverManager.getConnection("jdbc:ucanaccess://Todo.accdb");

            pst = con.prepareStatement("select * from content");
            rs = pst.executeQuery();
            while(rs.next())
            {
                String title = rs.getString("title");
                LocalDateTime due =  LocalDateTime.parse(rs.getString("due"));
                String word =  rs.getString("category");
                Category cat = null;
                switch(word)
                {
                    case "RED":
                        cat= Category.RED;
                        break;
                    case "WHITE":
                        cat= Category.WHITE;
                        break;
                    case "BLUE":
                        cat= Category.BLUE;
                        break;
                    case "PURPLE":
                        cat= Category.PURPLE;
                        break;
                    case "YELLOW":
                        cat= Category.YELLOW;
                        break;
                    case "GREEN":
                        cat= Category.GREEN;
                        break;
                }
                String word1 =  rs.getString("importance");
                Importance imp = null;
                switch(word1)
                {
                    case "LOW":
                        imp= Importance.LOW;
                        break;
                    case "NORMAL":
                        imp= Importance.NORMAL;
                        break;
                    case "HIGH":
                        imp= Importance.HIGH;
                        break;
                }
                String word2 =  rs.getString("status");
                Status s = null;
                switch(word2)
                {
                    case "PENDING":
                        s= Status.PENDING;
                        break;
                    case "STARTED":
                        s= Status.STARTED;
                        break;
                    case "PARTIAL":
                        s= Status.PARTIAL;
                        break;
                    case "COMPLETED":
                        s= Status.COMPLETED;
                        break;
                }
                todo = new Todo(title,due,cat,imp,s);
                mytodolist.add(todo);
            }
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}



