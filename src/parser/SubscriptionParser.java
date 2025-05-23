package parser;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import feed.Feed;
import subscription.SingleSubscription;
import subscription.Subscription;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/*
 * Esta clase implementa el parser del  archivo de suscripcion (json)
 * Leer https://www.w3docs.com/snippets/java/how-to-parse-json-in-java.html
 * */

public class SubscriptionParser extends GeneralParser {

    public static Subscription parse(String filePath) {
        Subscription subscriptions = new Subscription(null); // Crea un obj Subscription vacio.

        try (FileReader reader = new FileReader(filePath)) { //Try para abrir de forma segura. reader es utilizado para leer el archivo JSON.
            JSONTokener tokener = new JSONTokener(reader); //Analiza el contenido del archivo.
            JSONArray jsonArray = new JSONArray(tokener); //Representa todo el arreglo JSON (serian las 3 entradas).

            for (int i = 0; i < jsonArray.length(); i++) { //Para iterar sobre cada objeto del array. Cada obj representa una entrada o sea una subscripcion.
                JSONObject obj = jsonArray.getJSONObject(i);

                String url = obj.getString("url"); //Obtenemos la url.
                JSONArray paramsArray = obj.getJSONArray("urlParams"); //Lista urlParams.
                List<String> urlParams = new ArrayList<>(); // Se convierte la lista una List<String> de Java.
                
                for (int j = 0; j < paramsArray.length(); j++) { //Se recorre cada parametro del array y lo agregamos a la lista urlParams.
                    urlParams.add(paramsArray.getString(j));
                }

                String urlType = obj.getString("urlType"); // Obtencion del campo urlType, varia en rss o reddit.

                SingleSubscription sub = new SingleSubscription(url, urlParams, urlType); //Creamos una instancia de SingleSubscription con esos valores
                for (String p : urlParams) {
                    sub.setUrlParams(p);
                }

                subscriptions.addSingleSubscription(sub); //Se agrega la subscripcion individual a la lista de todaslas suscripciones.
            }

        } catch (Exception e) {
            System.err.println("Error leyendo archivo JSON: " + e.getMessage());
        }

        return subscriptions; 
        //Se devuelve el objeto subscription con todas las suscripciones cargadas.
    }

    @Override
    public Feed parse(String xml, String siteName) {
        throw new UnsupportedOperationException("SubscriptionParser no implementado.");
    }
    
}
