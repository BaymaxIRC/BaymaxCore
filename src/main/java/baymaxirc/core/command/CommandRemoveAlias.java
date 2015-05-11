package baymaxirc.core.command;

import baymaxirc.core.Baymax;
import org.pircbotx.hooks.types.GenericMessageEvent;

import java.util.ArrayList;

/**
 * @author shadowfacts
 */
public class CommandRemoveAlias implements ICommand {

	public static CommandRemoveAlias instance = new CommandRemoveAlias();

	@Override
	public String getCommandName() {
		return "removeAlias";
	}

	@Override
	public void execute(ArrayList<String> args, GenericMessageEvent event) {
		String alias = args.get(0);
		if (CommandAlias.instance.aliases.containsKey(alias) && Baymax.commandManager.commandExists(alias)) {
			Baymax.commandManager.removeCommand(alias);
			CommandAlias.instance.aliases.remove(alias);
			event.respond(String.format("Alias %s removed.", alias));
		} else {
			event.respond(String.format("%s is not an alias.", alias));
		}
	}

	@Override
	public void help(GenericMessageEvent event) {
		event.respond("Remove an alias created by the alias command.");
	}
}
