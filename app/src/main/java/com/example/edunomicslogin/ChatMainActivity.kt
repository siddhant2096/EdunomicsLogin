package com.example.edunomicslogin

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import androidx.appcompat.widget.SearchView
import android.view.Menu
import android.widget.*
import kotlinx.android.synthetic.main.activity_chat_main.*

class ChatMainActivity : AppCompatActivity() {
lateinit var listItems: ArrayList<String>
    lateinit var adapter: ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat_main)
        val listView: ListView
        listView = findViewById<ListView>(R.id.userslist)
// 1
// 2
         listItems = ArrayList<String>()
        listItems.add("Utkarsh")
        listItems.add("Yogesh")
        listItems.add("Sandeep")
// 3
// 4
        userslist.setTextFilterEnabled(true)
         adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, listItems)

        listView.adapter = adapter
        search_bar.setAdapter(adapter)
        search_bar.threshold=1
        listView.setOnItemClickListener { parent, view, position, id ->
            val intent = Intent(this, ChatInterface::class.java)
            intent.putExtra("username", listItems[position])
            startActivity(intent)
        }

search_bar.addTextChangedListener(object : TextWatcher {
    override fun afterTextChanged(s: Editable) {

    }


    override fun beforeTextChanged(s: CharSequence, start: Int,
                                   count: Int, after: Int) {
    }

    override fun onTextChanged(s: CharSequence, start: Int,
                               before: Int, count: Int) {
        (this@ChatMainActivity).adapter.getFilter().filter(s)
    }



}
)
    }

    /*override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater=menuInflater
        inflater.inflate(R.menu.main_menu,menu)
        val manager=getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchitem= menu?.findItem(R.id.searchbar)

        val searchView=searchitem?.actionView as SearchView
        searchView.setSearchableInfo(manager.getSearchableInfo(componentName))
        searchView.setSubmitButtonEnabled(true)
        searchView.setOnQueryTextListener(object :SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
               /* searchView.clearFocus()
                searchView.setQuery("",false)
                searchitem.collapseActionView()
                Toast.makeText(this@ChatMainActivity,"Searching",Toast.LENGTH_LONG)*/
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                adapter.getFilter().filter(newText);

                return true;
            }
        })
        return super.onCreateOptionsMenu(menu)
    }

*/


    }



