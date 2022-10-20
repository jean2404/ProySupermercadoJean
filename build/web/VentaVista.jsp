<%-- 
    Document   : VentaVista
    Created on : 20-jul-2021, 22:18:41
    Author     : sagit
--%>

<%@page import="vista.VentaPresentador"%>
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
                <h1 class="text-center">Gestion de Ventas Jeancito</h1>
                <% VentaPresentador venPre = (VentaPresentador) session.getAttribute("venPre"); %>
                <% Object[] f = venPre.getFil();%>
                <%! Object[] f2;%>
                <x:form action="VentaControl" >

                    <table class="table table-hover">
                        <tr><td>Numero</td><td><x:text property="num" value='<%= f[0].toString()%>' /></td></tr>
                        <tr><td>Fecha</td><td><x:text property="fec" value='<%= f[1].toString()%>' /></td></tr>
                        <tr><td>Empleado</td><td><x:text property="codEmp" value='<%= f[2].toString()%>' /></td></tr>
                    </table>

                    <% Object[] f3 = venPre.getFil2();%>
                    <table class="table table-hover">
                        <tr>
                            <td>Ingrese DNI</td>
                            <td><x:text property="dni" value='<%= f3[0].toString()%>' /> </td>
                            <td><x:submit property="acc" value="Buscar Cliente" /></td>
                        </tr><tr>
                            <td>Nombre Cliente</td>
                            <td colspan="2"><x:text property="cli" value='<%= f3[1].toString()%>' /> </td>

                        </tr>
                    </table>

                    <table class="table table-sm">
                        <tr>
                            <td>Codigo</td>
                            <td>Nombre</td>
                            <td>Precio</td>
                            <td>Cantidad</td>
                            <td>Importe</td>
                            <td></td>
                        </tr>
                        <% for (int i = 0; i < venPre.getLis().size(); i++) { %>
                        <% f2 = (Object[]) venPre.getLis().get(i);%>
                        <tr>
                            <x:form action="VentaControl">
                                <td><x:text property="cod" value='<%= f2[0].toString()%>' /></td>
                                <td><x:text property="nom" value='<%= f2[1].toString()%>' /></td>
                                <td><x:text property="pre" value='<%= f2[2].toString()%>' /></td>
                                <td><x:text property="can" value='<%= f2[3].toString()%>' /></td>
                                <td><x:text property="imp" value='<%= f2[4].toString()%>' /></td>
                                <td><x:submit property="acc" value="Quitar Articulo" /> </td>
                            </x:form>
                        </tr>
                        <% }%>
                    </table>

                    <table class="table table-hover">
                        <tr><td>
                                <x:submit property="acc" value="Listar Articulos" />
                                <x:submit property="acc" value="Grabar Venta" />
                                <a class="btn btn-light" href="AccesoMenu.jsp">Volver</a>
                                Total ==> <x:text property="tot" value='<%= f2[5].toString()%>' />
                            </td>
                        </tr>
                    </table>
                    <p class="h5"><%= venPre.getMsg()%></p>
                </x:form>
            </div>
        </div>

        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx" crossorigin="anonymous"></script>

    </body>
</html>
