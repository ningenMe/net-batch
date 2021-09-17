package ningenme.net.batch.infrastructure.qiita.mapper;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import ningenme.net.batch.infrastructure.qiita.dto.BlogQiitaDto;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;
import java.util.List;

@Component
public class BlogQiitaMapper {
    private final static String URL = "https://qiita.com/api/v2/items?page=1&per_page=20&query=user:ningenMe";

    public List<BlogQiitaDto> get() {
        Request request = new Request.Builder()
            .url(URL)
            .build();
        OkHttpClient okHttpClient = new OkHttpClient();
        try (Response response = okHttpClient.newCall(request).execute()) {
            String json = response.body().string();
            Gson gson = new Gson();
            Type type = new TypeToken<List<BlogQiitaDto>>() {
            }.getType();
            return gson.fromJson(json, type);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

}
