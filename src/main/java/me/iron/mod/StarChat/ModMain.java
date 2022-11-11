package me.iron.mod.StarChat;


import api.listener.events.controller.ServerInitializeEvent;
import api.mod.StarMod;
import me.iron.mod.StarChat.manager.ConfigManager;
import me.iron.mod.StarChat.manager.PlayerChatManager;

/**
 * StarMade mod starting template.
 *
 * @author TheDerpGamer
 * @version 1.0 - [03/05/2022]
 */
public class ModMain extends StarMod {

	//Instance
	private static ModMain instance;
	public static ModMain getInstance() {
		return instance;
	}
	public ModMain() { }

	@Override
	public void onEnable() {
		instance = this;
		configManager = new ConfigManager();
	}

	PlayerChatManager chatManager;

	public ConfigManager getConfigManager() {
		return configManager;
	}



	ConfigManager configManager;
	@Override
	public void onServerCreated(ServerInitializeEvent event) {
		super.onServerCreated(event);
		chatManager = new PlayerChatManager(getConfigManager().getWebHookUrl());
		chatManager.registerListeners();
	}

	@Override
	public void onDisable() {
		super.onDisable();
		if (chatManager != null)
			chatManager.disable();
	}
}
