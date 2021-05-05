<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="controller"
             class="controllers.ControllerDoctores"
             scope="request"/>
<!DOCTYPE html>
<html>
    <head>
        <link href="css/personal.css" rel="stylesheet" type="text/css"/>
        
        <jsp:include page="includes/webhead.jsp"/>
        <title>Plantilla</title>
    </head>
    <body>
        <jsp:include page="includes/webmenu.jsp"/>
    <main role="main" class="container">
     
        <div class="starter-template">
            <h1 style="color:blue">MODIFICAR DOCTORES</h1>
          <a href="webdoctores.jsp">Volver</a>
          <c:set var="apellido" value="${param.apellido}"/>
          <c:if  test="${apellido != null}">
              <c:set var="iddoctor" value="${param.iddoctor}"/>
              <c:set var="apellido" value="${param.apellido}"/>
              <c:set var="especialidad" value="${param.especialidad}"/>
              <c:set var="salario" value="${param.salario}"/>
              <c:set var="idhospital" value="${param.idhospital}"/>
              <c:set var="respuesta"
                     value="${controller.updateDoctor(iddoctor, apellido, especialidad, salario, idhospital)}"/>
              <c:redirect url="webdoctores.jsp"/>
          </c:if>
          <form method="post">
              
              <c:set var="iddoctor" value="${param.iddoctor}"/>
              <h1>
              <c:out value="${param.iddoctor}"/>
              </h1>
              <c:if test="${iddoctor != null}">
                <c:set var="doctor" value="${controller.getDoctor(iddoctor)}"/>
                <input type="hidden" name="iddoctor" value="${iddoctor}"/>
                <label style="color:red">Apellido</label>
                <input type="text" name="apellido" value="${doctor.apellido}"
                       class="form-control"/><br/>
                 <label style="color:red">especialidad</label>
                 <input type="text" name="especialidad" value="${doctor.especialidad}"
                       class="form-control"/><br/>
                  <label style="color:red">Salario</label>
                  <input type="text" name="salario" value="${doctor.salario}"
                       class="form-control"/><br/>
                   <label style="color:red">Hospital</label>
                   <input type="text" name="idhospital" value="${doctor.hospitalcod}"
                       class="form-control"/><br/>
                   <button type="submit" class="btn btn-info">Modificar</button>
              </c:if>
          </form>
        
        </div>
    </main><!-- /.container -->
    <jsp:include page="includes/webfooter.jsp"/>
    </body>
</html>
