package com.siemens.bt.jazz.services.base.rest.parameters;

import com.ibm.team.jfs.app.http.util.HttpConstants.HttpMethod;

import java.util.*;

/**
 * Class for condensing the most frequently used request information sent to the service.
 * <p>
 * Just a class that's easier to handle and cheap to build compared to HttServletRequest. Adds the path navigation
 * facility required for efficient routing.
 * </p>
 * <p>
 * All functions that handle parameters could be extracted to a separate RequestParameters class.
 * </p>
 */
public final class RestRequest implements Iterable<String> {
    private final Deque<String> uriQueue;
    private final String originalUri;
    private final Map<String, String[]> parameters;
    private final HttpMethod method;

    /**
     * Constructs a RestRequest from a method and a uri.
     *
     * @param method The HTTP method with which the request was made
     * @param uri    Destination URI of the request
     */
    public RestRequest(HttpMethod method, String uri) {
        this(method, uri, null);
    }

    /**
     * Constructs a RestRequest including optional parameters.
     *
     * @param method The HTTP method with which the request was made
     * @param uri    Destination URI of the request
     * @param map    Parameters passed to the service in the form of ?paramName=paramValue
     */
    public RestRequest(HttpMethod method, String uri, Map<String, String[]> map) {
        this.method = method;
        this.originalUri = uri;
        this.parameters = map;

        String[] pathFragments = uri.split("/");
        this.uriQueue = new ArrayDeque<>(Arrays.asList(pathFragments));
    }

    /**
     * Check if a given parameter was passed to the request.
     *
     * @param key Parameter name to check for
     * @return True if a parameter corresponding to key was found, false otherwise
     */
    public boolean hasParameter(String key) {
        return this.parameters.containsKey(key);
    }

    /**
     * Returns the parameter value of a given key.
     *
     * @param key Key to look for
     * @return Parameter value corresponding to key, null if it doesn't exist
     */
    public String getParameterValue(String key) {
        // TODO: Oh jeez... This is just pure java-uglyness.
        if (this.parameters.size() > 0 && this.hasParameter(key)) {
            return this.parameters.get(key)[0];
        }

        return null;
    }

    /**
     * Checks if there is a next path fragment of the current URL.
     * <p>A path fragment is a part of a slash separated URL: {@code /these/are/fragments}
     * Required for navigating the path efficiently.</p>
     *
     * @return True if there are more fragments, false otherwise
     */
    public boolean hasNextpath() {
        return !this.uriQueue.isEmpty();
    }

    /**
     * Next path fragment of the URL, starting at the front of the URL; removes the fragment.
     * <p>A path fragment is a part of a slash separated URL: {@code /these/are/fragments}
     * Required for navigating the path efficiently.</p>
     *
     * @return The next path fragment
     */
    public String nextPath() {
        return this.uriQueue.pop();
    }

    /**
     * Next path fragment, without removing it from the path.
     * <p>This function is a candidate for removal in a future release.</p>
     *
     * @return The next path fragment
     */
    public String peekNextPath() {
        return this.uriQueue.peek();
    }

    /**
     * Returns the HTTP method the request was made with.
     *
     * @return the method
     */
    public HttpMethod getMethod() {
        return this.method;
    }

    /**
     * String representation of the original request, returns the URL in standard format.
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return this.originalUri;
    }

    /**
     * Iterator for looping over the rest-path.
     * {@inheritDoc}
     */
    public Iterator<String> iterator() {
        return this.uriQueue.iterator();
    }

}
