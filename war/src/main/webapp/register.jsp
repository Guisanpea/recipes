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
  <title>Register</title>
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
        <h3 class="title has-text-grey">Register</h3>
        <p class="subtitle has-text-grey">Please register to proceed.</p>
        <div class="box">
          <figure class="avatar">
            <img src="resources/user_icon.png">
          </figure>
          <form action="register" method="post">
            <div class="field">
              <div class="control">
                <input class="input is-large" type="text" placeholder="Your username" name="username" autofocus="">
              </div>
            </div>
            <div class="field">
              <div class="control">
                <input class="input is-large" type="password" placeholder="Your Password" name="password">
              </div>
            </div>
            <div class="field">
              <div class="control">
                <input class="input is-large" type="text" placeholder="Your full name" name="fullName">
              </div>
            </div>
            <div>
              <label for="start" class="is-medium">Birth date</label>
              <input type="date" id="start" name="birthDate" value="1998-06-25"/>
            </div>
            <hr/>
            <button class="button is-block is-primary is-large is-fullwidth">Sign up</button>
          </form>
          <% if ( nonNull(request.getAttribute("incorrectRegister"))) { %>
          <hr/>
          <div class="box is-danger">Username already exists</div>
          <% } %>
        </div>
      </div>
    </div>
  </div>
</section>
</body>

</html>
