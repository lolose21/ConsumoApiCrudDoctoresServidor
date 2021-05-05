package controllers;

import java.io.IOException;
import java.util.List;
import models.Doctor;
import services.ServiceDoctores;

public class ControllerDoctores {

    ServiceDoctores service;

    public ControllerDoctores() {
        this.service = new ServiceDoctores();
    }

    /*  public String getDoctores() throws IOException {
        List<Doctor> doctores = this.service.getDoctores();
        String html = "";
        for (Doctor doc : doctores) {
            html += "<tr>";
            html += "<td>" + doc.getHospitalcod() + "</td>";
            html += "<td>" + doc.getDoctorno() + "</td>";
            html += "<td>" + doc.getApellido() + "</td>";
            html += "<td>" + doc.getEspecialidad() + "</td>";
            html += "<td>" + doc.getSalario() + "</td>";
            html += "<td>";
            html += "<button type='submit' name='eliminar' value='"
                    + doc.getDoctorno() + "'>Eliminar</button>";
            html += "</td>";
            html += "<td>";
            html += "<a href='webmodificardoctor.jsp?iddoctor="
                    + doc.getDoctorno() + "'>modificar</a>";
            html += "</td>";
            html += "</tr>";

        }
        return html;
    }*/
    public List<Doctor> getDoctores() throws IOException {
        List<Doctor> doctores = this.service.getDoctores();
        return doctores;
    }

    public String insertarDoctor(int hospitalcod, int doctorno, String apellido, String especialidad, int salario) throws IOException {
        int code = this.service.insertDoctor(hospitalcod, doctorno, apellido, especialidad, salario);
        return "<h1 style='color:blue'> Doctor insertado, code(" + code + ")</h1>";
    }

    public String deleteDoctor(String iddoctor) throws IOException {
        int code
                = this.service.eliminarDoctor(iddoctor);
        return "<h1>Doctor eliminado, code(" + code + ")</h1>";
    }
    //para modificar , vamos a crear un metodo para buscar al doctor
    //y dibujar las cajas co sus datos
    //una vez que pulse sobre el boton de modificar ,

    /* public String getDoctor(String iddoctor) throws IOException {
        Doctor doctor = this.service.findDoctor(iddoctor);
        String html = "<input type='hidden' name='iddoctor' value='"
                + doctor.getDoctorno() + "'/>";
        html += "<label>Apellido;</label>";
        html += "<input type='text' name='apellido' value='"
                + doctor.getApellido() + "'/><br/>";
        html += "<label>Especialidad;</label>";
        html += "<input type='text' name='especialidad' value='"
                + doctor.getEspecialidad() + "'/><br/>";
        html += "<label>Salario;</label>";
        html += "<input type='text' name='salario' value='"
                + doctor.getSalario() + "'/><br/>";
        html += "<label>Hospital;</label>";
        html += "<input type='text' name='idhospital' value='"
                + doctor.getHospitalcod() + "'/><br/>";
        return html;

    }*/
    public Doctor getDoctor(String iddoctor) throws IOException {
        Doctor doctor = this.service.findDoctor(iddoctor);
        return doctor;

    }

    public String updateDoctor(int iddoctor, String apellido,
            String especialidad, int salario, int idhospital) throws IOException {
        int code = this.service.updateDoctor(idhospital, iddoctor, apellido, especialidad, salario);
        return "<h1 style='color:fuchsia'>Doctor actualizado(" + code + ")</h1>";

    }
}
