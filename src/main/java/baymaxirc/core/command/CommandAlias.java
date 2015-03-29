package baymaxirc.core.command;

import baymaxirc.core.Baymax;
import org.pircbotx.hooks.types.GenericMessageEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Symlink one command to another.
 * @author shadowfacts
 */
public class CommandAlias implements ICommand {

	public static CommandAlias instance = new CommandAlias();

	public Map<String, String> aliases = new HashMap<>();

	@Override
	public String getCommandName() {
		return "alias";
	}

	@Override
	public void execute(ArrayList<String> args, GenericMessageEvent event) {
		String newAlias = args.get(0);
		String existingCommand = args.get(1);
		if (!aliases.containsKey(newAlias) && !Baymax.commandManager.getCommandList().contains(newAlias)) {
			aliases.put(newAlias, existingCommand);
			Baymax.commandManager.registerCommand(newAlias, Baymax.commandManager.getCommand("?" + existingCommand));
			event.respond(String.format("%s has been aliased to %s", newAlias, existingCommand));
		}
	}

	@Override
	public void help(GenericMessageEvent event) {
		event.respond("Alias one command to another.");
	}
}
