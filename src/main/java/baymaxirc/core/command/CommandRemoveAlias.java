package baymaxirc.core.command;

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
		if (CommandAlias.instance.aliases.containsKey(alias) && CommandManager.getCommandList().contains(alias)) {
			CommandManager.removeCommand(alias);
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
