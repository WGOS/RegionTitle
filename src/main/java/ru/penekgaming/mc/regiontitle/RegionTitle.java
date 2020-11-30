package ru.penekgaming.mc.regiontitle;

import com.google.inject.*;
import org.slf4j.Logger;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.Order;
import org.spongepowered.api.event.game.state.GamePreInitializationEvent;
import org.spongepowered.api.event.game.state.GameStartedServerEvent;
import org.spongepowered.api.plugin.Dependency;
import org.spongepowered.api.plugin.Plugin;
import ru.penekgaming.mc.regiontitle.flag.Flags;
import ru.penekgaming.mc.regiontitle.listener.PlayerListener;

@Plugin(
        id = RegionTitle.PLUGIN_ID,
        name = RegionTitle.PLUGIN_NAME,
        description = "Set greeting title for claimed regions",
        authors = {
                "WGOS"
        },
        dependencies = {
                @Dependency(id = "redprotect", version = "7.7.2")
        }
)
public class RegionTitle extends AbstractModule {
    public static final String PLUGIN_ID = "regiontitle";
    public static final String PLUGIN_NAME = "RegionTitle";

    private Injector injector;

    @Inject
    private Logger logger;

    @Listener
    public void onServerPreInit(GamePreInitializationEvent event) {
        injector = Guice.createInjector(this);

        Sponge.getEventManager().registerListeners(this, injector.getInstance(PlayerListener.class));
    }

    @Listener(order = Order.POST)
    public void onServerStart(GameStartedServerEvent event) {
        Flags.registerFlags();

        logger.info("{} is up and running!", PLUGIN_NAME);
    }

    @Provides
    public Logger getLogger() {
        return logger;
    }

    @Override
    protected void configure() {

    }
}
