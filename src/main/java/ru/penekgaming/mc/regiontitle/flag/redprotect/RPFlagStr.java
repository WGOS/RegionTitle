package ru.penekgaming.mc.regiontitle.flag.redprotect;

import br.net.fabiozumbi12.RedProtect.Sponge.Region;
import ru.penekgaming.mc.regiontitle.flag.Flag;

import java.util.Optional;

public class RPFlagStr extends Flag<String> {
    public RPFlagStr(String name, String defValue, boolean isAdmin) {
        super(name, defValue, isAdmin);
    }

    @Override
    public Optional<String> getValue(Region region) {
        String flagString = region.getFlagString(getName());

        if (flagString == null || flagString.equals("false") || flagString.isEmpty())
            return Optional.empty();

        return Optional.of(flagString);
    }
}
