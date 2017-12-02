package devdon.com.gtihubstars

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.MenuItem
import devdon.com.gtihubstars.model.ProjectModel
import kotlinx.android.synthetic.main.activity_project_list.*

class ProjectListActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_project_list)

        projectListRecyclerView.layoutManager = LinearLayoutManager(this)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val projects: ArrayList<ProjectModel> = intent.extras.getParcelableArrayList("projects")
        val adapter = ProjectListAdapter(projects)
        projectListRecyclerView.adapter = adapter

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.getItemId()) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)

    }
}
