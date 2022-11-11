import static org.junit.Assert.*;

import java.io.IOException;
import java.net.MalformedURLException;

import org.junit.Test;
import org.schema.game.network.objects.ChatMessage;

import me.iron.mod.StarChat.manager.MessageSenderThread;

public class MessageSenderThreadTest {
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
        myThread.setDryRun(true);
        myThread.sendDiscordMessage(m);
    }

    @Test
    public void deadUrlDiscordHook() throws IOException {
        //TODO throw error with url is not actually a discord thing
        me.iron.mod.StarChat.manager.MessageSenderThread myThread = new me.iron.mod.StarChat.manager.MessageSenderThread("https://www.geeksforgeeks.org/killing-threads-in-java/");
        ChatMessage m = new ChatMessage();
        m.sender = "test";
        m.text = "test message";
        myThread.setDryRun(true);
        myThread.sendDiscordMessage(m);
    }

    @Test
    public void queueConsumption() throws InterruptedException {
        MessageSenderThread myThread = new MessageSenderThread("owoboi.9000");
        myThread.setDryRun(true);
        //fill queue while thread is idle
        for (int i = 0; i< 10; i++) {
            ChatMessage m = new ChatMessage();
            m.text = "owo the " + i;
            m.sender = "owoBoi900"+i;
            try {
                myThread.messageQueue.put(m);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
        assertEquals(10, myThread.messageQueue.size());

        //start thread, automatically starts consuming queue, give it time to consume all
        myThread.start();
        Thread.sleep(50);
        myThread.terminate();
        Thread.sleep(50);
        assertEquals(0, myThread.messageQueue.size());
    }
}
