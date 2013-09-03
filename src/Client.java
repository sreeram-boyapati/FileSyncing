import java.rmi.*;
import java.awt.*;

import javax.swing.GroupLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Client {
	
	public static void main(String args[])
	{
		JFrame frame = new JFrame();
		frame.setSize(1000, 760);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(true);
		frame.setVisible(true);
		
		JPanel comm=new JPanel();
		GroupLayout gLayout = new GroupLayout(comm);
		comm.setLayout(gLayout);
		gLayout.setAutoCreateGaps(true);
		gLayout.setAutoCreateContainerGaps(true);
		JTextArea send_msg = new JTextArea(3, 5);
		JTextArea recv_msg = new JTextArea(3, 5);
		JTextField send_message = new JTextField("Send Message");
		JTextField recv_message = new JTextField("Recieve Message");
		gLayout.setVerticalGroup(gLayout.createSequentialGroup()
			.addGroup(gLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
				.addComponent(send_message)
				.addComponent(send_msg))
			.addGroup(gLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
				.addComponent(recv_message)
				.addComponent(recv_msg)));
		gLayout.setHorizontalGroup(gLayout.createSequentialGroup()
				.addGroup(gLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addComponent(send_message)
						.addComponent(recv_message))
				.addGroup(gLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addComponent(send_msg)
						.addComponent(recv_msg)));
		comm.setLayout(gLayout);
		comm.setVisible(true);
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
