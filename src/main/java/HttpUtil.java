import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpUtil {

    public static MyResponse sendRequest(String url) {
        MyResponse myResponse = new MyResponse();

        HttpURLConnection urlConnection = null;
        try {
            URL requestUrl = new URL(url);
            urlConnection = (HttpURLConnection) requestUrl.openConnection();
            urlConnection.setReadTimeout(20000);
            urlConnection.setConnectTimeout(20000);
            urlConnection.setRequestMethod("GET"); // optional, GET already by default
            int status = urlConnection.getResponseCode();
            myResponse.responseCode = status;
            if (status == HttpURLConnection.HTTP_OK) {
                myResponse.body = getStringFromStream(urlConnection.getInputStream());
            }
        } catch (Exception e) {
            myResponse.exception = e;
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }
        return myResponse;
    }

    private static String getStringFromStream(InputStream inputStream) throws IOException {
        final int BUFFER_SIZE = 4096;
        ByteArrayOutputStream resultStream = new ByteArrayOutputStream(BUFFER_SIZE);
        byte[] buffer = new byte[BUFFER_SIZE];
        int length;
        while ((length = inputStream.read(buffer)) != -1) {
            resultStream.write(buffer, 0, length);
        }
        return resultStream.toString("UTF-8");
    }

}