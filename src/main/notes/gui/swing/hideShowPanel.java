package main.notes.gui.swing;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class hideShowPanel {

	public static void main(String[] args) {
		JFrame f = new JFrame();
		f.setLayout(new BorderLayout());
		final JPanel p = new JPanel();
		p.add(new JLabel("Panel 1"));
		f.add(p, BorderLayout.NORTH);

		// create a button which will hide the panel when clicked.
		JButton b = new JButton("HIDE");
		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				p.setVisible(false);
			}
		});
		f.add(b, BorderLayout.CENTER);

		b = new JButton("SHOW");
		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				p.setVisible(true);
			}
		});
		f.add(b, BorderLayout.SOUTH);

		f.pack();
		f.setVisible(true);
	}

}
