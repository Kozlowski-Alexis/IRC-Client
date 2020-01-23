package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginForm extends JFrame{
	private static final long serialVersionUID = 1L;
	private JLabel urlPortLabel;
	private JTextField urlPortField;
	private JLabel loginLabel;
	private JTextField loginField;
	private JLabel passwordLabel;
	private JTextField passwordField;
	private JButton submitButton;
	
	public LoginForm() {
		super("Tchat IRC V0.1 - Connexion");
		final Container content = getContentPane();
		content.add(getContentPanel(), BorderLayout.CENTER);
		content.add(getSubmitPanel(), BorderLayout.SOUTH);
		this.setSize(600, 250);
		this.setResizable(false);
		setVisible(true);
	}
	
	
	public JPanel getContentPanel() {
		
		urlPortLabel = new JLabel("url + port");
		loginLabel = new JLabel("Login");
		passwordLabel = new JLabel("Mot de passe");
		urlPortLabel.setForeground(Color.white);
		loginLabel.setForeground(Color.white);
		passwordLabel.setForeground(Color.white);
		
		
		final JPanel labelPanel = new JPanel(new GridLayout(3 , 5, 5 , 5));
		
		labelPanel.add(urlPortLabel);
		labelPanel.add(loginLabel);
		labelPanel.add(passwordLabel);
		
		labelPanel.setBackground(Color.decode("#434343"));
		
		urlPortField = new JTextField(30);
		loginField = new JTextField(30);
		passwordField = new JPasswordField(30);
		final JPanel fieldsPanel = new JPanel(new GridLayout(3 , 5, 5 , 5));
		
		fieldsPanel.add(urlPortField);
		fieldsPanel.add(loginField);
		fieldsPanel.add(passwordField);
		
		fieldsPanel.setBackground(Color.decode("#434343"));
		
		final JPanel contentPanel = new JPanel(new BorderLayout());
		contentPanel.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 30));
		
		contentPanel.add(labelPanel, BorderLayout.WEST);
		contentPanel.add(fieldsPanel, BorderLayout.EAST);
		
		contentPanel.setBackground(Color.decode("#434343"));
		
		setResizable(false);
		return contentPanel;
	}
	
	public JPanel getSubmitPanel() {
		submitButton = new JButton("Se connecter");
		final JPanel submitPanel = new JPanel(new BorderLayout());
		
		
		submitPanel.add(submitButton, BorderLayout.CENTER);
		submitPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
		
		submitPanel.setBackground(Color.decode("#434343"));
		
		return submitPanel;
	}
}
