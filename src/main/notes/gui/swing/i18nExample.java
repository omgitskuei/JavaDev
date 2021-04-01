package main.notes.gui.swing;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

// https://stackoverflow.com/questions/58234116/refresh-language-of-swing-elements-at-runtime

public class i18nExample extends JFrame {
    private static final long serialVersionUID = 1L;

    public i18nExample() {
        super("test");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout());
        add(new MainPanel());

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private class MainPanel extends JPanel implements LocaleChangeable {
        private JLabel label;
        private JButton changeLocaleButton;

        public MainPanel() {
            super(new FlowLayout());
            label = new JLabel(Locale.ENGLISH.toString());
            add(label);

            changeLocaleButton = new JButton("Change Locale");
            changeLocaleButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
				    broadcastLocaleChange(Locale.CANADA);
				}
			});
            add(changeLocaleButton);
        }

        @Override
        public void localeChanged(Locale newLocale) {
            label.setText(newLocale.toString());
            System.out.println("Language changed.");
        }

//        private void broadcastLocaleChange(Locale locale) {
//            List<Component> components = getChildren(Component.class, LocaleTest.this);
//            components.stream().filter(LocaleChangeable.class::isInstance).map(LocaleChangeable.class::cast)
//                    .forEach(lc -> lc.localeChanged(locale));
//        }
        private void broadcastLocaleChange(Locale locale) {
            List<Component> components = getChildren(Component.class, i18nExample.this);
            //System.out.println(components);
            for(Component c : components) {
            	System.out.println(c.getClass());
            	System.out.println(c.getName());
            	System.out.println(c.getX());
            	System.out.println(c.getY());
            	System.out.println(c.getAlignmentX());
            	System.out.println(c.getAlignmentY());
            	System.out.println(c.getHeight());
            	System.out.println(c.getWidth());
            	System.out.println(c.getLocale());
            	c.setLocale(Locale.US);
            	System.out.println(c.getLocale());
            	System.out.println(c);
            	if(c.getClass() == JLabel.class) {
            		JLabel b = (JLabel) c;
            		b.setText("asd");
            	} else if (c.getClass() == JButton.class) {
            		JButton p = (JButton) c;
            		p.setText("asdasd");
            	}
            }
        }
    }

    private static <T extends Component> List<T> getChildren(Class<T> clazz, final Container container) {
        Component[] components;
        if (container instanceof JMenu)
            components = ((JMenu) container).getMenuComponents();
        else
            components = container.getComponents();
        List<T> compList = new ArrayList<T>();
        for (Component comp : components) {
            if (clazz.isAssignableFrom(comp.getClass())) {
                compList.add(clazz.cast(comp));
            }
            if (comp instanceof Container)
                compList.addAll(getChildren(clazz, (Container) comp));
        }
        return compList;
    }

    public static interface LocaleChangeable {
        void localeChanged(Locale newLocale);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new i18nExample().setVisible(true);
			}
		});
    }
}
