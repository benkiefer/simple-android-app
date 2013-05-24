package org.burgers.github_android;

import android.os.AsyncTask;
import org.eclipse.egit.github.core.Repository;
import org.eclipse.egit.github.core.service.RepositoryService;

import java.io.IOException;

public class GetUserRepositoryTask extends AsyncTask<String, Void, Repository> {
    @Override
    protected Repository doInBackground(String... strings) {
        try {
            return new RepositoryService().getRepository(strings[0], strings[1]);
        } catch (IOException e) {
            // todo: error handling
            e.printStackTrace();
            return null;
        }
    }
}
