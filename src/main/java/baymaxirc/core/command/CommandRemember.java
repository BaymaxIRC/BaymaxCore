package baymaxirc.core.command;

import baymaxirc.core.Baymax;
import com.google.common.base.Joiner;
import org.pircbotx.hooks.types.GenericMessageEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Remembers a simple command that just outputs a line of text.
 * @author shadowfacts
 */
public class CommandRemember implements ICommand {

	public static CommandRemember instance = new CommandRemember();

	public Map<String, String> remeberedCommands = new HashMap<>();

	@Override
	public String getCommandName() {
		return "remember";
	}

	@Override
	public void execute(ArrayList<String> args, GenericMessageEvent event) {
		String cmd = args.get(0);
		args.remove(0);
		String output = Joiner.on(' ').join(args);
		Baymax.commandManager.registerCommand(new ICommand() {
			@Override
			public String getCommandName() {
				return cmd;
			}

			@Override
			public void execute(ArrayList<String> args, GenericMessageEvent event) {
				event.respond(output);
			}

			@Override
			public void help(GenericMessageEvent event) {
				event.respond(String.format("%s is a command generated by remember.", cmd));
			}
		});
		remeberedCommands.put(cmd, output);
		event.respond(String.format("Remembered %s", cmd));
	}

	@Override
	public void help(GenericMessageEvent event) {
		event.respond("Remembers a simple command that outputs a line of text. See forget to remove remembered commands.");
	}
}
