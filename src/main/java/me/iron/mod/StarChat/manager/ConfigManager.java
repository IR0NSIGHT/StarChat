package me.iron.mod.StarChat.manager;


import api.utils.simpleconfig.SimpleConfigContainer;
import api.utils.simpleconfig.SimpleConfigString;
import me.iron.mod.StarChat.ModMain;

/**
 * <Description>
 *
 * @author TheDerpGamer
 * @version 1.0 - [03/05/2022]
 */
public class ConfigManager {
	private String webHookUrl;

	public ConfigManager() {
		readConfig();
	}

	void readConfig() {
		SimpleConfigContainer configContainer = new SimpleConfigContainer(
			ModMain.getInstance(),
			"properties",
			true
		);
		SimpleConfigString hookUrl = new SimpleConfigString(configContainer, "discord_webhook_url", "", "the url to your discord webhook.");
		configContainer.readWriteFields();
		webHookUrl = hookUrl.getValue();

	}


	public String getWebHookUrl() {
		return webHookUrl;
	}
}
