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
<div class="container">
    <h1>Remove items</h1>
    <form action="removedInventory">
    <div class="row">
            <div class="col-50">
        <label>From office</label>
            </div>
            <div class="col-50">
        <input type="number" name="fromOffice" placeholder="From office"/>
            </div>
    </div>
    <div class="row">
            <div class="col-50">
        <label>Inventory number</label>
            </div>
            <div class="col-50">
        <input type="number" name="invNumber" placeholder="Inventory number"/>
            </div>
    </div>
    <div class="row">
            <div class="col-50">
        <label>Count items</label>
            </div>
            <div class="col-50">
        <input type="number" name="countItems" placeholder="Count items"/>
            </div>
    </div>
        <button class="button" type="submit">Remove</button>
    </form>
</div>
</body>
</html>