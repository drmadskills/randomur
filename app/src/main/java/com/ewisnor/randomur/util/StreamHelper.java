package com.ewisnor.randomur.util;

import org.apache.http.Header;
import org.apache.http.HttpResponse;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.zip.GZIPInputStream;

/**
 * Helper functions for handling streams.
 *
 * Created by evan on 2015-01-02.
 */
public class StreamHelper {

    /**
     * Get the content of an HttpResponse as an InputStream. Supports Gzipped data.
     * @param response HTTP Response
     * @return A stream of the HTTP response data. Null if the HTTP Response was null
     */
    public static InputStream getResponseBodyStream(HttpResponse response) throws IOException {
        if (response == null || response.getEntity() == null) {
            return null;
        }
        InputStream inStream = response.getEntity().getContent();
        Header contentEncoding = response.getFirstHeader("Content-Encoding");

        if (contentEncoding != null
                && contentEncoding.getValue().equalsIgnoreCase("gzip")) {
            inStream = new GZIPInputStream(inStream);
        }

        return inStream;
    }

    /**
     * Convert an InputStream to a String.
     * @param is InputStream
     * @return String
     */
    public static String convertStreamToString(InputStream is) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();

        String line;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                is.close();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }

}
