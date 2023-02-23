import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

public class Pong extends JFrame {
    public static void main(String[] args) {
        new Pong();
    }

    private JLabel statusBar;
    public Pong()
    {
        setSize(600,600);
        setDefaultCloseOperation( WindowConstants.EXIT_ON_CLOSE) ;
        add(new GamePanel(), BorderLayout.CENTER);
        //add(new JLabel("TODO: score bar"), BorderLayout.NORTH);
        JButton button = new JButton("TODO: score bar");
        button.addActionListener(e->{createThreadToExecuteSlowRunningTask(); });
        add( button, BorderLayout.NORTH);

        statusBar = new JLabel("TODO: status Bar");
        add(statusBar, BorderLayout.SOUTH);
        setVisible(true);
    }

    private void createThreadToExecuteSlowRunningTask(){
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                slowRunningTask();

                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        statusBar.setText("Done");
                    }
                });

            }
        });
        t.start();
    }

    private void slowRunningTask() {
        try {
            for (int i =0;i<1_000_000;i++)
                System.out.print(i + " ");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private class GamePanel extends JPanel{
        private Point ball = new Point(100,200),
                      paddle = new Point(500,300),
                      delta = new Point(+1, -1); // change every iteration
        final int BALL_SIZE = 40,
                  PADDLE_WIDTH =10, PADDLE_HEIGHT=100;

        private Timer ballTimer = new javax.swing.Timer(10, // not dependably on time
                e -> {
                    Graphics g = GamePanel.this.getGraphics();
                    if (g==null)
                        return; // kludge
                    g.setColor( Color.BLACK);
                    g.fillOval(ball.x,ball.y,BALL_SIZE, BALL_SIZE);

                    // update location of ball
                    ball.translate(delta.x, delta.y);

                    if (ball.y <= 10 )
                    {
                        delta.y = -delta.y;
                    }
                    if (ball.x >= 500 )
                    {
                        delta.x = -delta.x;
                    }
                    //repaint();


                    g.setColor( Color.WHITE );
                    g.fillOval(ball.x,ball.y,BALL_SIZE, BALL_SIZE);
                });
        GamePanel()
        {
            setBackground(Color.BLACK);
           // ballTimer.start();

            this.addMouseWheelListener(new MouseWheelListener() {
                @Override
                public void mouseWheelMoved(MouseWheelEvent e) {
                    paddle.y += (int)(e.getPreciseWheelRotation() * 10);
                    repaint();
                }
            });
        }

        @Override
        public void paint(Graphics g)
        {
            super.paint(g);

            g.setColor( Color.WHITE );
     //       g.fillOval(ball.x,ball.y,BALL_SIZE, BALL_SIZE);

            g.fillRect(paddle.x, paddle.y, PADDLE_WIDTH, PADDLE_HEIGHT);
        }
    }
}
