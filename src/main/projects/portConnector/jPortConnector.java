package main.projects.portConnector;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.ConnectException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class jPortConnector {

	private String APPNAME = "jPortConnector";
	private String VERSION = "2021-05";

	private JFrame FRAME;

	private HashMap<String, GridBagConstraints> componentGBCs;
	private HashMap<String, JLabel> labels;
	private HashMap<String, JTextField> textfields;
	private HashMap<String, JButton> buttons;

//	  UI Layout: 
//	  FRAME 
//	  --> JPanel (frameJPanel, BorderLayout) 
//	  -- --> JPanel (appbodyJPanel, GridBagLayout) 
//	  -- -- // Row 1 
//	  -- -- --> JLabel (ipAddrLabel)
//	  -- -- --> JTextField (ipAddrTextfield) 
//	  -- -- // Row 2 
//	  -- -- --> JLabel (portLabel) 
//	  -- -- --> JTextFiel (portTextfield) 
//	  -- -- // Row 3 
//	  -- -- --> JButton (connBtn) 
//	  -- -- --> JButton (clearBtn) 
//	  -- -- // Row 4 
//	  -- -- --> JScrollPane (scrollPane) 
//	  -- -- -- --> JTextArea (resultTextArea)

	jPortConnector() {
		this.FRAME = new JFrame();

		this.componentGBCs = new HashMap<String, GridBagConstraints>();
		this.labels = new HashMap<String, JLabel>();
		this.textfields = new HashMap<String, JTextField>();
		this.buttons = new HashMap<String, JButton>();
	}

	public static void main(String[] args) {
		jPortConnector app = new jPortConnector();

		app.setLookAndFeel();

		app.setGBCs(app.componentGBCs);
		app.createLabels(app.labels);
		app.createTextfields(app.textfields);
		app.createButtons(app.buttons);

		app.FRAME.setTitle(app.APPNAME + " " + app.VERSION);
		app.FRAME.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		app.FRAME.setAlwaysOnTop(true);

		// Add frameJPanel
		JPanel frameJPanel = new JPanel();
		frameJPanel.setName("frameJPanel");
		frameJPanel.setLayout(new BorderLayout());
		app.FRAME.getContentPane().add(frameJPanel);

		// Add appbodyJPanel
		JPanel appbodyJPanel = new JPanel();
		appbodyJPanel.setLayout(new GridBagLayout());
		frameJPanel.add(appbodyJPanel, BorderLayout.CENTER);

		// Row 1
		// Add ipAddrLabel
		appbodyJPanel.add(app.labels.get("ipAddrLabel"), app.componentGBCs.get("ipAddrLabel"));

		// Add ipAddrTextfield
		appbodyJPanel.add(app.textfields.get("ipAddrTextfield"), app.componentGBCs.get("ipAddrTextfield"));

		// Row 2
		// Add portLabel
		appbodyJPanel.add(app.labels.get("portLabel"), app.componentGBCs.get("portLabel"));

		// Add portTextfield
		appbodyJPanel.add(app.textfields.get("portTextfield"), app.componentGBCs.get("portTextfield"));

		// Row 3
		// Add connBtn
		appbodyJPanel.add(app.buttons.get("connBtn"), app.componentGBCs.get("connBtn"));

		// Add clearBtn
		appbodyJPanel.add(app.buttons.get("clearBtn"), app.componentGBCs.get("clearBtn"));

		// Row 4
		// Add resultTextArea & scrollPane
		JTextArea resultTextArea = new JTextArea();
		resultTextArea.setEditable(false);
		JScrollPane scrollPane = new JScrollPane(resultTextArea);
		scrollPane.setPreferredSize(new Dimension(400, 350));
		appbodyJPanel.add(scrollPane, app.componentGBCs.get("resultTextArea"));

		// Add actionListeners
		app.addActionListeners(app, app.textfields, app.buttons, resultTextArea);

		app.FRAME.pack();

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				app.FRAME.setVisible(true);
			}
		});
	}

	private void addActionListeners(jPortConnector app, HashMap<String, JTextField> appTextfields,
			HashMap<String, JButton> appButtons, JTextArea resultTextArea) {

		ActionListener actListener;

		// IP
		actListener = new ActionListener() {
			public void actionPerformed(ActionEvent actEvent) {
				if (appTextfields.get("ipAddrTextfield").getText().trim().length() > 0) {
					// Validate IP input
					if (app.validateIP(appTextfields.get("ipAddrTextfield").getText())) {
						// Enable next textfield (if it's not already enabled)
						if (appTextfields.get("portTextfield").isEnabled() == false) {
							appTextfields.get("portTextfield").setEnabled(true);
						}
						appTextfields.get("portTextfield").grabFocus();
					} else {
						resultTextArea.setText(resultTextArea.getText() + "IP contains invalid characters." + System.getProperty("line.separator"));
					}
				} else {
					if (appTextfields.get("portTextfield").isEnabled()) {
						appTextfields.get("portTextfield").setEnabled(false);
					}
				}
			}
		};
		appTextfields.get("ipAddrTextfield").addActionListener(actListener);

		// Port
		actListener = new ActionListener() {
			public void actionPerformed(ActionEvent actEvent) {
				if (appTextfields.get("portTextfield").getText().trim().length() > 0) {
					// Validate PORT input
					if (app.validatePORT(appTextfields.get("portTextfield").getText())) {
						// Enable connBtn (if it's not already enabled)
						if (appButtons.get("connBtn").isEnabled() == false) {
							appButtons.get("connBtn").setEnabled(true);
						}
						appButtons.get("connBtn").grabFocus();
					} else {
						resultTextArea.setText(resultTextArea.getText() + "Port contains invalid characters." + System.getProperty("line.separator"));
					}
				} else {
					if (appButtons.get("connBtn").isEnabled()) {
						appButtons.get("connBtn").setEnabled(false);
					}
				}
			}
		};
		appTextfields.get("portTextfield").addActionListener(actListener);

		// Connect button
		actListener = new ActionListener() {
			public void actionPerformed(ActionEvent actEvent) {
				// Get inputs
				String ip = appTextfields.get("ipAddrTextfield").getText();
				String port = appTextfields.get("portTextfield").getText();
				// Enable clearBtn (if disabled)
				if (appButtons.get("clearBtn").isEnabled() == false) {
					appButtons.get("clearBtn").setEnabled(true);
				}
				// Try connecting to the IP:PORT
				resultTextArea
						.setText(resultTextArea.getText() + "Attempting to connect [" + ip + ":" + port + "]..." + System.getProperty("line.separator"));

				Boolean connResult = app.connect(ip, port, resultTextArea);

				if (connResult) {
					try {
						resultTextArea.setText(resultTextArea.getText() + "Connection to ["
								+ InetAddress.getByName(ip).getHostAddress() + ":" + port + "] Success!" + System.getProperty("line.separator"));
					} catch (UnknownHostException e) {
						resultTextArea.setText(resultTextArea.getText()
								+ "UnknownHostException thrown during InetAddress.getByName(ip).getHostAddress()");
					}
				} else {
					resultTextArea.setText(resultTextArea.getText() + "Connection Failed/Rejected." + System.getProperty("line.separator"));
				}
				// Focus next
				appButtons.get("clearBtn").grabFocus();
			}
		};
		appButtons.get("connBtn").addActionListener(actListener);

		actListener = new ActionListener() {
			public void actionPerformed(ActionEvent actEvent) {
				appTextfields.get("ipAddrTextfield").setText("");
				appTextfields.get("portTextfield").setText("");
				appTextfields.get("portTextfield").setEnabled(false);
				appButtons.get("connBtn").setEnabled(false);
				appButtons.get("clearBtn").setEnabled(false);
				appTextfields.get("ipAddrTextfield").grabFocus();
			}
		};
		appButtons.get("clearBtn").addActionListener(actListener);
	}

	private Boolean connect(String ip, String port, JTextArea resultTextArea) {
		try {
			Socket localSocket = new Socket(ip, Integer.parseInt(port));
			localSocket.close();
			return true;
		} catch (ConnectException connE) {
			return false;
		} catch (UnknownHostException e) {
			resultTextArea.setText(resultTextArea.getText() + "Invalid IP." + System.getProperty("line.separator"));
		} catch (NumberFormatException e) {
			resultTextArea.setText(resultTextArea.getText() + "Please type Numbers only for Port." + System.getProperty("line.separator"));
		} catch (Exception e) {
			resultTextArea.setText(resultTextArea.getText() + "Connection Failed with " + e.getClass() + System.getProperty("line.separator"));
		}
		return false;
	}

	private boolean validatePORT(String port) {
		Pattern numeric = Pattern.compile("[^0-9]", Pattern.CASE_INSENSITIVE);
		Matcher m = numeric.matcher(port);
		if (m.find()) {
			return false;
		}
		return true;
	}

	private boolean validateIP(String ip) {
		Pattern alphaNumeric = Pattern.compile("[^a-z0-9.]", Pattern.CASE_INSENSITIVE);
		Matcher m = alphaNumeric.matcher(ip);
		if (m.find()) {
			return false;
		}
		if (!ip.contains(".")) {
			return false;
		}
		return true;
	}

	private void createButtons(HashMap<String, JButton> appButtons) {
		JButton btn;

		btn = new JButton("Connect");
		btn.setEnabled(false);
		btn.setName("connBtn");
		appButtons.put("connBtn", btn);

		btn = new JButton("Clear");
		btn.setEnabled(false);
		btn.setName("clearBtn");
		appButtons.put("clearBtn", btn);
	}

	private void createTextfields(HashMap<String, JTextField> appTextfields) {
		JTextField tf;

		tf = new JTextField();
		tf.setName("ipAddrTextfield");
		tf.setEnabled(true);
		textfields.put("ipAddrTextfield", tf);

		tf = new JTextField();
		tf.setName("portTextfield");
		tf.setEnabled(false);
		textfields.put("portTextfield", tf);
	}

	private void createLabels(HashMap<String, JLabel> appLabels) {
		JLabel label;

		label = new JLabel("Enter IP Address: ");
		label.setName("ipAddrLabel");
//		label.setHorizontalTextPosition(JLabel.LEFT);
//		label.setVerticalTextPosition(JLabel.CENTER);
		appLabels.put("ipAddrLabel", label);

		label = new JLabel("Enter Port: ");
		label.setName("portLabel");
//		label.setHorizontalTextPosition(JLabel.LEFT);
//		label.setVerticalTextPosition(JLabel.CENTER);
		appLabels.put("portLabel", label);
	}

	private void setGBCs(HashMap<String, GridBagConstraints> appGBCs) {
		GridBagConstraints gbc;

		gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
//		gbc.weightx = 1;
//		gbc.weighty = 1;
		appGBCs.put("ipAddrLabel", gbc);

		gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.anchor = GridBagConstraints.NORTHWEST;
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.ipadx = 100;
		gbc.weightx = 1;
		gbc.weighty = 1;
		appGBCs.put("ipAddrTextfield", gbc);

		gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 1;
//		gbc.weightx = 1;
//		gbc.weighty = 1;
		appGBCs.put("portLabel", gbc);

		gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.anchor = GridBagConstraints.NORTHWEST;
		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.ipadx = 100;
		gbc.weightx = 1;
		gbc.weighty = 1;
		appGBCs.put("portTextfield", gbc);

		gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.anchor = GridBagConstraints.NORTHWEST;
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.weightx = 1;
		gbc.weighty = 1;
		appGBCs.put("connBtn", gbc);

		gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.anchor = GridBagConstraints.NORTHWEST;
		gbc.gridx = 1;
		gbc.gridy = 2;
		gbc.gridwidth = 2;
		gbc.weightx = 1;
		gbc.weighty = 1;
		appGBCs.put("clearBtn", gbc);

		gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.gridwidth = 3;
		appGBCs.put("resultTextArea", gbc);
	}

	/**
	 * Set look and feel of the App to current system's native esthetics. If failed,
	 * use default cross-platform Java esthetics
	 * 
	 * @author omgitskuei
	 * @since Apr 7 2021
	 */
	private void setLookAndFeel() {
		try {
			// Set Look&Feel to current System's
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (UnsupportedLookAndFeelException e) {
			try {
				UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
			} catch (Exception e1) {
				System.exit(1);
			}
		} catch (Exception e) {
			System.exit(1);
		}
	}
}
