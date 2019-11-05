package com.example.myappfcm;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.freshchat.consumer.sdk.Freshchat;
import com.freshchat.consumer.sdk.FreshchatConfig;
import com.freshchat.consumer.sdk.FreshchatUser;
import com.freshchat.consumer.sdk.exception.MethodNotAllowedException;

public class MainActivity extends AppCompatActivity {
    private Button btnShowConversations, btnShowFAQs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //init
        FreshchatConfig freshchatConfig = new FreshchatConfig("b675b02c-df26-49c7-a9f7-da68b7d04af4","71fd7fad-2cff-4901-a1ab-7bd143d8091d");
        Freshchat.getInstance(getApplicationContext()).init(freshchatConfig);

        //Update user information
        FreshchatUser user = Freshchat.getInstance(getApplicationContext()).getUser();
        user.setFirstName("John").setLastName("Doe").setEmail("john@john.doe").setPhone("001", "2542542544");
        try {
            Freshchat.getInstance(getApplicationContext()).setUser(user);
        } catch (MethodNotAllowedException e) {
            e.printStackTrace();
        }

        btnShowFAQs = (Button) findViewById(R.id.btnShowFAQs);
        btnShowConversations = (Button) findViewById(R.id.btnShowConversations);
        btnShowFAQs.setOnClickListener(viewClickListener);
        btnShowConversations.setOnClickListener(viewClickListener);
    }

    View.OnClickListener viewClickListener = new View.OnClickListener() {
        @Override
        public void onClick (View v) {
            if(v.getId() == R.id.btnShowFAQs) {

                Freshchat.showFAQs(MainActivity.this);

            } else if(v.getId() == R.id.btnShowConversations) {

                Freshchat.showConversations(MainActivity.this);

            }
        }
    };

}
