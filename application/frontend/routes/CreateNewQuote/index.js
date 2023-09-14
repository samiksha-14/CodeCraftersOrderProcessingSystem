document.addEventListener("DOMContentLoaded", function () {
  const form = document.getElementById("quoteForm");
  const customerNameOrIDInput = document.getElementById("customerNameOrID");
  const customerGSTInput = document.getElementById("customerGST");
  const customerAddressInput = document.getElementById("customerAddress");
  const customerCityInput = document.getElementById("customerCity");
  const customerPhoneInput = document.getElementById("customerPhone");
  const customerEmailInput = document.getElementById("customerEmail");
  const customerPinCodeInput = document.getElementById("customerPinCode");

  // customer data (JSON)
  var customers;

  fetch("../../assets/data/customers.json")
    .then((response) => response.json())
    .then((data) => (customers = data.customers));

  // Function to populate customer data based on name or ID
  function populateCustomerData(inputValue) {
    const customer = customers.find(
      (c) => c.name === inputValue || c.customer_id === inputValue,
    );

    if (customer) {
      customerGSTInput.value = customer.gst_number;
      customerAddressInput.value = customer.address;
      customerCityInput.value = customer.city;
      customerPhoneInput.value = customer.phone_number;
      customerEmailInput.value = customer.email;
      customerPinCodeInput.value = customer.pin_code;
    } else {
      // Clear the fields if customer not found
      customerGSTInput.value = "";
      customerAddressInput.value = "";
      customerCityInput.value = "";
      customerPhoneInput.value = "";
      customerEmailInput.value = "";
      customerPinCodeInput.value = "";
    }
  }

  // Event listener to populate customer data on input change
  customerNameOrIDInput.addEventListener("input", function () {
    const inputValue = customerNameOrIDInput.value;
    populateCustomerData(inputValue);
  });

  // Function to handle form submission
  form.addEventListener("submit", function (event) {
    event.preventDefault(); // Prevent the form from actually submitting

    const customerNameOrID = customerNameOrIDInput.value;
    const productList = productListSelect.value;
    const productQuantity = parseInt(productQuantityInput.value, 10);

    if (
      !customerNameOrID ||
      !productList ||
      isNaN(productQuantity) ||
      productQuantity <= 0
    ) {
      alert(
        "Please fill in all mandatory fields and provide a valid quantity.",
      );
      return; // Prevent further processing if validation fails
    }

    // Display shipping cost and total order value
    document.getElementById("shippingCost").textContent = "Calculated Value";
    document.getElementById("totalOrderValue").textContent = "Calculated Value";
  });
});
