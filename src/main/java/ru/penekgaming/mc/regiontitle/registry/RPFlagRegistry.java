package ru.penekgaming.mc.regiontitle.registry;

import br.net.fabiozumbi12.RedProtect.Sponge.RedProtect;
import ru.penekgaming.mc.regiontitle.flag.Flag;
import ru.penekgaming.mc.regiontitle.flag.Flags;

public class RPFlagRegistry implements FlagRegistry {
    @Override
    public void registerFlags() {
        for (Flag<?> flag : Flags.FLAGS) {
            if (flag.isAdmin())
                RedProtect.get().getAPI().addAdminFlag(flag.getName());
            else
                RedProtect.get().getAPI().addPlayerFlag(flag.getName(), flag.getDefValue());
        }
    }
}
