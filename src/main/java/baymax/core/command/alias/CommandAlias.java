package baymax.core.command.alias;

import baymax.core.command.Command;
import baymax.core.command.CommandManager;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.kitteh.irc.client.library.element.Channel;
import org.kitteh.irc.client.library.element.User;

import java.util.Optional;

/**
 * Command used when aliasing one command to another
 *
 * @author shadowfacts
 */
@AllArgsConstructor
@Getter
public class CommandAlias implements Command {

	private String name;
	private String target;

	@Override
	public String getName() {
		return name;
	}

	@Override
	public boolean canSenderUseCommand(Optional<Channel> channel, User user) {
		Optional<Command> targetCommand = CommandManager.instance.getCommand(target);
		return targetCommand.isPresent() && targetCommand.get().canSenderUseCommand(channel, user);
	}

	@Override
	public void processCommand(Optional<Channel> channel, User user, String[] args) {
		Optional<Command> targetCommand = CommandManager.instance.getCommand(target);
		if (targetCommand.isPresent()) {
			targetCommand.get().processCommand(channel, user, args);
		}
	}

	@Override
	public void handleHelpRequest(User user) {
		user.sendMessage(String.format("`%s` is an alias for `%s`. Sending help info for `%s`.", name, target, target));
		Optional<Command> targetCommand = CommandManager.instance.getCommand(target);
		if (targetCommand.isPresent()) {
			targetCommand.get().handleHelpRequest(user);
		}
	}

}
