package bresenham;

import java.awt.*;

/**
 * This class provides some functionality to simulte an edge that will be used
 * in <code>Bresenham</code> class.
 *
 * @author Vahid Mavaji
 * @version 1.0 2004
 */
public class Edge {

    private Point point1 = null;

    private Point point2 = null;

    protected int yMin = 0;

    protected int yMax = 0;

    /**
     * The overrided constructor
     *
     * @param point1 The first point of the edge
     * @param point2 The second point of the edge
     */
    public Edge(Point point1, Point point2) {
        this.point1 = (point1.x < point2.x) ? point1 : point2;
        this.point2 = (point1.x < point2.x) ? point2 : point1;
        yMin = (point1.y < point2.y) ? point1.y : point2.y;
        yMax = (point1.y > point2.y) ? point1.y : point2.y;
    }

    /**
     * With a <code>y</code> given, this methods returns the cross point of scanline and the current edge.
     *
     * @param y The <code>y</code> position of the scanline
     * @return The cross point
     */
    public int getCrossPoint(int y) {
        double m = (double) (point2.y - point1.y) / (double) (point2.x - point1.x);
        int x = (int) ((y - point1.y) / m) + point1.x;
        return x;
    }
}