package site.hanschen.common.utils;

import android.content.Context;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;

/**
 * @author HansChen
 */
public class ResourceUtils {

    @ColorInt
    public static int getColor(Context context, @ColorRes int colorId) {
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.LOLLIPOP_MR1) {
            return context.getResources().getColor(colorId);
        } else {
            return context.getColor(colorId);
        }
    }
}
