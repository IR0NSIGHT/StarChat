import org.junit.Test;

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
}
