package com.apps4ev.helloworld;

import android.Manifest;
import android.hardware.bydauto.ac.AbsBYDAutoAcListener;
import android.hardware.bydauto.ac.BYDAutoAcDevice;
import android.hardware.bydauto.bodywork.BYDAutoBodyworkDevice;
import android.hardware.bydauto.sensor.BYDAutoSensorDevice;
import android.hardware.bydauto.speed.AbsBYDAutoSpeedListener;
import android.hardware.bydauto.speed.BYDAutoSpeedDevice;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import static android.content.pm.PackageManager.PERMISSION_GRANTED;
import static android.hardware.bydauto.ac.BYDAutoAcDevice.getInstance;

public class MainActivity extends AppCompatActivity {
    private BYDAutoBodyworkDevice mBodyworkDevice = null;
    private BYDAutoAcDevice mAcDevice = null;

    private ImageButton refreshVinBtn, refreshStatusBtn;
    private TextView mVin, statusTV, mainTemperatureTV, deputyTemperatureTV, windLevelTV;
    private Switch switchAcSwitch, separateSwitch, ventilationSwitch,defrostSwitch,autoSwitch;
    private RadioGroup areaGroup;
    private RadioButton globalRb, mainRb, deputyRb;
    private EditText temperatureED, windLevelED;
    private Button temperatureSetBtn, windLevelSetBtn;
    private int area = BYDAutoAcDevice.AC_TEMPERATURE_MAIN_DEPUTY;

    private Button bt_getLightLevel;
    private TextView tv_lightLevel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mVin = (TextView) findViewById(R.id.vinCode);
        // Botão para Atualizar automaticamente o número do chassi do veículo
        refreshVinBtn = (ImageButton)findViewById(R.id.refreshVinBtn);

        // Definir o listener para o botão de atualização do número do chassi
        refreshVinBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getVinNumber();
            }
        });
        // Controles do Display
        statusTV = (TextView) findViewById(R.id.statusTV);
        mainTemperatureTV = (TextView) findViewById(R.id.mainTemperatureTV);
        deputyTemperatureTV = (TextView) findViewById(R.id.deputyTemperatureTV);
        windLevelTV = (TextView) findViewById(R.id.windLevel);
        refreshStatusBtn = (ImageButton) findViewById(R.id.refreshStatusBtn);
        refreshStatusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Retornar temperatura principal
                try {
                    int mainTemperature = mAcDevice.getTemprature(BYDAutoAcDevice.AC_TEMPERATURE_MAIN);
                    mainTemperatureTV.setText(String.valueOf(mainTemperature));
                }catch (Exception e){
                    String msg = "Não foi possível obter a temperatura principal: " + e.getMessage();
                    logMessage("Erro","RefreshTemperature", msg);
                    mainTemperatureTV.setText("-");
                }

                // Retornar temperatura da zona do passageiro
                try {
                    int deputyTemperature = mAcDevice.getTemprature(BYDAutoAcDevice.AC_TEMPERATURE_DEPUTY);
                    deputyTemperatureTV.setText(String.valueOf(deputyTemperature));
                }catch (Exception e){
                    String msg = "Não foi possível obter a temperatura principal: " + e.getMessage();
                    logMessage("Erro","RefreshTemperature", msg);
                    deputyTemperatureTV.setText("-");
                }
                // Retornar velocidade do ar-condicionado
                try {
                    int mainWindLevel = mAcDevice.getAcWindLevel();
                    windLevelTV.setText(String.valueOf(mainWindLevel));
                }catch (Exception e){
                    String msg = "Não foi possível obter a velocidade do ar-condicionado: " + e.getMessage();
                    logMessage("Erro","RefreshTemperature", msg);
                    windLevelTV.setText("-");
                }

            }
        });
        // Controles do ar-condicionado
        switchAcSwitch = (Switch) findViewById(R.id.switchAcSwitch);
        separateSwitch = (Switch) findViewById(R.id.separateSwitch);
        ventilationSwitch = (Switch) findViewById(R.id.ventilationSwitch);
        defrostSwitch = (Switch) findViewById(R.id.defrostSwitch);
        autoSwitch = (Switch) findViewById(R.id.autoSwitch);
        // Liga/Desliga ar-condicionado
        switchAcSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                String action = "";

                try {
                    if(isChecked){
                        action = "ligar";
                        mAcDevice.start(BYDAutoAcDevice.AC_CTRL_SOURCE_VOICE);
                    }else {
                        action = "desligar";
                        mAcDevice.stop(BYDAutoAcDevice.AC_CTRL_SOURCE_VOICE);
                    }
                }catch (Exception e){
                    String msg = "Não foi possível " + action + " o ar-condicionado: " + e.getMessage();
                    logMessage("Erro","RefreshTemperature", msg);
                }

                try {
                    int state = mAcDevice.getAcStartState();
                    String stateValue = (state==BYDAutoAcDevice.AC_POWER_ON?"Ar condicionado ligado":"Ar condicionado desligado");
                    Log.d("state",stateValue);
                }catch (Exception e){
                    String msg = "Não foi possível obter o status do ar-condicionado: " + e.getMessage();
                    logMessage("Erro","AcSwitch", msg);
                }

            }
        });
        // Circulação
        separateSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                try {
                    if(isChecked){
                        mAcDevice.setAcTemperatureControlMode(BYDAutoAcDevice.AC_CTRL_SOURCE_VOICE,
                                BYDAutoAcDevice.AC_TEMPCTRL_SEPARATE_ON);
                    }else {
                        mAcDevice.setAcTemperatureControlMode(BYDAutoAcDevice.AC_CTRL_SOURCE_VOICE,
                                BYDAutoAcDevice.AC_TEMPCTRL_SEPARATE_OFF);
                    }
                }catch (Exception e){
                    String msg = "Não foi possível alterar o status de circulação do ar-condicionado: " + e.getMessage();
                    logMessage("Erro","SeparateSwitch", msg);
                }

                try {
                    int code = mAcDevice.getAcTemperatureControlMode();
                    Log.d("code",code+"");
                }catch (Exception e){
                    String msg = "Não foi possível obter o status de circulação do ar-condicionado: " + e.getMessage();
                    logMessage("Erro","SeparateSwitch", msg);
                }

            }
        });
        // Ventilação
        ventilationSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                try {
                    if(isChecked){
                        mAcDevice.setAcVentilationState(BYDAutoAcDevice.AC_CTRL_SOURCE_VOICE,
                                BYDAutoAcDevice.AC_VENTILATION_STATE_ON);
                    }else {
                        mAcDevice.setAcVentilationState(BYDAutoAcDevice.AC_CTRL_SOURCE_VOICE,
                                BYDAutoAcDevice.AC_VENTILATION_STATE_OFF);
                    }
                }catch (Exception e) {
                    String msg = "Não foi possível alterar o status de ventilação do ar-condicionado: " + e.getMessage();
                    logMessage("Erro","ventilationSwitch", msg);
                }
                try {
                    int code = mAcDevice.getAcVentilationState();
                    Log.d("code",code+"");
                }catch (Exception e) {
                    String msg = "Não foi possível obter o status de ventilação do ar-condicionado: " + e.getMessage();
                    logMessage("Erro","ventilationSwitch", msg);
                }

            }
        });
        // Desembaçador frontal
        defrostSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                try {
                    if(isChecked){
                        mAcDevice.setAcDefrostState(BYDAutoAcDevice.AC_CTRL_SOURCE_VOICE,
                                BYDAutoAcDevice.AC_DEFROST_AREA_FRONT,
                                BYDAutoAcDevice.AC_DEFROST_STATE_ON);
                    }else {
                        mAcDevice.setAcDefrostState(BYDAutoAcDevice.AC_CTRL_SOURCE_VOICE,
                                BYDAutoAcDevice.AC_DEFROST_AREA_FRONT,
                                BYDAutoAcDevice.AC_DEFROST_STATE_OFF);
                    }
                }catch (Exception e){
                    String msg = "Não foi possível alterar o status do desembaçador frontal: " + e.getMessage();
                    logMessage("Erro","defrostSwitch", msg);
                }

                try {
                    int code = mAcDevice.getAcDefrostState(BYDAutoAcDevice.AC_DEFROST_AREA_FRONT);
                    Log.d("code",code+"");
                }catch (Exception e){
                    String msg = "Não foi possível obter o status do desembaçador frontal: " + e.getMessage();
                    logMessage("Erro","defrostSwitch", msg);
                }
            }
        });
        // Modo automático
        autoSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {

                try {
                    if(isChecked){
                        mAcDevice.setAcControlMode(BYDAutoAcDevice.AC_CTRL_SOURCE_VOICE,
                                BYDAutoAcDevice.AC_CTRLMODE_AUTO);
                    }else {
                        mAcDevice.setAcControlMode(BYDAutoAcDevice.AC_CTRL_SOURCE_VOICE,
                                BYDAutoAcDevice.AC_CTRLMODE_MANUAL);
                    }
                }catch (Exception e){
                    String msg = "Não foi possível alterar o modo automático do ar-condicionado: " + e.getMessage();
                    logMessage("Erro","autoSwitch", msg);
                }

                try {
                    int code = mAcDevice.getAcControlMode();
                    Log.d("code",code+"");
                }catch (Exception e){
                    String msg = "Não foi possível obter o status do modo automático do ar-condicionado: " + e.getMessage();
                    logMessage("Erro","autoSwitch", msg);
                }

            }
        });
        areaGroup = (RadioGroup) findViewById(R.id.areaGroup);
        globalRb = (RadioButton) findViewById(R.id.globalRb);
        mainRb = (RadioButton) findViewById(R.id.mainRb);
        deputyRb = (RadioButton) findViewById(R.id.deputyRb);
        areaGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                String areaValue = "Global";

                try {
                    if(globalRb.getId() == checkedId){
                        areaValue = "Global";
                        area = BYDAutoAcDevice.AC_TEMPERATURE_MAIN_DEPUTY;
                    }
                    if(mainRb.getId() == checkedId){
                        areaValue = "Motorista";
                        area = BYDAutoAcDevice.AC_TEMPERATURE_MAIN;
                    }
                    if(deputyRb.getId() == checkedId){
                        areaValue = "Passageiro";
                        area = BYDAutoAcDevice.AC_TEMPERATURE_DEPUTY;
                    }
                }catch (Exception e){
                    String msg = "Não foi possível alterar a zona do ar-condicionado ("+ areaValue + "): " + e.getMessage();
                    logMessage("Erro","areaGroup", msg);
                }

                Log.d("area",areaValue);
            }
        });
        temperatureED = (EditText) findViewById(R.id.temperatureED);
        windLevelED = (EditText) findViewById(R.id.windLevelED);
        temperatureSetBtn = (Button) findViewById(R.id.temperatureSetBtn);
        windLevelSetBtn = (Button) findViewById(R.id.windLevelSetBtn);
        temperatureSetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int value = 17;
                try{
                    value = Integer.valueOf(temperatureED.getText().toString()).intValue();
                }catch (Exception e){
                    String msg = "Determine um valor numérico entre 17 e 33! " + e.getMessage();
                    logMessage("Validation Error","temperatureSetBtn", msg);
                }

                try {
                    int code = mAcDevice.setAcTemperature(area,value,
                            BYDAutoAcDevice.AC_CTRL_SOURCE_VOICE,BYDAutoAcDevice.AC_TEMPERATURE_UNIT_OC);
                    Log.d("code","Sucesso: "+code);
                }catch (Exception e){
                    String msg = "Não foi estabelecer a temperatura para o ar-condicionado: " + e.getMessage();
                    logMessage("Erro","temperatureSetBtn", msg);
                }

            }
        });
        windLevelSetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int value = 0;
                try{
                    value = Integer.valueOf(windLevelED.getText().toString()).intValue();
                }catch (Exception e){
                    String msg = "Determine um valor numérico entre 1 e 7!" + e.getMessage();
                    logMessage("Validation Error","windLevelSetBtn", msg);
                }

                try{
                    int code = mAcDevice.setAcWindLevel(BYDAutoAcDevice.AC_CTRL_SOURCE_VOICE,value);
                    Log.d("code","Sucesso: "+code);
                }catch (Exception e){
                    String msg = "Não foi possível estabelecer a velocidade do ar-condicionado: " + e.getMessage();
                    logMessage("Erro","windLevelSetBtn", msg);
                }

            }
        });

        // Intensidade de luz
        bt_getLightLevel=(Button)findViewById(R.id.button_get_light_level);
        tv_lightLevel=(TextView)findViewById(R.id.text_light_level);
        bt_getLightLevel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    int light_level = BYDAutoSensorDevice.getInstance(MainActivity.this).getLightIntensity();
                    Log.d("Nível de luz：",Integer.toString(light_level));
                    tv_lightLevel.setText(String.valueOf(light_level));
                }catch (Exception e){
                    String msg = "Não foi possível obter a intensidade de luz: " + e.getMessage();
                    logMessage("Erro","bt_getLightLevel", msg);
                }

            }
        });
    }

    // Interface de monitoramento de velocidade do ar-condicionado do veículo
    private final AbsBYDAutoAcListener mAbsBYDAutoAcListener = new AbsBYDAutoAcListener() {
        @Override
        public void onTemperatureChanged(int area, int value) {
            super.onTemperatureChanged(area, value);
            String msg = "area = "+ area+", value = "+value;
            logMessage("A/C Monitor","AcMonitorListener", msg);

            switch (area) {
                case BYDAutoAcDevice.AC_TEMPERATURE_OUT:
                    statusTV.setText(value+"");
                    break;
                case BYDAutoAcDevice.AC_TEMPERATURE_MAIN:
                    mainTemperatureTV.setText(value+"");
                    break;
                case BYDAutoAcDevice.AC_TEMPERATURE_DEPUTY:
                    deputyTemperatureTV.setText(value+"");
                    break;
            }
        }
    };

    @Override
    protected void onStart() {
        super.onStart();
        //Permissões dinâmicas devem ser retornadas antes de receber status sobre a carroceria do veículo
        String msg = "Vai verificar as permissões necessárias...";
        logMessage("PERMISSÕES","PERMISSÕES", msg);

        if (!checkBodyworkCommonPermission()) {
            requestBodyworkCommonPermission();
        } else {
            getVinNumber();

            if (!checkAcCommonPermission()) {
                requestAcCommonPermission();
            } else {
                getWindLevel();
                setAcListener();
            }
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(mAcDevice!=null){
            Log.d("tag","2222");
            mAcDevice.unregisterListener(mAbsBYDAutoAcListener);
        }
    }


    //Verificar permissões necessárias para o aplicativo
    public boolean checkBodyworkCommonPermission() {
        return ContextCompat.checkSelfPermission(this,
                Manifest.permission.BYDAUTO_BODYWORK_COMMON) == PERMISSION_GRANTED;
    }

    public boolean checkAcCommonPermission() {
        return ContextCompat.checkSelfPermission(this,
                Manifest.permission.BYDAUTO_AC_COMMON) == PERMISSION_GRANTED;
    }

    // Solicitar permissões para o status da carroceria do veículo
    public void requestBodyworkCommonPermission() {
        String msg = "A permissão 'BYDAUTO_BODYWORK_COMMON' não foi concedida. Verificando se é possível solicitar";
        logMessage("PERMISSÕES","PERMISSÕES", msg);

        // Caso tenha solicitado e o usuário negado, retornará true。
        if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                android.Manifest.permission.BYDAUTO_BODYWORK_COMMON)) {
            msg = "A permissão 'BYDAUTO_BODYWORK_COMMON' foi rejeitada em uma solicitação anterior. Não será possível solicitar novamente";
            logMessage("Erro","PERMISSÕES", msg);
        } else {
            // Solicitar permissão de status da carroceria. Use onRequestPermissionsResult para verificar o retorno da solicitação
            msg = "Solicitando a permissão 'BYDAUTO_BODYWORK_COMMON'...";
            logMessage("PERMISSÕES","PERMISSÕES", msg);
            ActivityCompat.requestPermissions(this,
                    new String[]{android.Manifest.permission.BYDAUTO_BODYWORK_COMMON,}, 1);
        }

    }

    // Solicitar permissões para aparelhos de ar condicionado
    public void requestAcCommonPermission() {
        String msg = "A permissão 'BYDAUTO_AC_COMMON' não foi concedida. Verificando se é possível solicitar";
        logMessage("PERMISSÕES","PERMISSÕES", msg);

        // Caso tenha solicitado e o usuário negado, retornará  true
        if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                Manifest.permission.BYDAUTO_AC_COMMON)) {
            msg = "A permissão 'BYDAUTO_AC_COMMON' foi rejeitada em uma solicitação anterior. Não será possível solicitar novamente";
            logMessage("Erro","PERMISSÕES", msg);
        } else {
            // Solicitar permissão de acesso ao ar condicionado. Use onRequestPermissionsResult para verificar o retorno da solicitação
            msg = "Solicitando a permissão 'BYDAUTO_AC_COMMON'...";
            logMessage("PERMISSÕES","PERMISSÕES", msg);
            ActivityCompat.requestPermissions(this,
                    new String[]{android.Manifest.permission.BYDAUTO_AC_COMMON,}, 1);
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        String havePermissions = (permissions != null) ? "Permissions: true, " : "Permissions: false, ";
        String numberPermissions = (permissions != null) ? "Number of permissions: " + permissions.length + ", " : "Number of permissions: 0, ";
        String permissionsName = "";

        if (permissions.length > 0) {
            for (int i = 0; i < permissions.length; i++) {
                permissionsName += permissions[i] + ", ";
            }
        }
        String msg = "Retorno da solicitação de permissão: " + havePermissions + numberPermissions + permissionsName;
        logMessage("PERMISSÕES","PERMISSÕES", msg);

        if (requestCode == 1) {
            for (int i = 0; i < permissions.length; i++) {
                if (grantResults[i] == PERMISSION_GRANTED) {

                    msg = "Permissões :: " + permissions[i] + " :: Sucesso na aplicação";
                    logMessage("Sucesso","PERMISSÕES", msg);

                    if (permissions[i].equals( android.Manifest.permission.BYDAUTO_BODYWORK_COMMON)) {

                        getVinNumber();

                        // solicitar as permissões dinâmicas para acesso ao ar-condicionado
                        requestAcCommonPermission();

                    } else if (permissions[i].equals( android.Manifest.permission.BYDAUTO_AC_COMMON)) {

                        getWindLevel();

                        // listener para monitoramento da temperatura do ar-condicionado
                        try{
                            setAcListener();
                        }catch (Exception e){
                            msg = "Não foi possível instanciar o listener para monitoramento da temperatura do ar-condicionado: " + e.getMessage();
                            logMessage("Erro","PERMISSÕES", msg);
                        }
                    }
                } else {
                    msg = "Permissões :: " + permissions[i] + " :: Falha na aplicação";
                    logMessage("Erro","PERMISSÕES", msg);
                }
            }
        }
    }

    public void getVinNumber() {

        try {
            if (mBodyworkDevice == null) mBodyworkDevice = BYDAutoBodyworkDevice.getInstance(this);
        } catch (Exception e) {
            String msg = "Erro ao retornar o objeto do status da carroceria: " + e.getMessage();
            logMessage("Erro","getVinNumber", msg);
        }

        if (mBodyworkDevice != null) {
            // Obter o chassi do veículo
            try {
                String vin = mBodyworkDevice.getAutoVIN();
                //String vin = "BYD-HAUA";
                mVin.setText(vin);
            } catch (Exception e) {
                String msg = "Não foi possível obter o número do chassi: " + e.getMessage();
                logMessage("Erro","getVinNumber", msg);
            }
        } else {
            String msg = "Objeto do status da carroceria não instanciado!";
            logMessage("Erro","getVinNumber", msg);
        }

    }

    public void getWindLevel() {

        if (mAcDevice == null) mAcDevice = BYDAutoAcDevice.getInstance(this);

        if (mAcDevice != null) {
            // Estabelece o nível de vento
            // mAcDevice.setAcWindLevel(BYDAutoAcDevice.AC_CTRL_SOURCE_UI_KEY, BYDAutoAcDevice.AC_WINDLEVEL_3);
            // Retorna o nível de vento
            try {
                Integer acwindlevel = mAcDevice.getAcWindLevel();
                windLevelTV.setText(String.valueOf(acwindlevel));
            } catch (Exception e) {
                String msg = "Não foi possível obter o nível do ar-condicionado: " + e.getMessage();
                logMessage("Erro","getWindLevel", msg);
            }
        } else {
            String msg = "Objeto do ar-condicionado não instanciado!";
            logMessage("Erro","getWindLevel", msg);
        }

    }

    public void setAcListener(){

        if(mAcDevice!=null){
            Log.d("tag","1111");
            try {
                mAcDevice.registerListener(mAbsBYDAutoAcListener);
            }catch (Exception e){
                String msg = "Não foi possível instanciar o listener para monitoramento da temperatura do ar-condicionado: " + e.getMessage();
                logMessage("Erro","setAcListener", msg);
            }
        }

    }

    public void logMessage(String messageType, String TAG, String message){
        TextView mLogger = (TextView) findViewById(R.id.appLogger);
        mLogger.append("\n" + message);

        Toast.makeText(MainActivity.this, messageType, Toast.LENGTH_SHORT).show();
        Log.d(TAG,message);
    }
}
