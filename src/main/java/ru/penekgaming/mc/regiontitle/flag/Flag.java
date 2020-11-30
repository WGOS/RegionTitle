package ru.penekgaming.mc.regiontitle.flag;

import br.net.fabiozumbi12.RedProtect.Sponge.Region;
import org.spongepowered.api.event.cause.Cause;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class Flag<T> {
    private static final List<Flag<?>> FLAGS = new ArrayList<>();

    private final String name;
    private final T defValue;
    private final boolean isAdmin;

    public Flag(String name, T defValue, boolean isAdmin) {
        this.name = name;
        this.defValue = defValue;
        this.isAdmin = isAdmin;

        FLAGS.add(this);
    }

    public abstract Optional<T> getValue(Region region);

    public void setValue(Cause cause, Region region, T value) {
        region.setFlag(cause, name, value);
    }

    public String getName() {
        return name;
    }

    public T getDefValue() {
        return defValue;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public static List<Flag<?>> getFlags() {
        return FLAGS;
    }
}
