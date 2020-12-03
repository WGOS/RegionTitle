package ru.penekgaming.mc.regiontitle.flag;

import java.util.ArrayList;
import java.util.List;

public class Flags {
    private static final String DEF_STR = "undefined";

    public static final List<Flag<?>> FLAGS = new ArrayList<>();

    public static final Flag<Integer> TITLE_ENTER_FADE_IN_TICKS = new Flag<>(
            "title-enter-fade-in-ticks",
            "How many ticks title fades in after enter",
            10, true, Integer.class);

    public static final Flag<Integer> TITLE_ENTER_FADE_OUT_TICKS = new Flag<>(
            "title-enter-fade-out-ticks",
            "How many ticks title fades out after enter",
            10, true, Integer.class);

    public static final Flag<Integer> TITLE_ENTER_STAY_TICKS = new Flag<>(
            "title-enter-stay-ticks",
            "How many ticks title stays after enter",
            40, true, Integer.class);

    public static final Flag<String> TITLE_ENTER_MAIN = new Flag<>(
            "title-enter-main",
            "Title to show on player enter",
            DEF_STR, true, String.class);

    public static final Flag<String> TITLE_ENTER_SUBTITLE = new Flag<>(
            "title-enter-subtitle",
            "Subtitle to show on player enter",
            DEF_STR, true, String.class);

    public static final Flag<String> ENTER_SOUND = new Flag<>(
            "enter-sound",
            "Play sound to player on enter",
            DEF_STR, true, String.class);

    public static final Flag<Integer> TITLE_EXIT_FADE_IN_TICKS = new Flag<>(
            "title-exit-fade-in-ticks",
            "How many ticks title fades in after exit",
            10, true, Integer.class);

    public static final Flag<Integer> TITLE_EXIT_FADE_OUT_TICKS = new Flag<>(
            "title-exit-fade-out-ticks",
            "How many ticks title fades out after exit",
            10, true, Integer.class);

    public static final Flag<Integer> TITLE_EXIT_STAY_TICKS = new Flag<>(
            "title-exit-stay-ticks",
            "How many ticks title stays after exit",
            40, true, Integer.class);
    public static final Flag<String> TITLE_EXIT_MAIN = new Flag<>(
            "title-exit-main",
            "Title to show on player exit",
            DEF_STR, true, String.class);

    public static final Flag<String> TITLE_EXIT_SUBTITLE = new Flag<>(
            "title-exit-subtitle",
            "Subtitle to show on player exit",
            DEF_STR, true, String.class);

    public static final Flag<String> EXIT_SOUND = new Flag<>(
            "exit-sound",
            "Play sound to player on exit",
            DEF_STR, true, String.class);
}
