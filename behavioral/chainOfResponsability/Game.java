package behavioral.chainOfResponsability;

interface InteractionHandler {
    void handleInteraction(GameObject gameObject);
    void setNextHandler(InteractionHandler nextHandler);
}

class DoorInteractionHandler implements InteractionHandler {
    private InteractionHandler nextHandler;

    @Override
    public void handleInteraction(GameObject gameObject) {
        if (gameObject instanceof Door) {
            System.out.println("You have interacted with a door.");
        } else if (nextHandler != null) {
            nextHandler.handleInteraction(gameObject);
        }
    }

    @Override
    public void setNextHandler(InteractionHandler nextHandler) {
        this.nextHandler = nextHandler;
    }
}

class ChestInteractionHandler implements InteractionHandler {
    private InteractionHandler nextHandler;

    @Override
    public void handleInteraction(GameObject gameObject) {
        if (gameObject instanceof Chest) {
            System.out.println("You have interacted with a chest.");
        } else if (nextHandler != null) {
            nextHandler.handleInteraction(gameObject);
        }
    }

    @Override
    public void setNextHandler(InteractionHandler nextHandler) {
        this.nextHandler = nextHandler;
    }
}

class WeaponInteractionHandler implements InteractionHandler {
    private InteractionHandler nextHandler;

    @Override
    public void handleInteraction(GameObject gameObject) {
        if (gameObject instanceof Weapon) {
            System.out.println("You have interacted with a weapon.");
        } else if (nextHandler != null) {
            nextHandler.handleInteraction(gameObject);
        }
    }

    @Override
    public void setNextHandler(InteractionHandler nextHandler) {
        this.nextHandler = nextHandler;
    }
}

abstract class GameObject {
    protected String name;

    public GameObject(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

class Door extends GameObject {
    public Door(String name) {
        super(name);
    }
}

class Chest extends GameObject {
    public Chest(String name) {
        super(name);
    }
}

class Weapon extends GameObject {
    public Weapon(String name) {
        super(name);
    }
}

public class Game {
    public static void main(String args[]){
        InteractionHandler doorHandler = new DoorInteractionHandler();
        InteractionHandler chestHandler = new ChestInteractionHandler();
        InteractionHandler weaponHandler = new WeaponInteractionHandler();

        doorHandler.setNextHandler(chestHandler);
        chestHandler.setNextHandler(weaponHandler);

        GameObject door = new Door("Main door");
        GameObject chest = new Chest("Magic chest");
        GameObject door2 = new Door("Secondary door");
        GameObject weapon = new Weapon("Excalibur");

        doorHandler.handleInteraction(door);
        doorHandler.handleInteraction(door2);
        doorHandler.handleInteraction(weapon);
        doorHandler.handleInteraction(chest);
    }
}