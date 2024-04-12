import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class AddStudent implements ActionListener {
    private JTextField rollNumberField;
    private JTextField nameField;
    JButton addButton = new JButton("Add");

    String DB_URL = "jdbc:mysql://localhost:3306/mms";
    String DB_USER = "root";
    String DB_PASSWORD = "pklUHw:Im|$9~.T";
     JLabel success = new JLabel();
    public AddStudent() {
        JFrame frame = new JFrame();
        frame.setTitle("Marksheet Management System");
        frame.setSize(400, 240);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().setBackground(new Color(135, 206, 235));
       

        JLabel rollNumberLabel = new JLabel("Roll Number:");
        rollNumberField = new JTextField();

        JLabel nameLabel = new JLabel("Student Name:");
        nameField = new JTextField();

        Container inputPanel = frame.getContentPane();
        inputPanel.setLayout(null);


        success.setText("Student added in database!!");
        success.setBounds(20, 160, 220, 25);
        success.setVisible(false);

        rollNumberLabel.setBounds(20, 20, 200, 40);
        rollNumberField.setBounds(180, 20, 200, 40);
        inputPanel.add(rollNumberLabel);
        inputPanel.add(rollNumberField);

        nameLabel.setBounds(20, 70, 200, 40);
        nameField.setBounds(180, 70, 200, 40);
        inputPanel.add(nameLabel);
        inputPanel.add(nameField);

        addButton.addActionListener(this);
        addButton.setBounds(100, 120, 200, 40);
        ;
        inputPanel.add(addButton);
        inputPanel.add(success);
        frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addButton) {
            try {
                Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                Statement statement = connection.createStatement();
                String insertQuery = "INSERT INTO students (Roll_No, Name) "
                        +
                        "VALUES (?, ?)";

               
                 String rollNumber = rollNumberField.getText();
                 String name = nameField.getText();

                 ResultSet rs = statement.executeQuery("select * from result where roll_no='"+rollNumber+"'");
                
                 if(rs.next()){
                    success.setText("Stuent already exists!");
                    success.setVisible(true);
                 }
                 else{
                if(rollNumberField.getText().equals("")){
                    success.setText("Please enter data...");
                    success.setVisible(true);
                }
                PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
                preparedStatement.setInt(1, Integer.parseInt(rollNumber));
                preparedStatement.setString(2, name);

                preparedStatement.executeUpdate();
                
                preparedStatement.close();
                connection.close();

                System.out.println("Data stored in the database successfully!");
                success.setText("Student added in database!!");
                success.setVisible(true);
            }
            } catch (SQLException a) {
                a.printStackTrace();
            }
        }
    }

}
