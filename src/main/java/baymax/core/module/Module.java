package baymax.core.module;

import baymax.core.command.CommandRegistrar;
import com.typesafe.config.Config;

import java.util.List;

/**
 * @author shadowfacts
 */
public interface Module {

	String getName();

	void initializeConfig(Config config);

	boolean isEnabled();

	void registerCommands(CommandRegistrar registrar);

	void deregisterCommands(CommandRegistrar registrar);

	List<Object> getListeners();

}
