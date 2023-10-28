import java.sql.*;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;

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

    private final Map<Command.Type, Consumer<Command>> EXECUTION_MAP;

    {
        EXECUTION_MAP = Map.of(
                Command.Type.CREATE, this::runAdd,
                Command.Type.DELETE_ALL, this::runDeleteAll

        );
    }

    void run(final Command command) {
        System.out.println("##### RUNNING COMMAND #####");
        Consumer<Command> commandConsumer = Optional.ofNullable(EXECUTION_MAP.get(command.getType()))
                .orElseThrow(() -> new IllegalArgumentException(
                        String.format("Command: [%s] not supported", command.getType())));

        commandConsumer.accept(command);
        System.out.println("##### FINISHED COMMAND ######");
    }

    private void runAdd(final Command command) {
        if (!Command.Type.CREATE.equals(command.getType())) {
            throw new IllegalArgumentException(command.getType().getName());
        }

        try (
                Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
                PreparedStatement statement = connection.prepareStatement(SQL_INSERT);
        ) {
            statement.setString(1, command.getToDoItem().getName());
            statement.setString(2, command.getToDoItem().getDescription());
            statement.setTimestamp(3, Timestamp.valueOf(command.getToDoItem().getDeadline()));
            statement.setInt(4, command.getToDoItem().getPriority());
            int count = statement.executeUpdate();
            System.out.printf("Run [%s] successfully, modified [%s] rows%n", command.getType(), count);

        } catch (SQLException e) {
            System.err.printf("[%s] data error. Message: [%s]%n", command.getType(), e.getMessage());
        }
    }

    private void runDeleteAll(final Command command) {
        if (!Command.Type.DELETE_ALL.equals(command.getType())) {
            throw new IllegalArgumentException(command.getType().getName());
        }

        try (
                Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
                PreparedStatement statement = connection.prepareStatement(SQL_DELETE_ALL);
        ) {

            int count = statement.executeUpdate();
            System.out.printf("Run [%s] successfully, modified [%s] rows%n", command.getType(), count);

        } catch (SQLException e) {
            System.err.printf("[%s] data error. Message: [%s]%n", command.getToDoItem(), e.getMessage());
        }
    }
}
