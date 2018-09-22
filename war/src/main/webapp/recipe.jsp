<%@ page import="com.recipes.entities.Recipe" %>
<%@ page import="org.apache.commons.lang3.StringUtils" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.text.DateFormat" %><%--
  Created by IntelliJ IDEA.
  User: archie
  Date: 17/09/18
  Time: 21:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Title</title>
  <link rel="stylesheet" href="resources/css/recipe.css">
  <link href="https://fonts.googleapis.com/css?family=Open+Sans" rel="stylesheet">
</head>
<%
  Recipe recipe = (Recipe) request.getAttribute("recipe");
  String user = recipe.getUserId().getName();
  DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
  String date = df.format(recipe.getCreatedAt());
%>
<body>
<%@include file="header.jsp" %>
<div class="container">
  <section class="articles">
    <div class="column is-8 is-offset-2">
      <div class="card article">
        <div class="card-content">
          <div class="media">
            <div class="media-content has-text-centered">
              <p class="title article-title"><%=recipe.getName()%></p>
              <div class="tags has-addons level-item">
                <span class="tag is-rounded is-info">@<%=user%></span>
                <span class="tag is-rounded"><%=date%></span>
              </div>
            </div>
          </div>
          <div class="content article-body">
            <p><%= recipe.getDescription() %></p>
            <p><%= recipe.getPreparation() %></p>
          </div>
        </div>
      </div>
    </div>
  </section>
</div>
</body>
</html>
