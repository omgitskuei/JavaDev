package references.javaCoreFeatures;

import references.javaCoreFeatures.UsingInnerClasses.Chestpiece;
import references.javaCoreFeatures.UsingInnerClasses.Helmet;
import references.javaCoreFeatures.UsingInnerClasses.Weapon;

// An inner class can also be static, which means that you can access it without creating an object of the outer class
// One advantage of inner classes, is that they can access attributes and methods of the outer class

// Class
class Hero {
	// Class fields
	String name = "";
	int level = 0;
	Equipment equipment = new Equipment(Helmet.None,Chestpiece.None, Weapon.None, Weapon.None);
	String title = "";

	// Inner-Class
	class Equipment {
		Helmet head = Helmet.None;
		Chestpiece chest = Chestpiece.None;
		Weapon leftHand = Weapon.None;
		Weapon rightHand = Weapon.None;

		// Inner-Class Constructors
		public Equipment() {}
		public Equipment(Helmet helm, Chestpiece chest, Weapon lWeapon, Weapon rWeapon) {
			this.head = helm;
			this.chest = chest;
			this.leftHand = lWeapon;
			this.rightHand = rWeapon;
		}
		
		// Inner-Class method
		public void clearEquipment() {
			this.head = Helmet.None;
			this.chest = Chestpiece.None;
			this.leftHand = Weapon.None;
			this.rightHand = Weapon.None;
		}
	}

	// Class Constructor
	public Hero(String name, int level) {
		System.out.println("<Creating new hero; " + name + ", level " + level + ">");
		this.name = name;
		this.level = level;
	}

	// Class methods
	public void printStats() {
		System.out.println("- - - - - - - - printStats()- - - - - - - - ");
		String printName = name + (title=="" ? "" : ", "+title);		// if (title!="") { name, title }
		System.out.println("Hero Name: " + printName);
		System.out.println("Hero Level: " + level);
		System.out.println("Equipment:");
		System.out.println("^ Head:        " + this.equipment.head);
		System.out.println("^ Chest:       " + this.equipment.chest);
		System.out.println("^ Left-Hand:   " + this.equipment.leftHand);
		System.out.println("^ Right-Hand:  " + this.equipment.rightHand);
		System.out.println("- - - - - - - - - - - - - - - - - - - - - - ");
	}
	
	public void levelUp() {
		this.level = this.level+1;
	}

	public void setTitle(String string) {
		this.title = string;
	}

}

public class UsingInnerClasses {
	enum Helmet {
		None, CopperHelmet, IronHelmet, LeatherCap,
	}

	enum Chestpiece {
		None, CopperPlatemail
	}

	enum Weapon {
		None, CopperSword, Shortsword
	}

	// Entry point
	public static void main(String[] args) {
		// New hero
		Hero kuei = new Hero("Kuei", 5);
		// Set equipment
		Hero.Equipment kueiEquipment = kuei.new Equipment(Helmet.IronHelmet,Chestpiece.CopperPlatemail, Weapon.None, Weapon.Shortsword);
		kuei.equipment = kueiEquipment;
		kuei.printStats();
		
		kuei.setTitle("the Diplomat");
		kuei.levelUp();
		kuei.equipment.clearEquipment();
		kueiEquipment.rightHand = Weapon.CopperSword;
		kuei.equipment = kueiEquipment;
		kuei.printStats();
	}
}