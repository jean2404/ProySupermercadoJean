<%-- 
    Document   : Portal
    Created on : 24-jun-2021, 19:17:28
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
            body{
                background-image: url(fondoSuper.jpg);
                background-size: cover;
            }
            img{
                height: 100%;
            }
            .fondito{
                background-color: ghostwhite;
                height: 100%;
            }
            .roboto{
                font-family: 'Roboto', sans-serif;
            }
        </style>
    </head>
    <body>
        <div class="container">
            <div class="card mb-3 mx-auto" style="max-width: 540px; top: 200px;">
                <div class="row no-gutters">
                    <div class="col-md-5">
                        <img src="vendedor.jpg" class="card-img" alt="...">
                    </div>
                    <div class="col-md-7">
                        <div class="card-body fondito">
                            <h5 class="card-title text-center roboto" style="
                                font-size: 1.4rem;">SISTEMA SUPERMERCADO JV FRAMEWORKS</h5>
                            <a href="AccesoVista.jsp" class="btn btn-outline-dark w-100 roboto">Acceso al Sistema - Iniciar Sesión</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>


        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx" crossorigin="anonymous"></script>

    </body>
</html>
