package baymaxirc.core.command;

/**
 * @author shadowfacts
 */
public interface ICommand {

	String getCommandName();

//	void execute(ArrayList<String> args, GenericMessageEvent event);
//
//	default void help(GenericMessageEvent event) {
//		event.respond(getCommandName() + ": No information has been provided for this command.");
//	}

}
