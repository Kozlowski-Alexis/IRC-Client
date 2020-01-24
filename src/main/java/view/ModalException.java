package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class ModalException extends JFrame{
	private JLabel exceptionLabel;
	
	public ModalException(String errorMessage) {
		super("Tchat IRC V0.1 - Erreur !");
		final Container content = getContentPane();
		content.add(getContentPanel(errorMessage), BorderLayout.CENTER);
		this.setSize(600, 250);
		this.setResizable(false);
		setVisible(true);
	}
	
	public JPanel getContentPanel(String errorMessage) {
		exceptionLabel = new JLabel(errorMessage, SwingConstants.CENTER);
		exceptionLabel.setForeground(Color.white);
		
		
		final JPanel exceptionPanel = new JPanel(new BorderLayout());
		exceptionPanel.setBackground(Color.decode("#434343"));
		
		exceptionPanel.add(exceptionLabel, BorderLayout.CENTER);
		
		
		return exceptionPanel;
	}
}
