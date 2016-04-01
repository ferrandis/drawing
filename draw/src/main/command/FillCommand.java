package command;

import canvas.Canvas;

import java.awt.*;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static java.lang.Integer.parseInt;

public class FillCommand extends Command {

    public FillCommand(String command) {

        super(command);
    }

    @Override
    public Optional<Canvas> draw(Optional<Canvas> canvasOptional) throws Exception {
        if(!canvasOptional.isPresent()){
            throw new InvalidCommandException("You can't draw a fill without drawing a canvas first.");
        }

        List<String> lineCommands = Arrays.asList(command.split(" "));
        if(lineCommands.size() != 4){
            throw new InvalidCommandException("You need to supply B x y o to fill canvas.");
        }

        Canvas canvas = canvasOptional.get();
        try {
            Point point = new Point(parseInt(lineCommands.get(1)), parseInt(lineCommands.get(2)));
            canvas.fill(point, lineCommands.get(3).charAt(0));

        } catch (NumberFormatException e){
            throw new InvalidCommandException("You need to supply B x y o to fill. x and y have to be a number.");
        }
        return canvasOptional;
    }


}
