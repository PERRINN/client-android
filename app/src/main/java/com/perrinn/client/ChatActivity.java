package com.perrinn.client;

/**
 * Created by Antreas Christofi on 7/15/2016.
 */
        import android.os.Bundle;
        import android.support.v7.app.AppCompatActivity;
        import android.text.TextUtils;
        import android.view.Menu;
        import android.view.MenuItem;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.ListView;
        import android.widget.RelativeLayout;
        import android.widget.TextView;

        import com.chatlayoutexample.R;

        import java.text.DateFormat;
        import java.util.ArrayList;
        import java.util.Date;


public class ChatActivity extends AppCompatActivity {

    /*
    * /////////////////////////
    * variables for chat
    * /////////////////////////
    * */
    private EditText messageET;
    private ListView messagesContainer;
    private Button sendBtn;
    private ChatAdapter adapter;
    private ArrayList<ChatMessage> chatHistory;


    /*
* /////////////////////////
* This method launches when the activity does
* /////////////////////////
* */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        initControls();
    }

    /*
* /////////////////////////
* This method specifies options menu behaviour
* /////////////////////////
* */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_chat, menu);
        return true;
    }


    /*
* /////////////////////////
* This method specifies what happens when you click on the options menu
* /////////////////////////
* */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    /*
* /////////////////////////
* This method specifies the initial controls for the chat when the activity is launched
* loads a dummy history for testing
* sets onclickistener for the send button
* /////////////////////////
* */

    private void initControls() {
        messagesContainer = (ListView) findViewById(R.id.messagesContainer);
        messageET = (EditText) findViewById(R.id.messageEdit);
        sendBtn = (Button) findViewById(R.id.chatSendButton);

        //TextView meLabel = (TextView) findViewById(R.id.meLbl);
        //TextView companionLabel = (TextView) findViewById(R.id.friendLabel);
        RelativeLayout container = (RelativeLayout) findViewById(R.id.container);
        //companionLabel.setText("My Buddy");

        loadDummyHistory();

        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String messageText = messageET.getText().toString();
                if (TextUtils.isEmpty(messageText)) {
                    return;
                }

                ChatMessage chatMessage = new ChatMessage();
                chatMessage.setId(122);//dummy
                chatMessage.setMessage(messageText);
                chatMessage.setDate(DateFormat.getDateTimeInstance().format(new Date()));
                chatMessage.setMe(true);

                messageET.setText("");

                displayMessage(chatMessage);
            }
        });


    }


    /*
* /////////////////////////
* This method displays the messages
* /////////////////////////
*/
    public void displayMessage(ChatMessage message) {
        adapter.add(message);
        adapter.notifyDataSetChanged();
        scroll();
    }


    /*
* /////////////////////////
* This method implements scrolling behaviour
* /////////////////////////
* */

    private void scroll() {
        messagesContainer.setSelection(messagesContainer.getCount() - 1);
    }


    /*
* /////////////////////////
* This method is a dummy method for testing
* /////////////////////////
* */

    private void loadDummyHistory(){

        chatHistory = new ArrayList<ChatMessage>();

        ChatMessage msg = new ChatMessage();
        msg.setId(1);
        msg.setMe(false);
        msg.setMessage("Hello world!");
        msg.setDate(DateFormat.getDateTimeInstance().format(new Date()));
        chatHistory.add(msg);
        ChatMessage msg1 = new ChatMessage();
        msg1.setId(2);
        msg1.setMe(false);
        msg1.setMessage("Anyone there?");
        msg1.setDate(DateFormat.getDateTimeInstance().format(new Date()));
        chatHistory.add(msg1);

        adapter = new ChatAdapter(ChatActivity.this, new ArrayList<ChatMessage>());
        messagesContainer.setAdapter(adapter);

        for(int i=0; i<chatHistory.size(); i++) {
            ChatMessage message = chatHistory.get(i);
            displayMessage(message);
        }

    }

}

