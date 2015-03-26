package baymaxirc.core.util;

/**
 * Miscellaneous utilities.
 * @author shadowfacts
 */
public class MiscUtils {

	public static String getCommandFromString(String str) {
		return str.split(" ")[0].substring(1);
	}

}
