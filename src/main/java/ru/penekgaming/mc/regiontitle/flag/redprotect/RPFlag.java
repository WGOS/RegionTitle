package ru.penekgaming.mc.regiontitle.flag.redprotect;

import br.net.fabiozumbi12.RedProtect.Sponge.Region;
import ru.penekgaming.mc.regiontitle.flag.Flag;

import java.util.Optional;

public class RPFlag extends Flag<Boolean> {
    public RPFlag(String name, Boolean defValue, boolean isAdmin) {
        super(name, defValue, isAdmin);
    }

    @Override
    public Optional<Boolean> getValue(Region region) {
        return Optional.of(region.getFlagBool(this.getName()));
    }
}
