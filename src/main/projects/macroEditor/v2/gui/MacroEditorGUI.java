package main.projects.macroEditor.v2.gui;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class MacroEditorGUI {

	private JMenu initJMenu(String name, String label, ImageIcon icon) {
		JMenu jMenu = new JMenu(label);
		jMenu.setName(name);
		jMenu.setIcon(icon);
		return jMenu;
	}

	private JMenuItem initJMenuItem(String name, String label, ActionListener action, ImageIcon icon) {
		JMenuItem jMenuItem = new JMenuItem(label);
		jMenuItem.setName(name);
		jMenuItem.addActionListener(action);
		jMenuItem.setIcon(icon);
		return jMenuItem;
	}

	private JRadioButtonMenuItem initRadioMenuItem(String name, String label, ActionListener action, ImageIcon icon) {
		JRadioButtonMenuItem radio = new JRadioButtonMenuItem(label, true);
		radio.setActionCommand(name);
		radio.addActionListener(action);
		radio.setName(name);
		radio.setIcon(icon);
		return radio;
	}

	private ImageIcon initIcon(String path, int height, int width) {
		ImageIcon imageIcon = new ImageIcon(path);
		Image image = imageIcon.getImage(); // transform it
		Image newimg = image.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
		imageIcon = new ImageIcon(newimg); // transform it back
		return imageIcon;
	}
	
	public JMenuBar initJMenuBar(HashMap<String, String> labels, HashMap<String, String> iconPaths,
			HashMap<String, ActionListener> actions) {
		JMenuBar menu = new JMenuBar();
		JMenu file = initJMenu("menuBar_File", labels.get("menuBar_File"),
				initIcon(iconPaths.get("menuBar_File"), 20, 20));
		file.add(initJMenuItem("menuBar_File_Open", labels.get("menuBar_File_Open"), actions.get("menuBar_File_Open"),
				initIcon(iconPaths.get("menuBar_File_Open"), 20, 20)));
		file.add(initJMenuItem("menuBar_File_SaveAs", labels.get("menuBar_File_SaveAs"),
				actions.get("menuBar_File_SaveAs"), initIcon(iconPaths.get("menuBar_File_SaveAs"), 20, 20)));
		menu.add(file);

		JMenu help = initJMenu("menuBar_Help", labels.get("menuBar_Help"),
				initIcon(iconPaths.get("menuBar_Help"), 20, 20));
		help.add(initJMenuItem("menuBar_Help_About", labels.get("menuBar_Help_About"),
				actions.get("menuBar_Help_About"), initIcon(iconPaths.get("menuBar_Help_About"), 20, 20)));
		menu.add(help);

		JMenu prefs = initJMenu("menuBar_Prefs", labels.get("menuBar_Prefs"),
				initIcon(iconPaths.get("menuBar_Prefs"), 20, 20));

		prefs.add(initJMenuItem("menuBar_Prefs_Debug", labels.get("menuBar_Prefs_Debug"),
				actions.get("menuBar_Prefs_Debug"), initIcon(iconPaths.get("menuBar_Prefs_Debug"), 20, 20)));

		JMenu prefs_langs = initJMenu("menuBar_Prefs_Lang", labels.get("menuBar_Prefs_Lang"),
				initIcon(iconPaths.get("menuBar_Prefs_Lang"), 20, 20));
		ButtonGroup langRadioGrp = new ButtonGroup();
		JRadioButtonMenuItem prefs_langs_en = initRadioMenuItem("menuBar_Prefs_Lang_En", "English",
				actions.get("menuBar_Prefs_Lang_En"), initIcon(iconPaths.get("menuBar_Prefs_Lang_En"), 20, 20));
		JRadioButtonMenuItem prefs_langs_cn = initRadioMenuItem("menuBar_Prefs_Lang_Cn", "Chinese",
				actions.get("menuBar_Prefs_Lang_Cn"), initIcon(iconPaths.get("menuBar_Prefs_Lang_Cn"), 20, 20));
		langRadioGrp.add(prefs_langs_en);
		langRadioGrp.add(prefs_langs_cn);
		prefs_langs.add(prefs_langs_en);
		prefs_langs.add(prefs_langs_cn);
		prefs.add(prefs_langs);
		menu.add(prefs);
		return menu;
	}

	public GridBagConstraints createGridBagConstraint(
			Integer anchorX, Integer anchorY, 
			Integer spanX, Integer spanY,
			Boolean horizontalFill,
			Boolean verticalFill) {
		GridBagConstraints gc = new GridBagConstraints();
		// Specify the row and column at the upper left of the component.
		// The leftmost column is gridx=0.
		// The top row is gridy=0.
		gc.weightx = 1;
		gc.weighty = 1;
		gc.gridx = anchorX;
		gc.gridy = anchorY;
		// Specify the number of cells the component uses
		if(spanX != null) {
			gc.gridwidth = spanX;
		}
		if(spanY != null) {
			gc.gridheight = spanY;
		}
		// Valid GridBagConstraints constants include:
		// NONE (the default), 
		// HORIZONTAL (fill its display area horizontally),
		// VERTICAL (fill its display area vertically), 
		// BOTH (make the component fill its display area entirely)
		if(horizontalFill && verticalFill) {
			gc.fill = GridBagConstraints.BOTH;
		} else if(horizontalFill) {
			gc.fill = GridBagConstraints.HORIZONTAL;
		} else if(verticalFill) {
			gc.fill = GridBagConstraints.VERTICAL;
		}
		// Specifies the external padding of the component
//		gc.insets = new Insets(10, 10, 10, 10);
		return gc;
	}

	public JTextField initJTextField(Boolean enabled, Boolean focus) {
		KeyListener listener = new KeyListener() {
			@Override
			public void keyPressed(KeyEvent event) {
				printEventInfo("Key Pressed", event);
			}
			@Override
			public void keyReleased(KeyEvent event) {
				printEventInfo("Key Released", event);
			}
			@Override
			public void keyTyped(KeyEvent event) {
				printEventInfo("Key Typed", event);
			}
			private void printEventInfo(String str, KeyEvent e) {
				System.out.println(str);
				System.out.println("    KeyEvent id = " + e.getID());
				System.out.println((e.getID() == KeyEvent.KEY_TYPED) ? 
						"    KeyChar: " + e.getKeyChar()
						: "    KeyText: " + KeyEvent.getKeyText(e.getKeyCode()));

				int modifiersEx = e.getModifiersEx();
				String tmpString = KeyEvent.getModifiersExText(modifiersEx);
				String modString = (tmpString.length() > 0)
						? "    Extended modifiers = " + modifiersEx + " (" + tmpString + ")"
						: "    Extended modifiers = " + modifiersEx + " (no extended modifiers)";
				System.out.println(modString);

				String stringKeyLocation;
				switch (e.getKeyLocation()) {
				case KeyEvent.KEY_LOCATION_RIGHT:
					stringKeyLocation = "Right";
				case KeyEvent.KEY_LOCATION_LEFT:
					stringKeyLocation = "Left";
				case KeyEvent.KEY_LOCATION_NUMPAD:
					stringKeyLocation = "NumPad";
				case KeyEvent.KEY_LOCATION_STANDARD:
					stringKeyLocation = "Standard";
				case KeyEvent.KEY_LOCATION_UNKNOWN:
				default:
					stringKeyLocation = "Unknown";
				}
				System.out.println("    Location: " + stringKeyLocation);
				System.out.println("    Action Key: " + (e.isActionKey() ? "YES" : "NO"));
			}
		};

//		FRAME.getContentPane().add(BorderLayout.WEST, new JButton("WEST"));
		JTextField textFieldInput = new JTextField(30);
		textFieldInput.setEnabled(enabled);
		textFieldInput.addKeyListener(listener);
		if(focus) {
			textFieldInput.requestFocusInWindow();
		}
		// this enables capturing tabs as keyEvent, as opposed to using tab to traverse
		// to next component
		textFieldInput.setFocusTraversalKeysEnabled(false);

		return textFieldInput;
	}

	public int applyLookAndFeel() {
		int exitCode = 200;
		try {
			// Set Look&Feel to current System's
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (UnsupportedLookAndFeelException e) {
			// Default to CrossPlatformLookAndFeel if System's fails
			System.err.println(
					"An UnsupportedLookAndFeelException error occurred while setting LookAndFeel to current sysL&F.");
			try {
				UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
			} catch (UnsupportedLookAndFeelException e1) {
				exitCode = 400;
				System.err.println(
						"An UnsupportedLookAndFeelException error occurred while setting LookAndFeel to crossPlatformL&F.");
			} catch (ClassNotFoundException e1) {
				exitCode = 400;
				System.err.println(
						"An ClassNotFoundException error occurred while setting LookAndFeel to crossPlatformL&F.");
			} catch (InstantiationException e1) {
				exitCode = 400;
				System.err.println(
						"An InstantiationException error occurred while setting LookAndFeel to crossPlatformL&F.");
			} catch (IllegalAccessException e1) {
				exitCode = 400;
				System.err.println(
						"An IllegalAccessException error occurred while setting LookAndFeel to crossPlatformL&F.");
			}
		} catch (ClassNotFoundException e) {
			exitCode = 404;
			System.err.println("An ClassNotFoundException error occurred while setting LookAndFeel to current sysL&F.");
		} catch (InstantiationException e) {
			exitCode = 404;
			System.err.println("An InstantiationException error occurred while setting LookAndFeel to current sysL&F.");
		} catch (IllegalAccessException e) {
			exitCode = 404;
			System.err.println("An IllegalAccessException error occurred while setting LookAndFeel to current sysL&F.");
		}
		return exitCode;
	}
}
