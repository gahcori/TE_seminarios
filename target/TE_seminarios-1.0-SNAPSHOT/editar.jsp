<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="com.emergentes.modelo.Seminario"%>
<%
    Seminario semi = (Seminario) request.getAttribute("semi");
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/style.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta2/css/all.min.css">
        <title>Seminarios</title>
    </head>
    <body>
        <div class="container">
            <h1>
                <c:if test="${semi.id == 0}">
                    Nuevo registro
                </c:if>
                <c:if test="${semi.id != 0}">
                    Editar registro
                </c:if>
            </h1>
            <form action="MainController" method="post">
                <input type="hidden" name="id" value="${semi.id}">
                <div>
                    <label>Titulo</label>
                    <input type="text" name="titulo" placeholder="" value="${semi.titulo}">
                </div>
                <div>
                    <label>Expositor</label>
                    <input type="text" name="expositor" value="${semi.expositor}">
                </div>
                <div>
                    <label>Fecha</label>
                    <input type="text" name="fecha" placeholder="2021-12-31" value="${semi.fecha}">
                </div>
                <div>
                    <label>Hora</label>
                    <input type="text" name="hora" placeholder="00:00 - 00:00" value="${semi.hora}">
                </div>
                <div>
                    <label>Cupo</label>
                    <input type="number" name="cupo" value="${semi.cupo}">
                </div>
                <button type="submit">Enviar</button>
            </form>
        </div>
    </body>
</html>
