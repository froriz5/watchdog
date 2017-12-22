package com.feliperoriz.watchdog.data;

import android.support.annotation.NonNull;

import com.squareup.moshi.FromJson;
import com.squareup.moshi.ToJson;

public class MemberTypeAdapter {

    @ToJson String toPartyJson(@NonNull MemberDTO.Party party) {
        return party.getInitial();
    }

    @FromJson MemberDTO.Party fromJson(@NonNull String initial) {
        return MemberDTO.Party.Companion.fromString(initial);
    }
}
