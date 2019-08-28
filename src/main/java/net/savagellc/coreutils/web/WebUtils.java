package net.savagellc.coreutils.web;

import net.savagellc.coreutils.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class WebUtils {
    /**
     * Converts two strings to a URL form valid string with a default utf 8 encoding
     * @param key the key string
     * @param value the value string
     * @return the resulting form url encoded string
     * @throws UnsupportedEncodingException
     */
    public static String encodeParam(String key,String value) throws UnsupportedEncodingException {
       return encodeParam(key, value, "UTF-8");
    }
    /**
     * Converts two strings to a URL form valid string
     * @param key the key string
     * @param value the value string
     * @param encoding the encoding for the URL String
     * @return the resulting form url encoded string
     * @throws UnsupportedEncodingException
     */
    public static String encodeParam(String key,String value, String encoding) throws UnsupportedEncodingException {
        return( URLEncoder.encode(key, encoding) + "=" + URLEncoder.encode(value, encoding));
    }

    /**
     * Returns a connection to a certain url
     * @param url the url string
     * @param method the HTTP request method
     * @return the resulting connection
     * @throws IOException
     */
    public static HttpURLConnection openWebConnection(String url, String method) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
        connection.setRequestMethod(method);
        return connection;
    }
    /**
     * Returns a connection to a certain url with a GET http request method
     * @param url the url string
     * @return the resulting connection
     * @throws IOException
     */
    public static HttpURLConnection openWebConnection(String url) throws IOException {
        return openWebConnection(url, "GET");
    }

    /**
     * Enables a connection to send and receive through streams
     * @param connection the connection
     */
    public static void enableTrafficForConnection(HttpURLConnection connection) {
        connection.setDoInput(true);
        connection.setDoInput(true);
    }

    /**
     * Download a file through HTTP GET and save the result into a file
     * @param url the url to get the file from
     * @param targetFile the output file to write to
     * @param append if it should only append to the file
     * @throws IOException
     */
    public static void downloadFile(String url, File targetFile, boolean append) throws IOException {
        HttpURLConnection connection = openWebConnection(url);
        enableTrafficForConnection(connection);
        FileUtils.pipeToFile(targetFile, connection.getInputStream(), append);
    }
    /**
     * Download a file through HTTP GET and save the result into a file, file will always be overwritten
     * @param url the url to get the file from
     * @param targetFile the output file to write to
     * @throws IOException
     */
    public static void downloadFile(String url, File targetFile) throws IOException {
        downloadFile(url, targetFile, false);
    }
}
