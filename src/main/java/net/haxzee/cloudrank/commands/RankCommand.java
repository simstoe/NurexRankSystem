package net.haxzee.cloudrank.commands;

import de.dytanic.cloudnet.driver.CloudNetDriver;
import net.haxzee.cloudrank.RankService;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

/**
 * @author haxzee
 * created on 26/08/2022
 */

public class RankCommand extends Command {
    public RankCommand(String name) {
        super("rank");
    }

    @Override
    public void execute(CommandSender commandSender, String[] args) {
        ProxiedPlayer proxiedPlayer = (ProxiedPlayer) commandSender;

        if (!proxiedPlayer.hasPermission("nurex.rankcommand")) {
            proxiedPlayer.sendMessage(TextComponent.fromLegacyText(RankService.getPrefix() + "§cKeine Rechte!"));
            return;
        }

        if (args.length < 3) {
            sendErrorMessage(proxiedPlayer);
            return;
        }

        ProxiedPlayer target = ProxyServer.getInstance().getPlayer(args[1]);
        String groupName = args[2];

        switch (args[0]) {
            case "add":
                String time = args[3];
                if (target == null) {
                    proxiedPlayer.sendMessage(TextComponent.fromLegacyText(RankService.getPrefix() + "§cDer Spieler ist nicht online!"));
                    return;
                }
                if (CloudNetDriver.getInstance().getPermissionManagement().getGroup(groupName) == null) {
                    proxiedPlayer.sendMessage(TextComponent.fromLegacyText(RankService.getPrefix() + "§cDiese Gruppe gibt es nicht!"));
                    return;
                }
                if (time.equals("P")) {
                    CloudNetDriver.getInstance().getNodeInfoProvider().sendCommandLine("perms user " + target + " add group " + groupName);
                } else {
                    CloudNetDriver.getInstance().getNodeInfoProvider().sendCommandLine("perms user " + target + " add group " + groupName + " " + time);
                }

                proxiedPlayer.sendMessage(TextComponent.fromLegacyText(RankService.getPrefix() + "Die Gruppe wurde hinzugefügt!"));
                break;
            case "remove":
                if (target == null) {
                    proxiedPlayer.sendMessage(TextComponent.fromLegacyText(RankService.getPrefix() + "§cDer Spieler ist nicht online!"));
                    return;
                }
                if (CloudNetDriver.getInstance().getPermissionManagement().getGroup(groupName) == null) {
                    proxiedPlayer.sendMessage(TextComponent.fromLegacyText(RankService.getPrefix() + "§cDiese Gruppe gibt es nicht!"));
                    return;
                }
                CloudNetDriver.getInstance().getNodeInfoProvider().sendCommandLine("perms user " + target + " remove group " + groupName);
                proxiedPlayer.sendMessage(TextComponent.fromLegacyText(RankService.getPrefix() + "Die Gruppe wurde removed!"));
                break;
            default:
                sendErrorMessage(proxiedPlayer);
                break;

        }
    }

    private void sendErrorMessage(ProxiedPlayer proxiedPlayer) {
        proxiedPlayer.sendMessage(TextComponent.fromLegacyText(RankService.getPrefix() + "§cBenutze: /rank add/remove <Name> <Rang Name> <Haltbarkeit in Tagen / Permanent (P)>"));
    }
}