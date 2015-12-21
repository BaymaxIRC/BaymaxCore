package baymax.core.event;

import baymax.core.Baymax;
import baymax.core.command.CommandManager;
import baymax.core.factoid.FactoidManager;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.kitteh.irc.client.library.event.channel.ChannelMessageEvent;
import org.kitteh.irc.client.library.event.user.PrivateMessageEvent;
import org.kitteh.irc.lib.net.engio.mbassy.listener.Handler;

import java.util.Optional;

/**
 * @author shadowfacts
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BaymaxEventHandler {

	public static final BaymaxEventHandler instance = new BaymaxEventHandler();

	@Handler
	public void handleChannelMessage(ChannelMessageEvent event) {
		String commandPrefix = Baymax.instance.getConfig().getString("baymax.commands.prefix");
		String factoidPrefix = Baymax.instance.getConfig().getString("baymax.commands.factoid.prefix");
		if (event.getMessage().startsWith(commandPrefix + factoidPrefix)) {

			FactoidManager.instance.tryHandleFactoid(event.getChannel(), event.getActor(), event.getMessage());

		} else if (event.getMessage().startsWith(commandPrefix)) {

			CommandManager.instance.tryHandleCommand(Optional.of(event.getChannel()), event.getActor(), event.getMessage());

		}
	}

	@Handler
	public void handlePrivateMessage(PrivateMessageEvent event) {
		String commandPrefix = Baymax.instance.getConfig().getString("baymax.commands.prefix");
		if (event.getMessage().startsWith(commandPrefix)) {
			CommandManager.instance.tryHandleCommand(Optional.empty(), event.getActor(), event.getMessage());
		}
	}

}
