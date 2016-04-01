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

public class FillCommandTest {
    private Mockery context = new Mockery();
    private Canvas canvas = context.mock(Canvas.class);

    @Test
    public void canDrawFill() throws Exception {

        FillCommand fillCommand = new FillCommand("B 1 4 o");
        context.checking(new Expectations(){{
         oneOf(canvas).fill(new Point(1,4),'o');
        } });

        fillCommand.draw(Optional.of(canvas));
    }

    @Test
    public void throwInvalidCommanExceptionWhenItDoesnotContainFourArgs(){
        FillCommand fillCommand = new FillCommand("R 1 3");
        try {
            fillCommand.draw(Optional.of(canvas));
            fail();
        } catch (InvalidCommandException e) {
            assertThat(e.getMessage(), equalTo("You need to supply B x y o to fill canvas."));
        } catch (Exception e) {
            fail();
        }


    }

    @Test
    public void throwInvalidCommanExceptionWhenPointsAreNoneInteger(){
        FillCommand fillCommand = new FillCommand("B 3 n b");
        try {
            fillCommand.draw(Optional.of(canvas));
            fail();
        } catch (InvalidCommandException e) {
            assertThat(e.getMessage(), equalTo("You need to supply B x y o to fill. x and y have to be a number."));
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void throwCanvasNotInitialisedExceptionWhenCanvasIsEmpty(){
        FillCommand fillCommand = new FillCommand("B 1 2 o");
        try {
            fillCommand.draw(Optional.empty());
            fail();
        } catch (InvalidCommandException e) {
            assertThat(e.getMessage(), equalTo("You can't draw a fill without drawing a canvas first."));
        } catch (Exception e) {
            fail();
        }
    }
}