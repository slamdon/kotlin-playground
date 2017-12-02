package devdon.com.gtihubstars

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import devdon.com.gtihubstars.model.ProjectModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_star_list_item.*
import okhttp3.*
import org.json.JSONArray
import java.io.IOException
import java.io.Serializable

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupView()
    }

    private fun setupView() {
        searchButton.setOnClickListener(searchButtonClickHandler)
    }

    private var searchButtonClickHandler = View.OnClickListener { view ->
        val username = nameEditText.text
        if(username.isEmpty()){
            Toast.makeText(this@MainActivity,"please input username",Toast.LENGTH_SHORT).show()
            return@OnClickListener
        }

        val client = OkHttpClient()
        val request = Request.Builder()
                .url("https://api.github.com/users/$username/starred")
                .build()

        client.newCall(request).enqueue(object: Callback {
            override fun onFailure(call: Call?, e: IOException?) {
                Toast.makeText(this@MainActivity,"get data failed",Toast.LENGTH_SHORT).show()

            }

            override fun onResponse(call: Call?, response: Response?) {
                val responseData = response?.body()?.string()
                val json = JSONArray(responseData)
                val projects: ArrayList<ProjectModel> = ArrayList()

                for(i in 0..(json.length() - 1)){
                    val item = json.getJSONObject(i)

                    val owner = item.getJSONObject("owner")
                    val ownerName = owner.get("login").toString()
                    val avatarURL = owner.get("avatar_url").toString()
                    val projectName = item.get("name").toString()
                    val description = item.get("description").toString()
                    val starCount = item.get("stargazers_count").toString().toInt()
                    val forkCount = item.get("forks_count").toString().toInt()

                    val project = ProjectModel(projectName, description, avatarURL, starCount, forkCount, ownerName)
                    projects.add(project)
                }

                val intent = Intent(this@MainActivity, ProjectListActivity::class.java)
                val bundle = Bundle()
                bundle.putParcelableArrayList("projects", projects)
                intent.putExtras(bundle)
                startActivity(intent)

            }

        })


    }



}
