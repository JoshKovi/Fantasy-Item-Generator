package fantasy.item.generator.GUI.ComponentButtons;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;

import fantasy.item.generator.GUI.UIMainWindow;



public class NavButton extends JButton {

    private boolean isPressed;

    private final Color deepBlack = new Color(5,5,5);
    private final Color lightBlackGray = new Color(32,32,32);
    private final Color clickedPadding = new Color(80,80,80);
    private final Dimension CONSTANT_SIZE = new Dimension(140, 32);
    
    public NavButton(String buttonText, String buttonName, String panelNameToSwitch){
        super(buttonText);
        this.setName((buttonName == null) ? buttonText : buttonName);
        this.setForeground(Color.WHITE);

        this.setToolTipText(panelNameToSwitch);
        setMaximumSize(CONSTANT_SIZE);
        setMinimumSize(CONSTANT_SIZE);
        setSize(CONSTANT_SIZE);
        setPreferredSize(CONSTANT_SIZE);

        setOpaque(false);
        setContentAreaFilled(false);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                isPressed = true;
                repaint();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                
                isPressed = false;
                repaint();
                if (contains(e.getPoint())) {
                    System.out.println("Button click completed! Navigating to: " + panelNameToSwitch);
                    UIMainWindow.getInstance().setViewWithName(panelNameToSwitch);
                }
            }
        });

    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        int width = getWidth();
        int height = getHeight();

        if (isPressed){
            g2d.setColor(clickedPadding);
        } else {
            g2d.setColor(lightBlackGray);
        }
        g2d.fillRect(0, 0, width, height);

        g2d.setColor(deepBlack);
        int padding = height / 4;
        g2d.fillRect(padding, padding, width - padding * 2, height - padding * 2);

        g2d.setColor(Color.WHITE);
        FontMetrics fm = g2d.getFontMetrics();
        g2d.drawString(this.getText(), (width - fm.stringWidth(this.getText())) /2.0f, (height + fm.getAscent() - fm.getDescent())  /2.0f); 
        g2d.dispose();
    }
}
