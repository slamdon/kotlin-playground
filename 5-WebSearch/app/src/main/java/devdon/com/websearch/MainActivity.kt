package devdon.com.websearch

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val webViewClient = WebViewClient()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        webView.setWebViewClient(webViewClient)
        webView.loadUrl("https://www.google.com")

        // set handler
        searchButton.setOnClickListener(searchButtonHandler)
        searchEditText.setOnEditorActionListener(searchEditTextHandler)

    }

    private var searchButtonHandler = View.OnClickListener { view ->
        val searchText = searchEditText.text.toString()
        search(searchText)

        val ime = this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        ime.hideSoftInputFromWindow(view.windowToken, 0)
    }

    private var searchEditTextHandler = TextView.OnEditorActionListener { view, actionId, event ->
        println("did enter EditTextHandler")
        if(actionId == EditorInfo.IME_ACTION_SEARCH){
            val searchText = searchEditText.text.toString()
            search(searchText)
        }

        val ime = this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        ime.hideSoftInputFromWindow(view.windowToken, 0)
    }

    private fun search(text:String){
        if (text.isEmpty()) {
            val toast = Toast.makeText(this,"請輸入內容",1)
            toast.show()
        } else {
            webView.loadUrl("https://www.google.com.tw/search?q=$text")
        }
    }

}
