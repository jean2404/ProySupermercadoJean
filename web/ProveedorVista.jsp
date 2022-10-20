<%-- 
    Document   : ProveedorVista
    Created on : 19-jul-2021, 16:50:45
    Author     : sagit
--%>

<%@page import="vista.ProveedorPresentador"%>
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
            <div class="col-sm-6 mx-auto my-5">
                <h1 class="text-center tituloF">Mantenimiento de Proveedor</h1>
                <% ProveedorPresentador provPre = (ProveedorPresentador) session.getAttribute("provPre");%>
                <x:form action="ProveedorControl">
                    <% Object[] f = provPre.getFil();%>
                    <table class="table">
                        <tr>
                            <td>RUC</td><td><x:text property="ruc" value='<%= f[0].toString()%>'/> </td>       
                        </tr>
                        <tr>
                            <td>Nombre</td><td><x:text property="nom" value='<%= f[1].toString()%>'/> </td>       
                        </tr>
                        <tr>
                            <td>Direccion</td><td><x:text property="dir" value='<%= f[2].toString()%>'/> </td>       
                        </tr>
                        <tr>
                            <td colspan="2">
                                <x:submit property="acc" value="Grabar" />
                                <x:submit property="acc" value="Buscar" />
                                <x:submit property="acc" value="Actualizar" />
                                <x:submit property="acc" value="Eliminar" />
                                <x:submit property="acc" value="Limpiar" />
                                <a class="btn btn-light" href="AccesoMenu.jsp">Volver</a>
                            </td>
                        </tr>
                    </table>
                    <%= provPre.getMsg()%>

                </x:form>
            </div>
        </div>

        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx" crossorigin="anonymous"></script>

    </body>
</html>
