package com.example.jaimepaqui.controlador.swvolley;
import android.app.DownloadManager;
import android.content.Context;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class VolleyAlumnosSingleton {
    private RequestQueue queue;
    private Context context;
    private static VolleyAlumnosSingleton miIntancia;

    public VolleyAlumnosSingleton (Context context){
        this.context = context;
        queue = getRequestQue();
    }

    public RequestQueue getRequestQue(){
        if(queue == null){
            queue = Volley.newRequestQueue(context.getApplicationContext());
        }
        return queue;
    }

    public static synchronized VolleyAlumnosSingleton getInstance(Context context){
        if(miIntancia == null){
            miIntancia = new VolleyAlumnosSingleton(context);
        }
        return miIntancia;
    }

    public <T> void addToRequestQue(Request request){
        queue.add(request);
    }
}



