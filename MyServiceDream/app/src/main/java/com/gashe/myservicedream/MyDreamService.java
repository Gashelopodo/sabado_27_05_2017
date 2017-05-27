package com.gashe.myservicedream;

import android.content.SharedPreferences;
import android.service.dreams.DreamService;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class MyDreamService extends DreamService implements View.OnClickListener, Animation.AnimationListener {

    private Animation animation;

    public MyDreamService() {
    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();

        // setting del servicio
        setInteractive(true);
        setFullscreen(true);
        setContentView(R.layout.dream);

        SharedPreferences sp = getSharedPreferences("prefs", MODE_PRIVATE);
        String category = sp.getString("category", "animals");

        log("Categoria almacenada " + category);

        ImageView imageView = (ImageView) findViewById(R.id.imagen1);
        ImageView imageView2 = (ImageView) findViewById(R.id.imagen2);

        switch (category){
            case "animals":
                imageView.setImageResource(R.drawable.animal1);
                imageView2.setImageResource(R.drawable.animal2);
                break;
            case "cadizcf":
                imageView.setImageResource(R.drawable.cadiz1);
                imageView2.setImageResource(R.drawable.cadiz2);
                break;
            case "foods":
                imageView.setImageResource(R.drawable.comida1);
                imageView2.setImageResource(R.drawable.comida2);
                break;
        }

    }

    @Override
    public void onDreamingStarted() {

        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.layout_padre);
        //TODO cargar e iniciar animación
        animation = AnimationUtils.loadAnimation(this, R.anim.animation_dream);
        linearLayout.startAnimation(animation);
        animation.reset();
        animation.setAnimationListener(this);
        super.onDreamingStarted();
    }

    @Override
    public void onAnimationStart(Animation animation) {
        log("animacion empezada");
    }

    @Override
    public void onAnimationEnd(Animation animation) {
        log("animacion finalizada");
        ImageView imageView = (ImageView) findViewById(R.id.imagen1);
        ImageView imageView2 = (ImageView) findViewById(R.id.imagen2);

        imageView.setImageResource(R.mipmap.ic_launcher_round);
        imageView2.setImageResource(R.mipmap.ic_launcher);

        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.layout_padre);
        linearLayout.startAnimation(animation);
    }

    @Override
    public void onAnimationRepeat(Animation animation) {
        log("animacion repetida");
    }

    @Override
    public void onClick(View v) {

    }

    public void clickScreen(View view){
        log("Han tocado la pantalla");
        ImageView imageView = (ImageView) findViewById(R.id.imagen1);
        ImageView imageView2 = (ImageView) findViewById(R.id.imagen2);

        imageView.setImageResource(R.mipmap.ic_launcher_round);
        imageView2.setImageResource(R.mipmap.ic_launcher);

        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.layout_padre);
        linearLayout.startAnimation(animation);
    }



    public void log(String s){
        Log.d(getClass().getCanonicalName(), s);
    }
}
