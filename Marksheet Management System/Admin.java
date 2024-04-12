import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.String;
import java.awt.Color;

public class Admin extends JFrame implements ActionListener {
    JFrame frame = new JFrame();
    
    JTextField usernameField = new JTextField();
    JPasswordField passwordField = new JPasswordField();
    JButton login = new JButton();
    JLabel user = new JLabel();
    JLabel passwordtext = new JLabel();
    JLabel success = new JLabel();

    Admin() {
        user.setBounds(10, 20, 80, 25);
        user.setText("Username");
        usernameField.setBounds(100, 20, 165, 25);

        passwordtext.setBounds(10, 50, 80, 25);
        passwordtext.setText("Password");
        passwordField.setBounds(100, 50, 165, 25);

        login.setBounds(10, 80, 80, 25);
        login.setText("Login");
        login.addActionListener(this);

        success.setText("Wrong ID or Password!!!");
        success.setBounds(10, 115, 120, 25);
        success.setVisible(false);

        frame.setSize(350, 200);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().setBackground(new Color(128,128,128));
        frame.add(usernameField);
        frame.add(passwordField);
        frame.add(login);
        frame.add(user);
        frame.add(passwordtext);
        frame.add(success);
        frame.setVisible(true);
    }


    public void actionPerformed(ActionEvent e) {

        String username = usernameField.getText();
        String password = passwordField.getText();
        System.out.println("username: " + username + "  " + "password: " + password);

        if(username.equals("user") && password.equals("1234")){
            Options opt = new Options();
        }
        else{
            success.setVisible(true);
        }
    }
}