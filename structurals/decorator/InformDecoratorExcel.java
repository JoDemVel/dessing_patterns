package structurals.decorator;

public class InformDecoratorExcel extends InformDecorator{
    private Inform inform;

    public InformDecoratorExcel(Inform inform) {
        super(inform);
    }

    public void generate() {
        inform.generate();
        decorate();
    }

    @Override
    public void decorate() {
        System.out.println("Decorate inform with Excel");
    }
}
