package command;

import canvas.Canvas;

import java.awt.*;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static java.lang.Integer.parseInt;

public class LineCommand extends Command {

    public LineCommand(String command) {

        super(command);
    }

    @Override
    public Optional<canvas.Canvas> draw(Optional<Canvas> canvasOptional) throws Exception {
        if(!canvasOptional.isPresent()){
            throw new InvalidCommandException("You can't draw a line without drawing a canvas first.");
        }

        List<String> lineCommands = Arrays.asList(command.split(" "));
        if(lineCommands.size() != 5){
            throw new InvalidCommandException("You need to supply L x1 y1 x2 y2 to draw a line.");
        }

        Canvas canvas = canvasOptional.get();
        try {
            Point start = new Point(parseInt(lineCommands.get(1)), parseInt(lineCommands.get(2)));
            Point end = new Point(parseInt(lineCommands.get(3)), parseInt(lineCommands.get(4)));
            canvas.drawLine(start,end);

        } catch (NumberFormatException e){
            throw new InvalidCommandException("You need to supply L x1 y1 x2 y2 to draw a line. x and y have to be a number.");
        }
        return canvasOptional;
    }


}
