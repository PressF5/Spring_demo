<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<style>
body {
    background: linear-gradient(to right,#2c5364, #203a43, #0f2027);
}

.button {
  display: inline-block;
  border-radius: 4px;
  background-color: #008CBA;
  border: none;
  color: #FFFFFF;
  text-align: center;
  font-size: 28px;
  padding: 20px;
  width: 80%;
  transition: all 0.5s;
  cursor: pointer;
  margin-bottom: 5px;
}

.button span {
  cursor: pointer;
  display: inline-block;
  position: relative;
  transition: 0.5s;
}

.button span:after {
  content: '\00bb';
  position: absolute;
  opacity: 0;
  top: 0;
  right: -20px;
  transition: 0.5s;
}

.button:hover span {
  padding-right: 25px;
}

.button:hover span:after {
  opacity: 1;
  right: 0;
}

.parent {
  text-align: center;
  width: 40%;
  position: absolute;
  left: 50%;
  top: 25%;
  transform: translate(-50%, -50%);
}
h1{
  text-align: center;
  color: lightblue;
}
</style>
</head>
<body>
<div class=parent>
<h1>Make a choose</h1>

        <button class="button" onclick="window.location.href='addInventory'" style="vertical-align:middle"><span>Add inventory to the office </span></button>

        <button class="button" onclick="window.location.href='moveInventory'" style="vertical-align:middle"><span>Move inventory from the office to the office </span></button>

        <button class="button" onclick="window.location.href='removeInventory'" style="vertical-align:middle"><span>Remove inventory from the office </span></button>

        <button class="button" onclick="window.location.href='search'" style="vertical-align:middle"><span>Search </span></button>

        </div>
    </body>
</html>