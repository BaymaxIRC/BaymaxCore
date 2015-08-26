package baymaxirc.core.module;

import baymaxirc.core.command.CommandManager;
import baymaxirc.core.event.IEventHandler;

/**
 * @author shadowfacts
 */
public interface IModule {

	/**
	 * @return The name of the module.
	 */
	String getName();

	/**
	 * Register all commands here.
	 */
	default void registerCommands(CommandManager commandManager) {}

	/**
	 * @return The event handler.
	 */
	default IEventHandler getEventHandler() {
		return null;
	}

}
