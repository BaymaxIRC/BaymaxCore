package baymaxirc.core.event;

import com.google.common.eventbus.Subscribe;
import org.pircbotx.hooks.types.*;
import org.pircbotx.hooks.events.*;

/**
 * @author shadowfacts
 */
public interface IEventHandler {

	/**
	 * Fired when there is a message, can be channel message, PM, action, etc.
	 */
	@Subscribe
	default void handleGenericMessageEvent(GenericMessageEvent event) {}

	/**
	 * Fired when a user sends an action, e.g. typing in a client, "/me eats food".
	 */
	@Subscribe
	default void handleActionEvent(ActionEvent event) {}

	/**
	 * Fired when the servers sends out information about each channel on the server.
	 */
	@Subscribe
	default void handleChannelInfoEvent(ChannelInfoEvent event) {}

	/**
	 * Fired when the bot successfully connects to the server.
	 */
	@Subscribe
	default void handleConnectEvent(ConnectEvent event) {}

	/**
	 * Fired when the bot gets disconnected from the server.
	 */
	@Subscribe
	default void handleDisconnectEvent(DisconnectEvent event) {}


	/**
	 * Fired when the bot is invited to a channel the bot is in.
	 */
	@Subscribe
	default void handleInviteEvent(InviteEvent event) {}

	/**
	 * Fired when someone (potentially the bot) joins a channel the bot is in.
	 */
	@Subscribe
	default void handleJoinEvent(JoinEvent event) {}

	/**
	 * Fired when someone (potentially the bot) is kicked from a channel the bot is in.
	 */
	@Subscribe
	default void handleKickEvent(KickEvent event) {}

	/**
	 * Fired when a message is sent to a channel the bot is in.
	 */
	@Subscribe
	default void handleMessageEvent(MessageEvent event) {}

	/**
	 * Fired when the channel mod is changed.
	 */
	@Subscribe
	default void handleModeEvent(ModeEvent event) {}

	/**
	 * Fired when the MOTD has finished being sent.
	 */
	@Subscribe
	default void handleMotdEvent(MotdEvent event) {}

	/**
	 * Fired whenever someone (potentially the bot) changes nicknames.
	 */
	@Subscribe
	default void handleNickChangeEvent(NickChangeEvent event) {}

	/**
	 * Fired when someone (potentially the bot) receives operator status.
	 */
	@Subscribe
	default void handleOpEvent(OpEvent event) {}

	/**
	 * Fired when someone (potentially the bot) receives owner status.
	 * Note: This is not supported on all IRC servers.
	 */
	@Subscribe
	default void handleOwnerEvent(OwnerEvent event) {}

	/**
	 * Fired when someone (potentially the bot) parts a channel the bot is in.
	 */
	@Subscribe
	default void handlePartEvent(PartEvent event) {}

	/**
	 * Fired when someone pings the bot.
	 */
	@Subscribe
	default void handlePingEvent(PingEvent event) {}

	/**
	 * Fired whenever the bot receives a private message.
	 */
	@Subscribe
	default void handlePrivateMessageEvent(PrivateMessageEvent event) {}

	/**
	 * Fired when someone (potentially the bot) quites a channel the bot is in.
	 */
	@Subscribe
	default void handleQuitEvent(QuitEvent event) {}

	/**
	 * Fired when the server pings the bot.
	 */
	@Subscribe
	default void handleServerPingEvent(ServerPingEvent event) {}

	/**
	 * Fired when a users sets the topic or when the bot joins the channel and learns the topic.
	 */
	@Subscribe
	default void handleTopicEvent(TopicEvent event) {}

	/**
	 * Fired when the bot receives a user list from the server.
	 */
	@Subscribe
	default void handlerUserListEvent(UserListEvent event) {}

	/**
	 * Fired when the mode of a user is changed.
	 */
	@Subscribe
	default void handleUserModeEvent(UserModeEvent event) {}

	/**
	 * Fired when someone (potentially the bot) receives voice status.
	 */
	@Subscribe
	default void handleVoiceEvent(VoiceEvent event) {}

	/**
	 * Fired when the bot receives a whois request.
	 */
	@Subscribe
	default void handleWhoisEvent(WhoisEvent event) {}

}
