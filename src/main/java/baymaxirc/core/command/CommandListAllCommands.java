package baymaxirc.core.command;

import baymaxirc.core.api.command.ICommand;
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
		for (String cmdName : Manager.getCommandList()) {
			if (i == Manager.getCommandList().size() - 1) {
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
