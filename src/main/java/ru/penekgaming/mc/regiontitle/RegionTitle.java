package ru.penekgaming.mc.regiontitle;

import com.google.inject.Inject;
import org.slf4j.Logger;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.Order;
import org.spongepowered.api.event.game.state.GameStartedServerEvent;
import org.spongepowered.api.plugin.Dependency;
import org.spongepowered.api.plugin.Plugin;

@Plugin(
        id = RegionTitle.PLUGIN_ID,
        name = RegionTitle.PLUGIN_NAME,
        description = "Set greeting title for claimed regions",
        authors = {
                "WGOS"
        },
        dependencies = {
                @Dependency(id = PluginLoader.ID_RED_PROTECT, version = "7.7.2", optional = true),
                @Dependency(id = PluginLoader.ID_GRIEF_DEFENDER, optional = true)
        }
)
public class RegionTitle {
    public static final String PLUGIN_ID = "regiontitle";
    public static final String PLUGIN_NAME = "RegionTitle";

    private static RegionTitle instance;

    @Inject
    private Logger logger;

    @Listener(order = Order.POST)
    public void onServerStart(GameStartedServerEvent event) {
        instance = this;
        PluginLoader.load(logger);
    }

    public static RegionTitle getInstance() {
        return instance;
    }
}
