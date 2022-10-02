package com.itto.mydiary

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NoteAdapter(private val items: ArrayList<Note>): RecyclerView.Adapter<NoteAdapter.ViewHolder>(), OnNoteItemClickListener {

    lateinit var listener: OnNoteItemClickListener
    private var layoutType = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteAdapter.ViewHolder {
        val inflateView = LayoutInflater.from(parent.context).inflate(R.layout.note_item, parent, false)
        return ViewHolder(inflateView, this, layoutType)
    }

    override fun onBindViewHolder(holder: NoteAdapter.ViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
        holder.setLayoutType(layoutType)
    }

    fun setOnItemClickListener(listener: OnNoteItemClickListener) {
        this.listener = listener
    }

    override fun getItemCount() = items.size

    fun getItem(position: Int): Note? {
        return items[position]
    }

    fun addItem(item: Note?){
        if(item != null){
            items.add(item)
        }
    }

    override fun onItemClick(holder: ViewHolder, view: View, position: Int) {
        listener?.onItemClick(holder, view, position)
    }

    fun switchLayout(position: Int) {
        layoutType = position
    }

    class ViewHolder(itemView : View, listener: OnNoteItemClickListener, layoutType : Int) : RecyclerView.ViewHolder(itemView){

        private val pictureExistsImageView: ImageView = itemView.findViewById(R.id.pictureExistsImageView)
        private val pictureImageView: ImageView = itemView.findViewById(R.id.pictureImageView)

        private val contentsTextView: TextView = itemView.findViewById(R.id.contentsTextView)
        private val contentsTextView2: TextView = itemView.findViewById(R.id.contentsTextView2)

        private val locationTextView: TextView = itemView.findViewById(R.id.locationTextView)
        private val locationTextView2: TextView = itemView.findViewById(R.id.locationTextView2)

        private val dateTextView: TextView = itemView.findViewById(R.id.dateTextView)
        private val dateTextView2: TextView = itemView.findViewById(R.id.dateTextView2)

        private val moodImageView: ImageView = itemView.findViewById(R.id.moodImageView)
        private val moodImageView2: ImageView = itemView.findViewById(R.id.moodImageView2)

        private val weatherImageView: ImageView = itemView.findViewById(R.id.weatherImageView)
        private val weatherImageView2: ImageView = itemView.findViewById(R.id.weatherImageView2)

        private val layout1: LinearLayout = itemView.findViewById(R.id.layout1)
        private val layout2: LinearLayout = itemView.findViewById(R.id.layout2)


        init {
            itemView.setOnClickListener(View.OnClickListener {
                var position = adapterPosition
                listener?.onItemClick(this, itemView, position)
                setLayoutType(layoutType)
            })
        }

        fun bind(item: Note) {
            var mood = item.mood
            var moodIndex = Integer.parseInt(mood)
            setMoodImage(moodIndex)

            var picturePath = item.picture
            if(picturePath != null && picturePath != ""){
                pictureExistsImageView.visibility = View.VISIBLE
                pictureImageView.visibility = View.VISIBLE
                pictureImageView.setImageURI(Uri.parse("file://$picturePath"))
            }
            else{
                pictureExistsImageView.visibility = View.GONE
                pictureImageView.visibility = View.GONE
                pictureImageView.setImageResource(R.drawable.noimagefound)
            }

            var weather = item.weather
            var weatherIndex = Integer.parseInt(weather)
            setWeatherImage(weatherIndex)

            contentsTextView.text = item.contents
            contentsTextView2.text = item.contents

            locationTextView.text = item.address
            locationTextView2.text = item.address

            dateTextView.text = item.createDataStr
            dateTextView2.text = item.createDataStr
        }

        private fun setMoodImage(moodIndex : Int){
            when(moodIndex){
                0 -> {
                    moodImageView.setImageResource(R.drawable.smile1_48)
                    moodImageView2.setImageResource(R.drawable.smile1_48)
                }
                1 -> {
                    moodImageView.setImageResource(R.drawable.smile2_48)
                    moodImageView2.setImageResource(R.drawable.smile2_48)
                }
                2 -> {
                    moodImageView.setImageResource(R.drawable.smile3_48)
                    moodImageView2.setImageResource(R.drawable.smile3_48)
                }
                3 -> {
                    moodImageView.setImageResource(R.drawable.smile4_48)
                    moodImageView2.setImageResource(R.drawable.smile4_48)
                }
                4 -> {
                    moodImageView.setImageResource(R.drawable.smile5_48)
                    moodImageView2.setImageResource(R.drawable.smile5_48)
                }
                else -> {
                    moodImageView.setImageResource(R.drawable.smile3_48)
                    moodImageView2.setImageResource(R.drawable.smile3_48)
                }
            }
        }

        private fun setWeatherImage(weatherIndex : Int){
            when(weatherIndex){
                0 -> {
                    weatherImageView.setImageResource(R.drawable.weather_icon_1)
                    weatherImageView2.setImageResource(R.drawable.weather_icon_1)
                }
                1 -> {
                    weatherImageView.setImageResource(R.drawable.weather_icon_2)
                    weatherImageView2.setImageResource(R.drawable.weather_icon_2)
                }
                2 -> {
                    weatherImageView.setImageResource(R.drawable.weather_icon_3)
                    weatherImageView2.setImageResource(R.drawable.weather_icon_3)
                }
                3 -> {
                    weatherImageView.setImageResource(R.drawable.weather_icon_4)
                    weatherImageView2.setImageResource(R.drawable.weather_icon_4)
                }
                4 -> {
                    weatherImageView.setImageResource(R.drawable.weather_icon_5)
                    weatherImageView2.setImageResource(R.drawable.weather_icon_5)
                }
                5 -> {
                    weatherImageView.setImageResource(R.drawable.weather_icon_6)
                    weatherImageView2.setImageResource(R.drawable.weather_icon_6)
                }
                6 -> {
                    weatherImageView.setImageResource(R.drawable.weather_icon_7)
                    weatherImageView2.setImageResource(R.drawable.weather_icon_7)
                }
                else -> {
                    weatherImageView.setImageResource(R.drawable.weather_icon_1)
                    weatherImageView2.setImageResource(R.drawable.weather_icon_1)
                }
            }
        }

        fun setLayoutType(layoutType: Int){
            if( layoutType == 0){
                layout1.visibility = View.VISIBLE
                layout2.visibility = View.GONE
            }
            else if( layoutType == 1){
                layout1.visibility = View.GONE
                layout2.visibility = View.VISIBLE
            }
        }

    }
}

