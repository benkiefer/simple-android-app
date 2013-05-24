package org.burgers.github_android;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class RepositoryDetailsActivity extends Activity {
    public static final String REPOSITORY = "repository_list_item";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.repository_detail);

//        String username = getIntent().getStringExtra(RepositoryListActivity.USER_NAME);
        String repositoryName = getIntent().getStringExtra(RepositoryDetailsActivity.REPOSITORY);

        TextView name = (TextView) findViewById(R.id.repository_detail_name);
        name.setText(repositoryName);

        setTitle(repositoryName);
    }
}
