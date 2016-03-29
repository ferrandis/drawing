import java.awt.*;
import java.io.PrintWriter;

public class Canvas {
    private final PrintWriter writer;
    private final int width;
    private final int height;

    public Canvas(PrintWriter writer, int width, int height) {
        this.writer = writer;

        this.width = width;
        this.height = height;
    }

    public void drawCanvas() {
        drawHorizontalLine(writer);
        for (int i = 0; i < height; i++) {
            drawVerticalLine(writer);
        }
        drawHorizontalLine(writer);

        writer.flush();
    }

    private void drawHorizontalLine(PrintWriter printWriter){
        for (int i = 0; i < width+2; i++) {
            printWriter.print("-");
        }
        printWriter.print("\n");
    }

    private void drawVerticalLine(PrintWriter printWriter){
        printWriter.print("|");
        for (int i = 0; i < width; i++) {
            printWriter.print(" ");

        }
        printWriter.print("|\n");
    }

    public void drawLine(Point start, Point end) {

    }
}
