package com.luca.donotdisturb;

import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class ActivityMain extends Activity {

    private TextView led;
    private TextView profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_main);

        led = (TextView) findViewById(R.id.led);
        profile = (TextView) findViewById(R.id.profile);

        int ledStatus = 0;
        int profileStatus = 0;

        try {
            ledStatus = Settings.System.getInt(getContentResolver(), "notification_ligth_pulse");
        } catch (Settings.SettingNotFoundException e) {
            e.printStackTrace();
        }

        final AudioManager mobileMode = (AudioManager) this.getSystemService(Context.AUDIO_SERVICE);
        profileStatus = mobileMode.getMode();

        if (ledStatus == 0){
            led.setText("Led disabilitato");
        } else {
            led.setText("Led abilitato");
        }

        switch (profileStatus){
            case AudioManager.RINGER_MODE_NORMAL:
                profile.setText("Modalità NORMALE attiva");
                break;
            case AudioManager.RINGER_MODE_VIBRATE:
                profile.setText("Modalità VIBRAZIONE attiva");
                break;
            case AudioManager.RINGER_MODE_SILENT:
                profile.setText("Modalità SILENZIOSA attiva");
                break;
            default:
                profile.setText("Contesto non conosciuto");
                break;
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
