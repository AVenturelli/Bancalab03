package com.example.albiv.primaprova;
import android.view.View;

public class Layout extends Conta {

    public void notifiche() {
        final View decorView = getWindow().getDecorView();
        decorView.setOnSystemUiVisibilityChangeListener
                (new View.OnSystemUiVisibilityChangeListener() {
                    @Override
                    public void onSystemUiVisibilityChange(int visibility) {
                        if ((visibility & View.SYSTEM_UI_FLAG_FULLSCREEN) == 0) {
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);
                        }
                    }
                });
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
    }
    public void actionbar()
    {
        getSupportActionBar().hide();
    }
}
