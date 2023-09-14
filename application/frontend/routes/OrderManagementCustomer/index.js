document.addEventListener("DOMContentLoaded", function () {
  function goToOrderDetails(orderId) {
    window.location.href = `../OrderDetails/orderDetails.html?order_id=${orderId}`;
  }

  // Function to update the order status to "approved" in the JSON file
  function approveOrder(orderId) {
    // Fetch orders from the JSON file
    fetch("../../assets/data/orders.json")
      .then((response) => response.json())
      .then((data) => {
        // Find the order based on the order ID
        const order = data.orders.find((order) => order.order_id === orderId);

        if (order) {
          // Update the status to "approved"
          order.status = "approved";

          // Update the JSON file with the modified data
          fetch("../../assets/data/orders.json", {
            method: "PUT", // Use PUT to update the file
            body: JSON.stringify(data),
            headers: {
              "Content-Type": "application/json",
            },
          })
            .then(() => {
              // Refresh the page to reflect the updated status
              location.reload();
            })
            .catch((error) => {
              console.error("Error updating order status:", error);
            });
        } else {
          alert("Order not found.");
        }
      })
      .catch((error) => {
        console.error("Error fetching orders:", error);
      });
  }

  // Fetch orders from the JSON file
  fetch("../../assets/data/orders.json")
    .then((response) => response.json())
    .then((data) => {
      const orderList = document.getElementById("order-list");

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

                        <!-- Clickable item for detailed view -->
                        <div class="row mt-2 cursor-pointer" data-orderid="${
                          order.order_id
                        }">
                            <div class="col-md-6">
                              <button type="button" class="btn btn-primary view-details">
                                    View Details
                                </button>
                                <!-- "Approve" button -->
                                ${
                                  order.status === "pending"
                                    ? `
                                    <button
                                        type="button"
                                        class="btn btn-success approve-order"
                                        data-orderid="${order.order_id}"
                                    >
                                        Approve
                                    </button>
                                `
                                    : ""
                                }
                            </div>
                        </div>
                    </li>
                `;

        orderList.innerHTML += orderItem;
      });
    });

  // // Handle the "View Details" button click to navigate to order details page
  // const viewDetailsButtons = document.querySelectorAll(".view-details");
  // viewDetailsButtons.forEach((button) => {
  //   button.addEventListener("click", function () {
  //     const orderId =
  //       this.parentElement.parentElement.getAttribute("data-orderid");
  //     goToOrderDetails(orderId);
  //   });
  // });

  // // Handle the "Approve" button click to change the order status
  // const approveOrderButtons = document.querySelectorAll(".approve-order");
  // approveOrderButtons.forEach((button) => {
  //   button.addEventListener("click", function () {
  //     const orderId = this.getAttribute("data-orderid");
  //     approveOrder(orderId);
  //   });
  // });

  const contain = document.querySelector("#order-list");
  contain.addEventListener("click", function (e) {
    if (e.target.classList.contains("view-details")) {
      const orderId =
        e.target.parentElement.parentElement.getAttribute("data-orderid");
      goToOrderDetails(orderId);
    }

    if (e.target.classList.contains("approve-order")) {
      const orderId =
        e.target.parentElement.parentElement.getAttribute("data-orderid");
      approveOrder(orderId);
    }
  });

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
