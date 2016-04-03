import command.InvalidCommandException;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class DrawingProgramTest {
    @Test
    public void canDrawACanvas() throws InvalidCommandException {

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        DrawingProgram drawingProgram = new DrawingProgram(new PrintStream(outputStream));
        drawingProgram.enterCommand("C 5 2");

        String expectedCanvas = "-------\n"
                + "|     |\n"
                + "|     |\n"
                + "-------\n";

        assertThat(outputStream.toString(), equalTo(expectedCanvas));

    }

    @Test
    public void canDrawAVerticalLine() throws InvalidCommandException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        DrawingProgram drawingProgram = new DrawingProgram(new PrintStream(outputStream));
        drawingProgram.enterCommand("C 10 4");
        outputStream.reset();

        drawingProgram.enterCommand("L 6 2 6 3");

        String expectedCanvas = "------------\n"
                + "|          |\n"
                + "|     x    |\n"
                + "|     x    |\n"
                + "|          |\n"
                + "------------\n";

        assertThat(outputStream.toString(), equalTo(expectedCanvas));

    }

    @Test
    public void canDrawAnHorizontalLine() throws InvalidCommandException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        DrawingProgram drawingProgram = new DrawingProgram(new PrintStream(outputStream));
        drawingProgram.enterCommand("C 10 4");

        outputStream.reset();
        drawingProgram.enterCommand("L 1 2 6 2");
        String expectedCanvas = "------------\n"
                + "|          |\n"
                + "|xxxxxx    |\n"
                + "|          |\n"
                + "|          |\n"
                + "------------\n";

        assertThat(outputStream.toString(), equalTo(expectedCanvas));

    }

    @Test
    public void canDrawARectangle() throws InvalidCommandException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        DrawingProgram drawingProgram = new DrawingProgram(new PrintStream(outputStream));
        drawingProgram.enterCommand("C 10 4");

        outputStream.reset();
        drawingProgram.enterCommand("R 1 2 6 4");
        String expectedCanvas = "------------\n"
                + "|          |\n"
                + "|xxxxxx    |\n"
                + "|x    x    |\n"
                + "|xxxxxx    |\n"
                + "------------\n";

        assertThat(outputStream.toString(), equalTo(expectedCanvas));

    }

    @Test
    public void canFill() throws InvalidCommandException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        DrawingProgram drawingProgram = new DrawingProgram(new PrintStream(outputStream));
        drawingProgram.enterCommand("C 10 4");

        drawingProgram.enterCommand("R 1 2 6 4");

        outputStream.reset();

        drawingProgram.enterCommand("B 2 3 o");
        String expectedCanvas = "------------\n"
                + "|          |\n"
                + "|xxxxxx    |\n"
                + "|xoooox    |\n"
                + "|xxxxxx    |\n"
                + "------------\n";

        assertThat(outputStream.toString(), equalTo(expectedCanvas));

        outputStream.reset();

        drawingProgram.enterCommand("B 1 1 *");
        expectedCanvas = "------------\n"
                + "|**********|\n"
                + "|xxxxxx****|\n"
                + "|xoooox****|\n"
                + "|xxxxxx****|\n"
                + "------------\n";

        assertThat(outputStream.toString(), equalTo(expectedCanvas));

    }

    @Test
    public void canRefill() throws InvalidCommandException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        DrawingProgram drawingProgram = new DrawingProgram(new PrintStream(outputStream));
        drawingProgram.enterCommand("C 10 4");

        drawingProgram.enterCommand("R 1 2 6 4");

        outputStream.reset();

        drawingProgram.enterCommand("B 2 3 o");
        String expectedCanvas = "------------\n"
                + "|          |\n"
                + "|xxxxxx    |\n"
                + "|xoooox    |\n"
                + "|xxxxxx    |\n"
                + "------------\n";

        assertThat(outputStream.toString(), equalTo(expectedCanvas));

        outputStream.reset();

        drawingProgram.enterCommand("B 2 3 *");
        expectedCanvas = "------------\n"
                + "|          |\n"
                + "|xxxxxx    |\n"
                + "|x****x    |\n"
                + "|xxxxxx    |\n"
                + "------------\n";

        assertThat(outputStream.toString(), equalTo(expectedCanvas));

    }

    @Test
    public void startAndEndPointsShouldContainTheSameXsOrYs() throws InvalidCommandException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        DrawingProgram drawingProgram = new DrawingProgram(new PrintStream(outputStream));
        drawingProgram.enterCommand("C 20 4");
        outputStream.reset();

        drawingProgram.enterCommand("L 1 2 6 4");

        String expectedMessage = "You can only draw an horizontal or a vertical line here.\n"
                + "Please supply the same Xs or the same Ys in your points.\n";

        assertThat(outputStream.toString(), equalTo(expectedMessage));

    }

    @Test
    public void startAndEndPointsShouldBeWithinCanvasRange() throws InvalidCommandException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        DrawingProgram drawingProgram = new DrawingProgram(new PrintStream(outputStream));
        drawingProgram.enterCommand("C 20 4");
        outputStream.reset();

        drawingProgram.enterCommand("L 25 2 25 4");

        String expectedMessage = "You can't draw a line bigger than the canvas.\n";

        assertThat(outputStream.toString(), equalTo(expectedMessage));


    }

}