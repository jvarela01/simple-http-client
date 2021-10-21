package com.redhat.nola.gps;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpClient.Redirect;
import java.net.http.HttpClient.Version;
import java.net.http.HttpResponse.BodyHandlers;
import java.time.Duration;
import java.net.http.HttpClient;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/test-url")
public class SimpleHttpClient {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String printResponse(){
        String retorno="";
        String url=System.getenv("REMOTE_URL");
        try{
            HttpRequest request=HttpRequest.newBuilder().uri(new URI(url)).GET().build();
            HttpClient client=HttpClient.newBuilder()
            .version(Version.HTTP_1_1)
            .followRedirects(Redirect.NORMAL)
            .connectTimeout(Duration.ofSeconds(20))
            .build();
            HttpResponse<String> response=client.send(request, BodyHandlers.ofString());
            retorno="Response Status: "+response.statusCode();
        }
        catch(URISyntaxException e){ }
        catch(IOException e){ }
        catch(InterruptedException e){ }
        return retorno;
    }
}