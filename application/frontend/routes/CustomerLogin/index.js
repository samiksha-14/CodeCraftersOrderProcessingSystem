document.getElementById("loginForm").addEventListener("submit", function (e) {
  e.preventDefault();

  var customerId = document.getElementById("customerId").value;
  var password = document.getElementById("password").value;

  fetch("../../assets/data/customers.json")
    .then((response) => response.json())
    .then((data) => {
      var user = data["customers"].find(
        (u) =>
          (u.customer_id === customerId || u.name === customerId) &&
          u.password === password,
      );

      console.log(user);
      if (user) {
        // window.location.href = `../OrderManagementEmployee/orderManagementEmployee.html?employeeId=${user.name}&password=${user.password}`;
        alert("Successful Login");
      } else {
        alert("Invalid credentials. Please try again.");
      }
    });
});
