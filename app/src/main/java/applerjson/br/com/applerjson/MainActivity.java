package applerjson.br.com.applerjson;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

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


        //metodo para executar o processo de leitura do arquivo
        @Override
        protected String[] doInBackground(String... strings) {
            return new String[0];
        }
    }

    //metodo para a leitura do arquivo Json
    private String[] lerJson(String url) {
        InputStream is = null;
        String[] strClientes = null;

        try{
            //acesso a o webservice que retorna o JSON
            //url contem caminho do arquivo JSON
            is = NetworkUtils.OpenHttpConnection(url, this);


            //o InputStream é adicionado em um buffer
            BufferedReader streamReader = new BufferedReader(new InputStreamReader(is,"UTF-8"));
            StringBuilder jsonStrBuilder = new StringBuilder();

            String inputStr;
        }



    }
}
