import org.junit.Test;

import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class CanvasTest {

    @Test
    public void canDrawACanvas(){

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        Canvas canvas = new Canvas(5, 2);

        canvas.drawABlankCanvas(new PrintWriter(outputStream));
        String expectedCanvas = "-------\n"
                + "|     |\n"
                + "|     |\n"
                + "-------\n";

        assertThat(outputStream.toString(), equalTo(expectedCanvas));

    }

    @Test
    public void canDrawAVerticalLine(){
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        Canvas canvas = new Canvas(20, 4);

        canvas.drawABlankCanvas(new PrintWriter(outputStream));

        outputStream = new ByteArrayOutputStream();

        canvas.drawLine(new PrintWriter(outputStream), new Point(6,2), new Point(6,4));
        String expectedCanvas = "----------------------\n"
                + "|                    |\n"
                + "|     x              |\n"
                + "|     x              |\n"
                + "|     x              |\n"
                + "----------------------\n";

        assertThat(outputStream.toString(), equalTo(expectedCanvas));

    }
    @Test
    public void canDrawAnHorizontalLine(){
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        Canvas canvas = new Canvas(20, 4);

        canvas.drawABlankCanvas(new PrintWriter(outputStream));

        outputStream = new ByteArrayOutputStream();

        canvas.drawLine(new PrintWriter(outputStream), new Point(1,2), new Point(6,2));
        String expectedCanvas = "----------------------\n"
                + "|                    |\n"
                + "|xxxxxx              |\n"
                + "|                    |\n"
                + "|                    |\n"
                + "----------------------\n";

        assertThat(outputStream.toString(), equalTo(expectedCanvas));

    }
    @Test
    public void startAndEndPointsShouldContainTheSameXsOrYs(){
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        Canvas canvas = new Canvas(20, 4);

        canvas.drawABlankCanvas(new PrintWriter(outputStream));
        outputStream = new ByteArrayOutputStream();
        canvas.drawLine(new PrintWriter(outputStream),new Point(1,2), new Point(6,3));


        String expectedMessage = "You can only draw an horizontal or a vertical line here.\n"
                + "Please supply the same Xs or the same Ys in your points.\n";

        assertThat(outputStream.toString(), equalTo(expectedMessage));

    }

    @Test
    public void startAndEndPointsShouldBeWithinCanvasRange(){
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        Canvas canvas = new Canvas(20, 4);

        canvas.drawABlankCanvas(new PrintWriter(outputStream));
        outputStream = new ByteArrayOutputStream();
        canvas.drawLine(new PrintWriter(outputStream), new Point(25,2), new Point(25,3));


        String expectedMessage = "You can't draw a line bigger than the canvas\n";

        assertThat(outputStream.toString(), equalTo(expectedMessage));


    }
}
