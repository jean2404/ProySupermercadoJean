<%-- 
    Document   : AccesoMensaje
    Created on : 24-jun-2021, 19:23:40
    Author     : sagit
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
        <link rel="preconnect" href="https://fonts.gstatic.com">
        <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@100&display=swap" rel="stylesheet">
        <style type="text/css">
            .tituloF{
                font-family: 'Roboto', sans-serif;
                font-size: 1.5rem;
                color: black;
                font-weight: bold;
            }
            .lab{
                font-family: 'Roboto', sans-serif;
                font-size: 1.2rem;
                font-weight: bold;
                color: black;
            }
            body{
                background-image: url(men.jpg);
                background-size: cover;
            }
        </style>
    </head>
    <body>

        <div class="container">
            <div class="" style="margin-top: 200px">
                <div class="row">
                    <div class="col-sm-6 mx-auto p-3">
                        <p class="text-center tituloF">Mensaje del Sistema</p> 
                        <% String msg = (String) session.getAttribute("msg");%>
                        <p class="lab">
                            Mensaje: <%= msg%> 
                        </p>
                    </div>
                </div>
                <div class="row">
                    <div class="col-sm-4 mx-auto">
                        <a href="AccesoVista.jsp" class="btn btn-secondary w-100">Volver a intentar</a> 
                    </div>
                </div>

            </div>	
        </div> 

        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx" crossorigin="anonymous"></script>

    </body>
</html>
