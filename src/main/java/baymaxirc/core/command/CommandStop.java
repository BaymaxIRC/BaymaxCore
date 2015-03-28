package baymaxirc.core.command;

import org.pircbotx.hooks.types.GenericMessageEvent;

import java.util.ArrayList;

/**
 * @author shadowfacts
 */
public class CommandStop implements ICommand {

	public static CommandStop instance = new CommandStop();

	@Override
	public String getCommandName() {
		return "stop";
	}

	@Override
	public void execute(ArrayList<String> args, GenericMessageEvent event) {
		System.exit(0);
	}

	@Override
	public void help(GenericMessageEvent event) {
		event.respond("Stops the bot with System.exit()");
	}
}
