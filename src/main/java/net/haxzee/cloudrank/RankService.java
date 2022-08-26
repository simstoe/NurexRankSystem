package net.haxzee.cloudrank;

import net.md_5.bungee.api.plugin.Plugin;

/**
 * @author haxzee
 * created on 26/08/2022
 */

public class RankService extends Plugin {
    private static RankService instance;
    private static String prefix;

    @Override
    public void onEnable() {
        instance = this;
        prefix = "";
    }

    @Override
    public void onDisable() {

    }
}
