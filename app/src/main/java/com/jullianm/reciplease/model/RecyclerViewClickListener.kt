package com.jullianm.reciplease.model

import android.view.View

interface RecyclerViewClickListener {
    fun recyclerViewListClicked(v: View, position: Int)
}