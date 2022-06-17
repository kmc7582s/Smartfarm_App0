package www.saeon.co.kr;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {
    private String data = "";
    private Handler mHandler;
    private Socket socket;
    private String name;
    private BufferedReader networkReader;
    private BufferedWriter networkWriter;
    private String ip = "192.168.0.58"; // IP

    BufferedReader socket_in;
    PrintWriter socket_out;

    private int port = 7000; // PORT번호
    EditText input, ipadress;
    Button button, btconnect, btallon,btalloff;
    TextView output;
    TextView pump, vent, window, led, heater, cam, temp, moisture, cds, humidity, temp2;

    int cnt = 0;
    private byte[] byteArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        mHandler = new Handler();

        input = (EditText) findViewById(R.id.editText);
        ipadress = (EditText) findViewById(R.id.editText2);
        button = (Button) findViewById(R.id.button);
        btconnect = (Button) findViewById(R.id.button2);
        btallon = (Button) findViewById(R.id.button3);
        btalloff = (Button) findViewById(R.id.button4);

        output = (TextView) findViewById(R.id.textView);
        pump = (TextView) findViewById(R.id.txtPump);
        vent = (TextView) findViewById(R.id.txtVent);
        window = (TextView) findViewById(R.id.txtWindow);
        led = (TextView) findViewById(R.id.txtLed);
        heater = (TextView) findViewById(R.id.txtHeater);
        cam = (TextView) findViewById(R.id.txtCam);
        temp = (TextView) findViewById(R.id.txtTemp);
        moisture = (TextView) findViewById(R.id.txtMoisture);
        cds = (TextView) findViewById(R.id.txtCds);
        humidity = (TextView) findViewById(R.id.txtHumidity);
        temp2 = (TextView) findViewById(R.id.txtTemp2);

//        btconnect.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                ip = ipadress.getText().toString();
//                try {
//                    setSocket(ip, port);
//                } catch (IOException el) {
//                    //TODO Auto-generated catch block
//                    el.printStackTrace();
//                }
//                checkUpdate.start();
//            }
//        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(input.getText().toString() != null || input.getText().toString().equals("")){
                    PrintWriter out = new PrintWriter(networkWriter,true);
                    String return_msg = input.getText().toString();
                    out.println(return_msg);
                }
            }
        });

        btallon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PrintWriter out = new PrintWriter(networkWriter,true);
                String return_msg = "smartfarm:A1000X";
                out.println(return_msg);
            }
        });

        btalloff.setOnClickListener(new View.OnClickListener() {
            @Override
           public void onClick(View v){
               PrintWriter out = new PrintWriter(networkWriter,true);
               String return_msg = "smartfarm:A0000X";
               out.println(return_msg);
            }
        });
    }
}