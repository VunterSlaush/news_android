package jesse.dev.jnoticias.views.noticia;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.view.View;

import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.glide.slider.library.Animations.DescriptionAnimation;
import com.glide.slider.library.SliderLayout;
import com.glide.slider.library.SliderTypes.TextSliderView;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Observable;

import jesse.dev.jnoticias.R;
import jesse.dev.jnoticias.databinding.ActivityDetalleNoticiaBinding;
import jesse.dev.jnoticias.views.utils.BaseActivity;

public class DetalleNoticiaActivity extends BaseActivity {

    NoticiaViewModel viewModel;
    ActivityDetalleNoticiaBinding binding;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDataBinding();
        viewModel.setIntent(getIntent());

        ArrayList<String> images = getIntent().getStringArrayListExtra("images");
        String cover = getIntent().getStringExtra("cover");
        SliderLayout mDemoSlider = (SliderLayout) findViewById(R.id.slider);


        try {
            TextSliderView first = new TextSliderView(this);
            first.image(saveImage(this,cover));
            mDemoSlider.addSlider(first);
        } catch (IOException e) {
            e.printStackTrace();
        }


        for (int i = 0; i < images.size(); i++) {
            TextSliderView textSliderView = new TextSliderView(this);
            try {
                File f = saveImage(this,images.get(i));
                textSliderView.image(f);
                mDemoSlider.addSlider(textSliderView);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        mDemoSlider.setPresetTransformer(SliderLayout.Transformer.Default);
        mDemoSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        mDemoSlider.setCustomAnimation(new DescriptionAnimation());
        mDemoSlider.setDuration(4000);

    }

    @Override
    public void update(Observable observable, Object o) {

    }

    @Override
    public void initDataBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detalle_noticia);
        viewModel = new NoticiaViewModel(this);
        binding.setViewModel(viewModel);
    }

    public static File saveImage(final Context context, final String imageData) throws IOException {
        final byte[] imgBytesData = android.util.Base64.decode(imageData,
                android.util.Base64.DEFAULT);

        final File file = File.createTempFile("image", null, context.getCacheDir());
        final FileOutputStream fileOutputStream;
        try {
            fileOutputStream = new FileOutputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }

        final BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(
                fileOutputStream);
        try {
            bufferedOutputStream.write(imgBytesData);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                bufferedOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return file;
    }
}
