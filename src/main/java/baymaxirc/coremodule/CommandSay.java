package baymaxirc.coremodule;

import baymaxirc.core.command.ICommand;
import com.google.common.base.Joiner;
import org.pircbotx.hooks.types.GenericMessageEvent;

import java.util.ArrayList;

/**
 * @author shadowfacts
 */
public class CommandSay implements ICommand {

	public static CommandSay instance= new CommandSay();

	@Override
	public String getCommandName() {
		return "say";
	}

	@Override
	public void execute(ArrayList<String> args, GenericMessageEvent event) {
		event.respond(Joiner.on(' ').join(args));
	}

	@Override
	public void help(GenericMessageEvent event) {
		event.respond("Parrots back whatever you give it.");
		event.respond("Polly want a cracker? Polly want a cracker?");
	}
}
