package bresenham;

import java.awt.*;
import java.util.Iterator;
import java.util.Vector;

/**
 * This class gets a group of points and by using the bresenham method, draws a polygon
 * and then by using the scan line method fills it with a pattern provided
 * by <code>FillPattern</code> class. The pattern is the author's name.
 *
 * @author Vahid Mavaji
 * @version 1.0 2004
 */
public class Bresenham {

    private Vector vertices = new Vector();

    private Vector edges = new Vector();

    private boolean endOfPolygon = false;

    private int maxY = 0;

    private final static int pointRadius = 3;

    private final static Color pointColor = Color.black;

    private final static int lineWidth = 2;

    private final static Color lineColor = Color.black;

    /**
     * Default constructor.
     */
    public Bresenham() {
    }

    /**
     * Adds mouse click point to the current group of points.
     *
     * @param point The coordinates of mouse click
     */
    public void add(Point point) {
        vertices.add(point);
    }

    /**
     * The accessor method used to set the <code>endOfPolygon</code>
     *
     * @param maxY The maximum <code>y</code> of the screen used to convert
     *             the math coordinates to screen coordinates and vice versa.
     */
    public void endOfPolygon(int maxY) {
        this.endOfPolygon = true;
        this.maxY = maxY;
    }

    /**
     * The accessor methos used to derive the <code>endOfPolygon</code>
     *
     * @return The <code>endOfPolygon</code>
     */
    public boolean isEndOfPolygon() {
        return endOfPolygon;
    }

    /**
     * Draws line by using bresenham method between all pairs of vertices.
     *
     * @param g Passed from the UIL layer and used to draw anything
     */
    public void drawLineBresenham(Graphics g) {
        Point point1 = null;
        Point point2 = null;
        Iterator iterator = vertices.iterator();
        if (iterator.hasNext()) {
            point1 = (Point) iterator.next();
            g.setColor(pointColor);
            g.fillOval(point1.x - pointRadius, point1.y - pointRadius, 2 * pointRadius, 2 * pointRadius);
        }
        while (iterator.hasNext()) {
            point2 = (Point) iterator.next();
            g.setColor(lineColor);
            drawLineBresenham(g, point1, point2);
            g.setColor(pointColor);
            g.fillOval(point1.x - pointRadius, point1.y - pointRadius, 2 * pointRadius, 2 * pointRadius);
            g.fillOval(point2.x - pointRadius, point2.y - pointRadius, 2 * pointRadius, 2 * pointRadius);
            point1 = new Point(point2);
        }
        if (endOfPolygon && !vertices.isEmpty()) {
            point1 = (Point) vertices.firstElement();
            point2 = (Point) vertices.lastElement();
            g.setColor(lineColor);
            drawLineBresenham(g, point1, point2);
            g.setColor(pointColor);
            g.fillOval(point1.x - pointRadius, point1.y - pointRadius, 2 * pointRadius, 2 * pointRadius);
            g.fillOval(point2.x - pointRadius, point2.y - pointRadius, 2 * pointRadius, 2 * pointRadius);

            if (edges.isEmpty()) {
                generateEdges();
            }
            fillWithPattern(g);
        }
    }

    /**
     * Draws line by using bresenham method between a pair of vertices.
     *
     * @param g      Passed from the UIL layer and used to draw anything
     * @param point1 The first point
     * @param point2 The second point
     */
    private void drawLineBresenham(Graphics g, Point point1, Point point2) {
        Point p1 = (point1.x <= point2.x) ? (new Point(point1)) : (new Point(point2));
        Point p2 = (point1.x <= point2.x) ? (new Point(point2)) : (new Point(point1));

        double m = (double) (p2.y - p1.y) / (double) (p2.x - p1.x);
        if (Math.abs(m) < 1) {
            drawLineBresenham1(g, p1, p2, m);
        } else {
            drawLineBresenham2(g, p1, p2, m);
        }
    }

    /**
     * Draws line by using bresenham method between a pair of vertices
     * when the tangent of the line is less 1 (m < 1).
     *
     * @param g      Passed from the UIL layer and used to draw anything
     * @param point1 The first point
     * @param point2 The second point
     * @param m      The tangent
     */
    private void drawLineBresenham1(Graphics g, Point point1, Point point2, double m) {
        int x = point1.x;
        int y = point1.y;
        int dx = point2.x - point1.x;
        int dy = point2.y - point1.y;
        int pk = dx - 2 * dy;
        while (x <= point2.x) {
            g.fillOval(x - lineWidth, y - lineWidth, 2 * lineWidth, 2 * lineWidth);
            x++;
            y = (m > 0 && m < 1) ? ((pk < 0) ? y + 1 : y) : ((pk >= 0) ? y - 1 : y);
            pk = (m > 0 && m < 1) ? ((pk < 0) ? -2 * dy + 2 * dx + pk : pk - 2 * dy)
                    : ((pk >= 0) ? -2 * dy - 2 * dx + pk : pk - 2 * dy);
        }
    }

    /**
     * Draws line by using bresenham method between a pair of vertices
     * when the tangent of the line is greater 1 (m > 1).
     *
     * @param g      Passed from the UIL layer and used to draw anything
     * @param point1 The first point
     * @param point2 The second point
     * @param m      The tangent
     */
    private void drawLineBresenham2(Graphics g, Point point1, Point point2, double m) {
        Point p1 = (point1.y < point2.y) ? (new Point(point1)) : (new Point(point2));
        Point p2 = (point1.y < point2.y) ? (new Point(point2)) : (new Point(point1));

        int x = p1.x;
        int y = p1.y;
        int dx = p2.x - p1.x;
        int dy = p2.y - p1.y;
        int pk = dy - 2 * dx;
        while (y <= p2.y) {
            g.fillOval(x - lineWidth, y - lineWidth, 2 * lineWidth, 2 * lineWidth);
            y++;
            x = (m > 1) ? ((pk < 0) ? x + 1 : x) : ((pk >= 0) ? x - 1 : x);
            pk = (m > 1) ? ((pk < 0) ? -2 * dx + 2 * dy + pk : pk - 2 * dx)
                    : ((pk >= 0) ? -2 * dx - 2 * dy + pk : pk - 2 * dx);
        }
    }

    /**
     * Generates edges from the vertices.
     */
    private void generateEdges() {
        Iterator iterator = vertices.iterator();
        Point point1 = null;
        Point point2 = null;
        Point point3 = null;
        if (iterator.hasNext()) {
            point1 = (Point) iterator.next();
            if (iterator.hasNext()) {
                point2 = (Point) iterator.next();
            }
            if (!iterator.hasNext()) {
                edges.add(new Edge(point1, point2));
                return;
            }

            while (iterator.hasNext()) {
                point3 = (Point) iterator.next();
                if (point3.y > point2.y && point2.y > point1.y) {
                    edges.add(new Edge(point1, new Point(point2.x + 1, point2.y - 1)));
                } else if (point1.y > point2.y && point2.y > point3.y) {
                    edges.add(new Edge(point1, new Point(point2.x - 1, point2.y + 1)));
                } else {
                    edges.add(new Edge(point1, point2));
                }
                point1 = new Point(point2);
                point2 = new Point(point3);
            }

            point3 = (Point) vertices.firstElement();
            if (point3.y > point2.y && point2.y > point1.y) {
                edges.add(new Edge(point1, new Point(point2.x + 1, point2.y - 1)));
            } else if (point1.y > point2.y && point2.y > point3.y) {
                edges.add(new Edge(point1, new Point(point2.x - 1, point2.y + 1)));
            } else {
                edges.add(new Edge(point1, point2));
            }

            point1 = new Point(point2);
            point2 = new Point(point3);
            point3 = (Point) vertices.elementAt(1);
            if (point3.y > point2.y && point2.y > point1.y) {
                edges.add(new Edge(point1, new Point(point2.x + 1, point2.y - 1)));
            } else if (point1.y > point2.y && point2.y > point3.y) {
                edges.add(new Edge(point1, new Point(point2.x - 1, point2.y + 1)));
            } else {
                edges.add(new Edge(point1, point2));
            }
        }
    }

    /**
     * Fills the polygon with the fill pattern.
     *
     * @param g Passed from the UIL layer and used to draw anything
     */
    private void fillWithPattern(Graphics g) {
        int[][] fillPattern = FillPattern.fillPattern;
        Vector xes = new Vector();
        int scanLine = 0;
        while (scanLine <= maxY) {
            for (Iterator iterator = edges.iterator(); iterator.hasNext(); ) {
                Edge edge = (Edge) iterator.next();
                if (scanLine >= edge.yMin && scanLine <= edge.yMax) {
                    int x = edge.getCrossPoint(scanLine);
                    xes.add(new Integer(x));
                }
            }
            xes = sort(xes);
            for (Iterator iterator = xes.iterator(); iterator.hasNext(); ) {
                int x1 = ((Integer) iterator.next()).intValue();
                int x2 = ((Integer) iterator.next()).intValue();
                g.setColor(Color.red);
                for (int i = x1; i <= x2; i++) {
                    if (fillPattern[i % FillPattern.col][scanLine % FillPattern.col] != 0) {
                        g.fillOval(i, scanLine, 1, 1);
                    }
                }
            }
            scanLine++;
            xes.clear();
        }
    }

    /**
     * Sorts the elements of a <code>Vector</code> object
     *
     * @param vector The source vector to be sorted
     * @return The sorted vector
     */
    private Vector sort(Vector vector) {
        int[] a = new int[vector.size()];
        for (int i = 0; i < vector.size(); i++) {
            a[i] = ((Integer) vector.elementAt(i)).intValue();
        }

        for (int i = 0; i < a.length; i++) {
            for (int j = a.length - 1; j > i; j--) {
                if (a[j] < a[j - 1]) {
                    int temp = a[j];
                    a[j] = a[j - 1];
                    a[j - 1] = temp;
                }
            }
        }

        Vector v = new Vector();
        for (int i = 0; i < a.length; i++) {
            v.add(new Integer(a[i]));
        }
        return v;
    }
}