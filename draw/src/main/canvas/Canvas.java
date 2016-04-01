package canvas;

import java.awt.*;
import java.io.PrintStream;

public interface Canvas {
    void drawCanvas(PrintStream printStream);

    void drawLine(Point start, Point end) throws InvalidPointsException;

    void drawRectangle(Point start, Point end) throws InvalidPointsException;
}
