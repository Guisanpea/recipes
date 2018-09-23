<%@ page import="com.recipes.entities.Recipe" %>
<%@ page import="java.util.List" %>
<%@ page import="org.apache.commons.lang3.StringUtils" %><%--
  Created by IntelliJ IDEA.
  User: archie
  Date: 17/09/18
  Time: 20:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <meta charset="UTF-8">
  <title>Recipes</title>
  <link rel="stylesheet" href="resources/css/recipes.css">
</head>

<%
  List<Recipe> recipeList = (List<Recipe>) request.getAttribute("recipeList");
%>

<body>
<%@include file="header.jsp" %>
<hr/>
<div class="container">
  <h1 class="title">Recipes</h1>
  <%
    for (Recipe recipe :
        recipeList) {
        String recipeResume = recipe.getDescription().substring(0, 100) + "...";
        String categories = StringUtils.join(recipe.getCategoryList(), " - ");
  %>
  <div class="box">
    <h1 class="title"><%=recipe.getName()%></h1>
    <h2 class="subtitle"><%=categories%> - <%=recipe.getLevelId().getName()%></h2>

      <p><%=recipeResume%></p>

    <button class="button is-primary"><a href="recipe?id=<%=recipe.getId()%>"></a> </button>
  </div>
  <%
    }
  %>
</div>
<hr/>

</body>

</html>
