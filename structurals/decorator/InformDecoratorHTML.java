package structurals.decorator;

public class InformDecoratorHTML extends InformDecorator {
    private Inform inform;

    public InformDecoratorHTML(Inform inform) {
        super(inform);
    }

    public void generate() {
        inform.generate();
        decorate();
    }

    @Override
    public void decorate() {
        System.out.println("Decorate inform with HTML");
    }
}
