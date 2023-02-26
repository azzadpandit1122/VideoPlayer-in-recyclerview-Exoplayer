package com.wajahatkarim3.autovideoplayer

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.wajahatkarim3.autovideoplayer.adapter.FacebookRecyclerAdapter
import com.wajahatkarim3.autovideoplayer.databinding.ActivityMainBinding
import com.wajahatkarim3.autovideoplayer.model.MediaObject
import com.wajahatkarim3.autovideoplayer.utils.PlayerViewAdapter.Companion.playIndexThenPausePreviousPlayer
import com.wajahatkarim3.autovideoplayer.utils.PlayerViewAdapter.Companion.releaseAllPlayers
import com.wajahatkarim3.autovideoplayer.utils.RecyclerViewScrollListener


class MainActivity : AppCompatActivity() {
    //val binding: ActivityMainBinding

    private var recyclerView: RecyclerView? = null
    private var mAdapter: FacebookRecyclerAdapter? = null
    private var modelList = ArrayList<MediaObject>()

    // for handle scroll and get first visible item index
    private lateinit var scrollListener: RecyclerViewScrollListener
    lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       /* var binding: ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_contact_list)*/

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        recyclerView = binding.rvFeed

        listCreate()

        setAdapter()

    }

    private fun listCreate() {
        modelList =
        arrayListOf(
            MediaObject(
                "Big Buck Bunny",
                "https://media.nojoto.com/content/media/27795728/2023/02/feed/50ee93e67cc24929af82f34365cfb474/50ee93e67cc24929af82f34365cfb474_default.mp4",
                "https://static.klliq.com/thumbnails/UFfUCqtb4FYwLRmI_m2Pq8xvRw-7vA-2.png",
                "Big Buck Bunny tells the story of a giant rabbit with a heart bigger than himself. When one sunny day three rodents rudely harass him, something snaps... and the rabbit ain't no bunny anymore! In the typical cartoon tradition he prepares the nasty rodents a comical revenge.\\n\\nLicensed under the Creative Commons Attribution license\\nhttp://www.bigbuckbunny.org"
            ),
            MediaObject(
                "Elephant Dream",
                "https://media.nojoto.com/content/media/2607115/2021/04/feed/9d1f3f63c68f47af6ab9c635e1246b75/9d1f3f63c68f47af6ab9c635e1246b75_default.mp4",
                "https://static.klliq.com/thumbnails/uYSHHSfB6F183ZHYk1OnBjCe5C_1yseF.png",
                "The first Blender Open Movie from 2006"
            ),
            MediaObject(
                "For Bigger Blazes",
                "https://media.nojoto.com/content/media/27795728/2023/02/feed/50ee93e67cc24929af82f34365cfb474/50ee93e67cc24929af82f34365cfb474_default.mp4",
                "https://static.klliq.com/thumbnails/5a7Byj0r5ZIKC0gV9QWCneZQZEmKCP-B.png",
                "HBO GO now works with Chromecast -- the easiest way to enjoy online video on your TV. For when you want to settle into your Iron Throne to watch the latest episodes. For \$35.\\nLearn how to use Chromecast with HBO GO and more at google.com/chromecast."
            ),
            MediaObject(
                "For Bigger Escape",
                "https://media.nojoto.com/content/media/4702025/2021/11/feed/0fc4769d78bb033572f0e086eafcf147/0fc4769d78bb033572f0e086eafcf147_default.mp4",
                "https://static.klliq.com/thumbnails/vGRpl-Xw45xfOCborXr3bwAsl0uu_qMA.png",
                "Introducing Chromecast. The easiest way to enjoy online video and music on your TV—for when Batman's escapes aren't quite big enough. For \$35. Learn how to use Chromecast with Google Play Movies and more at google.com/chromecast."
            ),
            MediaObject(
                "For Bigger Fun",
                "https://media.nojoto.com/content/media/245648/2019/08/feed/62d2394af9d45a9c3517df0498ef5f3b/62d2394af9d45a9c3517df0498ef5f3b_default.mp4",
                "https://static.klliq.com/thumbnails/ZnFAHzGD9RQrRsBjJt2Pv3Y1vIAo11FX.png",
                "Introducing Chromecast. The easiest way to enjoy online video and music on your TV. For \$35.  Find out more at google.com/chromecast."
            )
        )
    }

    private fun setAdapter() {
        mAdapter = FacebookRecyclerAdapter(this, modelList)
        recyclerView!!.setHasFixedSize(true)

        // use a linear layout manager
        val layoutManager = LinearLayoutManager(this)
        recyclerView!!.layoutManager = layoutManager
        recyclerView!!.adapter = mAdapter
        scrollListener = object : RecyclerViewScrollListener() {
            override fun onItemIsFirstVisibleItem(index: Int) {
                Log.d("visible item index", index.toString())
                // play just visible item
                if (index != -1)
                    playIndexThenPausePreviousPlayer(index)
            }

        }
        recyclerView!!.addOnScrollListener(scrollListener)
        mAdapter!!.SetOnItemClickListener(object : FacebookRecyclerAdapter.OnItemClickListener {
            override fun onItemClick(view: View?, position: Int, model: MediaObject?) {

            }
        })
    }
    override fun onPause() {
        super.onPause()
        releaseAllPlayers()
    }


}