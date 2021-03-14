package Question;
import javax.swing.*;
import Login.*;
import java.sql.*;
import java.awt.*;
import java.awt.event.*;
public class Code 
{
	public static void main(String[] args)
	{
		CodeFrame f=new CodeFrame();
		f.setTitle("Code");
		f.setVisible(true);
		f.setBounds(100,100,350,300);
		f.getContentPane().setBackground(Color.WHITE);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setResizable(false);
	}
}
class CodeFrame extends JFrame implements ActionListener
{
	Container c=getContentPane();
	JLabel title=new JLabel("Code Page");
	JLabel code=new JLabel("Enter the code:");
	JTextField codefield=new JTextField();
	JButton b=new JButton("Proceed");
	String user;
	public CodeFrame()
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
		title.setBounds(100,30,180,40);
		title.setFont(title.getFont().deriveFont(30.0f));
		code.setBounds(35,100,100,30);
		codefield.setBounds(150,100,150,30);
		b.setBounds(150,165,100,30);
	}
	public void addComp()
	{
		c.add(title);
		c.add(code);
		c.add(codefield);
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
		String ycode=codefield.getText();
		try
		{
			Connection conn=DriverManager.getConnection("jdbc:mysql://localhost/college","root","");
			String query1="SELECT Code from code";
			Statement st=conn.createStatement();
			ResultSet rs1=st.executeQuery(query1);
			while(rs1.next())
			{
				String tcode=rs1.getString("Code");
				if(tcode.equals(ycode))
				{
					check++;
				}
			}
			if(check>0)
			{
				setVisible(false);
				dispose();
				Answers.main(null);
			}
			else
			{
				JOptionPane.showMessageDialog(CodeFrame.this,"Incorrect Code");
				setVisible(false);
				dispose();
				Code.main(null);
			}
		}
		catch(SQLException sqle)
		{
			sqle.printStackTrace();
		}
		
	}
}