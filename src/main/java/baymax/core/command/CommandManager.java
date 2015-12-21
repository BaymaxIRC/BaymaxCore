package baymax.core.command;

import baymax.core.util.LogHelper;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.kitteh.irc.client.library.element.Channel;
import org.kitteh.irc.client.library.element.User;

import java.util.*;

/**
 * Manages commands
 *
 * @author shadowfacts
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CommandManager implements CommandRegistrar {

	public static final CommandManager instance = new CommandManager();

	private final LogHelper log = LogHelper.getLogger("Baymax|CommandManager");

	/**
	 * The commands in the form of Name -> Command instance
	 */
	private Map<String, Command> commands = new HashMap<>();

	/**
	 * Registers a command
	 * @param name The command name
	 * @param command The command instance
	 */
	@Override
	public void registerCommand(String name, Command command) {
		if (!commands.containsKey(name)) {
			commands.put(name, command);
		} else {
			log.error("Command %s is already registered", name);
		}
	}

	/**
	 * Unregisters a command
	 * @param name The command name
	 */
	@Override
	public void unregisterCommand(String name) {
		if (commands.containsKey(name)) {
			commands.remove(name);
		} else {
			log.error("Command %s is not registered");
		}
	}

	/**
	 * @return A set of the names of all the registered commands
	 */
	public Set<String> getCommandNames() {
		return commands.keySet();
	}

	/**
	 * @param command The command name
	 * @return If the given command exists
	 */
	public boolean hasCommand(String command) {
		return getCommandNames().contains(command);
	}

	/**
	 * Retrieves a command with the given name
	 * @param name The command name
	 * @return the command, {@link Optional#empty()} if there is no such command
	 */
	public Optional<Command> getCommand(String name) {
		return hasCommand(name) ? Optional.of(commands.get(name)) : Optional.empty();
	}

	/**
	 * Attempts to handle a command from a user message
	 * @param channel The channel, {@link Optional#empty()} if this is taking place in a private message
	 * @param user The user who attempted to execute the command
	 * @param message The message the user sent
	 */
	public void tryHandleCommand(Optional<Channel> channel, User user, String message) {
		String[] bits = message.split(" ");
		String commandName = bits[0].substring(1, bits[0].length());
		if (commands.containsKey(commandName)) {

			Command command = commands.get(commandName);

			if (command.canSenderUseCommand(channel, user)) {

				command.processCommand(channel, user, Arrays.copyOfRange(bits, 1, bits.length));

			} else {
				user.sendMessage("Permission denied to execute command `" + commandName + "`");
			}

		} else {
			user.sendMessage("No such command `" + commandName + "`");
		}
	}

}
