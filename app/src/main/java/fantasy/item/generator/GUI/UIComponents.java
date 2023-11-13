package fantasy.item.generator.GUI;

import java.awt.event.ActionListener;
import java.util.Collection;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
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

    public static JButton genTextButton(String label, String name, ActionListener ActionListener) {
        JButton jButton = new JButton(label);
        jButton.setName(name);
        jButton.addActionListener(ActionListener);
        return jButton;
    }

    public static JComboBox<String> genEditableComboBox(Collection<String> comboValues, String name){
        JComboBox<String> dropDown = new JComboBox<>(comboValues.toArray(new String[0]));
        dropDown.setName(name);
        dropDown.setEditable(true);
        return dropDown;
    }




}
