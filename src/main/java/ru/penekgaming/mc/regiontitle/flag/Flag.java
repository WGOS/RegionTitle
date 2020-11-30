package ru.penekgaming.mc.regiontitle.flag;

import br.net.fabiozumbi12.RedProtect.Sponge.API.RedProtectAPI;
import br.net.fabiozumbi12.RedProtect.Sponge.RedProtect;
import br.net.fabiozumbi12.RedProtect.Sponge.Region;
import org.spongepowered.api.event.cause.Cause;

import java.util.Optional;

public abstract class Flag<T> {
    protected static final RedProtectAPI RP_API = RedProtect.get().getAPI();

    private final String name;
    private final T defValue;
    private final boolean isAdmin;

    public Flag(String name, T defValue, boolean isAdmin) {
        this.name = name;
        this.defValue = defValue;
        this.isAdmin = isAdmin;
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
}
