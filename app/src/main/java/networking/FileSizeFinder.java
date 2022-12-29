package networking;

import java.io.IOException;
import java.net.URLConnection;

public class FileSizeFinder {
    private String url;

    public FileSizeFinder(String url) {
        this.url = url;
    }

    public double getFileSize() throws IOException, IllegalStateException {
        Connection connection = new Connection(this.url,true);
        URLConnection urlConnection = connection.getConnection();
        Connection.printHeaders(urlConnection);
        return 0.0;
    }

    public static void main(String[] args){
        try {
            new FileSizeFinder("https://www.google.de").getFileSize();
        }catch (Exception exp){
            System.err.println(exp.getMessage());
            exp.printStackTrace();
        }
    }
}
