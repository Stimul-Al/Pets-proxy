package by.leverx.pets_proxy.connection.impl;

import by.leverx.pets_proxy.connection.DestinationConnection;
import com.sap.cloud.sdk.cloudplatform.connectivity.HttpDestination;
import lombok.RequiredArgsConstructor;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpRequestBase;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import static com.sap.cloud.sdk.cloudplatform.connectivity.DestinationAccessor.getDestination;
import static com.sap.cloud.sdk.cloudplatform.connectivity.HttpClientAccessor.getHttpClient;

@Component
@RequiredArgsConstructor
public class DestinationConnectionImpl implements DestinationConnection {

    @Value("${sap.destination.name}")
    private String destinationName;

    @Override public HttpResponse destination(HttpRequestBase httpRequest, String request) {
        HttpDestination destination = getDestination(destinationName).asHttp();
        HttpClient client = getHttpClient(destination);

        String strUri = destination.getUri() + request;
        URI uri = null;
        try {
            uri = new URI(strUri);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        httpRequest.setURI(uri);

        try {
            return client.execute(httpRequest);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
