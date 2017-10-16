package jesse.dev.jnoticias.views.noticias;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;


import java.util.ArrayList;
import java.util.List;

import io.apptik.widget.multiselectspinner.MultiSelectSpinner;
import jesse.dev.jnoticias.R;


/**
 * Created by Slaush on 23/06/2017.
 */

public class CategoriesDialog
{
    private Context context;
    private AlertDialog dialog;
    List<String> selected, options;
    private OnGet onGet;
    public CategoriesDialog(Context contxt,
                                  List<String> opciones,
                                  List<String> selected,
                                  OnGet onGet)
    {
        this.context = contxt;
        this.onGet = onGet;
        this.options = opciones;
        this.selected = selected;
    }


    public void build()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Selecciona las Categorias");

        View viewInflated = LayoutInflater.from(context).inflate(R.layout.categories_dialog, null);
        final ArrayList<String> options = new ArrayList<>();





        builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                selected.clear();
                for (int i = 0; i< options.size(); i++)
                {

                }
            }
        });
        builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }


    public interface OnGet
    {
        void get(List<String> users);
    }

}
