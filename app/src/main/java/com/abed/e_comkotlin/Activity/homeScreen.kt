package com.abed.e_comkotlin.Activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.abed.e_comkotlin.R
import com.abed.e_comkotlin.Session.LoginPref
import com.abed.e_comkotlin.adapter.MainRecyclerAdapter
import com.abed.e_comkotlin.models.AllCategories
import com.abed.e_comkotlin.models.CategoryItem
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar


class homeScreen : AppCompatActivity() {

    private var mainCategoryRecycler: RecyclerView? = null
    private var mainRecyclerAdapter: MainRecyclerAdapter? = null

    private lateinit var appBarConfiguration: AppBarConfiguration


    lateinit var session: LoginPref


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_screen)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val fab: FloatingActionButton = findViewById(R.id.fab)
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Opening Cart", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()

            val intent = Intent(this, Cartshow::class.java)
            startActivity(intent)

        }
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow
            ), drawerLayout
        )

        val categoryItemList: MutableList<CategoryItem> = ArrayList()
        categoryItemList.add(CategoryItem(1, R.drawable.ic__hapu))
        categoryItemList.add(CategoryItem(2, R.drawable.ic_smartphone))
        categoryItemList.add(CategoryItem(3, R.drawable.ic_online_shopping))

        val allCategory: MutableList<AllCategories> = ArrayList()
        allCategory.add(AllCategories(categoryItemList))
        setMainCategoryRecycler(allCategory)
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        var imageView: ImageView = findViewById(R.id.logout_Screen)
        var imageViewSql: ImageView = findViewById(R.id.Sql)
        var imageViewSqlView: ImageView = findViewById(R.id.exploreSqlitedata)

        imageView.setOnClickListener {
            val preferences = getSharedPreferences("Login_preference", Context.MODE_PRIVATE)
            val editor = preferences.edit()
            editor.clear()
            editor.apply()
            finish()
            val intent = Intent(this, login_screen::class.java)
            startActivity(intent)
        }

        imageViewSql.setOnClickListener {
            val intent = Intent(this, SqlCrud::class.java)
            startActivity(intent)
        }

        imageViewSqlView.setOnClickListener {
            val intent = Intent(this, ExploreData::class.java)
            startActivity(intent)

        }


    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.home_screen, menu)
        return true
    }

    private fun setMainCategoryRecycler(allCategory: List<AllCategories>) {
        mainCategoryRecycler = findViewById(R.id.main_recycler)
        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(this)
        mainCategoryRecycler!!.layoutManager = layoutManager
        mainRecyclerAdapter = MainRecyclerAdapter(this, allCategory)
        mainCategoryRecycler!!.adapter = mainRecyclerAdapter

    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }


}