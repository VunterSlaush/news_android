package jesse.dev.jnoticias.retrofit;

import java.util.List;

import jesse.dev.jnoticias.models.Noticia;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.GET;

/**
 * Created by Slaush on 15/10/2017.
 */

public interface NewsServices {
    @GET("/news/list")
    Call<List<Noticia>> getNews();

    @GET("/news/listByCategory")
    Call<List<Noticia>> getCategoryNews(@Field("nombre") String categoria);
}
