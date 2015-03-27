package baymaxirc.core.api.module;

import baymaxirc.core.api.event.IEventHandler;

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
	default void registerCommands() {

	}

	/**
	 * @return The event handler.
	 */
	default IEventHandler getModuleEventHandler() {
		return null;
	}

}
