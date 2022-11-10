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
import com.google.android.material.snackbar.Snackbar
import com.example.real_estate.R
import com.example.real_estate.adapters.ChatAdapter.ChatViewHolder
import com.example.real_estate.databinding.AdapterMessageOneBinding
import com.example.real_estate.models.Message
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow

class ChatAdapter(private var activity: Activity, private var messageList: List<Message>) : RecyclerView.Adapter<ChatViewHolder>() {

  class ChatViewHolder(private val itemClickChannel: Channel<String>, private val binding: AdapterMessageOneBinding) : RecyclerView.ViewHolder(binding.root) {

//    fun urlempty(item: message): String {
//      val urlasd = item.message
//      if(urlasd.isEmpty()){
//        return urlasd
//      } else {
//        urlasd.replace(item.message, "")
//      }



    @SuppressLint("SuspiciousIndentation")
    fun bindItem(item: Message){
      if (item.isReceived && item.message.contains("http:")) {
        //if (item.message.contains("http:")) {
          //binding.urlbtn.visibility = View.VISIBLE
          //Log.d("thodoris", item.message)
        val url = item.message
          itemClickChannel.trySend(url)
        //}
          binding.urlbtn.visibility = View.VISIBLE
          binding.messageReceive.visibility = View.GONE
          binding.messageSend.visibility = View.GONE

        //url.replace(item.message, "kostas")
        //binding.messageReceive.text = item.message

      } else if(item.isReceived){
        binding.messageReceive.visibility = View.VISIBLE
        binding.messageSend.visibility = View.GONE
        binding.messageReceive.text = item.message
      } else {
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