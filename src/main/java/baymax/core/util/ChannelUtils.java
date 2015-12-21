package baymax.core.util;

import org.kitteh.irc.client.library.element.Channel;
import org.kitteh.irc.client.library.element.ChannelMode;
import org.kitteh.irc.client.library.element.ChannelUserMode;
import org.kitteh.irc.client.library.element.User;

import java.util.Optional;
import java.util.Set;

/**
 * Utilities for channel operations
 *
 * @author shadowfacts
 */
public class ChannelUtils {

	/**
	 * Check if the given user has voice status or higher in the given channel
	 * @param channel The channel
	 * @param user The user
	 * @return Is the user a voice or higher
	 */
	public static boolean isAtLeastVoice(Channel channel, User user) {
		return isVoice(channel, user) || isHalfop(channel, user) || isOp(channel, user);
	}

	/**
	 * Check if the given user has halfop status or higher in the given channel
	 * @param channel The channel
	 * @param user The user
	 * @return Is the user a halfop or higher
	 */
	public static boolean isAtLeastHalfop(Channel channel, User user) {
		return isHalfop(channel, user) || isOp(channel, user);
	}

	/**
	 * Is the given user a voice in the given channel
	 * @param channel The channel
	 * @param user The user
	 * @return Is the user a voice
	 */
	public static boolean isVoice(Channel channel, User user) {
		Optional<Set<ChannelUserMode>> modes = channel.getUserModes(user.getNick());
		return modes.isPresent() && modes.get().stream()
				.map(ChannelMode::getChar)
				.filter(c -> c.equals('v'))
				.findAny()
				.isPresent();
	}

	/**
	 * Is the given user a halfop in the given channel
	 * @param channel The channel
	 * @param user The user
	 * @return Is the user a halfop
	 */
	public static boolean isHalfop(Channel channel, User user) {
		Optional<Set<ChannelUserMode>> modes = channel.getUserModes(user.getNick());
		return modes.isPresent() && modes.get().stream()
				.map(ChannelMode::getChar)
				.filter(c -> c.equals('h'))
				.findAny()
				.isPresent();
	}

	/**
	 * Is the given user an op in the given channel
	 * @param channel The channel
	 * @param user The user
	 * @return Is the user an op
	 */
	public static boolean isOp(Channel channel, User user) {
		Optional<Set<ChannelUserMode>> modes = channel.getUserModes(user.getNick());
		return modes.isPresent() && modes.get().stream()
				.map(ChannelMode::getChar)
				.filter(c -> c.equals('o'))
				.findAny()
				.isPresent();
	}

}
