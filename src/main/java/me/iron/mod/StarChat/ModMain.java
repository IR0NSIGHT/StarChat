package me.iron.mod.StarChat;


import api.mod.StarMod;
import me.iron.mod.StarChat.manager.LogManager;

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
		LogManager.initialize();
		registerListeners();
		registerPackets();
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
