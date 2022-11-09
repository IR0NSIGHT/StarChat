package me.iron.mod.MYMODHERE.element.block.systems.chambers;

import org.schema.game.common.data.blockeffects.config.StatusEffectType;
import org.schema.game.common.data.element.ElementInformation;

import api.config.BlockConfig;
import me.iron.mod.MYMODHERE.ModMain;
import me.iron.mod.MYMODHERE.element.ElementManager;

/**
 * <Description>
 *
 * @author TheDerpGamer
 * @version 1.0 - [03/05/2022]
 */
public abstract class ChamberBlock {

	protected ElementInformation blockInfo;

	public ChamberBlock(String name, short generalId, float chamberCapacity, StatusEffectType... statusEffects) {
		blockInfo = BlockConfig.newChamber(ModMain.getInstance(), name, generalId, statusEffects);
		blockInfo.chamberCapacity = chamberCapacity;
		ElementManager.addChamber(this);
	}

	public ElementInformation getBlockInfo() {
		return blockInfo;
	}

	public short getId() {
		return blockInfo.getId();
	}

	public void addChildren(ChamberBlock... children) {
		for(ChamberBlock child : children) {
			child.blockInfo.chamberParent = getId();
			child.blockInfo.chamberPrerequisites.add(getId());
			blockInfo.chamberChildren.add(child.getId());
		}
	}

	public void setUpgrade(ChamberBlock upgrade) {
		addChildren(upgrade);
		upgrade.blockInfo.chamberMutuallyExclusive.addAll(blockInfo.chamberMutuallyExclusive);
		blockInfo.chamberUpgradesTo = upgrade.getId();
	}

	public void addExclusives(ChamberBlock... exclusives) {
		for(ChamberBlock exclusive : exclusives) {
			exclusive.blockInfo.chamberMutuallyExclusive.add(getId());
			blockInfo.chamberMutuallyExclusive.add(exclusive.getId());
		}
	}

	public abstract void initialize();
}
