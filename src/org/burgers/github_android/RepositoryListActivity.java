package org.burgers.github_android;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import org.eclipse.egit.github.core.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class RepositoryListActivity extends ListActivity {
    public static final String USER_NAME = "user_name";

    @Override
     public void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);

        String username = getIntent().getStringExtra(USER_NAME);

        List<Repository> repositories = null;
        try {
            repositories = new GetRepositoriesTask().execute(username).get();
        } catch (InterruptedException e) {
            repositories = new ArrayList<Repository>();
            e.printStackTrace();
        } catch (ExecutionException e) {
            repositories = new ArrayList<Repository>();
            e.printStackTrace();
        }

        setTitle(username);
        setListAdapter(new RepositoryListAdapter(this, R.layout.row_layout, repositories));
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        Repository repo = (Repository) l.getItemAtPosition(position);

        Intent intent = new Intent(this, RepositoryDetailsActivity.class);

        intent.putExtra(RepositoryListActivity.USER_NAME, repo.getOwner().getName());
        intent.putExtra(RepositoryDetailsActivity.REPOSITORY, repo.getName());
        startActivity(intent);
    }
}
