package services;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import models.Doctor;

public class ServiceDoctores {

    String urlapi;

    public ServiceDoctores() {
        this.urlapi = "https://apicruddoctoresisma.azurewebsites.net/";

    }

    //creamos un metodo para convertir el Stream
    //de la peticion http a String
    private String readStreamHttp(InputStream stream) throws IOException {
        BufferedReader buffer
                = new BufferedReader(new InputStreamReader(stream));
        StringBuffer data = new StringBuffer();
        String linea = "";
        String separador = "";
        while ((linea = buffer.readLine()) != null) {
            data.append(separador + linea);
            separador = "\n";
        }
        return data.toString();
    }

    //metodo para poder realizar la peticion GET
    //este metodo lo vamos a utilizar para todas las
    //peticiones GET que tuvieramos en este service
    //es un metodo generico que devuelve el json
    private String getHttpRequest(String request) throws MalformedURLException, IOException {
        URL url = new URL(this.urlapi + request);
        HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
        conexion.setRequestMethod("GET");
        conexion.setRequestProperty("Accept", "application/json");
        if (conexion.getResponseCode() == 200) {
            InputStream stream = conexion.getInputStream();
            String data = this.readStreamHttp(stream);
            return data;
        } else {
            conexion.disconnect();
            return null;
        }
    }

    public List<Doctor> getDoctores() throws IOException {
        String request = "api/doctores";
        String datosjson = this.getHttpRequest(request);
        Gson converter = new Gson();
        List<Doctor> doctores
                = converter.fromJson(datosjson,
                        new TypeToken<List<Doctor>>() {
                        }.getType());
        return doctores;

    }

    public Doctor findDoctor(String id) throws IOException {
        String request = "api/doctores/" + id;
        String datosjson = this.getHttpRequest(request);
        Gson converter = new Gson();
        Doctor doctor
                = converter.fromJson(datosjson, Doctor.class);
        return doctor;
    }

    public int eliminarDoctor(String id) throws MalformedURLException, IOException {
        String request = "api/doctores/delete/" + id;
        URL url = new URL(this.urlapi + request);
        HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
        conexion.setRequestMethod("DELETE");
        int statuscode = conexion.getResponseCode();
        conexion.disconnect();
        return statuscode;
    }

    public int insertDoctor(int hospitalcod, int doctorno, String apellido, String especialidad, int salario) throws MalformedURLException, IOException {
        String request = "api/doctores/post";
        URL url = new URL(this.urlapi + request);
        HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
        conexion.setRequestMethod("POST");
        //debemos indicar el tipo de contenido que vamos a enviar
        conexion.setRequestProperty("Content-Type", "application/json");
        Doctor doctor = new Doctor(hospitalcod, doctorno, apellido, especialidad, salario);
        Gson converter = new Gson();
        String doctorjson = converter.toJson(doctor);
        //debemoa enviar al doctor al servicio , cuando leemos lo hacemos
        //mediante Stream, pues cuando enviamos , lo hacemos tambien
        //mediante Stream
        //indicamos al servicio que enviamos informacion
        conexion.setDoOutput(true);
        //recuperamos el Stream del servicio para poder escribir
        //datos sobre el
        OutputStream writer = conexion.getOutputStream();
        //escribimos los datos en stream formato byte[]
        writer.write(doctorjson.getBytes());
        //liberamos el flujo del Stream
        writer.flush();
        writer.close();
        int statuscode = conexion.getResponseCode();
        conexion.disconnect();
        return statuscode;
    }

    public int updateDoctor(int hospitalcod, int doctorno, String apellido, String especialidad, int salario) throws MalformedURLException, IOException {
        String request = "api/doctores/put";
        URL url = new URL(this.urlapi + request);
        HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
        conexion.setRequestMethod("PUT");
        conexion.setRequestProperty("Content-Type", "application/json");
        Doctor doctor = new Doctor(hospitalcod, doctorno, apellido, especialidad, salario);
        Gson converter = new Gson();
        String doctorjson = converter.toJson(doctor);
        conexion.setDoOutput(true);
        OutputStream writer = conexion.getOutputStream();
        //escribimos los datos en stream formato byte[]
        writer.write(doctorjson.getBytes());
        //liberamos el flujo del Stream
        writer.flush();
        writer.close();
        int statuscode = conexion.getResponseCode();
        conexion.disconnect();
        return statuscode;
    }
}
