package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import org.json.JSONArray;

import controller.LoginFormController;
import controller.TchatIndexController;

public class TchatIndex extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JButton submitButton;
	private JTextArea sendMessageField;
	private JScrollPane sendMessagePane;
	private JTextArea canalsField;
	private JTextArea membersField;
	private JScrollPane canalsPane;
	private JScrollPane membersPane;
	private JTextArea messageField;
	private JScrollPane messagePane;
	private JLabel memberLabel;
	private JLabel canalLabel;
	private JLabel titleLabel;
	private Socket client;
	private TchatIndexController tchatController;

	public TchatIndex(TchatIndexController tchatController, Socket client) {
		super("Tchat IRC V0.1 - Index");
		messageField = new JTextArea();
		canalsField = new JTextArea(15, 15);
		membersField = new JTextArea(15, 15);
		this.client = client;
		this.tchatController = tchatController;
		final Container content = getContentPane();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				tchatController.logout();
			}
		});
		content.add(getSendMessagePanel(tchatController), BorderLayout.SOUTH);
		content.add(getCanalPanel(), BorderLayout.EAST);
		content.add(getMessagePanel(), BorderLayout.CENTER);
		this.setMinimumSize(new Dimension(1000, 700));
		this.setSize(1080, 900);
		setVisible(true);
	}

	public JPanel getSendMessagePanel(TchatIndexController tchatIndexController) {
		submitButton = new JButton("Envoyer");
		sendMessageField = new JTextArea(4, 4);
		sendMessagePane = new JScrollPane(sendMessageField);
		sendMessageField.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		sendMessageField.setBackground(Color.decode("#4a86e8"));
		sendMessageField.setForeground(Color.white);
		sendMessageField.setFont(new Font("Arial", Font.BOLD, 13));

		submitButton.setMargin(new Insets(0, 98, 0, 100));
		submitButton.setBackground(Color.decode("#4a86e8"));
		submitButton.setForeground(Color.white);
		submitButton.setFont(new Font("Arial", Font.BOLD, 13));
		submitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

		final JPanel submitPanel = new JPanel(new BorderLayout());
		submitPanel.add(submitButton);
		submitPanel.setBorder(new LineBorder(Color.white, 2, true));

		sendMessagePane.setBorder(new LineBorder(Color.white, 2, true));

		final JPanel sendMessagePanel = new JPanel(new BorderLayout(20, 0));
		sendMessagePanel.setBackground(Color.decode("#434343"));
		sendMessagePanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
		sendMessagePanel.add(sendMessagePane, BorderLayout.CENTER);
		sendMessagePanel.add(submitPanel, BorderLayout.EAST);

		submitButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				String newChannel = sendMessageField.getText().split("(/new)")[1];
				if (sendMessageField.getText().equals("/new" + newChannel) || sendMessageField.getText().equals("/quit")) {
					if (sendMessageField.getText().equals("/new" + newChannel)) {
						tchatController.canalRegistration(newChannel.trim());
					}
					if (sendMessageField.getText().equals("/quit")) {
						tchatController.logout();
						setDefaultCloseOperation(DISPOSE_ON_CLOSE);
						dispose();
						LoginFormController loginController = new LoginFormController();
					}
				} else {
					tchatIndexController.sendMessage(sendMessageField.getText());
					sendMessageField.setText("");
				}

			}
		});

		return sendMessagePanel;
	}

	public JPanel getCanalPanel() {
		canalsField.setEditable(false);
		canalsField.setBackground(Color.decode("#4a86e8"));
		canalsField.setForeground(Color.white);
		canalsField.setFont(new Font("Arial", Font.BOLD, 13));
		canalsField.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		canalsPane = new JScrollPane(canalsField);
		canalsPane.setBackground(Color.decode("#434343"));
		canalsPane.setBorder(new LineBorder(Color.white, 2, true));

		membersField.setEditable(false);
		membersField.setBackground(Color.decode("#4a86e8"));
		membersField.setForeground(Color.white);
		membersField.setFont(new Font("Arial", Font.BOLD, 13));
		membersField.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		membersPane = new JScrollPane(membersField);
		membersPane.setBackground(Color.decode("#434343"));
		membersPane.setBorder(new LineBorder(Color.white, 2, true));

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

	public JTextArea setMessageField(String login, String message) {
		String newligne = System.getProperty("line.separator");
		String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
		messageField.append("[" + timeStamp + "] - <" + login + "> : " + message + newligne + newligne);
		return messageField;
	}

	public JTextArea setCanalField(JSONArray canals) {
		canalsField.setText("");
		String newligne = System.getProperty("line.separator");
		canals.forEach(canal -> {
			canalsField.append(canal.toString() + newligne);
		});
		return canalsField;
	}

	public JTextArea setMembersField(JSONArray members) {
		membersField.setText("");
		String newligne = System.getProperty("line.separator");
		members.forEach(member -> {
			membersField.append("<" + member.toString() + ">" + newligne);
		});
		return membersField;
	}

	public JPanel getMessagePanel() {
		messageField.setEditable(false);
		messagePane = new JScrollPane(messageField);
		messageField.setBackground(Color.decode("#4a86e8"));
		messageField.setForeground(Color.white);
		messageField.setFont(new Font("Arial", Font.BOLD, 13));
		messageField.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		titleLabel = new JLabel("Tchat IRC", SwingConstants.CENTER);
		titleLabel.setForeground(Color.white);
		titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
		titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 10, 10, 10));

		final JPanel titlePanel = new JPanel(new BorderLayout());
		titlePanel.setBackground(Color.decode("#434343"));
		titlePanel.add(titleLabel, BorderLayout.CENTER);

		final JPanel textAreaPanel = new JPanel(new BorderLayout());
		textAreaPanel.setBackground(Color.decode("#434343"));
		textAreaPanel.setBorder(new LineBorder(Color.white, 2, true));
		textAreaPanel.add(messagePane, BorderLayout.CENTER);

		final JPanel messagePanel = new JPanel(new BorderLayout());
		messagePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		messagePanel.setBackground(Color.decode("#434343"));
		messagePanel.add(textAreaPanel, BorderLayout.CENTER);

		final JPanel contentPanel = new JPanel(new BorderLayout());
		contentPanel.add(titlePanel, BorderLayout.NORTH);
		contentPanel.add(messagePanel, BorderLayout.CENTER);

		return contentPanel;
	}
}
