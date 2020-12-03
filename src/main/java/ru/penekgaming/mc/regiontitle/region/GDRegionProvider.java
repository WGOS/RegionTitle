package ru.penekgaming.mc.regiontitle.region;

import com.google.common.reflect.TypeToken;
import com.griefdefender.api.claim.Claim;
import com.griefdefender.api.permission.option.Option;
import com.griefdefender.registry.OptionRegistryModule;
import ru.penekgaming.mc.regiontitle.flag.Flag;

import java.util.HashSet;
import java.util.Optional;

public class GDRegionProvider implements RegionProvider<Claim> {
    @SuppressWarnings({"unchecked", "UnstableApiUsage", "rawtypes"})
    @Override
    public <T> T getFlagValue(Claim region, Flag<T> flag) {
        Optional<Option> option = OptionRegistryModule.getInstance().getById("griefdefender:" + flag.getName());

        if (!option.isPresent())
            return flag.getDefValue();

        Option<T> val = (Option<T>) option.get();

        T optionValue = region.getActiveOptionValue(TypeToken.of(flag.getValueClass()),
                val,
                new HashSet<>());

        if (flag.getValueClass() == String.class && ((String) optionValue).isEmpty())
            return flag.getDefValue();

        return optionValue;
    }
}
