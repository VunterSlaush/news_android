package jesse.dev.jnoticias.views.utils;

import android.support.v7.widget.RecyclerView;
import android.view.View;



public abstract class BindeableHolder<T> extends RecyclerView.ViewHolder
{
    public T item;
    public BindeableHolder(View itemView)
    {
        super(itemView);
    }

    public void bind(T t)
    {
        this.item = t;
        onBind();
    }

    public abstract void onBind();
}
