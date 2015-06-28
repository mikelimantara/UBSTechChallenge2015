package com.prototype.ubs.techchallenge.utils;

import android.content.Context;
import android.graphics.Typeface;

import java.lang.reflect.Field;
import java.lang.reflect.Type;

/**
 * Created by Michael on 28/6/2015.
 */
public class ReplaceFont {

    public static void replaceDefaultFont(Context context,
                                          String fontToBeReplaced,
                                          String fontInAsset) {
        Typeface customFontTypeFace = Typeface.createFromAsset(context.getAssets(), fontInAsset);
        replaceFont(fontToBeReplaced, customFontTypeFace);
    }

    private static void replaceFont(String fontToBeReplaced, Typeface customFontTypeFace) {
        try {
            Field myField = Typeface.class.getDeclaredField(fontToBeReplaced);
            myField.setAccessible(true);
            myField.set(null, customFontTypeFace);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }
}
