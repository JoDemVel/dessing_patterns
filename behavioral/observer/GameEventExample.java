package behavioral.observer;

import java.util.ArrayList;
import java.util.List;

// Interface for the Observer (suscriber)
interface GameEventListener {
    void onEventReceived(String event);
}

// Class Publisher, Management of the observers(subscribers)
class GameEventManager {
    private List<GameEventListener> listeners = new ArrayList<>();

    public void subscribe(GameEventListener listener) {
        listeners.add(listener);
    }

    public void unsubscribe(GameEventListener listener) {
        listeners.remove(listener);
    }

    public void notify(String event) {
        for (GameEventListener listener : listeners) {
            listener.onEventReceived(event);
        }
    }
}


class GameEvent {
    private String eventType;

    public GameEvent(String eventType) {
        this.eventType = eventType;
    }

    public String getEventType() {
        return eventType;
    }
}


class Game {
    private GameEventManager eventManager = new GameEventManager();

    public void addEventListener(GameEventListener listener) {
        eventManager.subscribe(listener);
    }

    public void removeEventListener(GameEventListener listener) {
        eventManager.unsubscribe(listener);
    }

    public void simulateGameEvent() {
        // Simulate a game event
        GameEvent event = new GameEvent("EnemySpawned");
        eventManager.notify(event.getEventType());
    }
}

// Observer
class EnemySpawnListener implements GameEventListener {
    @Override
    public void onEventReceived(String event) {
        if (event.equals("EnemySpawned")) {
            System.out.println("An enemy has appeared!");
        }
    }
}

public class GameEventExample {
    public static void main(String[] args) {
        Game game = new Game();
        EnemySpawnListener enemySpawnListener = new EnemySpawnListener();

        // Suspcribe to the game event
        game.addEventListener(enemySpawnListener);

        game.simulateGameEvent();

        game.removeEventListener(enemySpawnListener);
    }
}

