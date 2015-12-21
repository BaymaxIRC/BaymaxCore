package baymax.core.factoid;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.kitteh.irc.client.library.element.User;

/**
 * A factoid
 *
 * @author shadowfacts
 */
@Getter
@AllArgsConstructor
public class Factoid {

	/**
	 * The channel for the factoid. Every factoid has 1 channel.
	 */
	private String channel;

	/**
	 * The name the factoid has. The name is used to execute, update, and remove the factoid
	 */
	private String name;

	/**
	 * The information the factoid contains
	 */
	private String factoid;

	/**
	 * The nick of the person who created/updated the factoid
	 */
	private String creator;

	public Factoid(String channel, String name, String factoid, User creator) {
		this(channel, name, factoid, creator.getNick());
	}

}
