package baymax.core.command;

/**
 * A command registrar is anything that can be used to regidster commands.
 * This is normally {@link CommandManager#instance}
 *
 * @author shadowfacts
 */
public interface CommandRegistrar {

	default void registerCommand(Command command) {
		registerCommand(command.getName(), command);
	}

	void registerCommand(String name, Command command);

	void unregisterCommand(String name);

}
