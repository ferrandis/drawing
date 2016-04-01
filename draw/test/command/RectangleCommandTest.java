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

public class RectangleCommandTest {
    private Mockery context = new Mockery();
    private Canvas canvas = context.mock(Canvas.class);

    @Test
    public void canDrawRectangle() throws Exception {

        RectangleCommand rectangleCommand = new RectangleCommand("R 1 3 1 4");
        context.checking(new Expectations(){{
         oneOf(canvas).drawRectangle(new Point(1,3),new Point(1,4));
        } });

        rectangleCommand.draw(Optional.of(canvas));
    }

    @Test
    public void throwInvalidCommanExceptionWhenItDoesnotContainFiveArgs(){
        RectangleCommand rectangleCommand = new RectangleCommand("R 1 3");
        try {
            rectangleCommand.draw(Optional.of(canvas));
            fail();
        } catch (InvalidCommandException e) {
            assertThat(e.getMessage(), equalTo("You need to supply R x1 y1 x2 y2 to draw a rectangle."));
        } catch (Exception e) {
            fail();
        }


    }

    @Test
    public void throwInvalidCommanExceptionWhenPointsAreNoneInteger(){
        RectangleCommand rectangleCommand = new RectangleCommand("R 1 3.4 hello 4");
        try {
            rectangleCommand.draw(Optional.of(canvas));
            fail();
        } catch (InvalidCommandException e) {
            assertThat(e.getMessage(), equalTo("You need to supply R x1 y1 x2 y2 to draw a rectangle. x and y have to be a number."));
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void throwCanvasNotInitialisedExceptionWhenCanvasIsEmpty(){
        RectangleCommand rectangleCommand = new RectangleCommand("R 1 3 4 4");
        try {
            rectangleCommand.draw(Optional.empty());
            fail();
        } catch (InvalidCommandException e) {
            assertThat(e.getMessage(), equalTo("You can't draw a rectangle without drawing a canvas first."));
        } catch (Exception e) {
            fail();
        }
    }
}