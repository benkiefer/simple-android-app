package org.burgers.github_android;

import android.app.Activity;
import android.os.Bundle;
import org.eclipse.egit.github.core.Repository;
import org.eclipse.egit.github.core.service.RepositoryService;

import java.io.IOException;

public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        RepositoryService service = new RepositoryService();
        try {
            for (Repository repository : service.getRepositories("benkiefer")) {
                System.out.println(repository.getName());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        setContentView(R.layout.main);
    }
}
