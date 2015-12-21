package baymax.core.command;

/**
 * @author shadowfacts
 */
public interface CommandRegistrar {

	default void registerCommand(Command command) {
		registerCommand(command.getName(), command);
	}

	void registerCommand(String name, Command command);

	void unregisterCommand(String name);

}
