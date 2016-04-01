package command;

import canvas.Canvas;

import java.util.Optional;

public abstract class Command {

    protected final String command;

    public Command(String command) {
        this.command = command;
    }

    public abstract Optional<Canvas> draw(Optional<Canvas> canvas) throws Exception;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Command command1 = (Command) o;

        return !(command != null ? !command.equals(command1.command) : command1.command != null);

    }

    @Override
    public int hashCode() {
        return command != null ? command.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Command{" + command + '}';
    }
}
