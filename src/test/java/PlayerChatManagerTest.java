import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.Test;
import org.schema.game.network.objects.ChatMessage;

import api.listener.events.player.PlayerChatEvent;
import me.iron.mod.StarChat.manager.PlayerChatManager;

public class PlayerChatManagerTest {
    @Test (expected = IllegalArgumentException.class)
    public void rejectInvalidUrl() {
        PlayerChatManager manager = new PlayerChatManager("imNotAValidUrl.com");
    }

    @Test
    public void instantiateWithValidUrl() {
        PlayerChatManager manager = new PlayerChatManager("https://stackoverflow.com/questions/2230676/how-to-check-for-a-valid-url-in-java");

    }

    @Test
    public void noDoubleMessages() throws SQLException {
        PlayerChatManager manager = new PlayerChatManager("https://.owo.de");
        ChatMessage m = new ChatMessage();
        m.sender = "OwoSender";
        m.receiver = "all";
        m.text = "this is some text";
        m.receiverType = ChatMessage.ChatMessageType.CHANNEL;
        PlayerChatEvent event = new PlayerChatEvent(m, null);
        try {
            manager.handleChatEvent(event);
        } catch (NullPointerException ex) { //sideeffects causes nullpointer
            ;
        }
        assertFalse(manager.handleChatEvent(event)); //manager rejects same message
    }

    @Test
    public void noNonChannelMessages() {
        //make sure only channel types go through
        PlayerChatManager manager = new PlayerChatManager("https://.owo.de");
        for (ChatMessage.ChatMessageType type: ChatMessage.ChatMessageType.values()) {
            if (type.equals(ChatMessage.ChatMessageType.CHANNEL))
                continue;

            ChatMessage m = new ChatMessage();
            m.sender = "OwoSender";
            m.receiver = "all";
            m.text = "this is some text";
            m.receiverType = type;
            PlayerChatEvent event = new PlayerChatEvent(m, null);
            assertFalse(manager.handleChatEvent(event)); //manager rejects same message
        }
    }

    @Test
    public void onlyAllChannel() {
        //make sure only messages with receiver = "all" go through
        PlayerChatManager manager = new PlayerChatManager("https://.owo.de");
        for (String s: new String[]{"all","faction owobois","direct owoBoi to uwuGirl","public channel number one"}) {
            ChatMessage m = new ChatMessage();
            m.sender = "OwoSender";
            m.receiver = s;
            m.text = "this is some text";
            m.receiverType = ChatMessage.ChatMessageType.CHANNEL;
            PlayerChatEvent event = new PlayerChatEvent(m, null);
            if (!s.equalsIgnoreCase("all")) {
                assertFalse(manager.handleChatEvent(event)); //manager rejects same message
            } else {
                try {
                    manager.handleChatEvent(event);
                    fail();
                } catch (NullPointerException ignored) {
                    //desired outcome: passed far enough to crash
                }
            }
        }
    }
}
