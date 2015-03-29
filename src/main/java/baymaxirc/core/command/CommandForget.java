package baymaxirc.core.command;

import baymaxirc.core.Baymax;
import org.pircbotx.hooks.types.GenericMessageEvent;

import java.util.ArrayList;

/**
 * Forget commands created by the remember command.
 * @author shadowfacts
 */
public class CommandForget implements ICommand {

	public static CommandForget instance = new CommandForget();

	@Override
	public String getCommandName() {
		return "forget";
	}

	@Override
	public void execute(ArrayList<String> args, GenericMessageEvent event) {
		String cmd = args.get(0);
		if (CommandRemember.instance.remeberedCommands.containsKey(cmd)) {
			CommandRemember.instance.remeberedCommands.remove(cmd);
			Baymax.commandManager.removeCommand(cmd);
			event.respond(String.format("Forgotten %s", cmd));
		} else {
			event.respond(String.format("%s is not a command created by remember.", cmd));
		}
	}

	@Override
	public void help(GenericMessageEvent event) {
		event.respond("Forgets/removes a command created by the remember command.");
	}
}
