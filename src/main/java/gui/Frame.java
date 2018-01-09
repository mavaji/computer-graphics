package gui;


import bresenham.Bresenham;
import spline.SPline;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;

/**
 * This class creates a frame to make transactions with user.
 *
 * @author Vahid Mavaji
 * @version 1.0 2004
 */
public class Frame extends JFrame {
    Bresenham bresenham = new Bresenham();
    SPline spline = null;

    boolean isBresenham = false;
    boolean isSpline = false;

    JPanel contentPane;
    JMenuBar jMenuBar1 = new JMenuBar();
    JMenu jMenu1 = new JMenu();
    JMenuItem jMenuItem2 = new JMenuItem();
    JMenu jMenu2 = new JMenu();
    JMenuItem jMenuItem3 = new JMenuItem();
    JMenu jMenu3 = new JMenu();
    JMenuItem jMenuItem1 = new JMenuItem();
    JMenu jMenu4 = new JMenu();
    JMenuItem jMenuItem4 = new JMenuItem();
    JMenuItem jMenuItem5 = new JMenuItem();
    JMenuItem jMenuItem6 = new JMenuItem();
    JMenuItem jMenuItem7 = new JMenuItem();
    JMenu jMenu5 = new JMenu();

    /**
     * Construct the frame
     */
    public Frame() {
        enableEvents(AWTEvent.WINDOW_EVENT_MASK);
        try {
            jbInit();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Component initialization
     */
    private void jbInit() throws Exception {

        contentPane = (JPanel) this.getContentPane();
        contentPane.setLayout(null);
        this.setContentPane(contentPane);
        this.setResizable(false);

        spline = new SPline(contentPane.getY() + contentPane.getHeight());

        this.setSize(new Dimension(701, 476));
        this.setTitle("Compter Graphics Project...");
        this.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                this_keyPressed(e);
            }
        });

        contentPane.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                contentPane_mousePressed(e);
            }
        });
        jMenu1.setText("File");
        jMenuItem2.setText("Exit");
        jMenuItem2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                jMenuItem2_mousePressed(e);
            }
        });
        jMenu2.setText("Help");
        contentPane.setToolTipText("");
        jMenuBar1.setToolTipText("");
        jMenuItem3.setText("About...");
        jMenuItem3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(MouseEvent e) {
                jMenuItem3_mouseReleased(e);
            }
        });
        jMenu3.setText("Bresenham");
        jMenuItem1.setText("Reset");
        jMenuItem1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(MouseEvent e) {
                jMenuItem1_mouseReleased(e);
            }
        });
        jMenu4.setText("SPline");
        jMenuItem4.setText("Reset");
        jMenuItem4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(MouseEvent e) {
                jMenuItem4_mouseReleased(e);
            }
        });
        jMenuItem5.setText("Draw");
        jMenuItem5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(MouseEvent e) {
                jMenuItem5_mouseReleased(e);
            }

            public void mousePressed(MouseEvent e) {
                jMenuItem5_mousePressed(e);
            }
        });
        jMenuItem6.setText("Draw");
        jMenuItem6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                jMenuItem6_mousePressed(e);
            }

            public void mouseReleased(MouseEvent e) {
                jMenuItem6_mouseReleased(e);
            }
        });
        jMenuItem7.setText("Help");
        jMenuItem7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(MouseEvent e) {
                jMenuItem7_mouseReleased(e);
            }
        });
        jMenu5.setBackground(Color.red);
        jMenu5.setForeground(Color.white);
        jMenu5.setText("Click on the Screen to draw ");
        jMenuBar1.add(jMenu1);
        jMenuBar1.add(jMenu2);
        jMenuBar1.add(jMenu5);
        jMenu1.add(jMenu3);
        jMenu1.add(jMenu4);
        jMenu1.addSeparator();
        jMenu1.add(jMenuItem2);
        jMenu2.add(jMenuItem7);
        jMenu2.add(jMenuItem3);
        jMenu3.add(jMenuItem5);
        jMenu3.add(jMenuItem1);
        jMenu4.add(jMenuItem6);
        jMenu4.add(jMenuItem4);
        this.setJMenuBar(jMenuBar1);


    }

    /**
     * Overridden so we can exit when window is closed
     */
    protected void processWindowEvent(WindowEvent e) {
        super.processWindowEvent(e);
        if (e.getID() == WindowEvent.WINDOW_CLOSING) {
            System.exit(0);
        }
    }

    /**
     * Manages the mouse press events.
     *
     * @param e The <code>MouseEvent</code>
     */
    void contentPane_mousePressed(MouseEvent e) {
        if (isBresenham) {
            if (!bresenham.isEndOfPolygon()) {
                bresenham.add(e.getPoint());
            }
        } else if (isSpline) {
            if (!spline.isEndOfSpline()) {
                spline.add(e.getPoint());
            }
        }
        repaint();
    }

    /**
     * This method paints the graphical thing when the <code>repaint</code> is called.
     *
     * @param g Used to draw anything
     */
    public void paint(Graphics g) {
        super.paint(g);
        if (isBresenham) {
            bresenham.drawLineBresenham(contentPane.getGraphics());
        } else if (isSpline) {
            spline.drawSpline(contentPane.getGraphics());
        }
    }

    /**
     * Manages the key press events.
     *
     * @param e The <code>KeyEvent</code>
     */
    void this_keyPressed(KeyEvent e) {
        if (isBresenham) {
            if (!bresenham.isEndOfPolygon() && (e.getKeyCode() == KeyEvent.VK_ESCAPE)) {
                bresenham.endOfPolygon(contentPane.getY() + contentPane.getHeight());
                repaint();
            }
        } else if (isSpline) {
            if (!spline.isEndOfSpline() && (e.getKeyCode() == KeyEvent.VK_ESCAPE)) {
                spline.endOfSpline(contentPane.getY() + contentPane.getHeight());
                repaint();
            }
        }
    }

    void jMenuItem2_mousePressed(MouseEvent e) {
        System.exit(0);
    }

    void jMenuItem1_mouseReleased(MouseEvent e) {
        bresenham = new Bresenham();
        repaint();
    }

    void jMenuItem3_mouseReleased(MouseEvent e) {
        JDialog about = new About();
        about.setBounds(this.getX() + this.getWidth() / 4, this.getY() + this.getHeight() / 3, 340, 260);
        about.show();

    }

    void jMenuItem5_mouseReleased(MouseEvent e) {
        String message = "When you want to finish drawing the polygon press Escape to enclose the polygon.";
        JOptionPane.showMessageDialog(this, message, "Note", JOptionPane.WARNING_MESSAGE);
    }

    void jMenuItem5_mousePressed(MouseEvent e) {
        isBresenham = (!isBresenham) ? true : isBresenham;
        isSpline = (isBresenham) ? false : isSpline;
        repaint();
    }

    void jMenuItem6_mousePressed(MouseEvent e) {
        isSpline = (!isSpline) ? true : isSpline;
        isBresenham = (isSpline) ? false : isBresenham;
        repaint();
    }

    void jMenuItem4_mouseReleased(MouseEvent e) {
        spline = new SPline(contentPane.getY() + contentPane.getHeight());
        repaint();
    }

    void jMenuItem6_mouseReleased(MouseEvent e) {
        String message = "At end of each portion of curve press Escape to draw that portion." +
                "\nThe points must be at least 3.";
        JOptionPane.showMessageDialog(this, message, "Note", JOptionPane.WARNING_MESSAGE);
    }

    void jMenuItem7_mouseReleased(MouseEvent e) {
        String message = new String("From the File menu, Select wich operation you want(Bresenham or SPline).");
        JOptionPane.showMessageDialog(this, message, "Help", JOptionPane.WARNING_MESSAGE);
    }
}
