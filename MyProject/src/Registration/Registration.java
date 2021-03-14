package Registration;
import javax.swing.*;
import java.sql.*;
import java.sql.Connection;
import java.awt.*;
import java.awt.event.*;
import Login.*;
public class Registration 
{
	public static void main(String[] args) 
	{
		RegistrationFrame f=new RegistrationFrame();
		f.setTitle("Register");
		f.setVisible(true);
		f.setBounds(100,100,600,600);
		f.getContentPane().setBackground(Color.WHITE);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setResizable(false);
	}
}
class RegistrationFrame extends JFrame implements ActionListener
{
	Container c=getContentPane();
	JLabel title=new JLabel("Register");
	JLabel user=new JLabel("Username:");
	JLabel des=new JLabel("Designation: ");
	JLabel pwd=new JLabel("Password:");
	JLabel cpwd=new JLabel("Confirm Password: ");
	JTextField userfield=new JTextField();
	JPasswordField pwdfield=new JPasswordField();
	JTextField desfield=new JTextField();
	JPasswordField cpwdfield=new JPasswordField();
	JButton b=new JButton("Register");
	public RegistrationFrame()
	{
		setLayout();
		setLocationSize();
		addComp();
		addEvent();
	}
	public void setLayout()
	{
		c.setLayout(null);
	}
	public void setLocationSize()
	{
		title.setBounds(225,100,180,40);
		title.setFont(title.getFont().deriveFont(30.0f));
		user.setBounds(150,200,180,30);
		des.setBounds(150,250,180,30);
		pwd.setBounds(150,300,180,30);
		cpwd.setBounds(150,350,180,30);
		userfield.setBounds(280,200,150,30);
		desfield.setBounds(280,250,150,30);
		pwdfield.setBounds(280,300,150,30);
		cpwdfield.setBounds(280,350,150,30);
		b.setBounds(250,400,100,30);
	}
	public void addComp()
	{
		c.add(title);
		c.add(user);
		c.add(des);
		c.add(pwd);
		c.add(cpwd);
		c.add(userfield);
		c.add(desfield);
		c.add(pwdfield);
		c.add(cpwdfield);
		c.add(b);
	}
	public void addEvent()
	{
		b.addActionListener(this);
	}
	public void actionPerformed(ActionEvent e)
	{
		String username=userfield.getText();
		String designation=desfield.getText();
		String password=pwdfield.getText();
		String cpassword=cpwdfield.getText();
		try
		{
			Connection conn=DriverManager.getConnection("jdbc:mysql://localhost/college","root","");
			if(password.equals(cpassword))
			{
				if(designation.equals("student"))
				{
					String query = "INSERT INTO students values('" + username + "','" +password + "')";
					Statement st=conn.createStatement();
					int x=st.executeUpdate(query);
					setVisible(false);
					dispose();
					LoginForm.main(null);
				}
				else if(designation.equals("teacher"))
				{
					String query = "INSERT INTO teachers values('" + username + "','" +password + "')";
					Statement st=conn.createStatement();
					int x=st.executeUpdate(query);
					setVisible(false);
					dispose();
					LoginForm.main(null);
				}
			}
			else
			{
				JOptionPane.showMessageDialog(RegistrationFrame.this,"Check Password");
			}
		}
		catch(SQLException sqle)
		{
			sqle.printStackTrace();
		}
	}
}