package jesse.dev.jnoticias.views.utils;

import android.support.v7.app.AppCompatActivity;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by Slaush on 08/10/2017.
 */

public abstract class BaseActivity extends AppCompatActivity implements Observer
{

    public abstract  void update(Observable observable, Object o);
    public abstract void initDataBinding();
    public void setupObserver(Observable observable)
    {
        observable.addObserver(this);
    }
}
