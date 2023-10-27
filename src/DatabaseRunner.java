public class DatabaseRunner {

    private static final String URL = "jdbc:postgresql://localhost:5432/zajavka";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "20231021kj";
    private static final String SQL_INSERT
            = "INSERT INTO TODOLIST (NAME, DESCRIPTION, DEADLINE, PRIORITY) VALUES (?, ?, ?, ?);";
    private static final String SQL_UPDATE
            = "UPDATE TODOLIST SET DESCRIPTION = ?, DEADLINE = ?, PRIORITY = ? WHERE NAME = ?;";

    private static final String SQL_READ_WHERE = "SELECT * FROM TODOLIST WHERE NAME = ?;";
    private static final String SQL_READ_ALL = "SELECT * FROM TODOLIST;";
    private static final String SQL_DELETE = "DEETE FROM TODOLIST WHERE = ?;";
    private static final String SQL_DELETE_ALL = "DELETE FROM TODOLIST;";


}
