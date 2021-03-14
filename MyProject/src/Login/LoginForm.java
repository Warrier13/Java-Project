package Login;
import javax.swing.*;
import java.sql.*;
import java.awt.*;
import java.awt.event.*;
import Question.*;
public class LoginForm
{
	public static String user;
	public static void main(String[] args) 
	{
		LoginFrame f=new LoginFrame();
		f.setTitle("Login");
		f.setVisible(true);
		user=f.userfield.getText();
		f.setBounds(100,100,600,600);
		f.getContentPane().setBackground(Color.WHITE);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setResizable(false);
	}
}
class LoginFrame extends JFrame implements ActionListener
{
	Container c=getContentPane();
	JLabel title=new JLabel("Login");
	JLabel user=new JLabel("Username:");
	JLabel pwd=new JLabel("Password:");
	JTextField userfield=new JTextField();
	JPasswordField pwdfield=new JPasswordField();
	JButton b=new JButton("Login");
	public LoginFrame()
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
		title.setBounds(250,100,180,40);
		title.setFont(title.getFont().deriveFont(30.0f));
		user.setBounds(150,200,100,30);
		pwd.setBounds(150,250,100,30);
		userfield.setBounds(230,200,150,30);
		pwdfield.setBounds(230,250,150,30);
		b.setBounds(230,300,100,30);
	}
	public void addComp()
	{
		c.add(title);
		c.add(user);
		c.add(pwd);
		c.add(userfield);
		c.add(pwdfield);
		c.add(b);
	}
	public void addEvent()
	{
		b.addActionListener(this);
	}
	public void getUsername(String s)
	{
		s=userfield.getText();
	}
	public void actionPerformed(ActionEvent e)
	{
		String username=userfield.getText();
		String password=pwdfield.getText();
		String uname,pass;
		int c=0;
		try
		{
			Connection conn=DriverManager.getConnection("jdbc:mysql://localhost/college","root","");
			String query1="SELECT * from students";
			String query2="SELECT * from teachers";
			Statement st=conn.createStatement();
			ResultSet rs1=st.executeQuery(query1);
			while(rs1.next())
			{
				uname=rs1.getString("Username");
				pass=rs1.getString("Password");
				if(username.equals(uname)&&password.equals(pass))
				{
					c=1;
					JOptionPane.showMessageDialog(LoginFrame.this,"Login Success");
					setVisible(false);
					dispose();
					Code.main(null);
				}
			}
			ResultSet rs2=st.executeQuery(query2);
			while(rs2.next())
			{
				uname=rs2.getString("Username");
				pass=rs2.getString("Password");
				if(username.equals(uname)&&password.equals(pass))
				{
					c=1;
					JOptionPane.showMessageDialog(LoginFrame.this,"Directing to questions");
					setVisible(false);
					dispose();
					Questions.main(null);
				}
			}
			if(c==0)
			{
				JOptionPane.showMessageDialog(LoginFrame.this,"Check Username/Password incorrect");
			}
		}
		catch(SQLException sqle)
		{
			sqle.printStackTrace();
		}
	}
}