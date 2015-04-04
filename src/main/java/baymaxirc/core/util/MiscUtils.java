package baymaxirc.core.util;

import baymaxirc.core.Baymax;

/**
 * Miscellaneous utilities.
 * @author shadowfacts
 */
public class MiscUtils {

	public static String getCommandFromString(String str) {
		return str.split(" ")[0].substring(Baymax.commandStr.length());
	}

}
