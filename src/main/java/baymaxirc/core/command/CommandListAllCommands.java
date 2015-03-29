package baymaxirc.core.command;

import baymaxirc.core.Baymax;
import org.pircbotx.hooks.types.GenericMessageEvent;

import java.util.ArrayList;

/**
 * Lists all available commands, no parameters.
 * @author shadowfacts
 */
public class CommandListAllCommands implements ICommand {

	public static CommandListAllCommands instance = new CommandListAllCommands();

	@Override
	public String getCommandName() {
		return "commands";
	}

	@Override
	public void execute(ArrayList<String> args, GenericMessageEvent event) {
		StringBuilder builder = new StringBuilder();

		int i = 0;
		for (String cmdName : Baymax.commandManager.getCommandList()) {
			if (i == Baymax.commandManager.getCommandList().size() - 1) {
				builder.append("and " + cmdName);
			} else {
				builder.append(cmdName + ", ");
			}

			i++;
		}

		event.respond(builder.toString());
	}

	@Override
	public void help(GenericMessageEvent event) {
		event.respond("Lists all available commands.");
	}
}
