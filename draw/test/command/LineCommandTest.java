package command;

import canvas.Canvas;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Test;

import java.awt.*;
import java.util.Optional;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

public class LineCommandTest {
    private Mockery context = new Mockery();
    private Canvas canvas = context.mock(Canvas.class);

    @Test
    public void canDrawLine() throws Exception {

        LineCommand lineCommand = new LineCommand("L 1 3 1 4");
        context.checking(new Expectations(){{
         oneOf(canvas).drawLine(new Point(1,3),new Point(1,4));
        } });

        lineCommand.draw(Optional.of(canvas));
    }

    @Test
    public void throwInvalidCommanExceptionWhenItDoesnotContainFiveArgs(){
        LineCommand lineCommand = new LineCommand("L 1 3");
        try {
            lineCommand.draw(Optional.of(canvas));
            fail();
        } catch (InvalidCommandException e) {
            assertThat(e.getMessage(), equalTo("You need to supply L x1 y1 x2 y2 to draw a line."));
        } catch (Exception e) {
            fail();
        }


    }
    @Test
    public void throwInvalidCommanExceptionWhenPointsAreNoneInteger(){
        LineCommand lineCommand = new LineCommand("L 1 3.4 hello 4");
        try {
            lineCommand.draw(Optional.of(canvas));
            fail();
        } catch (InvalidCommandException e) {
            assertThat(e.getMessage(), equalTo("You need to supply L x1 y1 x2 y2 to draw a line. x and y have to be a number."));
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void throwCanvasNotInitialisedExceptionWhenCanvasIsEmpty(){
        LineCommand lineCommand = new LineCommand("L 1 3.4 hello 4");
        try {
            lineCommand.draw(Optional.empty());
            fail();
        } catch (InvalidCommandException e) {
            assertThat(e.getMessage(), equalTo("You can't draw a line without drawing a canvas first."));
        } catch (Exception e) {
            fail();
        }
    }
}