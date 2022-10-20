<%-- 
    Document   : CompraListado
    Created on : 21-jul-2021, 15:14:01
    Author     : sagit
--%>

<%@page import="vista.CompraPresentador"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="x" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>

        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
        <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@100&display=swap" rel="stylesheet">
        <style>@import"estiloRegistro.css";</style>
    </head>
    <body>
        <div class="container">
            <div class="col-sm-10 mx-auto my-5">
                <h1 class="text-center">Listado de Articulos</h1>
                <% CompraPresentador comPre = (CompraPresentador) session.getAttribute("comPre"); %>
                <table class="table table-hover">
                    <tr>
                        <td>Codigo</td>
                        <td>Nombre</td>
                        <td>Precio</td>
                        <td>Cantidad</td>
                        <td></td>
                    </tr>
                    <% for (int i = 0; i < comPre.getLis().size(); i++) { %>
                    <% Object[] f = (Object[]) comPre.getLis().get(i);%>
                    <tr>
                        <x:form action="CompraControl">
                            <td><x:text property="cod" value='<%= f[0].toString()%>' /></td>
                            <td><x:text property="nom" value='<%= f[1].toString()%>' /></td>
                            <td><x:text property="pre" value='<%= f[2].toString()%>' /></td>
                            <td><x:text property="can" value="" /></td>
                            <td><x:submit property="acc" value="Agregar Articulo" /> </td>
                        </x:form>
                    </tr>
                    <% }%>
                </table>
            </div>
        </div>
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx" crossorigin="anonymous"></script>

    </body>
</html>
