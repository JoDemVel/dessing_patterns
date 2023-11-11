package creationals.abstracFactory_builder_prototype;

public class DirectorSQLQuery {

    public SQLQueryBuilder makeSQL(String table){
        SQLQueryBuilder sql = new QuerySQL();
        sql.select(new String[]{"id", "name", "age"})
            .from(table)
            .where("age > 18");
        return sql.getResult();
    }
}
