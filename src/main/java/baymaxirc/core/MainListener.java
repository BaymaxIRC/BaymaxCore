package baymaxirc.core;

import baymaxirc.core.command.Manager;
import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.types.GenericMessageEvent;

/**
 * @author shadowfacts
 */
public class MainListener extends ListenerAdapter {

	@Override
	public void onGenericMessage(GenericMessageEvent event) {
//		if (CommandHandler.isCommand(event.getMessage())) {
//			event.respond("Hi! That was a command!");
//		}
		Manager.tryHandleCommand(event);
	}

}
