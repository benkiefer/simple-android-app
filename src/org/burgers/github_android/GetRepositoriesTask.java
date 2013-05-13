package org.burgers.github_android;

import android.os.AsyncTask;
import org.eclipse.egit.github.core.Repository;
import org.eclipse.egit.github.core.service.RepositoryService;

import java.io.IOException;

public class GetRepositoriesTask extends AsyncTask<String, Void, String> {
    @Override
    protected String doInBackground(String... strings) {
        RepositoryService service = new RepositoryService();
        try {
            for (Repository repository : service.getRepositories(strings[0])) {
                System.out.println(repository.getName());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "boom";
    }
}
