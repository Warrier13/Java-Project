package Question;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
public class Answers 
{
	public static int a=0;
	public static int score=0;
	public static void main(String[] args) 
	{
		AnswerFrame f=new AnswerFrame(a++);
		f.setTitle("Quiz");
		f.setVisible(true);
		f.setBounds(100,100,800,600);
		f.getContentPane().setBackground(Color.WHITE);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setResizable(false);
		score=f.total;
	}
}
class AnswerFrame extends JFrame implements ActionListener
{
	Container c=getContentPane();
	String[] coranswer=new String[50];
	String[] myanswer=new String[50];
	String[] q=new String[50];
	String[] oc=new String[50];
	String[] o1=new String[50];
	String[] o2=new String[50];
	String[] o3=new String[50];
	String[] o4=new String[50];
	int[] allmarks=new int[50];
	public static int size;
	int k=0,start,total=0;
	JLabel title=new JLabel("Quiz");
	JLabel question=new JLabel();
	JRadioButton op1=new JRadioButton();
	JRadioButton op2=new JRadioButton();
	JRadioButton op3=new JRadioButton();
	JRadioButton op4=new JRadioButton();
	JLabel time=new JLabel();
	Timer timer;
	JButton b=new JButton("Next");
	public AnswerFrame(int j)
	{
		setLayout();
		setLocationSize();
		addComp();
		addEvent();
		addQuestion(j);
		getTime();
		k=j;
		timer= new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {
			public void run()
			{
				startTimer();
				if(start==1)
				{
					timer.cancel();
					setVisible(false);
					dispose();
					if(k>=2)
					{
						Answers.main(null);
					}
				}
				--start;
			}
		},1000,1000);
	}
	public void setLayout()
	{
		c.setLayout(null);
	}
	public void setLocationSize()
	{
		title.setBounds(300,75,500,80);
		title.setFont(title.getFont().deriveFont(50.0f));
		question.setBounds(150,200,300,50);
		op1.setBounds(150,250,100,50);
		op1.setBackground(Color.WHITE);
		op2.setBounds(270,250,100,50);
		op2.setBackground(Color.WHITE);
		op3.setBounds(150,300,100,50);
		op3.setBackground(Color.WHITE);
		op4.setBounds(270,300,100,50);
		op4.setBackground(Color.WHITE);
		time.setBounds(150,350,300,50);
		b.setBounds(150,400,100,50);
	}
	public void addComp()
	{
		c.add(title);
		c.add(question);
		c.add(op1);
		c.add(op2);
		c.add(op3);
		c.add(op4);
		c.add(time);
		c.add(b);
	}
	public void addEvent()
	{
		b.addActionListener(this);
	}
	public void getTime()
	{
		String star="";
		try
		{
			Connection conn=DriverManager.getConnection("jdbc:mysql://localhost/college","root","");
			String query="SELECT * from code";
			Statement st=conn.createStatement();
			ResultSet rs=st.executeQuery(query);
			while(rs.next())
			{
				star=rs.getString("Time");
			}
			start=Integer.parseInt(star);
		}
		catch(SQLException sqle)
		{
			sqle.printStackTrace();
		}
	}
	public void startTimer()
	{
		time.setText("Time left: "+start);
	}
	public void addQuestion(int j)
	{	
		try
		{
			Connection conn=DriverManager.getConnection("jdbc:mysql://localhost/college","root","");
			String query="SELECT * from questions";
			Statement st=conn.createStatement();
			ResultSet rs=st.executeQuery(query);
			int i=0;
			while(rs.next())
			{
				q[i]=rs.getString("Question");
				o1[i]=rs.getString("Option1");
				o2[i]=rs.getString("Option2");
				o3[i]=rs.getString("Option3");
				o4[i]=rs.getString("Option4");
				coranswer[i]=rs.getString("CorrectOption");
				i++;
			}
			size=i;
			question.setText(q[j]);
			op1.setText(o1[j]);
			op2.setText(o2[j]);
			op3.setText(o3[j]);
			op4.setText(o4[j]);
		}
		catch(SQLException sqle)
		{
			sqle.printStackTrace();
		}
	}
	public void actionPerformed(ActionEvent e)
	{
		if(op1.isSelected())
		{
			myanswer[k]=o1[k];
		}
		else if(op2.isSelected())
		{
			myanswer[k]=o2[k];
		}
		else if(op3.isSelected())
		{
			myanswer[k]=o3[k];
		}
		else if(op4.isSelected())
		{
			myanswer[k]=o4[k];
		}
		if(myanswer[k].equals(coranswer[k]))
		{
			JOptionPane.showMessageDialog(AnswerFrame.this,"Correct");
			int score=1;
			try
			{
				Connection conn=DriverManager.getConnection("jdbc:mysql://localhost/college","root","");
				if(k==1)
				{
					String query = "DELETE from marks";
					Statement st=conn.createStatement();
					int x=st.executeUpdate(query);
				}
				String query = "INSERT INTO marks values('" + k + "','" +score + "')";
				Statement st=conn.createStatement();
				int x=st.executeUpdate(query);
			}
			catch(SQLException sqle)
			{
				sqle.printStackTrace();
			}
		}
		else
		{
			JOptionPane.showMessageDialog(AnswerFrame.this,"Wrong");
			int score=0;
			try
			{
				Connection conn=DriverManager.getConnection("jdbc:mysql://localhost/college","root","");
				String query = "INSERT INTO marks values('" + k + "','" +score + "')";
				Statement st=conn.createStatement();
				int x=st.executeUpdate(query);
			}
			catch(SQLException sqle)
			{
				sqle.printStackTrace();
			}
		}
		if(k<size-1)
		{
			setVisible(false);
			dispose();
			Answers.main(null);
		}
		else
		{
			setVisible(false);
			dispose();
			Results.main(null);
		}
	}
}