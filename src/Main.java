import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;
class MyApp extends JFrame {
    class MyEventHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println(e.getActionCommand());
            //JButton b = (JButton) e.getSource();
            b1.setText("We are NOT impressed");
        }
    }

    private JButton b1;

    MyApp() {
        b1 = new JButton("ImPress Me");
        this.add(b1, BorderLayout.NORTH);
        JTextArea ta1 = new JTextArea("The hungry fox yada yada");
        this.add(ta1, BorderLayout.CENTER);

        ActionListener myAL = new MyEventHandler();

        b1.addActionListener(myAL);

        this.setSize(800, 200);
        this.setTitle("GUI 101");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}
public class Main {
    // swing lib 1.2
    public static void main(String[] args) {
        JFrame app = new MyApp(); // default layout BorderLayout 5 N,S,Center

    }

}
