package baymax.core.command;

import baymax.core.util.LogHelper;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.kitteh.irc.client.library.element.Channel;
import org.kitteh.irc.client.library.element.User;

import java.util.*;

/**
 * @author shadowfacts
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CommandManager implements CommandRegistrar {

	public static final CommandManager instance = new CommandManager();

	private final LogHelper log = LogHelper.getLogger("Baymax|CommandManager");

	private Map<String, Command> commands = new HashMap<>();

	@Override
	public void registerCommand(String name, Command command) {
		if (!commands.containsKey(name)) {
			commands.put(name, command);
		} else {
			log.error("Command %s is already registered", name);
		}
	}

	@Override
	public void unregisterCommand(String name) {
		if (commands.containsKey(name)) {
			commands.remove(name);
		} else {
			log.error("Command %s is not registered");
		}
	}

	public Set<String> getCommandNames() {
		return commands.keySet();
	}

	public boolean hasCommand(String command) {
		return getCommandNames().contains(command);
	}

	public Optional<Command> getCommand(String name) {
		return hasCommand(name) ? Optional.of(commands.get(name)) : Optional.empty();
	}

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
