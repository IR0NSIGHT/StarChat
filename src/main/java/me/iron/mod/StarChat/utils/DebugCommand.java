package me.iron.mod.StarChat.utils;

import javax.annotation.Nullable;

import org.schema.game.common.data.player.PlayerState;
import org.schema.schine.common.language.Lng;
import org.schema.schine.network.server.ServerMessage;

import api.mod.StarMod;
import api.utils.game.chat.CommandInterface;
import me.iron.mod.StarChat.ModMain;

public class DebugCommand implements CommandInterface {
    @Override
    public String getCommand() {
        return "starchat";
    }

    @Override
    public String[] getAliases() {
        return new String[]{"starchat"};
    }

    @Override
    public String getDescription() {
        return "show info about starchat mod";
    }

    @Override
    public boolean isAdminOnly() {
        return true;
    }

    @Override
    public boolean onCommand(PlayerState playerState, String[] strings) {
        playerState.sendServerMessage(Lng.astr(ModMain.getInstance().getConfigManager().getWebHookUrl()), ServerMessage.MESSAGE_TYPE_SIMPLE);
        return true;
    }

    @Override
    public void serverAction(@Nullable PlayerState playerState, String[] strings) {
        ;//owo
    }

    @Override
    public StarMod getMod() {
        return ModMain.getInstance();
    }
}
