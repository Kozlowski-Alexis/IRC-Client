package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class TchatIndex extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JButton submitButton;
	private JTextField sendMessageField;
	private JTextArea canalsField;
	private JTextArea membersField;
	private JScrollPane canalsPane;
	private JScrollPane membersPane;
	private JTextArea messageField;
	private JScrollPane messagePane;
	private JLabel memberLabel;
	private JLabel canalLabel;
	
	public TchatIndex() {
		super("Tchat IRC V0.1");
		final Container content = getContentPane();
		content.add(getSendMessagePanel(), BorderLayout.SOUTH);
		content.add(getCanalPanel(), BorderLayout.EAST);
		content.add(getMessagePanel(), BorderLayout.CENTER);
		this.setSize(1080, 900);
		setVisible(true);
	}
	
	public JPanel getSendMessagePanel() {
		submitButton = new JButton("Envoyer");
		sendMessageField = new JTextField();
		
		final JPanel sendMessagePanel = new JPanel(new BorderLayout(10,0));
		sendMessagePanel.setBackground(Color.decode("#434343"));
		
		submitButton.setMargin(new Insets(0,80,0,100));
		sendMessageField.setMargin(new Insets(35, 0, 0, 0));
		
		sendMessagePanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
		sendMessagePanel.add(sendMessageField, BorderLayout.CENTER);
		sendMessagePanel.add(submitButton, BorderLayout.EAST);
		
		return sendMessagePanel;
	}
	
	public JPanel getCanalPanel() {
		canalsField = new JTextArea(15, 15);
		canalsField.setEditable(false);
		canalsPane = new JScrollPane(canalsField);
		membersField = new JTextArea(15, 15);
		membersField.setEditable(false);
		membersPane = new JScrollPane(membersField);
		memberLabel = new JLabel("Membres sur ce canal");
		canalLabel = new JLabel("Autres canaux");
		memberLabel.setForeground(Color.white);
		canalLabel.setForeground(Color.white);
		
		
		final JPanel canalsMembersPanel = new JPanel();
		canalsMembersPanel.setBackground(Color.decode("#434343"));
		
		canalsMembersPanel.setLayout(new BoxLayout(canalsMembersPanel, BoxLayout.PAGE_AXIS));
		canalsMembersPanel.setBorder(new EmptyBorder(0, 10, 10, 10));
		

		memberLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
		canalLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
		canalsMembersPanel.add(memberLabel);
		canalsMembersPanel.add(membersPane);
		canalsMembersPanel.add(canalLabel);
		canalsMembersPanel.add(canalsPane);
		

		
		return canalsMembersPanel;
	}
	
	public JPanel getMessagePanel() {
		messageField = new JTextArea();
		messageField.setEditable(false);
		messagePane = new JScrollPane(messageField);
		
		final JPanel messagePanel = new JPanel(new BorderLayout());
		messagePanel.setBackground(Color.decode("#434343"));
		
		messagePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		messagePanel.add(messagePane, BorderLayout.CENTER);
		
		return messagePanel;
	}
}
