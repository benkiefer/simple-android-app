package org.burgers.github_android;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import org.eclipse.egit.github.core.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class RepositoryListActivity extends ListActivity {
    public static final String USER_NAME = "user_name";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.repository_list);

        String username = getIntent().getStringExtra(USER_NAME);

        List<Repository> repositories = null;
        try {
            repositories = new GetRepositoriesTask(this).execute(username).get(20, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            repositories = new ArrayList<Repository>();
            e.printStackTrace();
        } catch (ExecutionException e) {
            repositories = new ArrayList<Repository>();
        } catch (TimeoutException e) {
            repositories = new ArrayList<Repository>();
        }

        if (!repositories.isEmpty()) {
            setListAdapter(new RepositoryListAdapter(this, R.layout.row_layout, repositories));
        } else {
            TextView emptyView = (TextView) getListView().getEmptyView();
            emptyView.setText("There are no repositories for user: " + username);
        }
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        Repository repo = (Repository) l.getItemAtPosition(position);

        Intent intent = new Intent(this, RepositoryDetailsActivity.class);

        intent.putExtra(RepositoryListActivity.USER_NAME, repo.getOwner().getLogin());
        intent.putExtra(RepositoryDetailsActivity.REPOSITORY, repo.getName());
        startActivity(intent);
    }

}
