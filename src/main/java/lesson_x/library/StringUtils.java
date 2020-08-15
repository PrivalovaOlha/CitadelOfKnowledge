package lesson_x.library;

public final class StringUtils {

    public static final String SPACE = " ";

    public static String repeat(long times, CharSequence source) {
        StringBuilder target = new StringBuilder();
        for (long i = 0; i < times; i++) {
            target.append(source);
        }
        return target.toString();
    }

    public static String repeat(long times, char source) {
        return repeat(times, "" + source);
    }

    private StringUtils() {
        throw new UnsupportedOperationException("private constructor");
    }

}
