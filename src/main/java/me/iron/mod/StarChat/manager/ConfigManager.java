package me.iron.mod.StarChat.manager;


import api.utils.simpleconfig.SimpleConfigContainer;
import api.utils.simpleconfig.SimpleConfigString;
import me.iron.mod.StarChat.ModMain;

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
		SimpleConfigString hookUrl = new SimpleConfigString(
				configContainer,
				"discord_webhook_url", "discord.com/api/webhooks/1234/5678-owo",
				"the url to your discord webhook. please remove https://");
		webHookUrl = "https://"+hookUrl.getValue();
	}

	public String getWebHookUrl() {
		return webHookUrl;
	}
}
