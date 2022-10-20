<%-- 
    Document   : AccesoMenu
    Created on : 24-jun-2021, 19:23:20
    Author     : sagit
--%>


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
                <p class="text-center tituloF">Menu Principal - Empleado</p>
                <% Object[] fil = (Object[]) session.getAttribute("fil");%>
                <div class="row">
                    <div class="col-sm-6">
                        <p>Codigo: <%= fil[0]%></p>
                    </div>
                    <div class="col-sm-6">
                        <p>Nombre: <%= fil[1]%></p>
                    </div>
                </div>   

            </div>

        </div>

        <div class="container-fluid">
            <nav class="navbar navbar-expand-lg navbar-light bg-light">
                <a class="navbar-brand" href="#">SuperMercado Jean</a>
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>

                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav">
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                Archivos
                            </a>
                            <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                                <a class="dropdown-item" href="#">Empleado</a>
                                <div class="dropdown-divider"></div>
                                <x:form action="EmpleadoControl">
                                    <x:submit property="acc" value="Nuevo Mantenimiento"/>
                                </x:form>
                                <div class="dropdown-divider"></div>
                                <a class="dropdown-item" href="#">Cliente</a>
                                <div class="dropdown-divider"></div>
                                <x:form action="ClienteControl">
                                    <x:submit property="acc" value="Nuevo Mantenimiento"/>
                                </x:form>
                                <div class="dropdown-divider"></div>
                                <a class="dropdown-item" href="#">Proveedor</a>
                                <div class="dropdown-divider"></div>
                                <x:form action="ProveedorControl">
                                    <x:submit property="acc" value="Nuevo Mantenimiento"/>
                                </x:form>


                            </div>
                        </li>

                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                Procesos
                            </a>
                            <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                                <x:form action="VentaControl">
                                    <x:hidden property="codEmp"  value="<%= fil[0].toString() %>"/>
                                    <x:submit property="acc" value="Nueva Venta"/>
                                </x:form>
                                <div class="dropdown-divider"></div>
                                <x:form action="CompraControl">
                                    <x:hidden property="codEmp"  value="<%= fil[0].toString() %>"/>
                                    <x:submit property="acc" value="Nueva Compra"/>
                                </x:form>
                            </div>
                        </li>

                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                Consultas
                            </a>
                            <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                                <a class="dropdown-item" href="#">Articulos</a>
                                <div class="dropdown-divider"></div>
                                <x:form action="ArticuloControl">
                                    <x:submit property="acc" value="Nuevo Mantenimiento"/>
                                </x:form>
                            </div>
                        </li>

                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                Consultas
                            </a>
                            <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                                <a class="dropdown-item" href="#">Existencias</a>
                                <div class="dropdown-divider"></div>
                                <a class="dropdown-item" href="#">Otros</a>
                            </div>
                        </li>
                        <li class="nav-item active">
                            <a class="nav-link" href="Portal.jsp">Salir</a>
                        </li>
                    </ul>
                </div>
            </nav>
        </div>

        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx" crossorigin="anonymous"></script>

    </body>
</html>
