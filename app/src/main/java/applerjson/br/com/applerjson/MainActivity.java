package applerjson.br.com.applerjson;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
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

        @Override
        protected void onPostExecute(String[] strings) {
            super.onPostExecute(strings);
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

            //utilizando para ler o conteudo retornado
            while ((inputStr = streamReader.readLine()) != null)jsonStrBuilder.append(inputStr);


            //o arquivo é transformado em um objeto JSONObject
            JSONObject jobj = new JSONObject(jsonStrBuilder.toString());

            JSONArray jArray = jobj.getJSONArray("items");
            strClientes = new String[jArray.length()];

            //transforma o objeto JSON em um ArrayString
            for(int i = 0; i < jArray.length(); i++) {
                // o valor da chave nome e atribuido desse objeto para o array
                JSONObject jObject = jArray.getJSONObject(i);
                strClientes[i] = jObject.getString("nome");
            }
        } catch (IOException ie) {
            Log.i("readJson", ie.getLocalizedMessage());
        } catch (JSONException e) {
            Log.i("readJson", e.getLocalizedMessage());
        }
        return strClientes;
    }


}
