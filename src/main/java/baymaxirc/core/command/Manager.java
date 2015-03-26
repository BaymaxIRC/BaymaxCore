package baymaxirc.core.command;

import baymaxirc.core.util.MiscUtils;
import org.pircbotx.hooks.types.GenericMessageEvent;

import java.util.*;

/**
 * @author shadowfacts
 */
public class Manager {

	private static Map<String, ICommand> commands = new HashMap<>();

	public static void registerCommand(ICommand cmd) {
		commands.put(cmd.getCommandName(), cmd);
	}

	public static void tryHandleCommand(GenericMessageEvent event) {
		ICommand command = getCommand(event);
		if (command != null) {
			ArrayList<String> args = new ArrayList<>(Arrays.asList(event.getMessage().split(" ")));
			args.remove(0);
			getCommand(event).execute(args, event);
		} else {
//			event.respond(String.format("%s is not a command.", MiscUtils.getCommandFromString(event.getMessage())));
		}
	}

	public static boolean isCommand(String msg) {
		if (msg.startsWith("?")) {
			return true;
		}
		return false;
	}

	public static ICommand getCommand(String command) {
		if (isCommand(command)) {
			return commands.get(MiscUtils.getCommandFromString(command));
		}
		return null;
	}

	public static ICommand getCommand(GenericMessageEvent event) {
		return getCommand(event.getMessage());
	}

	public static Set<String> getCommandList() {
		return commands.keySet();
	}

	public static void init() {
//		Register all the commands!
		registerCommand(CommandHelp.instance);
		registerCommand(CommandListAllCommands.instance);
		registerCommand(CommandVersion.instance);
	}

}
