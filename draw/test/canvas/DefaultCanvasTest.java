package canvas;

import org.junit.Test;

import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

public class DefaultCanvasTest {

    @Test
    public void canDrawACanvas(){
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        DefaultCanvas canvas = new DefaultCanvas(5, 2);

        canvas.drawCanvas(new PrintStream(outputStream));
        String expectedCanvas = "-------\n"
                + "|     |\n"
                + "|     |\n"
                + "-------\n";

        assertThat(outputStream.toString(), equalTo(expectedCanvas));

    }

    @Test
    public void canDrawLineToCorrectCell() throws InvalidPointsException {
        DefaultCanvas canvas = new DefaultCanvas(5, 5);

        canvas.drawLine(new Point(2,2), new Point(2,4));
        for (int i = 1; i < canvas.drawings.length-1; i++) {
            for (int j = 1; j < canvas.drawings[i].length-1; j++) {

                if(j==2 && i>=2 && i <=4) {
                    assertThat("at " + i + "," + j, canvas.drawings[i][j], equalTo('x'));
                }else{
                    assertNull("at " + i + "," + j,canvas.drawings[i][j]);
                }

            }
        }

    }

    @Test
    public void throwsInvalidPointExceptionWhenLinePointsAreNotForVerticalOrHorizontalLine(){

        DefaultCanvas canvas = new DefaultCanvas(5, 5);

        try {
            canvas.drawLine(new Point(1,3), new Point(2,4));
            fail();
        } catch (InvalidPointsException e) {
            assertThat(e.getMessage(), equalTo("You can only draw an horizontal or a vertical line here.\nPlease supply the same Xs or the same Ys in your points."));
        }
    }

    @Test
    public void throwsInvalidPointExceptionWhenLinePointsAreOutsideThanCanvas(){

        DefaultCanvas canvas = new DefaultCanvas(5, 5);

        try {
            canvas.drawLine(new Point(1,7), new Point(2,7));
            fail();
        } catch (InvalidPointsException e) {
            assertThat(e.getMessage(), equalTo("You can't draw a line bigger than the canvas."));
        }

    }

}