package canvas;

import java.awt.*;
import java.io.PrintStream;

public interface Canvas {
    void drawLine(Point start, Point end) throws InvalidPointsException;

    void drawCanvas(PrintStream printStream);
}
