package baymax.core.command;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.kitteh.irc.client.library.element.Channel;
import org.kitteh.irc.client.library.element.User;

import java.util.Optional;

/**
 * @author shadowfacts
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CommandListCommands implements Command {

	public static final CommandListCommands instance = new CommandListCommands();

	@Override
	public String getName() {
		return "commands";
	}

	@Override
	public boolean canSenderUseCommand(Optional<Channel> channel, User user) {
		return true;
	}

	@Override
	public void processCommand(Optional<Channel> channel, User user, String[] args) {
		if (channel.isPresent()) {
			channel.get().sendMessage(user.getNick() + ", I am private messaging you a list of all my commands");
		}
		user.sendMessage("My commands are: " + String.join(", ", CommandManager.instance.getCommandNames()));
	}

	@Override
	public void handleHelpRequest(User user) {
		user.sendMessage(getName() + ": Displays a list of all registered commands.");
	}

}
