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
    DrawLinesApp2()
    {
        super("Line Drawer 1.0");
        add(new DrawCanvas(), BorderLayout.CENTER);
        add(new JLabel(" "), BorderLayout.SOUTH); // status bar
        add(new JButton("New Game"), BorderLayout.NORTH);
        this.setSize(600,600);
        this.setDefaultCloseOperation( WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    class DrawCanvas extends JPanel
    {
        DrawCanvas()
        {
            setBackground( Color.WHITE);
            makeRandomLines();
//            setLayout(new GridLayout(3,3,5,5));
//            for (int i=0;i<9;i++)
//            {
//                add(new JButton());
//            }
        }

        Line lines[] = new Line[10];
        class Line{ int x1,y1,x2,y2;}
        int r(){ return (int)(Math.random() * 500);}
        public void makeRandomLines(){

            for (int i=0;i<10;i++)
            {
                lines[i] = new Line();
                lines[i].x1 = r();
                lines[i].y1 = r();
                lines[i].y2 = r();
                lines[i].x2 = r();
            }
        }
        @Override
        public void paint(Graphics g)
        {
            super.paint(g);

            for (int i=0;i<10;i++)
                g.drawLine(lines[i].x1,lines[i].y1,lines[i].x2,lines[i].y2);
        }
    }
}