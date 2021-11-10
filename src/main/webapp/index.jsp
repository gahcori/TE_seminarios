<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="java.util.List"%>
<%@page import="com.emergentes.modelo.Seminario"%>
<%
    List<Seminario> lista = (List<Seminario>) request.getAttribute("lista");
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
            <div class="containers">
                <p>SEGUNDO PARCIAL TEM-742</p>
                <span>Nombre: Guido Alvaro Huanca Cori</span>
                <span>Carnet: 7039179 L.P.</span>
            </div>
            <h1>Registro de Seminarios</h1>
            <p><a href="MainController?op=nuevo">Nuevo</a></p>
            <table border="1">
                <tr>
                    <th>Id</th>
                    <th>Titulo</th>
                    <th>Expositor</th>
                    <th>Fecha</th>
                    <th>Horas</th>
                    <th>Cupo</th>
                    <th></th>
                    <th></th>
                </tr>
                <c:forEach var="item" items="${lista}">
                    <tr>
                        <td>${item.id}</td>
                        <td>${item.titulo}</td>
                        <td>${item.expositor}</td>
                        <td>${item.fecha}</td>
                        <td>${item.hora}</td>
                        <td>${item.cupo}</td>
                        <td><a href="MainController?op=editar&id=${item.id}" title="Editar"><i class="fas fa-pen-alt"></i></a></td>
                        <td><a href="MainController?op=eliminar&id=${item.id}" title="Eliminar" onclick="return(confirm('Esta seguro de eliminar el registro?'))"><i class="fas fa-trash-alt"></i></a></td>
                    </tr>
                </c:forEach>
            </table>
    </body>
</html>
