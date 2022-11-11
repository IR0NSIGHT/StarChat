package me.iron.mod.StarChat.manager;


import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import api.utils.simpleconfig.SimpleConfigContainer;
import api.utils.simpleconfig.SimpleConfigString;
import me.iron.mod.StarChat.ModMain;

public class ConfigManager {
	private SimpleConfigString webHookUrl;

	public ConfigManager() {
		readConfig();
	}

	void readConfig() {
		SimpleConfigContainer configContainer = new SimpleConfigContainer(
			ModMain.getInstance(),
			"properties",
			true
		);




		configContainer.readWriteFields();
		webHookUrl = new SimpleConfigString(
				configContainer,
				"discord_webhook_url", "discord.com/api/webhooks/1234/5678-owo",
				"the url to your discord webhook. please remove https://");

		//TODO wait till that becomes public
		Method method = null;
		try {
			method = configContainer.getClass().getDeclaredMethod("setIsNotRemote");
			method.setAccessible(true);
			Object r = method.invoke(configContainer);
		} catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
			throw new RuntimeException(e);
		}
		System.out.println(webHookUrl.getValue());
	}

	public String getWebHookUrl() {
		return "https://" + webHookUrl.getValue();
	}
}
