package org.burgers.github_android;

import android.os.AsyncTask;
import org.eclipse.egit.github.core.Repository;
import org.eclipse.egit.github.core.service.RepositoryService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GetRepositoriesTask extends AsyncTask<String, Void, List<Repository>> {
    @Override
    protected List<Repository> doInBackground(String... strings) {
        try {
            return new RepositoryService().getRepositories(strings[0]);
        } catch (IOException e) {
            return new ArrayList<Repository>();
        }
    }
}
