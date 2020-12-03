package ru.penekgaming.mc.regiontitle.flag;

public class Flag<T> {
    private final String name;
    private final String description;
    private final T defValue;
    private final boolean isAdmin;
    private final Class<T> clazz;

    public Flag(String name, String description, T defValue, boolean isAdmin, Class<T> clazz) {
        this.name = name;
        this.description = description;
        this.defValue = defValue;
        this.isAdmin = isAdmin;
        this.clazz = clazz;

        Flags.FLAGS.add(this);
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

    public Class<T> getValueClass() {
        return clazz;
    }

    public String getDescription() {
        return description;
    }
}
