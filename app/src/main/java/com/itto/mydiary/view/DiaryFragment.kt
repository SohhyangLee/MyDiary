package com.itto.mydiary.view

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.itto.mydiary.R
import com.itto.mydiary.databinding.Fragment2Binding

class DiaryFragment : Fragment() {

    private var _context : Context? = null
    private var listener : OnTabItemSelectedListener? = null
    private var requestListener : OnRequestListener? = null
    private lateinit var mBinding: Fragment2Binding

    var weatherIndex = 0

    override fun onAttach(context: Context) {
        super.onAttach(context)
        this._context = context

        if(context is OnTabItemSelectedListener){
            listener = context
        }

        if(context is OnRequestListener){
            requestListener = context
        }
    }

    override fun onDetach() {
        super.onDetach()

        if(context != null){
            _context = null
            listener = null
            requestListener = null
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i(TAG, "2")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = Fragment2Binding.inflate(inflater, container, false)
        initUI()
        return mBinding.root
    }

    private fun initUI() {
        mBinding.saveButton.setOnClickListener {
            listener?.onTabSelected(0)
        }

        mBinding.deleteButton.setOnClickListener {
            listener?.onTabSelected(0)
        }

        mBinding.closeButton.setOnClickListener {
            listener?.onTabSelected(0)
        }

        mBinding.sliderView.setOnSlideListener {
            index -> Toast.makeText(context, "moodIndex changed to $index", Toast.LENGTH_SHORT).show()
        }

        mBinding.sliderView.setInitialIndex(2)
    }

    public fun setWeather(data: String?){
        if(data != null){
            if(data == "맑음") {
                mBinding.weatherIcon.setImageResource(R.drawable.weather_1)
                weatherIndex = 0
            } else if (data == "구름 조금") {
                mBinding.weatherIcon.setImageResource(R.drawable.weather_2)
                weatherIndex = 1
            } else if (data == "구름 많음") {
                mBinding.weatherIcon.setImageResource(R.drawable.weather_3)
                weatherIndex = 2
            } else if (data == "흐림") {
                mBinding.weatherIcon.setImageResource(R.drawable.weather_4)
                weatherIndex = 3
            } else if (data == "비") {
                mBinding.weatherIcon.setImageResource(R.drawable.weather_5)
                weatherIndex = 4
            } else if (data == "눈/비") {
                mBinding.weatherIcon.setImageResource(R.drawable.weather_6)
                weatherIndex = 5
            } else if (data == "눈") {
                mBinding.weatherIcon.setImageResource(R.drawable.weather_7)
                weatherIndex = 6
            } else {
                Log.d(TAG, "Unknown weather string : $data")
            }
        }
    }

    fun setAddress(data: String?) {
        mBinding.locationTextView.text = data
    }

    fun setDateString(dateString: String?) {
        mBinding.dateTextView.text = dateString
    }

    companion object {
        private const val TAG = "Fragment2"
    }
}