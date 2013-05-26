package org.burgers.github_android;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import org.eclipse.egit.github.core.Repository;
import org.eclipse.egit.github.core.service.RepositoryService;

import java.io.IOException;

public class GetUserRepositoryTask extends AsyncTask<String, Void, Repository> {
    private ProgressDialog progressDialog;
    private Context context;

    public GetUserRepositoryTask(Context context) {
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        progressDialog = ProgressDialog.show(context, "", "Retrieving repository details...");
    }

    @Override
    protected void onPostExecute(Repository repositories) {
        progressDialog.dismiss();
    }

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
