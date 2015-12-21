package baymax.core.factoid;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.kitteh.irc.client.library.element.Channel;
import org.kitteh.irc.client.library.element.User;

import java.util.*;

/**
 * @author shadowfacts
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FactoidManager {

	public static final FactoidManager instance = new FactoidManager();

	private Map<String, List<Factoid>> factoids = new HashMap<>();

//	TODO: make factoids persist

	public boolean hasFactoid(String channel, String name) {
		if (!factoids.containsKey(channel)) {
			return false;
		}
		Optional<Factoid> factoid = factoids.get(channel).stream()
				.filter(f -> f.getName().equals(name))
				.findAny();
		return factoid.isPresent();
	}

	public void setFactoid(String channel, Factoid factoid) {
		if (!factoids.containsKey(channel)) {
			factoids.put(channel, new ArrayList<>());
		}
		if (!hasFactoid(channel, factoid.getName())) {
			factoids.get(channel).add(factoid);
		}
	}

	public void removeFactoid(String channel, String name) {
		if (factoids.containsKey(channel)) {
			Optional<Factoid> factoid = factoids.get(channel).stream()
					.filter(f -> f.getName().equals(name))
					.findAny();
			if (factoid.isPresent()) {
				factoids.get(channel).remove(factoid.get());
			}
		}
	}

	public void tryHandleFactoid(Channel channel, User user, String message) {
		if (factoids.containsKey(channel.getName())) {
			String name = message.split(" ")[0].substring(2, message.split(" ")[0].length());

			Optional<Factoid> factoid = factoids.get(channel.getName()).stream()
					.filter(f -> f.getName().equals(name))
					.findAny();
			if (factoid.isPresent()) {
				channel.sendMessage(user.getNick() + ", " + factoid.get().getFactoid());
			} else {
				channel.sendMessage(user.getNick() + ", no such factoid `" + name + "`");
			}
		}
	}

}
