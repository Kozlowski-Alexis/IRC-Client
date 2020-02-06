package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;


import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;


public class ModalException extends JFrame{
	private JTextPane exceptionPane;
	private JLabel titleLabel;
	
	public ModalException(String errorMessage) {
		super("Tchat IRC V0.1 - Alerte !");
		final Container content = getContentPane();
		content.add(getContentPanel(errorMessage), BorderLayout.CENTER);
		this.setSize(600, 250);
		this.setResizable(false);
		setVisible(true);
	}
	
	public JPanel getContentPanel(String errorMessage) {
		titleLabel = new JLabel("Alerte !", SwingConstants.CENTER);
		titleLabel.setForeground(Color.white);
		titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
		titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
		titleLabel.setBackground(Color.decode("#434343"));
		
		JTextPane exceptionPane = new JTextPane();
		exceptionPane.setText(errorMessage);
		SimpleAttributeSet attribs = new SimpleAttributeSet();
		StyleConstants.setAlignment(attribs, StyleConstants.ALIGN_CENTER);
		StyleConstants.setForeground(attribs, Color.white);
		exceptionPane.setParagraphAttributes(attribs, true);
		exceptionPane.setEditable(false);
		exceptionPane.setBackground(Color.decode("#434343"));
		exceptionPane.setFont(new Font("Arial", Font.BOLD, 13));
		exceptionPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		final JPanel exceptionPanel = new JPanel(new BorderLayout());
		exceptionPanel.setBackground(Color.decode("#434343"));
		exceptionPanel.add(titleLabel, BorderLayout.NORTH);
		exceptionPanel.add(exceptionPane, BorderLayout.CENTER);
		
		
		return exceptionPanel;
	}
}
