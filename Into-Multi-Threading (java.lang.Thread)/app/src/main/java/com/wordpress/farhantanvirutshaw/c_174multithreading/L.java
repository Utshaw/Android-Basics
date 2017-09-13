package com.wordpress.farhantanvirutshaw.c_174multithreading;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by utshaw on 6/14/17.
 */

public class L {
    public static void m(String message)
    {
        Log.d("Utshaw",message);
    }

    public static void s(Context context , String message)
    {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }
}
