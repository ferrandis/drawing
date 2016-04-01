package command;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

public class CommandSelectorTest {

    @Test
    public void selectCorrectCommand() throws InvalidCommandException {
        assertThat(CommandSelector.select("C 1 2"), equalTo(new CanvasCommand("C 1 2")));
        assertThat(CommandSelector.select("L 1 2 3 4"), equalTo(new LineCommand("L 1 2 3 4")));

    }

    @Test
    public void throwsInvalidCommandExceptionWhenItBeginsWithUnexpectedCharacter(){
        try {
            CommandSelector.select("H 3 4");
            fail();
        } catch (InvalidCommandException e) {
            assertThat(e.getMessage(), equalTo("This command is not accepted."));
        }
    }
}