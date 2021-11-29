<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<style>

body {
    background: linear-gradient(to right,#2c5364, #203a43, #0f2027);
}

.button {
  background-color: #008CBA;
  border: none;
  color: white;
  padding: 16px 32px;
  text-align: center;
  font-size: 16px;
  margin: 4px 2px;
  opacity: 0.6;
  transition: 0.3s;
  display: inline-block;
  text-decoration: none;
  cursor: pointer;
  width: 100%;
  border-radius: 4px;
}

.button:hover {opacity: 1}

input {
  border: 2px solid #ccc;
  padding: 10px;
  box-sizing: border-box;
  background-color: lightblue;
  border-radius: 4px;
  width: 100%;
}

textarea {
  border: 2px solid #ccc;
  padding: 10px;
  box-sizing: border-box;
  background-color: lightblue;
  border-radius: 4px;
  width: 100%;
  resize: vertical;
  max-height: 200px;
  min-height: 100px;
}

label {
  text-align: initial;
  display: inline-block;
  font-size: 16pt;
  color: lightblue;
}
.col-50 {
  float: left;
  width: 50%;
  margin-top: 6px;
}

.row:after {
  content: "";
  display: table;
  clear: both;
}

.container {
    width: 25%;
    position: absolute;
    left: 50%;
    top: 5%;
    transform: translate(-50%, 0%);
}

h1{
  text-align: center;
  color: lightblue;
}
</style>
    <body>
        <br>
        <br>
        <div class="container">
        <h1>Save/Update items</h1>
        <form:form action="saveInventory" modelAttribute="item">

        <form:hidden path="id"/>

<div class="row">
        <div class="col-50">
              <label>Inventory number</label>
        </div>
        <div class="col-50">
               <form:input type="number" placeholder="Number of items" path="number"/>
        </div>
</div>

<div class="row">
        <div class="col-50">
               <label>Inventory description</label>
        </div>
        <div class="col-50">
               <form:textarea placeholder="Description" path="description"/>
        </div>
</div>

<div class="row">
        <div class="col-50">
               <label>Count of items</label>
        </div>
        <div class="col-50">
               <form:input type="number" placeholder="Count of items" path="countItems"/>
        </div>
</div>

<div class="row">
        <div class="col-50">
               <label>Office number</label>
        </div>
        <div class="col-50">
               <form:input type="number" placeholder="Office number" path="office.officeNumber"/>
        </div>
</div>
        <div class="row">
        <button class="button" type="submit">Save</button>
        </div>
        </form:form>
        </div>
    </body>
</html>