package eetac.dsa.Servidor;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.http.server.StaticHttpHandler;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import java.io.IOException;
import java.net.URI;


public class Servidor {
     //public static final String BASE_URI = "http://192.168.1.36:8080/myapp/";
    public static final String BASE_URI = "http://localhost:8080/myapp/";
    //public static final String BASE_URI = "http://172.16.1.133:8080/myapp/";
    //public static final String BASE_URI = "http://147.83.7.205:8080/myapp/";

    /**
     * Starts Grizzly HTTP server exposing JAX-RS resources defined in this application.
     * @return Grizzly HTTP server.
     */
    public static HttpServer startServer()
    {
        // create a resource config that scans for JAX-RS resources and providers
        // in edu.upc.dsa package
        final ResourceConfig rc = new ResourceConfig().packages("eetac.dsa");

        // create and start a new instance of grizzly http server
        // exposing the Jersey application at BASE_URI
        return GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), rc);
    }

    /**
     * Main method.
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException
    {
        final HttpServer server = startServer();

        StaticHttpHandler staticHttpHandler = new StaticHttpHandler("./public/");
        server.getServerConfiguration().addHttpHandler(staticHttpHandler, "/");

        System.out.println(String.format("La aplicación de Jersey se inició con WADL en %sapplication.wadl\n" +
                "Presionar enter para detenerlo...", BASE_URI));

        System.in.read();
        server.stop();
    }

}
