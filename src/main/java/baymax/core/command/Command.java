package baymax.core.command;

import org.kitteh.irc.client.library.element.Channel;
import org.kitteh.irc.client.library.element.User;

import java.util.Optional;

/**
 * @author shadowfacts
 */
public interface Command {

	String getName();

	boolean canSenderUseCommand(Optional<Channel> channel, User user);

	void processCommand(Optional<Channel> channel, User user, String[] args);

	void handleHelpRequest(User user);

}
