package applerjson.br.com.applerjson;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageButton btnLer = (ImageButton) findViewById(R.id.btnLer);

        btnLer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //instanciar a classe para leitura do arquivo JSON
                //declarando no caminho do url
                new LerJsonAsyncTask().execute("");
            }
        });



    }

    private class LerJsonAsyncTask extends AsyncTask <String, Void, String[]> {

        @Override
        protected String[] doInBackground(String... strings) {
            return new String[0];
        }
    }
}
