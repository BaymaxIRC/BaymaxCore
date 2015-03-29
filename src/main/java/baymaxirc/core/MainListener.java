package baymaxirc.core;

import org.pircbotx.hooks.Event;
import org.pircbotx.hooks.ListenerAdapter;

/**
 * @author shadowfacts
 */
public class MainListener extends ListenerAdapter {

	@Override
	public void onEvent(Event event) throws Exception {
//		super.onEvent(event);
		Baymax.eventBus.post(event);
	}

//	@Override
//	public void onGenericMessage(GenericMessageEvent event) throws Exception {
//		Baymax.commandManager.tryHandleCommand(event);
//	}
}
