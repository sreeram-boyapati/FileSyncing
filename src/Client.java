import java.net.MalformedURLException;
import java.rmi.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Client implements ActionListener{
	
	static JFrame frame;
	static JPanel comm;
	static JTextArea send_msg;
	static JTextArea recv_msg;
	static JButton send;
	static JButton recv;
	static JLabel send_message;
	static JLabel recv_message;
	static JLabel createFile_label;
	static JTextField Name_of_file;
	static JButton createFileButton;
	static JLabel Console;
	static JLabel OpenFileLabel;
	static JTextField open_file_field;
	static JButton Open_FileButton;
	static JTextArea console_window;
	static Syncer stub;
	public static void main(String args[]) throws MalformedURLException, RemoteException, NotBoundException
	{
		stub = (Syncer)Naming.lookup("rmi://localhost:5000/sonoo");
		Initialize_UI();
		
	
	}
	private static void Initialize_UI()
	{
		frame = new JFrame();
		frame.setSize(800, 560);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setVisible(true);
		
		comm=new JPanel();
		GroupLayout gLayout = new GroupLayout(comm);
		comm.setLayout(gLayout);
		gLayout.setAutoCreateGaps(true);
		gLayout.setAutoCreateContainerGaps(true);
		send_msg = new JTextArea();
		send_msg.setLineWrap(true);
		recv_msg = new JTextArea();
		recv_msg.setLineWrap(true);
		send=new JButton();
		recv=new JButton();
		send.setText("Send Message");
		recv.setText("Recieve Message");
		send_message = new JLabel("Send Message");
		recv_message = new JLabel("Recieve Message");
		createFile_label = new JLabel("Create File");
		Name_of_file = new JTextField("Enter Name of file");
		createFileButton = new JButton("Create File"); 
		Console = new JLabel("Status Terminal");
		OpenFileLabel = new JLabel("Open File");
		open_file_field = new JTextField("Enter Name of File");
		Open_FileButton = new JButton("Open File");
		console_window = new JTextArea();
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
					.addComponent(createFileButton))
			.addGroup(gLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
					.addGroup(gLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
							.addComponent(OpenFileLabel)
							.addComponent(open_file_field))
					.addComponent(Open_FileButton))
			.addGroup(gLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
					.addGroup(gLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(Console)
						.addComponent(console_window))));
			
		gLayout.setHorizontalGroup(gLayout.createSequentialGroup()
				.addGroup(gLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addComponent(send_message)
						.addComponent(recv_message)
						.addComponent(createFile_label)
						.addComponent(OpenFileLabel)
						.addComponent(Console))
				.addGroup(gLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addComponent(send_msg)
						.addComponent(recv_msg)
						.addComponent(Name_of_file)
						.addComponent(open_file_field)
						.addComponent(console_window))
				.addGroup(gLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addComponent(send)
						.addComponent(recv)
						.addComponent(createFileButton)
						.addComponent(Open_FileButton)));
		comm.setLayout(gLayout);
		comm.setVisible(true);
		comm.setPreferredSize(new Dimension(500, 650));
		frame.getContentPane().add(comm);
		frame.setTitle("File Syncer Client");
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

	@Override
	public void actionPerformed(ActionEvent Event) {
		try{
			if(Event.getSource() == createFileButton){
				String fileName = Name_of_file.getText();
				String Status = stub.createFile(fileName);
				console_window.setText(Status);
			}
			if(Event.getSource() == Open_FileButton){
				String fileName = open_file_field.getText();
				String Status = stub.openFile(fileName);
				console_window.setText(Status);
			}
			if(Event.getSource() == send){
				String message = send_msg.getText();
				String Status = stub.sendMsg(message);
				console_window.setText(Status);
			}
			if(Event.getSource() == recv){
				
				String Message = stub.recvMsg();
				recv_msg.setText(Message);
				if(Message != null){
					console_window.setText("Message Received Succesfully");
					
				}
				else{
					console_window.setText("Action Failed");
				}
			}
		}
		catch(Exception e){
				e.printStackTrace();
		}
			
	}
		
	
	
}
