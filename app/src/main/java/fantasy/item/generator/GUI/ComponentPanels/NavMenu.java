package fantasy.item.generator.GUI.ComponentPanels;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.Timer;

import fantasy.item.generator.GUI.LogonPanel;
import fantasy.item.generator.GUI.MainView;
import fantasy.item.generator.GUI.UIMainWindow;
import fantasy.item.generator.GUI.ComponentButtons.NavButton;

public class NavMenu extends JPanel{
    public static final String NAV_MENU_NAME = "Nav Menu";
    private Timer animationTimer;

    public NavMenu(){
        super();
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setName(NAV_MENU_NAME);
        this.add(new NavButton(MainView.MAIN_VIEW_NAME, null, MainView.MAIN_VIEW_NAME));
        this.add(new NavButton(NAV_MENU_NAME, null, NAV_MENU_NAME));
        this.add(new NavButton(LogonPanel.LOGON_WINDOW, null, LogonPanel.LOGON_WINDOW));
        this.setMaximumSize(new Dimension(120, Integer.MAX_VALUE));
        this.setOpaque(false);
        this.setVisible(true);
        animationTimer = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isVisible()) {
                    
                    repaint();
                }
            }
        });

        animationTimer.start();
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        setLocation(0, 0);
    }

}
