document.addEventListener('DOMContentLoaded', function() {
    const form = document.getElementById('quoteForm');
    const customerNameOrIDInput = document.getElementById('customerNameOrID');
    const customerGSTInput = document.getElementById('customerGST');
    const customerAddressInput = document.getElementById('customerAddress');
    const customerCityInput = document.getElementById('customerCity');
    const customerPhoneInput = document.getElementById('customerPhone');
    const customerEmailInput = document.getElementById('customerEmail');
    const customerPinCodeInput = document.getElementById('customerPinCode');
    
    // hardcoded customer data (JSON)
    const customers = [
        {
            id: 1,
            name: 'John Doe',
            gst: 'GST12345',
            address: '123 Main St',
            city: 'Cityville',
            phone: '555-123-4567',
            email: 'john@example.com',
            pinCode: '12345'
        },
         {
            id: 2,
            name: 'Jane Doe',
            gst: 'GST54345',
            address: '123 argo St',
            city: 'London',
            phone: '555-123-4567',
            email: 'jane@example.com',
            pinCode: '492932'
        },

    ];

    // Function to populate customer data based on name or ID
    function populateCustomerData(inputValue) {
        const customer = customers.find(c => c.name === inputValue || c.id === parseInt(inputValue, 10));
        
        if (customer) {
            customerGSTInput.value = customer.gst;
            customerAddressInput.value = customer.address;
            customerCityInput.value = customer.city;
            customerPhoneInput.value = customer.phone;
            customerEmailInput.value = customer.email;
            customerPinCodeInput.value = customer.pinCode;
        } else {
            // Clear the fields if customer not found
            customerGSTInput.value = '';
            customerAddressInput.value = '';
            customerCityInput.value = '';
            customerPhoneInput.value = '';
            customerEmailInput.value = '';
            customerPinCodeInput.value = '';
        }
    }

    // Event listener to populate customer data on input change
    customerNameOrIDInput.addEventListener('input', function() {
        const inputValue = customerNameOrIDInput.value;
        populateCustomerData(inputValue);
    });

    // Function to handle form submission
    form.addEventListener('submit', function(event) {
        event.preventDefault(); // Prevent the form from actually submitting
        
         const customerNameOrID = customerNameOrIDInput.value;
        const productList = productListSelect.value;
        const productQuantity = parseInt(productQuantityInput.value, 10);
        
        if (!customerNameOrID || !productList || isNaN(productQuantity) || productQuantity <= 0) {
            alert('Please fill in all mandatory fields and provide a valid quantity.');
            return; // Prevent further processing if validation fails
        }
        
        // Display shipping cost and total order value
        document.getElementById('shippingCost').textContent = 'Calculated Value';
        document.getElementById('totalOrderValue').textContent = 'Calculated Value';
    });
});
