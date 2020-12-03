package ru.penekgaming.mc.regiontitle;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import org.slf4j.Logger;
import ru.penekgaming.mc.regiontitle.region.RegionProvider;
import ru.penekgaming.mc.regiontitle.registry.FlagRegistry;

public class RegionTitleModule extends AbstractModule {
    private final Logger logger;
    private final FlagRegistry flagRegistry;
    private final RegionProvider<?> regionProvider;

    public RegionTitleModule(Logger logger, FlagRegistry flagRegistry, RegionProvider<?> regionProvider) {
        this.logger = logger;
        this.flagRegistry = flagRegistry;
        this.regionProvider = regionProvider;

    }

    @Override
    protected void configure() {

    }

    @Provides
    public Logger getLogger() {
        return logger;
    }

    @Provides
    public FlagRegistry getFlagRegistry() {
        return flagRegistry;
    }

    @Provides
    public RegionProvider<?> getRegionProvider() {
        return regionProvider;
    }
}
