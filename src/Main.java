import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.util.Random;

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

    boolean modifiedBit = true;
    class MyWL extends WindowAdapter
    {
        @Override
        public void windowClosing(WindowEvent e) {

            if (! modifiedBit)
                System.exit(0);
            else{
                int ret = JOptionPane.showConfirmDialog(null, "Save Work?",
                        "Save", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
                if (ret == JOptionPane.NO_OPTION)
                    System.exit(0);
            }
        }

    }
    private JButton b1;

    MyApp() {
//        this.setLayout(new GridLayout(3,3));
//        for (int i=0;i<9;i++)
//        {
//            JLabel laibel = new JLabel(Math.random() > 0.5? "O" : "X");
//            laibel.setFont( new Font("Courier", Font.BOLD, 85));
//            laibel.setForeground( new Color(0,0,128));
//            add(laibel);
//        }
//        b1 = new JButton("ImPress Me");
//        this.add(b1);
//        JTextArea ta1 = new JTextArea(10, 40);
//        ta1.setText("yad regel");
//        this.add(ta1);
//        add(new JLabel(";ofhsdfhkdjsafwefkjsdkjdsfgksghkjdsgh"));
//        add(new JTextField(20));

        //ActionListener myAL = new MyEventHandler();

        //b1.addActionListener(myAL);

        this.addWindowListener( new MyWL() );

        this.setSize(800, 800);
        this.setTitle("GUI 101");
 //       this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}
public class Main {
    // swing lib 1.2
    public static void main(String[] args) {
        JFrame app = new DrawApp();
    }
}
 class DrawApp extends JFrame{
        DrawApp()
        {
            setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            setSize(500,500);
            add(new JTextField(), BorderLayout.NORTH);
            add(new JLabel("Content"), BorderLayout.SOUTH);
            JPanel drawPanel = new DrawPanel();
            add( drawPanel, BorderLayout.CENTER);

            setVisible(true);
        }
        private class Line
        {
            int x1,x2,y1,y2;
        }
        private class DrawPanel extends JPanel
        {
            private Line line[] = new Line[10];
            DrawPanel()
            {
                this.setBackground( Color.WHITE);

                for (int i=0;i<10;i++)
                {
                    line[i] = new Line();
                    line[i].x1 = r();
                    line[i].x2 = r();
                    line[i].y1 = r();
                    line[i].y2 = r();
                }
//                this.setLayout(new GridLayout(3,3,5,5));
//                for (int i=0;i<9;i++)
//                    add(new JButton());
            }

            @Override
            public void paint(Graphics g)
            {
                super.paint(g);

                for (int i=0;i<10;i++)
                   g.drawLine(line[i].x1, line[i].y1,
                           line[i].x2, line[i].y2 );
            }

            Random rand = new Random();
            private int r() {
                return rand.nextInt(500);
            }
        }
 }

