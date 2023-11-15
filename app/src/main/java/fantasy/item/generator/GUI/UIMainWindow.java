package fantasy.item.generator.GUI;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UIMainWindow extends JFrame{

    private static final String DEFAULT_WINDOW_TITLE = "Fantasy Item Generator";
    private static final Dimension DEFAULT_MAIN_WINDOW_DIMENSION = new Dimension(600, 400);
    private static final Dimension DEFAULT_CHILD_WINDOW_DIMENSION = new Dimension(600, 400);

    private static UIMainWindow manager;
    private JPanel mainPanel;
    private static List<JFrame> childWindows = new ArrayList<>();
    private static Map<String, JPanel> panelsAvailable = new HashMap<>();

    public static UIMainWindow getInstance(){
        if(manager == null){
            manager = new UIMainWindow();
        }
        return manager;
    }

    private UIMainWindow(){
        super(DEFAULT_WINDOW_TITLE);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setMinimumSize(DEFAULT_MAIN_WINDOW_DIMENSION);
        this.getContentPane().setBackground(Color.LIGHT_GRAY);
        this.setLayout(new BorderLayout());
        this.setVisible(true);

    }

    public JFrame openNewChildWindow(String frameTitle){
        JFrame childWindow = new JFrame(frameTitle);
        childWindow.setName(frameTitle);
        childWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        childWindow.setMinimumSize(DEFAULT_CHILD_WINDOW_DIMENSION);
        childWindow.setVisible(true);
        childWindows.add(childWindow);
        return childWindow;
    }

    public JPanel addJPanelAndSetView(JPanel panel){
        panelsAvailable.putIfAbsent(panel.getName(), panel);
        if (this.mainPanel != null) {
            this.mainPanel.setVisible(false);
        }
        this.getContentPane().add(panel);
        this.mainPanel = panel;
        this.mainPanel.setVisible(true);
        this.mainPanel.revalidate();
        this.mainPanel.repaint();
        this.pack();
        return panel;
    }

    public JPanel getViewByName(String panelName){
        return panelsAvailable.get(panelName);
    }

    public JPanel setViewWithName(String panelName){
        if(!panelsAvailable.containsKey(panelName)){
            System.out.println(panelName + " Does not exist.");
            return this.mainPanel;
        }
        return addJPanelAndSetView(panelsAvailable.get(panelName));
    }

    public JPanel addJPanelToList(JPanel panel){
        panelsAvailable.putIfAbsent(panel.getName(), panel);
        return panel;
    }

    public JPanel removeJPanelFromList(String name){
        if(panelsAvailable.size() >= 2 && panelsAvailable.containsKey(name)){
            panelsAvailable.remove(name);
            return addJPanelAndSetView(panelsAvailable.values().iterator().next());
        }
        return panelsAvailable.get(name);
    }

 





    
}
