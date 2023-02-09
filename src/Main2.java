import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;


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
class T3_Model
{
    public enum PlayerValue {X, O, NONE}
    /**
     * @throws RuntimeException if location is not vacant
     * @param row
     * @param column
     */
//    private PlayerValue board[][] = new PlayerValue[3][3];
//    public void makeMove(int row, int column){}
//    public PlayerValue getCurrentPlayer(){}
//    public PlayerValue getWinner(){}
//    public boolean isGameOver() {}
//
}
class DrawLinesApp2 extends JFrame
{

    private ArrayList<Line> lines = new ArrayList<>();


    DrawLinesApp2()
    {
        super();
        setTitle("Line Drawer 1.0");
        add(new DrawCanvas(), BorderLayout.CENTER);
        add(new JLabel(" "), BorderLayout.SOUTH); // status bar
        JButton newGameButton = new JButton("New Game");
        newGameButton.addActionListener(
                e -> {  lines.clear();
                        repaint();    }
                );
        newGameButton.setForeground(Color.BLUE);
        add(newGameButton, BorderLayout.NORTH);
        this.setSize(600,600);

        addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
                // try with resources = gurantees close resources
                try (ObjectInputStream ois = new ObjectInputStream(  new FileInputStream("lines2.bin") )) {
                    lines = (ArrayList<Line>) ois.readObject( );
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                } catch (IOException ex) {
                    ex.printStackTrace();
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
                finally {

                }
                repaint();
            }

            @Override
            public void windowClosing(WindowEvent e) {
                FileOutputStream fos = null;
                try {
                    fos = new FileOutputStream("lines2.bin");
                    ObjectOutputStream oos = new ObjectOutputStream( fos );
                    oos.writeObject( lines );
                    oos.close();
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                System.exit(0);
            }

            @Override
            public void windowClosed(WindowEvent e) {

            }

            @Override
            public void windowIconified(WindowEvent e) {

            }

            @Override
            public void windowDeiconified(WindowEvent e) {

            }

            @Override
            public void windowActivated(WindowEvent e) {

            }

            @Override
            public void windowDeactivated(WindowEvent e) {

            }
        });


        this.setVisible(true);
    }

    class DrawCanvas extends JPanel
    {
        DrawCanvas()
        {
            setBackground( Color.WHITE);

            addMouseListener( new LineDrawerListener());
            //makeRandomLines();
//            setLayout(new GridLayout(3,3,5,5));
//            for (int i=0;i<9;i++)
//            {
//                add(new JButton());
//            }
        }

        class LineDrawerListener extends MouseAdapter{
            Point p;
            @Override
            public void mouseClicked(MouseEvent e) {
                if (p == null)
                {
                    p = e.getPoint();
                    return;
                }
                lines.add( new Line(p, e.getPoint()));
                p = e.getPoint();
                repaint();
            }


        }

//        int r(){ return (int)(Math.random() * 500);}
//        public void makeRandomLines(){
//
//            for (int i=0;i<10;i++)
//            {
//                lines[i] = new Line();
//                lines[i].x1 = r();
//                lines[i].y1 = r();
//                lines[i].y2 = r();
//                lines[i].x2 = r();
//            }
//        }
        @Override
        public void paint(Graphics g)
        {
            super.paint(g);

            for (Line l :lines)
                g.drawLine(l.x1,l.y1,l.x2,l.y2);
        }
    }
}
// supports Object Serizliation = convert to a sequence of bytes
class Line implements Serializable { // tagging
    int x1,y1,x2,y2;
    Line(Point p1, Point p2)
    {
        x1 = p1.x; y1 = p1.y;
        x2 = p2.x; y2 = p2.y;
    }
}