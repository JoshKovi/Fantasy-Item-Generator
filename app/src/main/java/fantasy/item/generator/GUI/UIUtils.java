package fantasy.item.generator.GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Window;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JWindow;

public class UIUtils {

    private static final String DEFAULT_WINDOW_TITLE = "Fantasy Item Generator";
    private static final Dimension DEFAULT_WINDOW_DIMENSION = new Dimension(600, 400);

    private JFrame mainWindow;
    private JPanel childJPanel;
    private JPanel logonWindow;

    public UIUtils() {
        this(DEFAULT_WINDOW_TITLE);
    }

    public UIUtils(String windowTitle) {
        // Setup initial window
        mainWindow = new JFrame(windowTitle);
        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainWindow.setLayout(null);

        mainWindow.setMinimumSize(DEFAULT_WINDOW_DIMENSION);
        mainWindow.setVisible(true);
        this.childJPanel = generateLogonWindow();
        mainWindow.add(childJPanel);

        mainWindow.addComponentListener(resizeAdapter());
        // Finally pack and repaint
        mainWindow.pack();
        mainWindow.repaint();
    }

    public JPanel generateLogonWindow() {
        logonWindow = new JPanel();
        logonWindow.setSize(DEFAULT_WINDOW_DIMENSION);
        logonWindow.setBackground(Color.LIGHT_GRAY);
        logonWindow.setName("Logon Window");

        logonWindow.add(UIComponents.generateTextEntryPanel("user", "Username: ", "Default User", logonWindow));
        logonWindow.repaint();
        return logonWindow;
    }

    private ComponentAdapter resizeAdapter() {
        return new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                Dimension newSize = e.getComponent().getSize();

                childJPanel.setSize(newSize);
                // childJPanel.setLocation(0, 0);

                mainWindow.revalidate();
                mainWindow.repaint();
            }
        };
    }

    public JPanel getLogonWindow() {
        return logonWindow;
    }

}
