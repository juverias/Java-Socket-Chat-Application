package client;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

public class Client {

	private JFrame clientFrame;
	private JTextArea jta;
	private JScrollPane jsp;
	private JTextField jtf;

	private Socket s;

	String ipadd;
	
	private DataInputStream dis ;
	private DataOutputStream dos ;
	
	Thread t = new Thread() {
		public void run() {
			while(true) {
				readMessage();
			}
		}
	};

	Client() {

		ipadd = JOptionPane.showInputDialog("Enter IP Address : ");
		
		if(ipadd!=null) {
			if (!ipadd.equals("")) {
				
				connectToServer();
				clientFrame = new JFrame("Client");
				clientFrame.setSize(500, 500);
				clientFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

				jta = new JTextArea();
				jta.setEditable(false);
				Font f= new Font("Arial", 1, 16);
				jta.setFont(f);
				jsp = new JScrollPane(jta); // Text area added on ScrollPane
				clientFrame.add(jsp); // Scroll Pane added on JFrame

				jtf = new JTextField();
				clientFrame.add(jtf, BorderLayout.SOUTH);
				jtf.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						sendMessage(jtf.getText());
				    	jta.append(jtf.getText()+"\n");
				    	jtf.setText(" ");
					}
				});

				clientFrame.setVisible(true);
			}
		}

	}

	public void connectToServer() {
		try {
			s = new Socket(ipadd, 1111);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	   public void setIOStreams() {
		   
	    	 try {
				dis = new DataInputStream(s.getInputStream()); // read
				dos = new DataOutputStream(s.getOutputStream()); // write 
	            
			 } catch (IOException e) {
				e.printStackTrace();
			 }
			   t.start();
	    }
	   
	    public void showMessage(String message) {
	    	jta.append(message+"\n");
			chatSound();
	    }
	   
	    public void sendMessage(String message) {
	    	try {
				dos.writeUTF(message);
		    	dos.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}

	    }
	    
	    public void readMessage() {
	    	try {
				String msg = dis.readUTF();
				showMessage("Server : "+msg);
			} catch (IOException e) {
				e.printStackTrace();
			}
	    }
	    
		public void chatSound() {

				InputStream fis = Client.class.getResourceAsStream("/sound/chat_sound.mp3");

				Player p;
				try {
					p = new Player(fis);
					p.play();
				} catch (JavaLayerException e) {
					e.printStackTrace();
				}
		 
		}

}
