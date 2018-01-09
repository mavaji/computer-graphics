package bresenham;

/**
 * This class provide a fill pattern that will be used is <code>Bresenham</code> class
 * to fill a polygon.
 *
 * @author Vahid Mavaji
 * @version 1.0 2004
 */
public class FillPattern {

    protected static final int row = 20;

    protected static final int col = 20;

    protected static int[][] fillPattern = new int[row][col];

    /**
     * This matrix produces the fill pattern.
     * if you look closer, its my name in persian i.e. "Vahid Mavaji" !
     */
    private static char[] pattern = {
            '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#',
            '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', ' ', '#', '#', '#', ' ', ' ', ' ', '#',
            '#', '#', '#', '#', ' ', '#', '#', '#', '#', '#', '#', ' ', '#', ' ', '#', '#', ' ', '#', ' ', '#',
            '#', '#', '#', '#', '#', ' ', '#', '#', '#', ' ', '#', '#', '#', '#', ' ', '#', ' ', ' ', ' ', '#',
            '#', '#', '#', '#', '#', '#', ' ', '#', '#', ' ', '#', '#', '#', '#', ' ', '#', '#', '#', ' ', '#',
            '#', '#', '#', '#', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '#', '#', '#', ' ', '#',
            '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', ' ', ' ', '#', '#',
            '#', '#', '#', '#', '#', '#', '#', ' ', '#', ' ', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#',
            '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#',
            '#', '#', '#', '#', '#', '#', '#', '#', '#', ' ', '#', ' ', ' ', ' ', '#', '#', ' ', ' ', ' ', '#',
            '#', '#', '#', '#', '#', ' ', '#', '#', '#', ' ', '#', ' ', '#', ' ', '#', '#', ' ', '#', ' ', '#',
            '#', '#', '#', '#', ' ', '#', ' ', '#', '#', ' ', '#', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '#',
            '#', '#', '#', '#', '#', '#', '#', ' ', '#', ' ', '#', '#', '#', ' ', '#', '#', '#', '#', '#', '#',
            '#', ' ', '#', '#', ' ', ' ', ' ', ' ', '#', ' ', '#', '#', '#', ' ', '#', '#', '#', '#', '#', '#',
            '#', ' ', '#', ' ', '#', '#', '#', '#', '#', ' ', '#', ' ', ' ', '#', '#', '#', '#', '#', '#', '#',
            '#', ' ', '#', '#', ' ', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#',
            '#', ' ', '#', '#', ' ', '#', ' ', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#',
            '#', ' ', ' ', ' ', ' ', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#',
            '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#',
            '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#'

    };

    /**
     * Default constructor
     */
    public FillPattern() {
    }

    /**
     * This code is written in static because it is executed only one time to save in time.
     */
    static {
        int k, l;
        for (int i = 0; i < pattern.length; i++) {
            k = i / col;
            l = i - k * row;
            if (pattern[i] == '#') {
                fillPattern[l][k] = 0;
            } else {
                fillPattern[l][k] = 1;
            }
        }
    }
}