package me.iron.mod.MYMODHERE;


import org.schema.schine.resource.ResourceLoader;

import api.config.BlockConfig;
import api.mod.StarMod;
import me.iron.mod.MYMODHERE.element.ElementManager;
import me.iron.mod.MYMODHERE.element.items.ExampleItem;
import me.iron.mod.MYMODHERE.manager.LogManager;
import me.iron.mod.MYMODHERE.manager.ResourceManager;

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

	@Override
	public void onResourceLoad(ResourceLoader loader) {
		ResourceManager.loadResources();
	}

	@Override
	public void onBlockConfigLoad(BlockConfig config) {
		ElementManager.addItem(new ExampleItem());

		ElementManager.initialize();
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
