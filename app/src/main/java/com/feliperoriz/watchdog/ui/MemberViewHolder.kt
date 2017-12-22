package com.feliperoriz.watchdog.ui

import android.content.Context
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import com.feliperoriz.watchdog.R
import com.feliperoriz.watchdog.data.MemberDTO

/**
 * Created by feliperoriz on 12/22/17.
 */
class MemberViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {

    val nameView: TextView?
    val roleView: TextView?
    val partyView: TextView?

    init {
        nameView = itemView?.findViewById(R.id.name)
        roleView = itemView?.findViewById(R.id.role)
        partyView = itemView?.findViewById(R.id.party)
    }

    fun setParty(context: Context, party: MemberDTO.Party) {
        partyView?.text = party.title
        val color = ContextCompat.getColor(context, party.color)
        partyView?.setTextColor(color)
    }
}