import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Options extends JFrame implements ActionListener {
    JButton addStudentButton = new JButton("ADD STUDENT");
        JButton updateResultButton = new JButton("UPDATE RESULT");
    public Options() {
        setTitle("Options");
        setSize(400, 200);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null); // Center the frame on the screen
        
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.setBackground(new Color(255, 165, 0));

        

        addStudentButton.addActionListener(this);

        updateResultButton.addActionListener(this);

        panel.add(addStudentButton);
        panel.add(updateResultButton);

        add(panel);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource()== addStudentButton){
        
        AddStudent ans = new AddStudent();
        }
        else if(e.getSource()== updateResultButton){
            MarksheetManagementSystem mms = new MarksheetManagementSystem();
        }
    }
}
