package vanitar.mdp.foodiepal_culinary_companion

data class BlogPost(
    val title: String,
    val content: String,
    val comments: MutableList<Comment> = mutableListOf()
)

data class Comment(
    val author: String,
    val text: String
)
