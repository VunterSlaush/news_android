package jesse.dev.jnoticias.views.noticia;

import android.content.Context;
import android.content.Intent;
import android.databinding.ObservableField;
import android.view.View;

import java.util.Observable;

/**
 * Created by Slaush on 15/10/2017.
 */

public class NoticiaViewModel extends Observable {

    public ObservableField<String> title;
    public ObservableField<String> autor;
    public ObservableField<String> cuerpo;
    public ObservableField<String> reportero;
    private Context context;

    public NoticiaViewModel(Context context) {
        title = new ObservableField<>();
        autor = new ObservableField<>();
        cuerpo = new ObservableField<>();
        reportero = new ObservableField<>();
        this.context = context;
    }

    public void setIntent(Intent intent) {
        title.set(intent.getStringExtra("titulo"));
        autor.set(intent.getStringExtra("autor"));
        cuerpo.set(intent.getStringExtra("cuerpo"));
        reportero.set(intent.getStringExtra("reportero"));
    }

    public void compartir(View view)
    {
        try {
            Intent i = new Intent(Intent.ACTION_SEND);

            i.putExtra(Intent.EXTRA_SUBJECT, title.get());
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            i.putExtra(Intent.EXTRA_TEXT, title.get()+" \n "+cuerpo.get());
            i.setType("text/plain");
            context.startActivity(Intent.createChooser(i, "Elige Uno"));
        } catch(Exception e) {
            //e.toString();
        }
    }
}
