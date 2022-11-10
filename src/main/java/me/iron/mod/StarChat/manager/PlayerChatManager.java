package me.iron.mod.StarChat.manager;

import java.io.IOException;

import org.schema.game.common.data.chat.AllChannel;
import org.schema.game.common.data.player.PlayerState;
import org.schema.game.network.objects.ChatMessage;
import org.schema.game.server.data.GameServerState;

import api.DebugFile;
import api.listener.Listener;
import api.listener.events.player.PlayerChatEvent;
import api.mod.StarLoader;
import me.iron.mod.StarChat.ModMain;
import me.iron.mod.StarChat.utils.DiscordWebhook;

public class PlayerChatManager {
    private int lastMessageHash;
    private DiscordWebhook webhook;
    public PlayerChatManager(String webhookUrl) {
        this.generalChatChannel = new AllChannel(null, -1);
        webhook = new DiscordWebhook(webhookUrl);

        StarLoader.registerListener(
                PlayerChatEvent.class,
                new Listener<PlayerChatEvent>() {
                    @Override
                    public void onEvent(PlayerChatEvent event) {
                        int thisHash = event.getMessage().sender.hashCode()+event.getMessage().text.hashCode();
                        if (thisHash == lastMessageHash || !isGeneralChat(event.getMessage()))
                            return;
                        lastMessageHash = thisHash;

                        PlayerState sender = GameServerState.instance.getPlayerFromNameIgnoreCaseWOException(event.getMessage().sender);
                        if (sender == null)
                            return; //not a player

                        DebugFile.log("sender: " + sender + " receiver: " + event.getMessage().receiver + " type:" + event.getMessage().receiverType + " text: " + event.getMessage().text);
                        try {
                            playerChat(
                                    event.getMessage().text,
                                    sender.getName(),
                                    sender.getFactionName()
                            );
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }


                    }
                },ModMain.getInstance()
            );
    }

    private AllChannel generalChatChannel;

    boolean senderIsPlayer(String sender) {
        return null != GameServerState.instance.getPlayerFromNameIgnoreCaseWOException(sender);
    }

    boolean isGeneralChat(ChatMessage chat) {
       return chat.receiverType.equals(ChatMessage.ChatMessageType.CHANNEL) &&
                chat.receiver.equalsIgnoreCase(generalChatChannel.getUniqueChannelName());
    }

    public void playerChat(String message, String playerName, String playerFactionName) throws IOException {
        //DebugFile.log("m: " + message + " p: " + playerName + " f: " + playerFactionName);
        //webhook.setUsername(playerName + "["+ playerFactionName+"]");
        //webhook.setContent(message);
        //webhook.execute();
    }
}
