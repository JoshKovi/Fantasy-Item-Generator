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
    private Timer animationTimer;
    private boolean wasNotVisible = false;
    public MainView(){
        super(new GridBagLayout());
        this.setName(MAIN_VIEW_NAME);
        this.setBackground(Color.BLACK);
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.WEST;
        JPanel navPanel = UIMainWindow.getInstance().addJPanelToList(new NavMenu());
        this.add(navPanel); 

        animationTimer = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isVisible() && wasNotVisible){
                    UIMainWindow.getInstance().getViewByName(MAIN_VIEW_NAME).revalidate();
                    UIMainWindow.getInstance().getViewByName(MAIN_VIEW_NAME).repaint();
                }  else if(isVisible()){
                    UIMainWindow.getInstance().getViewByName(MAIN_VIEW_NAME).repaint();
                }
            
            }
        });
        animationTimer.start();
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        
    }


}
