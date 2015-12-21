package baymax.core.factoid;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.kitteh.irc.client.library.element.User;

/**
 * @author shadowfacts
 */
@Getter
@AllArgsConstructor
public class Factoid {

	private String channel;
	private String name;
	private String factoid;
	private User creator;

}
