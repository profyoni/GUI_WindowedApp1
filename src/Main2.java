import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


class MyApp2 extends JFrame{

    boolean wasModified = true;
    class MyWindowListener extends WindowAdapter
    {
        @Override
        public void windowClosing(WindowEvent e) {
            if (! wasModified)
            {
                System.exit(0);
            }
            else{
                int response =
                JOptionPane.showConfirmDialog(null,
                        "Save your work from the entire semester, Yossi?",
                        "Save Your Work",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.WARNING_MESSAGE);
                if (response != JOptionPane.YES_OPTION)
                {
                    System.exit(0);
                }
            }
        }
    }

    class MyEventHandler implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println(e.getActionCommand()); // returns the text on event source
            //JButton b2 = (JButton) e.getSource(); // returns the event source that fired the event
            b.setText("I am impressed");
        }
    }
    private JButton b;
    MyApp2()
    {
        this.setSize(500,800);
        //this.setDefaultCloseOperation( WindowConstants.EXIT_ON_CLOSE );

        MyWindowListener wl = new MyWindowListener();
        this.addWindowListener(wl);

        this.setLayout( new GridLayout(6, 8));
        for (int i=0;i<48;i++)
        {
            JButton b = new JButton(Math.random() > 0.5 ? "X" : "O");
            b.setFont( new Font("Courier", Font.BOLD, 50));
            b.setForeground( new Color(0,0, 128));
            add(b);
        }
        add(new JLabel(":-0"), 25);
        // JFrame default layout BorderLayout :N, S, Center
        b = new JButton("Press Me"); // event source
        MyEventHandler eh = new MyEventHandler(); // event handler
        // register event Handler for event source
        b.addActionListener(eh);
        //this.add( b );

        this.setVisible(true);
    }
}

public class Main2 {
    public static void main(String[] args) {
        JFrame app = new DrawLinesApp2(); // Swing library
    }
}

class DrawLinesApp2 extends JFrame
{
    DrawLinesApp2()
    {
        super("Line Drawer 1.0");
        add(new DrawCanvas(), BorderLayout.CENTER);
        add(new JLabel(), BorderLayout.SOUTH);
        this.setSize(600,600);
        this.setDefaultCloseOperation( WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    class DrawCanvas extends JPanel
    {
        DrawCanvas()
        {
            setBackground( Color.BLACK);
        }
    }
}