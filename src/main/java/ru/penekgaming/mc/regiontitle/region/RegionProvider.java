package ru.penekgaming.mc.regiontitle.region;

import ru.penekgaming.mc.regiontitle.flag.Flag;

public interface RegionProvider<R> {
    <T> T getFlagValue(R region, Flag<T> flag);
}
