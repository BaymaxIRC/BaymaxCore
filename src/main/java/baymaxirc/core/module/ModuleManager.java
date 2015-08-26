package baymaxirc.core.module;

import baymaxirc.core.Baymax;
import baymaxirc.core.event.IEventHandler;
import net.shadowfacts.shadowlib.util.ClasspathUtils;
import net.shadowfacts.shadowlib.util.FileUtils;
import org.reflections.Reflections;

import java.io.File;
import java.util.ArrayList;
import java.util.Set;

/**
 * Manages all Baymax modules.
 * @author shadowfacts
 */
public class ModuleManager {

	private ArrayList<String> loadedModules = new ArrayList<>();

	public void loadModules() {
		FileUtils.forEachInDirectory(new File("./modules/"), ClasspathUtils::addFileToClasspath);

		Reflections reflections = new Reflections();
		Set<Class<? extends IModule>> classes = reflections.getSubTypesOf(IModule.class);
		for (Class<? extends IModule> clazz : classes) {
			IModule instance;
			try {
				instance = clazz.newInstance();
			} catch (Exception e) {
				Baymax.logger.error("There was an error loading the module %s, skipping.", clazz.getSimpleName());
				continue;
			}
			if (!loadedModules.contains(instance.getName())) {
				Baymax.logger.info("Loading %s.", instance.getName());
				IEventHandler handler = instance.getEventHandler();
				if (handler != null) {
					Baymax.eventBus.register(handler);
				}
				instance.registerCommands(Baymax.commandManager);
				Baymax.logger.info("Finished loading %s.", instance.getName());
			} else {
				Baymax.logger.error("The module %s was already loaded, skipping.", instance.getName());
			}
		}
	}

}
