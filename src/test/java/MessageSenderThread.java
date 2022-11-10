import java.io.IOException;
import java.net.MalformedURLException;

import org.junit.Test;
import org.schema.game.network.objects.ChatMessage;


public class MessageSenderThread {
    @Test (expected = IllegalArgumentException.class)
    public void emptyChatMessage() throws  IOException {
        me.iron.mod.StarChat.manager.MessageSenderThread myThread = new me.iron.mod.StarChat.manager.MessageSenderThread("");
        myThread.sendDiscordMessage(new ChatMessage());
    }

    @Test (expected = MalformedURLException.class)
    public void malformedUrlDiscordHook() throws IOException {
        me.iron.mod.StarChat.manager.MessageSenderThread myThread = new me.iron.mod.StarChat.manager.MessageSenderThread("");
        ChatMessage m = new ChatMessage();
        m.sender = "test";
        m.text = "test message";
        myThread.sendDiscordMessage(m);
    }

    @Test
    public void deadUrlDiscordHook() throws IOException {
        //TODO throw error with url is not actually a discord thing
        me.iron.mod.StarChat.manager.MessageSenderThread myThread = new me.iron.mod.StarChat.manager.MessageSenderThread("https://www.geeksforgeeks.org/killing-threads-in-java/");
        ChatMessage m = new ChatMessage();
        m.sender = "test";
        m.text = "test message";
        myThread.sendDiscordMessage(m);
    }

    @Test
    public void invalidJsonString() throws IOException {
        String testHook = "https://discord.com/api/webhooks/1039982257491943528/6PvtA-ub8k7TkgT1lpSeb_OhZmZDecVcjONePw7XXMPay6P6b7LOgreQrTYAwE7fnIn7";
        //TODO throw error with url is not actually a discord thing
        me.iron.mod.StarChat.manager.MessageSenderThread myThread = new me.iron.mod.StarChat.manager.MessageSenderThread(testHook);
        ChatMessage m = new ChatMessage();
        m.sender = "test";
        m.text = "\"test\n\t:,   \"\\\\#} message\"";
        myThread.sendDiscordMessage(m);
    }
}
