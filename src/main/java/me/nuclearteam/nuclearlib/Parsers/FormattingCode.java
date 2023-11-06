package me.nuclearteam.nuclearlib.Parsers;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FormattingCode {

    private final Plugin plugin;
    private final Player player;

    public FormattingCode(Plugin plugin, Player player) {
        this.plugin = plugin;
        this.player = player;
    }

    public String parse(String text) {
        // Compile the pattern for color and formatting codes
        Pattern pattern = Pattern.compile("&([0-9a-fk-or])(.*?)", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(text);

        // Replace color and formatting codes
        StringBuffer result = new StringBuffer();
        while (matcher.find()) {
            String code = matcher.group(1);
            String content = matcher.group(2);

            // Translate color codes and formatting codes
            String formattedContent = ChatColor.translateAlternateColorCodes('&', "&" + code + content);
            matcher.appendReplacement(result, Matcher.quoteReplacement(formattedContent));
        }
        matcher.appendTail(result);

        return result.toString();
    }
}
