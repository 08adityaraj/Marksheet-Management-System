import javax.swing.*;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

import javax.swing.table.DefaultTableModel;

public class Display implements ActionListener {
    private JTextField rollNumberField;
    JButton addButton = new JButton("SHOW RESULT");
    JTable table1 = new JTable();
    String DB_URL = "jdbc:mysql://localhost:3306/mms";
    String DB_USER = "root";
    String DB_PASSWORD = "pklUHw:Im|$9~.T";
    JLabel success = new JLabel();
    //static int k =0;
    public Display() {
        JFrame frame = new JFrame();
        frame.setTitle("Marksheet");
        frame.setSize(800, 430);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().setBackground(new Color(196, 164, 132));

        JLabel rollNumberLabel = new JLabel("Enter your Roll Number:");
        rollNumberField = new JTextField();

        addButton.addActionListener(this);

        success.setText("Student not found in database....");
        success.setBounds(20, 370, 220, 25);
        success.setVisible(false);

        Container inputPanel = frame.getContentPane();
        inputPanel.setLayout(null);

        rollNumberLabel.setBounds(20, 20, 200, 40);
        rollNumberField.setBounds(180, 20, 200, 40);
        inputPanel.add(rollNumberLabel);
        inputPanel.add(rollNumberField);


        addButton.setBounds(300, 90, 200, 40);

        JScrollPane scrollPane = new JScrollPane(table1);
        scrollPane.setPreferredSize(new Dimension(600, 190));

        JPanel mainPanel = new JPanel();
        mainPanel.add(scrollPane);
        mainPanel.setBounds(100,150,610,200);

        //table1.setBounds(0, 0, 600, 80);
        inputPanel.add(addButton);
        inputPanel.add(mainPanel);
        inputPanel.add(success);
        //inputPanel.add(table1);

        frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        String rollno = rollNumberField.getText();
        if(e.getSource()==addButton){
            try{
                 Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                 Statement statement = connection.createStatement();

                 ResultSet rs = statement.executeQuery("select * from result where roll_no='"+rollno+"'");
                

                 ResultSetMetaData rsmd = rs.getMetaData();
                 DefaultTableModel model = (DefaultTableModel)table1.getModel();

                 int cols =rsmd.getColumnCount();
                 String[] colName = new String[cols];
                 for(int i=0;i<cols;i++){
                    colName[i]=rsmd.getColumnName(i+1);
                 }

                 model.setColumnIdentifiers(colName);

                 String a,b,c,d,f,g,h,i,j;
                //  a="Roll_N";
                //  b="Name";
                //  c="English";
                //  d="Computer";
                //  f="Maths";
                //  g="Physics";
                //  h="Chemistry";
                //  i="Percentage";
                //  j="Grade";
                //  if(k==0){
                //  String[] row1 = {a,b,c,d,f,g,h,i,j};
                //  model.addRow(row1);
                //  k++;
                //  }
                 if(rs.next()){
                    a = rs.getString(1);
                    b = rs.getString(2);
                    c = rs.getString(3);
                    d = rs.getString(4);
                    f = rs.getString(5);
                    g = rs.getString(6);
                    h = rs.getString(7);
                    i = rs.getString(8);
                    j = rs.getString(9);

                    String[] row = {a,b,c,d,f,g,h,i,j};

                    model.addRow(row);
                    success.setVisible(false);
                 }
                 else{
                    success.setVisible(true);
                 }
                 
                 statement.close();
                 connection.close();

            }

            catch (SQLException a) {
                a.printStackTrace();
            }
        }
        
    }
}
