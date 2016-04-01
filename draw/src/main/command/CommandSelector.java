package command;

public class CommandSelector {
    public static Command select(String command) throws InvalidCommandException {
        switch (command.split(" ")[0]){
            case "C":
                return new CanvasCommand(command);
            case "L":
                return new LineCommand(command);
            case "R":
                return new RectangleCommand(command);
        }

        throw new InvalidCommandException("This command is not accepted.");
    }
}
