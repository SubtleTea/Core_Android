package com.example.socialmediaapp;

import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.socialmediaapp.loopjtasks.UserAPIClient;
import com.example.socialmediaapp.tools.GeneralTools;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ProfilePage extends AppCompatActivity implements UserAPIClient.OnRetrieveDetails{

    private Context context = ProfilePage.this;
    private TextView userName;
    private TextView githubLink;
    private TextView linkedinLink;
    private TextView skills;
    private TextView classes;
    private ProfilePage instance = null;
    private Button editName;
    private Button editGit;
    private Button editLi;
    private Button editSkill;
    private Button editClass;
    private long mLastClickTime = 0;
    private UserAPIClient userDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_page);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        instance = this;
        userName = (TextView) findViewById(R.id.userName);
        githubLink = (TextView) findViewById(R.id.githubLink);
        linkedinLink = (TextView) findViewById(R.id.linkedinLink);
        skills = (TextView) findViewById(R.id.skillsList);
        classes = (TextView) findViewById(R.id.classesList);
        editName = (Button) findViewById(R.id.editName_button);
        editGit = (Button) findViewById(R.id.editGithub_button);
        editLi = (Button) findViewById(R.id.editLI_button);
        editSkill = (Button) findViewById(R.id.editSkills_button);
        editClass = (Button) findViewById(R.id.editClasses_button);

        userDetails = new UserAPIClient(getApplicationContext(), instance);
        userDetails.getUserDetails();

        // editName Button
        editName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // prevents user from clicking button multiple times in less than 3 seconds
                if (SystemClock.elapsedRealtime() - mLastClickTime < 3000)
                    return;
                mLastClickTime = SystemClock.elapsedRealtime();
                // TODO: EDIT NAME
                sendUserToEditName();
            }
        });

        // editGit Button
        editGit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // prevents user from clicking button multiple times in less than 3 seconds
                if (SystemClock.elapsedRealtime() - mLastClickTime < 3000)
                    return;
                mLastClickTime = SystemClock.elapsedRealtime();
                // TODO: EDIT GITHUB
                sendUserToEditGithub();
            }
        });

        // editLi Button
        editLi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // prevents user from clicking button multiple times in less than 3 seconds
                if (SystemClock.elapsedRealtime() - mLastClickTime < 3000)
                    return;
                mLastClickTime = SystemClock.elapsedRealtime();
                // TODO: EDIT LINKEDIN
                sendUserToEditLinkedIn();
            }
        });

        // editSkill Button
        editSkill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // prevents user from clicking button multiple times in less than 3 seconds
                if (SystemClock.elapsedRealtime() - mLastClickTime < 3000)
                    return;
                mLastClickTime = SystemClock.elapsedRealtime();
                sendUserToSkills();
                // TODO: UPDATE CLASSES AFTER EDIT
            }
        });

        // editClass Button
        editClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // prevents user from clicking button multiple times in less than 3 seconds
                if (SystemClock.elapsedRealtime() - mLastClickTime < 3000)
                    return;
                mLastClickTime = SystemClock.elapsedRealtime();
                // TODO: EDIT CLASSES
            }
        });

    }

    // menu navigation
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.navigation_menu, menu);
        return true;
    }

    // menu select
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.nav_profile:
                Intent profIntent = new Intent(this, ProfilePage.class);
                this.startActivity(profIntent);
                return true;
            case R.id.nav_Collabs:
                Intent collabIntent = new Intent(this, CollabListActivity.class);
                this.startActivity(collabIntent);
                return true;
            case R.id.nav_messages:
                return true;
            case R.id.nav_logout:
                GeneralTools.doRestart(this, LoginActivity.class);
                finish();
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    // send User to skills screen so they can edit
    private void sendUserToSkills() {
        Intent skillPage = new Intent (ProfilePage.this, UserSkillsActivity.class);
        startActivity(skillPage);
    }

    // send User to editName fragment
    // bundle + key to pass parameter to EditProfileActivity.java
    private void sendUserToEditName() {
        Intent editProfile = new Intent (ProfilePage.this, EditProfileActivity.class);
        Bundle x = new Bundle();
        x.putInt("key",1);
        editProfile.putExtras(x);
        startActivity(editProfile);
    }

    // send User to editGithub fragment
    private void sendUserToEditGithub() {
        Intent editProfile = new Intent (ProfilePage.this, EditProfileActivity.class);
        Bundle x = new Bundle();
        x.putInt("key",2);
        editProfile.putExtras(x);
        startActivity(editProfile);
    }


    // send User to editLinkedIn fragment
    private void sendUserToEditLinkedIn() {
        Intent editProfile = new Intent (ProfilePage.this, EditProfileActivity.class);
        Bundle x = new Bundle();
        x.putInt("key",3);
        editProfile.putExtras(x);
        startActivity(editProfile);
    }

    // parse JSON object into the fields we want
    private void getUserName (JSONObject response){
        try {
            userName.setText(response.getString("username"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void getUserGithub (JSONObject response){
        try {
            githubLink.setText(response.getString("github"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void getUserLinkedIn (JSONObject response){
        try {
            linkedinLink.setText(response.getString("linkedin"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void getUserSkills (JSONObject response){
        try {
            JSONArray skillList = null;
            skillList = response.getJSONArray("skills");

            for(int i=0; i < skillList.length(); i++){
                String skillString = skillList.getString(i);
                skills.append(skillString + "\n");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void getUserClasses (JSONObject response){
        try {
            JSONArray classList = null;
            classList = response.getJSONArray("classes");

            for(int i=0; i < classList.length(); i++){
                String classString = classList.getString(i);
                classes.append(classString + "\n");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    // abstract function from UserAPIClient.java defined here
    @Override
    public void detailsRetrieved(Boolean success, JSONObject response) {
        if (success) {
            getUserName(response);
            getUserGithub(response);
            getUserLinkedIn(response);
            getUserSkills(response);
            getUserClasses(response);
        } else {
            // show error message to user
            Toast t = Toast.makeText(context, "ERROR", Toast.LENGTH_LONG);
            t.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0, 0);
            t.show();
        }
    }

}
