package com.example.asteroids.nasa.client.interceptors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpRequest;
import org.springframework.util.Assert;

import java.net.URI;

/**
 * A custom implementation of the {@link HttpRequest} interface that wraps an existing {@link HttpRequest}
 * and modifies its URI.
 */
public class CustomHttpRequest implements HttpRequest {

    private final HttpRequest request;
    private final URI uri;

    public CustomHttpRequest(HttpRequest request, URI uri) {
        Assert.notNull(request, "HttpRequest must not be null");
        Assert.notNull(uri, "URI must not be null");

        this.request = request;
        this.uri = uri;
    }

    @Override
    public HttpMethod getMethod() {
        return request.getMethod();
    }

    @Override
    public URI getURI() {
        return uri;
    }

    @Override
    public HttpHeaders getHeaders() {
        return request.getHeaders();
    }
}

