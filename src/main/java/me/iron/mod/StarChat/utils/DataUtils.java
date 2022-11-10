package me.iron.mod.StarChat.utils;

import api.DebugFile;
import api.common.GameClient;
import api.common.GameCommon;
import me.iron.mod.StarChat.ModMain;

/**
 * <Description>
 *
 * @author TheDerpGamer
 * @version 1.0 - [03/05/2022]
 */
public class DataUtils {

	public static String getResourcesPath() {
		return ModMain.getInstance().getSkeleton().getResourcesFolder().getPath().replace('\\', '/');
	}

	public static String getWorldDataPath() {
		String universeName = GameCommon.getUniqueContextId();
		if(!universeName.contains(":")) return getResourcesPath() + "/data/" + universeName;
		else {
			try {
				DebugFile.log("Client " + GameClient.getClientPlayerState().getName() + " attempted to illegally access server data.", null);
			} catch(Exception ignored) { }
			return null;
		}
	}
}
