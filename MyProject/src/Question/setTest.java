package Question;
import javax.swing.*;
import java.sql.*;
import java.awt.*;
import java.awt.event.*;
public class setTest 
{
	public static void main(String[] args) 
	{
		SetCodeFrame f=new SetCodeFrame();
		f.setTitle("Code");
		f.setVisible(true);
		f.setBounds(100,100,450,350);
		f.getContentPane().setBackground(Color.WHITE);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setResizable(false);
	}
}
class SetCodeFrame extends JFrame implements ActionListener
{
	Container c=getContentPane();
	JLabel title=new JLabel("Test Parameters");
	JLabel code=new JLabel("Enter the code:");
	JTextField codefield=new JTextField();
	JLabel qno=new JLabel("Enter the number of questions:");
	JTextField qnofield=new JTextField();
	JLabel time=new JLabel("Enter the time per question:");
	JTextField timefield=new JTextField();
	JButton b=new JButton("Submit");
	public SetCodeFrame()
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
		title.setBounds(125,30,250,40);
		title.setFont(title.getFont().deriveFont(25.0f));
		code.setBounds(50,100,180,30);
		codefield.setBounds(230,100,100,30);
		qno.setBounds(50,150,180,30);
		qnofield.setBounds(230,150,100,30);
		time.setBounds(50,200,180,30);
		timefield.setBounds(230,200,100,30);
		b.setBounds(230,250,100,30);
	}
	public void addComp()
	{
		c.add(title);
		c.add(code);
		c.add(codefield);
		c.add(qno);
		c.add(qnofield);
		c.add(time);
		c.add(timefield);
		c.add(b);
	}
	public void addEvent()
	{
		b.addActionListener(this);
	}
	public void actionPerformed(ActionEvent e)
	{
		String code=codefield.getText();
		String qno=qnofield.getText();
		String time=timefield.getText();
		int q=Integer.parseInt(qno);
		int t=Integer.parseInt(time);
		try
		{
			Connection conn=DriverManager.getConnection("jdbc:mysql://localhost/college","root","");
			String query1="SELECT * from questions";
			Statement st1=conn.createStatement();
			ResultSet rs1=st1.executeQuery(query1);
			int i=0;
			while(rs1.next())
			{
				i++;
			}
			if(q>i)
			{
				JOptionPane.showMessageDialog(SetCodeFrame.this,"Not enough questions in database");
			}
			else
			{
				String query = "INSERT INTO code values('" + code + "','" +q +"','" +t + "')";
				Statement st=conn.createStatement();
				int x=st.executeUpdate(query);
				setVisible(false);
				dispose();
				
			}
		}
		catch(SQLException sqle)
		{
			sqle.printStackTrace();
		}
		setVisible(false);
	}
}