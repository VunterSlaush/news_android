package jesse.dev.jnoticias.retrofit;

import java.util.List;

import jesse.dev.jnoticias.models.Noticia;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Slaush on 15/10/2017.
 */

public interface CategoriesService {
    @GET("/category/list")
    Call<List<String>> getCategories();
}
