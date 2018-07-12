package com.example.sugamparajuli.loginapp.utils;

import android.content.Context;
import android.widget.Toast;

public class ShowToast {

    public static void showToast(Context context, String message, boolean longTime) {

        int showTime = longTime ? Toast.LENGTH_LONG : Toast.LENGTH_SHORT;

        Toast.makeText(context, message, showTime).show();
    }
}
