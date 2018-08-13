package applerjson.br.com.applerjson;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

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


        //metodo para exibir somente uma mensagem para o usuario indicando a leitura do arquivo .json
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Toast.makeText(getBaseContext(),"Lendo Arquivo Json", Toast.LENGTH_LONG).show();
        }

        @Override
        protected String[] doInBackground(String... strings) {
            return new String[0];
        }
    }
}
