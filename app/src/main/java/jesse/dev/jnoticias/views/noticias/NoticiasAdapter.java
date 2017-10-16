package jesse.dev.jnoticias.views.noticias;

import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.ViewGroup;


import jesse.dev.jnoticias.R;
import jesse.dev.jnoticias.databinding.NoticiaItemBinding;
import jesse.dev.jnoticias.models.Noticia;
import jesse.dev.jnoticias.views.utils.BindeableAdapter;
import jesse.dev.jnoticias.views.utils.BindeableHolder;

/**
 * Created by Slaush on 08/10/2017.
 */

public class NoticiasAdapter extends BindeableAdapter<Noticia> {
    @Override
    public BindeableHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {

        NoticiaItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.noticia_item,
                parent, false);
        return new NoticiaViewHolder(binding);
    }

}
