package baymaxirc.coremodule;

import baymaxirc.core.event.IEventHandler;
import com.google.common.eventbus.Subscribe;
import org.pircbotx.hooks.events.JoinEvent;

/**
 * @author shadowfacts
 */
public class MyEventHandler implements IEventHandler {

	@Override
	@Subscribe
	public void handleJoinEvent(JoinEvent event) {
		event.respond("Hello Stranger!");
	}
}
