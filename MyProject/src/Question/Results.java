package Question;
import javax.swing.*;
import java.sql.*;
import java.awt.*;
import java.awt.event.*;
import Question.Answers;
public class Results 
{
	public static void main(String[] args) 
	{
		ResultFrame f=new ResultFrame();
		f.setTitle("Code");
		f.setVisible(true);
		f.setBounds(100,100,450,300);
		f.getContentPane().setBackground(Color.WHITE);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setResizable(false);
	}
}
class ResultFrame extends JFrame implements ActionListener
{
	Container c=getContentPane();
	JLabel title=new JLabel("Results Page");
	JLabel uname=new JLabel("Confirm your username:");
	JTextField unamefield=new JTextField();
	JButton b=new JButton("View Results");
	String user;
	public ResultFrame()
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
		title.setBounds(100,30,200,40);
		title.setFont(title.getFont().deriveFont(30.0f));
		uname.setBounds(35,100,180,30);
		unamefield.setBounds(200,100,180,30);
		b.setBounds(150,165,180,30);
	}
	public void addComp()
	{
		c.add(title);
		c.add(uname);
		c.add(unamefield);
		c.add(b);
	}
	public void addEvent()
	{
		b.addActionListener(this);
	}
	public void getUser(String s)
	{
		
	}
	public void actionPerformed(ActionEvent e)
	{
		int check=0;
		String user=unamefield.getText();
		try
		{
			Connection conn=DriverManager.getConnection("jdbc:mysql://localhost/college","root","");
			String query1="SELECT Username from students";
			Statement st=conn.createStatement();
			ResultSet rs1=st.executeQuery(query1);
			while(rs1.next())
			{
				String tcode=rs1.getString("Username");
				if(tcode.equals(user))
				{
					check++;
				}
			}
			if(check>0)
			{
				setVisible(false);
				dispose();
				int t=0;
				Connection conn1=DriverManager.getConnection("jdbc:mysql://localhost/college","root","");
				String query2 = "INSERT INTO result values('" + user + "','" +t + "')";
				Statement st1=conn.createStatement();
				int x=st.executeUpdate(query2);	
				String query3 = "SELECT * from marks";
				Statement st2=conn1.createStatement();
				ResultSet rs=st.executeQuery(query3);
				while(rs.next())
				{
					t+=Integer.parseInt(rs.getString("Marks"));
				}
				JOptionPane.showMessageDialog(ResultFrame.this,"Test Over, your final score is: "+t);
			}
			else
			{
				JOptionPane.showMessageDialog(ResultFrame.this,"Incorrect Username, try again");
				setVisible(false);
				dispose();
				Results.main(null);
			}
		}
		catch(SQLException sqle)
		{
			sqle.printStackTrace();
		}
		
	}
}