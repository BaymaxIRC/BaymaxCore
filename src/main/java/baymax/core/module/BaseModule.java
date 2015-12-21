package baymax.core.module;

import baymax.core.command.CommandRegistrar;
import com.typesafe.config.Config;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * Basic module that only requires {@link Module#getName()} to be implemented
 *
 * @author shadowfacts
 */
public abstract class BaseModule implements Module {

	@Getter
	private List<Object> listeners = new ArrayList<>();

	@Override
	public void initializeConfig(Config config) {

	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public void registerCommands(CommandRegistrar registrar) {

	}

}
