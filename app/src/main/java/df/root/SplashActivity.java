package df.root;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.TextView;

public class SplashActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        TextView title = findViewById(R.id.splashText);
        TextView sub = findViewById(R.id.splashSub);

        // Анимация заголовка — снизу вверх + fade in
        AnimationSet titleAnim = new AnimationSet(true);
        titleAnim.setInterpolator(new DecelerateInterpolator());

        AlphaAnimation fadeIn = new AlphaAnimation(0f, 1f);
        fadeIn.setDuration(700);
        fadeIn.setFillAfter(true);

        TranslateAnimation slideUp = new TranslateAnimation(0, 0, 80f, 0f);
        slideUp.setDuration(700);
        slideUp.setFillAfter(true);

        titleAnim.addAnimation(fadeIn);
        titleAnim.addAnimation(slideUp);
        titleAnim.setFillAfter(true);
        title.startAnimation(titleAnim);

        // Подпись появляется с задержкой
        if (sub != null) {
            sub.setAlpha(0f);
            sub.postDelayed(() -> {
                AlphaAnimation subAnim = new AlphaAnimation(0f, 1f);
                subAnim.setDuration(500);
                subAnim.setFillAfter(true);
                sub.setAlpha(1f);
                sub.startAnimation(subAnim);
            }, 400);
        }

        // Переход в MainActivity
        title.postDelayed(() -> {
            AlphaAnimation fadeOut = new AlphaAnimation(1f, 0f);
            fadeOut.setDuration(300);
            fadeOut.setFillAfter(true);
            fadeOut.setAnimationListener(new Animation.AnimationListener() {
                @Override public void onAnimationStart(Animation a) {}
                @Override public void onAnimationRepeat(Animation a) {}
                @Override public void onAnimationEnd(Animation a) {
                    startActivity(new Intent(SplashActivity.this, MainActivity.class));
                    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                    finish();
                }
            });
            title.startAnimation(fadeOut);
            if (sub != null) sub.startAnimation(fadeOut);
        }, 1800);
    }
}
