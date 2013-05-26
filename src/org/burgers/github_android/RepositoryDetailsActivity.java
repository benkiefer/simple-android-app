package org.burgers.github_android;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import org.eclipse.egit.github.core.Repository;

import java.util.concurrent.ExecutionException;

public class RepositoryDetailsActivity extends Activity {
    public static final String REPOSITORY = "repository_list_item";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.repository_detail);

        String username = getIntent().getStringExtra(RepositoryListActivity.USER_NAME);
        String repositoryName = getIntent().getStringExtra(RepositoryDetailsActivity.REPOSITORY);

        Repository repository = null;

        try {
            repository = new GetUserRepositoryTask().execute(username, repositoryName).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        if (repository != null){
            TextView name = (TextView) findViewById(R.id.repository_detail_name);
            name.setText(repository.getName());

            TextView description = (TextView) findViewById(R.id.repository_details_description);
            description.setText(repository.getDescription());

            TextView lastUpdated = (TextView) findViewById(R.id.repository_details_last_updated_date);

            TimeUpdated timeUpdated = new TimeUpdated(repository.getUpdatedAt());

            if (timeUpdated.isLongerThanMinute()){
                lastUpdated.setText(String.format("Last updated %d %s ago", timeUpdated.getLength(), timeUpdated.getUnit()));
            } else {
                lastUpdated.setText("Last updated a few moments ago");
            }

            setTitle(username);
        }
    }

}
