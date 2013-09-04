import java.rmi.*;
import java.awt.*;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpringLayout.Constraints;

public class Client {
	
	public static void main(String args[])
	{
		JFrame frame = new JFrame();
		frame.setSize(800, 560);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setVisible(true);
		
		JPanel comm=new JPanel();
		GroupLayout gLayout = new GroupLayout(comm);
		comm.setLayout(gLayout);
		gLayout.setAutoCreateGaps(true);
		gLayout.setAutoCreateContainerGaps(true);
		JTextArea send_msg = new JTextArea();
		send_msg.setLineWrap(true);
		JTextArea recv_msg = new JTextArea();
		recv_msg.setLineWrap(true);
		JButton send=new JButton();
		JButton recv=new JButton();
		send.setText("Send Message");
		recv.setText("Recieve Message");
		JLabel send_message = new JLabel("Send Message");
		JLabel recv_message = new JLabel("Recieve Message");
		JLabel createFile_label =new JLabel("Create File");
		JTextField Name_of_file=new JTextField("Enter Name of file");
		JButton Status=new JButton("Create File");
		gLayout.setVerticalGroup(gLayout.createSequentialGroup()
			.addGroup(gLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
					.addGroup(gLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(send_message)
						.addComponent(send_msg))
					.addComponent(send))
			.addGroup(gLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
					.addGroup(gLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(recv_message)
						.addComponent(recv_msg))
					.addComponent(recv))
			.addGroup(gLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
					.addGroup(gLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(createFile_label)
						.addComponent(Name_of_file))
					.addComponent(Status)));
		gLayout.setHorizontalGroup(gLayout.createSequentialGroup()
				.addGroup(gLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addComponent(send_message)
						.addComponent(recv_message)
						.addComponent(createFile_label))
				.addGroup(gLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addComponent(send_msg)
						.addComponent(recv_msg)
						.addComponent(Name_of_file))
				.addGroup(gLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addComponent(send)
						.addComponent(recv)
						.addComponent(Status)));
		comm.setLayout(gLayout);
		comm.setVisible(true);
		comm.setPreferredSize(new Dimension(500, 650));
		frame.getContentPane().add(comm);
		frame.setVisible(true);
	}
	private void connection()
	{
		try{
			Syncer stub=(Syncer)Naming.lookup("rmi://localhost:5000/sonoo");
			System.out.println(stub.add(3, 4));
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
}
