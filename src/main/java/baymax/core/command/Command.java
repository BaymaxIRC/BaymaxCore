package baymax.core.command;

import org.kitteh.irc.client.library.element.Channel;
import org.kitteh.irc.client.library.element.User;

import java.util.Optional;

/**
 * A command handled by Baymax.
 *
 * @author shadowfacts
 */
public interface Command {

	/**
	 * @return The command name
	 */
	String getName();

	/**
	 * Checks if the user is allowed to use the command
	 * @param channel The channel, {@link Optional#empty()} if in private message
	 * @param user The user attempting to execute the command
	 * @return If the user can use the command
	 */
	boolean canSenderUseCommand(Optional<Channel> channel, User user);

	/**
	 * Handle the execution of the command
	 * @param channel The channel, {@link Optional#empty()} if in private message
	 * @param user The user executing the command
	 * @param args The arguments being passed to the command
	 */
	void processCommand(Optional<Channel> channel, User user, String[] args);

	/**
	 * Called when the user runs the `help` command on this command.
	 * Typically private messages the user information about the command
	 * @param user The user requesting help
	 */
	void handleHelpRequest(User user);

}
