package com.example.edunomicslogin

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_chat_interface.*
import java.util.*
import kotlin.collections.ArrayList

class ChatInterface : AppCompatActivity() {
    var messageChatModelList: MutableList<MessageChatModel> =
        ArrayList()
    var displayList: MutableList<MessageChatModel> = ArrayList()
    var recyclerView: RecyclerView? = null
    var adapter: MessageChatAdapter? = null
    var messageET: EditText? = null
    var sendBtn: ImageView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat_interface)
        var friendname: String
        friendname= " "
        val intent: Intent
        intent=getIntent()
        val extras = intent.extras
        if (extras != null) {
            friendname = extras.getString("username").toString()
        }
        friend.text=friendname
      messageET = findViewById<View>(R.id.messageET) as EditText
        sendBtn = findViewById<View>(R.id.sendBtn) as ImageView
        recyclerView = findViewById<View>(R.id.recycler_view) as RecyclerView
        val manager =
            LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        recyclerView!!.layoutManager = manager
        val model1 = MessageChatModel(
            "Hello. How are you today?",
            "10:00 PM",
            0
        )
        val model2 = MessageChatModel(
            "Hey! I'm fine. WBY?",
            "10:00 PM",
            1
        )
        val model3 = MessageChatModel(
            "Coming to college, tommorow?",
            "10:00 PM",
            0
        )
        val model4 = MessageChatModel(
            "Ya, see you there",
            "10:00 PM",
            1
        )
        messageChatModelList.add(model1)
        messageChatModelList.add(model2)
        messageChatModelList.add(model3)
        messageChatModelList.add(model4)
        messageChatModelList.add(model1)
        messageChatModelList.add(model2)
        messageChatModelList.add(model3)
        messageChatModelList.add(model4)
        messageChatModelList.add(model1)
        messageChatModelList.add(model2)
        messageChatModelList.add(model3)
        messageChatModelList.add(model4)
        displayList.addAll(messageChatModelList)
        recyclerView!!.smoothScrollToPosition(messageChatModelList.size)
        adapter = MessageChatAdapter(messageChatModelList, this)

        recyclerView!!.adapter = adapter
        sendBtn!!.setOnClickListener {
            val msg = messageET!!.text.toString()
            val model = MessageChatModel(
                msg,
                "10:00 PM",
                0
            )
            messageChatModelList.add(model)
            recyclerView!!.smoothScrollToPosition(messageChatModelList.size)
            adapter!!.notifyDataSetChanged()
            messageET!!.setText("")
        }
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater=menuInflater
        inflater.inflate(R.menu.main_menu,menu)
        val manager=getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchitem= menu?.findItem(R.id.searchbar)

        val searchView=searchitem?.actionView as SearchView
        searchView.setOnQueryTextListener(object :SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                /* searchView.clearFocus()
                 searchView.setQuery("",false)
                 searchitem.collapseActionView()
                 Toast.makeText(this@ChatMainActivity,"Searching",Toast.LENGTH_LONG)*/
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if(newText!!.isNotEmpty())
                {
                    displayList.clear()
                    val search=newText.toLowerCase(Locale.getDefault())
                    messageChatModelList.forEach {
                        if(it.text.toLowerCase(Locale.getDefault()).contains(search))
                        {Toast.makeText(this@ChatInterface,it.text,Toast.LENGTH_LONG).show()
                            displayList.add(it)
                        }
                    }
                }
                else
                {
                    displayList.clear()
                    displayList.addAll(messageChatModelList)
                    recyclerView?.adapter!!.notifyDataSetChanged()
                }
                return true;
            }
        })
        return super.onCreateOptionsMenu(menu)
    }
}