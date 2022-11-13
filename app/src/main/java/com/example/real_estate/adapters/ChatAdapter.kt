package com.example.real_estate.adapters

import android.annotation.SuppressLint
import android.app.Activity
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.real_estate.Chatbot_activity
import com.google.android.material.snackbar.Snackbar
import com.example.real_estate.R
import com.example.real_estate.SecondFragment
import com.example.real_estate.adapters.ChatAdapter.ChatViewHolder
import com.example.real_estate.databinding.ActivityChatbotBinding
import com.example.real_estate.databinding.ActivityMainBinding
import com.example.real_estate.databinding.AdapterMessageOneBinding
import com.example.real_estate.models.Message
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.android.synthetic.main.adapter_message_one.*
import kotlin.math.log

class ChatAdapter(private var activity: Activity, private var messageList: List<Message>) : RecyclerView.Adapter<ChatViewHolder>() {

  class ChatViewHolder(private val itemClickChannel: Channel<String>, private val binding: AdapterMessageOneBinding) : RecyclerView.ViewHolder(binding.root) {

    @SuppressLint("SuspiciousIndentation")
    fun bindItem(item: Message){
      Log.d("asd", item.message)
      binding.resbtn.setOnClickListener{
        itemClickChannel.trySend(item.message)
      }
      if (item.isReceived && item.message.contains("&")) {

        //val url = item.message
        binding.resbtn.visibility = View.VISIBLE
        binding.messageReceive.visibility = View.GONE
        binding.messageSend.visibility = View.GONE


      } else if(item.isReceived){
        binding.resbtn.visibility = View.GONE
        binding.messageReceive.visibility = View.VISIBLE
        binding.messageSend.visibility = View.GONE
        binding.messageReceive.text = item.message
      } else {
        binding.resbtn.visibility = View.GONE
        binding.messageSend.visibility = View.VISIBLE
        binding.messageReceive.visibility = View.GONE
        binding.messageSend.text = item.message
     }
    }

  }

  private val itemClicksChannel: Channel<String> = Channel(Channel.RENDEZVOUS)
  val itemClicks: Flow<String> = itemClicksChannel.receiveAsFlow()

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {
    val layoutInflater = LayoutInflater.from(parent.context)
    val binding = AdapterMessageOneBinding.inflate(layoutInflater, parent, false)
    return ChatViewHolder(itemClicksChannel, binding)
  }

  override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {
    val message: String = messageList[position].message
    val isReceived: Boolean = messageList[position].isReceived
    holder.bindItem(messageList[position])

  }

  override fun getItemCount(): Int {
    return messageList.count()
  }
}