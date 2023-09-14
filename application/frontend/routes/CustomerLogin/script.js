 const button = document.querySelector(".btn");
   function validateLogin() {
            const users = [
                { id: '123', username: 'user1', password: 'password1' },
                { id: '456', username: 'user2', password: 'password2' },
                { id: '789', username: 'user3', password: 'password3' }
            ];

            const input = document.getElementById('customerId').value;
            const password = document.getElementById('password').value;

            // Check if input matches either ID or Username
            const user = users.find(u => u.id === input || u.username === input);

            if (user && user.password === password) {
                alert('Login successful');
                // You can redirect the user to another page here
            } else {
                alert('Login failed. Please check your credentials.');
            }
        }
button.addEventListener("click", validateLogin);
