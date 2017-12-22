package com.feliperoriz.watchdog.data;

import android.support.annotation.NonNull;

import com.squareup.moshi.FromJson;
import com.squareup.moshi.ToJson;

import org.joda.time.DateTime;

/**
 * Created by feliperoriz on 12/21/17.
 */

public class MemberTypeAdapter {

    @ToJson String toJson(@NonNull DateTime time) {
        return time.toString();
    }

    @FromJson DateTime fromJson(@NonNull String time) {
        return DateTime.parse(time);
    }
}
