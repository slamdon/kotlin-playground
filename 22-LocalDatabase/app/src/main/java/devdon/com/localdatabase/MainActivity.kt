package devdon.com.localdatabase

import android.content.DialogInterface
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.widget.GridLayoutManager
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_main.*
import android.widget.EditText


class MainActivity : AppCompatActivity() {

    lateinit var adapter: ItemsAdapter
    lateinit var dbHelper: MemberDatabaseHelper
    var items = ArrayList<ItemModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dbHelper = MemberDatabaseHelper(this)
        setupView()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.layout_menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if(item!!.itemId == R.id.layout_menu_plus){
            didClickPlus()
            return true
        }

        return super.onOptionsItemSelected(item)
    }

    private fun setupView() {
        adapter = ItemsAdapter(items)
        layoutItemsRecyclerView.layoutManager = GridLayoutManager(this,1)
        layoutItemsRecyclerView.adapter = adapter

        reloadData()

    }

    private fun reloadData() {
        items = dbHelper.getNames()
        adapter.items = items
        adapter.notifyDataSetChanged()
    }

    private fun didClickPlus() {
        val inputAlert = AlertDialog.Builder(this)
        inputAlert.setTitle("Add Name")
        inputAlert.setMessage("Your name is: ")

        val userInput = EditText(this)
        inputAlert.setView(userInput)
        inputAlert.setPositiveButton("新增",DialogInterface.OnClickListener{ dialog, id ->
            addNewName("${userInput.text.toString()}")

        })

        inputAlert.setNegativeButton("取消", DialogInterface.OnClickListener{ dialog, id ->
            println("取消")
        })


        inputAlert.show()
    }

    private fun addNewName(name:String) {
        MemberDatabaseHelper(this).addName(name)
        reloadData()
    }


}
