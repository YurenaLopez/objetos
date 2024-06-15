package org.vaadin.example;

import com.nimbusds.jose.shaded.gson.Gson;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.data.renderer.NativeButtonRenderer;
import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.springframework.security.core.parameters.P;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.List;


@Route
public class MainView extends VerticalLayout {

    private Grid<Productos> grid = new Grid<>(Productos.class);

    public MainView(@Autowired ProductosService service) {
        grid.setColumns("nombre", "categoria", "precio", "EAN13");
        // Configurar columnas del Grid
        grid.addColumn(Productos::getNombre).setHeader("Nombre").setAutoWidth(true);
        grid.addColumn(Productos::getCategoria).setHeader("Categoria").setAutoWidth(true);
        grid.addColumn(Productos::getPrecio).setHeader("Precio").setAutoWidth(true);
        grid.addColumn(Productos::getEAN13).setHeader("Ean13").setAutoWidth(true);
        // Configurar botón Generar PDF
        //PDFManager pdfManager = new PDFManager();
        //grid.addColumn(new NativeButtonRenderer<>("Añadir", productos -> {
          //  pdfManager.GenerarPDF(productos.getName(), productos);
        //}));

        // Añadir el Grid al layout principal
        add(grid);
        loadProductos();
    }

    private void loadProductos() {
        // Obtener datos del servicio o controlador
        List<Productos> productos = getProductosFromController();
        grid.setItems(productos);
    }

    private List<Productos> getProductosFromController() {
        String url = "http://localhost:8080/productos";

        try {
            // Configurar cliente HTTP
            HttpClient httpClient = HttpClient.newHttpClient();
            HttpRequest httpRequest = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .build();

            // Hacer la llamada GET
            HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                // Convertir la respuesta JSON a una lista de Characters
                Productos[] productossArray = new Gson().fromJson(response.body(), Productos[].class);
                return Arrays.asList(productossArray);
            } else {
                // En caso de error mostrar mensaje
                System.out.println("Error al obtener datos: " + response.statusCode());
            }
        } catch (Exception e) {
            // Manejar excepción
            e.printStackTrace();
        }

        // En caso de error
        return List.of();
    }
}

