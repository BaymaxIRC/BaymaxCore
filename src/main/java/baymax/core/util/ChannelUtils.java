package baymax.core.util;

import org.kitteh.irc.client.library.element.Channel;
import org.kitteh.irc.client.library.element.ChannelMode;
import org.kitteh.irc.client.library.element.ChannelUserMode;
import org.kitteh.irc.client.library.element.User;

import java.util.Optional;
import java.util.Set;

/**
 * @author shadowfacts
 */
public class ChannelUtils {

	public static boolean isAtLeastVoice(Channel channel, User user) {
		return isVoice(channel, user) || isHalfop(channel, user) || isOp(channel, user);
	}

	public static boolean isAtLeastHalfop(Channel channel, User user) {
		return isHalfop(channel, user) || isOp(channel, user);
	}

	public static boolean isVoice(Channel channel, User user) {
		Optional<Set<ChannelUserMode>> modes = channel.getUserModes(user.getNick());
		return modes.isPresent() && modes.get().stream()
				.map(ChannelMode::getChar)
				.filter(c -> c.equals('v'))
				.findAny()
				.isPresent();
	}

	public static boolean isHalfop(Channel channel, User user) {
		Optional<Set<ChannelUserMode>> modes = channel.getUserModes(user.getNick());
		return modes.isPresent() && modes.get().stream()
				.map(ChannelMode::getChar)
				.filter(c -> c.equals('h'))
				.findAny()
				.isPresent();
	}

	public static boolean isOp(Channel channel, User user) {
		Optional<Set<ChannelUserMode>> modes = channel.getUserModes(user.getNick());
		return modes.isPresent() && modes.get().stream()
				.map(ChannelMode::getChar)
				.filter(c -> c.equals('o'))
				.findAny()
				.isPresent();
	}

}
