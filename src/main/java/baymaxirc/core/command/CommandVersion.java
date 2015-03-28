package baymaxirc.core.command;

/**
 * Gets the current version of BaymaxCore.
 * @author shadowfacts
 */
public class CommandVersion implements ICommand {

	public static CommandVersion instance = new CommandVersion();

	@Override
	public String getCommandName() {
		return "version";
	}

//	@Override
//	public void execute(ArrayList<String> args, GenericMessageEvent event) {
//		event.respond(Reference.version);
//	}
//
//	@Override
//	public void help(GenericMessageEvent event) {
//		event.respond("Gets the current version of BaymaxCore.");
//	}
}
