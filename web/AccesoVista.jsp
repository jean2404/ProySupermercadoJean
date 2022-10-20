<%-- 
    Document   : AccesoVista
    Created on : 24-jun-2021, 19:22:53
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
                <p class="text-center tituloF">Acceso al Sistema<p>            	
                <x:form action="AccesoControl" method="post">
                    <div class="form-group row">
                        <label class="col-sm-3 col-form-label lab">Usuario</label>
                        <div class="col-sm-9">
                            <x:text property="usu" />
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="col-sm-3 col-form-label lab">Password</label>
                        <div class="col-sm-9">
                            <x:password property="pas" /> 
                        </div>
                    </div>
                    <div class="form-group row">
                        <div class="col-sm-6 mx-auto">
                            <x:submit value="Iniciar Sesion" />
                            <a href="Portal.jsp" class="btn btn-light">Volver</a>
                        </div>
                    </div>

                </x:form>
            </div>
        </div>

        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx" crossorigin="anonymous"></script>

    </body>
</html>
