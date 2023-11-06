package fantasy.item.generator.GUI;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ComponentListener;

import javax.swing.Action;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class UIComponents {

    private UIComponents() {
    };

    public static JLabel genLabel(String label, String name) {
        JLabel jLabel = new JLabel(label);
        jLabel.setName(name);
        return jLabel;
    }

    public static JTextArea genTextArea(String defaultString, String name) {
        JTextArea textArea = new JTextArea(defaultString);
        textArea.setEditable(true);
        textArea.setName(name);
        return textArea;
    }

    public static JButton genTextButton(String label, String name, Action action) {
        JButton jButton = new JButton(label);
        jButton.setName(name);
        jButton.setAction(action);
        return jButton;
    }

    public static JPanel entryPanel(JLabel label, JTextArea textArea) {
        JPanel panel = new JPanel();
        panel.setName(label.getName() + " panel");
        panel.setLayout(new GridLayout(1, 2));
        panel.add(label);
        panel.add(textArea);
        return panel;
    }

    public static JPanel generateTextEntryPanel(String namePrefix, String labelText, String defaultText,
            JPanel logonWindow) {
        Dimension parentSize = logonWindow.getSize();
        Dimension compSize = new Dimension(parentSize.width / 3, 60);

        JLabel label = genLabel(labelText, namePrefix + "_label");
        label.setMinimumSize(compSize);
        label.setSize(compSize);

        JTextArea textArea = genTextArea(defaultText, namePrefix + "_text");
        textArea.setMinimumSize(compSize);
        textArea.setSize(compSize);

        JPanel panel = new JPanel();
        panel.setName(namePrefix);
        panel.add(label);
        panel.add(textArea);
        panel.setLayout(new GridLayout());
        panel.setVisible(true);
        return panel;
    }

}
