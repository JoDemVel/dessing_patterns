package behavioral.mediator;

import java.util.ArrayList;
import java.util.List;

// Mediator interface
interface SpecialEffectsMediator {
    void addEffect(SpecialEffect effect);
    void triggerEffect(String effectType);
    void notify(String effectType);
}

// Concrete mediator
class GameSpecialEffectsMediator implements SpecialEffectsMediator {
    private List<SpecialEffect> effects = new ArrayList<>();

    public void addEffect(SpecialEffect effect) {
        effects.add(effect);
        effect.setMediator(this);
    }

    public void triggerEffect(String effectType) {
        for (SpecialEffect effect : effects) {
            if (effect.getType().equals(effectType)) {
                effect.trigger();
            }
        }
    }

    public void notify(String effectType) {
        System.out.println("Notifying other effects about: " + effectType);
        for (SpecialEffect effect : effects) {
            if (!effect.getType().equals(effectType)) {
                effect.notifyEffect();
            }
        }
    }
}

// Base class for all special effects
abstract class SpecialEffect {
    protected SpecialEffectsMediator mediator;
    protected String type;

    public SpecialEffect(String type) {
        this.type = type;
    }

    public void setMediator(SpecialEffectsMediator mediator) {
        this.mediator = mediator;
    }

    public String getType() {
        return type;
    }

    public abstract void trigger();
    public abstract void notifyEffect();
}

// Concrete special effect
class ParticleEffect extends SpecialEffect {
    public ParticleEffect(String type) {
        super(type);
    }

    public void trigger() {
        System.out.println("Triggered particle effect: " + getType());
        mediator.notify(getType());
    }

    public void notifyEffect() {
        System.out.println("Received notification: " + getType());
    }
}

public class GameEffects {
    public static void main(String[] args) {

        SpecialEffectsMediator mediator = new GameSpecialEffectsMediator();

        SpecialEffect particleEffect1 = new ParticleEffect("Explosion");
        SpecialEffect particleEffect2 = new ParticleEffect("Fire");
        SpecialEffect particleEffect3 = new ParticleEffect("Magic");

        mediator.addEffect(particleEffect1);
        mediator.addEffect(particleEffect2);
        mediator.addEffect(particleEffect3);

        // Trigger the explosion effect
        mediator.triggerEffect("Explosion");
    }
}
