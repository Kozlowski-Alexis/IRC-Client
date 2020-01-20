package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Logs extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JComboBox searchBox;
	private JTextField searchField;
	private JTextArea resultField;
	private JLabel mainLabel;
	private JLabel resultLabel;

	public Logs() {
		super("Tchat IRC V0.1");
		final Container content = getContentPane();
		content.add(getSearchPanel(), BorderLayout.NORTH);
		content.add(getResultPanel(), BorderLayout.CENTER);
		this.setSize(1080, 900);
		setVisible(true);
	}
	
	public JPanel getSearchPanel() {
		Object[] users = new Object[] {"Alexis", "Maxence", "Nagib"};
		searchBox = new JComboBox(users);
		searchField = new JTextField();
		mainLabel = new JLabel("Consultation des messages postes");
		mainLabel.setForeground(Color.white);
		
		final JPanel searchPanel = new JPanel(new BorderLayout(10,0));
		searchPanel.setBackground(Color.decode("#434343"));
		
		searchField.setMargin(new Insets(35, 0, 0, 0));
		
		mainLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
		
		searchPanel.setBorder(BorderFactory.createEmptyBorder(20, 10, 0, 10));
		searchPanel.add(mainLabel, BorderLayout.NORTH);
		searchPanel.add(searchField, BorderLayout.CENTER);
		searchPanel.add(searchBox, BorderLayout.EAST);
		
		return searchPanel;
	}
	
	public JPanel getResultPanel() {
		resultField = new JTextArea();
		resultLabel = new JLabel("Derniers messages");
		resultLabel.setForeground(Color.white);

		final JPanel resultPanel = new JPanel(new BorderLayout());
		resultPanel.setBackground(Color.decode("#434343"));
		
		resultField.setMargin(new Insets(10, 10, 10, 10));
		
		resultLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
		
		resultPanel.setBorder(BorderFactory.createEmptyBorder(30, 10, 20, 10));
		resultPanel.add(resultLabel, BorderLayout.NORTH);
		resultPanel.add(resultField, BorderLayout.CENTER);
		
		return resultPanel;
	}
}
