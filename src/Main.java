import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Random;


import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

//// enhanced enum
//enum Weekday // final static instances of type Weekday for each enum value
//{
//    Sunday("Rishon"), Monday("Sheni");
//
//    private String hebrewName;
//
//    Weekday(String hebrew) {
//        hebrewName = hebrew;
//    }
//}

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

/**
 * This code is cool
 */
public class Main {
    // swing lib 1.2
    public static void main(String[] args) {
        JFrame app = new DrawApp();
    }
}


 class DrawApp extends JFrame{
     private ArrayList<Line> lines = new ArrayList<>();
        DrawApp()
        {
            //setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            addWindowListener(new WindowAdapter() {
                @Override
                public void windowOpened(WindowEvent e) {
                    FileInputStream fis = null;
                    try {
                        fis = new FileInputStream("lines.bin");
                        ObjectInputStream ois = new ObjectInputStream(fis);
                        lines =  (ArrayList<Line>) ois.readObject();
                        ois.close();
                    } catch (FileNotFoundException ex) {
                        ex.printStackTrace();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    } catch (ClassNotFoundException ex) {
                        ex.printStackTrace();
                    }
                    repaint();

                }

                @Override
                public void windowClosing(WindowEvent e) {
                    try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("lines.bin"))){
                        oos.writeObject(lines);
                    } catch (FileNotFoundException ex) {
                        ex.printStackTrace();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }

                    System.exit(0);
                }
            });
            setSize(500,500);
            JButton clearButton = new JButton("Clear Lines");
            add(clearButton, BorderLayout.NORTH);




            add(new JLabel("Content"), BorderLayout.SOUTH);
            JPanel drawPanel = new DrawPanel();
            add( drawPanel, BorderLayout.CENTER);

            clearButton.addActionListener(
                    e -> { lines.clear();
                           drawPanel.repaint(); }
            );

            setVisible(true);
        }
        private static class Line implements Serializable
        {
            Line(Point p1, Point p2){x1=p1.x;y1=p1.y;x2=p2.x;y2=p2.y;}
            int x1,x2,y1,y2;
        }
        private class DrawPanel extends JPanel
        {

            DrawPanel()
            {
                this.setBackground( Color.WHITE);
               this.addMouseListener( // anonymous inner class
                 new MouseAdapter(){
                    private Point p;

                    @Override
                    public void mouseClicked(MouseEvent e) {
                        System.out.println( e.getPoint() );
                        if (p==null)
                        {   p = e.getPoint();
                            return;
                        }
                        lines.add(new Line(p, e.getPoint()));
                        p = e.getPoint();
                        DrawPanel.this.repaint();
                    }
                }
               );
            }

            @Override
            public void paint(Graphics g) {
                super.paint(g);

                for (Line l : lines)
                    g.drawLine(l.x1, l.y1, l.x2, l.y2);
            }
        }
 }



