package command;

import canvas.Canvas;
import canvas.DefaultCanvas;
import org.jmock.Mockery;
import org.junit.Test;

import java.util.Optional;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

public class CanvasCommandTest {
    private Mockery context = new Mockery();
    private Canvas canvas = context.mock(Canvas.class);

    @Test
    public void createACanvas() throws Exception {

        CanvasCommand canvasCommand = new CanvasCommand("C 3 4");

        assertThat(canvasCommand.draw(Optional.empty()).get(), equalTo(new DefaultCanvas(3,4)));
    }

    @Test
    public void resetToANewCanvasWhenCanvasCommandIsCalledAgain() throws Exception {

        CanvasCommand canvasCommand = new CanvasCommand("C 3 4");

        assertThat(canvasCommand.draw(Optional.of(new DefaultCanvas(10,4))).get(), equalTo(new DefaultCanvas(3,4)));
    }
    @Test
    public void throwInvalidCommanExceptionWhenItDoesnotContainThreeArgs(){
        CanvasCommand canvasCommand = new CanvasCommand("C 1");
        try {
            canvasCommand.draw(Optional.of(canvas));
            fail();
        } catch (InvalidCommandException e) {
            assertThat(e.getMessage(), equalTo("Canvas command need width and heigth."));
        } catch (Exception e) {
            fail();
        }


    }

    @Test
    public void throwInvalidCommanExceptionWhenWrongWidthAndHeightSupplied(){
        CanvasCommand canvasCommand = new CanvasCommand("C 1 hello");
        try {
            canvasCommand.draw(Optional.of(canvas));
            fail();
        } catch (InvalidCommandException e) {
            assertThat(e.getMessage(), equalTo("You need to supply C w h to draw a canvas."));
        } catch (Exception e) {
            fail();
        }
    }
}