package creationals.abstracFactory_builder_prototype;

public class InsertCreator implements Creator {
    public SQL createSQL() {
        return new InsertSQL();
    }

    public Mongo createMongo() {
        return new InsertMongo();
    }
}
