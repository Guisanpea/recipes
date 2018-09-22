<%@ page import="static java.util.Objects.nonNull" %><%--
  Created by IntelliJ IDEA.
  User: archie
  Date: 17/09/18
  Time: 20:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Login</title>
  <link rel="stylesheet" href="node_modules/@fortawesome/fontawesome-free/css/all.css">
  <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,400,700" rel="stylesheet">
  <link rel="stylesheet" href="node_modules/bulma/css/bulma.css">
  <link rel="stylesheet" href="resources/css/login.css">
</head>

<body>
<section class="hero is-success is-fullheight">
  <div class="hero-body">
    <div class="container has-text-centered">
      <div class="column is-4 is-offset-4">
        <h3 class="title has-text-grey">Login</h3>
        <p class="subtitle has-text-grey">Please login to proceed.</p>
        <div class="box">
          <figure class="avatar">
            <img src="resources/user_icon.png">
          </figure>
          <form action="login" method="post">
            <div class="field">
              <div class="control">
                <input class="input is-large" type="text" placeholder="Your username" autofocus="" name="username">
              </div>
            </div>

            <div class="field">
              <div class="control">
                <input class="input is-large" type="password" placeholder="Your Password" name="password">
              </div>
            </div>
            <button class="button is-block is-primary is-large is-fullwidth"><a href="login">Log in</a></button>
          </form>
          <% if ( nonNull(request.getAttribute("incorrectLogin"))) { %>
          <hr/>
          <div class="box is-danger">Incorrect username and password combination</div>
          <% } %>
        </div>
        <hr/>
        <p class="has-text-grey">
          <a href="register.jsp" class="is-large">Sign Up</a>
        </p>


      </div>
    </div>
  </div>
</section>
</body>

</html>
