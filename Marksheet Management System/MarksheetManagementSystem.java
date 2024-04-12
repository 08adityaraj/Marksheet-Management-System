import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class MarksheetManagementSystem extends JFrame implements ActionListener {
    private JTextField rollNumberField;
    private JTextField nameField;
    private JTextField englishField;
    private JTextField computerField;
    private JTextField mathsField;
    private JTextField physicsField;
    private JTextField chemistryField;

    String DB_URL = "jdbc:mysql://localhost:3306/mms";
    String DB_USER = "root";
    String DB_PASSWORD = "pklUHw:Im|$9~.T";
    JLabel success = new JLabel();

    public MarksheetManagementSystem() {
        JFrame frame = new JFrame();
        frame.setTitle("Marksheet Management System");
        frame.setSize(600, 550);
        frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        frame.getContentPane().setBackground(new Color(255, 255, 0));

        JLabel rollNumberLabel = new JLabel("Roll Number:");
        rollNumberField = new JTextField();

        JLabel nameLabel = new JLabel("Student Name:");
        nameField = new JTextField();

        JLabel englishLabel = new JLabel("English:");
        englishField = new JTextField();

        JLabel computerLabel = new JLabel("Computer:");
        computerField = new JTextField();

        JLabel mathsLabel = new JLabel("Maths:");
        mathsField = new JTextField();

        JLabel physicsLabel = new JLabel("Physics:");
        physicsField = new JTextField();

        JLabel chemistryLabel = new JLabel("Chemistry:");
        chemistryField = new JTextField();

        JButton addButton = new JButton("Add");
        addButton.addActionListener(this);

        success.setText("result added in database!!");
        success.setBounds(20, 460, 220, 25);
        success.setVisible(false);

        Container inputPanel = frame.getContentPane();
        inputPanel.setLayout(null);

        rollNumberLabel.setBounds(20, 20, 200, 40);
        rollNumberField.setBounds(180, 20, 200, 40);
        inputPanel.add(rollNumberLabel);
        inputPanel.add(rollNumberField);

        nameLabel.setBounds(20, 70, 200, 40);
        nameField.setBounds(180, 70, 200, 40);
        inputPanel.add(nameLabel);
        inputPanel.add(nameField);

        englishLabel.setBounds(20, 120, 200, 40);
        englishField.setBounds(180, 120, 200, 40);
        inputPanel.add(englishLabel);
        inputPanel.add(englishField);

        computerLabel.setBounds(20, 170, 200, 40);
        computerField.setBounds(180, 170, 200, 40);
        inputPanel.add(computerLabel);
        inputPanel.add(computerField);

        mathsLabel.setBounds(20, 220, 200, 40);
        mathsField.setBounds(180, 220, 200, 40);
        inputPanel.add(mathsLabel);
        inputPanel.add(mathsField);

        physicsLabel.setBounds(20, 270, 200, 40);
        physicsField.setBounds(180, 270, 200, 40);
        inputPanel.add(physicsLabel);
        inputPanel.add(physicsField);

        chemistryLabel.setBounds(20, 320, 200, 40);
        chemistryField.setBounds(180, 320, 200, 40);
        inputPanel.add(chemistryLabel);
        inputPanel.add(chemistryField);

        addButton.setBounds(300, 400, 200, 40);
        ;
        inputPanel.add(addButton);
        inputPanel.add(success);

        frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {

        if (e.getActionCommand().equals("Add")) {
            try {
                Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                Statement statement = connection.createStatement();
                Statement statement1 = connection.createStatement();

                String insertQuery = "INSERT INTO result (roll_no, name, engMarks, compMarks, mathMarks , phyMarks, chemMarks, percentage, grade) "
                        +
                        "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

                String rollNumber = rollNumberField.getText();
                String name = nameField.getText();
                String english = englishField.getText();
                String computer = computerField.getText();
                String maths = mathsField.getText();
                String physics = physicsField.getText();
                String chemistry = chemistryField.getText();

                float p = (Integer.parseInt(english) + Integer.parseInt(computer) + Integer.parseInt(maths)
                        + Integer.parseInt(physics) + Integer.parseInt(chemistry)) / 5;
                String grade;
                if (p >= 80) {
                    grade = "Honours";
                } else if (p >= 60) {
                    grade = "1st Div";
                } else if (p >= 50) {
                    grade = "2nd Div";
                } else if (p >= 40) {
                    grade = "3rd Div";
                } else {
                    grade = "Fail";
                }

                ResultSet rs = statement.executeQuery("select * from students where roll_no='" + rollNumber + "'");

                if (rs.next()) {
                    PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
                    preparedStatement.setInt(1, Integer.parseInt(rollNumber));
                    preparedStatement.setString(2, name);
                    preparedStatement.setInt(3, Integer.parseInt(english));
                    preparedStatement.setInt(4, Integer.parseInt(computer));
                    preparedStatement.setInt(5, Integer.parseInt(maths));
                    preparedStatement.setInt(6, Integer.parseInt(physics));
                    preparedStatement.setInt(7, Integer.parseInt(chemistry));
                    preparedStatement.setFloat(8, p);
                    preparedStatement.setString(9, grade);

                    preparedStatement.executeUpdate();

                    preparedStatement.close();
                    connection.close();

                    System.out.println("Data stored in the database successfully!");
                    success.setText("result added in database!!");
                    success.setVisible(true);
                 }
                else {
                    
                    success.setText("Please add the student first.");
                    success.setVisible(true);
                }
            } catch (SQLException a) {
                a.printStackTrace();
            }

        }
    }
}
