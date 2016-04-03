package command;

import canvas.Canvas;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Test;

import java.util.Optional;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class QuitCommandTest {
    private Mockery context = new Mockery();
    private Canvas canvas = context.mock(Canvas.class);
    private SystemExit systemExit = context.mock(SystemExit.class);

    @Test
    public void canQuit() throws Exception {
        QuitCommand quitCommand = new QuitCommand("Q", systemExit);

        context.checking(new Expectations(){{
        oneOf(systemExit).exit();
        }
        });
        assertThat(quitCommand.draw(Optional.of(canvas)), equalTo(Optional.empty()));
    }

}