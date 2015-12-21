package baymax.core.factoid;

import baymax.core.Baymax;
import baymax.core.command.Command;
import baymax.core.util.ChannelUtils;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.kitteh.irc.client.library.element.Channel;
import org.kitteh.irc.client.library.element.ChannelMode;
import org.kitteh.irc.client.library.element.ChannelUserMode;
import org.kitteh.irc.client.library.element.User;

import java.util.Arrays;
import java.util.Optional;
import java.util.Set;

/**
 * @author shadowfacts
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CommandSetFactoid implements Command {

	public static final CommandSetFactoid instance = new CommandSetFactoid();

	@Override
	public String getName() {
		return "set-factoid";
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
		if (args.length < 2) {
			user.sendMessage("`" + getName() + "` takes at least 2 arguments");
			return;
		}

		String name = args[0];
		String factoid = String.join(" ", Arrays.copyOfRange(args, 1, args.length));
		Factoid theFactoid = new Factoid(channel.get().getName(), name, factoid, user);
		FactoidManager.instance.setFactoid(channel.get().getName(), theFactoid);

		channel.get().sendMessage(user.getNick() + ", factoid `" + name + "` has been created");
	}

	@Override
	public void handleHelpRequest(User user) {
		user.sendMessage("Creates a factoid. Only usable in channels. Minimum level is '" + Baymax.instance.getConfig().getString("baymax.commands.factoid.minimumLevel") + "'");
	}
}
