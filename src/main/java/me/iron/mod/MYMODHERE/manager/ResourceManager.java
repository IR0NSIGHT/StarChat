package me.iron.mod.MYMODHERE.manager;

import java.util.HashMap;

import org.schema.schine.graphicsengine.forms.Sprite;

import api.utils.textures.StarLoaderTexture;
import me.iron.mod.MYMODHERE.ModMain;

/**
 * <Description>
 *
 * @author TheDerpGamer
 * @version 1.0 - [03/05/2022]
 */
public class ResourceManager {

	private static final String[] textureNames = {

	};

	private static final String[] spriteNames = {

	};

	private static final HashMap<String, StarLoaderTexture> textureMap = new HashMap<>();
	private static final HashMap<String, Sprite> spriteMap = new HashMap<>();

	public static void loadResources() {
		StarLoaderTexture.runOnGraphicsThread(new Runnable() {
			@Override
			public void run() {
				//Load Textures
				for(String texturePath : textureNames) {
					String textureName = texturePath.substring(texturePath.lastIndexOf('/') + 1);
					try {
						if(textureName.endsWith("icon")) {
							textureMap.put(textureName, StarLoaderTexture.newIconTexture(ModMain.getInstance().getJarBufferedImage("me/iron/mod/MYMODHERE/resources/textures/" + texturePath + ".png")));
						} else {
							textureMap.put(textureName, StarLoaderTexture.newBlockTexture(ModMain.getInstance().getJarBufferedImage("me/iron/mod/MYMODHERE/resources/textures/" + texturePath + ".png")));
						}
					} catch(Exception exception) {
						LogManager.logException("Failed to load texture \"" + texturePath + "\"", exception);
					}
				}

				//Load Sprites
				for(String spriteName : spriteNames) {
					try {
						spriteMap.put(spriteName, StarLoaderTexture.newSprite(ModMain.getInstance().getJarBufferedImage("me/iron/mod/MYMODHERE/resources/sprites/" + spriteName + ".png"), ModMain.getInstance(), spriteName, false, false));
						spriteMap.get(spriteName).setName(spriteName);
					} catch(Exception exception) {
						LogManager.logException("Failed to load sprite \"" + spriteName + "\"", exception);
					}
				}
			}
		});
	}

	public static StarLoaderTexture getTexture(String name) {
		return textureMap.get(name);
	}

	public static Sprite getSprite(String name) {
		return spriteMap.get(name);
	}
}
