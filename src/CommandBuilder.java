import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class CommandBuilder {

    Optional<Command> buildCommand(final String line) {
        String[] split = line.split(";");
        String commandType = split[0];

        if (!Command.Type.valuesAsList().contains(commandType)) {
            System.err.printf("User provided unknown command: [%s]%n" + commandType);
            return Optional.empty();
        }
        List<String> toDoParts = Arrays.asList(split).subList(1, split.length);
        return Optional.empty();

    }

}
