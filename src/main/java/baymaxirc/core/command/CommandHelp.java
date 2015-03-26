package baymaxirc.core.command;

import org.pircbotx.hooks.types.GenericMessageEvent;

import java.util.ArrayList;

/**
 * @author shadowfacts
 */
public class CommandHelp implements ICommand {

	public static CommandHelp instance = new CommandHelp();


	@Override
	public String getCommandName() {
		return "help";
	}

	@Override
	public void execute(ArrayList<String> args, GenericMessageEvent event) {

//		System.out.println(args.get(0));
		if (args.size() < 1) {
			event.respond("You have not provided a command to retrieve help on.");
		} else if (Manager.isCommand("?" + args.get(0))) {
			Manager.getCommand("?" + args.get(0)).help(event);
		} else {
			event.respond(String.format("%s is not a command.", args.get(0)));
		}
	}

	@Override
	public void help(GenericMessageEvent event) {
		event.respond("Duh. Displays help information for the requested command.");
		event.respond("You should know this, you just requested help about help using help.");
	}
}
