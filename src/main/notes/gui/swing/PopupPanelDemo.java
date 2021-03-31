package main.notes.gui.swing;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

class PopupPanelDemo {
	
	static JFrame f = new JFrame();
	static JLabel l = new JLabel("Date");
	static JTextField t = new JTextField(10);
	static JPanel p = new JPanel();
	static JButton b = new JButton("Show");
	
	public static void main(String ags[]) {
		p.setBorder(BorderFactory.createLineBorder(Color.black, 1));
		p.setBackground(Color.red);
		p.add(new JLabel("Test"));

		f.setLayout(new FlowLayout());
		f.add(l);
		f.add(t);
		f.add(b);

		f.setSize(400, 400);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setLocationRelativeTo(null);
		f.setVisible(true);

		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PopupFactory pf = PopupFactory.getSharedInstance();
				p.setSize(t.getWidth(), t.getHeight());
				p.setPreferredSize(new Dimension(t.getWidth(), t.getHeight()));
				Point l = t.getLocationOnScreen();
				Popup popup = pf.getPopup(f, p, l.x, l.y + t.getHeight());
				popup.show();
			}
		});
	}
}
