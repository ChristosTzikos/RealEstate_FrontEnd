package com.example.real_estate

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.google.api.gax.core.FixedCredentialsProvider
import com.google.auth.oauth2.GoogleCredentials
import com.google.auth.oauth2.ServiceAccountCredentials
import com.google.cloud.dialogflow.v2.DetectIntentRequest
import com.google.cloud.dialogflow.v2.DetectIntentResponse
import com.google.cloud.dialogflow.v2.QueryInput
import com.google.cloud.dialogflow.v2.SessionName
import com.google.cloud.dialogflow.v2.SessionsClient
import com.google.cloud.dialogflow.v2.SessionsSettings
import com.google.cloud.dialogflow.v2.TextInput
import com.example.real_estate.adapters.ChatAdapter
import com.example.real_estate.adapters.ChatAdapter.*
import com.example.real_estate.models.Message
import kotlinx.android.synthetic.main.activity_chatbot.*
import kotlinx.android.synthetic.main.adapter_message_one.*
import kotlinx.android.synthetic.main.adapter_message_one.view.*
import kotlinx.coroutines.Dispatchers.Default
import kotlinx.coroutines.GlobalScope
//import kotlinx.coroutines.NonCancellable.message
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.ArrayList
import java.util.UUID

class Chatbot_activity : AppCompatActivity() {
  private var messageList: ArrayList<Message> = ArrayList()


  //dialogFlow
  private var sessionsClient: SessionsClient? = null
  private var sessionName: SessionName? = null
  private val uuid = UUID.randomUUID().toString()
  private val TAG = "chatbotactivity"
  private lateinit var chatAdapter: ChatAdapter


  @SuppressLint("NotifyDataSetChanged")
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_chatbot)

   // var asdbtn = findViewById<Button>(R.id.btnUrl)

    //setting adapter to recyclerview
    chatAdapter = ChatAdapter(this, messageList)
    chatView.adapter = chatAdapter

    chatAdapter.itemClicks.onEach {

       val intent = Intent(this@Chatbot_activity, MainActivity::class.java)
       intent.putExtra("url", it)
       startActivity(intent)

    }.launchIn(lifecycleScope)


    //onclick listener to update the list and call dialogflow
    btnSend.setOnClickListener {
      val message: String = editMessage.text.toString()
      if (message.isNotEmpty()) {
        addMessageToList(message, false)
        sendMessageToBot(message)
      } else {
        Toast.makeText(this@Chatbot_activity, "Please enter text!", Toast.LENGTH_SHORT).show()
      }
    }

    //initialize bot config
    setUpBot()
  }


  @SuppressLint("NotifyDataSetChanged")
  private fun addMessageToList(message: String, isReceived: Boolean) {
    messageList.add(Message(message, isReceived))
    editMessage.setText("")
    chatAdapter.notifyDataSetChanged()
    chatView.layoutManager?.scrollToPosition(messageList.size - 1)
  }

  private fun setUpBot() {
    try {
      val stream = this.resources.openRawResource(R.raw.credential)
      val credentials: GoogleCredentials = GoogleCredentials.fromStream(stream)
        .createScoped("https://www.googleapis.com/auth/cloud-platform")
      val projectId: String = (credentials as ServiceAccountCredentials).projectId
      val settingsBuilder: SessionsSettings.Builder = SessionsSettings.newBuilder()
      val sessionsSettings: SessionsSettings = settingsBuilder.setCredentialsProvider(
        FixedCredentialsProvider.create(credentials)
      ).build()
      sessionsClient = SessionsClient.create(sessionsSettings)
      sessionName = SessionName.of(projectId, uuid)
      Log.d(TAG, "projectId : $projectId")
    } catch (e: Exception) {
      Log.d(TAG, "setUpBot: " + e.message)
    }
  }

  private fun sendMessageToBot(message: String) {
    val input = QueryInput.newBuilder()
      .setText(TextInput.newBuilder().setText(message).setLanguageCode("el")).build()
    GlobalScope.launch {
      sendMessageInBg(input)
    }
  }

  private suspend fun sendMessageInBg(
    queryInput: QueryInput
  ) {
    withContext(Default) {
      try {
        val detectIntentRequest = DetectIntentRequest.newBuilder()
          .setSession(sessionName.toString())
          .setQueryInput(queryInput)
          .build()
        val result = sessionsClient?.detectIntent(detectIntentRequest)
        if (result != null) {
          runOnUiThread {
            updateUI(result)
          }
        }
      } catch (e: java.lang.Exception) {
        Log.d(TAG, "doInBackground: " + e.message)
        e.printStackTrace()
      }
    }
  }


  private fun updateUI(response: DetectIntentResponse) {
    val botReply = response.queryResult.fulfillmentText
    if (botReply.isNotEmpty()) {

      addMessageToList(botReply, true)

    }
    //Toast.makeText(this, botReply, Toast.LENGTH_SHORT).show()

  }
}