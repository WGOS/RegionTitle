package ru.penekgaming.mc.regiontitle.flag;

import br.net.fabiozumbi12.RedProtect.Sponge.RedProtect;
import ru.penekgaming.mc.regiontitle.flag.redprotect.RPFlagInt;
import ru.penekgaming.mc.regiontitle.flag.redprotect.RPFlagStr;

public class Flags {
    /*
     * All flags are RPFlag for now
     * Probably should be changed to Flag type for compatibility over different plugins
     * */
    public static final RPFlagInt TITLE_FADE_IN_TICKS = new RPFlagInt("title-fade-in-ticks", 10, true);
    public static final RPFlagInt TITLE_FADE_OUT_TICKS = new RPFlagInt("title-fade-out-ticks", 10, true);
    public static final RPFlagInt TITLE_STAY_TICKS = new RPFlagInt("title-stay-ticks", 40, true);
    public static final RPFlagStr TITLE_MAIN = new RPFlagStr("title-main", null, true);
    public static final RPFlagStr TITLE_SUBTITLE = new RPFlagStr("title-subtitle", null, true);

    public static final RPFlagStr ENTER_SOUND = new RPFlagStr("enter-sound", null, true);

    public static void registerFlags() {
        for (Flag<?> flag : Flag.getFlags()) {
            if (flag.isAdmin())
                RedProtect.get().getAPI().addAdminFlag(flag.getName());
            else
                RedProtect.get().getAPI().addPlayerFlag(flag.getName(), flag.getDefValue());
        }
    }
}
