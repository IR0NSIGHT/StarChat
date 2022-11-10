package me.iron.mod.StarChat.manager;

import java.io.IOException;
import java.util.concurrent.SynchronousQueue;

import org.schema.game.network.objects.ChatMessage;

import me.iron.mod.StarChat.utils.DiscordWebhook;

public class MessageSenderThread extends Thread{
    private DiscordWebhook hook;
    private boolean run = true;
    public SynchronousQueue<ChatMessage>
            messageQueue = new SynchronousQueue<>();

    public MessageSenderThread(String discordWebhookUrl) {
        hook = new DiscordWebhook(discordWebhookUrl);
    }

    public void terminate() {
        run = false;
    }

    @Override
    public void run() {
        super.run();
        while(run) {
            consumeQueue();
        }
    }

    public void consumeQueue() {
        while (!messageQueue.isEmpty())
            try {
                ChatMessage m = messageQueue.take();
                sendDiscordMessage(m);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
    }

    public void sendDiscordMessage(ChatMessage m) throws IOException {
        if (m.text == null || m.sender == null)
            throw new IllegalArgumentException("chatmessage is missing text or sender as body: " + m.toDetailString());
        hook.setContent(m.text);
        hook.setUsername(m.sender);
        hook.execute();
    }
}
