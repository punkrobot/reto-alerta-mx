package mx.bytecraft.apps.alertamx;

import android.app.Activity;
import android.app.ListActivity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;

import java.io.IOException;
import java.util.List;

import mx.bytecraft.apps.alertamx.backend.capEndpoint.CapEndpoint;
import mx.bytecraft.apps.alertamx.backend.capEndpoint.model.Alert;

public class MainActivity extends Activity {

    private ProgressBar progress;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        progress = (ProgressBar)findViewById(R.id.progress);
        listView = (ListView)findViewById(R.id.listview);

        new EndpointAsyncTask().execute(10);
    }


    class EndpointAsyncTask extends AsyncTask<Integer, Void, List<Alert>> {
        private CapEndpoint endpoint;

        public EndpointAsyncTask() {
            CapEndpoint.Builder builder = new CapEndpoint.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null);
            endpoint = builder.build();
        }

        @Override
        protected List<Alert> doInBackground(Integer... params) {
            int count = params[0];

            try {
                return endpoint.getAlerts(count).execute().getAlerts();
            } catch (IOException e) {
                return null;
            }
        }

        @Override
        protected void onPostExecute(List<Alert> result) {
            Adapter adapter = new Adapter(MainActivity.this, result);
            listView.setAdapter(adapter);

            progress.setVisibility(View.GONE);
            listView.setVisibility(View.VISIBLE);
        }
    }

}
