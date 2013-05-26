package org.burgers.github_android;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import org.eclipse.egit.github.core.Repository;
import org.eclipse.egit.github.core.service.RepositoryService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GetRepositoriesTask extends AsyncTask<String, Void, List<Repository>> {
    public GetRepositoriesTask(Context context) {
        this.context = context;
    }

    private Context context;
    private ProgressDialog progressDialog;

    @Override
    protected void onPreExecute() {
        progressDialog = ProgressDialog.show(context, "", "Retrieving repositories...");
    }

    @Override
    protected void onPostExecute(List<Repository> repositories) {
        progressDialog.dismiss();
    }

    @Override
    protected List<Repository> doInBackground(String... strings) {
        try {
            return new RepositoryService().getRepositories(strings[0]);
        } catch (IOException e) {
            return new ArrayList<Repository>();
        }
    }
}
