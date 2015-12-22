package baymax.core.permission;

import baymax.core.Baymax;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.kitteh.irc.client.library.element.User;

/**
 * @author shadowfacts
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PermissionsManager {

	public static final PermissionsManager instance = new PermissionsManager();

	public boolean isAdmin(User user) {
		return Baymax.instance.getConfig().getStringList("baymax.admins").contains(user.getNick());
	}

}
