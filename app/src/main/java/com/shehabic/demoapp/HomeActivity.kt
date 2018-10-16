package com.shehabic.demoapp

import android.os.Bundle
import android.support.v7.app.AppCompatActivity;
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_home.*
import android.widget.FrameLayout
import com.deliveryhero.pandora.helpcenterflutter.HelpCenterFlutterPlugin
import io.flutter.view.FlutterMain
import io.flutter.facade.Flutter
import io.flutter.plugin.common.MethodChannel

class HomeActivity : AppCompatActivity(), HelpCenterFlutterPlugin.Listener {
    override fun onDataRequested(type: String, result: MethodChannel.Result) {
        result.success("A nice Android app responded to $type")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setSupportActionBar(toolbar)
        FlutterMain.startInitialization(this.applicationContext)
        HelpCenterFlutterPlugin.registerListener(this)
        fab.setOnClickListener {
            val flutterView = Flutter.createView(this@HomeActivity, lifecycle, "helpcenter")
            val layout = FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.MATCH_PARENT
            )
            addContentView(flutterView, layout)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_home, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
