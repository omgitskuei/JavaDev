package main.notes.gui.swing;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class changeButtonText {

	private static ArrayList<Container> allContainers = new ArrayList<Container>();
	
	public static void main(String[] args) {
		final JFrame FRAME = new JFrame();
		FRAME.setTitle("MacroEditor");
		FRAME.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		FRAME.setSize(400, 400);
		FRAME.setMinimumSize(new Dimension(400, 300));
		
		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setLayout(new GridBagLayout());
		JButton button;
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		button = new JButton("Change button text");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actEvent) {
				for(Component eachBtn : allContainers) {
					// eachBtn.set();
				}
			}
		});
		gbc.weightx = 0.5;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 0;
		gbc.gridy = 0;
		allContainers.add(button);
		buttonsPanel.add(button, gbc);

		button = new JButton("Target");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actEvent) {
				JButton a = (JButton) actEvent.getSource();
				a.setText(a.getText() + 1);
			}
		});
		gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 0.5;
		gbc.gridx = 1;
		gbc.gridy = 0;
		allContainers.add(button);
		buttonsPanel.add(button, gbc);
		
		FRAME.add(buttonsPanel);
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				FRAME.setVisible(true);
			}
		});
	}

}
