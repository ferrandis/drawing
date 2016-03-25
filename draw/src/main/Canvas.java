import java.io.PrintWriter;

public class Canvas {
    private final int width;
    private final int height;

    public Canvas(int width, int height) {

        this.width = width;
        this.height = height;
    }

    public void draw(PrintWriter printWriter) {

        drawHorizontalLine(printWriter);
        for (int i = 0; i < height; i++) {
            drawVerticalLine(printWriter);
        }
        drawHorizontalLine(printWriter);

        printWriter.flush();
    }

    private void drawHorizontalLine(PrintWriter printWriter){
        for (int i = 0; i < width; i++) {
            printWriter.print("-");
        }
        printWriter.print("\n");
    }
    private void drawVerticalLine(PrintWriter printWriter){
        printWriter.print("|");
        for (int i = 0; i < width-2; i++) {
            printWriter.print(" ");

        }
        printWriter.print("|\n");
    }
}
