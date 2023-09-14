document.addEventListener("DOMContentLoaded", function () {
  // Fetch orders from the JSON file
  fetch("../../assets/data/orders.json")
    .then((response) => response.json())
    .then((data) => {
      data.orders.forEach(function (order) {
        var orderItem = `
                    <li class="list-group-item">
                        <!-- Order details -->
                        <div class="row">
                            <div class="col-md-2">Order ID: <span class="order-id">${
                              order.order_id
                            }</span></div>
                            <div class="col-md-2">Customer: <span class="customer-name">${
                              order.customer_name
                            }</span></div>
                            <div class="col-md-2">Order Date: <span class="order-date">${
                              order.order_date
                            }</span></div>
                            <div class="col-md-2">Order Value: <span class="order-value">$${order.total_order_value.toFixed(
                              2,
                            )}</span></div>
                            <div class="col-md-2">City: <span class="customer-city">${
                              order.customer_shipping_address
                            }</span></div>
                            <div class="col-md-2">Status: <span class="order-status">${
                              order.status
                            }</span></div>
                        </div>

                        <!-- "View Invoice" button -->
                        <div class="row mt-2">
                            <div class="col-md-6">
                                ${
                                  order.status === "approved" ||
                                  order.status === "completed"
                                    ? `
                                    <button
                                        type="button"
                                        class="btn btn-primary view-invoice"
                                        data-toggle="modal"
                                        data-target="#invoiceModal"
                                        data-orderid="${order.order_id}"
                                    >
                                        View Invoice
                                    </button>
                                `
                                    : ""
                                }
                            </div>
                        </div>
                    </li>
                `;
        document.getElementById("order-list").innerHTML += orderItem;
      });
    });

  // // Handle the View Invoice button click to populate the modal
  // document.addEventListener("click", function (event) {
  //   if (event.target.classList.contains("view-invoice")) {
  //     var orderData = JSON.parse(event.target.getAttribute("data-order"));
  //     // Update the modal with orderData, similar to the previous example
  //     document.querySelector("#invoiceModal .modal-title").textContent =
  //       "Invoice for Order ID: " + orderData.order_id;
  //     // Populate the modal with invoice details here
  //   }
  // });

  function displayInvoice(orderId) {
    // Fetch invoices from the JSON file
    fetch("../../assets/data/invoices.json")
      .then((response) => response.json())
      .then((data) => {
        // Find the invoice based on the order ID
        const invoice = data.invoices.find(
          (inv) => inv.order_details.order_id === orderId,
        );

        if (invoice) {
          // Populate the modal with invoice details
          document.querySelector("#invoiceModal .modal-title").textContent =
            "Invoice for Order ID: " + invoice.order_details.order_id;

          // Create an HTML structure to display all invoice details
          const invoiceDetailsHtml = `
                        <p><strong>Order Date:</strong> ${
                          invoice.order_details.order_date
                        }</p>
                        <p><strong>Customer Name:</strong> ${
                          invoice.customer_details.customer_name
                        }</p>
                        <p><strong>Customer GST Number:</strong> ${
                          invoice.customer_details.gst_number
                        }</p>
                        <p><strong>Shipping Address:</strong> ${
                          invoice.customer_details.address
                        }</p>
                        <p><strong>City:</strong> ${
                          invoice.customer_details.city
                        }</p>
                        <p><strong>Email:</strong> ${
                          invoice.customer_details.email
                        }</p>
                        <p><strong>Phone Number:</strong> ${
                          invoice.customer_details.phone_number
                        }</p>
                        <p><strong>Pin Code:</strong> ${
                          invoice.customer_details.pin_code
                        }</p>
                        <hr>
                        <h4>Products:</h4>
                        <ul>
                            ${invoice.products
                              .map(
                                (product) => `
                                <li>
                                    <strong>Product ID:</strong> ${
                                      product.product_id
                                    }<br>
                                    <strong>Product Name:</strong> ${
                                      product.product_name
                                    }<br>
                                    <strong>Price:</strong> $${product.price.toFixed(
                                      2,
                                    )}<br>
                                    <strong>Quantity:</strong> ${
                                      product.quantity
                                    }<br>
                                </li>
                            `,
                              )
                              .join("")}
                        </ul>
                        <hr>
                        <p><strong>GST Type:</strong> ${invoice.gst_type}</p>
                        <p><strong>Total GST Amount:</strong> $${invoice.total_gst_amount.toFixed(
                          2,
                        )}</p>
                        <p><strong>Total Invoice Value:</strong> $${invoice.total_invoice_value.toFixed(
                          2,
                        )}</p>
                        <p><strong>Status:</strong> ${invoice.status}</p>
                    `;

          // Set the modal body content with the invoice details
          document.querySelector("#invoiceModal .modal-body").innerHTML =
            invoiceDetailsHtml;
        } else {
          alert("Invoice not found for the selected order ID.");
        }
      })
      .catch((error) => {
        console.error("Error fetching invoices:", error);
      });
  }

  function getUrlParameter(name) {
    name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
    const regex = new RegExp("[\\?&]" + name + "=([^&#]*)");
    const results = regex.exec(location.search);
    return results === null
      ? ""
      : decodeURIComponent(results[1].replace(/\+/g, " "));
  }

  // function getUrlParameter(parameterName) {
  //   // Get the URL query string
  //   const queryString = window.location.search;
  //
  //   // Create a URLSearchParams object from the query string
  //   const searchParams = new URLSearchParams(queryString);
  //
  //   // Use the get() method to retrieve the value of the specified parameter
  //   const parameterValue = searchParams.get(parameterName);
  //
  //   return parameterValue;
  // }

  const employeeId = getUrlParameter("employeeId");
  const password = getUrlParameter("password");

  if (employeeId && password) {
    document.getElementById(
      "employeeId",
    ).textContent = `Employee ID : ${employeeId}`;
    document.getElementById(
      "password",
    ).textContent = `Last Logged In : ${password}`;
  }

  const viewInvoiceButtons = document.querySelectorAll(".view-invoice");
  viewInvoiceButtons.forEach((button) => {
    button.addEventListener("click", function () {
      const orderId = this.getAttribute("data-orderid");
      displayInvoice(orderId);
    });
  });
});
