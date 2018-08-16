package applerjson.br.com.applerjson;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

//classe para efetuar o download dos bytes de um arquivo

class NetworkUtils {

    //metodo importante para verificar se o dispositivo pode se conectar a internet antes de efetuar o download dos dados
    public static InputStream OpenHttpConnection (String urlString, Context context) throws IOException {

        InputStream is = null;
        int resposta = -1;

        //menitora o estado de conectividade do dispositivo
        //obtem informacoes de rede atual
        ConnectivityManager cm = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);


        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();

        //verifica se o usuario esta conectado ou se pode conectar a rede
        boolean isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();


        if(isConnected){
            //conversao da url para tipo url
            URL url = new URL(urlString);
            //aberta uma conexao para tipo url
            URLConnection conn = url.openConnection();
            if(!(conn instanceof HttpURLConnection))
                throw new IOException("Não é uma conexão HTTP");
            try {
                HttpURLConnection httpConn = (HttpURLConnection) conn;
                //se a conexão estiver utilizando o protocola HTTP será retornado um objeto HttpURLConnection deve ser informado se a conexão aceita redirecionamento
                httpConn.setInstanceFollowRedirects(true);
                //metodo utilizado
                httpConn.setRequestMethod("GET");
                //objeto se conecta a url
                httpConn.connect();
                //recebe o codigo retornado
                resposta = httpConn.getResponseCode();
                //verifica se ocorreu algum erro de conexao
                if(resposta == HttpURLConnection.HTTP_OK){
                    is = httpConn.getInputStream();
                }
            }catch (Exception ex){
                Log.i("HttpConnection", ex.getLocalizedMessage());
                throw new IOException("Erro ao se conectar");
            }

        }
        //retorna que a conexao esta pronta para o conversao
        //do arquivo json para byte a byte
        return is;
    }



}
