package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import controller.LogsController;
import model.bean.Log;


public class Logs extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JComboBox<String> searchBox;
	private JTextField searchField;
	private JTextArea resultField;
	private JLabel mainLabel;
	private JLabel titleLabel;
	private JLabel resultLabel;

	public Logs(LogsController logsController) {
		super("Tchat IRC V0.1");
		resultField = new JTextArea("Aucuns Logs !!! \n");
		final Container content = getContentPane();
		content.add(getSearchPanel(logsController), BorderLayout.NORTH);
		content.add(getResultPanel(), BorderLayout.CENTER);
		this.setSize(1080, 900);
		this.setMinimumSize(new Dimension(800, 500));
		setVisible(true);
	}
	
	public JPanel getSearchPanel(LogsController logsController) {
		
		final JPanel searchBoxPanel = new JPanel(new BorderLayout());
		searchBox = new JComboBox<String>();
		searchBox.setPreferredSize(new Dimension(200,35));
		searchBox.setBackground(Color.decode("#4a86e8"));
		searchBox.setForeground(Color.white);
		searchBox.setFont(new Font("Arial", Font.BOLD, 13));
		searchBox.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		searchBoxPanel.add(searchBox);
		searchBoxPanel.setBorder(new LineBorder(Color.white, 2, true));
		List<Log> logList = logsController.getListLogs();
		for (Log log : logList) {
		   searchBox.addItem(log.getUserName());
		}
		final JPanel titlePanel = new JPanel(new BorderLayout());
		titleLabel = new JLabel("Logs", SwingConstants.CENTER);
		titleLabel.setForeground(Color.white);
		titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
		titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
		titlePanel.setBackground(Color.decode("#434343"));
		titlePanel.add(titleLabel, BorderLayout.CENTER);
		
		final JPanel searchFieldPanel = new JPanel(new BorderLayout());
		searchField = new JTextField();
		searchField.setBackground(Color.decode("#4a86e8"));
		searchField.setForeground(Color.white);
		searchField.setFont(new Font("Arial", Font.BOLD, 13));
		searchField.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		searchFieldPanel.add(searchField);
		searchFieldPanel.setBorder(new LineBorder(Color.white, 2, true));
		
		mainLabel = new JLabel("Consultation des messages postés");
		mainLabel.setForeground(Color.white);
		mainLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

		
		final JPanel searchPanel = new JPanel(new BorderLayout(10,0));
		searchPanel.setBackground(Color.decode("#434343"));
		searchPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 10));
		searchPanel.add(mainLabel, BorderLayout.NORTH);
		searchPanel.add(searchFieldPanel, BorderLayout.CENTER);
		searchPanel.add(searchBoxPanel, BorderLayout.EAST);
		
		final JPanel contentPanel = new JPanel(new BorderLayout());
		contentPanel.add(titlePanel, BorderLayout.NORTH);
		contentPanel.add(searchPanel, BorderLayout.CENTER);
		searchField.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String inputItem = searchField.getText();
				List<Log> listLogsByText = logsController.getListLogsByText(inputItem);
				if (listLogsByText != null) {
					resultField.setText("");
					resultField.append("Les Logs : \n \n");
					for (Log log : listLogsByText) {
					    resultField.append(log.getDate()+" :: "+log.getUserName()+" - "+log.getMessage()+"\n \n");
					}
				} else {
					resultField.setText("Aucuns Logs !!!");
				}
			}
		}); 
		
		searchBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String selectedItem = (String) searchBox.getSelectedItem();
				List<Log> listLogsByUser =  logsController.getListLogsByUser(selectedItem);
				if (listLogsByUser != null) {
					resultField.setText("");
					resultField.append("Les Logs : \n \n");
					for (Log log : listLogsByUser) {
					    resultField.append(log.getDate()+" :: "+log.getUserName()+" - "+log.getMessage()+"\n \n");
					}
				} 
			}
		});
		
		return contentPanel;
	}
	
	public JPanel getResultPanel() {
		
		final JPanel resultFieldPanel = new JPanel(new BorderLayout());
		resultField.setEditable(false);
		resultField.setBackground(Color.decode("#4a86e8"));
		resultField.setForeground(Color.white);
		resultField.setFont(new Font("Arial", Font.BOLD, 13));
		resultField.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		resultFieldPanel.add(resultField);
		resultFieldPanel.setBorder(new LineBorder(Color.white, 2, true));
		
		resultLabel = new JLabel("Derniers messages");
		resultLabel.setForeground(Color.white);
		resultLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
		
		final JPanel resultPanel = new JPanel(new BorderLayout());
		resultPanel.setBackground(Color.decode("#434343"));
		resultPanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 20, 10));
		resultPanel.add(resultLabel, BorderLayout.NORTH);
		resultPanel.add(resultFieldPanel, BorderLayout.CENTER);
		
		return resultPanel;
	}
}
