package fantasy.item.generator.GUI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.Timer;

import fantasy.item.generator.GUI.ComponentPanels.NavMenu;

public class MainView extends JPanel{
    public static final String MAIN_VIEW_NAME = "Main View";

    public MainView(){
        super(new GridBagLayout());
        this.setName(MAIN_VIEW_NAME);
        this.setBackground(Color.BLACK);
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.NORTHWEST;
        // constraints.weightx = 0.33;
        // constraints.weighty = 1.0;
        JPanel navPanel = UIMainWindow.getInstance().addJPanelToList(new NavMenu());
        this.add(navPanel, constraints); 

    }

    @Override
    public void setVisible(boolean visible){
        super.setVisible(visible);
        UIMainWindow.getInstance().getViewByName(NavMenu.NAV_MENU_NAME).setVisible(visible);
        if(visible){
            repaint();
        } 
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        setSize(UIMainWindow.getInstance().getSize());
        // UIMainWindow.getInstance().getViewByName(NavMenu.NAV_MENU_NAME).setSize(getWidth()/3, getHeight());
        UIMainWindow.getInstance().getViewByName(NavMenu.NAV_MENU_NAME).setVisible(true);
        
    }


}
