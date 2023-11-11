package creationals.abstracFactory_builder_prototype;
/**
 * Creator
 */
public interface Creator {
    public SQL createSQL();
    public Mongo createMongo();
}
