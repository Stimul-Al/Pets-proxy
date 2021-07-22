package by.leverx.pets_proxy.connection;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpRequestBase;

public interface DestinationConnection {

    HttpResponse destination(HttpRequestBase httpRequest, String request);
}
