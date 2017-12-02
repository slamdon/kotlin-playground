package devdon.com.gtihubstars

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import devdon.com.gtihubstars.model.ProjectModel
import kotlinx.android.synthetic.main.layout_star_list_item.view.*

/**
 * Created by slamdon on 2017/12/1.
 */

class ProjectListAdapter(var projects: ArrayList<ProjectModel>) : RecyclerView.Adapter<ProjectListAdapter.ViewHolder>() {

    // 入口
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // 指定了 layout
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_star_list_item, parent, false)
        return ViewHolder(view)
    }

    // 綁定資料
    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder?.bindProjectModel( projects[position] )
    }

    // 返回數目
    override fun getItemCount(): Int {
        return projects.size
    }

    // view
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun bindProjectModel(model: ProjectModel){
            // set description

            with(model){
                itemView.projectTextView.text = projectName
                itemView.descriptionTextView.text = description
                itemView.starTextView.text = "$starCount"
                itemView.forkTextView.text = "$forkCount"
                itemView.usernameTextView.text = username
            }

        }
    }
}