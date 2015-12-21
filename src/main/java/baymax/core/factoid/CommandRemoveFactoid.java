package baymax.core.factoid;

import baymax.core.Baymax;
import baymax.core.command.Command;
import baymax.core.util.ChannelUtils;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.kitteh.irc.client.library.element.Channel;
import org.kitteh.irc.client.library.element.ChannelUserMode;
import org.kitteh.irc.client.library.element.User;

import java.util.Optional;
import java.util.Set;

/**
 * Command to remove a factoid from the {@link FactoidManager#instance}
 * Only executable if the user is above the minimum level specified in the configuration file
 *
 * @author shadowfacts
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CommandRemoveFactoid implements Command {

	public static final CommandRemoveFactoid instance = new CommandRemoveFactoid();

	@Override
	public String getName() {
		return "remove-factoid";
	}

	@Override
	public boolean canSenderUseCommand(Optional<Channel> channel, User user) {
		if (channel.isPresent()) {
			String minimumLevel = Baymax.instance.getConfig().getString("baymax.commands.factoid.minimumLevel");
			if (minimumLevel.equals("any")) {
				return true;
			} else {
				Optional<Set<ChannelUserMode>> modes = channel.get().getUserModes(user.getNick());
				if (modes.isPresent()) {
					switch (minimumLevel) {
						case "voice":
							return ChannelUtils.isAtLeastVoice(channel.get(), user);
						case "halfop":
							return ChannelUtils.isAtLeastHalfop(channel.get(), user);
						case "op":
							return ChannelUtils.isOp(channel.get(), user);
					}
				}
			}
		}
		return false;
	}

	@Override
	public void processCommand(Optional<Channel> channel, User user, String[] args) {
		if (args.length != 1) {
			user.sendMessage("`" + getName() + "` takes 1 argument");
			return;
		}

		String name = args[0];
		if (FactoidManager.instance.hasFactoid(channel.get().getName(), name)) {
			FactoidManager.instance.removeFactoid(channel.get().getName(), name);
			channel.get().sendMessage(user.getNick() + ", factoid `" + name + "` has been removed");
		} else {
			channel.get().sendMessage(user.getNick() + ", no such factoid `" + name + "`");
		}
	}

	@Override
	public void handleHelpRequest(User user) {
		user.sendMessage("Removes a factoid. Only usable in channels. Minimum level is '" + Baymax.instance.getConfig().getString("baymax.commands.factoid.minimumLevel") + "'");
	}

}
