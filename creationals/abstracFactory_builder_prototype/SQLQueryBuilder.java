package creationals.abstracFactory_builder_prototype;

public interface SQLQueryBuilder {
    public void reset();

    public SQLQueryBuilder select(String[] fields);

    public SQLQueryBuilder from(String table);

    public SQLQueryBuilder where(String condition);

    public SQLQueryBuilder groupBy(String[] fields);

    public SQLQueryBuilder having(String condition);

    public SQLQueryBuilder orderBy(String[] fields);

    public SQLQueryBuilder getResult();
}
