<%--
  Created by IntelliJ IDEA.
  User: archie
  Date: 26/09/18
  Time: 18:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Create recipe</title>
</head>
<body>
<%@include file="header.jsp" %>
<form action="createRecipe" method="post">
  <div class="field is-horizontal">
    <div class="field-label is-normal">
      <label class="label">Recipe Name</label>
    </div>
    <div class="field-body">
      <div class="control">
        <input class="input" type="text" placeholder="e.g Currywurst">
      </div>
    </div>
  </div>
  <div class="field is-horizontal">
    <div class="field-label is-normal">
      <label class="label">Description</label>
    </div>
    <div class="field-body is-small">
      <div class="field">
        <div class="control">
            <textarea class="textarea is-small"
                      placeholder="e.g. Fast food dish of German origin consisting of steamed, then fried pork sausage"></textarea>
        </div>
      </div>
    </div>
  </div>
  <div class="field is-horizontal">
    <div class="field-label is-normal">
      <label class="label">Recipe Preparation</label>
    </div>
    <div class="field-body">
      <div class="field">
        <div class="control">
          <textarea class="textarea"></textarea>
        </div>
      </div>
    </div>
  </div>
</form>

</body>
</html>
