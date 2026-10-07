// ========================================
// BACKEND URL
// ========================================

const API_URL = "http://localhost:8082/api";


// ========================================
// LOAD DASHBOARD DATA
// ========================================

async function loadDashboard() {

    try {

        // Load passengers
        const passengerResponse = await fetch(`${API_URL}/passengers`);
        const passengers = await passengerResponse.json();

        document.getElementById("passengerCount").textContent =
            passengers.length;


        // Load flights
        const flightResponse = await fetch(`${API_URL}/flights`);
        const flights = await flightResponse.json();

        document.getElementById("flightCount").textContent =
            flights.length;


        // Load baggage
        const baggageResponse = await fetch(`${API_URL}/baggage`);
        const baggage = await baggageResponse.json();

        document.getElementById("baggageCount").textContent =
            baggage.length;


        // Load tracking
        const trackingResponse = await fetch(`${API_URL}/tracking`);
        const tracking = await trackingResponse.json();

        document.getElementById("trackingCount").textContent =
            tracking.length;

    } catch (error) {

        console.error("Dashboard error:", error);

    }
}


// ========================================
// LOAD BAGGAGE DETAILS
// JOIN QUERY
// ========================================

async function loadBaggageDetails() {

    const tableBody =
        document.getElementById("baggageTableBody");

    try {

        const response =
            await fetch(`${API_URL}/baggage/details`);

        if (!response.ok) {
            throw new Error("Failed to load baggage details");
        }

        const data = await response.json();

        tableBody.innerHTML = "";

        if (data.length === 0) {

            tableBody.innerHTML = `
                <tr>
                    <td colspan="5">
                        No baggage details found
                    </td>
                </tr>
            `;

            return;
        }


        data.forEach(row => {

            const baggageId = row[0];
            const passengerName = row[1];
            const flightNumber = row[2];
            const weight = row[3];
            const status = row[4];


            tableBody.innerHTML += `
                <tr>
                    <td>${baggageId}</td>
                    <td>${passengerName}</td>
                    <td>${flightNumber}</td>
                    <td>${weight}</td>
                    <td>
                        <span class="status">
                            ${status}
                        </span>
                    </td>
                </tr>
            `;

        });

    } catch (error) {

        console.error(error);

        tableBody.innerHTML = `
            <tr>
                <td colspan="5">
                    Unable to load baggage details.
                </td>
            </tr>
        `;
    }
}


// ========================================
// REGISTER BAGGAGE
// STORED PROCEDURE
// ========================================

document
    .getElementById("registerForm")
    .addEventListener("submit", async function(event) {

        event.preventDefault();


        const passengerId =
            document.getElementById("passengerId").value;

        const flightId =
            document.getElementById("flightId").value;

        const weight =
            document.getElementById("weight").value;


        const message =
            document.getElementById("registerMessage");


        try {

            const response = await fetch(
                `${API_URL}/baggage/register` +
                `?passengerId=${passengerId}` +
                `&flightId=${flightId}` +
                `&weight=${weight}`,
                {
                    method: "POST"
                }
            );


            if (!response.ok) {
                throw new Error("Registration failed");
            }


            const result = await response.text();

            message.textContent = result ||
                "Baggage registered successfully.";

            message.style.color = "green";


            // Clear form
            document.getElementById("registerForm").reset();


            // Refresh dashboard and baggage table
            loadDashboard();
            loadBaggageDetails();


        } catch (error) {

            console.error(error);

            message.textContent =
                "Failed to register baggage.";

            message.style.color = "red";
        }

    });


// ========================================
// UPDATE BAGGAGE STATUS
// TRIGGER WORKS IN DATABASE
// ========================================

document
    .getElementById("statusForm")
    .addEventListener("submit", async function(event) {

        event.preventDefault();


        const baggageId =
            document.getElementById("statusBaggageId").value;

        const status =
            document.getElementById("baggageStatus").value;


        const message =
            document.getElementById("statusMessage");


        try {

            const response = await fetch(
                `${API_URL}/baggage/${baggageId}/status` +
                `?status=${encodeURIComponent(status)}`,
                {
                    method: "PUT"
                }
            );


            if (!response.ok) {
                throw new Error("Status update failed");
            }


            message.textContent =
                "Baggage status updated successfully.";

            message.style.color = "green";


            document.getElementById("statusForm").reset();


            // Refresh baggage table
            loadBaggageDetails();

            // Refresh tracking count
            loadDashboard();

        } catch (error) {

            console.error(error);

            message.textContent =
                "Failed to update baggage status.";

            message.style.color = "red";
        }

    });


// ========================================
// LOAD EXCESS BAGGAGE CHARGES
// STORED FUNCTION
// ========================================

async function loadCharges() {

    const tableBody =
        document.getElementById("chargesTableBody");


    try {

        const response =
            await fetch(`${API_URL}/baggage/charges`);


        if (!response.ok) {
            throw new Error("Failed to load charges");
        }


        const data = await response.json();

        tableBody.innerHTML = "";


        if (data.length === 0) {

            tableBody.innerHTML = `
                <tr>
                    <td colspan="3">
                        No baggage charge data found.
                    </td>
                </tr>
            `;

            return;
        }


        data.forEach(row => {

            const baggageId = row[0];
            const weight = row[1];
            const charge = row[2];


            tableBody.innerHTML += `
                <tr>
                    <td>${baggageId}</td>
                    <td>${weight}</td>
                    <td>₹${charge}</td>
                </tr>
            `;

        });

    } catch (error) {

        console.error(error);

        tableBody.innerHTML = `
            <tr>
                <td colspan="3">
                    Unable to load excess baggage charges.
                </td>
            </tr>
        `;
    }
}


// ========================================
// LOAD FLIGHTS ABOVE AVERAGE BAGGAGE
// SUBQUERY
// ========================================

async function loadAboveAverageFlights() {

    const tableBody =
        document.getElementById("flightAnalysisBody");


    try {

        const response =
            await fetch(`${API_URL}/flights/above-average-baggage`);


        if (!response.ok) {
            throw new Error("Failed to load flight analysis");
        }


        const data = await response.json();

        tableBody.innerHTML = "";


        if (data.length === 0) {

            tableBody.innerHTML = `
                <tr>
                    <td colspan="5">
                        No flights found above average baggage.
                    </td>
                </tr>
            `;

            return;
        }


        data.forEach(row => {

            const flightId = row[0];
            const flightNumber = row[1];
            const source = row[2];
            const destination = row[3];
            const baggageCount = row[4];


            tableBody.innerHTML += `
                <tr>
                    <td>${flightId}</td>
                    <td>${flightNumber}</td>
                    <td>${source}</td>
                    <td>${destination}</td>
                    <td>${baggageCount}</td>
                </tr>
            `;

        });

    } catch (error) {

        console.error(error);

        tableBody.innerHTML = `
            <tr>
                <td colspan="5">
                    Unable to load flight analysis.
                </td>
            </tr>
        `;
    }
}


// ========================================
// LOAD ALL TRACKING RECORDS
// ========================================

async function loadTracking() {

    const tableBody =
        document.getElementById("trackingTableBody");


    try {

        const response =
            await fetch(`${API_URL}/tracking`);


        if (!response.ok) {
            throw new Error("Failed to load tracking");
        }


        const data = await response.json();

        displayTracking(data);

    } catch (error) {

        console.error(error);

        tableBody.innerHTML = `
            <tr>
                <td colspan="5">
                    Unable to load tracking records.
                </td>
            </tr>
        `;
    }
}


// ========================================
// SEARCH TRACKING BY BAGGAGE ID
// ========================================

async function searchTracking() {

    const baggageId =
        document.getElementById("trackingBaggageId").value;


    const tableBody =
        document.getElementById("trackingTableBody");


    if (!baggageId) {

        alert("Please enter a baggage ID.");

        return;
    }


    try {

        const response =
            await fetch(
                `${API_URL}/tracking/baggage/${baggageId}`
            );


        if (!response.ok) {
            throw new Error("Tracking search failed");
        }


        const data = await response.json();

        displayTracking(data);

    } catch (error) {

        console.error(error);

        tableBody.innerHTML = `
            <tr>
                <td colspan="5">
                    No tracking records found.
                </td>
            </tr>
        `;
    }
}


// ========================================
// DISPLAY TRACKING DATA
// ========================================

function displayTracking(data) {

    const tableBody =
        document.getElementById("trackingTableBody");


    tableBody.innerHTML = "";


    if (data.length === 0) {

        tableBody.innerHTML = `
            <tr>
                <td colspan="5">
                    No tracking records found.
                </td>
            </tr>
        `;

        return;
    }


    data.forEach(row => {

        /*
           Expected tracking object:

           trackingId
           baggageId
           status
           location
           trackingTime
        */


        const trackingId = row.trackingId;
        const baggageId = row.baggageId;
        const status = row.status;
        const location = row.location;
        const trackingTime = row.trackingTime;


        tableBody.innerHTML += `
            <tr>
                <td>${trackingId}</td>
                <td>${baggageId}</td>
                <td>
                    <span class="status">
                        ${status}
                    </span>
                </td>
                <td>${location || "Not available"}</td>
                <td>${trackingTime}</td>
            </tr>
        `;

    });
}


// ========================================
// PAGE LOAD
// ========================================

document.addEventListener("DOMContentLoaded", function() {

    loadDashboard();

    loadBaggageDetails();

});

//================================
//register passengers
//=================================

document.getElementById("passengerForm").addEventListener("submit", async function(event) {
    event.preventDefault();

    const name = document.getElementById("passengerName").value;
    const phone = document.getElementById("passengerPhone").value;
    const message = document.getElementById("passengerMessage");

    try {
        const response = await fetch(`${API_URL}/passengers`, {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({
                name: name,
                phone: phone
            })
        });

        if (!response.ok) {
            throw new Error("Failed to add passenger");
        }

        message.textContent = "Passenger added successfully!";
        message.style.color = "green";

        document.getElementById("passengerForm").reset();

        loadDashboard();

    } catch (error) {
        message.textContent = "Failed to add passenger.";
        message.style.color = "red";
    }
});