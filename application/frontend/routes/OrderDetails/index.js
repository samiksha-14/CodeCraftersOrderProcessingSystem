document.addEventListener("DOMContentLoaded", function () {
  // Function to extract query parameters from URL
  function getParameterByName(name, url) {
    if (!url) url = window.location.href;
    name = name.replace(/[[\]]/g, "\\$&");
    var regex = new RegExp("[?&]" + name + "(=([^&#]*)|&|#|$)"),
      results = regex.exec(url);
    if (!results) return null;
    if (!results[2]) return "";
    return decodeURIComponent(results[2].replace(/\+/g, " "));
  }

  // Get the order ID from the URL
  const orderId = getParameterByName("order_id");

  // Fetch orders from the JSON file
  fetch("../../assets/data/orders.json")
    .then((response) => response.json())
    .then((data) => {
      const orderDetails = document.getElementById("order-details");

      // Find the order based on the order ID
      const order = data.orders.find((order) => order.order_id === orderId);

      if (order) {
        // Create HTML structure to display order details
        const orderDetailsHtml = `
                    <div class="row">
                        <div class="col-md-6">
                            <strong>Order ID:</strong> ${order.order_id}<br>
                            <strong>Customer Name:</strong> ${order.customer_name}<br>
                            <strong>Order Date:</strong> ${order.order_date}<br>
                            <strong>Shipping Address:</strong> ${order.customer_shipping_address}<br>
                            <strong>Total Order Value:</strong> ${order.total_order_value}<br>
                            <strong>Shipping Cost:</strong> ${order.shipping_cost}<br>
                            <strong>Shipping Agency:</strong> ${order.shipping_agency}<br>
                            <strong>status:</strong> ${order.status}<br>
                            <!-- Add other order details here -->
                        </div>
                    </div>
                `;

        // Populate the order details container
        orderDetails.innerHTML = orderDetailsHtml;
      } else {
        alert("Order not found.");
      }
    })
    .catch((error) => {
      console.error("Error fetching orders:", error);
    });
});
