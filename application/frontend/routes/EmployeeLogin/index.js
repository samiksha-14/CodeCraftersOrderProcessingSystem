document.getElementById("loginForm").addEventListener("submit", function (e) {
  e.preventDefault();

  var employeeId = document.getElementById("employeeId").value;
  var password = document.getElementById("password").value;

  fetch("../../assets/data/employees.json")
    .then((response) => response.json())
    .then((data) => {
      var user = data["employees"].find(
        (u) => u.employeeId == employeeId && u.password == password,
      );

      console.log(user);
      if (user) {
        window.location.href = `../OrderManagementEmployee/orderManagementEmployee.html?employeeId=${user.employeeId}&password=${user.password}`;
      } else {
        alert("Invalid credentials. Please try again.");
      }
    });
});
