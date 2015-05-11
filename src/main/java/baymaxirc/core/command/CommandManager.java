package baymaxirc.core.command;

import baymaxirc.core.Baymax;
import baymaxirc.core.util.MiscUtils;
import com.google.common.eventbus.Subscribe;
import org.pircbotx.hooks.types.GenericMessageEvent;

import java.util.*;

/**
 * @author shadowfacts
 */
public class CommandManager {

	private Map<String, ICommand> commands = new HashMap<>();

	public CommandManager() {
		registerCommand(CommandHelp.instance);
		registerCommand(CommandListAllCommands.instance);
		registerCommand(CommandVersion.instance);
		registerCommand(CommandAlias.instance);
		registerCommand(CommandRemoveAlias.instance);
		registerCommand(CommandStop.instance);
		registerCommand(CommandRemember.instance);
		registerCommand(CommandForget.instance);

		Baymax.eventBus.register(this);
	}

	public void registerCommand(String name, ICommand cmd) {
		commands.put(name, cmd);
	}

	public void registerCommand(ICommand cmd) {
		registerCommand(cmd.getCommandName(), cmd);
	}

	public void removeCommand(String name) {
		commands.remove(name);
	}

	@Subscribe
	public void tryHandleCommand(GenericMessageEvent event) {
		if (event.getMessage().startsWith(Baymax.commandStr)) {
			ICommand command = getCommand(event);
			if (command != null) {
				ArrayList<String> args = new ArrayList<>(Arrays.asList(event.getMessage().split(" ")));
				args.remove(0);
				getCommand(event).execute(args, event);
			} else {
				event.respond(String.format("%s is not a command.", MiscUtils.getCommandFromString(event.getMessage())));
			}
		}
	}

	public boolean isCommand(String msg) {
		return msg.startsWith(Baymax.commandStr);
	}

	public ICommand getCommand(String command) {
		if (isCommand(command)) {
			return commands.get(MiscUtils.getCommandFromString(command));
		}
		return null;
	}

	public ICommand getCommand(GenericMessageEvent event) {
		return getCommand(event.getMessage());
	}

	public Set<String> getCommandList() {
		return commands.keySet();
	}

	public boolean commandExists(String command) {
		return commands.containsKey(command);
	}

}
