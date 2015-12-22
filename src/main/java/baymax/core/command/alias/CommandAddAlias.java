package baymax.core.command.alias;

import baymax.core.Baymax;
import baymax.core.command.Command;
import baymax.core.permission.PermissionsManager;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.kitteh.irc.client.library.element.Channel;
import org.kitteh.irc.client.library.element.User;

import java.util.Optional;

/**
 * @author shadowfacts
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CommandAddAlias implements Command {

	public static final CommandAddAlias instance = new CommandAddAlias();

	@Override
	public String getName() {
		return "alias";
	}

	@Override
	public boolean canSenderUseCommand(Optional<Channel> channel, User user) {
		return PermissionsManager.instance.isAdmin(user);
	}

	@Override
	public void processCommand(Optional<Channel> channel, User user, String[] args) {
		if (args.length != 2) {
			user.sendMessage("`" + getName() + "` takes 2 arguments");
			return;
		}
		AliasManager.instance.addAlias(args[0], args[1]);
	}

	@Override
	public void handleHelpRequest(User user) {
		user.sendMessage(String.format("`%s` alias one command to another. Only usable by %s admins", getName(), Baymax.instance.getClient().getNick()));
	}
}
