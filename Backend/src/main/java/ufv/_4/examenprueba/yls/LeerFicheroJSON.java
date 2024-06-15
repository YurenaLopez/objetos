package ufv._4.examenprueba.yls;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import org.apache.commons.io.FileUtils;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class LeerFicheroJSON {

    // Método para leer un archivo JSON y convertirlo a una lista de objetos NationalDataFile
    public static ArrayList<Productos> leerFicheroProductos() throws IOException {
        // Obtiene el archivo JSON
        InputStream inputStream = LeerFicheroJSON.class.getClassLoader().getResourceAsStream("productos.json");

        // Copia el archivo JSON a un File
        File productos = new File("productos.json");
        FileUtils.copyInputStreamToFile(inputStream, productos);

        // Inicializa Gson
        Gson gson = new Gson();

        // Lee el JSON desde el archivo y lo convierte a un ArrayList de NationalDataFile
        JsonReader reader = new JsonReader(new FileReader(productos));
        Type ProductosListType = new TypeToken<ArrayList<Productos>>() {}.getType();
        ArrayList<Productos> items = gson.fromJson(reader, ProductosListType);

        return items;
    }
}
