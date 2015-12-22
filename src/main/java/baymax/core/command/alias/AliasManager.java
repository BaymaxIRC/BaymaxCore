package baymax.core.command.alias;

import baymax.core.command.CommandManager;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

/**
 * @author shadowfacts
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AliasManager {

	public static final AliasManager instance = new AliasManager();

	private Map<String, CommandAlias> aliases = new HashMap<>();
//	TODO: Make aliases persist

	public void addAlias(String name, String target) {
		if (!aliases.containsKey(name) && !CommandManager.instance.hasCommand(name)) {
			CommandAlias aliasCommand = new CommandAlias(name, target);
			aliases.put(name, aliasCommand);
			CommandManager.instance.registerCommand(name, aliasCommand);
		}
	}

	public void removeAlias(String name) {
		if (aliases.containsKey(name)) {
			aliases.remove(name);
			CommandManager.instance.unregisterCommand(name);
		}
	}

}
