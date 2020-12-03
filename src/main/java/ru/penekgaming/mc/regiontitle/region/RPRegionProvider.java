package ru.penekgaming.mc.regiontitle.region;

import br.net.fabiozumbi12.RedProtect.Sponge.Region;
import ru.penekgaming.mc.regiontitle.flag.Flag;

public class RPRegionProvider implements RegionProvider<Region> {
    @SuppressWarnings("unchecked")
    @Override
    public <T> T getFlagValue(Region region, Flag<T> flag) {
        try {
            if (flag.getValueClass() == Boolean.class) {
                return (T) (region.getFlagBool(flag.getName()) ? Boolean.TRUE : Boolean.FALSE);
            }

            String flagString = region.getFlagString(flag.getName());

            Class<?> valueClass = flag.getValueClass();

            if (valueClass.isAssignableFrom(String.class))
                return (T) flagString;
            else if (valueClass.isAssignableFrom(Integer.class))
                return (T) Integer.valueOf(flagString);
            else
                return flag.getDefValue();
        } catch (Exception ignored) {
        }
        return flag.getDefValue();
    }
}
