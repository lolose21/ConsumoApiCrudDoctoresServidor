<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="controller"
             class="controllers.ControllerDoctores"
             scope="request"/>
<!DOCTYPE html>
<html>
    <head>
        <link href="css/fondo02.css" rel="stylesheet" type="text/css"/>
        <jsp:include page="includes/webhead.jsp"/>
        <title>Plantilla</title>
    </head>
    <body>
        <jsp:include page="includes/webmenu.jsp"/>
    <main role="main" class="container">

        <div class="starter-template">
          <h1>DOCTORES API CRUD</h1>
          <a href="webinsertardoctor.jsp" class="btn btn-danger">NUEVO DOCTOR</a>
          <c:set var="eliminar" value="${param.eliminar}"/>
          <c:if test="${eliminar != null}">
              <c:set var="mensaje"
                     value="${controller.deleteDoctor(eliminar)}"/>
              <c:out value="${mensaje}" escapeXml="false"/>
          </c:if>
        
          <form method="post">
          <table border="1" class="table table-dark">
              <thead>
                  <tr>
                      <th>HOSPITAL</th>
                      <th>DOCTORNO</th>
                      <th>APELLIDO</th>
                      <th>ESPECIALIDAD</th>
                      <th>SALARIO</th>
                      <th>ELIMINAR</th>
                  </tr>
              </thead>
              <tbody>
                  <c:forEach items="${controller.doctores}" var="doctor">
                      <tr>
                          <td>
                              <c:out value="${doctor.apellido}"/>
                          </td>
                           <td>
                              <c:out value="${doctor.especialidad}"/>
                          </td>
                           <td>
                              <c:out value="${doctor.salario}"/>
                          </td>
                           <td>
                              <c:out value="${doctor.hospitalcod}"/>
                          </td>
                          <td>
                              <button type="submit"
                                      name="eliminar"
                                      value="${doctor.doctorno}"
                                      class="btn btn-danger">
                                  ELIMINAR
                              </button>
                                      
                          </td>
                          <td>
                              
                              <a href="webmodificardoctor.jsp?iddoctor=${doctor.doctorno}"
                                 class="btn btn-outline-danger">
                              MODIFICAR
                              </a>
                          </td>
                      </tr>
                      
                  </c:forEach>
                 
              </tbody>
          </table>
          </form>
        </div>
    </main><!-- /.container -->
    <jsp:include page="includes/webfooter.jsp"/>
    </body>
</html>
