package com.feliperoriz.watchdog.ui

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.feliperoriz.watchdog.R
import com.feliperoriz.watchdog.data.MemberDTO

class MainAdapter(val context: Context, var members: List<MemberDTO> = ArrayList(), val inflater: LayoutInflater = LayoutInflater.from(context)) : RecyclerView.Adapter<MemberViewHolder>() {

    override fun getItemCount(): Int {
        return members.size
    }

    override fun onBindViewHolder(holder: MemberViewHolder?, position: Int) {
        val member = members[position]
        holder?.nameView?.text = member.name
        holder?.roleView?.text = member.role
        holder?.setParty(context, member.party)
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): MemberViewHolder {
        val view = inflater.inflate(R.layout.member_list_item, parent, false)
        return MemberViewHolder(view)
    }
}