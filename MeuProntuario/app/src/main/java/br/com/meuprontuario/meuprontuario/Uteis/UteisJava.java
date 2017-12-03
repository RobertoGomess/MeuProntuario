package br.com.meuprontuario.meuprontuario.Uteis;

/**
 * Created by Beto on 19/06/2017.
 */

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;

import com.google.android.gms.maps.GoogleMap;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import br.com.meuprontuario.meuprontuario.PacoteMenu.MenuHomeActivity;
import br.com.meuprontuario.meuprontuario.PermissionUtils;


public class UteisJava {

    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;

    public static String bytesParaString(InputStream ls) {

        try {

            byte[] buffer = new byte[1024];
            /**Armazena todos os bytes lidos*/
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            /**descobrir a quantidade de bytes lidos*/
            int bytesLidos;
            /**lendo um byte por vez*/
            while ((bytesLidos = ls.read(buffer)) != -1) {
                /**copia a quantidade de bytes lidos do buffer para o byteArrayOutputStream*/
                byteArrayOutputStream.write(buffer, 0, bytesLidos);
            }
            return new String(byteArrayOutputStream.toByteArray(), "UTF-8");

        } catch (Exception e) {
            return "";
        }
    }

    public static Bitmap downloadImageMovie(String urlImage) {
        try {
            /**Monta a URL a partir de uma String*/
            URL url = new URL("http://image.tmdb.org/t/p/w185/" + urlImage);
            /**riamos o objeto HttpURLConnection e obtemos a conexão com determinada url atraves do meotdo .openConnection.*/
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true); /**Informa que iremos apenas realizar leitura nessa determinada conexão*/
            connection.connect(); /**estabelece uma conexão com o servidor*/
            InputStream input = connection.getInputStream(); /**realiza o download da imagem*/
            Bitmap myBitmap = BitmapFactory.decodeStream(input); /**converte os bytes de retono do servidor em um bitmap**/
            return myBitmap;
        } catch (Exception e) {
            return null;
        }
    }
    public static void abriConfigGps( Context context ){

        Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
        context.startActivity(intent);
    }

    public static void enableMyLocation(Context context, AppCompatActivity activity, GoogleMap googleMap) {

        if (ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            // Permission to access the location is missing.
            PermissionUtils.requestPermission(activity, LOCATION_PERMISSION_REQUEST_CODE,
                    Manifest.permission.ACCESS_FINE_LOCATION);
        } else if (googleMap != null) {
            // Access to the location has been granted to the app.
            googleMap.setMyLocationEnabled(true);
            googleMap.getUiSettings().setMyLocationButtonEnabled(true);
        }
    }

    public static Location getMyPosition(Context context, AppCompatActivity activity, GoogleMap googleMap) {
        Location location = null;

        if (ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            // Permission to access the location is missing.
            PermissionUtils.requestPermission(activity, LOCATION_PERMISSION_REQUEST_CODE,
                    Manifest.permission.ACCESS_FINE_LOCATION);
        } else if (googleMap != null) {

            LocationManager locationManager = (LocationManager)
                    activity.getSystemService(Context.LOCATION_SERVICE);
            Criteria criteria = new Criteria();

            location = locationManager.getLastKnownLocation(locationManager
                    .getBestProvider(criteria, false));

        }
        return  location;
    }
    public static boolean isLocationEnabled(Context context) {
        int locationMode = 0;
        String locationProviders;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
            try {
                locationMode = Settings.Secure.getInt(context.getContentResolver(), Settings.Secure.LOCATION_MODE);

            } catch (Settings.SettingNotFoundException e) {
                e.printStackTrace();
            }

            return locationMode != Settings.Secure.LOCATION_MODE_OFF;

        }else{
            locationProviders = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.LOCATION_PROVIDERS_ALLOWED);
            return !TextUtils.isEmpty(locationProviders);
        }
    }

}