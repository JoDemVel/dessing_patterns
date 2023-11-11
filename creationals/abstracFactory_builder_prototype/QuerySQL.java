package creationals.abstracFactory_builder_prototype;

public class QuerySQL implements SQL, SQLQueryBuilder, Clonable{
    private QuerySQL result;
    private String[] fields;
    private String table;
    private String condition;
    private String[] groupBy;
    private String having;
    private String[] orderBy;

    public QuerySQL() {
    }

    private QuerySQL(QuerySQL q){
        this.result = q.result;
        this.fields = q.fields;
        this.table = q.table;
        this.condition = q.condition;
        this.groupBy = q.groupBy;
        this.having = q.having;
        this.orderBy = q.orderBy;
    }

    public void reset() {
        this.result = new QuerySQL();
    }

    public SQLQueryBuilder select(String[] fields) {
        this.fields = fields;
        return this;
    }

    public SQLQueryBuilder from(String table) {
        this.table = table;
        return this;
    }

    public SQLQueryBuilder where(String condition) {
        this.condition = condition;
        return this;
    }

    public SQLQueryBuilder groupBy(String[] fields) {
        this.groupBy = fields;
        return this;
    }

    public SQLQueryBuilder having(String condition) {
        this.having = condition;
        return this;
    }

    public SQLQueryBuilder orderBy(String[] fields) {
        this.orderBy = fields;
        return this;
    }

    public String getSQL() {
        String sql = "SELECT ";
        for (int i = 0; i < fields.length; i++) {
            sql += fields[i];
            if (i < fields.length - 1) {
                sql += ", ";
            }
        }
        sql += " FROM " + table;
        if (condition != null) {
            sql += " WHERE " + condition;
        }
        if (groupBy != null) {
            sql += " GROUP BY ";
            for (int i = 0; i < groupBy.length; i++) {
                sql += groupBy[i];
                if (i < groupBy.length - 1) {
                    sql += ", ";
                }
            }
        }
        if (having != null) {
            sql += " HAVING " + having;
        }
        if (orderBy != null) {
            sql += " ORDER BY ";
            for (int i = 0; i < orderBy.length; i++) {
                sql += orderBy[i];
                if (i < orderBy.length - 1) {
                    sql += ", ";
                }
            }
        }
        return sql;
    }

    public SQLQueryBuilder getResult() {
        return this;
    }

    public Clonable clonar() {
        return (Clonable)(new QuerySQL(this));
    }
}