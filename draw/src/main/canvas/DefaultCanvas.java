package canvas;

import java.awt.*;
import java.io.PrintStream;
import java.util.Arrays;

public class DefaultCanvas implements Canvas{
    public final int width;
    public final int height;
    private int canvasWidth;
    private int canvasHeight;
    public Character[][] drawings;

    public DefaultCanvas(int width, int height) {
        this.width = width;
        this.height = height;
        canvasHeight = height + 2;
        canvasWidth = width+2;
        drawings = new Character[canvasHeight][canvasWidth];
        drawHorizontalBoarder();
        drawVerticalBoarder();
    }

    public void drawCanvas(PrintStream printStream) {
        for(int row = 0; row < canvasHeight; row++) {
            for(int cell = 0; cell < canvasWidth; cell++) {
                Character character = drawings[row][cell];
                printStream.print(character == null ? ' ' : character);
            }
            printStream.print("\n");
        }

        printStream.flush();
    }

    public void drawLine(Point start, Point end) throws InvalidPointsException {
        if(isOutsideCanvasRange(start,end)){
            throw new InvalidPointsException("You can't draw a line bigger than the canvas.");
        }else if(isDiagonalPoints(start,end)){
            String message = "You can only draw an horizontal or a vertical line here.\n"
                    + "Please supply the same Xs or the same Ys in your points.";

            throw new InvalidPointsException(message);
        }
        for (int i = start.y; i <= end.y; i++) {
            for (int j = start.x; j <= end.x; j++) {
                drawings[i][j] = 'x';
            }
        }
    }

    private void drawHorizontalBoarder() {
        for (int i = 0; i < canvasWidth; i++) {
            drawings[0][i] = '-';
            drawings[canvasHeight-1][i] = '-';
        }
    }


    private void drawVerticalBoarder() {
        for (int i = 1; i < canvasHeight-1; i++) {
            drawings[i][0] = '|';
            drawings[i][canvasWidth-1] = '|';
        }
    }

    private boolean isOutsideCanvasRange(Point start, Point end) {
        return start.x > width || start.y > height || end.x > width || end.y > height;
    }

    private boolean isDiagonalPoints(Point start, Point end) {
        return !(start.x == end.x || start.y == end.y);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DefaultCanvas that = (DefaultCanvas) o;

        if (width != that.width) return false;
        if (height != that.height) return false;
        return Arrays.deepEquals(drawings, that.drawings);

    }

    @Override
    public int hashCode() {
        int result = width;
        result = 31 * result + height;
        result = 31 * result + Arrays.deepHashCode(drawings);
        return result;
    }
}



