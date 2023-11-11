package structurals.flyweight;

import java.util.HashMap;
import java.util.Map;

class CardFlyweight {
    private String name;
    private String description;
    private int value;

    public CardFlyweight(String name, String description, int value) {
        this.name = name;
        this.description = description;
        this.value = value;
    }

    public void showInformation() {
        System.out.println("Card: " + name + ", Description: " + description + ", Value: " + value);
    }
}

class CardManager {
    private Map<String, CardFlyweight> cards;

    public CardManager() {
        cards = new HashMap<>();
    }

    public CardFlyweight getCard(String name) {
        if (!cards.containsKey(name)) {
            CardFlyweight newCard = new CardFlyweight(name, "Default description", 0);
            cards.put(name, newCard);
        }
        return cards.get(name);
    }
}

class Card {
    private CardFlyweight flyweight;
    private String position;
    private String owner;

    public Card(CardFlyweight flyweight, String position, String owner) {
        this.flyweight = flyweight;
        this.position = position;
        this.owner = owner;
    }

    public void showInformation() {
        System.out.println("Position: " + position + ", Owner: " + owner);
        flyweight.showInformation();
    }
}

public class CardGame {
    public static void main(String[] args) {
        CardManager cardManager = new CardManager();

        Card card1 = new Card(cardManager.getCard("PowerfulMagic"), "Hand", "Player1");
        card1.showInformation();

        Card card2 = new Card(cardManager.getCard("PowerfulMagic"), "Board", "Player2");
        card2.showInformation();
    }
}
