package ru.penekgaming.mc.regiontitle.listener;

import com.google.inject.Inject;
import com.griefdefender.api.claim.Claim;
import com.griefdefender.api.event.BorderClaimEvent;
import net.kyori.event.method.annotation.Subscribe;
import org.slf4j.Logger;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.effect.sound.SoundType;
import org.spongepowered.api.entity.living.player.Player;
import ru.penekgaming.mc.regiontitle.flag.Flags;
import ru.penekgaming.mc.regiontitle.region.RegionProvider;
import ru.penekgaming.mc.regiontitle.util.TitleUtils;

import java.util.Optional;

public class GDListener {
    private final Logger logger;
    private final RegionProvider<Claim> regionProvider;

    @SuppressWarnings("unchecked")
    @Inject
    public GDListener(Logger logger, RegionProvider<?> regionProvider) {
        this.logger = logger;
        this.regionProvider = (RegionProvider<Claim>) regionProvider;
    }

    @Subscribe
    public void onClaimCross(BorderClaimEvent event) {
        if (!event.getUser().isPresent())
            return;

        Optional<Player> playerOptional = Sponge.getServer().getPlayer(event.getUser().get().getUniqueId());
        if (!playerOptional.isPresent())
            return;

        Player player = playerOptional.get();

        logger.debug("Player {} crossed claims", player.getName());

        String enterTitleMain = regionProvider.getFlagValue(event.getEnterClaim(), Flags.TITLE_ENTER_MAIN);
        if (!enterTitleMain.equals(Flags.TITLE_ENTER_MAIN.getDefValue())) {
            String subtitle = regionProvider.getFlagValue(event.getEnterClaim(), Flags.TITLE_ENTER_SUBTITLE);

            TitleUtils.showTitle(
                    player,
                    enterTitleMain,
                    subtitle.equals(Flags.TITLE_ENTER_SUBTITLE.getDefValue()) ? null : subtitle,
                    regionProvider.getFlagValue(event.getEnterClaim(), Flags.TITLE_ENTER_FADE_IN_TICKS),
                    regionProvider.getFlagValue(event.getEnterClaim(), Flags.TITLE_ENTER_FADE_OUT_TICKS),
                    regionProvider.getFlagValue(event.getEnterClaim(), Flags.TITLE_ENTER_STAY_TICKS)
            );
        }

        String enterSound = regionProvider.getFlagValue(event.getEnterClaim(), Flags.ENTER_SOUND);
        if (!enterSound.equals(Flags.ENTER_SOUND.getDefValue())) {
            player.playSound(SoundType.of(enterSound), player.getPosition(), 2.0);
        }

        String exitTitleMain = regionProvider.getFlagValue(event.getExitClaim(), Flags.TITLE_EXIT_MAIN);
        if (!exitTitleMain.equals(Flags.TITLE_EXIT_MAIN.getDefValue())) {
            String subtitle = regionProvider.getFlagValue(event.getExitClaim(), Flags.TITLE_EXIT_SUBTITLE);

            TitleUtils.showTitle(
                    player,
                    exitTitleMain,
                    subtitle.equals(Flags.TITLE_EXIT_SUBTITLE.getDefValue()) ? null : subtitle,
                    regionProvider.getFlagValue(event.getExitClaim(), Flags.TITLE_EXIT_FADE_IN_TICKS),
                    regionProvider.getFlagValue(event.getExitClaim(), Flags.TITLE_EXIT_FADE_OUT_TICKS),
                    regionProvider.getFlagValue(event.getExitClaim(), Flags.TITLE_EXIT_STAY_TICKS)
            );
        }

        String exitSound = regionProvider.getFlagValue(event.getExitClaim(), Flags.EXIT_SOUND);
        if (!exitSound.equals(Flags.EXIT_SOUND.getDefValue())) {
            player.playSound(SoundType.of(exitSound), player.getPosition(), 2.0);
        }
    }
}