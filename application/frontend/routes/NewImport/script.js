document.addEventListener("DOMContentLoaded", function () {
    const fileInput = document.getElementById("fileInput");
    const uploadButton = document.getElementById("uploadButton");
    const status = document.getElementById("status");

    uploadButton.addEventListener("click", function () {
        const file = fileInput.files[0];

        if (file) {
            const reader = new FileReader();

            reader.onload = function (e) {
                try {
                    const data = JSON.parse(e.target.result);

                    // Process the data from the uploaded JSON file
                    const processedData = processData(data);

                    // Display the status
                    status.innerHTML = `
                        Status: Completed<br>
                        Number of products imported: ${processedData.importedProducts}<br>
                        Number of products not imported: ${processedData.notImportedProducts}<br>
                        Number of products partially imported: ${processedData.partialImportedProducts}
                    `;

                    // Generate a new JSON file with the categorized data
                    downloadJSON(processedData.categorizedData, "dummydata.json");
                } catch (error) {
                    status.textContent = "Error: Invalid JSON format.";
                }
            };

            reader.readAsText(file);
        } else {
            status.textContent = "Please select a file.";
        }
    });

  function processData(data) {
    let importedProducts = 0;
    let notImportedProducts = 0;
    let partialImportedProducts = 0;
    let importStatus = "Completed"; // Default to "Completed"

    // Categorized data
    const categorizedData = {
        "Level 1": [],
        "Level 2": [],
        "Level 3": [],
    };

    data.forEach((product) => {
        if (isValidProduct(product)) {
            importedProducts++;
            categorizedData[product.category].push(product);
        } else {
            notImportedProducts++;
        }
    });

    // Calculate the number of partially imported products (if needed)
    // ...

    // Determine the import status
    if (notImportedProducts > 0) {
        importStatus = "Failed";
    } else if (partialImportedProducts > 0) {
        importStatus = "Partial";
    }

    // Display the status
    status.innerHTML = `
        Status: ${importStatus}<br>
        Number of products imported: ${importedProducts}<br>
        Number of products not imported: ${notImportedProducts}<br>
        Number of products partially imported: ${partialImportedProducts}
    `;

    // Return the results
    return {
        importedProducts,
        notImportedProducts,
        partialImportedProducts,
        importStatus,
        categorizedData,
    };
    // Calculate the number of partially imported products (if needed)
        // ...

        return {
            importedProducts,
            notImportedProducts,
            partialImportedProducts,
            categorizedData,
        };
}


        

    function isValidProduct(product) {
        // Implement your validation logic here
        // For this example, we assume all products are valid
        return true;
    }

    function downloadJSON(data, filename) {
        const blob = new Blob([JSON.stringify(data, null, 2)], { type: "application/json" });
        const url = URL.createObjectURL(blob);
        const a = document.createElement("a");
        a.href = url;
        a.download = filename;
        document.body.appendChild(a);
        a.click();
        window.URL.revokeObjectURL(url);
    }
});
