package me.iron.mod.MYMODHERE.element.items;

import org.schema.game.common.data.element.ElementCategory;
import org.schema.game.common.data.element.ElementInformation;

import api.config.BlockConfig;
import me.iron.mod.MYMODHERE.ModMain;

/**
 * <Description>
 *
 * @author TheDerpGamer
 * @version 1.0 - [03/23/2022]
 */
public abstract class Item {

	protected ElementInformation itemInfo;

	public Item(String name, ElementCategory category) {
		itemInfo = BlockConfig.newElement(ModMain.getInstance(), name, new short[6]);
		itemInfo.setPlacable(false);
		itemInfo.setPhysical(false);
		BlockConfig.setElementCategory(itemInfo, category);
	}

	public final ElementInformation getItemInfo() {
		return itemInfo;
	}

	public final short getId() {
		return itemInfo.getId();
	}

	public abstract void initialize();
}