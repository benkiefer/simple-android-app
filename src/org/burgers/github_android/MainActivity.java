package org.burgers.github_android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }

    public void findRepositories(View view){
        Intent intent = new Intent(this, RepositoryListActivity.class);
        EditText editText = (EditText) findViewById(R.id.repository_search_username);
        String username = editText.getText().toString();
        intent.putExtra(RepositoryListActivity.USER_NAME, username);
        startActivity(intent);
    }

}
