import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Lissajous extends JPanel {
    private JTextField a;
    private JTextField b;
    private JTextField delta;

    public Lissajous(int size) {
        setPreferredSize(new Dimension(size, size));//getting the preferred size of the JPanel to be a square with dimensions size x size.

        a = new JTextField("6     ");
        b = new JTextField("5     ");
        delta = new JTextField("0.5     ");


        ActionListener textFieldsListener = new LissajousActionListener();

        a.addActionListener(textFieldsListener);//passing obj od ActionListener class
        b.addActionListener(textFieldsListener);
        delta.addActionListener(textFieldsListener);

        add(a);//add JTextField components to the current instance of the JPanel,so they can be displayed within the JPanel when the program runs
        add(b);
        add(delta);
    }
    private class LissajousActionListener implements ActionListener {
        @Override public void actionPerformed(ActionEvent e) {
            repaint();
        }
    }
    @Override protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Get current values of a, b, and delta
        double a = Double.parseDouble(this.a.getText().trim());//get text and convert it to int(parseInt)
        double b = Double.parseDouble(this.b.getText().trim());//trim the extra space
        double delta = Double.parseDouble(this.delta.getText().trim());

        int size = getSize().width;
        //getwidth() gets the width of the JPanel at runtime,the method is provided by the Component class,superclass of JPanel
        //this method actually makes the drawing responsive to changes in the size of the window
        //we r relying on the given size in the constructor which would be square shape with this method

        double tMax = (a / gcd((int) a, (int) b)) * b;
        double xPrev = CoordinateX(0, a, delta, size);
        double yPrev = CoordinateY(0, b, size);

        for (double t = 0.01; t <= tMax; t += 0.01) {//let's put the suitably small increment to 0.1 as double
            double x = CoordinateX(t, a, delta, size);
            double y = CoordinateY(t, b, size);

            g.drawLine((int) xPrev, (int) yPrev, (int) x, (int) y);//drawing a line so all two points coordinates r set

            xPrev = x;
            yPrev = y;
        }
    }

    private double CoordinateX(double t, double a, double delta, int size) {
        return size / 2.0 + (2.0 * size / 5.0) * Math.sin(a * t * Math.PI + delta);
    }

    private double CoordinateY(double t, double b, int size) {
        return size / 2.0 + (2.0 * size / 5.0) * Math.sin(b * t * Math.PI);
    }

    private int gcd(int a, int b)
    {//exactly like what we code in py
        return b == 0 ? a : gcd(b, a % b);
    }


}