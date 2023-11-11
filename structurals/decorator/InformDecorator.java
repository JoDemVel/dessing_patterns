package structurals.decorator;

public abstract class InformDecorator implements Inform{
    public Inform inform;

    public InformDecorator(Inform inform) {
        this.inform = inform;
    }

    public void generate() {
        inform.generate();
    }

    public void decorate() {
        System.out.println("Decorate Inform");
    }
}
