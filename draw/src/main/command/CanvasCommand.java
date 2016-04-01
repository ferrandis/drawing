package command;

import canvas.Canvas;
import canvas.DefaultCanvas;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static java.lang.Integer.parseInt;

public class CanvasCommand extends Command {

    public CanvasCommand(String command) {
        super(command);
    }

    @Override
    public Optional<Canvas> draw(Optional<Canvas> canvas) throws InvalidCommandException {
        List<String> canvasCommands = Arrays.asList(command.split(" "));
        if(canvasCommands.size() != 3){
            throw new InvalidCommandException("Canvas command need width and heigth.");
        }
        try{
            return Optional.of(new DefaultCanvas(parseInt(canvasCommands.get(1)), parseInt(canvasCommands.get(2))));
        }catch (NumberFormatException e){
            throw new InvalidCommandException("You need to supply C w h to draw a canvas.");
        }
    }

}
