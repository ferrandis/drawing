package command;

import canvas.Canvas;

import java.util.Optional;

public class QuitCommand extends Command {

    private final SystemExit systemExit;

    public QuitCommand(String command, SystemExit systemExit) {

        super(command);
        this.systemExit = systemExit;
    }

    @Override
    public Optional<Canvas> draw(Optional<Canvas> canvasOptional) throws Exception {
        systemExit.exit();
        return Optional.empty();
    }


}
