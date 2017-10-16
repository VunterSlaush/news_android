package jesse.dev.jnoticias.views.utils;

/**
 * Created by Slaush on 08/10/2017.
 */

import android.support.v7.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;



/**
 * Created by Slaush on 18/06/2017.
 */

public abstract class BindeableAdapter<T> extends RecyclerView.Adapter<BindeableHolder> {

    protected List<T> list;

    public BindeableAdapter()
    {
        list = new ArrayList<>();
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public void onBindViewHolder(BindeableHolder holder, int position)
    {
        holder.bind(list.get(position));
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public void setList(List<T> items)
    {
        list.clear();
        list.addAll(items);
        notifyDataSetChanged();
    }


}
