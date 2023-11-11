package creationals.abstracFactory_builder_prototype;

public class Main {
    public static void main(String[] args) {
        QueryCreator qc = new QueryCreator();
        QuerySQL sql = (QuerySQL)(qc.createSQL());
        sql.select(new String[]{"id", "name", "age"})
            .from("users")
            .where("age > 18");
        System.out.println(sql.getSQL());

        DirectorSQLQuery dir = new DirectorSQLQuery();
        QuerySQL sql2 = (QuerySQL)(dir.makeSQL("users"));
        System.out.println(sql2.getSQL());

        QuerySQL sql3 = (QuerySQL)(sql2.clonar());
        sql3.groupBy(new String[]{"age"})
            .having("age > 20")
            .orderBy(new String[]{"age"});
        System.out.println(sql3.getSQL());
    }
}
