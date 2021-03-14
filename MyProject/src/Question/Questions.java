package Question;
import javax.swing.*;
import java.sql.*;
import java.sql.Connection;
import java.awt.*;
import java.awt.event.*;
public class Questions 
{

	public static void main(String[] args) 
	{
		QuestionFrame f=new QuestionFrame();
		f.setTitle("Set Question");
		f.setVisible(true);
		f.setBounds(100,100,800,700);
		f.getContentPane().setBackground(Color.WHITE);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setResizable(false);
	}
}
class QuestionFrame extends JFrame implements ActionListener
{
	Container c=getContentPane();
	JLabel title=new JLabel("Set Questions");
	JLabel subject=new JLabel("Enter subject: ");
	JLabel question=new JLabel("Enter question: ");
	JLabel op1=new JLabel("Option 1: ");
	JLabel op2=new JLabel("Option 2:");
	JLabel op3=new JLabel("Option 3: ");
	JLabel op4=new JLabel("Option 4: ");
	JLabel opc=new JLabel("Correct option: ");
	JTextField questionfield=new JTextField();
	JTextField subjectfield=new JTextField();
	JTextField op1field=new JTextField();
	JTextField op2field=new JTextField();
	JTextField op3field=new JTextField();
	JTextField op4field=new JTextField();
	JTextField opcfield=new JTextField();
	JCheckBox cont=new JCheckBox("Continue");
	JCheckBox exit=new JCheckBox("Exit");
	JCheckBox code=new JCheckBox("Set Code");
	JButton b=new JButton("Submit");
	public QuestionFrame()
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
		title.setBounds(300,50,400,30);
		title.setFont(title.getFont().deriveFont(30.0f));
		subject.setBounds(150,150,100,30);
		question.setBounds(150,200,100,30);
		op1.setBounds(150,250,100,50);
		op2.setBounds(150,300,100,30);
		op3.setBounds(150,350,100,30);
		op4.setBounds(150,400,100,30);
		opc.setBounds(150,450,100,30);
		subjectfield.setBounds(250,150,150,30);
		questionfield.setBounds(250,200,450,30);
		op1field.setBounds(250,250,150,30);
		op2field.setBounds(250,300,150,30);
		op3field.setBounds(250,350,150,30);
		op4field.setBounds(250,400,150,30);
		opcfield.setBounds(250,450,150,30);
		cont.setBounds(150,500,100,30);
		cont.setBackground(Color.WHITE);
		code.setBounds(250,500,100,30);
		code.setBackground(Color.WHITE);
		exit.setBounds(350,500,100,30);
		exit.setBackground(Color.WHITE);
		b.setBounds(250,550,100,30);
	}
	public void addComp()
	{
		c.add(title);
		c.add(subject);
		c.add(question);
		c.add(op1);
		c.add(op2);
		c.add(op3);
		c.add(op4);
		c.add(opc);
		c.add(subjectfield);
		c.add(questionfield);
		c.add(op1field);
		c.add(op2field);
		c.add(op3field);
		c.add(op4field);
		c.add(opcfield);
		c.add(cont);
		c.add(code);
		c.add(exit);
		c.add(b);
	}
	public void addEvent()
	{
		b.addActionListener(this);
	}
	public void actionPerformed(ActionEvent e)
	{
		String subject=subjectfield.getText();
		String question=questionfield.getText();
		String op1=op1field.getText();
		String op2=op2field.getText();
		String op3=op3field.getText();
		String op4=op4field.getText();
		String opc=opcfield.getText();
		try
		{
			Connection conn=DriverManager.getConnection("jdbc:mysql://localhost/college","root","");

	
					String query = "INSERT INTO questions values('" + question + "','" + op1 + "','" + op2 + "','" +op3 + "','" + op4 + "','" + opc + "','" +subject+"')";
					Statement st=conn.createStatement();
					int x=st.executeUpdate(query);
					if(cont.isSelected())
					{
						setVisible(false);
						dispose();
						Questions.main(null);
					}
					else if(code.isSelected())
					{
						setVisible(false);
						dispose();
						setTest.main(null);
					}
					else if(exit.isSelected())
					{
						JOptionPane.showMessageDialog(QuestionFrame.this,"Exiting");
						setVisible(false);
						dispose();
					}
		}
		catch(SQLException sqle)
		{
			sqle.printStackTrace();
		}
	}
}