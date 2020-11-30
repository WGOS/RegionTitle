package ru.penekgaming.mc.regiontitle.listener;

import br.net.fabiozumbi12.RedProtect.Sponge.RedProtect;
import br.net.fabiozumbi12.RedProtect.Sponge.Region;
import com.google.inject.Inject;
import org.slf4j.Logger;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.data.key.Keys;
import org.spongepowered.api.effect.sound.SoundType;
import org.spongepowered.api.entity.Entity;
import org.spongepowered.api.entity.EntitySnapshot;
import org.spongepowered.api.entity.Transform;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.Order;
import org.spongepowered.api.event.entity.MoveEntityEvent;
import org.spongepowered.api.text.serializer.TextSerializers;
import org.spongepowered.api.text.title.Title;
import org.spongepowered.api.world.World;
import ru.penekgaming.mc.regiontitle.flag.Flags;

import java.util.Optional;
import java.util.UUID;

public class PlayerListener {
    @Inject
    private Logger logger;

    @SuppressWarnings("OptionalGetWithoutIsPresent")
    @Listener(order = Order.LAST, beforeModifications = true)
    public void onPlayerMovement(MoveEntityEvent e) {
        // Logic from RedProtect
        if (RedProtect.get().getConfigManager().configRoot().performance.disable_onPlayerMoveEvent_handler)
            return;

        Entity entity = e.getTargetEntity();

        Player player = null;

        if (entity instanceof Player) {
            player = (Player) entity;
        } else if (entity.get(Keys.PASSENGERS).isPresent()) {
            for (Object uuidEnt : entity.get(Keys.PASSENGERS).get()) {
                if (uuidEnt instanceof UUID) {
                    if (Sponge.getServer().getPlayer((UUID) uuidEnt).isPresent()) {
                        player = Sponge.getServer().getPlayer((UUID) uuidEnt).get();
                    }
                } else if (uuidEnt instanceof EntitySnapshot) {
                    if (Sponge.getServer().getPlayer(((EntitySnapshot) uuidEnt).getUniqueId().get()).isPresent()) {
                        player = Sponge.getServer().getPlayer(((EntitySnapshot) uuidEnt).getUniqueId().get()).get();
                    }
                }
            }
        } else {
            return;
        }

        if (player == null)
            return;

        Transform<World> fromTransform = e.getFromTransform();
        Transform<World> toTransform = e.getToTransform();

        Region fromRegion = RedProtect.get().getRegionManager().getTopRegion(fromTransform.getLocation(), this.getClass().toString());
        Region toRegion = RedProtect.get().getRegionManager().getTopRegion(toTransform.getLocation(), this.getClass().toString());

        if (toRegion == null)
            return;

        if (fromRegion != null && fromRegion.getID().equals(toRegion.getID()))
            return;

        sendTitle(player, toRegion);
        playSound(player, toRegion);
    }

    private void sendTitle(Player player, Region region) {
        Optional<String> titleString = Flags.TITLE_MAIN.getValue(region);

        if (!titleString.isPresent())
            return;

        Title.Builder titleBuilder = Title.builder()
                .title(TextSerializers.FORMATTING_CODE.deserialize(titleString.get()))
                .fadeIn(Flags.TITLE_FADE_IN_TICKS.getValue(region).orElse(Flags.TITLE_FADE_IN_TICKS.getDefValue()))
                .fadeOut(Flags.TITLE_FADE_OUT_TICKS.getValue(region).orElse(Flags.TITLE_FADE_OUT_TICKS.getDefValue()))
                .stay(Flags.TITLE_STAY_TICKS.getValue(region).orElse(Flags.TITLE_STAY_TICKS.getDefValue()));

        titleBuilder.subtitle(TextSerializers.FORMATTING_CODE.deserialize(Flags.TITLE_SUBTITLE.getValue(region).orElse("")));

        player.resetTitle();
        player.sendTitle(titleBuilder.build());

        logger.debug("Sent title to client: {}", player.getName());
    }

    private void playSound(Player player, Region region) {
        Flags.ENTER_SOUND.getValue(region).ifPresent(s ->
                player.playSound(SoundType.of(s), player.getPosition(), 1.0)
        );
    }
}
