package ru.penekgaming.mc.regiontitle.registry;

import com.griefdefender.registry.OptionRegistryModule;
import net.kyori.text.ComponentBuilders;
import ru.penekgaming.mc.regiontitle.PluginLoader;
import ru.penekgaming.mc.regiontitle.flag.Flag;
import ru.penekgaming.mc.regiontitle.flag.Flags;
import ru.penekgaming.mc.regiontitle.flag.griefdefender.RTOption;

import java.util.HashSet;

public class GDFlagRegistry implements FlagRegistry {
    @Override
    public void registerFlags() {
        for (Flag<?> flag : Flags.FLAGS) {
            RTOption<?> option = new RTOption<>(
                    String.format("%s:%s", PluginLoader.ID_GRIEF_DEFENDER, flag.getName()),
                    flag.getName(),
                    ComponentBuilders.text(flag.getDescription()).build(),
                    false,
                    flag.isAdmin(),
                    new HashSet<>(),
                    flag.getValueClass(),
                    flag.getDefValue());

            OptionRegistryModule.getInstance().registerCustomType(option);
        }
    }
}
