package baymaxirc.core.command;

import org.pircbotx.hooks.types.GenericMessageEvent;

import java.util.ArrayList;

/**
 * Symlink one command to another.
 * @author shadowfacts
 */
public class CommandAlias implements ICommand {

	public static CommandAlias instance = new CommandAlias();

	@Override
	public String getCommandName() {
		return "alias";
	}

	@Override
	public void execute(ArrayList<String> args, GenericMessageEvent event) {
		Manager.registerCommand(args.get(0), Manager.getCommand("?" + args.get(1)));
		event.respond(String.format("%s has been aliased to %s", args.get(0), args.get(1)));
	}

	@Override
	public void help(GenericMessageEvent event) {
		event.respond("Alias one command to another.");
	}
}
