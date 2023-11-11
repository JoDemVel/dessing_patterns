package behavioral.command;

/**
 * COMMANDS
 */
interface Command {
    void execute();
}

// Concrete Commands
class JumpCommand implements Command {
    private Player player;

    public JumpCommand(Player player) {
        this.player = player;
    }

    public void execute() {
        player.jump();
    }
}

class ShootCommand implements Command {
    private Player player;

    public ShootCommand(Player player) {
        this.player = player;
    }

    public void execute() {
        player.shoot();
    }
}

class MoveCommand implements Command {
    private Player player;
    private int direction;

    public MoveCommand(Player player, int direction) {
        this.player = player;
        this.direction = direction;
    }

    public void execute() {
        player.move(direction);
    }
}

/**
 * RECEIVER
 */
// Player class that will receive and execute the commands
class Player {
    public void jump() {
        System.out.println("Player jumps");
    }

    public void shoot() {
        System.out.println("Player shoots");
    }

    public void move(int direction) {
        System.out.println("Player moves in direction: " + direction);
    }
}

/**
 * INVOKER
 */
// This class invoker will be in charge of executing the commands
class InputHandler {
    private Command jumpCommand;
    private Command shootCommand;
    private Command moveCommand;

    public InputHandler(Command jumpCommand, Command shootCommand, Command moveCommand) {
        this.jumpCommand = jumpCommand;
        this.shootCommand = shootCommand;
        this.moveCommand = moveCommand;
    }

    public void jumpPressed() {
        jumpCommand.execute();
    }

    public void shootPressed() {
        shootCommand.execute();
    }

    public void movePressed(int direction) {
        moveCommand.execute();
    }
}

public class Game {
    public static void main(String[] args) {
        Player player = new Player();
        Command jump = new JumpCommand(player);
        Command shoot = new ShootCommand(player);
        Command moveLeft = new MoveCommand(player, -1);

        InputHandler inputHandler = new InputHandler(jump, shoot, moveLeft);

        // Simulate user input
        inputHandler.jumpPressed();
        inputHandler.shootPressed();
        inputHandler.movePressed(-1);
    }
}
