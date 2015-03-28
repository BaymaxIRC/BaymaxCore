package baymaxirc.core;

import baymaxirc.core.command.CommandManager;
import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.types.GenericMessageEvent;

/**
 * @author shadowfacts
 */
public class MainListener extends ListenerAdapter {

	@Override
	public void onGenericMessage(GenericMessageEvent event) {
		CommandManager.tryHandleCommand(event);
	}

}
