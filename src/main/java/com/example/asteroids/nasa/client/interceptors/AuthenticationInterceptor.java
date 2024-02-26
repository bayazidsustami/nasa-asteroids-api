package com.example.asteroids.nasa.client.interceptors;

import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.net.URI;

/**
 * Custom implementation of {@link ClientHttpRequestInterceptor} that intercepts outgoing requests
 * made by a RestTemplate and appends an API key as a query parameter to the request URI.
 */
public class AuthenticationInterceptor implements ClientHttpRequestInterceptor {

    private final String apiKey;

    /**
     * Constructs a new {@code AuthenticationInterceptor} with the specified API key.
     *
     * @param apiKey The API key to be appended to the request URI as a query parameter.
     */
    public AuthenticationInterceptor(String apiKey) {
        this.apiKey = apiKey;
    }

    /**
     * Intercepts the outgoing HTTP request, appends the API key as a query parameter to the request URI,
     * and executes the request.
     *
     * @param request   The HTTP request to be intercepted.
     * @param body      The body of the HTTP request.
     * @param execution The execution callback for the intercepted request.
     * @return The HTTP response returned by executing the request.
     * @throws IOException if an I/O error occurs while executing the request.
     */
    @Override
    public ClientHttpResponse intercept(
            HttpRequest request,
            byte[] body,
            ClientHttpRequestExecution execution
    ) throws IOException {
        URI uri = request.getURI();
        uri = appendApiKeyToUri(uri);
        request = new CustomHttpRequest(request, uri);
        return execution.execute(request, body);
    }

    /**
     * Appends the API key as a query parameter to the given {@link URI}.
     *
     * @param uri The original URI.
     * @return The modified {@link URI} with the API key appended as a query parameter.
     */
    private URI appendApiKeyToUri(URI uri) {
        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
        queryParams.add("api_key", apiKey);
        return UriComponentsBuilder.fromUri(uri)
                .queryParams(queryParams)
                .build()
                .toUri();
    }
}
