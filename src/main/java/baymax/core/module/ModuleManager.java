package baymax.core.module;

import baymax.core.Baymax;
import baymax.core.command.CommandRegistrar;
import baymax.core.config.ConfigManager;
import baymax.core.util.LogHelper;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.shadowfacts.shadowlib.util.ClasspathUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.jar.Attributes;
import java.util.jar.JarInputStream;
import java.util.jar.Manifest;

/**
 * @author shadowfacts
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ModuleManager {

	private static final String MODULE_NAME = "Baymax-Module-Name";
	private static final String MODULE_VERSION = "Baymax-Module-Version";
	private static final String MODULE_CLASS = "Baymax-Module-Class";

	public static final ModuleManager instance = new ModuleManager();

	private final LogHelper log = LogHelper.getLogger("Baymax|ModuleManager");

	@Getter
	private Map<String, Module> modules = new HashMap<>();

	public void loadModules() {
		File[] moduleFiles = Baymax.instance.getModulesDir().listFiles();
		if (moduleFiles != null) {
			Arrays.stream(moduleFiles)
					.filter(f -> f.getName().endsWith(".jar"))
					.forEach(f -> {
						ClasspathUtils.addFileToClasspath(f);

						try (JarInputStream jarInputStream = new JarInputStream(new FileInputStream(f))) {
							Manifest manifest = jarInputStream.getManifest();
							Attributes attributes = manifest.getMainAttributes();

							if (attributes.containsKey(MODULE_NAME)) {
								String name = attributes.getValue(MODULE_NAME);
								String version = attributes.getValue(MODULE_VERSION);
								String className = attributes.getValue(MODULE_CLASS);

								log.info(String.format("Attempting to load module %s @ %s", name, version));
								try {
									Class moduleClass = Class.forName(className);
									Module module = (Module)moduleClass.newInstance();

									module.initializeConfig(ConfigManager.instance.getModuleConfig(module));

									if (module.isEnabled()) {

										modules.put(module.getName(), module);

									} else {
										log.info("Module %s is disabled, skipping", module.getName());
									}

								} catch (ClassNotFoundException e) {
									log.error(e, "Main module class %s for module %s @ %s could not be found", className, name, version);
								} catch (IllegalAccessException e) {
									log.error(e, "Main module class %s for module %s @ %s must provide public, no-args constructor", className, name, version);
								} catch (InstantiationException e) {
									log.error(e, "Main module class %s for module %s @ %s could not be constructed", className, name, version);
								}
							}
						} catch (IOException e) {
							log.error(e, "Problem reading jar manifest for %s", f.getName());
						}
					});
		}
	}

	public void registerCommands(CommandRegistrar registrar) {
		modules.values().stream()
				.forEach(module -> module.registerCommands(registrar));
	}

}
