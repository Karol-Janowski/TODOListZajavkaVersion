import java.util.Scanner;

public class ScannerRunner {
    public static void main(String[] args) {
        System.out.println("Write command: ");
        Scanner console = new Scanner(System.in);

        CommandBuilder commandBuilder = new CommandBuilder();
        DatabaseRunner databaseRunner = new DatabaseRunner();

        while (console.hasNext()) {
            String stringCommand = console.nextLine();
            commandBuilder.buildCommand(stringCommand)
                    .ifPresent(databaseRunner::run);
        }
    }
}
