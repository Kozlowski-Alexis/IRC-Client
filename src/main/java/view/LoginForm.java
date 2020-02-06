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
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import controller.LoginFormController;
import controller.LogsController;

public class LoginForm extends JFrame{
	
	private static final long serialVersionUID = 1L;
	private JLabel urlPortLabel;
	private JTextField urlPortField;
	private JLabel loginLabel;
	private JTextField loginField;
	private JLabel passwordLabel;
	private JPasswordField passwordField;
	private JButton submitButton;
	private JButton logsButton;
	private JLabel titleLabel;
	
	public LoginForm(LoginFormController loginController) {
		super("Tchat IRC V0.1 - Connexion");
		final Container content = getContentPane();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		content.add(getTitlePanel(), BorderLayout.NORTH);
		content.add(getContentPanel(), BorderLayout.CENTER);
		content.add(getSubmitPanel(loginController), BorderLayout.SOUTH);
		this.setSize(600, 375);
		this.setResizable(false);
		setVisible(true);
	}
	
	public JPanel getTitlePanel() {
		final JPanel titlePanel = new JPanel(new BorderLayout());
		titleLabel = new JLabel("Connexion", SwingConstants.CENTER);
		titleLabel.setForeground(Color.white);
		titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
		titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
		titlePanel.setBackground(Color.decode("#434343"));
		titlePanel.add(titleLabel, BorderLayout.CENTER);
		
		return titlePanel;
	}
	
	public JPanel getContentPanel() {
		urlPortLabel = new JLabel("url + port");
		urlPortLabel.setForeground(Color.white);
		
		loginLabel = new JLabel("Login");
		loginLabel.setForeground(Color.white);
		
		passwordLabel = new JLabel("Mot de passe");
		passwordLabel.setForeground(Color.white);
		
		final JPanel labelPanel = new JPanel(new GridLayout(3 , 5, 5 , 5));
		labelPanel.add(urlPortLabel);
		labelPanel.add(loginLabel);
		labelPanel.add(passwordLabel);
		
		labelPanel.setBackground(Color.decode("#434343"));
		
		final JPanel urlPortPanel = new JPanel(new BorderLayout());
		urlPortField = new JTextField(28);
		urlPortField.setBackground(Color.decode("#4a86e8"));
		urlPortField.setForeground(Color.white);
		urlPortField.setFont(new Font("Arial", Font.BOLD, 13));
		urlPortField.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		urlPortField.setDocument(new FieldLimit(30));
		urlPortPanel.add(urlPortField);
		urlPortPanel.setBorder(new LineBorder(Color.white, 2, true));
		
		final JPanel loginPanel = new JPanel(new BorderLayout());
		loginField = new JTextField(28);
		loginField.setBackground(Color.decode("#4a86e8"));
		loginField.setForeground(Color.white);
		loginField.setFont(new Font("Arial", Font.BOLD, 13));
		loginField.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		loginField.setDocument(new FieldLimit(40));
		loginPanel.add(loginField);
		loginPanel.setBorder(new LineBorder(Color.white, 2, true));
		
		
		final JPanel passwordPanel = new JPanel(new BorderLayout());
		passwordField = new JPasswordField(28);
		passwordField.setBackground(Color.decode("#4a86e8"));
		passwordField.setForeground(Color.white);
		passwordField.setFont(new Font("Arial", Font.BOLD, 13));
		passwordField.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		passwordField.setDocument(new FieldLimit(40));
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
	
	public JPanel getSubmitPanel(LoginFormController loginController) {
		submitButton = new JButton("Se connecter");
		submitButton.setBackground(Color.decode("#4a86e8"));
		submitButton.setForeground(Color.white);
		submitButton.setFont(new Font("Arial", Font.BOLD, 13));
		submitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		logsButton = new JButton("Accéder aux logs");
		logsButton.setBackground(Color.decode("#4a86e8"));
		logsButton.setForeground(Color.white);
		logsButton.setFont(new Font("Arial", Font.BOLD, 13));
		logsButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		

		submitButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				loginController.login(urlPortField.getText(), loginField.getText(), passwordField.getPassword());
			}
		});
		
		logsButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				LogsController logsController = new LogsController();
				close();
			}
		});
		
		final JPanel logsPanel = new JPanel(new BorderLayout());
		logsPanel.add(logsButton, BorderLayout.CENTER);
		logsPanel.setBorder(new LineBorder(Color.white, 2, true));
		logsPanel.setBackground(Color.decode("#434343"));
		
		final JPanel submitPanel = new JPanel(new BorderLayout());
		submitPanel.add(submitButton, BorderLayout.CENTER);
		submitPanel.setBorder(new LineBorder(Color.white, 2, true));
		submitPanel.setBackground(Color.decode("#434343"));
		
		final JPanel contentPanel = new JPanel(new GridLayout(2 , 5, 5 , 10));
		contentPanel.add(submitPanel);
		contentPanel.add(logsPanel);
		contentPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
		contentPanel.setBackground(Color.decode("#434343"));
		
		
		
		return contentPanel;
	}
	
	private void close() {
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
	}
}
