package baymaxirc.core.api.event;

import org.pircbotx.hooks.types.*;
import org.pircbotx.hooks.events.*;

/**
 * @author shadowfacts
 */
public interface IEventHandler {

	/**
	 * Fired when there is a message, can be channel message, PM, action, etc.
	 */
	default void handleGenericMessage(GenericMessageEvent event) {}

	/**
	 * Fired when a user sends an action, e.g. typing in a client, "/me eats food".
	 */
	default void handleAction(ActionEvent event) {}

	/**
	 * Fired when the servers sends out information about each channel on the server.
	 */
	default void handleChannelInfo(ChannelInfoEvent event) {}

	/**
	 * Fired when the bot successfully connects to the server.
	 */
	default void handleConnect(ConnectEvent event) {}

	/**
	 * Fired when the bot gets disconnected from the server.
	 */
	default void handleDisconnect(DisconnectEvent event) {}


	/**
	 * Fired when the bot is invited to a channel the bot is in.
	 */
	default void handleInvite(InviteEvent event) {}

	/**
	 * Fired when someone (potentially the bot) joins a channel the bot is in.
	 */
	default void handleJoin(JoinEvent event) {}

	/**
	 * Fired when someone (potentially the bot) is kicked from a channel the bot is in.
	 */
	default void handleKick(KickEvent event) {}

	/**
	 * Fired when a message is sent to a channel the bot is in.
	 */
	default void handleChannelMessage(MessageEvent event) {}

	/**
	 * Fired when the channel mod is changed.
	 */
	default void handleChannelModeChange(ModeEvent event) {}

	/**
	 * Fired when the MOTD has finished being sent.
	 */
	default void handleMOTD(MotdEvent event) {}

	/**
	 * Fired whenever someone (potentially the bot) changes nicknames.
	 */
	default void handleNickChange(NickChangeEvent event) {}

	/**
	 * Fired when someone (potentially the bot) receives operator status.
	 */
	default void handleOp(OpEvent event) {}

	/**
	 * Fired when someone (potentially the bot) receives owner status.
	 * Note: This is not supported on all IRC servers.
	 */
	default void handleOwner(OwnerEvent event) {}

	/**
	 * Fired when someone (potentially the bot) parts a channel the bot is in.
	 */
	default void handlePart(PartEvent event) {}

	/**
	 * Fired when someone pings the bot.
	 */
	default void handlePing(PingEvent event) {}

	/**
	 * Fired whenever the bot receives a private message.
	 */
	default void handlePrivateMessage(PrivateMessageEvent event) {}

	/**
	 * Fired when someone (potentially the bot) quites a channel the bot is in.
	 */
	default void handleQuit(QuitEvent event) {}

	/**
	 * Fired when the server pings the bot.
	 */
	default void handleServerPing(ServerPingEvent event) {}

	/**
	 * Fired when a users sets the topic or when the bot joins the channel and learns the topic.
	 */
	default void handleTopic(TopicEvent event) {}

	/**
	 * Fired when the bot receives a user list from the server.
	 */
	default void handlerUserList(UserListEvent event) {}

	/**
	 * Fired when the mode of a user is changed.
	 */
	default void handleUserModeChange(UserModeEvent event) {}

	/**
	 * Fired when someone (potentially the bot) receives voice status.
	 */
	default void handleVoice(VoiceEvent event) {}

	/**
	 * Fired when the bot receives a whois request.
	 */
	default void handleWhois(WhoisEvent event) {}

}
