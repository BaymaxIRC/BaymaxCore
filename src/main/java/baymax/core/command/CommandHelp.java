package baymax.core.command;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.kitteh.irc.client.library.element.Channel;
import org.kitteh.irc.client.library.element.User;

import java.util.Optional;

/**
 * This command is used to call {@link Command#handleHelpRequest(User)} on commands for help info
 *
 * @author shadowfacts
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CommandHelp implements Command {

	public static final CommandHelp instance = new CommandHelp();

	@Override
	public String getName() {
		return "help";
	}

	@Override
	public boolean canSenderUseCommand(Optional<Channel> channel, User user) {
		return true;
	}

	@Override
	public void processCommand(Optional<Channel> channel, User user, String[] args) {
		if (args.length != 1) {
			user.sendMessage("`" + getName() + "` only accepts 1 argument");
			return;
		}


		Optional<Command> command = CommandManager.instance.getCommand(args[0]);
		if (command.isPresent()) {
			command.get().handleHelpRequest(user);
		} else {
			user.sendMessage("No such command `" + args[0] + "`");
		}
	}

	@Override
	public void handleHelpRequest(User user) {
		user.sendMessage(getName() + ": displays help information for the requested command");
		user.sendMessage("Did you really just use help to ask for help with help?");
	}
}
