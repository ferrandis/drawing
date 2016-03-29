import java.awt.*;
import java.io.PrintWriter;

public class Canvas {
    private final int width;
    private final int height;

    public Canvas(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public void drawABlankCanvas(PrintWriter writer) {
        drawHorizontalBoarder(writer);
        for (int i = 0; i < height; i++) {
            drawVerticalBoarder(writer);
        }
        drawHorizontalBoarder(writer);

        writer.flush();
    }


    public void drawLine(PrintWriter writer, Point start, Point end) {
        if(isOutsideCanvasRange(start,end)){
            writer.print("You can't draw a line bigger than the canvas\n");
        }
        else if(isDiagonalPoints(start,end)){
            String message = "You can only draw an horizontal or a vertical line here.\n"
                    + "Please supply the same Xs or the same Ys in your points.\n";

            writer.print(message);
        }
        else{
            drawALine(writer,start,end);
        }

        writer.flush();
    }

    private boolean isDiagonalPoints(Point start, Point end) {
        return !(start.x == end.x || start.y == end.y);
    }

    private boolean isOutsideCanvasRange(Point start, Point end) {
        return start.x > width || start.y > height || end.x > width || end.y > height;
    }

    private void drawALine(PrintWriter writer, Point start, Point end) {
        drawHorizontalBoarder(writer);
        for (int i = 1; i <= height; i++) {
            writer.print("|");
            for (int j = 1; j <= width; j++) {
                 if(j>=start.x && j <=end.x && i >= start.y && i <=end.y){
                     writer.print("x");
                 }else {
                     writer.print(" ");
                 }
            }
            writer.print("|\n");
        }
        drawHorizontalBoarder(writer);

        writer.flush();
    }

    private void drawHorizontalBoarder(PrintWriter writer){
        for (int i = 0; i < width+2; i++) {
            writer.print("-");
        }
        writer.print("\n");
    }

    private void drawVerticalBoarder(PrintWriter writer){
        writer.print("|");
        for (int i = 0; i < width; i++) {
            writer.print(" ");

        }
        writer.print("|\n");
    }

}
