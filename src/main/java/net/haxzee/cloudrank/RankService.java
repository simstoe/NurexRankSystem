package net.haxzee.cloudrank;

import lombok.Getter;
import net.haxzee.cloudrank.commands.RankCommand;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Plugin;

/**
 * @author haxzee
 * created on 26/08/2022
 */

public class RankService extends Plugin {
    @Getter
    private static RankService instance;
    @Getter
    private static String prefix;

    @Override
    public void onEnable() {
        instance = this;
        prefix = "§cRank §8× §7";

        ProxyServer.getInstance().getPluginManager().registerCommand(this, new RankCommand("rank"));
    }

    @Override
    public void onDisable() {

    }
}
