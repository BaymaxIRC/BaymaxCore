package baymax.core.util;

import java.util.logging.LogManager;
import java.util.logging.Logger;

/**
 * @author shadowfacts
 */
public class LogHelper {

	private Logger log;

	private LogHelper(String name) {
		log = LogManager.getLogManager().getLogger(name);
	}

	public void error(String msg, Object... params) {
		log.severe(String.format(msg, params));
	}

	public void error(Throwable t, String msg, Object... params) {
		error(msg, params);
		t.printStackTrace();
	}

	public void warn(String msg, Object... params) {
		log.warning(String.format(msg, params));
	}

	public void info(String msg, Object... params) {
		log.info(String.format(msg, params));
	}

	public void config(String msg, Object... params) {
		log.config(String.format(msg, params));
	}

	public void fine(String msg, Object... params) {
		log.fine(String.format(msg, params));
	}

	public void finer(String msg, Object... params) {
		log.finer(String.format(msg, params));
	}

	public void finest(String msg, Object... params) {
		log.finest(String.format(msg, params));
	}

	public static LogHelper getLogger(String name) {
		return new LogHelper(name);
	}

}
