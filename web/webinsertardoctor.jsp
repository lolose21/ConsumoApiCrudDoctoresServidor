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
            <h1 style="color: blue">Insertar DOCTOR</h1>
          <a href="webdoctores.jsp" class="btn btn-dark">VOLVER</a>
          <form method="post">
              <label style="color: red">id hospital</label>
              <input type="text" name="cajaidhospital" /><br/>
              <label style="color: red">id doctor</label>
              <input type="text" name="cajaiddoctor" /><br/>
              <label style="color: red">APELLIDO</label>
              <input type="text" name="cajaapellido" /><br/>
              <label style="color: red">especialidad</label>
              <input type="text" name="cajaespecialidad" /><br/>
              <label style="color: red">salario</label>
              <input type="text" name="cajasalario" /><br/>
              <button type="submit" class="btn btn-outline-danger">
                  INSERTAR DOCTOR
              </button>
             
          </form>
          <c:set var="id" value="${param.cajaiddoctor}"/>
          <c:if test="${id != null}">
              <c:set var="hospitalcod" value="${param.cajaidhospital}"/>
              <c:set var="apellido" value="${param.cajaapellido}"/>
              <c:set var="especialidad" value="${param.cajaespecialidad}"/>
              <c:set var="salario" value="${param.cajasalario}"/>
              <c:set var="mensaje"
                     value="${controller.insertarDoctor(id,hospitalcod, apellido, especialidad, salario)}"/>
              <c:redirect url="webdoctores.jsp"/>
          </c:if>
       
        </div>
    </main><!-- /.container -->
    <jsp:include page="includes/webfooter.jsp"/>
    </body>
</html>
