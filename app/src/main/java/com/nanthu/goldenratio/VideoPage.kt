package com.nanthu.goldenratio

import android.os.Bundle
import android.view.View
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity

class VideoPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video_page)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN //Full Screen Mode

        //Setting Up the Video
        val video = findViewById<VideoView>(R.id.videoView)
        video.setVideoPath("android.resource://" + packageName + "/" + R.raw.grvideo)
        video.start()
    }
}