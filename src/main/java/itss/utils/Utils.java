package itss.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Utils {
    public static float round(float d, int scale) {
        BigDecimal bd = new BigDecimal(Float.toString(d));
        return bd.setScale(scale, RoundingMode.HALF_UP).floatValue();
    }
}
