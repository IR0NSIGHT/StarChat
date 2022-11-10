package me.iron.mod.StarChat.manager;


import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

import org.schema.game.common.data.chat.AllChannel;
import org.schema.game.common.data.player.PlayerState;
import org.schema.game.network.objects.ChatMessage;
import org.schema.game.server.data.GameServerState;

import api.DebugFile;
import api.listener.Listener;
import api.listener.events.player.PlayerChatEvent;
import api.mod.StarLoader;
import me.iron.mod.StarChat.ModMain;

public class PlayerChatManager {
    private int lastMessageHash;
    private MessageSenderThread senderThread;

    /**
     * instantiates manager and separate sender thread.
     * @param webhookUrl url for webhook to send message to
     */
    public PlayerChatManager(String webhookUrl) {
        try {
            URL u = new URL(webhookUrl); // this would check for the protocol
            u.toURI(); // does the e
        } catch (MalformedURLException | URISyntaxException ex) {
            throw new IllegalArgumentException("StarChat requires you to set a valid url for the discordhook in moddata/StarChat/properties");
        }

        this.generalChatChannel = new AllChannel(null, -1);
        senderThread = new MessageSenderThread(webhookUrl);
        senderThread.start();
    }

    public void registerListeners() {
        StarLoader.registerListener(
                PlayerChatEvent.class,
                new Listener<PlayerChatEvent>() {
                    @Override
                    public void onEvent(PlayerChatEvent event) {
                        handleChatEvent(event);
                    }
                },ModMain.getInstance()
        );
    }

    public void disable() {
        senderThread.terminate();
    }


    void handleChatEvent(PlayerChatEvent event) {
        int thisHash = event.getMessage().sender.hashCode()+event.getMessage().text.hashCode()+event.getMessage().receiver.hashCode()+event.getMessage().receiverType.hashCode();
        if (thisHash == lastMessageHash || !isGeneralChat(event.getMessage()))
            return;
        lastMessageHash = thisHash;

        PlayerState sender = GameServerState.instance.getPlayerFromNameIgnoreCaseWOException(event.getMessage().sender);
        if (sender == null)
            return; //not a player
        try {
            queueDiscordMessage(
                    event.getMessage().text,
                    sender.getName(),
                    sender.getFactionName()
            );
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private AllChannel generalChatChannel;

    boolean isGeneralChat(ChatMessage chat) {
       return chat.receiverType.equals(ChatMessage.ChatMessageType.CHANNEL) &&
                chat.receiver.equalsIgnoreCase(generalChatChannel.getUniqueChannelName());
    }

    public void queueDiscordMessage(String message, String playerName, String playerFactionName) throws InterruptedException {
        DebugFile.log("queue message m: " + message + " p: " + playerName + " f: " + playerFactionName);
        ChatMessage m = new ChatMessage();
        m.sender = playerName + " ["+playerFactionName+"]";
        m.text = message;

        senderThread.messageQueue.put(m);
    }

    String sanitizeString(String s) {
        s = s.replaceAll("@","_at_");
        s = s.replaceAll("\"","'");
        return s;
    }
}
