package jesse.dev.jnoticias.views.noticias;

import android.content.Context;
import android.content.Intent;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.view.View;

import java.util.ArrayList;

import jesse.dev.jnoticias.models.Noticia;
import jesse.dev.jnoticias.views.noticia.DetalleNoticiaActivity;

/**
 * Created by Slaush on 08/10/2017.
 */

public class NoticiaItemViewModel {

    public ObservableField<String> title;
    public ObservableField<String> categoria;
    private Context context;
    private Noticia noticia;

    public NoticiaItemViewModel(Noticia item, Context context) {
        this.context = context;
        this.title = new ObservableField<>();
        this.categoria = new ObservableField<>();
        this.setNoticia(item);

    }

    public void setNoticia(Noticia item) {
        this.noticia = item;
        title.set(noticia.getTitulo());
        categoria.set(noticia.getCategoria());
    }

    public void abrir(View view)
    {
        Intent i = new Intent(context, DetalleNoticiaActivity.class);
        i.putExtra("titulo",noticia.getTitulo());
        i.putExtra("cover",noticia.getCover());
        i.putExtra("cuerpo",noticia.getCuerpo());
        i.putStringArrayListExtra("images",new ArrayList<String>(noticia.getVideoOrImage()));
        i.putExtra("createdAt",noticia.getCreatedAt());
        i.putExtra("reportero",noticia.getReportero());
        context.startActivity(i);
    }
}

