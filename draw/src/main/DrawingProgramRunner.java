import command.InvalidCommandException;

import java.io.*;

public class DrawingProgramRunner {
    public static void main(String[] args) throws IOException, InvalidCommandException {
        PrintStream writer = System.out;
        DrawingProgram drawingProgram = new DrawingProgram(writer);

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String input;

        enterCommand(writer);

        while((input=br.readLine())!=null){
            drawingProgram.enterCommand(input);
            enterCommand(writer);
        }
    }

    private static void enterCommand(PrintStream writer) {
        writer.print("enter command: ");
    }
}
