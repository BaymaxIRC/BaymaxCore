package baymaxirc.core.event;

import org.pircbotx.hooks.Event;

import java.util.ArrayList;

/**
 * @author shadowfacts
 */
public class MainEventBus {

	private ArrayList<IEventHandler> handlers = new ArrayList<>();

	public void register(IEventHandler handler) {
		if (handler != null) handlers.add(handler);
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
