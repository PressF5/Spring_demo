<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<style>
body {
    background: linear-gradient(to right,#2c5364, #203a43, #0f2027);
}
.button {
  background-color: #008CBA;
  border: none;
  color: white;
  padding: 12px;
  text-align: center;
  font-size: 16px;
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

  background-image: url('https://icons.iconarchive.com/icons/jommans/briefness/16/Search-icon.png');
  background-position: 5px 11px;
  background-repeat: no-repeat;
  padding: 12px 10px 10px 25px;
}

select {
  border: 2px solid #ccc;
  padding: 10px;
  box-sizing: border-box;
  background-color: lightblue;
  border-radius: 4px;
  width: 100%;
}

label {
  padding: 6px 0px 25px 0;
  display: inline-block;
  font-size: 15pt;
  color: lightblue;
}

.col-70-left {
    float: left;
    width: 70%;
    margin-left: 1%;
}

.col-15-left {
    float: left;
    width: 15%;
    margin: 0% 0.8%;
}

.col-12-right {
    float: right;
    width: 12%;
}

.row:after {
  content: "";
  display: table;
  clear: both;
}

.container {
  width: 60%;
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
<div class="container">
    <h1>Search</h1>
    <form action="searched" method="post">
    <div class="row">
         <div class="col-70-left">
              <input type="number" name="officeOrInvNumber" placeholder="Number of office or inventory number"/>
         </div>
         <div class="col-15-left">
                <select name="choice">
                  <option value="office">Office</option>
                  <option value="invNumber">Inventory number</option>
                </select>
         </div>
         <div class="col-12-right">
                <button class="button" type="submit">Search</button>
         </div>
    </div>
    </div>
    </form>
</div>
<br>
<br>
</body>
</html>