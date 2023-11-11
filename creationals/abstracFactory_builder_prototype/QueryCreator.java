package creationals.abstracFactory_builder_prototype;

public class QueryCreator implements Creator{
    public SQL createSQL(){
        return new QuerySQL();
    }

    public Mongo createMongo(){
        return new QueryMongo();
    }
}
