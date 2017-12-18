package devdon.com.floatingwindow

import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.view.*
import android.view.animation.AnimationUtils
import android.widget.PopupWindow
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_popupwindow.view.*

class MainActivity : AppCompatActivity() {

    private lateinit var popupWindow: PopupWindow
    private lateinit var rootView: View
    private var isFloationActionButtonOpen = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        title = "ShowView"
        rootView = LayoutInflater.from(this).inflate(R.layout.activity_main, null)

        setupPopupWindow()
        setupFloatingActionButton()
    }

    private fun setupPopupWindow() {
        // init
        popupWindow = PopupWindow(this)
    }

    private fun setupFloatingActionButton() {
        val floatingActionButton1:FloatingActionButton = findViewById(R.id.layout_main_floatingActionButton1)
        val floatingActionButton2:FloatingActionButton = findViewById(R.id.layout_main_floatingActionButton2)
        val floatingActionButton3:FloatingActionButton = findViewById(R.id.layout_main_floatingActionButton3)

        // hide two button at the beginning
        floatingActionButton2.visibility = View.INVISIBLE
        floatingActionButton3.visibility = View.INVISIBLE

        // init animations
        val showLayoutAnimation = AnimationUtils.loadAnimation(this, R.anim.show_layout)
        val hideLayoutAnimation = AnimationUtils.loadAnimation(this, R.anim.hide_layout)
        val showButtonAnimation = AnimationUtils.loadAnimation(this, R.anim.show_button)
        val hideButtonAnimation = AnimationUtils.loadAnimation(this, R.anim.hide_button)

        // setup listener
        layout_main_showButton.setOnClickListener(showButtonClickHandler)

        // open/close FloatingActionButton
        floatingActionButton1.setOnClickListener{ view ->
            if(isFloationActionButtonOpen) {
                floatingActionButton2.startAnimation(hideLayoutAnimation)
                floatingActionButton3.startAnimation(hideLayoutAnimation)
                floatingActionButton1.startAnimation(hideButtonAnimation)

            } else {
                floatingActionButton2.startAnimation(showLayoutAnimation)
                floatingActionButton3.startAnimation(showLayoutAnimation)
                floatingActionButton1.startAnimation(showButtonAnimation)
            }

            isFloationActionButtonOpen = !isFloationActionButtonOpen

        }

        floatingActionButton2.setOnClickListener{ _ ->
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("tel:0963123456")))
        }

        floatingActionButton3.setOnClickListener{ _ ->
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("sms:0963123456")))

        }
    }

    private var showButtonClickHandler = View.OnClickListener { view ->
        showPopupWindow()
    }

    private fun showPopupWindow() {
        val popupView = LayoutInflater.from(this).inflate(R.layout.layout_popupwindow, null)

        // set click listener for ok button
        popupView.layout_popupwindow_confirmButton.setOnClickListener{ view ->
            popupWindow.dismiss()
        }

        popupWindow.contentView = popupView
        popupWindow.width = ViewGroup.LayoutParams.MATCH_PARENT
        popupWindow.height = ViewGroup.LayoutParams.WRAP_CONTENT

        // disappear popupWindow if touch outside of it
        popupWindow.isOutsideTouchable = true

        // show popWindow
        popupWindow.showAsDropDown(layout_main_showButton)
    }
}
