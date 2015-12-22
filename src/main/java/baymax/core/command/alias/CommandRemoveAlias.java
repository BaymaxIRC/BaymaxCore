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
public class CommandRemoveAlias implements Command {

	public static final CommandRemoveAlias instance = new CommandRemoveAlias();

	@Override
	public String getName() {
		return "remove-alias";
	}

	@Override
	public boolean canSenderUseCommand(Optional<Channel> channel, User user) {
		return PermissionsManager.instance.isAdmin(user);
	}

	@Override
	public void processCommand(Optional<Channel> channel, User user, String[] args) {
		if (args.length != 1) {
			user.sendMessage(String.format("`%s` takes 1 argument, the alias name to remove", getName()));
			return;
		}
		AliasManager.instance.removeAlias(args[0]);
	}

	@Override
	public void handleHelpRequest(User user) {
		user.sendMessage(String.format("`%s` removes an alias created by `alias`. Only usable by %s admins", getName(), Baymax.instance.getClient().getNick()));
	}
}
