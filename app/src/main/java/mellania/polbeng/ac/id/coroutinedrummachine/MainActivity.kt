package mellania.polbeng.ac.id.coroutinedrummachine

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class MainActivity : AppCompatActivity() {
        private lateinit var chilled: MediaPlayer
        private lateinit var ring: MediaPlayer

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_main)

            chilled = MediaPlayer.create(this, R.raw.chilled)
            ring = MediaPlayer.create(this, R.raw.ring)
            btnStart.setOnClickListener {
                runBlocking {
                    launch {
                        playBeats("x-x-x-x-x-x-x-x-x-x-x-x-", R.raw.ring)
                    }
                    playBeats("x-----x-----x-----x-----", R.raw.chilled)
                }
            }
        }

        suspend fun playBeats(beats: String, fileId: Int) {
            val parts = beats.split("x")
            var count = 0
            for (part in parts) {
                count += part.length + 1
                if (part == "") {
                    if (fileId == R.raw.chilled)
                        chilled.start()
                    else
                        ring.start()
                } else {
                    delay(1000 * (part.length + 1L))
                    if (count < beats.length) {
                        if (fileId == R.raw.chilled)
                            chilled.start()
                        else
                            ring.start()
                    }
                }
            }
        }

        override fun onStop() {
            super.onStop()
            chilled.stop()
            ring.stop()
        }
    }