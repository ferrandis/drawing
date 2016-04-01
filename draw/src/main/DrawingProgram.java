import canvas.Canvas;
import command.CommandSelector;

import java.io.PrintStream;
import java.util.Optional;

public class DrawingProgram {
    private final PrintStream writer;

    protected Optional<Canvas> canvas = Optional.empty();

    public DrawingProgram(PrintStream writer) {
        this.writer = writer;
    }

    public void enterCommand(String command){
        try {

            canvas = CommandSelector.select(command).draw(canvas);
            canvas.get().drawCanvas(writer);

        } catch (Exception e) {
            writer.println(e.getMessage());
        }

        writer.flush();
    }
}
