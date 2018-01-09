package spline;

import java.awt.*;
import java.util.Iterator;
import java.util.Vector;

/**
 * This class gets some sets(groups) of points & for each set of points draws the corresponding Spline curve.
 *
 * @author Vahid Mavaji
 * @version 1.0 2004
 */
public class SPline {

    private Vector groupsOfPoint = new Vector();

    private Vector points = new Vector();

    private double precision = 0.00001;

    private boolean endOfSpline = false;

    private int maxY = 0;

    private int pointRadius = 3;

    /**
     * Default constructor.
     */
    public SPline() {
    }

    /**
     * The overrided constructor
     *
     * @param maxY The maximum <code>y</code> of the screen used to convert
     *             the math coordinates to screen coordinates and vice versa.
     */
    public SPline(int maxY) {
        this.maxY = maxY;
    }

    /**
     * Adds mouse click point to the current group of points.
     *
     * @param point The coordinates of mouse click
     */
    public void add(Point point) {
        points.add(point);
    }

    /**
     * The accessor method used to set the <code>endOfSpline</code>
     *
     * @param maxY The maximum <code>y</code> of the screen used to convert
     *             the math coordinates to screen coordinates and vice versa.
     */
    public void endOfSpline(int maxY) {
        endOfSpline = true;
        this.maxY = maxY;
    }

    /**
     * The accessor methos used to derive the <code>endOfSpline</code>
     *
     * @return The <code>endOfSpline</code>
     */
    public boolean isEndOfSpline() {
        return endOfSpline;
    }

    /**
     * This methos draws the Spline curves for each group of points.
     *
     * @param g Passed from the UIL layer and used to draw anything
     */
    public void drawSpline(Graphics g) {
        final int size = points.size();

        for (int i = 0; i < size; i++) {
            g.fillOval(((Point) points.elementAt(i)).x - pointRadius, ((Point) points.elementAt(i)).y - pointRadius, 2 * pointRadius, 2 * pointRadius);
        }

        if (endOfSpline) {
            if (!points.isEmpty() && points.size() >= 3) {
                groupsOfPoint.add(points);
                points = new Vector();
            }
            endOfSpline = false;
        }

        Iterator iterator = groupsOfPoint.iterator();
        while (iterator.hasNext()) {

            Vector currentPoints = (Vector) iterator.next();
            final int currentSize = currentPoints.size();

            int[] x = new int[currentSize];
            int[] y = new int[currentSize];
            for (int i = 0; i < currentSize; i++) {
                x[i] = ((Point) currentPoints.elementAt(i)).x;
                y[i] = maxY - ((Point) currentPoints.elementAt(i)).y;
            }

            int[] h = new int[currentSize - 1];
            double[] d = new double[currentSize - 1];
            for (int i = 0; i < currentSize - 1; i++) {
                h[i] = (x[i + 1] - x[i]);
                d[i] = ((double) (y[i + 1] - y[i]) / (double) h[i]);
            }

            double[] u = new double[currentSize - 1];
            for (int i = 1; i < currentSize - 1; i++) {
                u[i] = (6 * (d[i] - d[i - 1]));
            }

            double[] m = new double[currentSize];
            m[0] = 0;
            m[currentSize - 1] = 0;
            for (int i = 1; i < currentSize - 1; i++) {
                m[i] = (d[i] - d[i - 1]);
            }

            double[] temp = new double[currentSize];
            copy(temp, m);

            while (true) {
                temp[1] = (u[1] - h[1] * m[2]) / (2 * (h[0] + h[1]));

                for (int i = 2; i < currentSize - 2; i++) {
                    temp[i] = (u[i] - h[i] * m[i + 1] - h[i - 1] * temp[i - 1]) / (2 * (h[i - 1] + h[i]));
                }

                temp[currentSize - 2] = (u[currentSize - 2] - h[currentSize - 3] * m[currentSize - 3]) / (2 * (h[currentSize - 3] + h[currentSize - 2]));

                if (!isEqual(m, temp)) {
                    copy(m, temp);
                } else {
                    break;
                }
            }

            for (int i = 0; i < currentSize - 1; i++) {
                double s1 = d[i] - (h[i] * (2 * m[i] + m[i + 1]) / 6);
                double s2 = m[i] / 2;
                double s3 = (m[i + 1] - m[i]) / (6 * h[i]);

                for (int k = Math.min(x[i], x[i + 1]); k <= Math.max(x[i], x[i + 1]); k++) {
                    int l = (int) (y[i] + (k - x[i]) * (s1 + (k - x[i]) * (s2 + (k - x[i]) * s3)));
                    g.fillOval(k, maxY - l, 1, 1);
                }
            }
        }
    }

    /**
     * Copies one array of <code>double</code> variables to another.
     *
     * @param a2 The destination array
     * @param a1 The source array
     */
    private void copy(double[] a2, double[] a1) {
        for (int i = 0; i < a1.length; i++) {
            a2[i] = a1[i];
        }
    }

    /**
     * Compare two arrays of <code>double</code> and if the difference is less than some precision return true.
     *
     * @param a1 The first array
     * @param a2 The second array
     * @return <code>true</code> if two arrays are equal by some precision
     */
    private boolean isEqual(double[] a1, double[] a2) {
        boolean result = true;
        for (int i = 0; i < a1.length; i++) {
            if (Math.abs(a1[i] - a2[i]) > precision) {
                result = false;
                break;
            }
        }

        return result;
    }
}