package structurals.decorator;

public class Main {
    public static void main(String[] args) {
        Inform inf1 = new InformPDF();
        InformDecorator id1 = new InformDecoratorHTML(inf1);
        InformDecorator id2 = new InformDecoratorExcel(id1);

        inf1.generate();
        id1.decorate();
        id2.decorate();
    }
}
