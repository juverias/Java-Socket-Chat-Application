package server;

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
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

public class Server {

	private JFrame serverFrame;
	private JTextArea jta;
	private JScrollPane jsp;
	private JTextField jtf;

	private ServerSocket ss;

	private InetAddress ip;

	private Socket s;
	private DataInputStream dis;
	private DataOutputStream dos;

	Thread t = new Thread() {
		public void run() {
			while (true) {
				readMessage();
			}
		}
	};

	Server() {
		serverFrame = new JFrame("Server");
		serverFrame.setSize(500, 500);
		serverFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		jta = new JTextArea();
		jta.setEditable(false);
		Font f= new Font("Arial", 1, 16);
		jta.setFont(f);
		jsp = new JScrollPane(jta); // Text area added on ScrollPane
		serverFrame.add(jsp); // Scroll Pane added on JFrame

		jtf = new JTextField();
		serverFrame.add(jtf, BorderLayout.SOUTH);
		jtf.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				sendMessage(jtf.getText());
				jta.append(jtf.getText() + "\n");
				jtf.setText(" ");
			}
		});
		jtf.setEditable(false);
		serverFrame.setVisible(true);
	}

	public void waitingForClient() {
		try {
			String ipadd = getIpAddress();

			ss = new ServerSocket(1111);
			jta.append("Waiting for Client ....\n");
			jta.append("To connect with Server please provide IP Address : " + ipadd);
			s = ss.accept();
			jta.setText("Client Connected \n");
			jta.append("----------------------------------------------\n");
			jtf.setEditable(true);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getIpAddress() {
		String ip_add = null;
		try {
			ip = InetAddress.getLocalHost();
			ip_add = ip.getHostAddress();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}

		return ip_add;
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
		jta.append(message + "\n");
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
			showMessage("Client : "+msg);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void chatSound() {

			InputStream fis = Server.class.getResourceAsStream("/sound/chat_sound.mp3");
		    if (fis == null) {
		        System.out.println("Server sound file not found!");
		        return;
		    }

		    System.out.println("Server sound file loaded!");
		    
			Player p;
			try {
				p = new Player(fis);
				p.play();
			} catch (JavaLayerException e) {
				e.printStackTrace();
			}
		
		
	}

}
