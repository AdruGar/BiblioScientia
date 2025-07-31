
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="BiblioScientia Devs">
    <title>Registro · BiblioScientia</title>
    <link href="/bootstrap/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="py-4">
    <main>
        <div class="container">
            <h1 class="rounded">Crea tu cuenta</h1>
            <p class="lead">Únete y crea tu propia biblioteca de bolsillo</p>
            <p>Es totalmente gratuita, con tu cuenta podrás gestionar tus archivos, compartir y guardar información nunca fue tan fácil.</p>
            <ul class="nav nav-tabs" role="tablist">
                <li class="nav-item"> <a class="nav-link active" href="#" role="tab">Crea tu Usuario</a> </li>
                <li class="nav-item"> <a class="nav-link" href="#" role="tab">Configura tu cuenta</a> </li>
                <li class="nav-item"> <a class="nav-link" href="#" role="tab">Gestiona tu librería</a> </li>
                <li class="nav-item"> <a class="nav-link" href="#" role="tab">Bienvenido</a> </li>
            </ul>
        </div>

        <div class="container" id="containers">
            <hr class="my-4">
            <form action="/OficinaMySQL/registro" method="post" class="align-self-start float-none h-auto w-50" style="margin-left: 237px;">
                <div class="mb-3">
                    <label for="email">Ingresa tu dirección de correo</label>
                    <input type="email" class="form-control" name="email" id="email" placeholder="Enter email" required>
                </div>

                <div class="mb-3">
                    <label for="password">Crea tu contraseña</label>
                    <input type="password" class="form-control" name="password" id="password" placeholder="Password" required>
                    <div class="form-text">
                        Tu contraseña debe tener entre 8 y 20 caracteres, incluir letras y números, y no debe contener espacios ni caracteres especiales.
                    </div>
                </div>

                <div class="mb-3">
                    <label for="rol">Escoge tu rol</label>
                    <select class="form-control" name="rol" id="rol" required>
                        <option value="Estudiante">Estudiante</option>
                        <option value="Profesor">Profesor</option>
                        <option value="Uso personal">Uso personal</option>
                        <option value="Otro">Otro</option>
                    </select>
                </div>

                <div class="mb-3 form-check">
                    <input type="checkbox" class="form-check-input" id="rememberMe">
                    <label class="form-check-label" for="rememberMe">Recuérdame</label>
                </div>

                <button type="submit" class="btn btn-primary">Registrar</button>
            </form>
        </div>
    </main>

    <script src="/assets/js/popper.min.js"></script>
    <script src="/bootstrap/js/bootstrap.min.js"></script>
</body>
</html>
