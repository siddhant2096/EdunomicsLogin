package com.example.edunomicslogin

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.*
import kotlin.collections.ArrayList

class MessageChatAdapter(
    val messageChatModelList: List<MessageChatModel>,
    var context: Context
) : RecyclerView.Adapter<MessageChatAdapter.MessageHolder>() {
    // Determines the appropriate ViewType according to the sender of the message.
    override fun getItemViewType(position: Int): Int {
        return if (messageChatModelList[position].viewType == 0) {
            // If the current user is the sender of the message
            VIEW_TYPE_MESSAGE_SENT
        } else {
            // If some other user sent the message
            VIEW_TYPE_MESSAGE_RECEIVED
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MessageHolder {
        return (if (viewType == VIEW_TYPE_MESSAGE_SENT) {
            MessageHolder.SentMessageHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.send_layout, parent, false)
            )
        } else {
            MessageHolder.ReceivedMessageHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.receive_layout, parent, false)
            )

        })

    }

    override fun onBindViewHolder(
        holder: MessageHolder,
        position: Int
    ) {
        val message = messageChatModelList[position]
        when (holder!!.itemViewType) {
            VIEW_TYPE_MESSAGE_SENT -> (holder as MessageHolder.SentMessageHolder?)!!.bind(
                message
            )
            VIEW_TYPE_MESSAGE_RECEIVED -> (holder as MessageHolder.ReceivedMessageHolder?)!!.bind(
                message
            )
        }
    }

    override fun getItemCount(): Int {
        return messageChatModelList.size
    }


    open class MessageHolder(itemview: View) : RecyclerView.ViewHolder(itemview) {
        class SentMessageHolder(itemView: View) :
            MessageHolder(itemView) {
            var message: TextView
            var time: TextView
            fun bind(messageModel: MessageChatModel) {
                message.text = messageModel.text
                time.text = messageModel.time
            }

            init {
                message = itemView.findViewById<View>(R.id.message) as TextView
                time = itemView.findViewById<View>(R.id.time) as TextView
            }
        }

        class ReceivedMessageHolder(itemView: View) :
            MessageHolder(itemView) {
            var message: TextView
            var time: TextView
            fun bind(messageModel: MessageChatModel) {
                message.text = messageModel.text
                time.text = messageModel.time
            }

            init {
                message = itemView.findViewById<View>(R.id.message) as TextView
                time = itemView.findViewById<View>(R.id.time) as TextView
            }
        }
    }

    companion object {
        private const val VIEW_TYPE_MESSAGE_SENT = 1
        private const val VIEW_TYPE_MESSAGE_RECEIVED = 2
    }





    }
