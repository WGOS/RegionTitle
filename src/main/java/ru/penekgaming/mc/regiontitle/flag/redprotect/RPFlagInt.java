package ru.penekgaming.mc.regiontitle.flag.redprotect;

import br.net.fabiozumbi12.RedProtect.Sponge.Region;
import ru.penekgaming.mc.regiontitle.flag.Flag;

import java.util.Optional;

public class RPFlagInt extends Flag<Integer> {
    public RPFlagInt(String name, int defValue, boolean isAdmin) {
        super(name, defValue, isAdmin);

        if (isAdmin)
            RP_API.addAdminFlag(name);
        else
            RP_API.addPlayerFlag(name, defValue);
    }

    @Override
    public Optional<Integer> getValue(Region region) {
        int val = getDefValue();

        try {
            val = Integer.parseInt(region.getFlagString(getName()));
        } catch (Exception ignored) {
        }

        return Optional.of(val);
    }
}
