package com.itto.mydiary.view

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.itto.mydiary.model.Note
import com.itto.mydiary.databinding.Fragment1Binding

class NoteListFragment : Fragment() {

    private var _context: Context? = null
    lateinit var recyclerView: RecyclerView
    lateinit var adapter: NoteAdapter
    private var listener: OnTabItemSelectedListener? = null
    private lateinit var mBinding: Fragment1Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i(TAG, "1")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = Fragment1Binding.inflate(inflater, container, false)
        initUI()
        return mBinding.root
    }

    private fun initUI() {
        mBinding.todayWriteButton.setOnClickListener {
            listener?.onTabSelected(1)
        }
        mBinding.switchButton.setOnSwitchListener { position, _ ->
            adapter.switchLayout(position)
            adapter.notifyDataSetChanged()
        }

        adapter = NoteAdapter(arrayListOf())

        recyclerView = mBinding.recyclerView
        val layoutManager = LinearLayoutManager(context)
        recyclerView.layoutManager = layoutManager

        //example
        adapter.addItem(
            Note(
                0,
                "0",
                "강남구 삼성동",
                "",
                "",
                "HappyDay",
                "1",
                "capture1.jpg",
            "9월 20일"
            )
        )

        adapter.addItem(
            Note(
                1,
                "1",
                "성남시 고등동",
                "",
                "",
                "Rainy Mood",
                "0",
                "capture1.jpg",
                "9월 22일"
            )
        )

        adapter.addItem(
            Note(
                2,
                "0",
                "강남구 역삼동",
                "",
                "",
                "HappyDay",
                "3",
                null,
                "9월 25일"
            )
        )

        recyclerView.adapter = adapter

        adapter.setOnItemClickListener(object : OnNoteItemClickListener {
            override fun onItemClick(holder: NoteAdapter.ViewHolder, view: View, position: Int) {
                val item = adapter.getItem(position)
                Toast.makeText(context, "clicked item : ${item?.contents}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        this._context = context

        if(context is OnTabItemSelectedListener){
            listener = context
        }
    }

    override fun onDetach() {
        super.onDetach()

        if(_context != null){
            _context = null
            listener = null
        }
    }

    companion object {
        private const val TAG = "Fragment1"
    }
}