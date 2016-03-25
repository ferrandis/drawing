import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class CanvasTest {
    @Test
    public void canDrawACanvas(){
        Canvas canvas = new Canvas(5, 2);

        String expectedCanvas = "-----\n"
                + "|   |\n"
                + "|   |\n"
                + "-----\n";

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        canvas.draw(new PrintWriter(outputStream));
        assertThat(outputStream.toString(), equalTo(expectedCanvas));

    }
}
