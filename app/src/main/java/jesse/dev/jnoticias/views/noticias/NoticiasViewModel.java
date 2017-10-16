package jesse.dev.jnoticias.views.noticias;

import android.content.Context;
import android.databinding.ObservableField;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import io.apptik.widget.multiselectspinner.BaseMultiSelectSpinner;
import io.apptik.widget.multiselectspinner.MultiSelectSpinner;
import jesse.dev.jnoticias.MyApplication;
import jesse.dev.jnoticias.models.Noticia;
import jesse.dev.jnoticias.retrofit.CategoriesService;
import jesse.dev.jnoticias.retrofit.NewsServices;
import jesse.dev.jnoticias.views.utils.Pnotify;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Slaush on 08/10/2017.
 */

public class NoticiasViewModel extends Observable{

    public ObservableField<String> busqueda;

    private Context context;
    private List<Noticia> noticias;
    private List<Noticia> buscadas;
    private List<String> categories;
    private List<String> selectedCategories;
    private MultiSelectSpinner spinner;
    public NoticiasViewModel(Context context, MultiSelectSpinner spinner)
    {
        this.context = context;
        buscadas = new ArrayList<>();
        busqueda = new ObservableField<>("");
        selectedCategories = new ArrayList<>();
        categories = new ArrayList<>();
        this.spinner = spinner;
        fetchNoticias();
        fetchCategories();

    }

    public List<Noticia> getNoticiaList()
    {
        return buscadas;
    }

    public void refresh()
    {
        fetchNoticias();
    }

    private void fetchNoticias()
    {
        NewsServices services = MyApplication.getInstance().getRetrofit().create(NewsServices.class);
        services.getNews().enqueue(new Callback<List<Noticia>>() {
            @Override
            public void onResponse(Call<List<Noticia>> call, Response<List<Noticia>> response)
            {
                noticias = response.body();
                changeNoticiasData(noticias);
            }

            @Override
            public void onFailure(Call<List<Noticia>> call, Throwable t) {
                Pnotify.makeText(context,"Error al Conseguir las noticias",Toast.LENGTH_SHORT,Pnotify.ERROR);
            }
        });
    }

    private void fetchCategories()
    {
        CategoriesService services = MyApplication.getInstance().getRetrofit().create(CategoriesService.class);
        services.getCategories().enqueue(new Callback<List<String>>() {
            @Override
            public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                categories.clear();
                categories.addAll(response.body());
                initCategoriesSelector();
                Log.i("JESS","Categories:"+categories.toString());
            }

            @Override
            public void onFailure(Call<List<String>> call, Throwable t) {
                Pnotify.makeText(context,"Error al Conseguir las categorias",Toast.LENGTH_SHORT,Pnotify.ERROR);
            }
        });
    }

    private void initCategoriesSelector()
    {
        ArrayAdapter<String> adapter = new ArrayAdapter <String>(context,
                android.R.layout.simple_list_item_multiple_choice,
                categories);

        spinner
                .setListAdapter(adapter)
                .setSelectAll(true)
                .setMinSelectedItems(1);
        spinner.setListener(new BaseMultiSelectSpinner.MultiSpinnerListener() {
            @Override
            public void onItemsSelected(boolean[] selected) {
                setSelected(selected);
            }
        });
    }

    private void changeNoticiasData(List<Noticia> noticias)
    {
        this.buscadas.clear();
        this.buscadas.addAll(noticias);
        setChanged();
        notifyObservers();
    }

    public void find(View view)
    {
        ArrayList<Noticia> finded = new ArrayList<>();
        for (Noticia n : noticias)
        {
            if (n.match(busqueda.get()))
                finded.add(n);
        }
        Log.i("JESS","Buscadas:"+buscadas.size()+" ALL:"+noticias.size());
        changeNoticiasData(finded);
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setSelected(boolean[] selected) {
        for (int i = 0; i< selected.length; i++)
        {
            if (selected[i])
                selectedCategories.add(categories.get(i));
        }
        ArrayList<Noticia> finded = new ArrayList<>();
        for (Noticia n : noticias)
        {
            if (selectedCategories.contains(n.getCategoria()))
                finded.add(n);
        }
        Log.i("JESS","Buscadas:"+buscadas.size()+" ALL:"+noticias.size());
        changeNoticiasData(finded);

    }
}
