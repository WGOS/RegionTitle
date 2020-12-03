package ru.penekgaming.mc.regiontitle;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.griefdefender.api.GriefDefender;
import org.slf4j.Logger;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.plugin.PluginContainer;
import ru.penekgaming.mc.regiontitle.listener.GDListener;
import ru.penekgaming.mc.regiontitle.listener.RPListener;
import ru.penekgaming.mc.regiontitle.region.GDRegionProvider;
import ru.penekgaming.mc.regiontitle.region.RPRegionProvider;
import ru.penekgaming.mc.regiontitle.region.RegionProvider;
import ru.penekgaming.mc.regiontitle.registry.FlagRegistry;
import ru.penekgaming.mc.regiontitle.registry.GDFlagRegistry;
import ru.penekgaming.mc.regiontitle.registry.RPFlagRegistry;

import java.util.ArrayList;
import java.util.List;

public class PluginLoader {
    public static final String ID_GRIEF_DEFENDER = "griefdefender";
    public static final String ID_RED_PROTECT = "redprotect";

    public static void load(Logger logger) {
        List<PluginContainer> availPlugins = new ArrayList<>();

        Sponge.getPluginManager().getPlugin(ID_GRIEF_DEFENDER).ifPresent(availPlugins::add);
        Sponge.getPluginManager().getPlugin(ID_RED_PROTECT).ifPresent(availPlugins::add);

        if (availPlugins.size() < 1) {
            logger.error(RegionTitle.PLUGIN_NAME + " is disabled. No claim plugins found");
        }

        for (PluginContainer plugin : availPlugins) {
            logger.info("Loading for {}", plugin.getName());
            switch (plugin.getId()) {
                case ID_GRIEF_DEFENDER:
                    Injector injector = injectAndRegisterFlags(logger, new GDFlagRegistry(), new GDRegionProvider());
                    GriefDefender.getEventManager().register(injector.getInstance(GDListener.class));
                    break;

                case ID_RED_PROTECT:
                    injector = injectAndRegisterFlags(logger, new RPFlagRegistry(), new RPRegionProvider());
                    Sponge.getEventManager().registerListeners(RegionTitle.getInstance(), injector.getInstance(RPListener.class));
                    break;
            }
        }
    }

    private static Injector injectAndRegisterFlags(Logger logger, FlagRegistry flagRegistry, RegionProvider<?> regionProvider) {
        logger.info("Creating injector");
        Injector injector = Guice.createInjector(new RegionTitleModule(logger, flagRegistry, regionProvider));

        logger.info("Registering flags or options");
        flagRegistry.registerFlags();

        return injector;
    }
}
