package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

public class LoginForm extends JFrame{
	private static final long serialVersionUID = 1L;
	private JLabel urlPortLabel;
	private JTextField urlPortField;
	private JLabel loginLabel;
	private JTextField loginField;
	private JLabel passwordLabel;
	private JPasswordField passwordField;
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
		
		final JPanel urlPortPanel = new JPanel(new BorderLayout());
		urlPortField = new JTextField(30);
		urlPortField.setBackground(Color.decode("#4a86e8"));
		urlPortField.setForeground(Color.white);
		urlPortField.setFont(new Font("Arial", Font.BOLD, 13));
		urlPortField.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		urlPortPanel.add(urlPortField);
		urlPortPanel.setBorder(new LineBorder(Color.white, 2, true));
		
		final JPanel loginPanel = new JPanel(new BorderLayout());
		loginField = new JTextField(30);
		loginField.setBackground(Color.decode("#4a86e8"));
		loginField.setForeground(Color.white);
		loginField.setFont(new Font("Arial", Font.BOLD, 13));
		loginField.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		loginPanel.add(loginField);
		loginPanel.setBorder(new LineBorder(Color.white, 2, true));
		
		
		final JPanel passwordPanel = new JPanel(new BorderLayout());
		passwordField = new JPasswordField(30);
		passwordField.setBackground(Color.decode("#4a86e8"));
		passwordField.setForeground(Color.white);
		passwordField.setFont(new Font("Arial", Font.BOLD, 13));
		passwordField.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		passwordPanel.add(passwordField);
		passwordPanel.setBorder(new LineBorder(Color.white, 2, true));
		
		final JPanel fieldsPanel = new JPanel(new GridLayout(3 , 5, 5 , 5));
		fieldsPanel.setBackground(Color.decode("#434343"));
		
		fieldsPanel.add(urlPortPanel);
		fieldsPanel.add(loginPanel);
		fieldsPanel.add(passwordPanel);
		
		
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
		submitButton.setBackground(Color.decode("#4a86e8"));
		submitButton.setForeground(Color.white);
		submitButton.setFont(new Font("Arial", Font.BOLD, 13));
		submitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		

		submitButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				System.out.println(loginField.getText());
				System.out.println(passwordField.getPassword());
				
			}
		});
		
		final JPanel submitPanel = new JPanel(new BorderLayout());
		submitPanel.add(submitButton, BorderLayout.CENTER);
		submitPanel.setBorder(new LineBorder(Color.white, 2, true));
		submitPanel.setBackground(Color.decode("#434343"));
		
		final JPanel contentPanel = new JPanel(new BorderLayout());
		contentPanel.add(submitPanel, BorderLayout.CENTER);
		contentPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
		contentPanel.setBackground(Color.decode("#434343"));
		
		
		
		return contentPanel;
	}
}
