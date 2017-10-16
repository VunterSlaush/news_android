package jesse.dev.jnoticias.views.noticias;

import android.util.Base64;
import android.view.View;

import com.bumptech.glide.Glide;

import jesse.dev.jnoticias.R;
import jesse.dev.jnoticias.databinding.NoticiaItemBinding;
import jesse.dev.jnoticias.models.Noticia;
import jesse.dev.jnoticias.views.utils.BindeableHolder;

/**
 * Created by Slaush on 08/10/2017.
 */

public class NoticiaViewHolder extends BindeableHolder<Noticia> {

    NoticiaItemBinding binding;
    public NoticiaViewHolder(NoticiaItemBinding binding) {
        super(binding.itemNoticia);
        this.binding = binding;
    }

    @Override
    public void onBind() {
        if(binding.getViewModel() == null)
            binding.setViewModel(new NoticiaItemViewModel(item, itemView.getContext()));
        else
            binding.getViewModel().setNoticia(item);

        byte[] imageByteArray = Base64.decode(item.getCover(), Base64.DEFAULT);
        Glide.with(itemView.getContext())
                .load(imageByteArray)
                .asBitmap()
                .placeholder(R.drawable.ic_broken)
                .into(binding.image);
    }
}
