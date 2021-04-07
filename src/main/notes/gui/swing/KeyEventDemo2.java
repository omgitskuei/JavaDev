package main.notes.gui.swing;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
 
import javax.swing.JFrame;
import javax.swing.JTextField;

public class KeyEventDemo2 {

	public static void main(String args[]) {
        JFrame frame = new JFrame("Key Listener");
        Container contentPane = frame.getContentPane();
        KeyListener listener = new KeyListener() {
        	/** Handle the key typed event from the text field. */
            public void keyTyped(KeyEvent e) {
            	printEventInfo("KEY TYPED: ", e);
            }

            /** Handle the key-pressed event from the text field. */
            public void keyPressed(KeyEvent e) {
            	printEventInfo("KEY PRESSED: ", e);
            }

            /** Handle the key-released event from the text field. */
            public void keyReleased(KeyEvent e) {
            	printEventInfo("KEY RELEASED: ", e);
            }
            private void printEventInfo(String str, KeyEvent e) {
                System.out.println(str);
                
                System.out.println("    KeyEvent id = " + e.getID());
                System.out.println((e.getID() == KeyEvent.KEY_TYPED) ? 
                    "    KeyChar: " +  e.getKeyChar()
                        : "    KeyText: " + KeyEvent.getKeyText(e.getKeyCode()));
                
                int modifiersEx = e.getModifiersEx();
                String tmpString = KeyEvent.getModifiersExText(modifiersEx);
                String modString = (tmpString.length() > 0) ? 
                	"    Extended modifiers = " + modifiersEx + " (" + tmpString + ")" 
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
                System.out.println("    Action Key: " + (e.isActionKey()?"YES":"NO"));
            }
        };
        JTextField textField = new JTextField();
        textField.addKeyListener(listener);
        contentPane.add(textField, BorderLayout.NORTH);
        frame.pack();
        frame.setVisible(true);
    }

}
