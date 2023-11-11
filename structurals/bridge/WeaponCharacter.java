package structurals.bridge;

// IMPLEMENTATIONS
interface Weapon {
    void wield();
    void swing();
    void unwield();
}

class Sword implements Weapon {
    @Override
    public void wield() {
        System.out.println("Sword wielded");
    }

    @Override
    public void swing() {
        System.out.println("Sword swung");
    }

    @Override
    public void unwield() {
        System.out.println("Sword unwielded");
    }
}

class Bow implements Weapon {
    @Override
    public void wield() {
        System.out.println("Bow wielded");
    }

    @Override
    public void swing() {
        System.out.println("Bow arrows");
    }

    @Override
    public void unwield() {
        System.out.println("Bow unwielded");
    }
}

// ABSTRACTIONS
abstract class Character {
    protected Weapon weapon;

    public Character(Weapon weapon) {
        this.weapon = weapon;
    }

    public abstract void fight();
}

class Warrior extends Character {
    public Warrior(Weapon weapon) {
        super(weapon);
    }

    @Override
    public void fight() {
        System.out.println("Warrior fights with:");
        weapon.wield();
        weapon.swing();
        weapon.unwield();
    }
}

class Mage extends Character {
    public Mage(Weapon weapon) {
        super(weapon);
    }

    @Override
    public void fight() {
        System.out.println("Mage fights with:");
        weapon.wield();
        weapon.swing();
        weapon.unwield();
    }
}

public class WeaponCharacter {
    public static void main(String[] args) {
        Weapon sword = new Sword();
        Character warrior = new Warrior(sword);
        warrior.fight();

        Weapon bow = new Bow();
        Character mage = new Mage(bow);
        mage.fight();
    }
}
