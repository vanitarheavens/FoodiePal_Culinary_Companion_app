package vanitar.mdp.foodiepal_culinary_companion

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {

    private lateinit var viewPager: ViewPager2
    private lateinit var tabLayout: TabLayout
    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var fabAdd: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewPager = findViewById(R.id.viewPager)
        tabLayout = findViewById(R.id.tabLayout)
        bottomNavigationView = findViewById(R.id.bottomNavigationView)


        val viewPager2 = setupViewPager()
        setupTabLayout(viewPager2)
        setupBottomNavigationView()
    }

    private fun setupViewPager(): ViewPager2 {
        val adapter = MainPagerAdapter(this)
        adapter.addFragment(RecipesFragment(), "Recipes")
        adapter.addFragment(MealPlannerFragment(), "Meal Planner")
        adapter.addFragment(BlogFragment(), "Blog")
        adapter.addFragment(ContactFragment(), "Contact")
        adapter.addFragment(AboutMeFragment(), "About Me")

        viewPager.adapter = adapter
        return viewPager
    }

    private fun setupTabLayout(viewPager: ViewPager2) {
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            when (position) {
                0 -> tab.text = "Recipes"
                1 -> tab.text = "Menu Planner"
                2 -> tab.text = "Blog"
                3 -> tab.text = "Contact"
                4 -> tab.text = "About Me"
            }
        }.attach()
    }
    private fun setupBottomNavigationView() {
        bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.menu_recipes -> viewPager.currentItem = 0
                R.id.menu_menu_planner -> viewPager.currentItem = 1
                R.id.menu_blog -> viewPager.currentItem = 2
                // Add more cases if needed for additional items
            }
            true
        }
    }

}
