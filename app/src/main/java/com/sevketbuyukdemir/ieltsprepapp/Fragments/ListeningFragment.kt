package com.sevketbuyukdemir.ieltsprepapp.Fragments

import android.media.MediaPlayer
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageButton
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sevketbuyukdemir.ieltsprepapp.ListeningActivity
import com.sevketbuyukdemir.ieltsprepapp.R
import com.sevketbuyukdemir.ieltsprepapp.utils.Question
import com.sevketbuyukdemir.ieltsprepapp.view_adapters.ListeningActivityRecyclerViewAdapter
import kotlin.properties.Delegates

class ListeningFragment : Fragment(), View.OnClickListener {
    private val MEDIA_PLAYER_NOT_AVALIABLE = 0
    private val MEDIA_PLAYER_AVAILABLE = 1
    private val MEDIA_PLAYER_STOPED = 2
    private var MEDIA_PLAYER_STATE = -1
    private lateinit var mediaPlayer: MediaPlayer
    private var sectionNumber by Delegates.notNull<Char>()
    lateinit var uriString : String
    lateinit var questionList: ArrayList<Question>
    lateinit var tvMediaPlayerTitle : TextView
    lateinit var tvMediaPlayerDescription : TextView
    lateinit var btnPlayPause : AppCompatImageButton
    lateinit var recyclerViewListening : RecyclerView
    lateinit var btnListeningSubmit : Button
    private var submitted : Boolean = false

    companion object {
        fun newInstance(sectionNumber : Char,
                        uriString: String,
                        questionList : ArrayList<Question>): ListeningFragment {
            val args = Bundle()
            args.putString("uri", uriString)
            args.putParcelableArrayList("questionList",questionList)
            args.putChar("sectionNumber", sectionNumber)
            val fragment = ListeningFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val args = arguments
        // Get sound uri pass for MediaPlayer
        uriString = arguments?.getString("uri").toString()
        // prepare media player for playSound function
        if(uriString == "listening_1"){
            mediaPlayer = MediaPlayer.create(context, R.raw.listening_1)
        } else if(uriString == "listening_2") {
            mediaPlayer = MediaPlayer.create(context, R.raw.listening_2)
        } else if(uriString == "listening_3") {
            mediaPlayer = MediaPlayer.create(context, R.raw.listening_3)
        } else if(uriString == "listening_4") {
            mediaPlayer = MediaPlayer.create(context, R.raw.listening_4)
        }
        // Update media player state
        MEDIA_PLAYER_STATE = MEDIA_PLAYER_NOT_AVALIABLE
        // Get question list for RecyclerView
        sectionNumber = args?.getChar("sectionNumber")!!
        questionList = args.getParcelableArrayList<Question>("questionList") as ArrayList<Question>
    }

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_listening, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init(view)
        if(MEDIA_PLAYER_STATE == MEDIA_PLAYER_STOPED){
            btnPlayPause.setImageResource(R.drawable.play)
            tvMediaPlayerDescription.text = getString(R.string.custom_media_player_description_text_stopped)
        } else if(MEDIA_PLAYER_STATE == MEDIA_PLAYER_AVAILABLE) {
            btnPlayPause.setImageResource(R.drawable.pause)
            tvMediaPlayerDescription.text = getString(R.string.custom_media_player_description_text_playing)
        }
        if(submitted){
            btnListeningSubmit.text = getString(R.string.submit_button_text_submitted)
        }
    }

    private fun init(view : View) {
        // Bind views with Ids
        tvMediaPlayerTitle = view.findViewById(R.id.tv_media_player_title)
        tvMediaPlayerDescription = view.findViewById(R.id.tv_media_player_button_description)
        btnPlayPause = view.findViewById(R.id.play_pause_button)
        // Bind RecyclerView with id
        recyclerViewListening = view.findViewById(R.id.recycler_view_listening)
        // Set layout manager to RecyclerView
        val llm = LinearLayoutManager(context)
        llm.orientation = LinearLayoutManager.VERTICAL
        recyclerViewListening.layoutManager = llm
        // RecyclerView adapter initiation
        val zeroAscii = '0'.toInt() // This is necessary for get actual integer value
        val sectionNo = (sectionNumber.toInt() - zeroAscii)
        val listeningActivityRecyclerViewAdapter =
            context?.let { ListeningActivityRecyclerViewAdapter(sectionNo, it, questionList) }
        recyclerViewListening.adapter = listeningActivityRecyclerViewAdapter

        val titleText = "Section - $sectionNo listening sound:"
        tvMediaPlayerTitle.text = titleText

        btnListeningSubmit = view.findViewById(R.id.btn_listening_submit)
        // Set click listener to views
        btnPlayPause.setOnClickListener(this)
        btnListeningSubmit.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        if (v != null) {
            when(v.id){
                R.id.play_pause_button ->
                    if(MEDIA_PLAYER_STATE == MEDIA_PLAYER_NOT_AVALIABLE) {
                        playSound()
                    } else if(MEDIA_PLAYER_STATE == MEDIA_PLAYER_STOPED){
                        startSound()
                    } else if(MEDIA_PLAYER_STATE == MEDIA_PLAYER_AVAILABLE) {
                        pauseSound()
                    }
                R.id.btn_listening_submit -> submit()
            }
        }
    }

    private fun submit(){
        if(!submitted) {
            btnListeningSubmit.text = getString(R.string.submit_button_text_submitted)
            ListeningActivity.submittedSectionCount++
            ListeningActivity.submitAllListeningSections()
            submitted = true
        }
    }

    private fun playSound(){
        mediaPlayer.isLooping = false
        mediaPlayer.start()
        mediaPlayer.setOnCompletionListener { MediaPlayer.OnCompletionListener { mp -> mp?.release() } }
        mediaPlayer.setOnErrorListener { _, _, _ -> true }
        // Update media player state
        MEDIA_PLAYER_STATE = MEDIA_PLAYER_AVAILABLE
        btnPlayPause.setImageResource(R.drawable.pause)
        tvMediaPlayerDescription.text = getString(R.string.custom_media_player_description_text_playing)
    }

    private fun startSound(){
        if(!mediaPlayer.isPlaying){
            mediaPlayer.start()
        }
        // Update media player state
        MEDIA_PLAYER_STATE = MEDIA_PLAYER_AVAILABLE
        btnPlayPause.setImageResource(R.drawable.pause)
        tvMediaPlayerDescription.text = getString(R.string.custom_media_player_description_text_playing)
    }

    private fun pauseSound(){
        if(mediaPlayer.isPlaying){
            mediaPlayer.pause()
        }
        // Update media player state
        MEDIA_PLAYER_STATE = MEDIA_PLAYER_STOPED
        btnPlayPause.setImageResource(R.drawable.play)
        tvMediaPlayerDescription.text = getString(R.string.custom_media_player_description_text_stopped)
    }
}