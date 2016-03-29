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
        Canvas canvas = new Canvas(new PrintWriter(outputStream), 5, 2);

        canvas.drawCanvas();
        String expectedCanvas = "-------\n"
                + "|     |\n"
                + "|     |\n"
                + "-------\n";

        assertThat(outputStream.toString(), equalTo(expectedCanvas));

    }

    @Test
    public void canDrawAHorizontalLine(){
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        Canvas canvas = new Canvas(new PrintWriter(outputStream), 20, 4);

        canvas.drawLine(new Point(1,2), new Point(6,2));
        String expectedCanvas = "----------------------\n"
                + "|                    |\n"
                + "|xxxxxx              |\n"
                + "|                    |\n"
                + "|                    |\n"
                + "----------------------\n";

    }
}
