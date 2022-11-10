package me.iron.mod.StarChat;


import api.listener.events.controller.ServerInitializeEvent;
import api.mod.StarMod;
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
	public static void main(String[] args) { }
	public ModMain() { }

	@Override
	public void onEnable() {
		instance = this;
		registerListeners();
		registerPackets();
	}

	@Override
	public void onServerCreated(ServerInitializeEvent event) {
		super.onServerCreated(event);
		new PlayerChatManager("https://discord.com/api/webhooks/1039982257491943528/6PvtA-ub8k7TkgT1lpSeb_OhZmZDecVcjONePw7XXMPay6P6b7LOgreQrTYAwE7fnIn7");
	}

	/**
	 * Use to register mod listeners.
	 */
	private void registerListeners() {

	}

	/**
	 * Use to register mod packets.
	 */
	private void registerPackets() {

	}
}
