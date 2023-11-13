package fantasy.item.generator.GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.swing.Timer;

import fantasy.item.generator.Data.DataHandling.DataHolder;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class LogonPanel extends JPanel{
    public static final String LOGON_WINDOW = "Logon Window";
    public static final String IMAGE_FILE_NAME = "robert-lukeman-_RBcxo9AU-U-unsplash.jpg";
    public static final String LOGIN = "Login";

    private BufferedImage backgroundImage;
    private float gradientX1 = 0.0f;
    private float gradientX2 = 0.20f;
    private float gradientY1 = 0.0f;
    private float gradientY2 = 0.20f;
    private float increment = 0.005f;
    private boolean reverseX = false;
    private boolean reverseY = false;
    private Timer animationTimer;

    private final Color backgroundRed = new Color(217, 21, 11, 40);
    private final Color backgroundGreen = new Color(9, 156, 39, 40);

    public LogonPanel(){
        super(new GridBagLayout());
        this.setName(LOGON_WINDOW);
        try{
            String imagePath = System.getProperty("user.dir") + "/resources/" + IMAGE_FILE_NAME;
            backgroundImage = ImageIO.read(new File(imagePath));
        } catch (IOException e) {
            System.out.println("Loading Background Image Failed." + e.getMessage());
            this.setBackground(Color.GRAY);
        }
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.weightx = 0.30;
        constraints.weighty = 0.30;
        this.add(createLoginComponent(), constraints);




        animationTimer = new Timer(20, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Update gradient positions
                if (reverseX) {
                    gradientX1 -= increment;
                    gradientX2 -= increment;
                } else {
                    gradientX1 += increment;
                    gradientX2 += increment;
                }

                if (reverseY) {
                    gradientY1 -= increment;
                    gradientY2 -= increment;
                } else {
                    gradientY1 += increment;
                    gradientY2 += increment;
                }

                // Check and reverse if exceeding bounds
                if (gradientX1 <= -0.2f || gradientX1 >= 1.2f) {
                    reverseX = !reverseX;
                }
                if (gradientY1 <= -0.2f || gradientY1 >= 1.2f) {
                    reverseY = !reverseY;
                }
                
                repaint();
            }
        });

        animationTimer.start();

    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);

        if(backgroundImage != null){
            double scaleX = (double) getWidth() / backgroundImage.getWidth();
            double scaleY = (double) getHeight() / backgroundImage.getHeight();

            double scale = Math.max(scaleX, scaleY);

            int width = (int) (backgroundImage.getWidth() * scale);
            int height = (int) (backgroundImage.getHeight() * scale);

            g.drawImage(backgroundImage, 0, 0, width, height, null);
        } else {
            this.setBackground(Color.DARK_GRAY);
        }

        GradientPaint gradientPaint = new GradientPaint(
            getWidth() * gradientX1, getHeight() * gradientY1, backgroundRed, 
            getWidth() * gradientX2, getHeight() * gradientY2, backgroundGreen);

        ((Graphics2D) g).setPaint(gradientPaint);
        g.fillRect(0, 0, getWidth(), getHeight());
    }


    private JPanel createLoginComponent(){
        JPanel login = new JPanel(new GridBagLayout());
        login.setName(LOGIN);
        login.setPreferredSize(new Dimension(300, 150));
        JLabel label = UIComponents.genLabel("Select a profile to create under:", "promptProfile");
        label.setFont(new Font("Comic Sans MS", Font.BOLD, 26));
        label.setForeground(Color.WHITE);

        JLabel profileLabel = UIComponents.genLabel("Profile:", "profileLabel");
        profileLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
        profileLabel.setForeground(Color.WHITE);
        
        JComboBox<String> profiles = UIComponents.genEditableComboBox(DataHolder.getInstance().getProfiles(), "ProfileDropDown");
        JButton entry = UIComponents.genTextButton("Enter", "Enter", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO: Figure out how to properly pass an event up here to load the DB and Switch to a main UI.
                // Ill probably need to Modify the UIManager to become a JFrame
            }
        });

        entry.setSize(new Dimension(300,40));
        
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.insets = new Insets(5, 5, 5, 5);
        login.add(label, constraints);

        constraints.gridy = 1;
        constraints.gridx = 0;
        constraints.gridwidth = 1;
        //constraints.anchor = GridBagConstraints.CENTER;
        login.add(profileLabel, constraints);

        constraints.gridx = 1;
        login.add(profiles, constraints);

        constraints.gridwidth = 2;
        constraints.gridy = 2;
        //constraints.anchor = GridBagConstraints.CENTER;
        login.add(entry, constraints);
        
        login.setOpaque(false);
        login.setVisible(true);
        return login;
    }



}
