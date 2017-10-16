package jesse.dev.jnoticias.views.noticias;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.Observable;

import io.apptik.widget.multiselectspinner.BaseMultiSelectSpinner;
import io.apptik.widget.multiselectspinner.MultiSelectSpinner;
import jesse.dev.jnoticias.R;
import jesse.dev.jnoticias.databinding.ActivityNoticiasBinding;
import jesse.dev.jnoticias.views.utils.BaseActivity;

public class NoticiasActivity extends BaseActivity {

    NoticiasViewModel viewModel;
    private ActivityNoticiasBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        initDataBinding();
        setupListView();
        setupObserver(viewModel);

        MultiSelectSpinner multiSelectSpinner = binding.spinner;

    }


    @Override
    public void update(Observable observable, Object o)
    {
        if (observable instanceof NoticiasViewModel)
        {
            binding.swipe.setRefreshing(false);
            NoticiasAdapter adapter = (NoticiasAdapter) binding.noticias.getAdapter();
            NoticiasViewModel viewModel = (NoticiasViewModel) observable;
            binding.noticias.getItemAnimator().endAnimations();
            adapter.setList(viewModel.getNoticiaList());
        }
    }


    @Override
    public void initDataBinding()
    {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_noticias);
        viewModel = new NoticiasViewModel(this,binding.spinner);
        binding.setViewModel(viewModel);
    }

    private void setupListView()
    {
        NoticiasAdapter adapter = new NoticiasAdapter();
        adapter.setHasStableIds(true);

        binding.noticias.setLayoutManager(new LinearLayoutManager(this));
        binding.noticias.setAdapter(adapter);
        binding.swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                viewModel.refresh();
            }
        });
    }

}
