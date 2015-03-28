package baymaxirc.core.event;

import org.pircbotx.hooks.Event;

import java.util.ArrayList;

/**
 * @author shadowfacts
 */
public class MainEventBus {

	private ArrayList<IEventHandler> handlers;

	public void register(IEventHandler handler) {
		handlers.add(handler);
	}

	public void post(Event event) {
//		if (event instanceof ) {
//			for (IEventHandler handler : handlers) {
//
//			}
//		}

//		for (IEventHandler handler : handlers) {
//			handler.getClass().
//		}
	}


}
