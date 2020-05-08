import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import okhttp3.*;
import java.io.IOException;
import java.util.Scanner;

class Main {

    static OkHttpClient client = new OkHttpClient();
    static String URL = "https://api.privatbank.ua/p24api/exchange_rates?json&date=";
    static String DATE = "";

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите дату в формате DD.MM.YYYY");
        DATE = scanner.nextLine();
        String getResponse = getRequest(URL + DATE);
        Model modelOkHttp3 = deserialize(getResponse);
        System.out.println(modelOkHttp3.getUSDRate().toString());
        Model modelHttpUrlConnection = deserialize(withHttpUrlConnection(URL + DATE));
        System.out.println(modelHttpUrlConnection.getUSDRate().toString());
    }

    public static String withHttpUrlConnection(String urlDate) {
        MyResponse response = HttpUtil.sendRequest(urlDate);
        if (response.getException() == null) {
            System.out.println(response.getResponseCode());
            return response.getBody();
        } else {
            System.out.println("Request failed: " + response.getException());
        }
        return null;
    }

    static String getRequest(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response = client.newCall(request).execute();
        int code = client.newCall(request).execute().code();
        System.out.println(code);
        if (code != 200) {
            System.out.println("Error " + code);
            return null;
        }
        return response.body().string();
    }

    private static Model deserialize(String json) {
        Gson gson = new GsonBuilder().create();
        return gson.fromJson(json, Model.class);
    }

}