import javax.swing.*;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class Login implements ActionListener{
    JButton admn = new JButton("ADMIN");
    JButton student = new JButton("STUDENT");
    public Login(){
        JFrame lgn = new JFrame();
        lgn.setTitle("Welcome to The LNMIIT");
        ImageIcon image = new ImageIcon("logo.png");
        lgn.setIconImage(image.getImage());

        lgn.getContentPane().setBackground(new Color(0,130,127));

        // JPanel panel = new JPanel();
        // lgn.getContentPane();
        lgn.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        lgn.setBounds(0,0,500,500);
        
        
        admn.setBounds(150,10,200,40);
        admn.addActionListener(this);


        
        student.setBounds(150,50,200,40);
        student.addActionListener(this);

        Container container = lgn.getContentPane();
        container.setLayout(null);

        container.add(admn);
        container.add(student);
        lgn.setVisible(true);
    }
    public void actionPerformed(ActionEvent e) {

       if(e.getSource()==admn){
           
           Admin a = new Admin();
       }

       else if(e.getSource()==student){
           Display disp = new Display();
       }
    }
    
}
