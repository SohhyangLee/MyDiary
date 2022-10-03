package com.itto.mydiary.view

import android.view.View
import com.itto.mydiary.view.NoteAdapter

interface OnNoteItemClickListener {
    fun onItemClick(holder: NoteAdapter.ViewHolder, view: View, position: Int)
}