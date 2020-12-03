package ru.penekgaming.mc.regiontitle.util;

import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.serializer.TextSerializers;
import org.spongepowered.api.text.title.Title;

public class TitleUtils {
    public static void showTitle(Player player, String title, String subtitle, int fadeInTicks, int fadeOutTicks, int stayTick) {
        player.resetTitle();
        player.clearTitle();

        Title.Builder titleBuilder = Title.builder();

        titleBuilder.title(TextSerializers.FORMATTING_CODE.deserialize(title))
                .fadeIn(fadeInTicks)
                .fadeOut(fadeOutTicks)
                .stay(stayTick);

        if (subtitle != null)
            titleBuilder.subtitle(TextSerializers.FORMATTING_CODE.deserialize(subtitle));

        player.sendTitle(titleBuilder.build());
    }
}
