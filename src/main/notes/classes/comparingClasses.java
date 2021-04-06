package main.notes.classes;

import java.awt.AWTException;
import java.awt.Component;
import java.awt.Container;
import java.awt.Robot;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JMenu;

public class comparingClasses {

	public static void main(String[] args) throws AWTException {
		
		Robot r = new Robot();
		// ? is Wildcard generics
		if(r.getClass() instanceof Class<?>) {
			System.out.println("r.getClass() = " + r.getClass());				// class java.awt.Robot
			System.out.println("Robot is a class");
		}
		// One way to compare class
		if(r instanceof Robot) {							// true
			System.out.println("r is an instance of Robot");
		}
		// Another way, albeit clumsier
		if(String.valueOf(r.getClass()).equals("class java.awt.Robot")) {	// true
			System.out.println("another way to compare class");
		}
		// YET Another way
		if(r.getClass() == Robot.class) {					// true
			System.out.println("yet another way to compare class");
		}
	}
	
	
	// a recursive method to get children components from java swing container,
	// where class comparison plays a role
	private static <T extends Component> List<T> getChildren(Class<T> comparedClass, final Container container) {
        Component[] components;
        // Get children
        // JMenu has a different method for getting children components compared to other containers
        if (container instanceof JMenu) {
        	components = ((JMenu) container).getMenuComponents();
        }
        else {
            components = container.getComponents();
        }
        // loop through all children of param container
        List<T> compList = new ArrayList<T>();
        for (Component comp : components) {
        	// determines if the class or interface represented by this Class object is either the same as,
        	// or is a superclass or superinterface of, the class or interface represented by the specified Class parameter.
            if (comparedClass.isAssignableFrom(comp.getClass())) {
                compList.add(comparedClass.cast(comp));
            }
            // This child has children of their own, recursive call method again to get its children
            if (comp instanceof Container) {
            	compList.addAll(
            		getChildren(comparedClass, (Container) comp)
            	);
            }
        }
        return compList;
	}

}
