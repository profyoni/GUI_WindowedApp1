import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

public class Pong2 extends JFrame {
    public static void main(String[] args) {
        new Pong2();
    }

    private JButton button;
    public Pong2()
    {
        setSize(700,700);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        add(new GamePanel(), BorderLayout.CENTER);
        button = new JButton("TODO: Score");
        add(button, BorderLayout.NORTH);
        button.addActionListener( e -> {
            startThreadThatCallsVerySlowMethod();
        });



        add(new JLabel("TODO: status bar"), BorderLayout.SOUTH);
        setVisible(true);
    }

    private void startThreadThatCallsVerySlowMethod() {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                verySlowMethod();
                // tell the EDT to setText on button
                SwingUtilities.invokeLater( () -> button.setText("Done") );
            }
        });
        t.start(); // not t.run

    }

    private void verySlowMethod() {
        for (int i=0;i<1_000_000;i++)
            System.out.print(i + " ");
    }

    private static class GamePanel extends JPanel{
        private Point ball = new Point(100,200);
        private Point delta = new Point(+2,-2);
        private Point paddle = new Point(20,300);
        GamePanel(){
            this.setBackground( Color.BLACK );
            Timer ballTimer = new Timer(20,
                    e -> {
                // update location
                        Graphics g = getGraphics();
                        if (g==null)
                            return;
                        // draw in BLACK in old location
                        g.setColor( getBackground() );
                        g.fillOval(ball.x, ball.y, 40,40);

                        ball.translate(delta.x,  delta.y);

                        // bounds checking
                        if (ball.y < 10 || ball.y > 500) // top or bottom border
                        {
                            delta.y = -delta.y;
                        }
                        if (ball.x < 10 || ball.x > 700) // top or bottom border
                        {
                            delta.x = -delta.x;
                        }

                        g.setColor(Color.WHITE);
                        g.fillOval(ball.x, ball.y, 40,40);

                        //repaint();
                    });
            ballTimer.start();

            addMouseWheelListener( e ->{
                paddle.y += -e.getPreciseWheelRotation() * 5;
                //repaint();
                Graphics g = getGraphics();
                if (g != null)
                {
                    g.setColor(Color.WHITE);
                    g.fillRect(paddle.x,paddle.y, 10,80);
                }
            });
        }

        @Override
        public void paint(Graphics g){
            super.paint(g);

            g.setColor(Color.WHITE);
            g.fillOval(ball.x, ball.y, 40,40);

            g.fillRect(paddle.x,paddle.y, 10,80);
        }
    }
}
