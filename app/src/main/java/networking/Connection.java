package networking;


import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class Connection {
    private final String url;
    private URLConnection connection;

    public Connection(String url) {
        this.url = url;
        this.connection = null;
    }

    /**
     * constructor that provides the option to set up the connection
     * @param url url of the resource
     * @param init flag that specifies wether to set up the connection
     * @throws IOException if there is an error during setting up connection
     * @see Connection#setUpConnection()
     */
    public Connection(String url, boolean init) throws IOException {
        this(url);
        if(init)
            this.connection = setUpConnection();
    }

    public String getUrl() {
        return url;
    }

    public URLConnection getConnection() throws IllegalStateException{
        if (connection == null){
            throw new IllegalStateException("Connection has not been set up yet");
        }
        return connection;
    }

    /**
     * returns an established connection to the given url
     * @return URLConnection
     * @throws MalformedURLException  if the URL is invalid (e.g. no valid protocol is specified)
     * @throws IOException if there is an IO exception while establishing the connection
     */
    public URLConnection setUpConnection() throws IOException {
        URL url = new URL(this.url);
        URLConnection connection = url.openConnection(); // will return a connection for the protocol: e.g. HTTP, HTTPS, JAR
        connection.setConnectTimeout(5000);
        connection.setUseCaches(false);
        connection.connect();
        return connection;
    }

    /**
     * prints the headers returned by the server
     * primarily used for debugging purposes
     * @param urlConnection the connection
     */
    public static void printHeaders(final URLConnection urlConnection){
        System.out.println("======Header:======");
        var headers  = urlConnection.getHeaderFields();
        for(var h : headers.entrySet()){
            System.out.println("-" + h.getKey());
            for(String s : h.getValue()){
                System.out.println("  > " + s);
            }
        }
    }
}
