package ru.penekgaming.mc.regiontitle.flag.griefdefender;

import com.griefdefender.permission.option.GDOption;
import net.kyori.text.Component;

import java.util.Set;

public class RTOption<T> extends GDOption<T> {
    private final boolean isAdmin;
    private final Object defValue;

    public RTOption(String id, String name, Component description, boolean multiValued, boolean isAdmin,
                    Set<String> requiredContexts, Class<T> allowed, Object defValue) {
        super(id, name, description, multiValued, requiredContexts, allowed);
        this.isAdmin = isAdmin;
        this.defValue = defValue;
    }

    @Override
    public boolean isAdmin() {
        return isAdmin;
    }

    @SuppressWarnings("unchecked")
    @Override
    public T getDefaultValue() {
        return (T) defValue;
    }
}
