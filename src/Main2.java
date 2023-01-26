import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



class MyApp2 extends JFrame{
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
        this.setDefaultCloseOperation( WindowConstants.EXIT_ON_CLOSE );

        // JFrame default layout BorderLayout :N, S, Center
        b = new JButton("Press Me"); // event source
        MyEventHandler eh = new MyEventHandler(); // event handler
        // register event Handler for event source
        b.addActionListener(eh);
        this.add( b , BorderLayout.NORTH);
        this.add( new JTextArea("yada yada"), BorderLayout.CENTER);
        this.setVisible(true);
    }
}

public class Main2 {
    public static void main(String[] args) {
        JFrame app = new MyApp2(); // Swing library
    }
}
