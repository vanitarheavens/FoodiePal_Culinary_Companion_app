package vanitar.mdp.foodiepal_culinary_companion

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class BlogAdapter(private val blogPosts: List<BlogPost>, private val itemClickListener: OnItemClickListener) :
    RecyclerView.Adapter<BlogAdapter.BlogViewHolder>() {

    interface OnItemClickListener {
        fun onItemClick(blogPost: BlogPost)
    }

    class BlogViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTextView: TextView = itemView.findViewById(R.id.titleTextView)
        val contentTextView: TextView = itemView.findViewById(R.id.contentTextView)
        val commentRecyclerView: RecyclerView = itemView.findViewById(R.id.commentRecyclerView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BlogViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_blog_post, parent, false)
        return BlogViewHolder(view)
    }

    override fun onBindViewHolder(holder: BlogViewHolder, position: Int) {
        val blogPost = blogPosts[position]
        holder.titleTextView.text = blogPost.title
        holder.contentTextView.text = blogPost.content

        // Display comments if any
        val commentAdapter = CommentAdapter(blogPost.comments)
        holder.commentRecyclerView.adapter = commentAdapter
    }

    override fun getItemCount(): Int {
        return blogPosts.size
    }
}
