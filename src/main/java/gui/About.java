package gui;

import javax.swing.*;
import java.awt.*;

/**
 * This class creates the about dialog.
 *
 * @author Vahid Mavaji
 * @version 1.0 2004
 */
public class About extends JDialog {
    JPanel panel1 = new JPanel();
    JLabel jLabel1 = new JLabel();
    JLabel jLabel2 = new JLabel();
    JLabel jLabel3 = new JLabel();
    JLabel jLabel4 = new JLabel();
    JLabel jLabel5 = new JLabel();
    JLabel jLabel6 = new JLabel();
    JLabel jLabel7 = new JLabel();

    /**
     * The constructor.
     *
     * @param frame The <code>Frame</code> from which the dialog is displayed
     * @param title The <code>String</code> to display in the dialog's title bar
     * @param modal true for a modal dialog, false for one that allows other windows to be active at the same time
     */
    public About(Frame frame, String title, boolean modal) {
        super(frame, title, modal);
        try {
            jbInit();
            pack();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * @see #About
     */
    public About() {
        this(null, "", false);
    }

    /**
     * Initialize this class.
     *
     * @throws Exception
     */
    void jbInit() throws Exception {

        panel1.setLayout(null);
        jLabel1.setFont(new java.awt.Font("Dialog", 1, 12));
        jLabel1.setText("Computer Graphics Project");
        jLabel1.setBounds(new Rectangle(78, 14, 157, 27));
        jLabel2.setFont(new java.awt.Font("Dialog", 1, 12));
        jLabel2.setText("Prof : Dr. Gholizadeh");
        jLabel2.setBounds(new Rectangle(99, 43, 120, 23));
        jLabel3.setText("Student : Vahid Mavaji");
        jLabel3.setBounds(new Rectangle(94, 70, 126, 17));
        jLabel4.setText("78103507");
        jLabel4.setBounds(new Rectangle(126, 88, 67, 25));
        jLabel5.setFont(new java.awt.Font("Dialog", 2, 12));
        jLabel5.setText("Summer 2004");
        jLabel5.setBounds(new Rectangle(121, 142, 86, 19));
        jLabel6.setFont(new java.awt.Font("Dialog", 2, 12));
        jLabel6.setText("Computer Department");
        jLabel6.setBounds(new Rectangle(100, 160, 129, 28));
        jLabel7.setFont(new java.awt.Font("Dialog", 2, 12));
        jLabel7.setText("Iran University of Science & Technology");
        jLabel7.setBounds(new Rectangle(55, 185, 230, 28));
        this.setResizable(false);
        this.getContentPane().setBackground(Color.lightGray);
        this.setModal(true);
        this.setTitle("About...");
        getContentPane().add(panel1);
        panel1.add(jLabel7, null);
        panel1.add(jLabel1, null);
        panel1.add(jLabel5, null);
        panel1.add(jLabel6, null);
        panel1.add(jLabel2, null);
        panel1.add(jLabel3, null);
        panel1.add(jLabel4, null);
    }
}
