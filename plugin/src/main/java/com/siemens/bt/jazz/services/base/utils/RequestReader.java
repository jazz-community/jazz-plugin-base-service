package com.siemens.bt.jazz.services.base.utils;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


/**
 * Functions for consistently reading the HTTP body of all HttpServletRequests.
 *
 * <p>
 * This class is necessary because not every version / combination of jazz and java ee offer the same reproducible
 * behavior when reading from the request with the offered methods. These functions wrap the buffered and unbuffered
 * functionalities for easy use.
 * </p>
 */
public class RequestReader {

    /**
     * Reads the body of a request as a plain string.
     * <p>
     * This function is buffered using the built-in buffering. This is sufficient and scales well independently of
     * input. Check <a href="http://recursor.blogspot.ch/2006/09/bufferedreader-vs-inputstream.html">this blog post</a>
     * for more information. That should be more than sufficient for us. In addition, using the buffered functionality
     * already takes care of the charset that is used for the request (compare this function to readUnbuffered).
     * </p>
     *
     * @param request Request of which to read body
     * @return Content of request as string
     * @throws IOException If input stream is invalid
     */
    public static String readAsString(HttpServletRequest request) throws IOException {
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        request.getInputStream()));

        StringBuilder builder = new StringBuilder(request.getContentLength());

        for (String line = reader.readLine(); line != null; line = reader.readLine()) {
            builder.append(line);
        }

        return builder.toString();
    }

    /**
     * Reads the body of a request and parses it as JSON.
     *
     * <p>
     * The caller of this function is responsible for handling any parsing errors should invalid JSON be in a
     * request.
     * </p>
     *
     * @param request Request of which to parse body
     * @return Content of request parsed as JSON
     * @throws IOException If input stream is invalid
     */
    public static JsonObject readAsJson(HttpServletRequest request) throws IOException {
        String content = RequestReader.readAsString(request);
        return new Gson().fromJson(content, JsonObject.class);
    }

    /**
     * Reads the body of a request and parses it as a JSON array.
     *
     * <p>
     * The caller of this function is responsible for handling any parsing errors and making sure that this function
     * is called on a request actually containing a JSON-array.
     * </p>
     *
     * @param request Request of which to parse body
     * @return Content of request parsed as a JSON array
     * @throws IOException If input stream is invalid
     */
    public static JsonArray readAsArray(HttpServletRequest request) throws IOException {
        String content = RequestReader.readAsString(request);
        return new Gson().fromJson(content, JsonArray.class);
    }

    /**
     * This function is only for demonstration purposes, don't use it in production code.
     *
     * <p>
     * This serves the purpose of showing the underlying logic that the buffered version uses. This parses the
     * content byte-wise. It doesn't, however, accommodate large request bodies and would likely start to fail at
     * multiple large requests. To circumvent this, a fixed size buffer and a StringBuilder would have to be used,
     * parsing the String once the buffer is full. Too much work for our scenario.
     * </p>
     *
     * @param request Request of which to read body
     * @return Content of request as a string
     * @throws IOException If input stream is invalid
     */
    @SuppressWarnings("unused")
    private static String readUnbuffered(HttpServletRequest request) throws IOException {
        int byteLength = request.getContentLength();
        byte[] buffer = new byte[byteLength];
        ServletInputStream stream = request.getInputStream();

        for (int i = 0; i < byteLength; ++i) {
            int value = stream.read();

            if (value > 0) {
                // safe cast because InputStream only returns ints in the range of 0-255, if positive.
                buffer[i] = (byte) value;
            } else {
                // -1 means we can stop
                break;
            }
        }

        String encoding = request.getCharacterEncoding();

        return new String(buffer, encoding);
    }
}
