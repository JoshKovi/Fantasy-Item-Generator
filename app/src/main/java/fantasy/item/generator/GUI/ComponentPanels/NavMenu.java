package fantasy.item.generator.GUI.ComponentPanels;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JPanel;

import fantasy.item.generator.GUI.LogonPanel;
import fantasy.item.generator.GUI.MainView;
import fantasy.item.generator.GUI.ComponentButtons.NavButton;

public class NavMenu extends JPanel{
    public static final String NAV_MENU_NAME = "Nav Menu";

    private final Color deepBlack = new Color(5,5,5);
    private final Color lightBlackGray = new Color(32,32,32);
    private final Color clickedPadding = new Color(48,48,48);

    public NavMenu(){
        super(new GridLayout(0, 1));
        this.setName(NAV_MENU_NAME);
        this.add(new NavButton(MainView.MAIN_VIEW_NAME, null, MainView.MAIN_VIEW_NAME));
        this.add(new NavButton(NAV_MENU_NAME, null, NAV_MENU_NAME));
        this.add(new NavButton(LogonPanel.LOGON_WINDOW, null, LogonPanel.LOGON_WINDOW));
        this.setOpaque(false);
        this.setVisible(true);
    }

}
