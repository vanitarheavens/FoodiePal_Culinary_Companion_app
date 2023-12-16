package vanitar.mdp.foodiepal_culinary_companion

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import androidx.appcompat.app.AlertDialog

class BlogFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var fabAddBlogPost: FloatingActionButton
    private lateinit var blogAdapter: BlogAdapter

    private val blogPosts = mutableListOf<BlogPost>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_blog, container, false)

        // Initialize RecyclerView
        recyclerView = view.findViewById(R.id.recyclerViewBlog)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // Initialize FloatingActionButton
        fabAddBlogPost = view.findViewById(R.id.fabAddBlogPost)

        // Initialize the adapter with an empty list (or existing data)
        blogAdapter = BlogAdapter(blogPosts, object : BlogAdapter.OnItemClickListener {
            override fun onItemClick(blogPost: BlogPost) {
                // Handle item click (e.g., open blog post details)
            }
        })
        recyclerView.adapter = blogAdapter

        // Set up FloatingActionButton click listener
        fabAddBlogPost.setOnClickListener {
            // Show the add blog post dialog
            showAddBlogPostDialog()
        }

        return view
    }

    private fun showAddBlogPostDialog() {
        val dialogView = layoutInflater.inflate(R.layout.dialog_add_blog_post, null)

        val etBlogTitle = dialogView.findViewById<EditText>(R.id.etBlogTitle)
        val etBlogComment = dialogView.findViewById<EditText>(R.id.etBlogComment)

        val alertDialogBuilder = AlertDialog.Builder(requireContext())
            .setTitle("Add Blog Post")
            .setView(dialogView)
            .setPositiveButton("Add") { _, _ ->
                val title = etBlogTitle.text.toString()
                val content = etBlogComment.text.toString()

                // Create a new blog post
                val newBlogPost = BlogPost(title, content)

                // Add the new blog post to the list
                blogPosts.add(newBlogPost)

                // Notify the adapter that the data set has changed
                blogAdapter.notifyDataSetChanged()
            }
            .setNegativeButton("Cancel", null)

        alertDialogBuilder.show()
    }
}